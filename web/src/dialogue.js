// Dialogue reader — parses and runs EK conversation trees.
//
// A conversation .txt is tab-separated: index, type(Q|A), text, text_ES, GoTo,
// conditions, actions. Rows sharing an index are alternatives:
//   - Q rows are NPC lines; the engine shows the first whose conditions pass, then a
//     "Continue" that jumps to its GoTo.
//   - A rows are player answers; each passing-condition row is a choice button that
//     jumps to its GoTo.
// GoTo 0 ends the conversation. Conditions/actions use "Verb#args;Verb#args".
//
// The world state (global variables + party/followers) lives on the scene so quests,
// triggers and dialogue all read/write the same flags — exactly how the base game's
// GameData variable store + party work (see GameLogic/Condition.java, ScriptedAction).

const COLS = { index: 0, type: 1, text: 2, goto: 4, conditions: 5, actions: 6 };

export function parseConversation(txt) {
  const nodes = new Map();                            // index -> [rows]
  const lines = txt.split('\n');
  for (let i = 1; i < lines.length; i++) {            // row 0 is the header
    const f = lines[i].replace(/\r$/, '').split('\t');
    const index = (f[COLS.index] || '').trim();
    const type = (f[COLS.type] || '').trim();
    if (!index || (type !== 'Q' && type !== 'A')) continue;
    const row = {
      index, type,
      text: f[COLS.text] || '',
      goto: (f[COLS.goto] || '').trim() || '0',
      conditions: unquote((f[COLS.conditions] || '').trim()),
      actions: unquote((f[COLS.actions] || '').trim()),
    };
    if (!nodes.has(index)) nodes.set(index, []);
    nodes.get(index).push(row);
  }
  return nodes;
}

// EK TSV wraps condition/action fields in double quotes whenever they contain commas
// (e.g. "SetVariable#x,10;..."). Strip the wrapping quotes and unescape doubled quotes
// so the FIRST verb isn't mis-parsed with a leading '"'.
function unquote(s) {
  if (s.length >= 2 && s[0] === '"' && s[s.length - 1] === '"') s = s.slice(1, -1);
  return s.replace(/""/g, '"');
}

// Split "Verb#a,b;Verb2#c" into [{ verb, args:[..] }].
function parseOps(s) {
  if (!s) return [];
  return s.split(';').filter(Boolean).map(part => {
    const [verb, rest] = part.split('#');
    return { verb: (verb || '').trim(), args: (rest || '').split(',').map(x => x.trim()) };
  });
}

export class Dialogue {
  constructor(scene, root) {
    this.scene = scene;
    this.state = scene.gameState || (scene.gameState = { vars: {}, followers: new Set() });
    this.active = false;
    this._buildUI(root);
  }

  _buildUI(root) {
    this.box = document.createElement('div');
    this.box.id = 'dlg'; this.box.style.display = 'none';
    this.box.innerHTML =
      '<div class="dlg-inner">' +
      '  <img class="dlg-portrait" alt="">' +
      '  <div class="dlg-body">' +
      '    <div class="dlg-name"></div>' +
      '    <div class="dlg-text"></div>' +
      '    <div class="dlg-choices"></div>' +
      '  </div>' +
      '</div>';
    root.appendChild(this.box);
    this.$portrait = this.box.querySelector('.dlg-portrait');
    this.$name = this.box.querySelector('.dlg-name');
    this.$text = this.box.querySelector('.dlg-text');
    this.$choices = this.box.querySelector('.dlg-choices');
  }

  // ---- condition / action evaluation -----------------------------------------
  passes(condStr) {
    for (const { verb, args } of parseOps(condStr)) {
      const v = verb.toLowerCase();
      const has = (t) => this.state.followers.has(t);
      const num = (name) => (this.state.vars[name] || 0);
      if (v === 'npcisfollower' || v === 'npcisinparty') { if (!has(args[0])) return false; }
      else if (v === 'npcisnotinparty' || v === 'npcisnotfollower') { if (has(args[0])) return false; }
      else if (v === 'variablelower') { if (!(num(args[0]) < +args[1])) return false; }
      else if (v === 'variablegreater') { if (!(num(args[0]) > +args[1])) return false; }
      else if (v === 'variableequal' || v === 'variable') { if (!(num(args[0]) === +args[1])) return false; }
      // unknown conditions are treated as satisfied so dialogue never dead-ends
    }
    return true;
  }

  // Run a node's action list. Most verbs apply immediately, but Travel# is DEFERRED
  // until the whole list has run: the real engine executes every action in order
  // (see ScriptedAction switch), so SetVariable#/PlayerRobbed# that follow a Travel#
  // must still take effect. StopRender#/Sleep# request a fade-to-black that wraps the
  // travel — this is the tutorial's camp→sleep→robbed→wake-near-Lannager beat.
  runActions(actStr) {
    let travel = null, fade = false;
    for (const { verb, args } of parseOps(actStr)) {
      const v = verb.toLowerCase();
      const set = (n, val) => { this.state.vars[n] = val; };
      if (v === 'startconversation') { this.start(args[0], args[1] || '1'); return 'jumped'; }
      else if (v === 'travel') { travel = { map: args[0], entry: args[1] }; }
      else if (v === 'npcfollow') this.state.followers.add(args[0]);
      else if (v === 'npcleaveparty' || v === 'npcstop' || v === 'npcstopfollowing')
        this.state.followers.delete(args[0]);
      else if (v === 'setvariable') set(args[0], +args[1]);
      else if (v === 'incvariable' || v === 'variableraise') set(args[0], (this.state.vars[args[0]] || 0) + (+args[1] || 1));
      else if (v === 'decvariable' || v === 'variablelower') set(args[0], (this.state.vars[args[0]] || 0) - (+args[1] || 1));
      else if (v === 'stoprender' || v === 'sleep') fade = true;
      else if (v === 'playerrobbed') this._robPlayer();
      else if (v === 'gaingold') this._gold(+args[0] || 0);
      else if (v === 'losegold') this._gold(-(+args[0] || 0));
      else if (v === 'gainskillpoint') { const m = this.scene.playerModel; if (m) m.skillPoints = (m.skillPoints || 0) + (+args[0] || 1); }
      else if (v === 'trainskill') this._train(args[0]);
      else if (v === 'resetplayerskills') { const m = this.scene.playerModel; if (m) { m.trained.clear(); m.disciplines.clear(); } }
      // other verbs (give items, quests, sounds…) are no-ops for now
    }
    if (travel) {
      this.end();
      this._travel(travel.map, travel.entry, fade);       // async; fire-and-forget
      return 'travelled';
    }
    return 'ok';
  }

  // PlayerRobbed# — the real engine (Player.C1) wipes the inventory and resets gold to
  // 18 (the starting purse). No item system yet, so we mirror the gold reset and clear
  // any tracked items, then refresh the HUD.
  // TrainSkill#<id> — a trainer NPC teaches an advanced skill (deobf/TRAINERS_SPEC.md).
  _train(id) {
    const m = this.scene.playerModel;
    if (!m) return;
    const learned = m.learnSkill(id, this.scene.trainers || {});
    if (learned && this.scene.gameHud) this.scene.gameHud.update(true);
  }

  _gold(n) {
    const m = this.scene.playerModel;
    if (!m) return;
    if (n >= 0) m.addGold(n); else m.spendGold(-n);
    if (this.scene.gameHud) this.scene.gameHud.update(true);
  }

  _robPlayer() {
    const pm = this.scene.playerModel;
    if (!pm) return;
    if (Array.isArray(pm.items)) pm.items.length = 0;
    pm.gold = 18;
    if (this.scene.gameHud) this.scene.gameHud.update(true);
  }

  // Deferred Travel#. When `fade` is set (Sleep#/StopRender#), black the screen out,
  // swap area behind the curtain, then fade back in — the tutorial wake-up.
  async _travel(map, entry, fade) {
    if (fade && this.scene.fadeBlack) {
      await this.scene.fadeBlack(true);                   // curtain down
      await this.scene.goArea(map, entry, true);          // preferDefault entry ('0001')
      await this.scene.fadeBlack(false);                  // curtain up
    } else {
      this.scene.goArea(map, entry, true);
    }
  }

  // ---- run --------------------------------------------------------------------
  async start(convId, startNode = '1', speaker) {
    if (speaker) this._speaker = speaker;
    try {
      if (this._convId !== convId) {
        const txt = await fetch(`assets/conversations/${convId}.txt`).then(r => r.text());
        this._nodes = parseConversation(txt);
        this._convId = convId;
      }
    } catch { this.end(); return; }
    this.active = true;
    this.scene._dialogue = true;
    this.box.style.display = 'block';
    if (this.scene.setChromeHidden) this.scene.setChromeHidden(true);   // hide the tap toggle
    this._show(String(startNode));
  }

  _show(index) {
    if (!index || index === '0') { this.end(); return; }
    const rows = (this._nodes.get(index) || []).filter(r => this.passes(r.conditions));
    if (!rows.length) { this.end(); return; }

    const isPlayer = rows[0].type === 'A';
    this.$name.textContent = isPlayer ? (this.scene.player ? this.scene.player.name : 'You')
                                      : (this._speaker && this._speaker.label) || 'NPC';
    this._setPortrait(isPlayer);
    this.$choices.innerHTML = '';

    if (isPlayer) {                                    // player answers -> choice buttons
      this.$text.textContent = '';
      for (const row of rows) {
        const b = document.createElement('button');
        b.className = 'dlg-choice';
        b.textContent = clean(row.text);
        b.onclick = () => {
          if (this.runActions(row.actions) === 'jumped' || !this.active) return;
          this._show(row.goto);
        };
        this.$choices.appendChild(b);
      }
    } else {                                           // NPC line -> first valid, Continue
      const row = rows[0];
      this.$text.textContent = clean(row.text);
      const outcome = this.runActions(row.actions);
      if (outcome === 'jumped' || outcome === 'travelled') return;
      const b = document.createElement('button');
      b.className = 'dlg-choice dlg-continue';
      b.textContent = row.goto === '0' ? 'End' : 'Continue';
      b.onclick = () => this._show(row.goto);
      this.$choices.appendChild(b);
    }
  }

  _setPortrait(isPlayer) {
    let src = '';
    if (isPlayer && this.scene.player && this.scene.player.portrait)
      src = `assets/portraits/${this.scene.player.gender.toLowerCase()}/${this.scene.player.portrait}`;
    else if (!isPlayer && this._speaker && this._speaker.portrait)
      src = this._speaker.portrait;
    this.$portrait.style.display = src ? 'block' : 'none';
    if (src) this.$portrait.src = src;
  }

  end() {
    this.active = false;
    this.scene._dialogue = false;
    this._speaker = null;
    this.box.style.display = 'none';
    if (this.scene.setChromeHidden) this.scene.setChromeHidden(false);   // restore the tap toggle
  }
}

// Strip EK inline markup ([BLUE]..[], <p>) from a display string.
function clean(s) { return s.replace(/\[[A-Z]*\]/g, '').replace(/<p>/g, '\n').trim(); }
