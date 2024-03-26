#!/bin/bash

create_post() {
    local host=$1
    curl -s "$host/api/spam"
}

send_requests() {
    local rate_per_second=$1
    local duration_seconds=$2
    local host=$3
    local start_time=$(date +%s)
    echo "sending to $host/api/spam"

    while [ $(($(date +%s) - start_time)) -lt $duration_seconds ]; do
        create_post "$host"
        sleep_for_next_request "$rate_per_second" "$start_time"
    done
}

sleep_for_next_request() {
    local rate_per_second=$1
    local start_time=$2
    local elapsed_time=$(($(date +%s) - start_time))
    local expected_next_request_time=$(echo "$elapsed_time + 1 / $rate_per_second" | bc -l)
    local sleep_time=$(echo "$expected_next_request_time - $(date +%s)" | bc -l)
    if [ "$(echo "$sleep_time > 0" | bc)" -eq 1 ]; then
        sleep "$sleep_time"
    fi
}

main() {
    if [ $# -ne 3 ]; then
        echo "Usage: $0 <rate_per_second> <duration_seconds> <host>"
        exit 1
    fi

    local rate_per_second=$1
    local duration_seconds=$2
    local host=$3

    echo "Sending requests at $rate_per_second requests per second for $duration_seconds seconds to $host..."

    send_requests "$rate_per_second" "$duration_seconds" "$host"
}

main "$@"
