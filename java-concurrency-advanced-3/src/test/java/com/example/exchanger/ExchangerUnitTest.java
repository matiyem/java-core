package com.example.exchanger;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutionException;

import static java.util.concurrent.CompletableFuture.runAsync;
import static org.junit.Assert.assertEquals;

public class ExchangerUnitTest {
    
    
    @Test
    public void givenThreads_whenMessageExchanged_thenCorrect() {
//        در اینجا، ما دو رشته را داریم که با استفاده از مبدل مشترک، پیام‌ها را بین یکدیگر رد و بدل می‌کنند. بیایید مثالی را ببینیم که در آن یک شی از رشته اصلی با یک رشته جدید مبادله می کنیم:
        Exchanger<String> exchanger = new Exchanger<>();

        Runnable taskA = () -> {
            try {
                String message = exchanger.exchange("from A");
                assertEquals("from B", message);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        };

        Runnable taskB = () -> {
            try {
                String message = exchanger.exchange("from B");
                assertEquals("from A", message);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        };

        CompletableFuture.allOf(runAsync(taskA), runAsync(taskB)).join();
    }

    @Test
    public void givenThread_WhenExchangedMessage_thenCorrect() throws InterruptedException, ExecutionException {
//        توجه داشته باشید که ابتدا باید thread runner و بعداً exchange() را در thread اصلی فراخوانی کنیم.
//
//همچنین، توجه داشته باشید که اگر رشته دوم به نقطه تبادل در زمان نرسد، تماس اولین رشته ممکن است به پایان برسد. مدت زمانی که اولین رشته باید منتظر بماند را می توان با استفاده از مبادله بارگذاری شده کنترل کرد (T t، مدت زمان طولانی، TimeUnit timeUnit).
        Exchanger<String> exchanger = new Exchanger<>();

        Runnable runner = () -> {
            try {
                String message = exchanger.exchange("from runner");
                assertEquals("to runner", message);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        };

        CompletableFuture<Void> result = CompletableFuture.runAsync(runner);
        String msg = exchanger.exchange("to runner");
        assertEquals("from runner", msg);
        result.join();
    }

}
