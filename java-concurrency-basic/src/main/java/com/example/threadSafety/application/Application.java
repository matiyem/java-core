package com.example.threadSafety.application;

import com.example.threadSafety.callables.*;
import com.example.threadSafety.mathutils.MathUtils;
import com.example.threadSafety.service.*;

import java.util.*;
import java.util.concurrent.*;

/*
    Create by Atiye Mousavi 
    Date: 1/18/2022
    Time: 11:41 AM
**/
public class Application {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new Thread(() -> {
            System.out.println(MathUtils.factorial(10));
        }).start();
        new Thread(() -> {
            System.out.println(MathUtils.factorial(5));
        }).start();

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        MessageService messageService = new MessageService("Welcome to Baeldung!");
        Future<String> future1 = executorService.submit(new MessageServiceCallable(messageService));
        Future<String> future2 = executorService.submit(new MessageServiceCallable(messageService));

        System.out.println(future1.get());
        System.out.println(future2.get());

        Counter counter=new Counter();
        Future<Integer> future3=executorService.submit(new CounterCallable(counter));
        Future<Integer> future4=executorService.submit(new CounterCallable(counter));
        System.out.println(future3.get());
        System.out.println(future4.get());

        ObjectLockCounter objectLockCounter=new ObjectLockCounter();
        Future<Integer> future5=executorService.submit(new ExtrinsicLockCounterCallable(objectLockCounter));
        Future<Integer> future6=executorService.submit(new ExtrinsicLockCounterCallable(objectLockCounter));
        System.out.println(future5.get());
        System.out.println(future6.get());

        ReentrantLockCounter reentrantLockCounter=new ReentrantLockCounter();
        Future<Integer> future7=executorService.submit(new ReentrantLockCounterCallable(reentrantLockCounter));
        Future<Integer> future8=executorService.submit(new ReentrantLockCounterCallable(reentrantLockCounter));
        System.out.println(future7.get());
        System.out.println(future8.get());

        ReentrantReadWriteLockCounter reentrantReadWriteLockCounter=new ReentrantReadWriteLockCounter();
        Future<Integer> future9=executorService.submit(new ReentranReadWriteLockCounterCallable(reentrantReadWriteLockCounter));
        Future<Integer> future10=executorService.submit(new ReentranReadWriteLockCounterCallable(reentrantReadWriteLockCounter));
        System.out.println(future9.get());
        System.out.println(future10.get());

//همچنین می‌توان با استفاده از مجموعه کلاس‌های اتمی که جاوا ارائه می‌کند، از جمله AtomicInteger، AtomicLong، AtomicBoolean و AtomicReference به thread-safety دست یافت.
//
//کلاس‌های اتمی به ما اجازه می‌دهند تا عملیات اتمی را بدون استفاده از همگام‌سازی انجام دهیم، که از نظر نخی ایمن هستند. یک عملیات اتمی در یک عملیات در سطح ماشین اجرا می شود.
//
//برای درک مشکلی که با این روش حل می شود، اجازه دهید به کلاس Counter زیر نگاه کنیم:
        AtomicCounter atomicCounter=new AtomicCounter();
        Future<Integer> future11=executorService.submit(new AtomicCounterCallable(atomicCounter));
        Future<Integer> future12=executorService.submit(new AtomicCounterCallable(atomicCounter));
        System.out.println(future11.get());
        System.out.println(future12.get());
//        در برنامه نویسی شی گرا (OOP)، اشیا در واقع نیاز به حفظ حالت از طریق فیلدها و پیاده سازی رفتار از طریق یک یا چند روش دارند.
//
//اگر واقعاً نیاز به حفظ حالت داشته باشیم، می‌توانیم کلاس‌های thread-safe را ایجاد کنیم که حالت را بین رشته‌ها به اشتراک نمی‌گذارند، با ایجاد فیلدهای آن‌ها به صورت رشته محلی.
//
//ما به راحتی می‌توانیم کلاس‌هایی را ایجاد کنیم که فیلدهای آن‌ها به صورت thread-local هستند و فقط با تعریف فیلدهای خصوصی در کلاس‌های Thread.
//
//        //    به طور مشابه، می‌توانیم با اختصاص نمونه‌های ThreadLocal به یک فیلد، فیلدهای thread-local ایجاد کنیم.
//        فیلدهای Thread-local تقریباً شبیه فیلدهای کلاس معمولی هستند، با این تفاوت که هر رشته ای که از طریق یک setter/getter به آنها دسترسی پیدا می کند، یک کپی اولیه مستقل از فیلد دریافت می کند به طوری که هر رشته حالت خاص خود را دارد.
//        ما به راحتی می‌توانیم با استفاده از مجموعه بسته‌بندی‌های همگام‌سازی موجود در چارچوب مجموعه‌ها، مجموعه‌های ایمن برای رشته ایجاد کنیم.
//
//برای مثال، می‌توانیم از یکی از این بسته‌های همگام‌سازی برای ایجاد یک مجموعه ایمن برای رشته استفاده کنیم:



//        بیایید در نظر داشته باشیم که مجموعه‌های همگام‌سازی شده از قفل ذاتی در هر روش استفاده می‌کنند.
//
//این بدان معناست که متدها تنها با یک رشته در یک زمان قابل دسترسی هستند، در حالی که سایر رشته ها تا زمانی که قفل متد توسط اولین رشته باز نشود مسدود می شوند.
//        بنابراین، همگام سازی به دلیل منطق زیربنایی دسترسی همگام، جریمه ای در عملکرد دارد.
        Collection<Integer> syncCollection= Collections.synchronizedCollection(new ArrayList<>());
        Thread thread11 = new Thread(() -> syncCollection.addAll(Arrays.asList(1, 2, 3, 4, 5, 6)));
        Thread thread12 = new Thread(() -> syncCollection.addAll(Arrays.asList(1, 2, 3, 4, 5, 6)));
        thread11.start();
        thread12.start();
//به جای مجموعه‌های همگام‌سازی شده، می‌توانیم از مجموعه‌های همزمان برای ایجاد مجموعه‌های ایمن برای رشته‌ها استفاده کنیم.
//
//جاوا بسته java.util.concurrent را ارائه می دهد که شامل چندین مجموعه همزمان است، مانند ConcurrentHashMap:
//        برخلاف همتایان همگام‌شده‌شان، مجموعه‌های همزمان با تقسیم کردن داده‌هایشان به بخش‌ها، به ایمنی رشته دست می‌یابند. به عنوان مثال، در یک نقشه همزمان، چندین رشته می توانند در بخش های مختلف نقشه قفل شوند، بنابراین چندین رشته می توانند به طور همزمان به نقشه دسترسی داشته باشند.
//
//به دلیل مزایای ذاتی دسترسی به رشته همزمان، مجموعه‌های همزمان عملکرد بسیار بیشتری نسبت به مجموعه‌های همگام دارند.
//
//شایان ذکر است که مجموعه های همگام و همزمان تنها خود مجموعه را به صورت تاپیک ایمن می کنند و نه محتوا را.
        Map<String,String> concurrentMap=new ConcurrentHashMap<>();
        concurrentMap.put("1","one");
        concurrentMap.put("2","two");
        concurrentMap.put("3","three");
    }
}
