package com.company;

import java.util.concurrent.Callable;

public class IncrementThread implements Callable<String> {

    private NonBlockingIncrement nonBlockingIncrement;

    public IncrementThread(NonBlockingIncrement nonBlockingIncrement) {
        this.nonBlockingIncrement = nonBlockingIncrement;
    }


    public String call() throws Exception {

        nonBlockingIncrement.increment();

        return Thread.currentThread().getName();
    }
}
