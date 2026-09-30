# MP mod dialogue in English — spec (v72)

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
`tools/mp_english.py`, run right after the MP merge (build step 2b), on the top-level conversations of the merge:
1. Russian row that exists in the official file (same index/type/Spanish) → official English line.
2. Otherwise, exact match in `tools/mp_translations.tsv` (ru⇥en) → that translation. v72 ships the 16 lines the
   three official NPCs need (so Irolio, the hunter and the Varannari hunters are fully English).
3. The mod's English collar lines "…on he" / "Take off he…" → "him" / "his".
The build log prints how many Russian lines remain. Growing `mp_translations.tsv` translates the rest (the owner
decides whether/when; it is ~7k lines).

## Not changed
Language-folder copies (`FR/`, `PT/`, … hold the same Russian; stock EK falls back to them only in that language),
Spanish column of the mod's new rows ("port" placeholders).
