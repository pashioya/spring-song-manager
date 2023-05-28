#!/bin/bash

# Check if the Docker container exists and is running
if docker inspect -f '{{.State.Running}}' my-postgres-container >/dev/null 2>&1; then
    echo "PostgreSQL container 'my-postgres-container' is already running."
else
    # Build the Docker image
    docker build -t my-postgres .
    # Run the Docker container
    docker run -d -p 5432:5432 --name my-postgres-container my-postgres
    echo "PostgreSQL container 'my-postgres-container' started."
fi
