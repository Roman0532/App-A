#!/bin/bash
LIB="lib/*"
SRC="src/"
OUT="out/classes/"

sudoe() {
[[ "$#" -ne 2 ]] && echo "Usage: sudoe <text> <file>" && return 1
echo "$1" | sudo tee --append "$2" > /dev/null
}

if [ "$(expr substr $(uname -s) 1 10)" == "MINGW64_NT" ]; then
    CP="$LIB"
fi

java -cp ""$OUT";"$CP"" Main $@






