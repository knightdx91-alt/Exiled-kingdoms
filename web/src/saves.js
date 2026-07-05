// Browser-local save games via IndexedDB. No server, no account — saves live on
// the player's device. Includes export/import so a save can be backed up or moved
// between devices (browser storage can be cleared, so this is the safety net).

const DB = 'exiled-kingdoms', STORE = 'saves', V = 1;

function open() {
  return new Promise((res, rej) => {
    const r = indexedDB.open(DB, V);
    r.onupgradeneeded = () => {
      const db = r.result;
      if (!db.objectStoreNames.contains(STORE))
        db.createObjectStore(STORE, { keyPath: 'slot' });
    };
    r.onsuccess = () => res(r.result);
    r.onerror = () => rej(r.error);
  });
}
function tx(mode, fn) {
  return open().then(db => new Promise((res, rej) => {
    const t = db.transaction(STORE, mode), s = t.objectStore(STORE);
    const out = fn(s);
    t.oncomplete = () => res(out.result !== undefined ? out.result : out);
    t.onerror = () => rej(t.error);
  }));
}

export const Saves = {
  // data is any JSON-serializable game state
  put: (slot, data, meta = {}) =>
    tx('readwrite', s => s.put({ slot, data, meta, savedAt: Date.now() })),
  get: (slot) => tx('readonly', s => s.get(slot)),
  list: () => tx('readonly', s => s.getAll()).then(rows =>
    (rows || []).map(({ slot, meta, savedAt }) => ({ slot, meta, savedAt }))
                .sort((a, b) => b.savedAt - a.savedAt)),
  delete: (slot) => tx('readwrite', s => s.delete(slot)),

  // export ALL saves as a downloadable JSON blob (backup / transfer)
  async export() {
    const rows = await tx('readonly', s => s.getAll());
    return new Blob([JSON.stringify({ app: 'exiled-kingdoms', v: 1, saves: rows || [] })],
                    { type: 'application/json' });
  },
  // import a previously exported blob/object, restoring all slots
  async import(json) {
    const obj = typeof json === 'string' ? JSON.parse(json) : json;
    const rows = (obj && obj.saves) || [];
    await open().then(db => new Promise((res, rej) => {
      const t = db.transaction(STORE, 'readwrite'), s = t.objectStore(STORE);
      rows.forEach(r => s.put(r));
      t.oncomplete = res; t.onerror = () => rej(t.error);
    }));
    return rows.length;
  },
};
