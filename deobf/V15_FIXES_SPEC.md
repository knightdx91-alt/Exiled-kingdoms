# v15 — five owner-reported fixes (reversed against the 4.2.2 base)

Base APK sha256 `5fc7c866…` (the owner's clean build). Every finding below was read
straight out of the base dex disassembly, not guessed.

## 1. Wizard companion ignores its defensive kit (Mage Barrier / Mage Armor)
`AISkillUsage.ekWizardAI()` (added in v8) only ever rolled the three offensive
spells `fireball / ice_storm / lightning_bolt`. **Mage Barrier is a *passive*** that
only does anything while **Mage Armor is active** (`skills_advanced2.txt`: Mage Barrier
"Enemies hitting you while your Mage Armor is active…"), and the AI never cast Mage
Armor, so the barrier could never trigger.
**Fix:** add `mage_armor` (a self-buff — the existing `Character.c(id)` cast path applies
it to the caster; the trailing `o(enemyID)` target is ignored for a buff) to
`WIZARD_SPELLS`, ordered last so offence keeps priority.

## 2. Buying a mage skill on the Hero grants no usable mana
`CharacterSheet.C()I` (max mana) **short-circuits on `V()Z` at the very top** — returns 0
when `V()` is false. `V()` returns true only for `CharacterClass.e` (WIZARD) or `.d`
(CLERIC). The Hero is `CharacterClass.b` (WARRIOR) → `V()` false → `C()` returns 0 →
**no mana pool at all**, so the v3 hero-mana patch on `CharacterStats.g()` was dead code
(`C()` never reaches the `g()` add).
**Fix:** (a) `CharacterSheet.V()` also returns true for `CharacterClass.b`; (b) the Hero
branch of `CharacterStats.g()` forces the multiplier to 2 so the base pool scales as
`level*2 + 12` (the same formula the mod already gives race-NPC casters) instead of the
flat `level*0 + 12`. Current mana = `C() - missingMana`; `missingMana` starts at 0, so
the pool is full the moment it appears — no refill hook needed.

## 3. Not every skill shows on a companion sheet (incl. Grissenda, a Hero)
`SkillWindow` only lists a skill on an NPC sheet when the skill's **`NPC` column (col 2)
is `Y`**. Across the six base skill files most rows are `N` — e.g. all four mage actives,
every advanced-mage skill (Mage Barrier, Fire/Ice/Earth Mastery, …), Charge, Stealth,
Evasion, Sacred Fire. v8 flipped only six of them.
**Fix:** flip **every** skill row's `NPC` column to `Y` in the six base files
(`skills.txt`, `skills2.txt`, `skills3.txt`, `skills_advanced.txt`, `skills_advanced2.txt`,
`skills_advanced3.txt`), so the full basic + advanced set is visible for any companion of
any class. The four newly-touched files are added to the build's extract/repack lists.

## 4. Summon-Familiar route dialog never appears; always summons vanilla familiars
**Root cause (certain):** `GameVariables.b(String)I` returns **`-255`** for an *unset*
variable (it only returns 0 for names containing `REP_ / rumor_ / GL_`; everything else
falls to the `-0xff` return). The v9 feature assumed unset = 0:
* `ekNeedsSummonRoute` gated the dialog on `if-nez var → skip`; `-255 ≠ 0`, so the dialog
  was **always** skipped.
* `ekSummonId/ekSummonCap` matched the route against 1/2/3; `-255` matched none and fell
  through to `DEFAULT_ROUTE = 2` (Arcane) → the plain familiar the owner saw.
**Fix:** in `ekNeedsSummonRoute`, treat "route chosen" as `var > 0` (`if-gtz var → skip`)
so an unset `-255` correctly prompts; and drop the `rank == 0` gate so players who already
knew `lesser_summoning` before the mod still get to pick a path on their next purchase.
(The saved routes are 1/2/3, always `> 0`, so a set route still suppresses the prompt.)

## 5. Viewing character Details → NullPointerException (CONFIRMED from device log)
Owner's `EK_crash.txt` (2026-08-05):
```
java.lang.NullPointerException
  at ...SpriteBatch.draw  ->  ...TextureRegionDrawable.draw
  at ...InventorySlotImage.draw   (e/a/d/e/r)
  ... Stage.draw  ->  e.a.d.y.j (GameHUD.java)
```
`InventorySlotImage.a(I)` sets a slot to an item id: it scans `Rules.a[]` for the item,
reads its `icon`, and `Assets.b(icon)` → `TextureRegion`. **If the id is not in `Rules.a[]`
(row removed) or the icon asset is missing, the region register stays null**, yet the code
still builds `new TextureRegionDrawable(null)` and stores it as `e`; `draw()` then draws
`e` and NPEs inside `SpriteBatch.draw`. This is triggered whenever the character screen /
details panel redraws a slot holding such an item — most notably the **cheat items
(Tome of Renown / Phase Stone / Anchor Stone, ids 9990-9992) removed from `items.txt` in
v13**: any save that picked one up before v13 now holds an orphaned id.
**Fix (`tools/patch_inventory_icon_fix.py`):** in `a(I)`, when the resolved region is null,
leave `e` null (which `draw()` already skips) instead of wrapping null. The orphaned item
just shows an empty slot; no crash.

*(A separate, harmless hardening also went in: `c0.b()` — the skill-window Details button —
no-ops when no skill is selected, closing a latent null-deref there. It is not the crash
above.)*

## 6. (v16) Character Details → NullPointerException, second crash (device-confirmed)
After §5 stopped the draw NPE, the owner's next `EK_crash.txt` named a different site:
```
RuntimeException: Actor: u: Details
Caused by: NullPointerException
  at MainActivity.f  <- SettingsData.a <- Settings.e <- Analytics.a <- GPGSUpdate.a
  at StatsDetailWindow(h0).a  <- CharacterWindow(e).touchDown
```
`MainActivity.f()` (a device-id hash used by analytics / Play-Games) reads
`Settings.Secure.getString(resolver, "android_ld")` and immediately calls `.length()`.
On the 4.2.2 device that secure row is **null** → NPE, and opening the stats-detail
window runs the analytics update that calls it. `f()` already computes a `"99999"`
fallback when the id tail isn't hex.
**Fix (`tools/patch_gpgs_deviceid_fix.py`):** null-check the getString result; a null id
takes the existing `"99999"` fallback instead of being dereferenced.
