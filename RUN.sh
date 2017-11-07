#!/bin/bash
LIB="lib/*"
SRC="src/"
OUT="out/classes/"

chmod +x RUN.sh

if [ "$(expr substr $(uname -s) 1 10)" == "MINGW64_NT" ]; then
    CP="lib/*;out/classes/Main.jar"
	
	elif [ "$(expr substr $(uname -s) 1 5)" == "Linux" ]; then
	
    CP="lib/*:out/classes/Main.jar"
fi

java -cp "$CP" Main $@
