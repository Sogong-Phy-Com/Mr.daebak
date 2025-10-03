#!/bin/bash
echo "Running Mr. Dinner Service..."
echo

# Check if classes exist
if [ ! -f "com/mrdinner/app/Main.class" ]; then
    echo "Classes not found. Building first..."
    chmod +x build.sh
    ./build.sh
    if [ $? -ne 0 ]; then
        echo "Build failed. Exiting..."
        exit 1
    fi
fi

echo "Starting Mr. Dinner Service..."
echo

java com.mrdinner.app.Main

echo
echo "Application finished."
