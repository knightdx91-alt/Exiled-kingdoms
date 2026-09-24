# Items added by the multiplayer mod: review (2026-09-24)

Owner: "He added a lot of stupid items I think, I got a dildo as a drop."
The merge added **961 items** (ids 10055-11071; 631 unique names): 540 weapons, 107 misc, 98 chest armour, 47 potions,
45 leggings, 27 shields, 18 scrolls, 18 helmets, 15 necklaces, 12 cloaks, 11 boots, 9 rings, 8 gloves, 6 belts.
Most are normal fantasy gear (Bewitched/Blessed tiers of existing weapons, Heavenly Knights / Imperial / Dragon sets,
quest keys, monster trophies). Full list: `deobf/MP_ITEMS_ALL.tsv`.
Where the flagged ones come from: `data/rules/loot.txt` (UTF-16) tables, map chests in `tmx/*.tmx`, and conversation
shops/rewards. `A10_tower` / `A10_tower2` are the mod author's showroom maps holding one of everything (incl. the
developer junk below).

## A. Sexual / crude (recommend: out)
| id | item | how you get it |
|---|---|---|
| 10106 | Dildo (weapon) | loot: orc_chest_11 85 %, barrels_drink 40 %, mine_barrel1 30 %, durant 75 %, guymask 100 %, "bitch" 14 %; chests I9_dungeon, H6_cave; sorceress enchanting |
| 10888-10891 | Bewitched Dildo ×4 | sorceress Magnolia / Beatrice enchanting |
| 10996 | Divine Dildo | F6_sleeper |
| 10616 | Axe of the Bull's Dick | "bulls_dick_loot" |
| 10648 | Chastity Belt ("It'll make a pussy out of you") | I9_dominatrix2 |
| 10361 (+3 Bewitched) | Satisfier (bow, "convey your love") | "dominatrix_loot", Mage Ronald |
| 10892, 10995 (+4 Bewitched) | Impressive Dignity / Perfect Impressive Dignity ("small but good") | sorceresses, E10_bubba, F6_sleeper |
| 10808 | Michaela's favorite panties | I9_mikaela quest, NG_stalker |
| 10809 | Mikaela's autograph | I9_mikaela |
| 10994 | Magic Photoalbum (Sansa's "body in these pictures") | H12_fir_tree |
| 10990, 11058 | Armorbra | H12_craftsman |
| 10108 | Summon girl ("Girl for an hour") | "pimp" 100 %, F10_rickman |
| 10402 | Collar of Submission ("perverted magic") | ~100 guard/villager conversations (a control-a-person mechanic) |
| 10400 | Troll Scrotum (necklace) | E8_troll, F13 |
| 11019 | Wooden cock | adept_loot_5 |
| 11035 | Black underpants | adept_loot_6 |
| 11011 | Scroll of wisdom: Justice-3 (dick joke) | adept_loot_5 |
| 10110 | Pants ("someone's underpants") | orc_chest_11, "bitch", metalist |

## B. Modern / joke items that break the setting (owner's call)
Vodka 10114 (26 loot tables), Ethanol 10115, Dumbbell 10107, Gangster Bit 10123 (+Bewitched/Blessed), Protein Mixed
Fodder 10111, Knickers 10109, Kevlar Vest 10340, Bow Machine Gun 10213, Rosary beads "so bro respect you" 10615,
Certificate of Merit from Tavern 10864 (worth 1,400,000 gold, "endless supply of pipisa"), Vomit Cleaner 10985,
Eye of Tol 2.0 10443, Teddy Bear (belt) 10797, Metal Pants 10835, Meat ape 10419, Nasty appendage 10428.

## C. Developer junk (recommend: out)
Opa 10624, Test 10626, pop1 10763 (only in the A10 showroom maps).

## Done (v64): owner chose "rename + stop drops", plus modern/joke items, developer junk and crude NPC names
`tools/clean_mp_items.py`, run by `merge_mp_content.py` on every build (data only; no code):
- **Renamed** (name in every language column, description where crude, stock icon/sprite where the art was crude):
  Dildo → Oak Cudgel (+Bewitched/Divine), Impressive Dignity → Stout Dirk (+Bewitched/Perfect), Satisfier → Heartseeker
  (+Bewitched), Gangster Bit → Brigand's Cudgel (+Bewitched/Blessed), Axe of the Bull's Dick → Axe of the Bull Lord,
  Chastity Belt → Iron Girdle, Michaela's favorite panties → Michaela's silk ribbon, Magic Photoalbum → Magic
  Sketchbook, Armorbra → Sansa's Breastplate (stock plate icon + worn sprite), Summon girl → Hireling's Contract,
  Collar of Submission → Collar of Obedience, Troll Scrotum → Troll Tusk Necklace, Wooden cock → Wooden figurine,
  Black underpants → Black rag, Pants → Padded Breeches; Vodka → Firewater, Ethanol → Distilled Spirit, Dumbbell →
  Iron Weight, Protein Mixed Fodder → Hearty Gruel, Knickers → Training Breeches, Kevlar Vest → Weavesteel Vest, Bow
  Machine Gun → Rapid Bow, Rosary beads → Prayer Beads, Certificate of Merit from Tavern → Tavern Commendation (value
  1,400,000 → 100), Vomit Cleaner → Sickening Blade, Eye of Tol 2.0 → Eye of Tol, Reforged, Teddy Bear → Bearskin Belt,
  Metal Pants → Orcish Iron Breeches, Meat ape → Ape Meat, Nasty appendage → Withered Claw; two scroll/trophy
  descriptions rewritten. Internal ids and stat keys (`dildo`, `satisfier_bow`) are unchanged, so saves and quests work.
- **No longer drop**: all of the above (and their variants) plus Opa/Test/pop1: 61 loot rows now give a stock item of
  the same kind (Wooden Club, Potion of Light Healing, Leather Leggings/Cuirass, Damaged Wolf Pelt), 7 map chests
  likewise, 27 shop lists just lose them. Quests/conversations that hand them out give the renamed item.
- **Art**: the 24 crude in-hand weapon sheets (`composite/*_mainhand_dyldo*.png`, used by NPCs and the old items) are
  replaced by the stock dagger sheet (same 1260×1540 layout). The mod's underwear bodies (`*_naked*`) are left as is.
- **NPC names** (every language): Pimp → Racketeer, Harlot → Bandit Scout, Dominatrix Philippa → Warlady Philippa,
  Bull's Dick → Bull Lord, Green bastard → Green Brute, Just sex → Brawler, Anal lord → Abyssal Lord, "It's kind of
  fucked up" → Twisted Horror, Dream EK-wanker → Dream Wanderer, Femipet → Tamed Demon, Black guy → Stranger, Black
  Bro → Brother in Arms, Dead Bastard → Dead Brute, Great Good Dignity → Great Demon, Best tank in game → Wild
  Brawler, You know who it is → Mysterious Stranger, Illusory bro → Illusory Brother, (Energo) Machine Gunner →
  (Energy) Arbalester, Nerd to grave → Restless Scholar.
- Not changed (owner's call): pop-culture names (Shrek, Chewbacca, Tarzan, Saruman, Gendalf, Robin Good, Bugs Gambino,
  Horseradish, "From 2007", numbered "1st…8st" enemies) and dialogue written around these NPCs (only one line named an
  item; it now says Oak Cudgel).
