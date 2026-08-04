#!/usr/bin/env python3
"""
Fix the character-Details crash on devices without a usable secure device id
(owner's EK_crash.txt, 2026-08-05, *second* crash after the InventorySlotImage fix):

    java.lang.RuntimeException: Actor: u: Details
    Caused by: java.lang.NullPointerException
      at net.fdgames.ek.android.MainActivity.f
      at net.fdgames.ek.SettingsData.a  ->  Settings.e  ->  Analytics.a
      at net.fdgames.ek.GPGSUpdate.a
      at e.a.d.e.h0.a          (StatsDetailWindow)
      at e.a.d.e.e.touchDown   (CharacterWindow Details button)

Opening the stats-detail window fires an analytics / Play-Games update that calls
`MainActivity.f()`. `f()` reads `Settings.Secure.getString(resolver, "android_ld")` and
immediately calls `.length()` on it. On the owner's 4.2.2 device that secure row is
**null**, so `.length()` NPEs. The method already computes a fallback string ("99999")
for the case where the id's tail isn't hex -- so the fix is to send a null id down that
same fallback instead of dereferencing it.

Run from inside the decoded tree (see tools/build_mod_4_2_2.sh).
"""
w = "."
p = f'{w}/smali/net/fdgames/ek/android/MainActivity.smali'
s = open(p, encoding='utf-8').read()

# 1) null-check the secure string right after getString(), before .length().
get = ('    invoke-static {v0, v1}, Landroid/provider/Settings$Secure;->getString'
       '(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;\n\n'
       '    move-result-object v0\n\n'
       '    invoke-virtual {v0}, Ljava/lang/String;->length()I\n')
assert s.count(get) == 1, "MainActivity.f() getString/length sequence not found"
s = s.replace(get,
              ('    invoke-static {v0, v1}, Landroid/provider/Settings$Secure;->getString'
               '(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;\n\n'
               '    move-result-object v0\n\n'
               '    if-eqz v0, :ekf_fallback\n\n'
               '    invoke-virtual {v0}, Ljava/lang/String;->length()I\n'), 1)

# 2) label the existing "99999" fallback assignment so the null path lands on it.
fb = '    const-string v0, "99999"\n'
assert s.count(fb) == 1, "MainActivity.f() fallback constant not found"
s = s.replace(fb, '    :ekf_fallback\n' + fb, 1)

open(p, 'w', encoding='utf-8').write(s)
print('patched MainActivity.f(): null secure device-id -> "99999" fallback, not an NPE')
print("DONE")
