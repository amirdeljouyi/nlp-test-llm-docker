#!/bin/bash

INPUT_TYPE=$1
ATTEMPT=$2

if [ "$INPUT_TYPE" == "utgen-files" ]; then
    if [ -z "$ATTEMPT" ]; then
        echo "No attempt specified. Running UTGen multiple..."
        /app/run-utgen-multiple.sh
    else
        echo "Running UTGen with attempt $ATTEMPT..."
        /app/run-utgen-files.sh "$ATTEMPT"
    fi
else
    if [ -z "$ATTEMPT" ]; then
        echo "No attempt specified. Running Evo multiple..."
        /app/run-evo-multiple.sh
    else
        echo "Running Evo with attempt $ATTEMPT..."
        /app/run-evo.sh "$ATTEMPT"
    fi
fi