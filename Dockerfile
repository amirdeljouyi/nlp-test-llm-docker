# Base image
FROM eclipse-temurin:11-jdk AS utgen-nlp-client
LABEL authors="amirdeljouyi"

RUN apt-get update && apt-get clean

# Set workdir
WORKDIR /app

# Copy binary folder to /app/dataset
COPY binary/ /app/dataset/

# Copy necessary scripts
COPY *.sh *.csv *.jar /app/

# Ensure scripts are executable
RUN chmod +x /app/*.sh /app/*.csv /app/*.jar

# Set workdir to dataset if needed
WORKDIR /app

# Run entrypoint script that picks the right one
CMD ["bash", "/app/run-experiment.sh"]