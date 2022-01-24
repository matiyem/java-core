package com.example.concurrent.runnable;

import org.apache.commons.lang3.RandomUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class RunnableVsThreadLiveTest {
//	به عبارت ساده، ما به طور کلی استفاده از Runnable over Thread را تشویق می کنیم:
//
//هنگام گسترش کلاس Thread، هیچ یک از متدهای آن را لغو نمی کنیم. در عوض، روش Runnable (که Thread اتفاقاً پیاده‌سازی می‌شود) را نادیده می‌گیریم. این نقض آشکار اصل IS-A Thread است
//ایجاد یک پیاده‌سازی Runnable و ارسال آن به کلاس Thread از ترکیب و نه وراثت استفاده می‌کند - که انعطاف‌پذیرتر است.
//پس از گسترش کلاس Thread، نمی توانیم کلاس دیگری را گسترش دهیم
//از جاوا 8 به بعد، Runnables را می توان به عنوان عبارات لامبدا نشان داد

	private static Logger log = 
	  LoggerFactory.getLogger(RunnableVsThreadLiveTest.class);

	private static ExecutorService executorService;
	
	@BeforeClass
	public static void setup() {
		executorService = Executors.newCachedThreadPool(); 
	}
	
	@Test
	public void givenARunnable_whenRunIt_thenResult() throws Exception{
		Thread thread = new Thread(new SimpleRunnable(
		  "SimpleRunnable executed using Thread"));
		thread.start();
		thread.join();
	}
	
	@Test
	public void givenARunnable_whenSubmitToES_thenResult() throws Exception{
		
		executorService.submit(new SimpleRunnable(
		  "SimpleRunnable executed using ExecutorService")).get();
	}
	
	@Test
	public void givenARunnableLambda_whenSubmitToES_thenResult() 
	  throws Exception{
		
		executorService.submit(()-> 
		  log.info("Lambda runnable executed!!!")).get();
	}
	
	@Test
	public void givenAThread_whenRunIt_thenResult() throws Exception{
		Thread thread = new SimpleThread(
		  "SimpleThread executed using Thread");
		thread.start();
		thread.join();
	}
	
	@Test
	public void givenAThread_whenSubmitToES_thenResult() throws Exception{
		
		executorService.submit(new SimpleThread(
		  "SimpleThread executed using ExecutorService")).get();
	}
	
	@Test
	public void givenACallable_whenSubmitToES_thenResult() throws Exception {
		
		Future<Integer> future = executorService.submit(
		  new SimpleCallable());
		log.info("Result from callable: {}", future.get());
	}
	
	@Test
	public void givenACallableAsLambda_whenSubmitToES_thenResult() 
	  throws Exception {
		
		Future<Integer> future = executorService.submit(() -> RandomUtils.nextInt(0, 100));
		
		log.info("Result from callable: {}", future.get());
	}
	
	@AfterClass
	public static void tearDown() {
		if ( executorService != null && !executorService.isShutdown()) {
			executorService.shutdown();
		}
	}
}

class SimpleThread extends Thread{
	
	private static final Logger log = 
	  LoggerFactory.getLogger(SimpleThread.class);
	
	private String message;
	
	SimpleThread(String message) {
		this.message = message;
	}
	
	@Override
	public void run() {
		log.info(message);
	}
}

class SimpleRunnable implements Runnable {

	private static final Logger log = 
	  LoggerFactory.getLogger(SimpleRunnable.class);
	
	private String message;
	
	SimpleRunnable(String message) {
		this.message = message;
	}
	
	
	@Override
	public void run() {
		log.info(message);
	}
}

class SimpleCallable implements Callable<Integer> {

	@Override
	public Integer call() throws Exception {
		return RandomUtils.nextInt(0, 100);
	}

}

