# UI / HUD tier — recovered catalogue

Every EK-owned UI class (packages `n0/ o0/ p0/ q0/`), reversed to **purpose + key data**
by reading its superclass, `GameString` label keys, and `net.fdgames` entity references.
This is the level needed to plan the port; full per-handler logic is read when each window
is actually built (scene2d `Window`/`Table`/`Dialog`/`Actor` on the **1280×720** design
scale — see `ENGINE_SPEC.md`). **Billing/store (`a/*`) intentionally skipped.**

Multiple files per class = inner classes (listeners). Obfuscated paths in `( )`.

## HUD & in-game overlay
- **GameHUD** (`n0/x,y,z,a0`) — the main gameplay HUD: quickslots, health/mana, minimap
  hook, touch input (`InputListener`), messages like `GAME_QUICKSAVED`, `NO_TALK`,
  `SLEEP_CONFIRM`, `YOU_FOUND`, `NOT_ALLOWED`. Central in-game UI.
- **HUDMessages** (`n0/e0…y0`, ~23 files) — the transient on-screen message/toast family
  (one class per message style, base `p0.b`); each renders a queued HUD notification.
- **FloatingText** (`n0/w`) — damage/heal/XP numbers floating up from actors.
- **GameLogConsole** (`n0/b0`) / **GameLogWindow** (`n0/c0`) — scrolling event log (in-HUD
  console + full window, `BACK`).
- **ConversationWindow** (`n0/k,l,m,n,o`) — NPC dialogue UI: options list, `LEAVE`,
  `CONTINUE`, `CHOOSE_OPTION_CONTINUE`; reads `NPC`/`StaticNPC`. Drives the conversation VM.
- **WorldMapImage** (`n0/q1`) + **WorldMapInfoTable** (`n0/r1`) — the world map view
  (`Areas`/`Regions`, `UNKNOWN_AREA`, `UNKNOWN_REGION`, `BACK_TO_GAME`).
- **EndingWindow** (`n0/p`) — end-game screens (`ENDING_1..3`).

## Character / skills / inventory
- **CharacterWindow** (`o0/e,f,g`) — character sheet: `ATTACK_STATS`, `DPS`, `RESISTANCES`,
  `BACKPACK`, `EQUIPMENT`, `QUICKSLOTS`; tabs over the `CharacterSheet`.
- **CharacterPreview** (`o0/d`) — the rendered paper-doll of the character.
- **StatsDetailWindow** (`o0/x`) — stat/armor/damage help (`HELP_STATS`, `HELP_ARMOR…`).
- **CharacterDamageDescription** / **…CriticalDamageDescription** (`o0/b,c`) — damage tooltips.
- **SkillWindow** (`o0/t,u,v,w`) — skill tree/allocation (`SPEND_ALL_SKILLS`,
  `SKILL_REQUISITES_ERR`, `STAT_DETAILS`). **SkillDetailTable** (`o0/o,p`) — per-skill panel
  (`LEVEL`, `SECONDS`, `COST`, `ACTIVE_SKILL`/`PASSIVE`). **SkillInfoWindow** (`o0/r`),
  **SkillTrainWindow** (`o0/s`, `LEARN`), **SkillImage** (`o0/q`), **SkillButton** (`n0/l1`),
  **ChooseSkillSlotDialog** (`o0/h`).
- **TraitsWindow** (`o0/a0`) / **TraitsTable** (`o0/z`) / **TraitDescriptionTable** (`o0/y`) —
  traits: `T_STR/T_END/T_AGI/T_INT/T_AWA/T_PER`, `AVAILABLE_TRAIT_POINTS`, `SPEND_ALL_TRAITS`.
- **ItemDescriptionTable** (`o0/j`) / **ItemPreviewTable** (`o0/k`) — item tooltips
  (`DAMAGE`, `WEAPON_LIGHT/RANGED/2HANDED`). **InventorySlotImage** (`o0/i`),
  **ArmorImage** (`o0/a`), **QuickSlotWindow** (`o0/m`, `CHOOSE_SLOT_BUTTON`).
- **JournalWindow** (`o0/l`) — quest journal + rumors (`LEARN_MORE`, `TAVERN_RUMOR`,
  `NO_RUMORS`). **ReputationWindow** (`o0/n`) — faction rep (`FACTION`, `REPUTATION`).

## World interaction windows
- **CastleWindow** (`n0/d`) + **CastleDescriptionTable** (`n0/c`) — castle/keep UI:
  `FINE`, `QUESTS`, `VAULT`, enemy descriptors.
- **RestWindow** (`n0/g1`) — inn rest: `DRINK`, `GOSSIP`, rumors on sleep. **InnDescriptionTable** (`n0/a1`, `GOSSIP_BONUS`).
- **VaultWindow** (`n0/n1`) — storage vault (`VAULT_BUY`, `VAULT_COST`).
- **TeleportWindow** (`n0/m1`) — recall/teleport (`CHOOSE_DESTINATION`, `RECALL`).
- **ChooseQuestWindow** (`n0/f`) — quest accept (`ACCEPT_QUEST`, `QUEST_TIME`, `DAYS`).
- **WaitWindow / WaitMenuWindow** (`n0/p1,o1`) — timed wait/`LOADING`.

## Menus & new-game
- **NewGameWindow** (`q0/v,w`) — hero creation: `CREATE_YOUR_HERO`, `NAME_LENGHT`,
  `START_GAME`; uses **ClassImage** (`q0/f`), **GenderImage** (`q0/q`),
  **DifficultyImage** (`q0/o`). **ClassesHelpWindow** (`p0/a`) — WARRIOR/ROGUE/CLERIC/MAGE.
- **ChooseGameWindow** (`q0/b`) — save-slot chooser (`CHOOSE_GAME`, overwrite confirm).
- **LibraryWindow** (`q0/t,u`) — extras: `HEROES`, `IRONMAN`, `RELOAD_FROM_LAST_SLEEP`.
- **CreditsWindow** (`q0/i,j`, `CREATED_BY`) · **RateWindow** (`q0/x`) · **UIHelpWindow** (`p0/c`)
  · **TipDialog** (`p0/b`).

## Settings & platform
- **SettingsWindow** (`n0/j1`) / **SettingsDesktopWindow** (`n0/i1`) / **GameOptionsWindow**
  (`n0/d0`) — options: difficulty change (`DIF_LOWERED`, `RED_DIFFICULTY_CONFIRM`), language
  (`WARNING_LANG_CHANGED_RESTART`), `CANT_SAVE_INDOOR`.
- **KeyboardWindow** (`n0/c1`) + **KeyConfigTable** (`n0/b1`) — key bindings
  (`KEYBOARD_ATTACK/INTERACT/CHARACTER…`). **ControllerSettingsWindow/Button** (`q0/h,g`).
- **ChooseResolutionDialog** (`q0/e`), **ChooseLanguageDialog** (`q0/c`),
  **LanguageImage** (`q0/s`), **ChooseLeaderboardDialog** (`q0/d`), **GPGSConnectButton**
  (`q0/p`, Google Play Games).
- **SaveWindow / ChooseSaveWindow** (`n0/h1`, `n0/g,h,i`) — save/overwrite (`GAME_SAVED`,
  `CANT_SAVE_INDOOR`). Cloud **BackupWindow / DesktopBackupWindow / WaitBackupWindow**
  (`q0/a,k,l,m,n,z`) — *cloud-save UI; adjacent to billing, low priority.*

## Reusable widgets & dialogs
- **ADTProgressBar / ADTVerticalProgressBar** (`n0/a,b`) — progress bars (health/cast/xp).
- **ExtendedLabel** (`n0/q,r,s`) / **FadingLabelSmall** (`n0/u`) — label variants.
- **ExtendedTextButton** (`n0/t`, controller-aware) / **FlashingImageButton** (`n0/v`) /
  **PlayerButton** (`n0/e1`) / **RecoverButton** (`n0/f1`) — buttons.
- **ConfirmDialog** (`n0/j`) / **SimpleDialog** (`n0/k1`) / **HowManyDialog** (`n0/z0`, qty
  picker) / **LockedDialog** (`n0/d1`) / **CharacterMessageDialog** (`n0/e`) — modal dialogs.
- **IShowableWindow** (`q0/r`) — the show/hide window interface many of the above implement.

## Status
All above are **identified + purpose-catalogued** (this file). None are ported (the web
port has no UI layer yet). When a window is built, read its handlers in the listed file(s),
port it on the 1280×720 scale, and flip its `CLASS_MAP.tsv` status to `ported`.
