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
echo "4" %ERRORLEVEL%
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
PAUSE