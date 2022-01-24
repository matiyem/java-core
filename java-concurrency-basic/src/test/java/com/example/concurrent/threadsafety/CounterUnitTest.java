package com.example.concurrent.threadsafety;

import com.example.threadSafety.callables.CounterCallable;
import com.example.threadSafety.service.Counter;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CounterUnitTest {

    @Test
    public void whenCalledIncrementCounter_thenCorrect() throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Counter counter = new Counter();
        //کانتر را افزایش میدهد
        Future<Integer> future1 = (Future<Integer>) executorService.submit(new CounterCallable(counter));
        Future<Integer> future2 = (Future<Integer>) executorService.submit(new CounterCallable(counter));

        // Just to make sure both are completed
        future1.get();
        future2.get();

        assertThat(counter.getCounter()).isEqualTo(2);
    }
}
