import json,collections,re
rows=json.load(open('map_out/full_classified_map.json'))
def is_support(o): return o['obf'].startswith(('android.','androidx.'))
# reclassify android.support out of GAME-CODE
for r in rows:
    if is_support(r) and r['category']=='GAME-CODE': r['category']='androidx'
game=[r for r in rows if r['category']=='GAME-CODE' and not is_support(r)]
# dedupe by real name (multiple obf fragments -> same class w/ inner classes)
byreal=collections.defaultdict(list)
for r in game: byreal[r['real']].append(r['obf'])
print("Distinct recoverable game classes:",len(byreal))
cat=collections.Counter(r['category'] for r in rows)
print("Final categories:",dict(cat.most_common()))
# write clean deliverables
libgdx={r['obf']:r['real'] for r in rows if r['category'].startswith('libgdx')}
json.dump({r['obf']:r['real'] for r in rows}, open('map_out/deobf_map_full.json','w'),indent=1,sort_keys=True)
json.dump({k:sorted(set(v)) for k,v in sorted(byreal.items())}, open('map_out/recoverable_game_classes.json','w'),indent=1)
print("wrote deobf_map_full.json (%d) + recoverable_game_classes.json"%len(rows))
