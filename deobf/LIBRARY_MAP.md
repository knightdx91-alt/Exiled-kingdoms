# Third-party library tier — identified

The non-EK obfuscated files (149) are all **stock third-party libraries** shaded into
single-letter packages by R8. Identified by jadx `compiled from:` names + class signatures.
**None are EK game logic** — verified by checking the "unclassified" set (they resolved to
libGDX math/net and gdx-pay, not EK). Behaviour is known from each library's public source,
so these need no reversing. Full per-file list with owners is in `CLASS_MAP.tsv`.

| Library | Files | What it is | Relevance to the web port |
|---|---|---|---|
| **libGDX** | 72 | The game engine: `scenes.scene2d` UI, `maps`/`TiledMap*` loaders, `graphics` (Batch/Mesh/Shader/Texture), `math` (MathUtils, RandomXS128, Intersector, EarClippingTriangulator…), `net` (Socket/ServerSocket), `assets`, `audio`, `utils` (Array/Pool/Json). | **Reimplemented, not ported** — Phaser 3 is our libGDX equivalent. Useful as the behavioural reference for what EK's own classes call into (e.g. `TmxMapLoader`, `OrthographicCamera`). |
| **box2dlights** | 6 | 2D lighting: `RayHandler` (ambient + shadows), `PointLight`/`PositionalLight`/`Light`/`LightMap`, `BlendFunc`. | **The lighting model we're matching** (A1). GameLevelRenderer sets its ambient + adds PointLights. Our flat tint approximates `RayHandler`. |
| **gdx-pay + Google Play Billing** | 15 | In-app purchase: `PurchaseManager`, `PurchaseObserver`, `Transaction`, `Offer`, `GdxPayException`, `PurchaseManagerGoogleBilling`. | **Skipped per request (billing).** No web equivalent needed. |
| **AndroidX / support** | 41 | Android UI shim: AppCompat, ActionBar/Menu, Typeface/Fonts, DrawableWrapper, lifecycle/collection helpers. | **N/A for web** — Android-platform glue; the browser has no analogue. |
| **platform/backend** | 3 | `Analytics`, `AndroidResolver`, `Provider` — platform service shims. | N/A for web. |
| **Android-generated / synthetic** | 12 | `k/*` = generated `R.*` resource-ID tables, `c/a` resource holder, `e0/c` = an R8 lambda. | **No logic to decode** — generated boilerplate. |

## Bottom line
Across all **292** obfuscated files: the **EK-owned** tier (143) is fully catalogued
(`ENGINE_SPEC.md` + `UI_SPEC.md`, billing excepted); the **library** tier (149) is
identified and needs no reversal (public source). The only files with no recovered name
are 12 generated/synthetic ones with nothing to decode, plus `a/a` (billing). Nothing
game-relevant remains unaccounted for.
