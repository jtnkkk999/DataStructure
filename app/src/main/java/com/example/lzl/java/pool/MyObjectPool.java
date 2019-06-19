package com.example.lzl.java.pool;

public class MyObjectPool extends ObjectPool{
    public MyObjectPool(int initialCapacity, int maxCapacity) {
        super(initialCapacity, maxCapacity);
    }

    public MyObjectPool(int maxCapacity) {
        super(maxCapacity);
    }

    @Override
    protected Object create() {//LRU
        return new Object();
    }
}
