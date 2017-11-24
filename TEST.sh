#!/bin/bash
# Счетчик тестов которые не прошли
errors=0
# Счетчик тестов которые прошли 
passed=0
# Общий счетчик количества тестов
count=0
check() {
 ./RUN.sh $1
  result=$?
  ((count++))
# Если полученый код после вызова RUN.sh равен желаемому
# увеличиваем количество пройденых тестов
    if [[ $result -eq $2 ]]; then
       ((passed++))
       echo $count  PASSED $1 "'$2'" $result
# Иначе увеличиваем количество не пройденых
    else
       ((errors++))
       echo $count FAILED $1  "'$2'" $result
    fi
}

./BUILD.sh



# Последний тест , переопределяем переменную окружения
export LOGIN="xxx"
export PASSWORD="yyy"
check "" 255
echo
echo $errors test not passed
echo $passed test passed
#  количество не пройденых тестов больше 0 возвращать код 1
if [[ $errors -gt 0 ]]; then
    exit 1
fi
