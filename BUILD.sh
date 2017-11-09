#!/bin/bash

source ./CONFIG.sh

rm -rf $OUT
mkdir -p $OUT

# Компиляция проекта
find . -name "*.java" | xargs javac -sourcepath "SRC" -classpath "$CP" -d "$OUT" 

# Создание jar файла
jar -cfe $JAR Main -C $OUT .

