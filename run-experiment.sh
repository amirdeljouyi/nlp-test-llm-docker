#!/bin/bash

INPUT_TYPE=$1
ATTEMPT=$2

if [ "$INPUT_TYPE" == "llmsuite" ]; then
    if [ -z "$ATTEMPT" ]; then
        echo "No attempt specified. Running LLMSuite multiple..."
        /app/run-llmsuite-multiple.sh
    else
        echo "Running LLMSuite with attempt $ATTEMPT..."
        /app/run-llmsuite-files.sh "$ATTEMPT"
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