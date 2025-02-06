@ECHO OFF

REM Create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM Delete output from previous run
del ACTUAL.TXT

REM Compile the code into the bin folder
javac -cp ..\src\main\java -Xlint:none -d ..\bin ..\src\main\java\EchoBox.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)

REM No error here, errorlevel == 0

REM Run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin EchoBox < input.txt > ACTUAL.TXT

REM Compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
