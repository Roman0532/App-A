@echo off
start
javac Main.java
java Main Roman 123
echo "0" %ERRORLEVEL%
java Main Roman 321
echo "2" %ERRORLEVEL%
java Main Roman123 123
echo "1" %ERRORLEVEL%
java Main Roman 123 READ a.b
echo "0" %ERRORLEVEL%
java Main Roman 123 READ a.b.c
echo "0" %ERRORLEVEL%
java Main Roman 123 rwe a.b
echo "3" %ERRORLEVEL%
java Main Roman1 000 WRITE a.b
echo "4" %ERRORLEVEL%
java Main Roman2 0000 EXECUTE a.b
echo "0" %ERRORLEVEL%
java Main Roman1 000 WRITE CCC
echo "4" %ERRORLEVEL%
java Main Roman1 000 WRITE ab.c
echo "4" %ERRORLEVEL%
java Main Roman1 000 WRITE a.QQ
echo "4" %ERRORLEVEL%

java Main Roman 123 READ a.b 2017-10-08 2017-10-08 100
echo "0" %ERRORLEVEL%
java Main Roman 123 READ a.b 1111111111 2017-10-08 100
echo "5" %ERRORLEVEL%
java Main Roman 123 READ a.b 2017-10-08 2017-10-08 str100
echo "5" %ERRORLEVEL%
java Main Roman 123 WRITE a.b 1111111111 2222222222 100
echo "3" %ERRORLEVEL%
java Main Vasya qwerty READ A.B 1111111111 2222222222 str100
echo "1" %ERRORLEVEL%
PAUSE