## The tutorial map as a tabletop diorama (QUEST_3D_TUTORIAL_SPEC.md §2).
##
## Board (this node: placed on the table, yaw-rotated and scaled by the player)
##   └ World (translated so the hero stays at the board centre; 1 unit = 1 cell)
##       ├ floor quad (baked iso floor, affine UVs)
##       ├ one MultiMesh of Y-billboard cards per tileset (trees, rocks, objects)
##       ├ crates, loot bags (Sprite3D)
##       └ actors (Sprite3D on the real 9x11 x 140 px sheets)
class_name Board
extends Node3D

const S := sqrt(2.0) / 64.0                  # art pixel -> cells, horizontally
const SY := S / cos(deg_to_rad(30.0))        # art pixel -> cells, vertically (2:1 = 30° view)
const FRAME := 140
const COLS := 9
const ROW := {"U": 0, "RU": 1, "R": 2, "RD": 3, "D": 4}
const ATTACK_ROW_OFFSET := 5
const FEET := 0.85                           # sprite.js origin: feet at 85% of the frame

var game: Game
var world := Node3D.new()
var view_radius := 11.0                      # cells shown around the hero
var tint := Vector3(0.93, 0.93, 0.93)        # map.js ambientColor: outdoor day
var viewer := Vector3.ZERO                   # head position (world space), set by main
var _mats: Array[ShaderMaterial] = []
var _actors := {}                            # ent id -> {spr, t, attack_t, ent}
var _sheets := {}
var _focus := Vector2.ZERO
var _floaters: Array = []
var _crates: Array = []

func build(g: Game) -> void:
	game = g
	add_child(world)
	_build_floor()
	_build_cards()
	_build_crates()
	game.ent_added.connect(_on_add)
	game.ent_removed.connect(_on_remove)
	game.attacked.connect(_on_attack)
	game.floater.connect(_on_floater)
	game.bolt.connect(_on_bolt)

func snap() -> void:
	_focus = game.hero.pos

func _tex(path: String) -> Texture2D:
	return load(path) as Texture2D

func _build_floor() -> void:
	var cj = Game._json("res://data/cards.json")
	var f = cj.floor
	var w := float(game.W)
	var h := float(game.H)
	# Iso projection (affine): cell-space (x, z) -> floor image pixel.
	var uv := func(x: float, z: float) -> Vector2:
		return Vector2(((x - z) * f.tw / 2.0 + f.offX + f.tw / 2.0) / f.w,
				((x + z) * f.th / 2.0 + f.top) / f.h)
	var st := SurfaceTool.new()
	st.begin(Mesh.PRIMITIVE_TRIANGLES)
	var corners := [Vector2(0, 0), Vector2(w, 0), Vector2(w, h), Vector2(0, 0), Vector2(w, h), Vector2(0, h)]
	for c in corners:
		st.set_uv(uv.call(c.x, c.y))
		st.add_vertex(Vector3(c.x, 0, c.y))
	var mi := MeshInstance3D.new()
	mi.mesh = st.commit()
	var m := ShaderMaterial.new()
	m.shader = load("res://shaders/floor.gdshader")
	m.set_shader_parameter("floor_tex", _tex("res://data/floor.png"))
	mi.material_override = m
	_mats.append(m)
	world.add_child(mi)

func _build_cards() -> void:
	var cj = Game._json("res://data/cards.json")
	var quad := ArrayMesh.new()
	var arr := []
	arr.resize(Mesh.ARRAY_MAX)
	arr[Mesh.ARRAY_VERTEX] = PackedVector3Array([Vector3(-0.5, 0, 0), Vector3(0.5, 0, 0), Vector3(0.5, 1, 0), Vector3(-0.5, 1, 0)])
	arr[Mesh.ARRAY_TEX_UV] = PackedVector2Array([Vector2(0, 1), Vector2(1, 1), Vector2(1, 0), Vector2(0, 0)])
	arr[Mesh.ARRAY_INDEX] = PackedInt32Array([0, 1, 2, 0, 2, 3])
	quad.add_surface_from_arrays(Mesh.PRIMITIVE_TRIANGLES, arr)
	for sh in cj.sheets:
		var mm := MultiMesh.new()
		mm.transform_format = MultiMesh.TRANSFORM_3D
		mm.use_custom_data = true
		mm.mesh = quad
		mm.instance_count = sh.cards.size()
		var cw: float = sh.tilew * S
		var ch: float = sh.tileh * SY
		var drop := 16.0 * SY        # the art's cell centre is 16 px above its bottom edge
		for i in sh.cards.size():
			var cd = sh.cards[i]
			var col := int(cd[2]) % int(sh.columns)
			var row := int(cd[2]) / int(sh.columns)
			var t := Transform3D(Basis.from_scale(Vector3(cw, ch, 1)), Vector3(cd[0] + 0.5, -drop, cd[1] + 0.5))
			mm.set_instance_transform(i, t)
			mm.set_instance_custom_data(i, Color(col * float(sh.tilew) / sh.imagew, row * float(sh.tileh) / sh.imageh,
					float(sh.tilew) / sh.imagew, float(sh.tileh) / sh.imageh))
		var mmi := MultiMeshInstance3D.new()
		mmi.multimesh = mm
		var m := ShaderMaterial.new()
		m.shader = load("res://shaders/card.gdshader")
		m.set_shader_parameter("atlas", _tex("res://data/tiles/" + String(sh.image)))
		mmi.material_override = m
		mmi.custom_aabb = AABB(Vector3(0, -2, 0), Vector3(game.W, 12, game.H))
		_mats.append(m)
		world.add_child(mmi)

func _sprite(tex: Texture2D, px: float) -> Sprite3D:
	var s := Sprite3D.new()
	s.texture = tex
	s.pixel_size = px
	s.billboard = BaseMaterial3D.BILLBOARD_FIXED_Y
	s.shaded = false
	s.alpha_cut = SpriteBase3D.ALPHA_CUT_DISCARD
	s.texture_filter = BaseMaterial3D.TEXTURE_FILTER_LINEAR_WITH_MIPMAPS
	s.scale = Vector3(1, SY / S, 1)
	return s

func _build_crates() -> void:
	for c in game.M.containers:
		var s := _sprite(_tex("res://data/icons/%s.png" % c.icon), S)
		s.offset = Vector2(0, 20)
		s.position = Vector3(c.c + 0.5, 0, c.r + 0.5)
		world.add_child(s)
		_crates.append(s)

# --- actors ------------------------------------------------------------------------------
func _on_add(e) -> void:
	var s: Sprite3D
	if e.is_bag:
		s = _sprite(_tex("res://data/icons/loot.png"), S * 0.8)
		s.offset = Vector2(0, 18)
	else:
		if not _sheets.has(e.sheet):
			_sheets[e.sheet] = _tex("res://data/sprites/%s.png" % e.sheet)
		s = _sprite(_sheets[e.sheet], S)
		s.hframes = COLS
		s.vframes = 11
		s.offset = Vector2(0, FRAME * (FEET - 0.5))
		s.frame = ROW.D * COLS
	world.add_child(s)
	_actors[e.id] = {"spr": s, "t": randf(), "attack_t": -1.0, "ent": e}
	_place(e, s)

func _on_remove(e) -> void:
	var a = _actors.get(e.id)
	if a == null:
		return
	_actors.erase(e.id)
	var s: Sprite3D = a.spr
	if e.is_bag or e.cbt == null:
		s.queue_free()
		return
	var tw := create_tween()                  # fade the corpse out (combat.js _onEnemyDeath)
	tw.tween_property(s, "modulate:a", 0.0, 0.7)
	tw.tween_callback(s.queue_free)

func _on_attack(e) -> void:
	var a = _actors.get(e.id)
	if a:
		a.attack_t = 0.0

func _place(e, s: Sprite3D) -> void:
	s.position = Vector3(e.pos.x, 0, e.pos.y)

## sprite.js / move.js facingFor, but relative to where the viewer stands: the row is
## chosen from the heading as seen on screen, so walking away from you shows the back.
func _facing(e) -> Array:
	var vw := world.to_local(viewer)
	var fwd := Vector2(e.pos.x - vw.x, e.pos.y - vw.z)
	if fwd.length() < 0.001:
		fwd = Vector2(0, -1)
	fwd = fwd.normalized()
	var right := Vector2(-fwd.y, fwd.x)
	var dx: float = e.heading.dot(right)
	var dy: float = -e.heading.dot(fwd)
	var a := rad_to_deg(atan2(dy, dx))
	if a >= -22.5 and a < 22.5: return ["R", false]
	if a >= 22.5 and a < 67.5: return ["RD", false]
	if a >= 67.5 and a < 112.5: return ["D", false]
	if a >= 112.5 and a < 157.5: return ["RD", true]
	if a >= 157.5 or a < -157.5: return ["R", true]
	if a >= -157.5 and a < -112.5: return ["RU", true]
	if a >= -112.5 and a < -67.5: return ["U", false]
	return ["RU", false]

func _process(dt: float) -> void:
	if game == null or game.hero == null:
		return
	# keep the hero at the board centre (the 2D camera follow), eased
	_focus = _focus.lerp(game.hero.pos, minf(1.0, dt * 8.0))
	world.position = Vector3(-_focus.x, 0, -_focus.y)
	var focus_w := global_transform * Vector3.ZERO
	var r := view_radius * global_transform.basis.get_scale().x
	for m in _mats:
		m.set_shader_parameter("viewer_pos", viewer)
		m.set_shader_parameter("focus", focus_w)
		m.set_shader_parameter("radius", r)
		m.set_shader_parameter("tint", tint)
	var col := Color(tint.x, tint.y, tint.z)
	for c in _crates:
		c.modulate = col
		c.visible = (Vector2(c.position.x, c.position.z) - _focus).length() < view_radius
	for id in _actors:
		var a = _actors[id]
		var e = a.ent
		var s: Sprite3D = a.spr
		_place(e, s)
		s.visible = (e.pos - _focus).length() < view_radius
		s.modulate = Color(col, s.modulate.a)
		if e.is_bag:
			continue
		var f := _facing(e)
		var row: int = ROW[f[0]]
		s.flip_h = f[1]
		a.t += dt
		if a.attack_t >= 0.0:
			a.attack_t += dt
			var fr := int(a.attack_t * 14.0)
			if fr >= 4:
				a.attack_t = -1.0
			else:
				s.frame = (row + ATTACK_ROW_OFFSET) * COLS + fr
				continue
		if e.moving:
			s.frame = row * COLS + 1 + int(a.t * 12.0) % 8
		else:
			s.frame = row * COLS
	for fl in _floaters.duplicate():
		fl.life -= dt
		fl.node.position.y += dt * 0.9
		fl.node.modulate.a = clampf(fl.life / 0.9, 0.0, 1.0)
		fl.node.outline_modulate.a = fl.node.modulate.a
		if fl.life <= 0.0:
			fl.node.queue_free()
			_floaters.erase(fl)

func _on_floater(e, text: String, color: Color, big: bool) -> void:
	if text == "":
		return
	var l := Label3D.new()
	l.text = text
	l.billboard = BaseMaterial3D.BILLBOARD_ENABLED
	l.no_depth_test = true
	l.font_size = 64 if big else 48
	l.pixel_size = 0.012
	l.outline_size = 16
	l.modulate = color
	l.outline_modulate = Color(0, 0, 0)
	l.position = Vector3(e.pos.x, 2.2, e.pos.y)
	world.add_child(l)
	_floaters.append({"node": l, "life": 0.9})

func _on_bolt(from: Vector2, to: Vector2, secs: float) -> void:
	var m := MeshInstance3D.new()
	var sp := SphereMesh.new()
	sp.radius = 0.12
	sp.height = 0.24
	m.mesh = sp
	var mat := StandardMaterial3D.new()
	mat.shading_mode = BaseMaterial3D.SHADING_MODE_UNSHADED
	mat.albedo_color = Color(0.7, 0.85, 1.0)
	m.material_override = mat
	m.position = Vector3(from.x, 1.0, from.y)
	world.add_child(m)
	var tw := create_tween()
	tw.tween_property(m, "position", Vector3(to.x, 1.0, to.y), secs)
	tw.tween_callback(m.queue_free)

## World (cell-space) point under a world-space ray, on the floor plane, or null.
func ray_to_cell(origin: Vector3, dir: Vector3):
	var inv := world.global_transform.affine_inverse()
	var o := inv * origin
	var d := inv.basis * dir
	if absf(d.y) < 1e-5:
		return null
	var t := -o.y / d.y
	if t <= 0.0:
		return null
	var p := o + d * t
	if Vector2(p.x, p.z).distance_to(_focus) > view_radius:
		return null
	return Vector2(p.x, p.z)
