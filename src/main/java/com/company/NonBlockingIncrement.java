package com.company;

import java.util.concurrent.atomic.AtomicLong;

public class NonBlockingIncrement {

    private AtomicLong value;

    public NonBlockingIncrement(AtomicLong value) {
        this.value =  value ;
    }

    public long get(){
        return value.get();
    }

    public void increment(){
        long v;
        do {
            v = value.get();
        } while( !value.compareAndSet(v, v + 1) );
    }
}
