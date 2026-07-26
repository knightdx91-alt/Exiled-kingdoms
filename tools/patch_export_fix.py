#!/usr/bin/env python3
"""
Fix "Export & Cloud Save -> export to file" doing nothing on Android 4.2.2.

Root cause (reversed, not guessed):
  Serializer.a(Z)V writes the backup with
      new FileOutputStream(Gdx.files.getExternalStoragePath() + "EK.bak")
  and `AndroidFiles.getExternalStoragePath()` returns the field `externalFilesPath`,
  which the AndroidFiles constructor sets from `getExternalFilesDir(null)` ->
  /sdcard/Android/data/net.fdgames.ek.android/files/.
  The whole export body is wrapped in `catch (Exception) { printStackTrace(); }`, so
  ANY failure is completely silent -- which is exactly the "nothing happens" symptom.

  On Android 4.2.2 that app-external directory is unreliable: `getExternalFilesDir()`
  returns null when the volume is not mounted at construction time (AndroidFiles then
  stores null, and the path becomes the literal "nullEK.bak"), and the directory is not
  guaranteed to exist -- `new FileOutputStream(path)` does NOT create parent dirs, so it
  throws FileNotFoundException/ENOENT straight into the silent catch. Simply adding
  WRITE_EXTERNAL_STORAGE (done in the previous build) does not help with either case.

Fix (2 smali edits):
  1. `AndroidFiles.getExternalStoragePath()` returns
     `Environment.getExternalStorageDirectory().getAbsolutePath() + "/"` (i.e. /sdcard/),
     falling back to the original field if that is unavailable. /sdcard always exists, so
     no mkdirs is needed, and WRITE_EXTERNAL_STORAGE (already in the manifest) covers it.
     This also restores the behaviour the game's own help text describes for this era:
     "Saves will be exported to a file called EK.bak, on the root of your phone storage"
     (BACKUP_INFO_FILE_TEXT), and it makes the import fallbacks the engine already probes
     -- "download/EK.bak", "Download/EK.bak", "sdcard/Download/EK.bak" -- actually
     resolve to real folders. Export and import stay symmetric because both go through
     this same root (FileHandle.file() resolves FileType.External via it too).
     Scope is safe: the only users of this root are Serializer (backup/restore) and the
     two backup-window classes that display the path.
  2. The silent `catch` in Serializer.a(Z)V now also pushes the exception text to
     GameConsole, so any future failure is diagnosable instead of invisible.

Run from a work dir containing `smali/` (see tools/build_mod_4_2_2.sh).
"""
w = "."

# ---------------------------------------------------------------------------
# 1) AndroidFiles.getExternalStoragePath() -> real external storage root
# ---------------------------------------------------------------------------
p = f'{w}/smali/com/badlogic/gdx/backends/android/AndroidFiles.smali'
s = open(p, encoding='utf-8').read()

old = """.method public getExternalStoragePath()Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Lcom/badlogic/gdx/backends/android/AndroidFiles;->externalFilesPath:Ljava/lang/String;

    return-object v0
.end method"""
assert s.count(old) == 1, "getExternalStoragePath() body not found/unique"

new = """.method public getExternalStoragePath()Ljava/lang/String;
    .locals 3

    invoke-static {}, Landroid/os/Environment;->getExternalStorageDirectory()Ljava/io/File;

    move-result-object v0

    if-eqz v0, :ekexport_fallback

    invoke-virtual {v0}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v0

    if-eqz v0, :ekexport_fallback

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v2, "/"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    return-object v0

    :ekexport_fallback
    iget-object v0, p0, Lcom/badlogic/gdx/backends/android/AndroidFiles;->externalFilesPath:Ljava/lang/String;

    return-object v0
.end method"""
s = s.replace(old, new, 1)
open(p, 'w', encoding='utf-8').write(s)
print("patched AndroidFiles.getExternalStoragePath() -> external storage root (/sdcard/)")

# ---------------------------------------------------------------------------
# 2) Write the backup into Download/ (v2, 2026-07-26).
#    /sdcard-root writes never landed anywhere the owner could see (the crash
#    logger's /sdcard file suffered the same fate), so target the Downloads
#    folder instead -- which is also what the IMPORT side already probes
#    ("Download/EK.bak" etc., relative to the external root, verified in the
#    dex). Only the write side needs changing: the two filename constants gain
#    a "Download/" prefix, and since FileOutputStream does not create parent
#    dirs, mkdirs() runs on the parent first.
# ---------------------------------------------------------------------------
p = f'{w}/smali/net/fdgames/Helpers/Serializer.smali'
s = open(p, encoding='utf-8').read()

ms = s.index('.method public static a(Z)V')
me = s.index('.end method', ms)
method = s[ms:me]

for name in ('EK_GPGS.bak', 'EK.bak'):
    old = f'    const-string p0, "{name}"\n'
    assert method.count(old) == 1, f"filename constant {name} not found/unique"
    method = method.replace(old, f'    const-string p0, "Download/{name}"\n', 1)

# full path is in p0 right before the FileOutputStream ctor; v1 is dead here
mk_anchor = '    invoke-direct {v0, p0}, Ljava/io/FileOutputStream;-><init>(Ljava/lang/String;)V\n'
assert method.count(mk_anchor) == 1, "FileOutputStream ctor anchor not found/unique"
mkdirs = ('    new-instance v1, Ljava/io/File;\n\n'
          '    invoke-direct {v1, p0}, Ljava/io/File;-><init>(Ljava/lang/String;)V\n\n'
          '    invoke-virtual {v1}, Ljava/io/File;->getParentFile()Ljava/io/File;\n\n'
          '    move-result-object v1\n\n'
          '    if-eqz v1, :ekexp_nomk\n\n'
          '    invoke-virtual {v1}, Ljava/io/File;->mkdirs()Z\n\n'
          '    :ekexp_nomk\n')
method = method.replace(mk_anchor, mkdirs + mk_anchor, 1)

old_catch = """    move-exception p0

    invoke-virtual {p0}, Ljava/lang/Exception;->printStackTrace()V
"""
assert method.count(old_catch) == 1, "export catch block not found/unique"

new_catch = """    move-exception p0

    invoke-virtual {p0}, Ljava/lang/Exception;->printStackTrace()V

    invoke-virtual {p0}, Ljava/lang/Throwable;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-static {p0}, Lnet/fdgames/Helpers/GameConsole;->a(Ljava/lang/String;)V
"""
method = method.replace(old_catch, new_catch, 1)
s = s[:ms] + method + s[me:]
open(p, 'w', encoding='utf-8').write(s)
print("patched Serializer.a(Z)V: export -> Download/EK.bak (+mkdirs), errors on GameConsole")
print("DONE")
