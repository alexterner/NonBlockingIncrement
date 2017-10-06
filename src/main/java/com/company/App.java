package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Hello world!
 *
 */
public class App 
{
    private static final int NUM_THREADS = 10;

    public static void main( String[] args )
    {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        NonBlockingIncrement nonBlockingIncrement = new NonBlockingIncrement(5);
        List<Future<String>> futures = new ArrayList();
        for(int i = 0 ; i < NUM_THREADS; i++){
            Callable callable = new IncrementThread( nonBlockingIncrement );
            Future<String> future = executorService.submit( callable );
            futures.add( future );
        }

        for(Future<String> future : futures){
            try {
                String threadName = future.get();
                System.out.println("Executed thread " + threadName);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        System.out.println(" After running 10 threads the value is  = " + nonBlockingIncrement.get());

        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }
}
