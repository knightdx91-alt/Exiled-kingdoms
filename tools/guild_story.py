"""Story engine for the guild main quest lines (deobf/GUILD_EXPANSION_SPEC.md §10).

Compiles tools/guild_story_data.py into conversation rows (scenes injected at node 1 of existing NPC files, or new
conversation files for new characters), map objects (the new characters and ambushes, shown only for their story
steps), quest-item rows, the leader's hub rows (chapter offers, reminders) and the journal quest ekg_st_<g>.
Used by guild_expansion.py, which passes its file helpers in `H`.
"""
import re

B_ST = 'ekg_st_'


class Story:
    def __init__(self, g, data, H):
        self.g, self.d, self.H = g, data, H
        self.var = B_ST + g
        self.state = {}          # 'k.i' -> state number; 'k.done'
        self.first = {}          # chapter k -> first state
        s = 1
        for k, ch in enumerate(data['chapters'], 1):
            self.first[k] = s
            for i, st in enumerate(ch['steps'], 1):
                self.state['%d.%d' % (k, i)] = s
                self.state['%d.%s' % (k, st['label'])] = s
                s += 1
            self.state['%d.done' % k] = s
            s += 1
        assert s < 100, 'too many story states'
        self.state['0.done'] = 0
        self.rows = {}           # conv file -> list of row dicts (scene rows)
        self.newfiles = {}       # conv file -> list of idle rows

    # ------------------------------------------------------------------ helpers
    def st(self, ref):
        return self.state[ref]

    def fmt(self, text):
        return re.sub(r'\{(\d+\.\w+)\}', lambda m: str(self.state[m.group(1)]), text)

    def promote_rank(self, k):
        return sum(1 for c in self.d['chapters'][:k] if c.get('promote'))

    # ------------------------------------------------------------------ scenes
    def compile(self, R, alloc, G, reward_actions):
        """R(index,type,text,goto,cond,act) builds a row; alloc(file) gives a free node number."""
        chs = self.d['chapters']
        for k, ch in enumerate(chs, 1):
            for i, stp in enumerate(ch['steps'], 1):
                s = self.st('%d.%d' % (k, i))
                last = i == len(ch['steps'])
                nxt = self.st('%d.done' % k)
                if not last:
                    nxt = s + 1
                fin = ['SetVariable#%s,%d' % (self.var, nxt)]
                master_block = None
                if last and ch.get('promote'):
                    r = self.promote_rank(k)
                    fin = reward_actions(r, master=ch.get('master')) + (
                        ['SetVariable#%s,100' % self.var] if ch.get('master') else fin)
                    if ch.get('master'):
                        master_block = ['SetVariable#%s,%d' % (self.var, self.st('%d.done' % k))]
                f = stp['at']
                rows = self.rows.setdefault(f, [])
                beats = stp['scene']
                qn = [1] + [alloc(f) for _ in beats[1:]]
                an = [alloc(f) for _ in beats]
                needs = stp.get('alt_need') or [stp.get('need', [])]
                for j, bt in enumerate(beats):
                    texts = bt['t'] if isinstance(bt['t'], list) else [(None, bt['t'])]
                    if j == 0:
                        for nd in needs:
                            for vc, tx in texts:
                                c = ['VariableEqual#%s,%d' % (self.var, s)] + list(nd) + ([vc] if vc else [])
                                rows.append(('entry', R(1, 'Q', self.fmt(tx), an[j], ';'.join(c))))
                    else:
                        for vc, tx in texts:
                            rows.append(('body', R(qn[j], 'Q', self.fmt(tx), an[j], vc or '')))
                    for op in bt['o']:
                        go = op.get('go')
                        acts = [op['a']] if op.get('a') else []
                        cond = [op['c']] if op.get('c') else []
                        if go is None:
                            if op.get('jump'):
                                acts.append('SetVariable#%s,%d' % (self.var, self.st('%d.%s' % (k, op['jump']))))
                            else:
                                if master_block:
                                    rows.append(('body', R(an[j], 'A', op['t'] + ' ' + '[BLUE](You already lead another guild: the seat cannot be yours)[]',
                                                            0, ';'.join(cond + ['VariableGreater#ekg_gm,0']),
                                                            ';'.join(acts + master_block))))
                                    cond = cond + ['VariableLower#ekg_gm,1']
                                acts += fin
                            goto = 0
                        elif go == 'hold':
                            goto = 0
                        else:
                            goto = qn[go]
                        rows.append(('body', R(an[j], 'A', self.fmt(op['t']), goto, ';'.join(cond), ';'.join(acts))))
        for npc in self.d['npcs']:
            if npc['kind'] in ('static', 'talker') and npc.get('talk', True):
                idle = self.d['idle'].get(npc['id'], '...')
                idle = idle if isinstance(idle, list) else [(None, idle)]
                self.newfiles[npc['id']] = [R(1, 'Q', self.fmt(t), 0, self.fmt(c) if c else '') for c, t in idle]

    # ------------------------------------------------------------------ hub rows at the leader (node 720)
    def hub(self, R, G, rk, rep, T):
        chs = self.d['chapters']
        rows = [R(720, 'Q', G['gm_text'], 701, 'VariableEqual#%s,5' % rk)]
        for k, ch in enumerate(chs, 1):
            for i, stp in enumerate(ch['steps'], 1):
                s = self.st('%d.%d' % (k, i))
                rows.append(R(720, 'Q', '[BLUE](%s, chapter %d: %s)[] %s' % (self.d.get('quest', ''), k, ch['title'], stp['j']),
                              701, 'VariableEqual#%s,%d' % (self.var, s)))
        for k, ch in enumerate(chs, 1):
            prev = self.st('%d.done' % (k - 1))
            cond = ['VariableEqual#%s,%d' % (self.var, prev), 'PlayerIsLevel#%d' % ch['level'],
                    'VariableGreater#%s,%d' % (rep, ch['rep'] - 1)]
            if ch.get('master'):
                rows.append(R(720, 'Q', "You have climbed as high as any member can while you lead another guild. Only one seat may be yours.",
                              701, 'VariableEqual#%s,%d;VariableGreater#ekg_gm,0' % (self.var, prev)))
                cond.append('VariableLower#ekg_gm,1')
            a = 760 + k
            rows.append(R(720, 'Q', '[BLUE](Chapter %d: %s)[] %s' % (k, ch['title'], ch['brief']), a, ';'.join(cond)))
            rows.append(R(a, 'A', "I'll do it.", 0, '', 'SetVariable#%s,%d' % (self.var, self.first[k])))
            rows.append(R(a, 'A', 'Not yet.', 700))
            rows.append(R(720, 'Q', "When you have fulfilled at least [BLUE]%d guild contracts[] and reached [BLUE]level %d[], I will have work for you: the next chapter, '%s'." % (ch['rep'], ch['level'], ch['title']),
                          701, 'VariableEqual#%s,%d' % (self.var, prev)))
        return rows

    def migrate_rows(self, R, G, rk, goto, T):
        """v74 saves with a rank already earned: jump to the end of the chapter that grants that rank."""
        out = []
        promo = [k for k, c in enumerate(self.d['chapters'], 1) if c.get('promote')]
        for r in range(1, 6):
            k = promo[r - 1]
            tgt = 100 if r == 5 else self.st('%d.done' % k)
            out.append(R(1, 'Q', "Welcome back, %s. A great deal has happened while you were away; the guild has work only you can do. [BLUE](The %s story continues from your rank)[]"
                         % (T[r], G['name']), goto, 'VariableEqual#%s,%d;VariableLower#%s,1;VariableEqual#guild_%s,1' % (rk, r, self.var, self.g),
                         'SetVariable#%s,%d' % (self.var, tgt)))
        return out

    # ------------------------------------------------------------------ journal
    def journal(self, G, T):
        st = {}
        for k, ch in enumerate(self.d['chapters'], 1):
            for i, stp in enumerate(ch['steps'], 1):
                st[self.st('%d.%d' % (k, i))] = '[%s] %s' % (ch['title'], stp['j'])
            if not ch.get('master'):
                nk = self.d['chapters'][k] if k < len(self.d['chapters']) else None
                st[self.st('%d.done' % k)] = "Chapter %d, '%s', is complete.%s" % (
                    k, ch['title'], (" The next chapter, '%s', needs level %d and %d guild contracts; then I should speak to %s." % (
                        nk['title'], nk['level'], nk['rep'], G['leader_name'])) if nk else '')
            else:
                st[self.st('%d.done' % k)] = "Chapter %d, '%s': I could not take the seat while leading another guild." % (k, ch['title'])
        st[100] = 'I became %s of the %s.' % (T[5], G['name'])
        return st

    # ------------------------------------------------------------------ map objects
    def objects(self):
        out = {}
        for n in self.d['npcs']:
            a = self.st(n['show'][0])
            cond = ['VariableGreater#%s,%d' % (self.var, a - 1)]
            if n['show'][1]:
                b = self.st(n['show'][1])
                cond.append('VariableLower#%s,%d' % (self.var, b + 1))
            if n.get('cond'):
                cond.append(n['cond'])
            p = [('conditions', ';'.join(cond)), ('name', n['name'])]
            if n['kind'] == 'static':
                typ = 'staticNPC'
                p += [('conversation', n['id']), ('facing', 'l'), ('gender', n.get('gender', 'm')),
                      ('portrait', str(n.get('portrait', 18))), ('sprite', n['sprite']), ('tag', n['id'] + '_t')]
            else:
                typ = 'spawn'
                p += [('spawn', n['spawn']), ('unique_tag', n['id']), ('tag', n.get('group', n['id'] + '_t'))]
                if n['kind'] == 'foe':
                    p += [('faction', n.get('faction', 'enemy')), ('hostile', 'VariableLower#false,1'), ('wander', '1')]
                else:
                    p += [('faction', 'neutral'), ('wander', '0')]
                    if n.get('talk', True):
                        p += [('conversation', n['id']), ('portrait', str(n.get('portrait', 18)))]
            out.setdefault(n['map'], []).append((typ, n['id'], n['at'], p))
        return out
