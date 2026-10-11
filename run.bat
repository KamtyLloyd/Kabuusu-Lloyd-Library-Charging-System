@echo off
javac *.java
if errorlevel 1 pause & exit /b 1
java Main
pause
