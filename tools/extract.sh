#!/usr/bin/env bash
# Reproduces the reverse-engineering of Exiled Kingdoms.apk performed to recover
# the lost source. Requires: java, unzip, maven (for jadx resolution), curl.
#
# Usage: ./tools/extract.sh path/to/ExiledKingdoms.apk
set -euo pipefail

APK="${1:?usage: extract.sh <apk>}"
OUT="$(cd "$(dirname "$0")/.." && pwd)/recovered"
WORK="$(mktemp -d)"
JADX_VER=1.5.5

echo ">> Extracting assets + native libs + manifest"
mkdir -p "$OUT/assets" "$OUT/native"
unzip -oq "$APK" 'assets/*'   -d "$OUT/assets"   || true
unzip -oq "$APK" 'lib/*'      -d "$OUT/native"   || true
unzip -oq "$APK" 'classes*.dex' -d "$WORK"

echo ">> Resolving jadx (jadx-core + jadx-dex-input) from Maven Central"
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
    args.getInputFiles().add(new File(a[0]));
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

echo ">> Decompiling classes.dex"
java -Xmx3g -cp "$CP" Run "$WORK"/classes.dex "$WORK/decompiled"

echo ">> Copying game source (net.fdgames) into recovered/src"
mkdir -p "$OUT/src"
cp -r "$WORK/decompiled/sources/net" "$OUT/src/"

echo ">> Done. Recovered game source: $(find "$OUT/src" -name '*.java' | wc -l) files"
rm -rf "$WORK"
