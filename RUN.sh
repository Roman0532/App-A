#!/bin/bash
LIB="lib/*"
SRC="src/"
OUT="out/classes/"
JAR="out/classes/Main.jar"

if [ "$(expr substr $(uname -s) 1 10)" == "MINGW64_NT" ]; then
    CP="$LIB;$JAR"
elif [ "$(expr substr $(uname -s) 1 10)" == "Linux" ]; then
    CP="$LIB:$JAR"
fi

java -cp "$CP" Main $@