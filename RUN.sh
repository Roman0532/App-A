#!/bin/bash
source ./CONFIG.sh
export login="sa"
# Запуск проекта
java -cp "$CP" Main $@
