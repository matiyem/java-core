package com.example.exchanger;

import org.junit.Test;

import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutionException;

import static java.util.concurrent.CompletableFuture.runAsync;


public class ExchangerPipeLineManualTest {

    private static final int BUFFER_SIZE = 100;

    @Test
    public void givenData_whenPassedThrough_thenCorrect() throws InterruptedException, ExecutionException {
//در اینجا ما سه رشته داریم: reader، processor و writer. آنها با هم به عنوان یک خط لوله واحد کار می کنند و داده ها را بین خود رد و بدل می کنند.
//
//ReaderExchanger بین Reader و Thread پردازنده به اشتراک گذاشته می شود، در حالی که writerExchanger بین پردازنده و Writer Thread مشترک است.
//
//توجه داشته باشید که مثال اینجا فقط برای نمایش است. هنگام ایجاد حلقه های بی نهایت با while(true) باید مراقب باشیم. همچنین برای اینکه کد قابل خواندن باشد، برخی از موارد استثنا را حذف کرده ایم.
//
//این الگوی تبادل داده در حین استفاده مجدد از بافر امکان جمع آوری زباله کمتر را فراهم می کند. متد Exchange همان نمونه های صف را برمی گرداند و بنابراین هیچ GC برای این اشیا وجود نخواهد داشت. برخلاف هر صف مسدود کننده، مبدل هیچ گره یا شیئی را برای نگهداری و اشتراک گذاری داده ایجاد نمی کند.
//
//ایجاد چنین خط لوله ای مشابه الگوی Disrupter است، با یک تفاوت اساسی، الگوی Disrupter از چندین تولیدکننده و مصرف کننده پشتیبانی می کند، در حالی که یک مبدل می تواند بین یک جفت مصرف کننده و تولید کننده استفاده شود.
        Exchanger<Queue<String>> readerExchanger = new Exchanger<>();
        Exchanger<Queue<String>> writerExchanger = new Exchanger<>();
        int counter = 0;

        Runnable reader = () -> {
            Queue<String> readerBuffer = new ConcurrentLinkedQueue<>();
            while (true) {
                readerBuffer.add(UUID.randomUUID().toString());
                if (readerBuffer.size() >= BUFFER_SIZE) {
                    try {
                        readerBuffer = readerExchanger.exchange(readerBuffer);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        throw new RuntimeException(e);
                    }
                }
            }
        };

        Runnable processor = () -> {
            Queue<String> processorBuffer = new ConcurrentLinkedQueue<>();
            Queue<String> writerBuffer = new ConcurrentLinkedQueue<>();
            try {
                processorBuffer = readerExchanger.exchange(processorBuffer);
                while (true) {
                    writerBuffer.add(processorBuffer.poll());
                    if (processorBuffer.isEmpty()) {
                        try {
                            processorBuffer = readerExchanger.exchange(processorBuffer);
                            writerBuffer = writerExchanger.exchange(writerBuffer);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            throw new RuntimeException(e);
                        }
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        };

        Runnable writer = () -> {
            Queue<String> writerBuffer = new ConcurrentLinkedQueue<>();
            try {
                writerBuffer = writerExchanger.exchange(writerBuffer);
                while (true) {
                    System.out.println(writerBuffer.poll());
                    if (writerBuffer.isEmpty()) {
                        writerBuffer = writerExchanger.exchange(writerBuffer);
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        };

        CompletableFuture.allOf(runAsync(reader), runAsync(processor), runAsync(writer)).get();
    }

}
