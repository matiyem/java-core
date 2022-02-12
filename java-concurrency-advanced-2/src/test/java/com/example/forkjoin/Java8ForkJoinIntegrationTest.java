package com.example.forkjoin;

import com.example.forkJoin.CustomRecursiveAction;
import com.example.forkJoin.CustomRecursiveTask;
import com.example.forkJoin.util.PoolUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

import static org.junit.Assert.*;

public class Java8ForkJoinIntegrationTest {
//    چارچوب فورک/پیوستن در جاوا 7 ارائه شده است. ابزارهایی برای کمک به سرعت بخشیدن به پردازش موازی با تلاش برای استفاده از تمام هسته‌های پردازنده موجود - که از طریق رویکرد تفرقه و غلبه انجام می‌شود، فراهم می‌کند.
//
//در عمل، این بدان معنی است که چارچوب ابتدا "چنگال" می شود، و به صورت بازگشتی وظیفه را به وظایف فرعی مستقل کوچکتر تقسیم می کند تا زمانی که به اندازه کافی ساده باشند که به صورت ناهمزمان اجرا شوند.
//
//پس از آن، بخش "پیوستن" شروع می شود، که در آن نتایج همه وظایف فرعی به صورت بازگشتی به یک نتیجه واحد می پیوندند، یا در مورد یک وظیفه که خالی می شود، برنامه به سادگی منتظر می ماند تا هر کار فرعی اجرا شود.
//
//برای ارائه اجرای موازی موثر، چارچوب fork/join از مجموعه ای از رشته ها به نام ForkJoinPool استفاده می کند که رشته های کارگری از نوع ForkJoinWorkerThread را مدیریت می کند.

//    به زبان ساده - رشته‌های آزاد سعی می‌کنند کار را از تعداد نخ‌های شلوغ «دزدیدن» کنند.
//
//به‌طور پیش‌فرض، یک Worker Thread وظایفی را از head deque خود دریافت می‌کند. وقتی خالی است، نخ یک کار را از دم دک یک رشته شلوغ دیگر یا از صف ورودی سراسری می گیرد، زیرا این جایی است که احتمالاً بزرگترین قطعات کار در آنجا قرار می گیرند.
//
//این رویکرد احتمال رقابت رشته‌ها برای وظایف را به حداقل می‌رساند. همچنین تعداد دفعاتی که نخ باید به دنبال کار برود را کاهش می‌دهد، زیرا ابتدا روی بزرگترین تکه‌های کار موجود کار می‌کند.

    private int[] arr;
    private CustomRecursiveTask customRecursiveTask;

    @Before
    public void init() {
        Random random = new Random();
        arr = new int[50];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(35);
        }
        customRecursiveTask = new CustomRecursiveTask(arr);
    }

    @Test
    public void callPoolUtil_whenExistsAndExpectedType_thenCorrect() {
//        ForkJoinPool قلب این چارچوب است. این یک پیاده سازی از ExecutorService است که رشته های کارگر را مدیریت می کند و ابزارهایی را برای به دست آوردن اطلاعات در مورد وضعیت و عملکرد Thread Pool در اختیار ما قرار می دهد.
//
//نخ های کارگر می توانند تنها یک کار را در یک زمان اجرا کنند، اما ForkJoinPool یک رشته مجزا برای هر زیرکار ایجاد نمی کند. در عوض، هر رشته در استخر دارای صف دو طرفه (یا deque، عرشه تلفظ شده) خود است که وظایف را ذخیره می کند.
//
//این معماری برای متعادل کردن حجم کاری نخ با کمک الگوریتم کار سرقت حیاتی است.
        ForkJoinPool forkJoinPool = PoolUtil.forkJoinPool;
        ForkJoinPool forkJoinPoolTwo = PoolUtil.forkJoinPool;

        assertNotNull(forkJoinPool);
        assertEquals(2, forkJoinPool.getParallelism());
        assertEquals(forkJoinPool, forkJoinPoolTwo);
    }

    @Test
    public void callCommonPool_whenExistsAndExpectedType_thenCorrect() {
//        در جاوا 8، راحت ترین راه برای دسترسی به نمونه ForkJoinPool استفاده از روش استاتیک ()commonPool است. همانطور که از نام آن پیداست، این یک مرجع به استخر مشترک ارائه می کند، که یک مخزن نخ پیش فرض برای هر ForkJoinTask است.
//
//با توجه به مستندات اوراکل، استفاده از مخزن مشترک از پیش تعریف شده مصرف منابع را کاهش می دهد، زیرا این امر مانع ایجاد یک Thread Pool جداگانه برای هر کار می شود.
//        همین رفتار را می توان در جاوا 7 با ایجاد یک ForkJoinPool و تخصیص آن به یک فیلد استاتیک عمومی از یک کلاس ابزار بدست آورد:
        ForkJoinPool commonPool = ForkJoinPool.commonPool();
        ForkJoinPool commonPoolTwo = ForkJoinPool.commonPool();

        assertNotNull(commonPool);
        assertEquals(commonPool, commonPoolTwo);
    }

    @Test
    public void executeRecursiveAction_whenExecuted_thenCorrect() {

        CustomRecursiveAction myRecursiveAction = new CustomRecursiveAction("ddddffffgggghhhh");
        ForkJoinPool.commonPool().invoke(myRecursiveAction);

        assertTrue(myRecursiveAction.isDone());

    }

    @Test
    public void executeRecursiveTask_whenExecuted_thenCorrect() {
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();

        forkJoinPool.execute(customRecursiveTask);
        customRecursiveTask.join();
        assertTrue(customRecursiveTask.isDone());

        forkJoinPool.submit(customRecursiveTask);
        customRecursiveTask.join();
        assertTrue(customRecursiveTask.isDone());
    }

    @Test
    public void executeRecursiveTaskWithFJ_whenExecuted_thenCorrect() {
        CustomRecursiveTask customRecursiveTaskFirst = new CustomRecursiveTask(arr);
        CustomRecursiveTask customRecursiveTaskSecond = new CustomRecursiveTask(arr);
        CustomRecursiveTask customRecursiveTaskLast = new CustomRecursiveTask(arr);

        customRecursiveTaskFirst.fork();
        customRecursiveTaskSecond.fork();
        customRecursiveTaskLast.fork();
        int result = 0;
        result += customRecursiveTaskLast.join();
        result += customRecursiveTaskSecond.join();
        result += customRecursiveTaskFirst.join();

        assertTrue(customRecursiveTaskFirst.isDone());
        assertTrue(customRecursiveTaskSecond.isDone());
        assertTrue(customRecursiveTaskLast.isDone());
        assertTrue(result != 0);
    }

}
