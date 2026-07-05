# In-game HUD + player model — recovered spec

Scope for Track B TODO #2 (player model + HUD), traced from the source per the
faithfulness rule. HUD = **GameHUD** (`n0/z`, 1832 lines; wrappers `n0/x,y,a0`),
which reads `GameData.v().player`. Player stat model = `CharacterSheet` /
`CharacterStats` / `CharacterTraits` / `SkillSet` (see `CHARACTER_STATS_SPEC.md`).

## Player model (values a new character starts with)
Built by `GameData.b(PlayerCreation)` → `Player.C1()` (new-game init):
- **level** — from XP via `Rules.f3252f` table (below); starts **1**. Max level **25**.
- **XP** — starts 0.
- **gold** — **18** (`Player.C1(): this.gold = 18`).
- **attributes** — the 6 chosen at creation (STR/END/AGI/INT/AWA/PER, 0–12).
- **maxHP** `CharacterStats.g()` = `base + perLevel·level` (human race):
  Warrior 45/+6, Cleric 35/+5, Rogue 30/+4, Wizard 20/+3.
  *(APPROX note: the class-description text quotes level-1 HP as the base — 45/35/30/23
  — so devs may treat level 1 as the base value; code adds one `perLevel`. Using the
  code formula; revisit if in-game values differ.)*
- **maxMana** `CharacterStats.h()` = Wizard & Cleric only: `12 + 2·level`; others 0.
- **currentHP/currentMana** — full at start (`missingHP = missingMana = 0`).
- Starting inventory adds item `501` (a basic item) — inventory system is TODO #3.

### XP → level table (`Rules.f3252f`, cumulative XP to reach the level)
```
1:0  2:300  3:1000  4:3000  5:6000  6:11000  7:18000  8:27000  9:38000  10:50000
11:64000 12:80000 13:100000 14:125000 15:160000 16:200000 17:250000 18:310000
19:380000 20:460000 21:560000 22:700000 23:1000000 24:1500000 25:2400000
```

## HUD elements (what GameHUD draws)
Reads live from `GameData.v().player` each frame:
- **Health bar** (red) — `currentHP / maxHP` (`ADTProgressBar`, `n0/a`).
- **Mana bar** (blue) — `currentMana / maxMana`; hidden for non-casters (maxMana 0).
- **XP bar + level** — progress from this level's XP threshold to the next; level number.
- **Gold** — coin count.
- **Portrait** — the player's chosen portrait (frame).
- **Quickslot bar** — `player.activables[]` (assignable skills/potions); each shows
  its icon + cooldown/charges. (Binding UI = `QuickSlotWindow o0/m`; TODO with items.)
- **Action buttons** — Character sheet, Inventory/Backpack, Menu (save/settings), and
  the movement touchpad. `HELP_*` overlay strings drive the first-time help.
- **Floating text** (`n0/w`) — damage/heal/XP numbers (TODO with combat).
- **HUD messages/toasts** (`n0/e0…y0`) — transient notifications.

## Web-port plan (this change)
- `web/src/player.js` — `PlayerModel` from the created `PlayerCreation`: derives
  maxHP/maxMana/level/damage from `creation.json`; tracks hp/mana/xp/gold; `level(xp)`,
  `xpProgress()`, `gainXP/heal/spendGold`. Persists in the save `gameState`.
- `web/src/hud.js` — responsive DOM overlay: portrait, HP/mana/XP bars + level, gold,
  buttons (Character, Inventory, Menu) and a quickslot row; reflows tall↔wide; hidden
  during dialogue/creation. A **Character panel** shows attributes + derived stats.
- Styling is placeholder EK-theme for now (exact GameHUD art is a later fidelity pass;
  see the UI fidelity rule). Quickslots/inventory/combat are TODO #3.
