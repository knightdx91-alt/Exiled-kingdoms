# MP content pack merge (item 12 + the art side of item 4)

Source: the owner's `Exiled-Kingdoms-Multiplayer-PVP-v1-3-1218-mod.apk` (Drive). Tool:
`tools/merge_mp_content.py`, run by `build_mod_4_2_2.sh` step 2b when `EK_MP_APK` is set,
BEFORE our patches (so ours apply on top of the mod's versions of shared files).

## Why this is safe (verified)
* **Official content is identical**: vanilla 2025 (1217) assets vs our base (1207) = byte-identical
  (only two stray log files differ). Every file the mod adds or changes is the mod's own
  (Sorrow-Mod content + the multiplayer/arena additions).
* **Same data model**: all enums in `net/fdgames` (97 script actions, 43 conditions, item/weapon/
  damage/effect types, races, classes…) are identical by name; rule/world/spawn/quest tables keep
  the same columns (only `items_text.txt` appends `name_FR/desc_FR`, and loaders read by index).
* **Our patches re-apply cleanly** on the mod's versions: items, bestiary, the six skill tables,
  the four companion conversations, `G9.tmx` (made whitespace-tolerant — the mod re-saved it in
  Tiled with ` />`, object ids, and no `unique_tag` on janod; NPCSpawn/Despawn use the `tag`),
  strings/texts. No duplicate conversation ids introduced (Janod: mod rows 58–60, ours 61–68).
* **Atlases**: all 14 `.pack` files have the same regions; the 82 changed graphics are repainted
  PNGs. UI skin image keeps its 256×128 layout (atlas unchanged).
* **Missing item icons**: ~200 of the mod's 961 new items name icons that exist nowhere in the
  pack; both engines then fall back to a portrait texture (`Assets.c` / theirs `Assets.b`) — no
  crash, identical behaviour to the mod.

## What comes in (counts)
9,258 new + 946 changed files: conversations (8,503 new / 668 changed), maps 248/13, quests
92/115, sprites 363, graphics 401/82, sounds 311, music 31, world 13/14, ui 31/35, rules 8/19.
Media (mp3/ogg/wav/png/jpg) are zipped STORED (Android streams them via openFd).

## Excluded (not content)
Everything outside `assets/data/` (PDALIFE repack files, 18 random-named blobs, `hs_err` log,
`init_log.txt`); `conversations/RU/_backup_original/` (711); `*_ORIGINAL_BACKUP*`; `*.bak*`;
`old_*`/`old-*` in `data/ui` (the modder's pre-redesign art); `desktop.ini`, `*.bat`, `*.bmfc`.
Legit files starting with "old" elsewhere (e.g. `sprites/staticNPC/old_female.png`) are kept.

## Content-level changes the owner should know about
* **Every bestiary entry is +3 levels** (e.g. Grissenda 2→5, skeletons 3–5→6–8) — the mod's global
  difficulty choice. Our summons are unaffected (their level comes from caster level + rank).
* Skills: more skills NPC-usable, some class restrictions removed, Town Gate cooldown 20→3,
  Summoner renamed "Summonerr" + a new "Summoner"; 12 new Sorrow skills are data-only (no code in
  the mod either — they behave the same in both).
* A "collar of submission" (item 10402) follower mechanic appears in companion conversations
  (e.g. Janod: mod rows 58–60); it coexists with our Janod recruitment.
* The main-menu logo image is taller (512×341); the mod's redesigned menu doesn't draw it — handled
  with the menu work (item 4, code side).
* APK grows from ~127 MB to ~374 MB.
