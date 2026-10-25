## Exiled Kingdoms VR: the tutorial area on a tabletop (Quest 3S, OpenXR + passthrough).
## Wires the rules (game.gd), the diorama (board.gd), conversations (dialogue.gd) and the
## floating panels to the Touch controllers, or to mouse + keyboard when no headset is
## present (desktop testing). Controls: QUEST_3D_TUTORIAL_SPEC.md §3.
extends Node3D

const CLASSES := ["WARRIOR", "ROGUE", "CLERIC", "WIZARD", "HERO"]
const BOARD_SCALE := 0.055         # metres per cell on the table
const TINT_DAY := Vector3(0.93, 0.93, 0.93)
const TINT_DAWN := Vector3(0.65, 0.65, 0.68)

var game := Game.new()
var dialogue: Dialogue
var board := Board.new()
var xr := false
var origin: XROrigin3D
var head: Node3D                    # XRCamera3D or the desktop Camera3D
var left: XRController3D
var right: XRController3D
var laser: MeshInstance3D
var dot: MeshInstance3D
var fade: MeshInstance3D
var hud: Panel3D
var dlg_panel: Panel3D
var menu: Panel3D
var music := AudioStreamPlayer.new()
var sfx: Array[AudioStreamPlayer] = []
var _sfx_cache := {}
var pending := {}                   # walk-then-interact: {kind, ent | index}
var phase := "menu"                 # menu | play | sleep | wake | end
var sel_class := "WARRIOR"
var sel_gender := "M"
var _placed := false
var _turn_latch := false
var _grab_from := Vector3.ZERO
var _grabbing := false
var _trigger_was := false
var _hover_panel: Panel3D = null
var _log: Array[String] = []
var _ui := {}                       # named HUD controls
var autotest := false

func _ready() -> void:
	game.load_data()
	add_child(game)
	dialogue = Dialogue.new(game)
	dialogue.show.connect(_on_dialogue_show)
	dialogue.ended.connect(_on_dialogue_end)
	dialogue.sleep_requested.connect(_on_sleep)
	game.on_trigger = _on_trigger
	game.message.connect(_on_message)
	game.hud_changed.connect(_refresh_hud)
	game.sound.connect(_play)
	_setup_view()
	board.build(game)
	board.scale = Vector3.ONE * BOARD_SCALE
	add_child(board)
	_setup_audio()
	_build_hud()
	_build_dialogue_panel()
	_build_menu()
	_show_menu()
	autotest = "--autotest" in OS.get_cmdline_user_args()
	if autotest:
		var at = load("res://scripts/autotest.gd").new()
		at.main = self
		add_child(at)

# --- view: OpenXR with passthrough, else a desktop camera over the table ---------------
func _setup_view() -> void:
	var env := WorldEnvironment.new()
	var e := Environment.new()
	e.background_mode = Environment.BG_COLOR
	e.background_color = Color(0.09, 0.1, 0.12)
	env.environment = e
	add_child(env)
	var iface := XRServer.find_interface("OpenXR")
	xr = iface != null and iface.is_initialized()
	origin = XROrigin3D.new()
	add_child(origin)
	if xr:
		get_viewport().use_xr = true
		DisplayServer.window_set_vsync_mode(DisplayServer.VSYNC_DISABLED)
		if XRInterface.XR_ENV_BLEND_MODE_ALPHA_BLEND in iface.get_supported_environment_blend_modes():
			iface.environment_blend_mode = XRInterface.XR_ENV_BLEND_MODE_ALPHA_BLEND
			get_viewport().transparent_bg = true
			e.background_color = Color(0, 0, 0, 0)
		var cam := XRCamera3D.new()
		origin.add_child(cam)
		head = cam
		left = XRController3D.new()
		left.tracker = "left_hand"
		left.pose = "aim"
		right = XRController3D.new()
		right.tracker = "right_hand"
		right.pose = "aim"
		origin.add_child(left)
		origin.add_child(right)
		_build_laser()
	else:
		var cam := Camera3D.new()
		cam.fov = 60
		origin.add_child(cam)
		cam.position = Vector3(0, 0.42, 0.42)
		cam.look_at(Vector3(0, 0, -0.02))
		head = cam
		_placed = true
	fade = MeshInstance3D.new()
	var sm := SphereMesh.new()
	sm.radius = 0.15
	sm.height = 0.3
	sm.flip_faces = true
	fade.mesh = sm
	var fm := StandardMaterial3D.new()
	fm.shading_mode = BaseMaterial3D.SHADING_MODE_UNSHADED
	fm.transparency = BaseMaterial3D.TRANSPARENCY_ALPHA
	fm.no_depth_test = true
	fm.render_priority = 1
	fm.albedo_color = Color(0, 0, 0, 0)
	fade.material_override = fm
	head.add_child(fade)

func _build_laser() -> void:
	laser = MeshInstance3D.new()
	var cm := CylinderMesh.new()
	cm.top_radius = 0.002
	cm.bottom_radius = 0.002
	cm.height = 1.0
	laser.mesh = cm
	var lm := StandardMaterial3D.new()
	lm.shading_mode = BaseMaterial3D.SHADING_MODE_UNSHADED
	lm.albedo_color = Color(1.0, 0.85, 0.4)
	laser.material_override = lm
	laser.rotation_degrees.x = -90
	right.add_child(laser)
	dot = MeshInstance3D.new()
	var sp := SphereMesh.new()
	sp.radius = 0.006
	sp.height = 0.012
	dot.mesh = sp
	dot.material_override = lm
	add_child(dot)

func _setup_audio() -> void:
	add_child(music)
	var ms = load("res://data/music/%s.mp3" % game.M.music)
	if ms:
		ms.loop = true
		music.stream = ms
		music.volume_db = -8
	for i in 6:
		var p := AudioStreamPlayer.new()
		add_child(p)
		sfx.append(p)

func _play(name: String, _at := Vector2.ZERO) -> void:
	if not _sfx_cache.has(name):
		_sfx_cache[name] = load("res://data/sfx/%s.mp3" % name)
	var st = _sfx_cache[name]
	if st == null:
		return
	for p in sfx:
		if not p.playing:
			p.stream = st
			p.volume_db = -6
			p.play()
			return

# --- panels ------------------------------------------------------------------------------
func _vbox(parent: Control, sep := 10) -> VBoxContainer:
	var v := VBoxContainer.new()
	v.add_theme_constant_override("separation", sep)
	parent.add_child(v)
	return v

func _label(parent: Control, text: String, size := 27, color := UiTheme.PAPER, head_font := false) -> Label:
	var l := Label.new()
	l.text = text
	l.add_theme_font_size_override("font_size", size)
	l.add_theme_color_override("font_color", color)
	if head_font:
		l.add_theme_font_override("font", UiTheme.heading_font())
	l.autowrap_mode = TextServer.AUTOWRAP_WORD_SMART
	parent.add_child(l)
	return l

func _button(parent: Control, text: String, cb: Callable) -> Button:
	var b := Button.new()
	b.text = text
	b.pressed.connect(cb)
	b.focus_mode = Control.FOCUS_NONE
	parent.add_child(b)
	return b

func _frame(p: Panel3D) -> PanelContainer:
	var pc := PanelContainer.new()
	pc.set_anchors_preset(Control.PRESET_TOP_WIDE)        # grows to its content
	p.root.add_child(pc)
	return pc

func _build_hud() -> void:
	hud = Panel3D.new()
	hud.setup(Vector2i(560, 520), 0.2)
	add_child(hud)
	var v := _vbox(_frame(hud), 6)
	var top := HBoxContainer.new()
	top.add_theme_constant_override("separation", 12)
	v.add_child(top)
	var por := TextureRect.new()
	por.custom_minimum_size = Vector2(96, 96)
	por.expand_mode = TextureRect.EXPAND_IGNORE_SIZE
	por.stretch_mode = TextureRect.STRETCH_KEEP_ASPECT_CENTERED
	top.add_child(por)
	_ui.portrait = por
	var tv := _vbox(top, 2)
	tv.size_flags_horizontal = Control.SIZE_EXPAND_FILL
	_ui.title = _label(tv, "", 28, UiTheme.GOLD, true)
	_ui.hp = ProgressBar.new()
	_ui.hp.custom_minimum_size = Vector2(0, 26)
	tv.add_child(_ui.hp)
	_ui.xp = ProgressBar.new()
	_ui.xp.custom_minimum_size = Vector2(0, 12)
	_ui.xp.show_percentage = false
	var xf := StyleBoxFlat.new()
	xf.bg_color = Color("d4a017")
	xf.set_corner_radius_all(6)
	_ui.xp.add_theme_stylebox_override("fill", xf)
	tv.add_child(_ui.xp)
	_ui.stats = _label(v, "", 24)
	_ui.ally = _label(v, "", 22, Color("8fd0ff"))
	var row := HBoxContainer.new()
	row.add_theme_constant_override("separation", 8)
	v.add_child(row)
	_button(row, "Attack", func(): _attack())
	_ui.potion = _button(row, "Potion", func(): game.use_potion())
	_ui.pause = _button(row, "Pause", func(): _toggle_pause())
	var row2 := HBoxContainer.new()
	row2.add_theme_constant_override("separation", 8)
	v.add_child(row2)
	_button(row2, "<", func(): _turn(-1))
	_button(row2, ">", func(): _turn(1))
	_button(row2, "-", func(): _zoom(-1.0))
	_button(row2, "+", func(): _zoom(1.0))
	_ui.log = _label(v, "", 21, Color("d8cfb8"))
	hud.set_shown(false)

func _build_dialogue_panel() -> void:
	dlg_panel = Panel3D.new()
	dlg_panel.setup(Vector2i(900, 470), 0.42)
	add_child(dlg_panel)
	var h := HBoxContainer.new()
	h.add_theme_constant_override("separation", 18)
	_frame(dlg_panel).add_child(h)
	var por := TextureRect.new()
	por.custom_minimum_size = Vector2(150, 150)
	por.expand_mode = TextureRect.EXPAND_IGNORE_SIZE
	por.stretch_mode = TextureRect.STRETCH_KEEP_ASPECT_CENTERED
	por.size_flags_vertical = Control.SIZE_SHRINK_BEGIN
	h.add_child(por)
	_ui.dlg_portrait = por
	var v := _vbox(h, 10)
	v.size_flags_horizontal = Control.SIZE_EXPAND_FILL
	_ui.dlg_name = _label(v, "", 32, UiTheme.GOLD, true)
	_ui.dlg_text = _label(v, "", 28)
	_ui.dlg_choices = _vbox(v, 8)
	dlg_panel.set_shown(false)

func _build_menu() -> void:
	menu = Panel3D.new()
	menu.setup(Vector2i(900, 800), 0.45)
	add_child(menu)
	_ui.menu_box = _vbox(_frame(menu), 12)

func _clear(c: Node) -> void:
	for ch in c.get_children():
		c.remove_child(ch)
		ch.queue_free()

func _show_menu() -> void:
	phase = "menu"
	var v: VBoxContainer = _ui.menu_box
	_clear(v)
	var logo := TextureRect.new()
	logo.texture = load("res://data/ui/logo.png")
	logo.custom_minimum_size = Vector2(0, 230)
	logo.expand_mode = TextureRect.EXPAND_IGNORE_SIZE
	logo.stretch_mode = TextureRect.STRETCH_KEEP_ASPECT_CENTERED
	v.add_child(logo)
	_label(v, "The road west of Lannegar. Tutorial area, from your hero-v78 game.", 24).horizontal_alignment = HORIZONTAL_ALIGNMENT_CENTER
	var cr := HBoxContainer.new()
	cr.alignment = BoxContainer.ALIGNMENT_CENTER
	cr.add_theme_constant_override("separation", 8)
	v.add_child(cr)
	for c in CLASSES:
		var b := _button(cr, c.capitalize(), _pick_class.bind(c))
		b.toggle_mode = true
		b.button_pressed = c == sel_class
	var gr := HBoxContainer.new()
	gr.alignment = BoxContainer.ALIGNMENT_CENTER
	gr.add_theme_constant_override("separation", 8)
	v.add_child(gr)
	for g in ["M", "F"]:
		var b := _button(gr, "Male" if g == "M" else "Female", _pick_gender.bind(g))
		b.toggle_mode = true
		b.button_pressed = g == sel_gender
	var go := _button(v, "Begin your journey", func(): _start())
	go.add_theme_font_size_override("font_size", 36)
	var help := "Left stick: walk   Trigger: point & click   A: attack   B: potion\n" + \
			"Right stick: turn / zoom the board   Left grip: move the board   Menu: pause" if xr else \
			"WASD: walk   Click: walk / attack / talk / open   Space: attack   H: potion\n" + \
			"Q/E: turn the board   Wheel: zoom   P: pause"
	_label(v, help, 21, Color("bfb39a")).horizontal_alignment = HORIZONTAL_ALIGNMENT_CENTER
	menu.set_shown(true)
	hud.set_shown(false)
	dlg_panel.set_shown(false)

func _pick_class(c: String) -> void:
	sel_class = c
	_show_menu()

func _pick_gender(g: String) -> void:
	sel_gender = g
	_show_menu()

func _start() -> void:
	board.tint = TINT_DAY
	game.new_game(sel_class, sel_gender)
	board.snap()
	dialogue.player_portrait = "res://data/portraits/%s_1.png" % ("female" if sel_gender == "F" else "male")
	_ui.portrait.texture = load(dialogue.player_portrait)
	_log.clear()
	pending = {}
	phase = "play"
	menu.set_shown(false)
	hud.set_shown(true)
	_on_message("You travel the road west, alone.", Color("d8cfb8"))
	if music.stream and not music.playing:
		music.play()
	_refresh_hud()

func _refresh_hud() -> void:
	if game.hero == null:
		return
	_ui.title.text = "%s  %s  Lv %d" % ["Hero" if sel_class == "HERO" else sel_class.capitalize(),
			"(paused)" if game.paused else "", game.level()]
	_ui.hp.max_value = game.max_hp()
	_ui.hp.value = game.hp
	_ui.xp.value = game.xp_progress() * 100.0
	var w = game.D.starterWeapons.get(sel_class)
	var ring := ""
	for id in game.worn:
		ring = "   Ring: %s" % game.D.items[str(id)].name
	_ui.stats.text = "HP %d/%d   Gold %d   Potions %d\n%s%s" % [game.hp, game.max_hp(), game.gold,
			game.potions(), w.name if w else "", ring]
	var f := game.follower()
	_ui.ally.text = "Adaon  HP %d/%d" % [f.cbt.hp, f.cbt.maxHp] if f else ""
	_ui.potion.disabled = game.potions() == 0
	_ui.pause.text = "Resume" if game.paused else "Pause"
	_ui.log.text = "\n".join(_log)

func _on_message(text: String, _c: Color) -> void:
	_log.append(text)
	while _log.size() > 3:
		_log.pop_front()
	if hud:
		_refresh_hud()

# --- dialogue ------------------------------------------------------------------------------
func _on_trigger(acts: String, conds: String) -> void:
	if phase == "play" and dialogue.passes(conds):
		pending = {}
		game.hero.path = []
		dialogue.run(acts)

func _on_dialogue_show(view: Dictionary) -> void:
	_ui.dlg_name.text = view.speaker
	_ui.dlg_text.text = view.text
	_ui.dlg_text.visible = view.text != ""
	_ui.dlg_portrait.texture = load(view.portrait) if view.portrait != "" else null
	_clear(_ui.dlg_choices)
	for i in view.choices.size():
		var b := _button(_ui.dlg_choices, view.choices[i], dialogue.choose.bind(i))
		b.alignment = HORIZONTAL_ALIGNMENT_LEFT
		b.autowrap_mode = TextServer.AUTOWRAP_WORD_SMART
		b.add_theme_font_override("font", ThemeDB.fallback_font)
		b.add_theme_font_size_override("font_size", 26)
	dlg_panel.set_shown(true)

func _on_dialogue_end() -> void:
	dlg_panel.set_shown(false)
	if phase == "wake":
		_show_end()

## Camp node 50: NPCStopFollowing; StopRender; Travel#H10; Sleep; robbed (APPROX Q7).
func _on_sleep() -> void:
	phase = "sleep"
	hud.set_shown(false)
	var fm: StandardMaterial3D = fade.material_override
	var tw := create_tween()
	tw.tween_property(fm, "albedo_color:a", 1.0, 1.5)
	tw.tween_interval(1.2)
	tw.tween_callback(_night)
	tw.tween_property(fm, "albedo_color:a", 0.0, 2.0)
	tw.tween_callback(_wake)

func _night() -> void:
	for e in game.ents.duplicate():
		if e.npc and e.npc.get("tag") == "adaon_tutorial":
			game._remove(e)            # gone in the night, with your purse
	board.tint = TINT_DAWN

func _wake() -> void:
	phase = "wake"
	dialogue.start("intro_wakeup", {"name": "You", "portrait": dialogue.player_portrait})

func _show_end() -> void:
	phase = "end"
	game.ended = true
	var v: VBoxContainer = _ui.menu_box
	_clear(v)
	_label(v, "End of the tutorial area", 40, UiTheme.GOLD, true).horizontal_alignment = HORIZONTAL_ALIGNMENT_CENTER
	_label(v, "Adaon is gone, and so are your purse and the letter. Lannegar lies down the road to the west (Travel#H10).\n\n" +
			"want_letter_back = %d     gold = %d     level %d" % [int(game.vars.get("want_letter_back", 0)), game.gold, game.level()], 26)
	_button(v, "Play again", func(): _show_menu())
	menu.set_shown(true)

# --- actions -----------------------------------------------------------------------------
func _attack() -> void:
	if phase == "play" and not game.hero_swing():
		_on_message("No enemy nearby.", Color("aaaaaa"))

func _toggle_pause() -> void:
	if phase == "play":
		game.paused = not game.paused
		_refresh_hud()

func _turn(dir: int) -> void:
	board.rotate_y(deg_to_rad(30.0 * dir))

func _zoom(dir: float) -> void:
	var s := clampf(board.scale.x * (1.0 + 0.15 * dir), 0.015, 0.15)
	board.scale = Vector3.ONE * s

## Point-and-click on the board (GameScreen tap): enemy → attack, NPC → talk, crate →
## open, loot → pick up, else walk there.
func board_click(p: Vector2) -> void:
	if phase != "play" or game.in_dialogue:
		return
	if game.paused:
		return
	var best = null
	var bd := 1.0
	for e in game.ents:
		if e.is_hero:
			continue
		var d: float = e.pos.distance_to(p)
		if e.cbt and e.cbt.side == "enemy":
			d -= 0.3                                 # enemies win ties
		if d < bd:
			bd = d
			best = e
	var cell := Vector2i(floori(p.x), floori(p.y))
	if best:
		if best.cbt and best.cbt.side == "enemy":
			game.set_target(best)
			pending = {}
			return
		pending = {"kind": "bag" if best.is_bag else "talk", "ent": best}
		game.target = null
		game.walk_to(best.cell())
		return
	for i in game.M.containers.size():
		var c = game.M.containers[i]
		if Vector2(c.c + 0.5, c.r + 0.5).distance_to(p) < 1.0:
			pending = {"kind": "crate", "index": i}
			game.target = null
			game.walk_to(game.nearest_walkable(c.c + 1, c.r))
			return
	pending = {}
	game.target = null
	game.walk_to(cell)

func _do_pending() -> void:
	if pending.is_empty() or game.in_dialogue:
		return
	var hc := game.hero.cell()
	match pending.kind:
		"talk":
			var e = pending.ent
			if e.gone:
				pending = {}
			elif Game.dist(hc, e.cell()) <= 1 or (game.hero.path.is_empty() and Game.dist(hc, e.cell()) <= 2):
				pending = {}
				game.hero.path = []
				game._face(game.hero, e.pos)
				var conv := String(e.npc.get("conversation", ""))
				if conv != "":
					dialogue.start(conv, dialogue._speaker_for(String(e.npc.get("tag", e.npc.get("name", "")))))
		"bag":
			if pending.ent.gone:
				pending = {}
			elif Game.dist(hc, pending.ent.cell()) <= 1:
				game.take_bag(pending.ent)
				pending = {}
		"crate":
			var c = game.M.containers[pending.index]
			if Game.dist(hc, Vector2i(c.c, c.r)) <= 1:
				game.open_container(pending.index)
				pending = {}
			elif game.hero.path.is_empty():
				pending = {}

# --- per frame ---------------------------------------------------------------------------
func _process(dt: float) -> void:
	board.viewer = head.global_position
	if xr:
		_xr_input(dt)
	else:
		_desktop_input()
	if phase == "play":
		_do_pending()
	game.tick(dt)
	_place_panels()

func _flat(v: Vector3) -> Vector3:
	v.y = 0
	return v.normalized() if v.length() > 0.0001 else Vector3.FORWARD

## A walk direction given in view space (x right, y away) -> board cells.
func _stick_to_board(s: Vector2) -> Vector2:
	if s.length() < 0.15:
		return Vector2.ZERO
	var fwd := _flat(-head.global_transform.basis.z)
	var rgt := _flat(head.global_transform.basis.x)
	var wdir := fwd * s.y + rgt * s.x
	var l := board.world.global_transform.basis.inverse() * wdir
	return Vector2(l.x, l.z).normalized() * minf(1.0, s.length())

func _place_board_in_front() -> void:
	var fwd := _flat(-head.global_transform.basis.z)
	board.global_position = head.global_position + fwd * 0.55 + Vector3(0, -0.45, 0)
	board.rotation = Vector3(0, atan2(-fwd.x, -fwd.z), 0)
	_placed = true

func _place_panels() -> void:
	board.visible = phase != "menu"
	var to_head := func(n: Node3D, at: Vector3):
		n.global_position = at
		var d := head.global_position - at
		d.y = 0
		if d.length() > 0.001:
			n.look_at(at - d, Vector3.UP)
	var bp := board.global_position
	var hb := _flat(head.global_position - bp)
	if not xr:
		# desktop: panels sit in front of the camera, like a HUD
		var cam := head.global_transform
		var at := func(n: Node3D, x: float, y: float, z: float):
			n.global_transform = cam * Transform3D(Basis(), Vector3(x, y, -z))
		if dlg_panel.visible: at.call(dlg_panel, 0.08, 0.06, 0.6)
		if menu.visible: at.call(menu, 0, 0, 0.62)
		if hud.visible: at.call(hud, -0.3, -0.13, 0.62)
		return
	if dlg_panel.visible:
		to_head.call(dlg_panel, bp + Vector3(0, 0.3, 0) + hb * 0.12)
	if menu.visible:
		to_head.call(menu, bp + Vector3(0, 0.3, 0) + hb * 0.1)
	if hud.visible:
		if xr:
			hud.global_transform = left.global_transform * Transform3D(Basis.from_euler(Vector3(deg_to_rad(-60), 0, 0)), Vector3(0, 0.06, -0.1))
		else:
			to_head.call(hud, bp + _flat(head.global_transform.basis.x) * -0.36 + Vector3(0, 0.14, 0) + hb * 0.18)

# --- XR input ----------------------------------------------------------------------------
func _xr_input(dt: float) -> void:
	if not _placed and head.global_position.length() > 0.01:
		_place_board_in_front()
	if left.get_is_active() and left.is_button_pressed("primary_click"):
		_place_board_in_front()
	game.stick = _stick_to_board(left.get_vector2("primary")) if phase == "play" else Vector2.ZERO
	var rs := right.get_vector2("primary")
	if absf(rs.x) > 0.7:
		if not _turn_latch:
			_turn(-1 if rs.x > 0 else 1)
			_turn_latch = true
	else:
		_turn_latch = false
	if absf(rs.y) > 0.5:
		_zoom(signf(rs.y) * dt * 4.0)
	# grab and move the board
	var grip := left.get_float("grip") > 0.6
	if grip and not _grabbing:
		_grabbing = true
		_grab_from = left.global_position - board.global_position
	elif grip:
		board.global_position = left.global_position - _grab_from
	else:
		_grabbing = false
	_edge("a", right.is_button_pressed("ax_button"), func(): _attack())
	_edge("b", right.is_button_pressed("by_button"), func(): game.use_potion())
	_edge("x", left.is_button_pressed("ax_button"), func(): game.use_potion())
	_edge("menu", left.is_button_pressed("menu_button"), func(): _toggle_pause())
	var t := right.get_float("trigger") > 0.6
	_laser(right.global_position, -right.global_transform.basis.z, t and not _trigger_was, _trigger_was and not t)
	_trigger_was = t

var _edges := {}
func _edge(k: String, on: bool, cb: Callable) -> void:
	if on and not _edges.get(k, false):
		cb.call()
	_edges[k] = on

## One pointer ray (controller laser or mouse): UI panels first, then the board.
func _laser(o: Vector3, d: Vector3, pressed: bool, released: bool) -> void:
	var q := PhysicsRayQueryParameters3D.create(o, o + d * 5.0, 2)
	q.collide_with_bodies = true
	var hit := get_world_3d().direct_space_state.intersect_ray(q)
	var length := 5.0
	if hit:
		var p: Panel3D = hit.collider.get_parent()
		if _hover_panel and _hover_panel != p:
			_hover_panel.pointer_exit()
		_hover_panel = p
		p.pointer(hit.position, pressed, released)
		length = o.distance_to(hit.position)
		if dot:
			dot.global_position = hit.position
	else:
		if _hover_panel:
			_hover_panel.pointer_exit()
			_hover_panel = null
		var c = board.ray_to_cell(o, d)
		if c != null:
			var wp := board.world.global_transform * Vector3(c.x, 0, c.y)
			length = o.distance_to(wp)
			if dot:
				dot.global_position = wp
			if pressed:
				board_click(c)
	if laser:
		laser.scale = Vector3(1, length, 1)
		laser.position = Vector3(0, 0, -length / 2.0)

# --- desktop input -----------------------------------------------------------------------
var _mouse_down := false
func _desktop_input() -> void:
	var s := Vector2(Input.get_axis(&"ui_left", &"ui_right"), Input.get_axis(&"ui_down", &"ui_up"))
	if Input.is_key_pressed(KEY_A): s.x -= 1
	if Input.is_key_pressed(KEY_D): s.x += 1
	if Input.is_key_pressed(KEY_W): s.y += 1
	if Input.is_key_pressed(KEY_S): s.y -= 1
	if not autotest:
		game.stick = _stick_to_board(s) if phase == "play" else Vector2.ZERO
	var cam := head as Camera3D
	var mp := get_viewport().get_mouse_position()
	var down := Input.is_mouse_button_pressed(MOUSE_BUTTON_LEFT)
	_laser(cam.project_ray_origin(mp), cam.project_ray_normal(mp), down and not _mouse_down, _mouse_down and not down)
	_mouse_down = down

func _unhandled_input(ev: InputEvent) -> void:
	if ev is InputEventKey and ev.pressed and not ev.echo:
		match ev.keycode:
			KEY_SPACE: _attack()
			KEY_H: game.use_potion()
			KEY_P: _toggle_pause()
			KEY_Q: _turn(-1)
			KEY_E: _turn(1)
	elif ev is InputEventMouseButton and ev.pressed:
		if ev.button_index == MOUSE_BUTTON_WHEEL_UP: _zoom(1.0)
		elif ev.button_index == MOUSE_BUTTON_WHEEL_DOWN: _zoom(-1.0)
