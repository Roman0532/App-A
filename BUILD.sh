#!/bin/bash
LIB="lib/*"
SRC="src"
OUT="out/classes"
JAR="out/classes/Main.jar"

if [ "$(expr substr $(uname -s) 1 10)" == "MINGW64_NT" ]; then
    CP="$LIB"
elif [ "$(expr substr $(uname -s) 1 5)" == "Linux" ]; then
    CP="$LIB"
fi

rm -rf $OUT
mkdir -p $OUT

find . -name "*.java" | xargs javac -sourcepath "SRC" -classpath "$CP" -d "$OUT" 

jar -cfe $JAR Main -C $OUT .
