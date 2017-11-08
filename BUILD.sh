#!/bin/bash
source ./CONFIG.sh

rm -rf $OUT
mkdir -p $OUT

find . -name "*.java" | xargs javac -sourcepath "SRC" -classpath "$CP" -d "$OUT" 

jar -cfe $JAR Main -C $OUT .
