#!/usr/bin/env python3
"""Play a guild's main quest line on built data (deobf/GUILD_EXPANSION_SPEC.md §10) with many choice policies.

usage: guild_story_sim.py <data dir containing conversations/ and tmx/> <guild> [runs]
Checks every run reaches Guild Master (ekg_st_<g> = 100, rank 5), that each scene's speaker exists (conversation
file) and that every NPCIsDead tag it needs is a unique_tag on some map. Exit code 1 on any failure.
"""
import collections
import glob
import os
import random
import re
import sys

D, g = sys.argv[1], sys.argv[2]
RUNS = int(sys.argv[3]) if len(sys.argv) > 3 else 40
CONV = os.path.join(D, 'conversations')
LEADER = {'warriors': ('NG_warriors_daukar', 'Are there any contracts'), 'seventh': ('NG_sewers_kardagis', 'Guild business'),
          'wizards': ('IM_ilemma', 'Guild business'), 'three': ('NI_hall_archbishop', 'Guild business'),
          'loreseekers': ('NG_library_librarian', 'Guild business'), 'golden': ('FT_library_guardian', 'Guild business')}[g]
CONTRACT = {'warriors': (1001, 3), 'seventh': (1008, 4), 'wizards': (1005, 3), 'three': (1009, 10),
            'loreseekers': (1022, 2), 'golden': (2015, 1)}[g]
SV = 'ekg_st_' + g
CACHE = {}


def load(f):
    if f not in CACHE:
        p = os.path.join(CONV, f + '.txt')
        L = open(p, encoding='utf-8-sig', errors='replace').read().replace('\r\n', '\n').split('\n')
        h = [x.strip() for x in L[0].split('\t')]
        rows = []
        if 'index' not in h or 'conditions' not in h:
            CACHE[f] = rows
            return rows
        for l in L[1:]:
            r = l.split('\t')
            if len(r) < len(h) - 1:
                continue
            rows.append({k: v.strip().strip('"') for k, v in zip(h, r)})
        CACHE[f] = rows
    return CACHE[f]


UNIQUE = set()
for p in glob.glob(os.path.join(D, 'tmx', '*.tmx')):
    UNIQUE |= set(re.findall(r'name="unique_tag" value="([^"]+)"', open(p, encoding='utf-8', errors='replace').read()))


class Game:
    def __init__(self, rng):
        self.V = collections.defaultdict(int)
        self.dead, self.inv, self.gold, self.rng = set(), collections.Counter(), 100000, rng
        self.V['guild_' + g] = 1
        self.V['talked_librarian'] = 1      # joining the Loreseekers means you have met Rurazar already

    def cond(self, c):
        for x in [x for x in c.split(';') if x]:
            n, _, a = x.partition('#')
            args = a.split(',')
            n = n[0].upper() + n[1:]
            if n == 'VariableEqual':
                ok = self.V[args[0]] == int(args[1])
            elif n == 'VariableGreater':
                ok = self.V[args[0]] > int(args[1])
            elif n == 'VariableLower':
                ok = self.V[args[0]] < int(args[1])
            elif n == 'PlayerIsLevel':
                ok = True
            elif n == 'NPCIsDead':
                ok = a in self.dead
            elif n == 'PlayerHasItems':
                ok = self.inv[int(args[0])] >= int(args[1])
            elif n == 'PlayerHasGold':
                ok = self.gold >= int(a)
            elif n in ('NPCisFollower', 'PlayerHasItem', 'PlayerHasGuild', 'PlayerIsClass'):
                ok = False
            else:
                ok = True
            if not ok:
                return False
        return True

    def act(self, a):
        for x in [x for x in a.split(';') if x]:
            n, _, s = x.partition('#')
            args = s.split(',')
            n = n[0].upper() + n[1:]
            if n == 'SetVariable':
                self.V[args[0]] = int(args[1])
            elif n == 'IncVariable':
                self.V[args[0]] += int(args[1])
            elif n == 'LoseItems':
                self.inv[int(args[0])] -= int(args[1])
            elif n == 'GainItem':
                self.inv[int(s)] += 1
            elif n == 'GainGold':
                self.gold += int(s)
            elif n == 'LoseGold':
                self.gold -= int(s)

    def Q(self, rows, i):
        for r in rows:
            if r['index'] == str(i) and r['type'] == 'Q' and self.cond(r['conditions']):
                self.act(r['actions'])
                return r

    def A(self, rows, i):
        return [r for r in rows if r['index'] == str(i) and r['type'] == 'A' and self.cond(r['conditions'])][:4]

    def talk(self, f, pick):
        rows = load(f)
        q = self.Q(rows, 1)
        seen = [q['text']]
        for _ in range(40):
            ans = self.A(rows, q['Go To'])
            if not ans:
                return seen
            a = pick(ans)
            if a is None:
                return seen
            self.act(a['actions'])
            if a['Go To'] in ('0', ''):
                return seen
            q = self.Q(rows, a['Go To'])
            assert q, (f, a['Go To'])
            seen.append(q['text'])
        raise AssertionError('conversation loop in ' + f)

    def menu(self, *path):
        f, m = LEADER
        want = [m] + list(path)

        def pick(ans):
            if not want:
                return None
            for r in ans:
                if want and want[0] in r['text']:
                    want.pop(0)
                    return r
            raise AssertionError('menu option %s not in %s' % (want[:1], [r['text'][:30] for r in ans]))
        return self.talk(f, pick)


def scene_file(st):
    for p in glob.glob(os.path.join(CONV, '*.txt')):
        f = os.path.basename(p)[:-4]
        for r in load(f):
            if r['index'] == '1' and r['type'] == 'Q' and 'VariableEqual#%s,%d' % (SV, st) in r['conditions'].split(';'):
                return f, r
    return None, None


# find every state's speaker once (file scans are slow)
SPEAKER = {}
for st in range(1, 100):
    f, r = scene_file(st)
    if f:
        SPEAKER[st] = f
fails = 0
for run in range(RUNS):
    rng = random.Random(run)
    G = Game(rng)
    policy = ['first', 'last', 'random'][run % 3]

    def pick(ans):
        if policy == 'first':
            return ans[0]
        if policy == 'last':
            return ans[-1]
        return rng.choice(ans)
    trail = []
    try:
        for guard in range(200):
            st = G.V[SV]
            if st == 100:
                break
            if st in SPEAKER:
                f = SPEAKER[st]
                for r in load(f):
                    if r['index'] == '1' and r['type'] == 'Q' and 'VariableEqual#%s,%d' % (SV, st) in r['conditions'].split(';'):
                        for x in r['conditions'].split(';'):
                            n, _, a = x.partition('#')
                            if n == 'NPCIsDead':
                                assert a in UNIQUE, 'NPCIsDead#%s: no such unique_tag on any map' % a
                                G.dead.add(a)
                            if n == 'PlayerHasItems':
                                i, c = a.split(',')
                                G.inv[int(i)] = max(G.inv[int(i)], int(c))
                G.talk(f, pick)
                trail.append((st, f, G.V[SV]))
                if G.V[SV] == st:        # 'hold' or a branch that needs another visit
                    G.talk(f, lambda ans: ans[0])
                    trail.append((st, f, G.V[SV]))
                continue
            # a chapter boundary: fill contracts, then take the next chapter from the leader
            for _ in range(20):
                t = G.menu('advancement')
                if any(x.startswith('[BLUE](Chapter') for x in t):
                    G.menu('advancement', "I'll do it")
                    break
                G.inv[CONTRACT[0]] += CONTRACT[1]
                G.menu('contracts', 'have')
            else:
                raise AssertionError('no chapter offered at state %d' % st)
        assert G.V[SV] == 100, 'stuck at state %d' % G.V[SV]
        assert G.V['ekg_rank_' + g] == 5 and G.V['ekg_gm'] > 0, 'no Guild Master seat'
    except AssertionError as e:
        fails += 1
        print('RUN %d (%s) FAILED: %s\n  trail: %s' % (run, policy, e, trail[-6:]))
choices = sorted({k for k in G.V if k.startswith('ekg_c_')})
print('%s: %d/%d runs reached Guild Master; %d story states with speakers; choice vars: %s'
      % (g, RUNS - fails, RUNS, len(SPEAKER), ', '.join(choices)))
sys.exit(1 if fails else 0)
