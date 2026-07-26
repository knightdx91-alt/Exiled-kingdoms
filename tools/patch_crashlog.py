#!/usr/bin/env python3
"""
Crash logger for the 4.2.2 mod builds. Installs a default UncaughtExceptionHandler
(first line of MainActivity.onCreate) that writes the full stack trace to
/sdcard/EK_crash.txt before delegating to the system handler.

Why: the device has no adb/logcat access for us. The Hero build crashes between
the loading screen and the main menu and every static check (register/type
verification of all patched methods, D8 over the whole patched dex, symbol
existence for every referenced method/field) passes -- the real trace is the
only way left to pin it. With this in every build, any future crash report
comes with /sdcard/EK_crash.txt.

Run from inside the disassembled tree (like the other patchers).
"""
w = "."
CL = 'Lnet/fdgames/ek/android/EkCrashLog;'
UEH = 'Ljava/lang/Thread$UncaughtExceptionHandler;'

# 1) the handler class
open(f'{w}/smali/net/fdgames/ek/android/EkCrashLog.smali', 'w', encoding='utf-8').write(f'''\
.class public {CL}
.super Ljava/lang/Object;
.source "EkCrashLog.java"

# interfaces
.implements {UEH}


# instance fields
.field private final a:{UEH}


# direct methods
.method public constructor <init>({UEH})V
    .locals 0

    invoke-direct {{p0}}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, {CL}->a:{UEH}

    return-void
.end method

.method public static a()V
    .locals 2

    invoke-static {{}}, Ljava/lang/Thread;->getDefaultUncaughtExceptionHandler(){UEH}

    move-result-object v0

    instance-of v1, v0, {CL}

    if-eqz v1, :ekcl_install

    return-void

    :ekcl_install
    new-instance v1, {CL}

    invoke-direct {{v1, v0}}, {CL}-><init>({UEH})V

    invoke-static {{v1}}, Ljava/lang/Thread;->setDefaultUncaughtExceptionHandler({UEH})V

    return-void
.end method


# virtual methods
.method public uncaughtException(Ljava/lang/Thread;Ljava/lang/Throwable;)V
    .locals 2

    :try_start_0
    new-instance v0, Ljava/io/FileWriter;

    const-string v1, "/sdcard/EK_crash.txt"

    invoke-direct {{v0, v1}}, Ljava/io/FileWriter;-><init>(Ljava/lang/String;)V

    invoke-static {{p2}}, Landroid/util/Log;->getStackTraceString(Ljava/lang/Throwable;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {{v0, v1}}, Ljava/io/FileWriter;->write(Ljava/lang/String;)V

    invoke-virtual {{v0}}, Ljava/io/FileWriter;->close()V
    :try_end_0
    .catch Ljava/lang/Exception; {{:try_start_0 .. :try_end_0}} :catch_0

    :catch_0
    iget-object v0, p0, {CL}->a:{UEH}

    if-eqz v0, :ekcl_done

    invoke-interface {{v0, p1, p2}}, {UEH}->uncaughtException(Ljava/lang/Thread;Ljava/lang/Throwable;)V

    :ekcl_done
    return-void
.end method
''')
print("added net/fdgames/ek/android/EkCrashLog")

# 2) install it first thing in MainActivity.onCreate
p = f'{w}/smali/net/fdgames/ek/android/MainActivity.smali'
s = open(p, encoding='utf-8').read()
anchor = '.method public onCreate(Landroid/os/Bundle;)V\n    .locals 7\n'
assert s.count(anchor) == 1, "MainActivity.onCreate anchor not found"
s = s.replace(anchor, anchor + f'\n    invoke-static {{}}, {CL}->a()V\n', 1)
open(p, 'w', encoding='utf-8').write(s)
print("patched MainActivity.onCreate: crash logger installed")
print("DONE")
