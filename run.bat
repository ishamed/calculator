@echo off
cd src
javac Main.java
if %errorlevel% neq 0 exit /b %errorlevel%
java Main
cd ..
pause