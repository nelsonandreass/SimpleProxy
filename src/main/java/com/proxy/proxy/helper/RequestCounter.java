package com.proxy.proxy.helper;

import java.util.concurrent.atomic.AtomicInteger;

public class RequestCounter {
	private AtomicInteger count = new AtomicInteger(0);

    public int increment() {
        return count.incrementAndGet();
    }

    public int getCount() {
        return count.get();
    }
}
