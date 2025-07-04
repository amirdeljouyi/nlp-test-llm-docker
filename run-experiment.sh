#!/bin/bash

INPUT_TYPE=$1
ATTEMPT=$2
JAVA=$3

echo $INPUT_TYPE $ATTEMPT $JAVA

if [ "$INPUT_TYPE" == "llmsuite" ]; then
    if [ -z "$ATTEMPT" ]; then
        echo "No attempt specified. Running LLMSuite multiple..."
        /app/run-llmsuite-multiple.sh
    else
        echo "Running LLMSuite with JAVA $JAVA with attempt $ATTEMPT..."
        /app/"run-llmsuite-files.${JAVA}.sh" "$ATTEMPT"
    fi
elif [ "$INPUT_TYPE" == "llmsuite-llama" ]; then
    if [ -z "$ATTEMPT" ]; then
        echo "No attempt specified. Running LLMSuite-LLama multiple..."
        /app/run-llmsuite-multiple.sh
    else
        echo "Running LLMSuite with JAVA $JAVA with attempt $ATTEMPT..."
        /app/"run-llmsuite-llama.${JAVA}.sh" "$ATTEMPT"
    fi
elif [ "$INPUT_TYPE" == "llmsuite-file" ]; then
    if [ -z "$ATTEMPT" ]; then
        echo "No attempt specified. Running LLMSuite-File multiple..."
        /app/run-llmsuite-multiple.sh
    else
        echo "Running LLMSuite with JAVA $JAVA with attempt $ATTEMPT..."
        /app/"run-llmsuite-file.${JAVA}.sh" "$ATTEMPT"
    fi
elif [ "$INPUT_TYPE" == "codamosa" ]; then
    if [ -z "$ATTEMPT" ]; then
        echo "No attempt specified. Running CodaMosa multiple..."
        /app/run-codamosa-multiple.sh
    else
        echo "Running CodaMosa with JAVA $JAVA with attempt $ATTEMPT..."
        /app/"run-codamosa.${JAVA}.sh" "$ATTEMPT"
    fi
else
    if [ -z "$ATTEMPT" ]; then
        echo "No attempt specified. Running Evo multiple..."
        /app/run-evo-multiple.sh
    else
        echo "Running EvoSuite with JAVA $JAVA with attempt $ATTEMPT..."
        /app/"run-evo.${JAVA}.sh" "$ATTEMPT"
    fi
fi