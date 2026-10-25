## Desktop playthrough of the whole tutorial area (godot --path quest -- --autotest
## --shots=DIR). Plays it as a player would: start, loot the crates, fight down the
## road, meet Adaon, fight with him, camp, sleep, wake robbed. Saves screenshots and
## prints AUTOTEST PASS / FAIL with the checks from QUEST_3D_TUTORIAL_SPEC.md §1.
extends Node

var main
var shots := ""
var step := "boot"
var t := 0.0
var kills0 := 0
var seen := {}
var log_lines: Array[String] = []
var choice_t := 0.0
var route: Array = []
var shot_n := 0

func _ready() -> void:
	for a in OS.get_cmdline_user_args():
		if a.begins_with("--shots="):
			shots = a.substr(8)
	Engine.time_scale = 3.0
	main.game.message.connect(func(s, _c): log_lines.append(s); print("  msg: ", s))
	main.dialogue.show.connect(_on_show)

func _shot(name: String) -> void:
	if shots == "":
		return
	await RenderingServer.frame_post_draw
	shot_n += 1
	var img := get_viewport().get_texture().get_image()
	img.save_png("%s/%02d_%s.png" % [shots, shot_n, name])
	print("  shot ", name)

func _on_show(view: Dictionary) -> void:
	var key: String = "%s:%s" % [main.dialogue.conv, view.text.left(20)]
	if not seen.has(main.dialogue.conv):
		seen[main.dialogue.conv] = true
		_shot("dlg_" + main.dialogue.conv)
	print("  dlg [%s] %s %s" % [view.speaker, view.text.left(60), view.choices])
	choice_t = 0.6

func _enemies_near(r: int) -> bool:
	return main.game.nearest_enemy(main.game.hero.cell(), r) != null

func _process(dt: float) -> void:
	t += dt
	var g: Game = main.game
	if t > 900.0:
		_fail("timeout in step " + step)
		return
	if main.dialogue.active:
		choice_t -= dt
		if choice_t <= 0.0:
			choice_t = 99.0
			main.dialogue.choose(0)
		return
	match step:
		"boot":
			if t > 1.0:
				await _shot("menu")
				main._start()
				step = "start"
				t = 0.0
		"start":
			if t > 1.0:
				await _shot("start_road")
				Engine.time_scale = 3.0
				route = [["crate", 0], ["walk", Vector2i(60, 40)], ["crate", 1], ["walk", Vector2i(47, 37)],
						["adaon", null], ["walk", Vector2i(30, 39)], ["walk", Vector2i(20, 39)],
						["walk", Vector2i(15, 38)], ["camp", Vector2i(9, 37)]]
				step = "route"
		"route":
			if g.target or _enemies_near(5):
				if not g.target:
					g.hero_swing()
				if not seen.has("fight"):
					seen.fight = true
					await _shot("fight")
				return
			if g.hp < g.max_hp() * 0.4 and g.potions() > 0:
				g.use_potion()
			if route.is_empty():
				return
			var r = route[0]
			match r[0]:
				"crate":
					var c = g.M.containers[r[1]]
					if g.opened.has(r[1]):
						route.pop_front()
					elif g.hero.path.is_empty() and main.pending.is_empty():
						main.board_click(Vector2(c.c + 0.5, c.r + 0.5))
				"walk":
					if Game.dist(g.hero.cell(), r[1]) <= 1:
						route.pop_front()
					elif g.hero.path.is_empty():
						g.walk_to(r[1])
				"adaon":
					if not g.followers.is_empty():
						await _shot("adaon_joins")
						route.pop_front()
					elif g.hero.path.is_empty() and main.pending.is_empty():
						for e in g.ents:
							if e.npc and e.npc.get("tag") == "adaon_tutorial":
								main.board_click(e.pos)
				"camp":
					if main.phase != "play":
						route.pop_front()
						step = "night"
					elif g.hero.path.is_empty():
						g.walk_to(r[1])
		"night":
			if main.phase == "end":
				step = "done"
				await _shot("end")
				_check()
			elif main.phase == "wake" and not seen.has("dawn"):
				seen.dawn = true

func _check() -> void:
	var g: Game = main.game
	var fails := []
	if int(g.vars.get("want_letter_back", 0)) != 10: fails.append("want_letter_back != 10")
	if g.gold != 18: fails.append("gold %d != 18 after PlayerRobbed" % g.gold)
	if not g.backpack.is_empty() or not g.worn.is_empty(): fails.append("inventory not wiped")
	if g.opened.size() != 2: fails.append("crates opened %d/2" % g.opened.size())
	if not ("adaon_tutorial" in seen and "adaon_tutorial_camp" in seen and "intro_wakeup" in seen):
		fails.append("conversations seen: %s" % [seen.keys()])
	if g.followers.has("adaon_tutorial"): fails.append("Adaon still following")
	var crate_logs := log_lines.filter(func(s): return s.begins_with("Crate"))
	if not (crate_logs.any(func(s): return "Potion of Light Healing" in s) and crate_logs.any(func(s): return "Lesser Ring of Endurance" in s)):
		fails.append("v78 crate contents missing: %s" % [crate_logs])
	print("level %d xp %d enemies left %d" % [g.level(), g.xp,
			g.ents.filter(func(e): return e.cbt and e.cbt.side == "enemy").size()])
	if fails.is_empty():
		print("AUTOTEST PASS")
	else:
		_fail(", ".join(fails))
		return
	get_tree().quit(0)

func _fail(why: String) -> void:
	print("AUTOTEST FAIL: ", why)
	get_tree().quit(1)
