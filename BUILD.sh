#!/bin/bash
LIB="lib/*"
SRC="src"
OUT="out/classes"
JAR="out/classes/Main.jar"
if [ "$(expr substr $(uname -s) 1 10)" == "MINGW64_NT" ]; then
    CP="$LIB;$JAR"
elif [ "$(expr substr $(uname -s) 1 10)" == "Linux" ]; then
    CP="$LIB:$JAR"
fi

rm -rf $OUT
mkdir -p $OUT

find . -name "*.java" | xargs javac -sourcepath "SRC" -classpath "$CP" -d "$OUT"

jar -cfe $JAR Main -C $OUT .
=======

source ./CONFIG.sh

rm -rf $OUT
mkdir -p $OUT

# Компиляция проекта
find . -name "*.java" | xargs javac -sourcepath "SRC" -classpath "$CP" -d "$OUT" 

# Создание jar файла
jar -cfe $JAR Main -C $OUT .

