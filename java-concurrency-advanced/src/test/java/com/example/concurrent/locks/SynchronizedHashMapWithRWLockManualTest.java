package com.example.concurrent.locks;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static junit.framework.TestCase.assertEquals;

public class SynchronizedHashMapWithRWLockManualTest {
//    بیایید نگاهی به روش‌های موجود در رابط Lock بیندازیم:
//
//void lock() – در صورت موجود بودن قفل را بدست آورید. اگر قفل در دسترس نباشد یک رشته تا زمانی که قفل آزاد نشود مسدود می شود
//void lockInterruptibly() – این شبیه به lock() است، اما اجازه می دهد تا thread مسدود شده قطع شود و اجرا را از طریق یک java.lang.InterruptedException از سر بگیرد.
//() tryLock boolean – این یک نسخه غیر مسدود کننده متد lock() است. سعی می کند فورا قفل را بدست آورد، در صورت موفقیت آمیز بودن قفل، مقدار واقعی را باز می گرداند
//boolean tryLock(long timeout, TimeUnit timeUnit) - این شبیه به tryLock() است، با این تفاوت که قبل از اینکه از تلاش برای بدست آوردن Lock دست بکشد، مدت زمان تعیین شده را به پایان می رساند.
//void unlock() – نمونه Lock را باز می کند
//یک نمونه قفل شده باید همیشه باز باشد تا از شرایط بن بست جلوگیری شود. یک بلوک کد توصیه شده برای استفاده از قفل باید حاوی یک try/catch و در نهایت مسدود باشد:

    @Test
    public void whenWriting_ThenNoReading() {
        SynchronizedHashMapWithRWLock object = new SynchronizedHashMapWithRWLock();
        final int threadCount = 3;
        final ExecutorService service = Executors.newFixedThreadPool(threadCount);

        executeWriterThreads(object, threadCount, service);

        assertEquals(object.isReadLockAvailable(), false);

        service.shutdown();
    }

    @Test
    public void whenReading_ThenMultipleReadingAllowed() {
        SynchronizedHashMapWithRWLock object = new SynchronizedHashMapWithRWLock();
        final int threadCount = 5;
        final ExecutorService service = Executors.newFixedThreadPool(threadCount);

        executeReaderThreads(object, threadCount, service);

        assertEquals(object.isReadLockAvailable(), true);

        service.shutdown();
    }

    private void executeWriterThreads(SynchronizedHashMapWithRWLock object, int threadCount, ExecutorService service) {
        for (int i = 0; i < threadCount; i++) {
            service.execute(() -> {
                try {
                    object.put("key" + threadCount, "value" + threadCount);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private void executeReaderThreads(SynchronizedHashMapWithRWLock object, int threadCount, ExecutorService service) {
        for (int i = 0; i < threadCount; i++)
            service.execute(() -> object.get("key" + threadCount));
    }

}
