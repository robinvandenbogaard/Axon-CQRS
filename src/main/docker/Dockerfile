FROM ubuntu:latest

# Install required packages
RUN apt-get update

RUN apt-get install -y bash curl jq uuid-runtime bc && \
    rm -rf /var/lib/apt/lists/*

# Copy the Bash script into the container
COPY send_requests.sh /data/send_requests.sh

# Make the Bash script executable
RUN chmod +x /data/send_requests.sh

# Set the entry point to the Bash script
ENTRYPOINT ["/data/send_requests.sh"]
