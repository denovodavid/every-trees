@ECHO off
ECHO Starting Build Process...
CALL gradlew.bat build
ECHO.
PAUSE
START .\build\libs