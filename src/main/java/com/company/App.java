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
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        AtomicLong value = new AtomicLong( 5 );
        NonBlockingIncrement nonBlockingIncrement = new NonBlockingIncrement(value);
        List<Future<String>> futures = new ArrayList();
        for(int i = 0 ; i < 10; i++){
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
