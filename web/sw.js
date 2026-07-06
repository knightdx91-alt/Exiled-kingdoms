// Service worker: offline shell + full-game cache (uncompressed, no shrinking).
//
// - install: cache the app shell so the game boots offline immediately.
// - message {type:'CACHE_ALL'}: fetch asset-manifest.json and cache EVERY asset
//   (the whole ~150 MB game) into a persistent cache, reporting progress. Nothing
//   is downscaled or re-encoded — assets are stored exactly as served.
// - fetch: cache-first, so once cached the game runs with no network at all.
//   Bookmark the page (or install it) and it opens offline.

const VERSION = 'ek-v10';
const SHELL = `${VERSION}-shell`;
const FULL  = `${VERSION}-full`;
// App code (HTML/JS/CSS/manifest) is served network-first so new deploys show up
// immediately when online; the big immutable game assets stay cache-first.
const SHELL_RE = /\.(?:html|js|css|webmanifest)$/;

const SHELL_URLS = [
  './', './index.html', './manifest.webmanifest', './icon.svg',
  './vendor/phaser.min.js', './src/main.js', './src/style.css', './src/saves.js',
];

self.addEventListener('install', (e) => {
  e.waitUntil(caches.open(SHELL).then(c => c.addAll(SHELL_URLS)).then(() => self.skipWaiting()));
});

self.addEventListener('activate', (e) => {
  e.waitUntil((async () => {
    // drop caches from older versions, keep current shell+full
    const keep = new Set([SHELL, FULL]);
    for (const k of await caches.keys()) if (!keep.has(k)) await caches.delete(k);
    await self.clients.claim();
  })());
});

self.addEventListener('fetch', (e) => {
  if (e.request.method !== 'GET') return;
  const url = new URL(e.request.url);
  const sameOrigin = url.origin === self.location.origin;
  const isShell = e.request.mode === 'navigate' || (sameOrigin && SHELL_RE.test(url.pathname));

  if (isShell) {
    // Network-first: always get the freshest app code online; fall back to cache offline.
    e.respondWith((async () => {
      try {
        const res = await fetch(e.request, { cache: 'no-store' });
        if (res.ok && sameOrigin) { const c = await caches.open(SHELL); c.put(e.request, res.clone()); }
        return res;
      } catch (err) {
        const hit = await caches.match(e.request, { ignoreSearch: true });
        return hit || caches.match('./index.html');
      }
    })());
    return;
  }

  // Cache-first for the big, immutable game assets (tmx/png/mp3/…).
  e.respondWith((async () => {
    const hit = await caches.match(e.request, { ignoreSearch: true });
    if (hit) return hit;
    try {
      const res = await fetch(e.request);
      if (res.ok && sameOrigin) { const c = await caches.open(FULL); c.put(e.request, res.clone()); }
      return res;
    } catch (err) {
      return caches.match('./index.html');   // offline fallback for navigations
    }
  })());
});

// Cache the entire game on request, streaming progress back to the page.
self.addEventListener('message', (e) => {
  if (!e.data || e.data.type !== 'CACHE_ALL') return;
  e.waitUntil((async () => {
    const client = e.source;
    let list = [];
    try {
      list = await (await fetch('./asset-manifest.json', { cache: 'no-store' })).json();
    } catch (_) { /* no manifest yet -> shell-only offline */ }
    const cache = await caches.open(FULL);
    let done = 0, failed = 0;
    const BATCH = 8;
    for (let i = 0; i < list.length; i += BATCH) {
      await Promise.all(list.slice(i, i + BATCH).map(async (url) => {
        try {
          if (!(await cache.match(url))) {
            const r = await fetch(url, { cache: 'no-store' });
            if (r.ok) await cache.put(url, r); else failed++;
          }
        } catch (_) { failed++; }
        done++;
        client && client.postMessage({ type: 'CACHE_PROGRESS', done, total: list.length, failed });
      }));
    }
    client && client.postMessage({ type: 'CACHE_DONE', total: list.length, failed });
  })());
});
