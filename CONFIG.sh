#!/bin/bash
# Папка где хранятся библиотеки
LIB="lib/*"
# Папка с ресурсами
RES="src/resources/*"
# Папка с исходниками
SRC="src"
# Папка в которой лежат откомпилированные классы
OUT="out/classes"

# Имя Jar файла
JAR="out/classes/Main.jar"

if [ "$(expr substr $(uname -s) 1 10)" == "MINGW64_NT" ]; then
     CP="$LIB;$JAR"
elif [ "$(expr substr $(uname -s) 1 10)" == "Linux" ]; then
     CP="$LIB:$JAR"
fi
