#!/bin/bash
LIB="lib/*"
SRC="src/"
OUT="out/classes/"

 if [ "$(expr substr $(uname -s) 1 10)" == "MINGW64_NT" ]; then
     CP="$LIB"
 fi

java -cp ""$OUT";"$CP"" Main $@






