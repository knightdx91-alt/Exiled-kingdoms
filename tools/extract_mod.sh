#!/usr/bin/env bash
# Wrapper around extract.sh logic that writes to a caller-chosen OUT dir and
# decompiles ALL classes*.dex (mod APKs ship a 2nd dex). Does not touch recovered/.
# Usage: ./tools/extract_mod.sh <apk> <out-dir>
set -euo pipefail

APK="${1:?usage: extract_mod.sh <apk> <out-dir>}"
OUT="${2:?usage: extract_mod.sh <apk> <out-dir>}"
mkdir -p "$OUT"
OUT="$(cd "$OUT" && pwd)"
WORK="$(mktemp -d)"
JADX_VER=1.5.5

echo ">> [$APK] Extracting assets + native libs + manifest"
mkdir -p "$OUT/assets" "$OUT/native"
unzip -oq "$APK" 'assets/*'     -d "$OUT/assets" || true
unzip -oq "$APK" 'lib/*'        -d "$OUT/native" || true
unzip -oq "$APK" 'classes*.dex' -d "$WORK"

echo ">> Resolving jadx from Maven Central"
mkdir -p "$WORK/jadx" && cd "$WORK/jadx"
cat > pom.xml <<POM
<project xmlns="http://maven.apache.org/POM/4.0.0">
  <modelVersion>4.0.0</modelVersion>
  <groupId>local</groupId><artifactId>jadxrun</artifactId><version>1</version><packaging>pom</packaging>
  <repositories><repository><id>google</id><url>https://dl.google.com/android/maven2</url></repository></repositories>
  <dependencies>
    <dependency><groupId>io.github.skylot</groupId><artifactId>jadx-core</artifactId><version>${JADX_VER}</version></dependency>
    <dependency><groupId>io.github.skylot</groupId><artifactId>jadx-dex-input</artifactId><version>${JADX_VER}</version></dependency>
  </dependencies>
</project>
POM
mvn -q org.apache.maven.plugins:maven-dependency-plugin:3.6.1:build-classpath -Dmdep.outputFile=cp.txt

cat > Run.java <<'JAVA'
import jadx.api.*; import java.io.File;
public class Run {
  public static void main(String[] a) throws Exception {
    JadxArgs args = new JadxArgs();
    for (int i = 2; i < a.length; i++) args.getInputFiles().add(new File(a[i]));
    args.setOutDir(new File(a[1]));
    args.setShowInconsistentCode(true);
    args.setThreadsCount(Runtime.getRuntime().availableProcessors());
    try (JadxDecompiler j = new JadxDecompiler(args)) { j.load(); j.save();
      System.out.println("Classes: " + j.getClasses().size()); }
  }
}
JAVA
CP="$(cat cp.txt):."
javac -cp "$CP" Run.java

echo ">> Decompiling all dex ($(ls "$WORK"/classes*.dex | wc -l) files)"
java -Xmx3g -cp "$CP" Run _ "$WORK/decompiled" "$WORK"/classes*.dex

echo ">> Copying game source (net.fdgames) into $OUT/src"
mkdir -p "$OUT/src"
cp -r "$WORK/decompiled/sources/net" "$OUT/src/" 2>/dev/null || \
  cp -r "$WORK/decompiled/sources/"* "$OUT/src/"

echo ">> Done [$APK]: $(find "$OUT/src" -name '*.java' | wc -l) java files, $(find "$OUT/assets" -type f | wc -l) asset files"
rm -rf "$WORK"
