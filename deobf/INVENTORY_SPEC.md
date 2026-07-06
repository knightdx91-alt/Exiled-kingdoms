# Items / inventory / equipment — recovered spec

TODO #1 (keystone). Reversed from `Rules/Item.java`, `Rules/ItemAttributes.java`,
`GameEntities/CharacterSheet/CharacterInventory.java`, `Rules.java` (items.txt loader),
and the equip logic in `CharacterInventory.c()`.

## items.txt columns (0-indexed, tab-separated)
`0 item_ID · 1 name · 2 type · 3 weaponstats · 4 armor · 5 resistances · 6 traitBonus ·
7 attributes · 8 value · 9 icon · 10 hp · 11 mana · 12 damage_bonus · 13 damage_bonus_type ·
14 proc_type · 15 proc_chance · 16 proc_level · 17 sprite · 18 OnTake · 19 OnTakeConditions ·
20 OnUse · 21 Manacost · 22 Requisites · 23 Classes · 24 stack`
- **`weaponstats` (col 3)** on a `WEAPON` item is a **weapon id into `weapons.txt`**
  (e.g. `goblin_fleshcutter`) — the equipped weapon's damage/crit/reach/type come from
  `weapons.json` (already generated).
- **`armor` (col 4)** = flat armor bonus; **`hp`/`mana` (10/11)** = max HP/mana bonuses;
  **`resistances` (5)** = `Fire,Cold,Shock,Death,Toxic,Spirit` csv; **`value` (8)** = gold.
- **`Classes` (23)** = equip restriction (`W`/`R`/`C`/`M` csv, blank = anyone) — already
  enforced by `PlayerModel.canUseItemClass`.
- **`OnUse` (20)** = a scripted action for consumables (`GainHP#30`, …); **`stack` (24)**
  = stackable (potions/scrolls/general).

## Item types → equipment slot (`Item.ItemType`, `CharacterInventory.c()`)
`WEAPON → mainhand · SHIELD → offhand · armor_head → head · armor_chest → body ·
armor_arms → hands · armor_legs → legs · armor_feet → feet · ring → ring/ring2 (two) ·
belt → belt · cloak → cloak · necklace → necklace`. **Non-equippable:** `general`,
`POTION`, `SCROLL` (backpack/consumable only).

## Equipment slots (`CharacterInventory`)
12 slots, each holding one item id: `mainhand, offhand, head, body, hands, legs, feet,
ring, ring2, belt, cloak, necklace`. Equipping a ring fills `ring` then `ring2`, else
swaps `ring`. Equipping returns the previously-worn item to the backpack. A `shield`
flag is set when `offhand` holds a SHIELD (feeds combat's `Z()` armor-absorb 0.4).

## Derived stats from worn gear (`CharacterInventory` bonuses + `CharacterSheet`)
- **Armor** = `PlayerModel.armor()` (STR-based `b()`) **+ Σ worn `item.armor`**.
- **Resistances** = Σ worn `item.resistances` per type (was all-zero).
- **Max HP / Max Mana** += Σ worn `item.hp` / `item.mana`.
- **Attack** = the mainhand weapon's stats (`weapons.json[weaponstats]`); no weapon →
  the class default (existing `weaponId()`), i.e. unarmed/starter.
- Shield in offhand → combat armor-absorb factor 0.4 (`Z()`), else 0.2.

## Web implementation
- **`web/tools/gen-items.mjs`** → `assets/data/items.json`: `id → { name, type, slot,
  weapon, armor, hp, mana, resist{}, value, icon, sprite, classes, stack, onUse }`.
- **`PlayerModel`** gains `equipment {slot:id}`, `backpack [ids]`, `quickslots`; methods
  `addItem`, `equip(id)`, `unequip(slot)` (class-gated via `canUseItemClass`),
  `wornArmor/wornResist/wornHpBonus/wornManaBonus`, and `weaponId()`/`armor()`/`resist()`/
  `maxHP()`/`maxMana()` updated to fold in worn gear + shield flag. Persisted in `toJSON`.
- **Combat** (`src/combat.js`): the hero combatant reads weapon from equipped mainhand and
  armor/resist/shield from gear; **loot drops become real backpack items** (id resolved
  through `items.json`), gold still to the purse.
- **Dialogue**: `GiveItem#id[,n]` / `RemoveItem#id` add/remove backpack items.
- **Character/Inventory screen** (`src/hud.js`, portrait → panel): paper-doll of the 12
  slots, backpack grid, quick slots, and Attack/Armor/Resistance stat blocks — the
  `InventoryScreen` reference. Tap a backpack item to equip; tap a slot to unequip.

## Deviations (DEOBFUSCATION_STATUS.md §3)
- Item **procs**, `traitBonus`/`attributes` item mods, `Requisites`, `OnTake`, and
  scroll effects are parsed but not all applied yet (armor/hp/mana/resist/weapon/value are).
  Consumable `OnUse` runs through the existing dialogue action runner where supported.
- Inventory-screen **styling** is the agreed placeholder EK theme until matched to the
  real `InventoryScreen` art (owner's fidelity pass).
