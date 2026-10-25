## A flat 2D UI (Control tree in a SubViewport) shown on a quad in 3D, clickable with
## the controller laser or, on desktop, the mouse ray. Collision layer 2 = UI.
class_name Panel3D
extends Node3D

var vp := SubViewport.new()
var root := Control.new()
var size_px := Vector2i(800, 500)
var width_m := 0.5
var _body := StaticBody3D.new()
var _mesh := MeshInstance3D.new()
var _last := Vector2(-1, -1)

func setup(px: Vector2i, meters: float) -> void:
	size_px = px
	width_m = meters
	vp.size = px
	vp.transparent_bg = true
	vp.disable_3d = true
	vp.gui_embed_subwindows = true
	vp.render_target_update_mode = SubViewport.UPDATE_ALWAYS
	add_child(vp)
	root.size = Vector2(px)
	root.theme = UiTheme.theme()
	vp.add_child(root)
	var h := meters * px.y / px.x
	var q := QuadMesh.new()
	q.size = Vector2(meters, h)
	_mesh.mesh = q
	var m := StandardMaterial3D.new()
	m.shading_mode = BaseMaterial3D.SHADING_MODE_UNSHADED
	m.transparency = BaseMaterial3D.TRANSPARENCY_ALPHA
	m.albedo_texture = vp.get_texture()
	m.texture_filter = BaseMaterial3D.TEXTURE_FILTER_LINEAR_WITH_MIPMAPS
	m.no_depth_test = true          # panels float above the board, never sink into it
	m.render_priority = 2
	_mesh.material_override = m
	add_child(_mesh)
	var cs := CollisionShape3D.new()
	var box := BoxShape3D.new()
	box.size = Vector3(meters, h, 0.01)
	cs.shape = box
	_body.collision_layer = 2
	_body.collision_mask = 0
	_body.add_child(cs)
	add_child(_body)

func body() -> StaticBody3D:
	return _body

## Feed a laser hit (world point) into the 2D UI as mouse motion / button events.
func pointer(hit: Vector3, pressed: bool, released: bool) -> void:
	var l := to_local(hit)
	var h := width_m * size_px.y / size_px.x
	var p := Vector2((l.x / width_m + 0.5) * size_px.x, (0.5 - l.y / h) * size_px.y)
	if p != _last:
		var mv := InputEventMouseMotion.new()
		mv.position = p
		mv.global_position = p
		mv.relative = p - _last if _last.x >= 0 else Vector2.ZERO
		vp.push_input(mv)
		_last = p
	if pressed or released:
		var b := InputEventMouseButton.new()
		b.button_index = MOUSE_BUTTON_LEFT
		b.pressed = pressed
		b.position = p
		b.global_position = p
		vp.push_input(b)

func pointer_exit() -> void:
	if _last.x >= 0:
		var mv := InputEventMouseMotion.new()
		mv.position = Vector2(-100, -100)
		vp.push_input(mv)
	_last = Vector2(-1, -1)

func set_shown(on: bool) -> void:
	visible = on
	vp.render_target_update_mode = SubViewport.UPDATE_ALWAYS if on else SubViewport.UPDATE_DISABLED
	_body.collision_layer = 2 if on else 0
