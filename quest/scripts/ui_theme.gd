## EK's gold-on-stone look for the floating panels, with the game's own bitmap fonts
## (immortal30 for headings and buttons; body text uses Godot's default font,
## because the APK's tahoma27white bitmap renders some glyph runs slanted) from the v78 APK.
class_name UiTheme
extends RefCounted

const GOLD := Color("e8c66a")
const PAPER := Color("efe2c0")
const STONE := Color(0.10, 0.08, 0.06, 0.92)

static var _theme: Theme

static func theme() -> Theme:
	if _theme:
		return _theme
	var t := Theme.new()
	var head = load("res://data/fonts/immortal30.fnt")
	t.default_font_size = 27
	var panel := StyleBoxFlat.new()
	panel.bg_color = STONE
	panel.border_color = GOLD.darkened(0.25)
	panel.set_border_width_all(3)
	panel.set_corner_radius_all(10)
	panel.set_content_margin_all(18)
	t.set_stylebox("panel", "PanelContainer", panel)
	for state in ["normal", "hover", "pressed", "focus", "disabled"]:
		var b := StyleBoxFlat.new()
		b.bg_color = {"normal": Color(0.22, 0.17, 0.11), "hover": Color(0.38, 0.29, 0.15),
				"pressed": Color(0.5, 0.38, 0.17), "focus": Color(0.22, 0.17, 0.11),
				"disabled": Color(0.15, 0.13, 0.11)}[state]
		b.border_color = GOLD if state == "hover" else GOLD.darkened(0.45)
		b.set_border_width_all(2)
		b.set_corner_radius_all(8)
		b.content_margin_left = 16
		b.content_margin_right = 16
		b.content_margin_top = 8
		b.content_margin_bottom = 8
		t.set_stylebox(state, "Button", b)
	if head:
		t.set_font("font", "Button", head)
	t.set_font_size("font_size", "Button", 30)
	t.set_color("font_color", "Button", PAPER)
	t.set_color("font_hover_color", "Button", GOLD)
	t.set_color("font_color", "Label", PAPER)
	var bg := StyleBoxFlat.new()
	bg.bg_color = Color(0.05, 0.04, 0.03)
	bg.set_corner_radius_all(6)
	var fill := StyleBoxFlat.new()
	fill.bg_color = Color("c0392b")
	fill.set_corner_radius_all(6)
	t.set_stylebox("background", "ProgressBar", bg)
	t.set_stylebox("fill", "ProgressBar", fill)
	t.set_font_size("font_size", "ProgressBar", 20)
	_theme = t
	return t

static func heading_font() -> Font:
	return load("res://data/fonts/immortal30.fnt")
