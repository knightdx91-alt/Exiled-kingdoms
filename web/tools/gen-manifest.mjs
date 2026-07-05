// Generates asset-manifest.json: the complete list of URLs the service worker
// precaches for full offline play. Walks the shell files + everything under
// web/assets/ (the recovered game assets, once copied in). Every file is listed
// as-is — nothing is excluded or shrunk, honoring "cache the full game".
//
// Usage: node tools/gen-manifest.mjs
import fs from 'node:fs';
import path from 'node:path';

const ROOT = path.resolve('.');
const INCLUDE_DIRS = ['assets', 'vendor', 'src'];
const SHELL = ['index.html', 'manifest.webmanifest', 'icon.svg'];

function walk(dir, out = []) {
  const abs = path.join(ROOT, dir);
  if (!fs.existsSync(abs)) return out;
  for (const name of fs.readdirSync(abs)) {
    const rel = path.join(dir, name);
    const st = fs.statSync(path.join(ROOT, rel));
    if (st.isDirectory()) walk(rel, out);
    else out.push('./' + rel.split(path.sep).join('/'));
  }
  return out;
}

const urls = ['./'].concat(
  SHELL.map(f => './' + f),
  ...INCLUDE_DIRS.map(d => walk(d))
);
const unique = [...new Set(urls)].sort();
fs.writeFileSync('asset-manifest.json', JSON.stringify(unique, null, 0));

let bytes = 0;
for (const u of unique) {
  const p = path.join(ROOT, u.replace('./', ''));
  try { if (fs.statSync(p).isFile()) bytes += fs.statSync(p).size; } catch {}
}
console.log(`asset-manifest.json: ${unique.length} files, ${(bytes / 1048576).toFixed(1)} MB total`);
