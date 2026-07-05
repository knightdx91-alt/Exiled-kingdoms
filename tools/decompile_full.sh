#!/usr/bin/env bash
# Decompiles the ENTIRE APK to a full source tree — all classes*.dex, all packages,
# including the R8-obfuscated engine/render/UI tier (single-letter packages k0/ m0/
# n0/ …). Unlike tools/extract.sh (which keeps only net.fdgames), this is what lets us
# open any obfuscated class and read its ACTUAL member-level logic when porting a
# feature — look the class up in deobf/CLASS_MAP.tsv, then read its file here.
#
# Requires: java, unzip, maven (jadx is resolved from Maven Central, then cached).
# Usage: ./tools/decompile_full.sh <ExiledKingdoms.apk> [out-dir]
#   out-dir defaults to ./decompiled  (tracked in-repo; re-run only to refresh)
set -euo pipefail

APK="${1:?usage: decompile_full.sh <apk> [out-dir]}"
OUT="${2:-$(cd "$(dirname "$0")/.." && pwd)/decompiled}"
WORK="$(mktemp -d)"
JADX_VER=1.5.5

echo ">> Extracting all dex from $APK"
mkdir -p "$OUT"
unzip -oq "$APK" 'classes*.dex' -d "$WORK"

echo ">> Resolving jadx (jadx-core + jadx-dex-input) from Maven Central"
mkdir -p "$WORK/jadx" && cd "$WORK/jadx"
cat > pom.xml <<POM
<project xmlns="http://maven.apache.org/POM/4.0.0">
  <modelVersion>4.0.0</modelVersion>
  <groupId>local</groupId><artifactId>jadxfull</artifactId><version>1</version><packaging>pom</packaging>
  <dependencies>
    <dependency><groupId>io.github.skylot</groupId><artifactId>jadx-core</artifactId><version>${JADX_VER}</version></dependency>
    <dependency><groupId>io.github.skylot</groupId><artifactId>jadx-dex-input</artifactId><version>${JADX_VER}</version></dependency>
  </dependencies>
</project>
POM
if ! mvn -q org.apache.maven.plugins:maven-dependency-plugin:3.6.1:build-classpath -Dmdep.outputFile=cp.txt 2>/dev/null; then
  # offline/proxy hiccup: fall back to whatever is already cached in ~/.m2
  echo ">> Maven resolve failed; building classpath from ~/.m2 cache"
  find "$HOME/.m2" -name '*.jar' | tr '\n' ':' > cp.txt
fi
CP="$(cat cp.txt):."

cat > Run.java <<'JAVA'
import jadx.api.*; import java.io.File;
public class Run {
  public static void main(String[] a) throws Exception {
    JadxArgs args = new JadxArgs();
    for (int i = 1; i < a.length; i++) args.getInputFiles().add(new File(a[i]));
    args.setOutDir(new File(a[0]));
    args.setShowInconsistentCode(true);                 // keep partially-decompiled bodies
    args.setThreadsCount(Runtime.getRuntime().availableProcessors());
    try (JadxDecompiler j = new JadxDecompiler(args)) { j.load(); j.save();
      System.out.println("Classes: " + j.getClasses().size()); }
  }
}
JAVA
javac -cp "$CP" Run.java

echo ">> Decompiling ALL dex -> $OUT (this is the full obfuscated tree)"
java -Xmx3g -cp "$CP" Run "$OUT" "$WORK"/classes*.dex

echo ">> Done. Read any class by its deobf/CLASS_MAP.tsv path, e.g.:"
echo "     less $OUT/sources/n0/k.java     # = ConversationWindow"
echo "   Total java files: $(find "$OUT/sources" -name '*.java' | wc -l)"
rm -rf "$WORK"
