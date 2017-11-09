#!/bin/bash
source ./CONFIG.sh

# Запуск проекта
java -cp "$CP" Main $@
