@echo off
cd src

javac Main.java
if %errorlevel% neq 0 (
    echo Compilation failed. Cleaning up all .class files...
    for /r %%i in (*.class) do del /q "%%i"
    cd ..
    exit /b %errorlevel%
)

java Main

echo Cleaning up all .class files...
for /r %%i in (*.class) do del /q "%%i"

cd ..
pause