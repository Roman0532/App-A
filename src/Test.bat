@echo off
start
javac Main.java
java Main Roman 123
echo "0" %ERRORLEVEL%
java Main Roman 321
echo "2" %ERRORLEVEL%
java Main Roman123 123
echo "1" %ERRORLEVEL%
PAUSE