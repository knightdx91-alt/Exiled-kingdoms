// OpenXR bridge for Exiled Kingdoms VR (Quest 3S). Java side: ekvr.android.Xr.
// Spec: deobf/QUEST_VR_SPEC.md §4.
//
// One thread (the game's render thread, holding the EGL context) calls everything:
//   init()        instance + session on the caller's EGL context, LOCAL_FLOOR space,
//                 one GL swapchain per eye, Touch-controller actions, optional FB passthrough
//   beginFrame()  events, xrWaitFrame/BeginFrame, eye views, controllers, acquire images
//   endFrame()    release images, submit the projection layer (over passthrough if on)
// Data is exchanged through direct buffers the Java side allocates once:
//   views  [eye*11]: qx qy qz qw px py pz tanL tanR tanU tanD  (LOCAL_FLOOR space)
//   input  [hand*16]: active, qx qy qz qw px py pz (aim), trigger, grip, stickX, stickY,
//                     a/x, b/y, stickClick, menu        (hand 0 = left, 1 = right)
//   images [0..1] = swapchain image index per eye this frame
#include <jni.h>
#include <string.h>
#include <stdlib.h>
#include <math.h>
#include <android/log.h>
#include <EGL/egl.h>
#include <GLES3/gl3.h>
#define XR_USE_PLATFORM_ANDROID
#define XR_USE_GRAPHICS_API_OPENGL_ES
#include <openxr/openxr.h>
#include <openxr/openxr_platform.h>

#define LOG(...) __android_log_print(ANDROID_LOG_INFO, "ekxr", __VA_ARGS__)
#define CHK(x) do { XrResult r_ = (x); if (XR_FAILED(r_)) { LOG("%s failed: %d", #x, r_); return 0; } } while (0)

static XrInstance inst;
static XrSystemId sys;
static XrSession sess;
static XrSpace space, aimSpace[2];
static XrSwapchain chain[2];
static uint32_t chainLen[2];
static XrSwapchainImageOpenGLESKHR chainImg[2][8];
static int32_t W, H;
static XrSessionState state = XR_SESSION_STATE_UNKNOWN;
static int running, frameOk;
static XrFrameState frame;
static XrView views[2];
static XrActionSet aset;
static XrAction aAim, aTrig, aGrip, aStick, aPrim, aSec, aStickClick, aMenu;
static XrPath hand[2];
static float *vBuf, *iBuf;
static int *imgBuf;
// passthrough (XR_FB_passthrough), optional
static int hasPt;
static XrPassthroughFB pt;
static XrPassthroughLayerFB ptLayer;
static PFN_xrCreatePassthroughFB xrCreatePassthroughFB_;
static PFN_xrPassthroughStartFB xrPassthroughStartFB_;
static PFN_xrCreatePassthroughLayerFB xrCreatePassthroughLayerFB_;
static PFN_xrPassthroughLayerResumeFB xrPassthroughLayerResumeFB_;

static XrPath path(const char *s) { XrPath p = 0; xrStringToPath(inst, s, &p); return p; }

static XrAction action(const char *name, XrActionType type, int perHand) {
    XrActionCreateInfo ai = {XR_TYPE_ACTION_CREATE_INFO};
    strcpy(ai.actionName, name);
    strcpy(ai.localizedActionName, name);
    ai.actionType = type;
    if (perHand) { ai.countSubactionPaths = 2; ai.subactionPaths = hand; }
    XrAction a = XR_NULL_HANDLE;
    xrCreateAction(aset, &ai, &a);
    return a;
}

static int suggest(const char *profile) {
    XrActionSuggestedBinding b[] = {
        {aAim, path("/user/hand/left/input/aim/pose")}, {aAim, path("/user/hand/right/input/aim/pose")},
        {aTrig, path("/user/hand/left/input/trigger/value")}, {aTrig, path("/user/hand/right/input/trigger/value")},
        {aGrip, path("/user/hand/left/input/squeeze/value")}, {aGrip, path("/user/hand/right/input/squeeze/value")},
        {aStick, path("/user/hand/left/input/thumbstick")}, {aStick, path("/user/hand/right/input/thumbstick")},
        {aStickClick, path("/user/hand/left/input/thumbstick/click")}, {aStickClick, path("/user/hand/right/input/thumbstick/click")},
        {aPrim, path("/user/hand/left/input/x/click")}, {aPrim, path("/user/hand/right/input/a/click")},
        {aSec, path("/user/hand/left/input/y/click")}, {aSec, path("/user/hand/right/input/b/click")},
        {aMenu, path("/user/hand/left/input/menu/click")},
    };
    XrInteractionProfileSuggestedBinding s = {XR_TYPE_INTERACTION_PROFILE_SUGGESTED_BINDING};
    s.interactionProfile = path(profile);
    s.suggestedBindings = b;
    s.countSuggestedBindings = sizeof(b) / sizeof(b[0]);
    return XR_SUCCEEDED(xrSuggestInteractionProfileBindings(inst, &s));
}

JNIEXPORT jboolean JNICALL Java_ekvr_android_Xr_init(JNIEnv *env, jclass cls, jobject activity,
        jlong display, jlong config, jlong context, jobject views_, jobject input_, jobject images_) {
    JavaVM *vm;
    (*env)->GetJavaVM(env, &vm);
    vBuf = (*env)->GetDirectBufferAddress(env, views_);
    iBuf = (*env)->GetDirectBufferAddress(env, input_);
    imgBuf = (*env)->GetDirectBufferAddress(env, images_);
    jobject act = (*env)->NewGlobalRef(env, activity);

    PFN_xrInitializeLoaderKHR initLoader;
    xrGetInstanceProcAddr(XR_NULL_HANDLE, "xrInitializeLoaderKHR", (PFN_xrVoidFunction *) &initLoader);
    XrLoaderInitInfoAndroidKHR li = {XR_TYPE_LOADER_INIT_INFO_ANDROID_KHR};
    li.applicationVM = vm;
    li.applicationContext = act;
    if (initLoader) initLoader((XrLoaderInitInfoBaseHeaderKHR *) &li);

    uint32_t n = 0;
    xrEnumerateInstanceExtensionProperties(NULL, 0, &n, NULL);
    XrExtensionProperties *props = calloc(n, sizeof(*props));
    for (uint32_t i = 0; i < n; i++) props[i].type = XR_TYPE_EXTENSION_PROPERTIES;
    xrEnumerateInstanceExtensionProperties(NULL, n, &n, props);
    const char *exts[4] = {XR_KHR_OPENGL_ES_ENABLE_EXTENSION_NAME, XR_KHR_ANDROID_CREATE_INSTANCE_EXTENSION_NAME};
    uint32_t ne = 2;
    for (uint32_t i = 0; i < n; i++)
        if (!strcmp(props[i].extensionName, XR_FB_PASSTHROUGH_EXTENSION_NAME)) { exts[ne++] = XR_FB_PASSTHROUGH_EXTENSION_NAME; hasPt = 1; }
    free(props);

    XrInstanceCreateInfoAndroidKHR ia = {XR_TYPE_INSTANCE_CREATE_INFO_ANDROID_KHR};
    ia.applicationVM = vm;
    ia.applicationActivity = act;
    XrInstanceCreateInfo ci = {XR_TYPE_INSTANCE_CREATE_INFO};
    ci.next = &ia;
    strcpy(ci.applicationInfo.applicationName, "Exiled Kingdoms VR");
    ci.applicationInfo.applicationVersion = 1;
    strcpy(ci.applicationInfo.engineName, "libGDX + ekvr");
    ci.applicationInfo.apiVersion = XR_MAKE_VERSION(1, 0, 34);
    ci.enabledExtensionCount = ne;
    ci.enabledExtensionNames = exts;
    CHK(xrCreateInstance(&ci, &inst));

    XrSystemGetInfo sg = {XR_TYPE_SYSTEM_GET_INFO};
    sg.formFactor = XR_FORM_FACTOR_HEAD_MOUNTED_DISPLAY;
    CHK(xrGetSystem(inst, &sg, &sys));

    PFN_xrGetOpenGLESGraphicsRequirementsKHR req;
    xrGetInstanceProcAddr(inst, "xrGetOpenGLESGraphicsRequirementsKHR", (PFN_xrVoidFunction *) &req);
    XrGraphicsRequirementsOpenGLESKHR gr = {XR_TYPE_GRAPHICS_REQUIREMENTS_OPENGL_ES_KHR};
    req(inst, sys, &gr);

    XrGraphicsBindingOpenGLESAndroidKHR gb = {XR_TYPE_GRAPHICS_BINDING_OPENGL_ES_ANDROID_KHR};
    gb.display = (EGLDisplay) (intptr_t) display;
    gb.config = (EGLConfig) (intptr_t) config;
    gb.context = (EGLContext) (intptr_t) context;
    XrSessionCreateInfo sc = {XR_TYPE_SESSION_CREATE_INFO};
    sc.next = &gb;
    sc.systemId = sys;
    CHK(xrCreateSession(inst, &sc, &sess));

    XrReferenceSpaceCreateInfo rs = {XR_TYPE_REFERENCE_SPACE_CREATE_INFO};
    rs.poseInReferenceSpace.orientation.w = 1;
    rs.referenceSpaceType = XR_REFERENCE_SPACE_TYPE_LOCAL_FLOOR_EXT;
    if (XR_FAILED(xrCreateReferenceSpace(sess, &rs, &space))) {
        rs.referenceSpaceType = XR_REFERENCE_SPACE_TYPE_STAGE;
        if (XR_FAILED(xrCreateReferenceSpace(sess, &rs, &space))) {
            rs.referenceSpaceType = XR_REFERENCE_SPACE_TYPE_LOCAL;
            CHK(xrCreateReferenceSpace(sess, &rs, &space));
        }
    }

    XrViewConfigurationView vcv[2] = {{XR_TYPE_VIEW_CONFIGURATION_VIEW}, {XR_TYPE_VIEW_CONFIGURATION_VIEW}};
    uint32_t nv = 0;
    CHK(xrEnumerateViewConfigurationViews(inst, sys, XR_VIEW_CONFIGURATION_TYPE_PRIMARY_STEREO, 2, &nv, vcv));
    W = vcv[0].recommendedImageRectWidth;
    H = vcv[0].recommendedImageRectHeight;
    for (int e = 0; e < 2; e++) {
        XrSwapchainCreateInfo sci = {XR_TYPE_SWAPCHAIN_CREATE_INFO};
        sci.usageFlags = XR_SWAPCHAIN_USAGE_COLOR_ATTACHMENT_BIT | XR_SWAPCHAIN_USAGE_SAMPLED_BIT;
        sci.format = GL_RGBA8;          // EK colours are already sRGB-encoded: write them as-is
        sci.sampleCount = 1;
        sci.width = W;
        sci.height = H;
        sci.faceCount = 1;
        sci.arraySize = 1;
        sci.mipCount = 1;
        CHK(xrCreateSwapchain(sess, &sci, &chain[e]));
        uint32_t cnt = 0;
        xrEnumerateSwapchainImages(chain[e], 0, &cnt, NULL);
        if (cnt > 8) cnt = 8;
        for (uint32_t i = 0; i < cnt; i++) chainImg[e][i].type = XR_TYPE_SWAPCHAIN_IMAGE_OPENGL_ES_KHR;
        xrEnumerateSwapchainImages(chain[e], cnt, &cnt, (XrSwapchainImageBaseHeader *) chainImg[e]);
        chainLen[e] = cnt;
    }
    views[0].type = views[1].type = XR_TYPE_VIEW;

    // controllers
    hand[0] = path("/user/hand/left");
    hand[1] = path("/user/hand/right");
    XrActionSetCreateInfo as = {XR_TYPE_ACTION_SET_CREATE_INFO};
    strcpy(as.actionSetName, "game");
    strcpy(as.localizedActionSetName, "Game");
    CHK(xrCreateActionSet(inst, &as, &aset));
    aAim = action("aim", XR_ACTION_TYPE_POSE_INPUT, 1);
    aTrig = action("trigger", XR_ACTION_TYPE_FLOAT_INPUT, 1);
    aGrip = action("grip", XR_ACTION_TYPE_FLOAT_INPUT, 1);
    aStick = action("stick", XR_ACTION_TYPE_VECTOR2F_INPUT, 1);
    aStickClick = action("stick_click", XR_ACTION_TYPE_BOOLEAN_INPUT, 1);
    aPrim = action("primary", XR_ACTION_TYPE_BOOLEAN_INPUT, 1);
    aSec = action("secondary", XR_ACTION_TYPE_BOOLEAN_INPUT, 1);
    aMenu = action("menu", XR_ACTION_TYPE_BOOLEAN_INPUT, 1);
    suggest("/interaction_profiles/oculus/touch_controller");
    suggest("/interaction_profiles/meta/touch_controller_plus");
    for (int h = 0; h < 2; h++) {
        XrActionSpaceCreateInfo asi = {XR_TYPE_ACTION_SPACE_CREATE_INFO};
        asi.action = aAim;
        asi.subactionPath = hand[h];
        asi.poseInActionSpace.orientation.w = 1;
        xrCreateActionSpace(sess, &asi, &aimSpace[h]);
    }
    XrSessionActionSetsAttachInfo at = {XR_TYPE_SESSION_ACTION_SETS_ATTACH_INFO};
    at.countActionSets = 1;
    at.actionSets = &aset;
    CHK(xrAttachSessionActionSets(sess, &at));

    if (hasPt) {
        xrGetInstanceProcAddr(inst, "xrCreatePassthroughFB", (PFN_xrVoidFunction *) &xrCreatePassthroughFB_);
        xrGetInstanceProcAddr(inst, "xrPassthroughStartFB", (PFN_xrVoidFunction *) &xrPassthroughStartFB_);
        xrGetInstanceProcAddr(inst, "xrCreatePassthroughLayerFB", (PFN_xrVoidFunction *) &xrCreatePassthroughLayerFB_);
        xrGetInstanceProcAddr(inst, "xrPassthroughLayerResumeFB", (PFN_xrVoidFunction *) &xrPassthroughLayerResumeFB_);
        XrPassthroughCreateInfoFB pci = {XR_TYPE_PASSTHROUGH_CREATE_INFO_FB};
        if (XR_SUCCEEDED(xrCreatePassthroughFB_(sess, &pci, &pt))) {
            XrPassthroughLayerCreateInfoFB lci = {XR_TYPE_PASSTHROUGH_LAYER_CREATE_INFO_FB};
            lci.passthrough = pt;
            lci.purpose = XR_PASSTHROUGH_LAYER_PURPOSE_RECONSTRUCTION_FB;
            if (XR_FAILED(xrCreatePassthroughLayerFB_(sess, &lci, &ptLayer))) hasPt = 0;
            else { xrPassthroughStartFB_(pt); xrPassthroughLayerResumeFB_(ptLayer); }
        } else hasPt = 0;
    }
    LOG("OpenXR ready: %dx%d per eye, passthrough %d", W, H, hasPt);
    return 1;
}

JNIEXPORT jint JNICALL Java_ekvr_android_Xr_width(JNIEnv *e, jclass c) { return W; }
JNIEXPORT jint JNICALL Java_ekvr_android_Xr_height(JNIEnv *e, jclass c) { return H; }
JNIEXPORT jboolean JNICALL Java_ekvr_android_Xr_hasPassthrough(JNIEnv *e, jclass c) { return hasPt; }
JNIEXPORT jint JNICALL Java_ekvr_android_Xr_image(JNIEnv *e, jclass c, jint eye, jint i) {
    return i < (int) chainLen[eye] ? (jint) chainImg[eye][i].image : 0;
}
JNIEXPORT jint JNICALL Java_ekvr_android_Xr_imageCount(JNIEnv *e, jclass c, jint eye) { return chainLen[eye]; }

static void putPose(float *o, XrPosef p) {
    o[0] = p.orientation.x; o[1] = p.orientation.y; o[2] = p.orientation.z; o[3] = p.orientation.w;
    o[4] = p.position.x; o[5] = p.position.y; o[6] = p.position.z;
}

// 1 = render this frame, 0 = skip rendering (not focused/visible), -1 = quit
JNIEXPORT jint JNICALL Java_ekvr_android_Xr_beginFrame(JNIEnv *env, jclass cls) {
    XrEventDataBuffer ev = {XR_TYPE_EVENT_DATA_BUFFER};
    while (xrPollEvent(inst, &ev) == XR_SUCCESS) {
        if (ev.type == XR_TYPE_EVENT_DATA_SESSION_STATE_CHANGED) {
            state = ((XrEventDataSessionStateChanged *) &ev)->state;
            if (state == XR_SESSION_STATE_READY) {
                XrSessionBeginInfo bi = {XR_TYPE_SESSION_BEGIN_INFO};
                bi.primaryViewConfigurationType = XR_VIEW_CONFIGURATION_TYPE_PRIMARY_STEREO;
                xrBeginSession(sess, &bi);
                running = 1;
            } else if (state == XR_SESSION_STATE_STOPPING) {
                xrEndSession(sess);
                running = 0;
            } else if (state == XR_SESSION_STATE_EXITING || state == XR_SESSION_STATE_LOSS_PENDING) {
                return -1;
            }
        }
        ev.type = XR_TYPE_EVENT_DATA_BUFFER;
    }
    frameOk = 0;
    if (!running) return 0;
    frame.type = XR_TYPE_FRAME_STATE;
    XrFrameWaitInfo wi = {XR_TYPE_FRAME_WAIT_INFO};
    if (XR_FAILED(xrWaitFrame(sess, &wi, &frame))) return 0;
    XrFrameBeginInfo fb = {XR_TYPE_FRAME_BEGIN_INFO};
    if (XR_FAILED(xrBeginFrame(sess, &fb))) return 0;
    frameOk = 1;
    if (!frame.shouldRender) return 0;

    XrViewLocateInfo vl = {XR_TYPE_VIEW_LOCATE_INFO};
    vl.viewConfigurationType = XR_VIEW_CONFIGURATION_TYPE_PRIMARY_STEREO;
    vl.displayTime = frame.predictedDisplayTime;
    vl.space = space;
    XrViewState vs = {XR_TYPE_VIEW_STATE};
    uint32_t nv = 0;
    xrLocateViews(sess, &vl, &vs, 2, &nv, views);
    for (int e = 0; e < 2; e++) {
        float *o = vBuf + e * 11;
        putPose(o, views[e].pose);
        o[7] = tanf(views[e].fov.angleLeft); o[8] = tanf(views[e].fov.angleRight);
        o[9] = tanf(views[e].fov.angleUp); o[10] = tanf(views[e].fov.angleDown);
    }

    XrActiveActionSet aas = {aset, XR_NULL_PATH};
    XrActionsSyncInfo si = {XR_TYPE_ACTIONS_SYNC_INFO};
    si.countActiveActionSets = 1;
    si.activeActionSets = &aas;
    xrSyncActions(sess, &si);
    for (int h = 0; h < 2; h++) {
        float *o = iBuf + h * 16;
        XrActionStateGetInfo gi = {XR_TYPE_ACTION_STATE_GET_INFO};
        gi.subactionPath = hand[h];
        XrSpaceLocation loc = {XR_TYPE_SPACE_LOCATION};
        xrLocateSpace(aimSpace[h], space, frame.predictedDisplayTime, &loc);
        o[0] = (loc.locationFlags & XR_SPACE_LOCATION_ORIENTATION_VALID_BIT) ? 1 : 0;
        putPose(o + 1, loc.pose);
        XrActionStateFloat f = {XR_TYPE_ACTION_STATE_FLOAT};
        gi.action = aTrig; xrGetActionStateFloat(sess, &gi, &f); o[8] = f.currentState;
        gi.action = aGrip; xrGetActionStateFloat(sess, &gi, &f); o[9] = f.currentState;
        XrActionStateVector2f v = {XR_TYPE_ACTION_STATE_VECTOR2F};
        gi.action = aStick; xrGetActionStateVector2f(sess, &gi, &v); o[10] = v.currentState.x; o[11] = v.currentState.y;
        XrActionStateBoolean b = {XR_TYPE_ACTION_STATE_BOOLEAN};
        gi.action = aPrim; xrGetActionStateBoolean(sess, &gi, &b); o[12] = b.currentState;
        gi.action = aSec; xrGetActionStateBoolean(sess, &gi, &b); o[13] = b.currentState;
        gi.action = aStickClick; xrGetActionStateBoolean(sess, &gi, &b); o[14] = b.currentState;
        gi.action = aMenu; xrGetActionStateBoolean(sess, &gi, &b); o[15] = b.currentState;
    }
    for (int e = 0; e < 2; e++) {
        XrSwapchainImageAcquireInfo ai = {XR_TYPE_SWAPCHAIN_IMAGE_ACQUIRE_INFO};
        uint32_t idx = 0;
        xrAcquireSwapchainImage(chain[e], &ai, &idx);
        XrSwapchainImageWaitInfo wti = {XR_TYPE_SWAPCHAIN_IMAGE_WAIT_INFO};
        wti.timeout = XR_INFINITE_DURATION;
        xrWaitSwapchainImage(chain[e], &wti);
        imgBuf[e] = idx;
    }
    return 1;
}

JNIEXPORT void JNICALL Java_ekvr_android_Xr_endFrame(JNIEnv *env, jclass cls, jboolean rendered, jboolean passthrough) {
    if (!frameOk) return;
    XrCompositionLayerProjectionView pv[2] = {{XR_TYPE_COMPOSITION_LAYER_PROJECTION_VIEW}, {XR_TYPE_COMPOSITION_LAYER_PROJECTION_VIEW}};
    XrCompositionLayerProjection proj = {XR_TYPE_COMPOSITION_LAYER_PROJECTION};
    XrCompositionLayerPassthroughFB ptl = {XR_TYPE_COMPOSITION_LAYER_PASSTHROUGH_FB};
    const XrCompositionLayerBaseHeader *layers[2];
    uint32_t nl = 0;
    if (rendered) {
        for (int e = 0; e < 2; e++) {
            XrSwapchainImageReleaseInfo ri = {XR_TYPE_SWAPCHAIN_IMAGE_RELEASE_INFO};
            xrReleaseSwapchainImage(chain[e], &ri);
            pv[e].pose = views[e].pose;
            pv[e].fov = views[e].fov;
            pv[e].subImage.swapchain = chain[e];
            pv[e].subImage.imageRect.extent.width = W;
            pv[e].subImage.imageRect.extent.height = H;
        }
        if (passthrough && hasPt) {
            ptl.layerHandle = ptLayer;
            ptl.flags = XR_COMPOSITION_LAYER_BLEND_TEXTURE_SOURCE_ALPHA_BIT;
            layers[nl++] = (XrCompositionLayerBaseHeader *) &ptl;
            proj.layerFlags = XR_COMPOSITION_LAYER_BLEND_TEXTURE_SOURCE_ALPHA_BIT |
                              XR_COMPOSITION_LAYER_UNPREMULTIPLIED_ALPHA_BIT;
        }
        proj.space = space;
        proj.viewCount = 2;
        proj.views = pv;
        layers[nl++] = (XrCompositionLayerBaseHeader *) &proj;
    }
    XrFrameEndInfo fe = {XR_TYPE_FRAME_END_INFO};
    fe.displayTime = frame.predictedDisplayTime;
    fe.environmentBlendMode = XR_ENVIRONMENT_BLEND_MODE_OPAQUE;
    fe.layerCount = nl;
    fe.layers = layers;
    xrEndFrame(sess, &fe);
}
