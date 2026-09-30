# Guild expansion — spec (v74)

Owner: "expand all the guild stuff … joining a guild doesn't really do much". Chosen scope (AskUserQuestion):
ranks you climb, guild quest lines (repeatable contracts **and** a dedicated main quest line per guild), passive
member bonuses, guild shops/gear; **modest, lore-friendly** strength. Follow-up: "you should be able to eventually
become the guild master for each guild, but only for one; becoming guild master revokes your membership in the
other guilds, but you don't lose any perks or anything you got from those guilds".

## 1. Reversed (what the engine already gives us)
- Membership = variable `guild_warriors|guild_seventh|guild_wizards|guild_three` (0/1), `HERO_GUILDS_SPEC.md`.
- Conversation files (`Conversation.java`): rows `index type text text_ES "Go To" conditions actions`.
  **Q**: the first row (file order) with that index whose conditions hold is shown; its actions fire; its Go To
  names the answer set. **A**: the first **4** matching rows with that index are listed (`ConversationAnswers`
  breaks at 4); Go To 0 ends. Conditions `;`-separated, all must hold.
- Conditions used: `VariableEqual/Greater/Lower#v,n`, `PlayerIsLevel#n` (level ≥ n), `PlayerHasItems#id,n`,
  `NPCIsDead#unique_tag` (persistent dead list: a boss killed before the chapter still counts).
- Actions used: `SetVariable/IncVariable#v,n`, `GainGold#n`, `GainXP#n`, `GainItem#id`, `LoseItems#id,n`,
  `OpenShop#` (opens the **talking NPC's** shop, e.g. `D9_wizard_member` node 80).
- Journal (`QUEST_SPEC.md`): quest progress **is** the variable named after the quest id; `quests/list.txt` +
  `quests/<id>.txt` (`progress description description_ES actions`, row 0 = name). ≥100 = completed.
- Shops: a map `spawn`/`staticNPC` object's `shop_items` (+ `shop_modifier`, a buy-price multiplier:
  `o0/f`: price = value × modifier) becomes that NPC's shop (`MonsterSpawn`, `l0/b`).
- Passive bonuses: `CharacterInventory.u()V` (theirs; our decompile `s()`) recomputes `DefenseBonus`, `HPBonus`,
  `ManaBonus`, `devicesBonus`, `detectionBonus`, `traits[6]` (0 STR 1 END 2 AGI 3 INT 4 AWA 5 PER, `CharacterTraits`)
  from the equipped items; the v-upgrade feature already appends to its tail (`patch_mp_features.py`).
- Daukar already has an unanswered "Are there any contracts available?" (node 63 "Not at the moment") — the hook.

## 2. Leaders, guild menu, variables
| Guild | g | Leader (menu node) | Stock NPC | Visit NPCs |
|---|---|---|---|---|
| Warriors' Guild | warriors | Sgt. Daukar `NG_warriors_daukar` (60) | Daukar | Toel `FT_warriors_toel` (Freetown), Morg `NI_warriors_morg` (Nivarian) |
| Seventh House | seventh | Sister Kardagis `NG_sewers_kardagis` (60) | Torja `NG_sewers_torja` | Arkados `FT_seventh_arkados` (Freetown), Torja |
| Wizard's Guild | wizards | High Mage Ilemma `IM_ilemma` (60) | Ilemma | Aglaron `D9_tower_aglaron`, Arabelle `E11_tower_arabelle` |
| Church of the Three | three | Archbishop Dilla `NI_hall_archbishop` (2) | Dilla | — |

Daukar: her existing dead-end answer "Are there any contracts available?" (node 60→63) now opens the guild menu
(members only). Kardagis, Ilemma, Dilla: their member greeting leads to a two-answer choice (699: "(Guild business)"
/ "Something else..." → 698 → their usual menu), so the 4-answer cap never hides a quest option. The guild menu is
our nodes 700+ (contracts / standing & chapter / members' stock / back). Variables (all per guild g):
`ekg_mem_<g>` 1 once greeted as a member (perks + journal), `ekg_rank_<g>` 0..5 (5 = Guild Master),
`ekg_rep_<g>` contract count, `ekg_mq_<g>` main-quest progress (journal quest), `ekg_gq_<g>` standing journal
quest; global `ekg_gm` = 0 or the guild number (1 warriors, 2 seventh, 3 wizards, 4 three).

## 3. Ranks
Titles r0..r4 + master; promotion to r+1 needs: chapter r+1 of the main quest finished, `ekg_rep ≥ R`, level ≥ L.

| r→r+1 | 0→1 | 1→2 | 2→3 | 3→4 | 4→GM |
|---|---|---|---|---|---|
| contracts R | 2 | 4 | 7 | 10 | 14 |
| level L | 8 | 12 | 15 | 18 | 21 |

Warriors: Recruit, Soldier, Veteran, Champion, Warmaster, **Guild Master**. Seventh: Associate, Operative, Shadow,
Master Thief, Hand of the House, **Master of the House**. Wizards: Apprentice, Adept, Magus, Master Magus,
Archmagus, **Grand Magus**. Church: Acolyte, Deacon, Priest, Templar, Exemplar, **Hierophant**.

## 4. Repeatable contracts (existing loot, +1 `ekg_rep` each, pay ≈ 1.3× item value + XP)
| Guild | Contracts |
|---|---|
| Warriors | 3 Minotaur Horn (1001), 2 Troll Hide (1011), 3 Chitin Carapace (1015) |
| Seventh | 4 Poison Sac (1008), 2 Emerald (2012), 1 Gold Ingot (1021) |
| Wizards | 3 Fire Salts (1005), 3 Living Ice (1006), 3 Sparkling Powder (1007) |
| Church | 10 Zombie Flesh (1009), 6 Skull (1010), 1 Demonic Skull (1013) |
Three per guild so the node keeps a "back" answer (4-answer cap).

## 5. Main quest lines (5 chapters; chapter k unlocks promotion to rank k)
Objectives use always-present world bosses (`NPCIsDead#tag`, spawn objects with no conditions) and visits to
guild NPCs (a Q row injected at the top of their node 1 while the chapter waits for the visit).

| | Ch1 | Ch2 | Ch3 | Ch4 | Ch5 (Guild Master) |
|---|---|---|---|---|---|
| Warriors "The Iron Oath" | visit Toel (Freetown) → Kakrak, Imperial Fortress (Steel Coast) | King Gurguth, Gurguth Cave (Trollfens) | Giant chief, Sanctuary Peak | Minotaur Underking, Sunken Citadel (Ashen Wastes) → report to Morg (Nivarian) | Basrudaxul, Bappasalar Cave |
| Seventh "The Long Game" | visit Arkados (Freetown) → Surtag, Jabal Grotto | Xidar, Jabal Grotto | Director, Golden Cove Bank | Castle Storme executioner → report to Torja | tribute: 3 Gold Ingot + 15000 gold |
| Wizards "Echoes of the Council" | visit Aglaron (Iron Valley enclave) + 3 Sparkling Powder | Crypt necromancer, Mercian Royal Crypt | Mausoleum lich (Deadwood) → report to Arabelle (Solliga enclave) | Lich of the Sewer of Horrors (Icemist) | Flame Lord, Icemist Underlevels |
| Church "The Long Vigil" | 6 Skulls for the ossuary | Lich of Irazur Tomb (Great Inori) | Lich of the Forgotten Temple (Eastern Inori) | Greater demon, Hellish Cave (Fögas Forest) | Void Lord, Forbidden Pit (Mount Orogg) |

Promotion rewards (class-free gear, gold, XP):
| | r1 | r2 | r3 | r4 | GM |
|---|---|---|---|---|---|
| Warriors | Greater Ring of Endurance 3024 | Ring of the Bull 3032 | Iron Will Mantle 3505 | Ring of Health 3012 | Superior Belt of Might 7005 |
| Seventh | Ring of the Trader 3039 | Ring of Charm 3022 | Spectral Cloak 3504 | Belt of Agility 7001 | Superior Belt of Agility 7004 |
| Wizards | Lesser Ring of Learning 3021 | Oasis Ring 3013 | Tiara of the Heavens 4500 | Ring of the Star Traveller 3033 | Greater Tiara of the Heavens 4503 |
| Church | Greater Ring of Death Ward 3015 | Ring of Vitality 3026 | Totem of Protection 463 | The Abbot's Ring 3028 | Bishop Ring 3030 |

## 6. Guild Master (one guild only)
Chapter 5 is offered only while `ekg_gm = 0`. Accepting the seat sets `ekg_gm`, `ekg_rank_<g> = 5` and
`guild_<other> = 0` for the other three (membership, trainers and guild menus there close). **Perks, ranks, items
and journal history stay**: perks read `ekg_mem`/`ekg_rank`, never `guild_*`. Every oath answer (any row whose actions
set `guild_*,1`, incl. the Church's "renounce" option) gets `VariableLower#ek_gm,1`, so nobody (the Hero included)
can re-take an oath after becoming a Guild Master. Other guilds' standing journals show "former member".

## 7. Passive perks (tier t = rank+1; the Guild Master seat counts t = 6 for its own guild)
| Guild | Per tier | Attributes |
|---|---|---|
| Warriors | +3 HP, +½ armor | STR +1 at t≥4, END +1 at t=6 |
| Seventh | +2 HP, +2 devices, +1 detection | AGI +1 at t≥4, AWA +1 at t=6 |
| Wizards | +4 mana | INT +1 at t≥4, AWA +1 at t=6 |
| Church | +2 HP, +2 mana | PER +1 at t≥4, END +1 at t=6 |
Max per guild ≈ one good ring. `EkGuild.apply(inv)` adds them at the tail of the player's `CharacterInventory.u()`
(only the player's inventory); `EkGuild.tick()` (from `EkAuto.tick`, every 3 s) re-runs `u()` when a rank changes.

## 8. Members' stock (shop_modifier 0.85, opened from "Guild business" at rank ≥ 1)
Warriors (Daukar): platemail pieces 115-119, Bluesteel Greatsword 534, Maul 537, Greataxe 539, Hero's Shield 185,
Scutum 191, Warrior's Gorget 4021. Seventh (Torja): Assassin's Cuirass/Leggings 146/147, dirks 406/407/409/410,
Stiletto 611, Assassin's Longbow 730. Wizards (Ilemma): staves 381/382/387/388/392, robes 325/341, rings
3036/3038, Pouch of Reagents 460, Imperial College Emblem 4018. Church (Dilla): Blessed coif/boots/helm 131/145/161,
Holy Helm 221, Anointed Shield 193, Shield of Life 187, Bone maces 617/618.
Staves/robes keep their own class requirements (the store only lists them).

## 9. Implementation
Variables are prefixed `ekg_` (not `ek_`) so `EkShare.isCharVar` keeps them with the character.

`tools/guild_expansion.py` (build step 2d, after `hero_guilds.py`): conversations (root + every language copy
with a `conditions` column; new rows are English), `quests/` (+`list.txt`), map objects (`shop_items`,
`shop_modifier`). Java: `EkGuild.java`; smali: `patch_mp_features.py` appends `EkGuild.apply` to `u()V`.
APPROX: new content (not EK's), logged in `DEOBFUSCATION_STATUS.md` §3.

## 10. Story engine (v75)
Owner: the quest lines need "actual meat". `tools/guild_story.py` compiles `tools/guild_story_data.py`:
- **States**: journal quest `ekg_st_<g>` counts steps 1..N across chapters, each chapter followed by a "done" state;
  100 = Guild Master. Chapter k is offered at the leader's node 720 when `ekg_st` = previous done state, level ≥ L,
  contracts ≥ R (and `ekg_gm` = 0 for the master chapter). Promotions happen at the end of chapters marked
  `promote` (5 per story; the last also takes the seat and revokes other memberships).
- **Scenes**: a step's scene is injected at node 1 (above every other line) of the speaker's conversation, under
  `ekg_st = step` + the step's `need` (`NPCIsDead#`, `PlayerHasItems#`, or `alt_need` alternatives); beats become
  Q/A nodes from 800 up (free numbers per file). An option with go=None finishes the step.
- **New characters**: map objects added to the objectgroup of existing spawns, at coordinates of existing objects
  (walkable), with `conditions` = the story range they belong to. `static` = staticNPC (sprite/portrait); `talker`
  = spawn with faction `neutral` + conversation + `unique_tag`, made hostile by `NPCHostile#<tag>` (EK's own
  pattern; `ScriptedAction` case 26); `foe` = hostile spawn (`hostile` VariableLower#false,1, faction
  bandits/enemy). `unique_tag` makes deaths count (`NPC.X` → `deadNPCs`) and stops respawns.
- **Items**: quest items appended to `rules/items.txt` / `items_text.txt` (ids 9501+), type general, value -1.
- **Migration**: a v74 save with rank r jumps to the done state of the chapter that grants rank r.
- **Check**: `tools/guild_story_sim.py <data dir> <g> [runs]` plays the story with first/last/random choice policies
  and fails on any dead end, missing speaker or NPCIsDead tag that no map defines.

Warriors' Guild — "The Iron Oath" (8 chapters, 21 steps): Blood on the Road (Brann's patrol ambushed, Crimson
scouts; → Soldier), Red Sashes (Toel, Lyse Corwen, Garrick the Knife; choice: protect / pay off / hand over Lyse),
The Beast-Binder (Oreth: spare or kill; King Gurguth; → Veteran), The Giants' Price (Morg, Captain Sera Blackwell:
fight or turn her; giant chief), Crown and Coin (courier Maddoc; Vane's letters: Magistrate or leverage; →
Champion), Horns of the Underking (beastbinder + Underking; → Warmaster), The Crimson Company (Brann's past;
Varrek Dunmore: duel, or surrender if Vane fell publicly), The Founders' Oath (blessings of Toel and Morg,
Basrudaxul; ceremony reflects earlier choices; → Guild Master).

Seventh House — "The Long Game" (8 chapters, 23 steps; v76). Sources: Kardagis/Torja/Arkados conversations
(`NG_sewers_kardagis`, `NG_sewers_torja`, `FT_seventh_arkados`), the Golden Hand of the Grey Library
(`FT_library`, `FT_library_guardian`, lost_good_book), the Golden Cove Bank (`H6_bank`, `H6_vault_door` colours
red-blue-green-blue-yellow, `H6_director`), and EK's own hints that "the boss" is a woman ("the boss is in, if you
need to see her"). Chapters: Loose Lips (Torja; runner Pell hunted by Gilded Blades east of Kingsbridge, G9; Golden
Hand token; → Operative), The Gilded Ledger (Arkados; clerk Anselm Tully in the Grey Library: bribe / threaten /
silence; Hale's letters), The Rat (fence Silas Wren sold the weekly password: turn him / Torja's judgement / kill;
→ Shadow), The Thief-Taker (Captain Odran Brand at the Iron Valley signpost, D9: fight, or show him Hale's letters
and he tears up the writ), The Golden Cove Job (Maribel Oste outside the bank in Friguld: full share on the House's
word / 500 gold now; kill H6_director; → Master Thief), Blood Price (Pell kidnapped; Sable and the Gilded Knives in
the smuggler hideout, G8_hideout; → Hand of the House), The Long Game (Magister Corvin Hale in the Grey Library:
fight, or exile him if Wren was turned or Brand walked away), The Seventh Seat (Kardagis is the Seventh; blessings
of Torja and Arkados; the White King, IM_white_king; ceremony reflects Wren/Maribel; → Master of the House).
Choice variables `ekg_c_seventh_{anselm,wren,brand,maribel,hale}`. Items 9511 Gilded Token, 9512 Hale's Letters.

Wizard's Guild — "Echoes of the Council" (8 chapters, 20 steps; v77). Sources: `IM_ilemma` (her agelessness:
"much older than she appears"), `D9_tower_aglaron`, `E11_tower_arabelle`, the four Enclaves (`E11_wizard`,
`G8_tower`), Icemist's unexplored depths (`IM_torden`), `IM_sewer`, `IM_underlevel`, `IM_planeoffire`. Chapters: The
Hollow Apprentice (Neris Vale drained of her gift, Hollow Shades outside the Inori Enclave; → Adept), Cold Iron
(Aglaron; renegade necromancer D9_crypt_necro; ledger names 'the Keeper'), The Keeper (Magus Orrin Castellane lies
about the Mausoleum lich; Arabelle's records; tell Ilemma now or keep watching; → Magus), Echoes Below (Tobin Marsh
under the Sewer of Horrors lich; Resonance Shard; the Crown of Echoes; Ilemma's confession), The Keeper of the Vault
(Iselde Rook: turn her with the shard or fight; → Master Magus), The Flame Lord (IM_lord_flame; → Archmagus), The
Crown of Echoes (Orrin in the Underlevels: fight, or he frees the gifts if Iselde was turned; Crown broken or
sealed), The Grand Chair (Aglaron, Arabelle, Pit Lord IM_lord_pit; → Grand Magus). Choices
`ekg_c_wizards_{told,iselde,orrin,crown}`; items 9521-9523.

Church of the Three — "The Long Vigil" (8 chapters, 21 steps; v77). Sources: `NI_hall_archbishop` (Duremas, the
burned *Oppalan Frontier* with its pre-Three priests, the Archbishop's unease), `altar_the_three` (Arbenos, Thelume,
Nivaria), `G9_priest` (Sister Arta), lich_G7, lich_H7 (Thelume's shrine), D11_abbey / D11_greater_demon, C13_lord.
Chapters: The Empty Ossuary (Brother Tamsin; Bonepickers at the Iron Valley graveyard; Saint Aldwen; Pale Token;
→ Deacon), The Risen of Irazur (Arta; lich_G7; Ada Fenn: comfort her or hand her to the Inquisition), The
Inquisitor (Hesper Crowe: side with fire or mercy; lich_H7; Mord's journal; → Priest), The Pale Flock (Sorrel the
Bone-Caller: fight, or her flock goes home if Ada did), The Hellish Cave (greater demon; → Templar), The Burned Book
(the Archbishop's confession; stop Crowe burning a village: fight, or she stands down if you argued for mercy; →
Exemplar), The Pale Shepherd (Corvane Mord: fight, or he repents), The Long Vigil (Arta, Tamsin, the Void Lord; →
Hierophant). Choices `ekg_c_three_{ada,crowe,sorrel,crowefate,mord}`; items 9531-9532.

## 11. Two more guilds: Loreseekers and Golden Hand (v78)
Source: `Condition.java` case 35 (`PlayerHasGuild`) already tests **six** variables: guild_seventh, guild_warriors,
**guild_golden**, **guild_loreseekers**, guild_wizards, guild_three. EK 4.2.2 never sets the two middle ones (no join
path), so the developer planned them. v78 uses exactly those variables, so the one-oath rule (non-Hero classes)
covers them natively; ORDER grows to 6 (`ekg_gm` 5 = Loreseekers, 6 = Golden Hand) and the Guild Master seat
revokes all five other memberships (EkGuild.tick loops over all six).

Join (new, `join_rows`): an answer at the top of the leader's usual menu (first A row; EK shows only 4) opens node 790:
refused if `ekg_gm` > 0 or `PlayerHasGuild#;PlayerIsntClass#warrior`; else the oath needs Friendly standing
(REP_loreseekers ≥ 10, the Librarian's own "friend of our order" bar; REP_goldenhand ≥ 3, the Guardian's "records show
you've helped" bar) or a bond (1500 / 2500 gold). Taking it sets guild_X=1 and +5 reputation (Golden Hand also sets
FT_access_library=1). Everything after that (welcome row, 699 two-step menu, hub 700+, contracts, stock, journal,
story) is the same machinery as the four EK guilds.

| | Loreseekers | Golden Hand |
|---|---|---|
| Leader | Master Librarian Rurazar (`NG_library_librarian`, Great Library, New Garand) | the Guardian (`FT_library_guardian`, Grey Library, Freetown) |
| Ranks | Initiate, Scribe, Archivist, Loreseeker, Loremaster, Master Librarian | Clerk, Factor, Broker, Consul, Magnate, Master of the Hand |
| Contracts | 2 Muud'ari Energy Cells, Vorator Egg, Demonic Wolf Skull | Pearl, Ruby, Sapphire |
| Perks per tier | +1 HP, +2 mana, +1 detection; Intellect at tier 4; Awareness as GM | +2 HP, +½ armor; Personality at tier 4; Agility as GM |
| Stock (0.85) | scrolls, tomes, mana potions | gems, rings, healing/shield potions |

Loreseekers — "The Last Codex" (8 chapters, 22 steps). Built on EK's Loreseeker lore (`H4_explorer`: an Imperial
Loreseeker studied one subject for decades, wrote a Codex, then became a Loremaster; Tangir's Lost Coven and Jorgus the
Red, master of earth elementals; Tremadan of Myros). New cast: Sabine Holt (Scribe), Loremaster Evander Quill. The Cut
Page (page-cutters in the farmlands; → Scribe), The Earthen Door (Jorgus's Abandoned Tower, g11 guardians), Loremaster
Quill (Lannegar's dead city, H10_undead_lady; give him the folio or not; → Archivist), The Cave of Echoes (E12 golem;
Quill's hirelings), The Deep City (H10_lich; the Witch Queens of Orogg; → Loreseeker), The Maze of Lamth (zombie
dragon; → Loremaster), The Reader (Quill in the Witch Queens' cave: fight, or he yields if you trusted him; publish /
seal / burn the Codex), The Master's Chair (Tangir, Gebadi, the three Witch Queens; → Master Librarian).

Golden Hand — "The Gilded Oath" (8 chapters, 22 steps). Built on EK's Golden Hand lore (`FT_library_guardian`: King
Danar II gave them the Grey Library; the founder's sword taken from a vampire-hunter, from the MP content). New cast:
Lisbet Marrow, Old Nandor, Pim Ashgrove, Consul Aurel Vesk. Bad Debts (Mudrats on the Iron Valley road; → Factor),
Cooked Books (Pim in Friguld: bribe or threaten; the Salt Road), The Consul (Primzar was Vesk's own man; report openly
or keep it; → Broker), The Dragon's Tithe (Urcrymdrax; the Salt Road manifest), Hostile Takeover (Lisbet framed; Gilded
Knives in the Jabal Hills; Vesk's seal; → Consul), Dawnbrand (the founder's sword in the white dragon's hoard; →
Magnate), The Final Audit (Vesk: fight, expose him, or take his 20,000-crown bribe and expose him anyway), The
Northern Charter (Lisbet, Nandor, the Ice Elemental Lord; → Master of the Hand).
Items 9551-9552, 9541-9544. Sim: all six guilds 60/60.
