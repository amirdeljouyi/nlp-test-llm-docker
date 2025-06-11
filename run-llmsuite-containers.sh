#!/bin/bash

# Number of containers
NUM_CONTAINERS=5

# CPU cores per container
CPUS_PER_CONTAINER=6

# Memory per container
MEMORY="16g"

# Starting CPU core index
START_CPU=0

# Base image
IMAGE_NAME="llmsuite-nlp-image"

docker build -t $IMAGE_NAME Dockerfile

for i in $(seq 1 $NUM_CONTAINERS); do
  # Calculate core range
  CPU_START=$((START_CPU + (i - 1) * CPUS_PER_CONTAINER))
  CPU_END=$((CPU_START + CPUS_PER_CONTAINER - 1))

  # Volume path
  HOST_VOLUME="$(pwd)/output/llmsuite/$i"
  CONTAINER_VOLUME="/app/dataset/llmsuite"

  # Ensure the host directory exists
  mkdir -p "$HOST_VOLUME"

  echo "Starting container llmsuite-$i with CPUs $CPU_START-$CPU_END..."

  docker run -d \
    --cpus="$CPUS_PER_CONTAINER" \
    --cpuset-cpus="$CPU_START-$CPU_END" \
    --memory="$MEMORY" \
    --memory-swap="$MEMORY" \
    -v "$HOST_VOLUME":"$CONTAINER_VOLUME" \
    --name "llmsuite-$i" \
    "$IMAGE_NAME" \
    llmsuite $i
done