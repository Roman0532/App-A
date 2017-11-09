#!/bin/bash
# Счетсчик тестов которые не прошли
errors=0
# Счетсчик тестов которые прошли
passed=0

check () {
 ./RUN.sh $1
  result=$?
# Если полученый код после вызова RUN.sh равен желаемому
# увеличиваем количество пройденых тестов
    if [[  $result -eq $2 ]]; then
        echo "'$2'" $result
        ((passed++))
# Иначе увеличиваем количество не пройденых
    else
        echo "'$2'" $result
        ((errors++))
   fi
}

./BUILD.sh

check "" 1
check "-h" 0

check "-login Roman -password 123" 0
check "-login Roman -password 321" 2
check "-login Roman123 -password 123" 1

check "-login Roman -password 123 -role READ -resource a.b" 0
check "-login Roman -password 123 -role READ -resource a.b.c" 0
check "-login Roman -password 123 -role rwe -resource a.b" 3
check "-login Roman1 -password 000 -role WRITE -resource a.b" 4
check "-login Roman -password 123 -role READ -resource a.b" 0
check "-login Roman2 -password 0000 -role EXECUTE -resource a.b" 0
check "-login Roman1 -password 000 -role WRITE -resource CCC" 4
check "-login Roman1 -password 000 -role WRITE -resource ab.c" 4
check "-login Roman1 -password 000 -role WRITE -resource a.QQ" 4

check "-login Roman -password 123 -role READ -resource a.b -dateStart 2017-10-08 -dateEnd 2017-10-08 -volume 100" 0
check "-login Roman -password 123 -role READ -resource a.b -dateStart 2017-10-08 -dateEnd 2017-10-08 -volume 100" 0
check "-login Roman -password 123 -role READ -resource a.b -dateStart 1111111111 -dateEnd 2017-10-08 -volume 100" 5
check "-login Roman -password 123 -role READ -resource a.b -dateStart 2017-10-08 -dateEnd 2017-10-08 -volume str100" 5
check "-login Roman -password 123 -role WRITE -resource a.b -dateStart 1111111111 -dateEnd 2222222222 -volume 100" 4
check "-login Vasya -password qwerty -role READ -resource A.B -dateStart 1111111111 -dateEnd 2222222222 -volume str100" 1

check "-login XXX -password XXX" 0
check "-login jdoe -password XXX" 2
check "-login jdoe -password sup3rpaZZ" 0
check "-login xxx -password yyy" 0
check "-login jdoe -password sup3rpaZZ" 0

check "-login jdoe -password sup3rpaZZ -role READ -resource a" 0
check "-login jdoe -password sup3rpaZZ -role READ -resource a.b" 0
check "-login jdoe -password sup3rpaZZ -role XXX -resource a.b" 3
check "-login jdoe -password sup3rpaZZ -role READ -resource XXX" 4
check "-login jdoe -password sup3rpaZZ -role WRITE -resource a" 4
check "-login jdoe -password sup3rpaZZ -role WRITE -resource a.bc" 4

check "-login jdoe -password sup3rpaZZ -role READ -resource a.b -dateStart 2015-01-01 -dateEnd 2015-12-31 -volume 100" 0
check "-login jdoe -password sup3rpaZZ -role READ -resource a.b -dateStart 01-01-2015 -dateEnd 2015-12-31 -volume 100" 5
check "-login jdoe -password sup3rpaZZ -role READ -resource a.b -dateStart 2015-01-01 -dateEnd 2015-12-31 -volume XXX" 5
check "-login X -password X -role READ -resource X -dateStart 2015-01-01 -dateEnd 2015-12-31 -volume XXX" 1
check "-login X -password X -role READ -resource X" 1

echo $errors test not passed
echo $passed test passed

#  количество не пройденых тестов больше 0 возвращать код 1
if [[ $errors -gt 0 ]]; then
	exit 1
fi
