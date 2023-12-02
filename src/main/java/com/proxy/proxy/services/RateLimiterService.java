package com.proxy.proxy.services;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.proxy.proxy.helper.RequestCounter;

@Service
public class RateLimiterService {
	private final Map<String, RequestCounter> requestCounters = new ConcurrentHashMap<>();
	
	private static final int MAX_REQUESTS_PER_MINUTE = 60; // Adjust as needed

    public boolean allowRequest(String senderId, String ipAddress) {
        String key = senderId + "_" + ipAddress;

        // Get or create a RequestCounter for the key
        RequestCounter counter = requestCounters.computeIfAbsent(key, k -> new RequestCounter());

        // Increment the request count
        int count = counter.increment();

        // Check if the request count exceeds the limit
        if (count <= MAX_REQUESTS_PER_MINUTE) {
            return true; // Allow the request
        } else {
            return false; // Deny the request
        }
    }
}
