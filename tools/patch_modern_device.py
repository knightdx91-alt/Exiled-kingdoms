#!/usr/bin/env python3
"""
Modern-Android (Android 11+ / Galaxy Z Fold 8) storage layer for an EK mod build.

Run from inside a disassembled tree (a dir containing `smali/`), like every other
patcher here. Applied by tools/build_modern_compat.sh on top of a finished mod APK.

WHY (full reasoning in deobf/MODERN_DEVICE_COMPAT.md §2 "Wall 4"):
  patch_export_fix.py repointed libGDX's external root from the app-specific
  directory to /sdcard/. That is correct on Android 4.2.2 (install-time
  permissions, always writable) and wrong on Android 11+, where writing there
  needs a RUNTIME grant this 2023 build never asks for. Save export/import and
  the crash log would all fail into a silent catch -- the owner's old
  "export does nothing" symptom, back again.

WHAT IT DOES (3 edits):
  1. adds net/fdgames/ek/android/EkStorage:
       ekInit(Activity)     - remember getExternalFilesDir(null) (always writable,
                              no permission needed) and, on API>=23, request
                              READ/WRITE_EXTERNAL_STORAGE once if not held.
       ekRoot(String)       - the external root, probe-tested ONCE and cached:
                              write+delete <sdcard>/Download/.ek_write_test; on
                              success the root is <sdcard>/ (stock v18/v19 behaviour, so the
                              tablet and a permission-granted phone are identical),
                              else the app-specific dir, else libGDX's own
                              externalFilesPath, else "/sdcard/".
       ekDir()              - <root>/Download as a File, for the crash logger.
  2. AndroidFiles.getExternalStoragePath() -> EkStorage.ekRoot(externalFilesPath).
     Export and import share this one root, so they stay symmetric and the
     engine's existing "Download/EK.bak" probes keep resolving.
  3. EkCrashLog's first write target -> EkStorage.ekDir() (same register, same
     File type; the existing /sdcard/ fallback chain is left alone).

Register discipline (this repo has burned two builds on VerifyErrors): every
branch here joins on a single type or on a register that is never read again,
and the API-23-only guard lives in its own tiny method so no int/reference
merge is possible at a join.
"""
import re
import sys

w = "."
PKG = 'net/fdgames/ek/android'
CL = f'L{PKG}/EkStorage;'

# ---------------------------------------------------------------------------
# 1) the helper class
# ---------------------------------------------------------------------------
open(f'{w}/smali/{PKG}/EkStorage.smali', 'w', encoding='utf-8').write(f'''\
.class public {CL}
.super Ljava/lang/Object;
.source "EkStorage.java"


# static fields
.field public static appDir:Ljava/lang/String;

.field public static cachedRoot:Ljava/lang/String;


# direct methods
.method public static ekInit(Landroid/app/Activity;)V
    .locals 0

    invoke-static {{p0}}, {CL}->ekSetAppDir(Landroid/content/Context;)V

    invoke-static {{p0}}, {CL}->ekAskPerm(Landroid/app/Activity;)V

    return-void
.end method

.method private static ekSetAppDir(Landroid/content/Context;)V
    .locals 3

    :try_start_dir
    const/4 v0, 0x0

    invoke-virtual {{p0, v0}}, Landroid/content/Context;->getExternalFilesDir(Ljava/lang/String;)Ljava/io/File;

    move-result-object v0

    if-eqz v0, :ekst_dirout

    invoke-virtual {{v0}}, Ljava/io/File;->mkdirs()Z

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {{v1}}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {{v0}}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {{v1, v0}}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v2, "/"

    invoke-virtual {{v1, v2}}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {{v1}}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    sput-object v0, {CL}->appDir:Ljava/lang/String;
    :try_end_dir
    .catch Ljava/lang/Throwable; {{:try_start_dir .. :try_end_dir}} :ekst_dircatch

    :ekst_dirout
    return-void

    :ekst_dircatch
    move-exception v0

    return-void
.end method

# true only when this is API>=23 AND the grant is missing. Kept separate so the
# API-23-only calls sit behind one boolean and no int/reference join exists.
.method private static ekNeedPerm(Landroid/content/Context;)Z
    .locals 2

    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v1, 0x17

    if-lt v0, v1, :ekst_noperm

    const-string v0, "android.permission.WRITE_EXTERNAL_STORAGE"

    invoke-virtual {{p0, v0}}, Landroid/content/Context;->checkSelfPermission(Ljava/lang/String;)I

    move-result v0

    if-eqz v0, :ekst_noperm

    const/4 v0, 0x1

    return v0

    :ekst_noperm
    const/4 v0, 0x0

    return v0
.end method

.method private static ekAskPerm(Landroid/app/Activity;)V
    .locals 3

    invoke-static {{p0}}, {CL}->ekNeedPerm(Landroid/content/Context;)Z

    move-result v0

    if-nez v0, :ekst_ask

    return-void

    :ekst_ask
    :try_start_perm
    const/4 v0, 0x2

    new-array v0, v0, [Ljava/lang/String;

    const/4 v1, 0x0

    const-string v2, "android.permission.WRITE_EXTERNAL_STORAGE"

    aput-object v2, v0, v1

    const/4 v1, 0x1

    const-string v2, "android.permission.READ_EXTERNAL_STORAGE"

    aput-object v2, v0, v1

    const/16 v1, 0x454b

    invoke-virtual {{p0, v0, v1}}, Landroid/app/Activity;->requestPermissions([Ljava/lang/String;I)V
    :try_end_perm
    .catch Ljava/lang/Throwable; {{:try_start_perm .. :try_end_perm}} :ekst_permcatch

    return-void

    :ekst_permcatch
    move-exception v0

    return-void
.end method

# <sdcard>/ if <sdcard>/Download is really writable, else null.
.method private static ekProbe()Ljava/lang/String;
    .locals 5

    :try_start_probe
    invoke-static {{}}, Landroid/os/Environment;->getExternalStorageDirectory()Ljava/io/File;

    move-result-object v0

    if-nez v0, :ekst_have

    const/4 v0, 0x0

    return-object v0

    :ekst_have
    new-instance v1, Ljava/io/File;

    const-string v2, "Download"

    invoke-direct {{v1, v0, v2}}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    invoke-virtual {{v1}}, Ljava/io/File;->mkdirs()Z

    new-instance v2, Ljava/io/File;

    const-string v3, ".ek_write_test"

    invoke-direct {{v2, v1, v3}}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    new-instance v3, Ljava/io/FileOutputStream;

    invoke-direct {{v3, v2}}, Ljava/io/FileOutputStream;-><init>(Ljava/io/File;)V

    const/16 v4, 0x45

    invoke-virtual {{v3, v4}}, Ljava/io/FileOutputStream;->write(I)V

    invoke-virtual {{v3}}, Ljava/io/FileOutputStream;->close()V

    invoke-virtual {{v2}}, Ljava/io/File;->delete()Z

    invoke-virtual {{v0}}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v0

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {{v1}}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {{v1, v0}}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v2, "/"

    invoke-virtual {{v1, v2}}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {{v1}}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0
    :try_end_probe
    .catch Ljava/lang/Throwable; {{:try_start_probe .. :try_end_probe}} :ekst_probecatch

    return-object v0

    :ekst_probecatch
    move-exception v0

    const/4 v0, 0x0

    return-object v0
.end method

.method public static ekRoot(Ljava/lang/String;)Ljava/lang/String;
    .locals 1

    sget-object v0, {CL}->cachedRoot:Ljava/lang/String;

    if-eqz v0, :ekst_compute

    return-object v0

    :ekst_compute
    invoke-static {{}}, {CL}->ekProbe()Ljava/lang/String;

    move-result-object v0

    if-nez v0, :ekst_store

    sget-object v0, {CL}->appDir:Ljava/lang/String;

    if-nez v0, :ekst_store

    move-object v0, p0

    if-nez v0, :ekst_store

    const-string v0, "/sdcard/"

    :ekst_store
    sput-object v0, {CL}->cachedRoot:Ljava/lang/String;

    return-object v0
.end method

.method public static ekDir()Ljava/io/File;
    .locals 3

    const/4 v0, 0x0

    invoke-static {{v0}}, {CL}->ekRoot(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    new-instance v1, Ljava/io/File;

    const-string v2, "Download"

    invoke-direct {{v1, v0, v2}}, Ljava/io/File;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    return-object v1
.end method
''')
print("added %s/EkStorage (ekInit / ekRoot / ekDir)" % PKG)

# ---------------------------------------------------------------------------
# 2) AndroidFiles.getExternalStoragePath() -> EkStorage.ekRoot(externalFilesPath)
# ---------------------------------------------------------------------------
p = f'{w}/smali/com/badlogic/gdx/backends/android/AndroidFiles.smali'
s = open(p, encoding='utf-8').read()
pat = re.compile(
    r'\.method public getExternalStoragePath\(\)Ljava/lang/String;.*?\.end method',
    re.S)
assert len(pat.findall(s)) == 1, "getExternalStoragePath() not found/unique"
s = pat.sub('''.method public getExternalStoragePath()Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Lcom/badlogic/gdx/backends/android/AndroidFiles;->externalFilesPath:Ljava/lang/String;

    invoke-static {v0}, ''' + CL + '''->ekRoot(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method''', s)
open(p, 'w', encoding='utf-8').write(s)
print("patched AndroidFiles.getExternalStoragePath() -> EkStorage.ekRoot()")

# ---------------------------------------------------------------------------
# 3) EkCrashLog: first write target -> EkStorage.ekDir()
#    Anchored on the instruction pair, with the register captured -- never on a
#    label name (apktool and baksmali number labels differently).
# ---------------------------------------------------------------------------
p = f'{w}/smali/{PKG}/EkCrashLog.smali'
s = open(p, encoding='utf-8').read()
pat = re.compile(
    r'sget-object (v\d+), Landroid/os/Environment;->DIRECTORY_DOWNLOADS:Ljava/lang/String;\s*\n\s*\n'
    r'\s*invoke-static \{\1\}, Landroid/os/Environment;->getExternalStoragePublicDirectory'
    r'\(Ljava/lang/String;\)Ljava/io/File;')
m = pat.search(s)
assert m and len(pat.findall(s)) == 1, "EkCrashLog Downloads-dir anchor not found/unique"
s = pat.sub('invoke-static {}, ' + CL + '->ekDir()Ljava/io/File;', s, count=1)
open(p, 'w', encoding='utf-8').write(s)
print("patched EkCrashLog: crash report dir -> EkStorage.ekDir()")

# ---------------------------------------------------------------------------
# 4) MainActivity.onCreate: EkStorage.ekInit(this) before anything else
# ---------------------------------------------------------------------------
p = f'{w}/smali/{PKG}/MainActivity.smali'
s = open(p, encoding='utf-8').read()
pat = re.compile(r'(\.method public onCreate\(Landroid/os/Bundle;\)V\s*\n\s*\.locals \d+\s*\n)')
assert len(pat.findall(s)) == 1, "MainActivity.onCreate anchor not found/unique"
assert 'EkStorage;->ekInit' not in s, "already patched"
s = pat.sub(r'\1\n    invoke-static {p0}, ' + CL + '->ekInit(Landroid/app/Activity;)V\n', s, count=1)
open(p, 'w', encoding='utf-8').write(s)
print("patched MainActivity.onCreate: EkStorage.ekInit(this)")
print("DONE")
