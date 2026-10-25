## Game rules for the tutorial area: map, collision, A*, actors, combat, loot,
## triggers and the player model. No rendering here (board.gd draws, main.gd wires input).
##
## Ported from the verified web build: web/src/move.js (GameMap.v() collision, 8-way A*
## without corner cutting), web/src/combat.js (COMBAT_SPEC.md rolls, g0/armor mitigation,
## PatrollerAI / CompanionAI) and web/src/player.js (CHARACTER_STATS_SPEC.md). Data comes
## from the hero-v78 APK via tools/quest_extract.py. Spec: deobf/QUEST_3D_TUTORIAL_SPEC.md.
class_name Game
extends Node

signal floater(ent, text: String, color: Color, big: bool)
signal message(text: String, color: Color)
signal ent_added(ent)
signal ent_removed(ent)
signal attacked(ent)            # ent started a swing (for the attack animation)
signal sound(name: String, at: Vector2)
signal hud_changed
signal bolt(from: Vector2, to: Vector2, secs: float)

const AGGRO_TILES := 6          # enemy detection radius
const LEASH_TILES := 12         # enemy gives up beyond this
const FOLLOW_MAX := 4           # a follower stays within this many tiles of the hero
const REPLAN := 0.4             # s between a chaser's path re-plans
const HERO_SPEED := 3.2         # cells/s (web: 140 px/s)
const MOVE_SPEED := 2.75        # cells/s for other combatants (web: 120 px/s)
const HOSTILE := ["enemy", "beasts", "bandits", "monster", "undead"]
const FRIENDLY := ["player", "ally", "companion"]
# APPROX Q4 (= web A9): monster max-HP per race tier, and the per-class damage multiplier.
const RACE_HP := {"weak": [6, 2], "": [10, 4], "strong": [18, 6], "miniboss": [40, 10],
		"boss": [80, 16], "human": [20, 4], "npc": [20, 4]}
const CLASS_DMGMUL := {"warrior": 0.5, "rogue": 0.75, "cleric": 0.3, "wizard": 0.0}
const ARMOR_K := {"WARRIOR": 1, "ROGUE": 2, "CLERIC": 1, "WIZARD": 3}

class Ent:
	extends RefCounted
	var id := 0
	var name := ""
	var npc := {}                 # the map spawn record
	var rec := {}                 # bestiary record
	var sheet := ""               # sprite sheet name
	var pos := Vector2.ZERO       # continuous position, in cells (cell centre = c + 0.5)
	var heading := Vector2(-1, 0)
	var moving := false
	var cbt = null                # combat state Dictionary, or null for talk-only NPCs
	var path: Array = []
	var path_idx := 0
	var replan := 0.0
	var home := Vector2.ZERO
	var wander := 0
	var wander_t := 0.0
	var is_hero := false
	var is_bag := false
	var bag := {}                 # {gold, items}
	var gone := false
	func cell() -> Vector2i:
		return Vector2i(floori(pos.x), floori(pos.y))

var D := {}                       # game.json
var M := {}                       # map.json
var W := 0
var H := 0
var walk := PackedByteArray()
var astar := AStarGrid2D.new()
var ents: Array = []
var hero: Ent
var next_id := 1
var vars := {}
var followers := {}
var paused := false
var in_dialogue := false
var ended := false
var stick := Vector2.ZERO          # hero walk input, cells/s direction (board space)
var target: Ent = null             # hero's attack target
var hero_cd := 0.0
var last_cell := Vector2i(-1, -1)
var opened := {}                   # container index -> true
var on_trigger: Callable           # (actions, conditions) -> void, set by main
var rng := RandomNumberGenerator.new()

# --- player model (web/src/player.js) -------------------------------------------
var pc_class := "WARRIOR"
var gender := "M"
var xp := 0
var hp := 1
var gold := 18
var backpack: Array = []           # item ids
var worn: Array = []               # worn item ids (the ring) — APPROX Q5

func load_data() -> void:
	D = _json("res://data/game.json")
	M = _json("res://data/map.json")
	W = M.w
	H = M.h
	walk.resize(W * H)
	var s: String = M.walk
	for i in W * H:
		walk[i] = 1 if s.unicode_at(i) == 49 else 0
	astar.region = Rect2i(0, 0, W, H)
	astar.cell_size = Vector2.ONE
	astar.diagonal_mode = AStarGrid2D.DIAGONAL_MODE_ONLY_IF_NO_OBSTACLES   # no corner cutting
	astar.default_compute_heuristic = AStarGrid2D.HEURISTIC_EUCLIDEAN
	astar.update()
	for r in H:
		for c in W:
			if walk[r * W + c] == 0:
				astar.set_point_solid(Vector2i(c, r), true)

static func _json(path: String):
	return JSON.parse_string(FileAccess.get_file_as_string(path))

func new_game(cls: String, g: String) -> void:
	for e in ents:
		ent_removed.emit(e)
	ents.clear()
	vars.clear()
	followers.clear()
	opened.clear()
	paused = false
	in_dialogue = false
	ended = false
	target = null
	pc_class = cls
	gender = g
	xp = 0
	gold = int(D.startGold)
	backpack.clear()
	worn.clear()
	hp = max_hp()
	hero = Ent.new()
	hero.is_hero = true
	hero.name = "You"
	hero.sheet = "female_knight" if g == "F" else "male_knight"   # APPROX Q3
	var e0 = M.entries["0001"]                                      # MainMenuScreen.s(): entry 1
	hero.pos = Vector2(e0.c + 0.5, e0.r + 0.5)
	hero.heading = Vector2(-1, 0)                                   # down the road, west
	_add(hero)
	last_cell = hero.cell()
	for n in M.npcs:
		var e := Ent.new()
		e.npc = n
		e.rec = D.monsters.get(n.spawn, {})
		e.name = String(n.name).capitalize()
		e.sheet = e.rec.get("sprite", "male_knight")
		e.pos = Vector2(n.c + 0.5, n.r + 0.5)
		e.home = e.pos
		e.wander = int(n.get("wander", 0))
		e.wander_t = rng.randf_range(1.0, 4.0)
		e.heading = Vector2(1, 0)
		_attach(e)
		_add(e)
	hud_changed.emit()

func _add(e: Ent) -> void:
	e.id = next_id
	next_id += 1
	ents.append(e)
	ent_added.emit(e)

func _remove(e: Ent) -> void:
	e.gone = true
	ents.erase(e)
	ent_removed.emit(e)

# --- collision / pathing ---------------------------------------------------------
func walkable(c: int, r: int) -> bool:
	return c >= 0 and r >= 0 and c < W and r < H and walk[r * W + c] == 1

func nearest_walkable(c: int, r: int) -> Vector2i:
	if walkable(c, r):
		return Vector2i(c, r)
	for rad in range(1, 6):
		for dr in range(-rad, rad + 1):
			for dc in range(-rad, rad + 1):
				if walkable(c + dc, r + dr):
					return Vector2i(c + dc, r + dr)
	return Vector2i(c, r)

func plan(from: Vector2i, to: Vector2i) -> Array:
	if not walkable(to.x, to.y) or not walkable(from.x, from.y):
		return []
	var p := astar.get_id_path(from, to)
	return Array(p)

static func dist(a: Vector2i, b: Vector2i) -> int:
	return maxi(absi(a.x - b.x), absi(a.y - b.y))

# --- player model ------------------------------------------------------------------
func cls() -> Dictionary:
	return D.classes.get(pc_class, D.classes.WARRIOR)

func level() -> int:
	var t: Array = D.xpTable
	var lv := 1
	for i in range(2, t.size()):
		if xp >= int(t[i]):
			lv = i
		else:
			break
	return mini(lv, int(D.maxLevel))

func xp_progress() -> float:
	var t: Array = D.xpTable
	var lv := level()
	if lv + 1 >= t.size():
		return 1.0
	var cur := float(t[lv])
	var nxt := float(t[lv + 1])
	return clampf((xp - cur) / maxf(1.0, nxt - cur), 0.0, 1.0)

func _worn_items() -> Array:
	var out := []
	for id in worn:
		var it = D.items.get(str(id))
		if it:
			out.append(it)
	return out

func max_hp() -> int:
	var c := cls()
	var bonus := 0
	for it in _worn_items():
		bonus += int(it.hp)
	return int(c.hp) + int(c.hpPerLevel) * level() + bonus

func hero_cbt() -> Dictionary:
	var w = D.starterWeapons.get(pc_class)
	var armor := maxi(0, 0 - int(ARMOR_K.get(pc_class, 2)))       # STR 0 (APPROX Q2)
	var resist := {}
	for it in _worn_items():
		armor += int(it.armor)
		for k in it.resist:
			resist[k] = int(resist.get(k, 0)) + int(it.resist[k])
	var mul := float(cls().get("dmgMul", 0.5))
	return {"hp": hp, "maxHp": max_hp(), "armor": armor, "resist": resist, "weapon": w,
			"dmgBonus": maxi(0, roundi(mul * level())), "reach": int(w.reach) if w else 1,
			"shield": false, "dead": false}

func potions() -> int:
	return backpack.count(5000)

func use_potion() -> bool:
	var i := backpack.find(5000)
	if i < 0 or ended:
		return false
	backpack.remove_at(i)
	_run_use(String(D.items["5000"].onUse))
	sound.emit("hit_1", hero.pos)
	hud_changed.emit()
	return true

func _run_use(acts: String) -> void:
	for part in acts.split(";", false):
		var bits := part.split("#")
		if bits[0].strip_edges().to_lower() == "gainhp":
			var before := hp
			hp = mini(max_hp(), hp + int(bits[1]))
			floater.emit(hero, "+%d" % (hp - before), Color("7fd07f"), false)

func gain_xp(n: int) -> void:
	var lv := level()
	xp += n
	if level() > lv:
		hp = max_hp()
		message.emit("Level up! You are now level %d." % level(), Color("ffd54a"))
		floater.emit(hero, "Level %d!" % level(), Color("ffd54a"), true)
	hud_changed.emit()

# --- combat (web/src/combat.js) ------------------------------------------------------
static func g0(v: int, r: int) -> int:
	if r == 0:
		return v
	if r > 50:
		if r <= 80:
			r = 50 + (r - 50) / 2
		elif r <= 110:
			r = 65 + (r - 80) / 3
		else:
			r = 75 + (r - 110) / 4
	if r >= 100:
		return 0
	return (100 - r) * v / 100

func rint(a: int, b: int) -> int:
	return rng.randi_range(a, maxi(a, b))

func mitigate(raw: int, type: String, armor: int, resist: Dictionary, shield: bool) -> int:
	if type == "Normal" or type == "":
		var f := 0.4 if shield else 0.2
		var absorbed := rint(floori(armor * f), armor)
		return maxi(raw - absorbed, ceili(raw * 0.15))
	return maxi(0, g0(raw, int(resist.get(type, 0))))

func roll_damage(w, bonus: int) -> Dictionary:
	if w == null:
		w = {"min": 1, "max": 3, "crit": 5, "critMul": 200, "type": "Normal"}
	var crit := rint(1, 100) <= int(w.crit)
	var v: int
	if crit:
		var mul := maxi(150, int(w.critMul))
		v = rint(bonus + int(w.max) + 1, roundi(mul / 100.0 * (bonus + int(w.max))))
	else:
		v = rint(bonus + int(w.min), bonus + int(w.max))
	return {"hp": maxi(1, v), "crit": crit, "type": String(w.get("type", "Normal"))}

func _attach(e: Ent) -> void:
	var rec := e.rec
	var faction := String(rec.get("faction", "")).to_lower()
	var hostile := faction in HOSTILE
	if not hostile and not (faction in FRIENDLY):
		e.cbt = null
		return
	var lv := rint(int(rec.get("minlevel", 1)), int(rec.get("maxlevel", rec.get("minlevel", 1))))
	var t: Array = RACE_HP.get(String(rec.get("race", "")), RACE_HP[""])
	var mx := maxi(1, int(t[0]) + int(t[1]) * lv)
	var w = rec.get("weapon")
	e.cbt = {"side": "enemy" if hostile else "ally", "hp": mx, "maxHp": mx, "level": lv,
			"armor": int(rec.get("armor", 0)), "resist": rec.get("resist", {}), "weapon": w,
			"dmgBonus": maxi(0, roundi(float(CLASS_DMGMUL.get(String(rec.get("class", "")), 0.4)) * lv)),
			"reach": int(w.reach) if w else 1, "cd": 0.0, "target": false, "dead": false,
			"xp": 8 + lv * 6, "loot": String(rec.get("loot", "")), "aggroed": false}

func is_follower(e: Ent) -> bool:
	return followers.has(String(e.npc.get("tag", ""))) or followers.has(String(e.npc.get("name", ""))) \
			or followers.has(String(e.npc.get("spawn", "")))

func follower() -> Ent:
	for e in ents:
		if e.cbt and e.cbt.side == "ally" and is_follower(e):
			return e
	return null

func nearest_enemy(from: Vector2i, rng_tiles: float) -> Ent:
	var best: Ent = null
	var bd := rng_tiles + 0.5
	for e in ents:
		if e.cbt == null or e.cbt.dead or e.cbt.side != "enemy":
			continue
		var d := float(dist(e.cell(), from))
		if d < bd:
			bd = d
			best = e
	return best

func set_target(e: Ent) -> void:
	if e and e.cbt and e.cbt.side == "enemy" and not e.cbt.dead:
		target = e

## The ATTACK button (GameHUD → GameScreen.l()): strike an enemy in reach now, else
## engage the nearest within 6 tiles. Returns false if nothing to fight.
func hero_swing() -> bool:
	if paused or in_dialogue or ended:
		return false
	var hc := hero.cell()
	var hcb := hero_cbt()
	var t := nearest_enemy(hc, hcb.reach + 0.5)
	if t:
		target = t
		if hero_cd <= 0.0:
			_hero_strike(hcb, t)
		return true
	var near := nearest_enemy(hc, 6)
	if near:
		target = near
		return true
	return false

func _hero_strike(hcb: Dictionary, t: Ent) -> void:
	_resolve(hcb, t.cbt, hero, t, false)
	hero_cd = (int(hcb.weapon.speed) if hcb.weapon else 10) * 0.045
	_face(hero, t.pos)

func _face(e: Ent, to: Vector2) -> void:
	var d := to - e.pos
	if d.length() > 0.01:
		e.heading = d.normalized()

func _resolve(atk: Dictionary, def: Dictionary, atk_ent: Ent, def_ent: Ent, def_is_hero: bool) -> void:
	attacked.emit(atk_ent)
	sound.emit("swing" if rng.randf() < 0.5 else "sword", atk_ent.pos)
	var w = atk.weapon
	var roll := roll_damage(w, int(atk.dmgBonus))
	var land := func():
		if def.dead or def_ent.gone:
			return
		var taken := mitigate(int(roll.hp), String(roll.type), int(def.armor), def.resist, bool(def.get("shield", false)))
		def.hp = maxi(0, int(def.hp) - taken)
		var col := Color("ff6b6b") if def_is_hero else (Color("ffd54a") if roll.crit else Color.WHITE)
		floater.emit(def_ent, str(taken), col, bool(roll.crit))
		sound.emit("hit" if rng.randf() < 0.5 else "hit_1", def_ent.pos)
		if def.hp <= 0:
			def.dead = true
		if def_is_hero:
			hp = def.hp
			hud_changed.emit()
			if hp <= 0:
				_hero_death()
		elif def.dead:
			_on_death(def_ent)
		else:
			hud_changed.emit()
	if w and w.get("ranged", false):
		# Ranged: the bolt flies for a moment and lands on arrival (web A16).
		var secs := clampf(atk_ent.pos.distance_to(def_ent.pos) * 0.05, 0.09, 0.32)
		bolt.emit(atk_ent.pos, def_ent.pos, secs)
		get_tree().create_timer(secs).timeout.connect(land)
	else:
		land.call()

func _on_death(e: Ent) -> void:
	if e.cbt.get("looted", false):
		return
	e.cbt.looted = true
	gain_xp(int(e.cbt.xp))
	_roll_loot(e)
	if target == e:
		target = null
	_remove(e)

func _roll_loot(e: Ent) -> void:
	var rows: Array = D.loot.get(e.cbt.loot, [])
	var g := 0
	var items := []
	for row in rows:
		if rint(1, 100) > int(row.chance):
			continue
		if int(row.item) < 0:
			g += rint(1, 5) + int(e.cbt.level) * 2     # APPROX Q5 (web formula)
		else:
			items.append(int(row.item))
	if g == 0 and items.is_empty():
		return
	var b := Ent.new()
	b.is_bag = true
	b.name = "Loot"
	b.pos = Vector2(e.cell()) + Vector2(0.5, 0.5)
	b.bag = {"gold": g, "items": items}
	_add(b)

func _hero_death() -> void:
	# APPROX Q6 (= web A11): non-punishing — full heal, enemies disengage.
	hp = max_hp()
	for e in ents:
		if e.cbt and e.cbt.side == "enemy":
			e.cbt.target = false
	target = null
	floater.emit(hero, "You fall!", Color("ff5252"), true)
	message.emit("You fall... and get back up.", Color("ff5252"))
	hud_changed.emit()

# --- pickups ---------------------------------------------------------------------------
func take_bag(b: Ent) -> void:
	var parts := []
	if int(b.bag.gold) > 0:
		gold += int(b.bag.gold)
		parts.append("%d gold" % int(b.bag.gold))
	for id in b.bag.items:
		parts.append(_take_item(int(id)))
	message.emit("Looted: " + ", ".join(parts), Color("ffe9a8"))
	_remove(b)
	hud_changed.emit()

func _take_item(id: int) -> String:
	var it = D.items.get(str(id), {"name": "item %d" % id, "type": ""})
	if String(it.type).to_lower() == "ring" and worn.is_empty():
		worn.append(id)                                  # APPROX Q5: rings are worn on pickup
		hud_changed.emit()
		return "%s (worn)" % it.name
	backpack.append(id)
	return String(it.name)

func open_container(i: int) -> void:
	if opened.has(i):
		message.emit("It's empty.", Color("aaaaaa"))
		return
	opened[i] = true
	var c = M.containers[i]
	var got := []
	for s in String(c.items).split(",", false):
		got.append(_take_item(int(s)))
	message.emit("%s: %s" % [c.name, ", ".join(got) if got else "empty"], Color("ffe9a8"))
	sound.emit("hit_1", Vector2(c.c + 0.5, c.r + 0.5))
	hud_changed.emit()

func container_at(cell: Vector2i) -> int:
	for i in M.containers.size():
		var c = M.containers[i]
		if dist(Vector2i(c.c, c.r), cell) <= 0:
			return i
	return -1

# --- per-frame -------------------------------------------------------------------------
func tick(dt: float) -> void:
	if hero == null:
		return
	if paused or in_dialogue or ended:
		hero.moving = false
		for e in ents:
			e.moving = false
		return
	_hero_move(dt)
	_hero_turn(dt)
	for e in ents.duplicate():
		if e.gone or e.is_hero or e.is_bag:
			continue
		e.moving = false
		if e.cbt == null or e.cbt.dead:
			continue
		e.cbt.cd = maxf(0.0, e.cbt.cd - dt)
		e.replan = maxf(0.0, e.replan - dt)
		if e.cbt.side == "enemy":
			_enemy_turn(e, dt)
		else:
			_ally_turn(e, dt)
	var hc := hero.cell()
	if hc != last_cell:
		var prev := last_cell
		last_cell = hc
		_check_triggers(prev, hc)
		for e in ents.duplicate():
			if e.is_bag and e.cell() == hc:
				take_bag(e)

func walk_to(cell: Vector2i) -> void:
	var goal := nearest_walkable(cell.x, cell.y)
	var p := plan(hero.cell(), goal)
	hero.path = p
	hero.path_idx = 1

func _hero_move(dt: float) -> void:
	hero.moving = false
	if stick.length() > 0.15:
		hero.path = []
		target = null if target and dist(target.cell(), hero.cell()) > hero_cbt().reach else target
		var v := stick.limit_length(1.0) * HERO_SPEED * dt
		hero.heading = stick.normalized()
		for cand in [v, Vector2(v.x, 0), Vector2(0, v.y)]:
			var np: Vector2 = hero.pos + cand
			if cand.length() > 0.0001 and walkable(floori(np.x), floori(np.y)):
				hero.pos = np
				hero.moving = true
				break
		return
	_follow_path(hero, HERO_SPEED, dt)

func _follow_path(e: Ent, speed: float, dt: float) -> void:
	if e.path.is_empty() or e.path_idx >= e.path.size():
		e.path = []
		return
	var nxt: Vector2i = e.path[e.path_idx]
	var tp := Vector2(nxt) + Vector2(0.5, 0.5)
	var d := tp - e.pos
	var step := speed * dt
	e.moving = true
	if d.length() > 0.001:
		e.heading = d.normalized()
	if d.length() <= step:
		e.pos = tp
		e.path_idx += 1
	else:
		e.pos += d.normalized() * step

func _hero_turn(dt: float) -> void:
	hero_cd = maxf(0.0, hero_cd - dt)
	var t := target
	if t == null or t.gone or t.cbt == null or t.cbt.dead:
		target = null
		return
	var hcb := hero_cbt()
	var d := dist(hero.cell(), t.cell())
	if d <= hcb.reach:
		hero.path = []
		if hero_cd <= 0.0:
			_hero_strike(hcb, t)
	elif d <= 14 and hero.path.is_empty() and stick.length() <= 0.15:
		walk_to(t.cell())

func _enemy_turn(e: Ent, dt: float) -> void:
	var c: Dictionary = e.cbt
	var hc := hero.cell()
	var d := dist(e.cell(), hc)
	if not c.target and d <= AGGRO_TILES:
		c.target = true
		if not c.aggroed:
			c.aggroed = true
			var snds: Array = e.rec.get("aggroSounds", [])
			if snds:
				sound.emit(String(snds[rng.randi() % snds.size()]), e.pos)
	if c.target and d > LEASH_TILES:
		c.target = false
		e.path = []
		return
	if not c.target:
		_wander(e, dt)
		return
	if d <= int(c.reach):
		e.path = []
		if c.cd <= 0.0:
			var hcb := hero_cbt()
			_resolve(c, hcb, e, hero, true)
			c.cd = (int(c.weapon.speed) if c.weapon else 12) * 0.06
			_face(e, hero.pos)
	else:
		_step_toward(e, hc, dt)

func _ally_turn(e: Ent, dt: float) -> void:
	var c: Dictionary = e.cbt
	var fol := is_follower(e)
	var tgt: Ent = null
	if fol and target and not target.gone and not target.cbt.dead:
		tgt = target
	if tgt == null:
		var best: Ent = null
		var bd := AGGRO_TILES + 0.5
		for o in ents:
			if o.cbt == null or o.cbt.dead or o.cbt.side != "enemy":
				continue
			var dd := float(dist(e.cell(), o.cell()))
			if dd < bd:
				bd = dd
				best = o
		tgt = best
	if tgt:
		var d := dist(e.cell(), tgt.cell())
		if d <= int(c.reach):
			e.path = []
			if c.cd <= 0.0:
				_resolve(c, tgt.cbt, e, tgt, false)
				c.cd = (int(c.weapon.speed) if c.weapon else 12) * 0.06
				_face(e, tgt.pos)
		else:
			_step_toward(e, tgt.cell(), dt)
	elif fol and dist(e.cell(), hero.cell()) > FOLLOW_MAX:
		_step_toward(e, hero.cell(), dt)              # trail the hero (CompanionAI)

func _step_toward(e: Ent, goal: Vector2i, dt: float) -> void:
	if e.path.is_empty() or e.replan <= 0.0 or e.path_idx >= e.path.size():
		var g := nearest_walkable(goal.x, goal.y)
		var p := plan(e.cell(), g)
		e.path = p if p.size() > 1 else []
		e.path_idx = 1
		e.replan = REPLAN
	_follow_path(e, MOVE_SPEED, dt)

func _wander(e: Ent, dt: float) -> void:
	if e.wander <= 0:
		return
	if not e.path.is_empty():
		_follow_path(e, MOVE_SPEED * 0.45, dt)
		return
	e.wander_t -= dt
	if e.wander_t > 0.0:
		return
	e.wander_t = rng.randf_range(2.5, 6.0)
	var h := Vector2i(e.home)
	var g := Vector2i(h.x + rint(-e.wander, e.wander), h.y + rint(-e.wander, e.wander))
	if walkable(g.x, g.y):
		var p := plan(e.cell(), g)
		e.path = p if p.size() > 1 else []
		e.path_idx = 1

# --- triggers (map object layer) -------------------------------------------------------
func _check_triggers(prev: Vector2i, now: Vector2i) -> void:
	if in_dialogue:
		return
	for t in M.triggers:
		var r := Rect2i(int(t.c), int(t.r), int(t.w), int(t.h))
		if r.has_point(now) and not r.has_point(prev) and on_trigger.is_valid():
			on_trigger.call(String(t.get("actions", "")), String(t.get("conditions", "")))
