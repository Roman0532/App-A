@echo off
start
javac -classpath "../lib/commons-cli-1.4.jar" Main.java
java Main -l Roman -p 123
echo "0" %ERRORLEVEL%
java Main -l Roman -p 321
echo "2" %ERRORLEVEL%
java Main -l Roman123 -p 123
echo "1" %ERRORLEVEL%
java Main -l Roman -p 123 -rol READ -res a.b
echo "0" %ERRORLEVEL%
java Main -l Roman -p 123 -rol READ -res a.b.c
echo "0" %ERRORLEVEL%
java Main -l Roman -p 123 -rol rwe -res a.b
echo "3" %ERRORLEVEL%
java Main -l Roman1 -p 000 -rol WRITE -res a.b
echo "4" %ERRORLEVEL%
java Main -l Roman2 -p 0000 -rol EXECUTE -res a.b
echo "0" %ERRORLEVEL%
java Main -l Roman1 -p 000 -rol WRITE -res CCC
echo "4" %ERRORLEVEL%
java Main -l Roman1 -p 000 -rol WRITE -res ab.c
echo "4" %ERRORLEVEL%
java Main -l Roman1 -p 000 -rol WRITE -res a.QQ
echo "4" %ERRORLEVEL%

java Main -l Roman -p 123 -rol READ -res a.b -ds 2017-10-08 -de 2017-10-08 -vol 100
echo "0" %ERRORLEVEL%
java Main -l Roman -p 123 -rol READ -res a.b -ds 1111111111 -de 2017-10-08 -vol 100
echo "5" %ERRORLEVEL%
java Main -l Roman -p 123 -rol READ -res a.b -ds 2017-10-08 -de 2017-10-08 -vol str100
echo "5" %ERRORLEVEL%
java Main -l Roman -p 123 -rol WRITE -res a.b -ds 1111111111 -de 2222222222 -vol 100
echo "3" %ERRORLEVEL%
java Main -l Vasya -p qwerty -rol READ -res A.B -ds 1111111111 -de 2222222222 -vol str100
echo "1" %ERRORLEVEL%
PAUSE