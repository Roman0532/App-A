#!/bin/bash
LIB="lib/*"
SRC="src"
OUT="out/classes"
JAR="Main.jar"
if [ "$(expr substr $(uname -s) 1 10)" == "MINGW64_NT" ]; then
    CP="$LIB"
fi

rm -rf "$OUT"
mkdir -p $OUT

find . -name "*.java" | xargs javac -sourcepath "SRC" -classpath "$CP" -d "$OUT" 

jar -cfe $JAR Main manifest.txt Main.class