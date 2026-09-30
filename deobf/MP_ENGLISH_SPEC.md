# MP mod dialogue in English — spec (v72, completed v73)

Owner report: "Sir Irolio chat is broken" — his window shows only punctuation (`( ) . , ?`).

## Cause (measured on the v71 APK)
The MP content pack (Sorrow-Mod content) wrote its dialogue in **Russian into the English `text` column**. The game
font used for English has no Cyrillic glyphs, so every Russian letter is dropped and only punctuation is drawn.
- Top-level conversations with Russian in `text`: **591 files, 9,072 rows** (7,111 unique strings with quests).
  Quest journal text: 928 rows (`quests/*.txt`). `rules`: 2 rows.
- Only **3 of them are official EK conversations** the mod edited: `IM_knight_irolio` (Sir Irolio), `I10_hunter`,
  `varannari_hunters`. The mod re-wrote their original lines in Russian and added a few new ones (the Janod/Sir
  Damian cloak hook, the collar-of-submission options). The official lines still carry the official Spanish text,
  so `(index, type, text_ES)` identifies them in the base file.
- The other 588 files are the mod's own NPCs/quests; no English exists for them anywhere in the APK (the language
  folders hold the same Russian).

## Change
`tools/mp_english.py`, run right after the MP merge (build step 2b), on every top-level `conversations` (`text`) and
`quests` (`description`) file the merge put in the work dir:
1. Russian row that exists in the official file (same keys + Spanish text) → official English line (27 rows).
2. Otherwise → `tools/mp_en.tsv` (`id<TAB>English`, id = `sha1(trimmed Russian cell)[:10]`). v72 shipped the 16
   lines of the three official NPCs; **v73 ships all 7,069 unique strings** (dialogue + quest journal), so the build
   log reads `0 Russian lines left`.
3. The mod's English collar lines "…on he" / "Take off he…" → "him" / "his".
4. Any top-level data cell that is Latin text with ≤3 Cyrillic look-alike letters ("Leather Сloak") → Latin letters.

Translation rules (v73): official EK names kept (items_text, bestiary_names, regions, factions, quest titles, place
names such as Rhöneis, Sol-Laqueul, Icemist, Thelume); `[BLUE](…)[]` markup and `{LEVELxN}` placeholders kept
verbatim; crude/sexual/slur-heavy lines rendered faithfully but not graphically (APPROX, DEOBFUSCATION_STATUS §3).

## Not changed
Language-folder copies (`FR/`, `PT/`, … hold the same Russian; stock EK falls back to them only in that language),
Spanish column of the mod's new rows ("port" placeholders).
