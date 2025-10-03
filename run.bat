@echo off
echo Running Mr. Dinner Service...
echo.

REM Check if classes exist
if not exist com\mrdinner\app\Main.class (
    echo Classes not found. Building first...
    call build.bat
    if %errorlevel% neq 0 (
        echo Build failed. Exiting...
        pause
        exit /b 1
    )
)

echo Starting Mr. Dinner Service...
echo.

java com.mrdinner.app.Main

echo.
echo Application finished.
pause
