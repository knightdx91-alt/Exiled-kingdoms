## EK conversation trees (port of web/src/dialogue.js; QUEST_3D_TUTORIAL_SPEC.md §1).
##
## A conversation .txt is tab-separated: index, type (Q|A), text, text_ES, GoTo,
## conditions, actions. Rows sharing an index are alternatives: the first passing Q row
## is the NPC line; every passing A row is a player choice. GoTo 0 ends.
class_name Dialogue
extends RefCounted

signal show(view: Dictionary)     # {speaker, portrait, player, text, choices: [String]}
signal ended
signal sleep_requested            # StopRender#/Sleep# + Travel#: the camp night (APPROX Q7)

var game: Game
var nodes := {}
var conv := ""
var speaker := {}
var player_portrait := ""
var active := false
var _rows: Array = []
var _deferred := ""               # actions held until the player presses Continue

func _init(g: Game) -> void:
	game = g

static func parse(txt: String) -> Dictionary:
	var out := {}
	var lines := txt.trim_prefix("﻿").split("\n")
	for i in range(1, lines.size()):
		var f := lines[i].trim_suffix("\r").split("\t")
		if f.size() < 2:
			continue
		var idx := f[0].strip_edges()
		var type := f[1].strip_edges()
		if idx == "" or (type != "Q" and type != "A"):
			continue
		var row := {"type": type, "text": f[2] if f.size() > 2 else "",
				"goto": (f[4].strip_edges() if f.size() > 4 else "") if f.size() > 4 and f[4].strip_edges() != "" else "0",
				"cond": _unquote(f[5].strip_edges() if f.size() > 5 else ""),
				"act": _unquote(f[6].strip_edges() if f.size() > 6 else "")}
		if not out.has(idx):
			out[idx] = []
		out[idx].append(row)
	return out

## EK wraps comma-bearing fields in quotes; strip them so the first verb parses.
static func _unquote(s: String) -> String:
	if s.length() >= 2 and s[0] == "\"" and s[s.length() - 1] == "\"":
		s = s.substr(1, s.length() - 2)
	return s.replace("\"\"", "\"")

static func ops(s: String) -> Array:
	var out := []
	for part in s.split(";", false):
		var bits := part.split("#")
		var args := []
		if bits.size() > 1:
			for a in bits[1].split(","):
				args.append(a.strip_edges())
		out.append({"verb": bits[0].strip_edges().to_lower(), "args": args})
	return out

static func clean(s: String) -> String:
	var re := RegEx.create_from_string("\\[[A-Z]*\\]")
	return re.sub(s, "", true).replace("<p>", "\n").strip_edges()

func passes(cond: String) -> bool:
	for o in ops(cond):
		var v: String = o.verb
		var a: Array = o.args
		var n := func(k): return int(game.vars.get(k, 0))
		if v == "npcisfollower" or v == "npcisinparty":
			if not game.followers.has(a[0]): return false
		elif v == "npcisnotinparty" or v == "npcisnotfollower":
			if game.followers.has(a[0]): return false
		elif v == "variablelower":
			if not (n.call(a[0]) < int(a[1])): return false
		elif v == "variablegreater":
			if not (n.call(a[0]) > int(a[1])): return false
		elif v == "variableequal" or v == "variable":
			if not (n.call(a[0]) == int(a[1])): return false
		# unknown conditions pass, so a tree never dead-ends (same as web)
	return true

## Run an action list. StartConversation jumps; Travel#/Sleep#/StopRender# are the
## camp exit and are deferred until the player has read the line.
func run(acts: String) -> String:
	var travel := false
	for o in ops(acts):
		var v: String = o.verb
		var a: Array = o.args
		if v == "startconversation":
			start(a[1] if a.size() > 1 else a[0], _speaker_for(a[0]))
			return "jumped"
		elif v == "npcfollow":
			game.followers[a[0]] = true
			game.message.emit("Adaon joins you.", Color("8fd0ff"))
			game.hud_changed.emit()
		elif v == "npcstopfollowing" or v == "npcleaveparty":
			game.followers.erase(a[0])
			game.hud_changed.emit()
		elif v == "setvariable":
			game.vars[a[0]] = int(a[1])
		elif v == "incvariable":
			game.vars[a[0]] = int(game.vars.get(a[0], 0)) + (int(a[1]) if a.size() > 1 else 1)
		elif v == "playerrobbed":
			# Player.C1: inventory wiped, gold reset to the starting purse (18).
			game.backpack.clear()
			game.worn.clear()
			game.gold = 18
			game.hp = mini(game.hp, game.max_hp())
			game.hud_changed.emit()
		elif v == "travel" or v == "sleep" or v == "stoprender":
			travel = true
	return "travel" if travel else "ok"

func _speaker_for(tag: String) -> Dictionary:
	for e in game.ents:
		if e.npc and (e.npc.get("tag") == tag or e.npc.get("name") == tag or e.npc.get("spawn") == tag):
			var p := String(e.rec.get("portrait", "0"))
			var g := "female" if e.rec.get("gender", "M") == "F" else "male"
			return {"name": e.name, "portrait": "" if p == "0" else "res://data/portraits/%s_%s.png" % [g, p]}
	return {"name": tag.capitalize(), "portrait": ""}

func start(conv_id: String, who: Dictionary) -> void:
	if conv != conv_id:
		nodes = parse(FileAccess.get_file_as_string("res://data/conv/%s.txt" % conv_id))
		conv = conv_id
	speaker = who
	active = true
	game.in_dialogue = true
	_show("1")

func _show(idx: String) -> void:
	if idx == "" or idx == "0":
		end()
		return
	var rows := []
	for r in nodes.get(idx, []):
		if passes(r.cond):
			rows.append(r)
	if rows.is_empty():
		end()
		return
	_rows = rows
	var is_player: bool = rows[0].type == "A"
	var view := {"speaker": "You" if is_player else speaker.get("name", "NPC"),
			"portrait": player_portrait if is_player else speaker.get("portrait", ""),
			"player": is_player, "text": "", "choices": []}
	if is_player:
		for r in rows:
			view.choices.append(clean(r.text))
	else:
		var r = rows[0]
		view.text = clean(r.text)
		var out := run(r.act)
		if out == "jumped":
			return
		if out == "travel":
			_deferred = "sleep"
			view.choices.append("Sleep")
		else:
			view.choices.append("End" if r.goto == "0" else "Continue")
	show.emit(view)

func choose(i: int) -> void:
	if not active or i < 0 or i >= _rows.size() and _rows[0].type == "A":
		return
	if _deferred == "sleep":
		_deferred = ""
		end()
		sleep_requested.emit()
		return
	var r = _rows[mini(i, _rows.size() - 1)]
	if r.type == "A":
		if run(r.act) == "jumped" or not active:
			return
	_show(String(r.goto))

func end() -> void:
	active = false
	game.in_dialogue = false
	_rows = []
	ended.emit()
