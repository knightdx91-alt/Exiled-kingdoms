#!/usr/bin/env bash
# Type-aware method-call de-obfuscation via JavaParser + JavaSymbolSolver.
# Resolves each obfuscated method call's receiver type against stock libGDX and
# renames per deobf/method_lookup.tsv. Usage: run.sh <src-root> <gdx.jar>
set -euo pipefail
D="$(cd "$(dirname "$0")" && pwd)"; ROOT="$D/../.."
CACHE="$ROOT/.cache"; mkdir -p "$CACHE"
POM="$CACHE/jp-cp.txt"
if [ ! -f "$POM" ]; then
  tmp=$(mktemp -d); cat > "$tmp/pom.xml" <<POM
<project xmlns="http://maven.apache.org/POM/4.0.0"><modelVersion>4.0.0</modelVersion>
<groupId>l</groupId><artifactId>jp</artifactId><version>1</version><packaging>pom</packaging>
<dependencies><dependency><groupId>com.github.javaparser</groupId>
<artifactId>javaparser-symbol-solver-core</artifactId><version>3.26.2</version></dependency></dependencies></project>
POM
  (cd "$tmp" && mvn -q org.apache.maven.plugins:maven-dependency-plugin:3.6.1:build-classpath -Dmdep.outputFile="$POM")
fi
CP="$(cat "$POM")"
javac -cp "$CP" -d "$CACHE" "$D/Remap.java"
java -cp "$CP:$CACHE" Remap "$1" "$2" "$ROOT/deobf/method_lookup.tsv"
