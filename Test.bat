
@echo off
start
javac -sourcepath ./src -classpath lib/commons-cli-1.4.jar src/Main.java

java -classpath "src/;lib/commons-cli-1.4.jar"; Main
java -classpath "src/;lib/commons-cli-1.4.jar"; Main -h
java -classpath "src/;lib/commons-cli-1.4.jar"; Main -login Roman -password 123
echo "0" %ERRORLEVEL%
java -classpath "src/;lib/commons-cli-1.4.jar"; Main -login Roman -password 321
echo "2" %ERRORLEVEL%
java -classpath "src/;lib/commons-cli-1.4.jar"; Main -login Roman123 -password 123
echo "1" %ERRORLEVEL%
java -classpath "src/;lib/commons-cli-1.4.jar"; Main -login Roman -password 123 -role READ -resource a.b
echo "0" %ERRORLEVEL%
java -classpath "src/;lib/commons-cli-1.4.jar"; Main -login Roman -password 123 -role READ -resource a.b.c
echo "0" %ERRORLEVEL%
java -classpath "src/;lib/commons-cli-1.4.jar"; Main -login Roman -password 123 -role rwe -resource a.b
echo "3" %ERRORLEVEL%
java -classpath "src/;lib/commons-cli-1.4.jar"; Main -login Roman1 -password 000 -role WRITE -resource a.b
echo "4" %ERRORLEVEL%
java -classpath "src/;lib/commons-cli-1.4.jar"; Main -login Roman -password 123 -role READ -resource a.b
echo "0" %ERRORLEVEL%
java -classpath "src/;lib/commons-cli-1.4.jar"; Main -login Roman2 -password 0000 -role EXECUTE -resource a.b
echo "0" %ERRORLEVEL%
java -classpath "src/;lib/commons-cli-1.4.jar"; Main -login Roman1 -password 000 -role WRITE -resource CCC
echo "4" %ERRORLEVEL%
java -classpath "src/;lib/commons-cli-1.4.jar"; Main -login Roman1 -password 000 -role WRITE -resource ab.c
echo "4" %ERRORLEVEL%
java -classpath "src/;lib/commons-cli-1.4.jar"; Main -login Roman1 -password 000 -role WRITE -resource a.QQ
echo "4" %ERRORLEVEL%

java -classpath "src/;lib/commons-cli-1.4.jar"; Main -login Roman -password 123 -role READ -resource a.b -dataStart 2017-10-08 -dataEnd 2017-10-08 -volume 100
echo "0" %ERRORLEVEL%
java -classpath "src/;lib/commons-cli-1.4.jar"; Main -login Roman -password 123 -role READ -resource a.b -dataStart 1111111111 -dataEnd 2017-10-08 -volume 100
echo "5" %ERRORLEVEL%
java -classpath "src/;lib/commons-cli-1.4.jar"; Main -login Roman -password 123 -role READ -resource a.b -dataStart 2017-10-08 -dataEnd 2017-10-08 -volume str100
echo "5" %ERRORLEVEL%
java -classpath "src/;lib/commons-cli-1.4.jar"; Main -login Roman -password 123 -role WRITE -resource a.b -dataStart 1111111111 -dataEnd 2222222222 -volume 100
echo "4" %ERRORLEVEL%
java -classpath "src/;lib/commons-cli-1.4.jar"; Main -login Vasya -password qwerty -role READ -resource A.B -dataStart 1111111111 -dataEnd 2222222222 -volume str100
echo "1" %ERRORLEVEL%
PAUSE