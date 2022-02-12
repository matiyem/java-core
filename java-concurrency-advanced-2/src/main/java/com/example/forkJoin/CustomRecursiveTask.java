package com.example.forkJoin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * created by Atiye Mousavi
 * Date: 2/5/2022
 * Time: 5:21 PM
 **/


public class CustomRecursiveTask extends RecursiveTask<Integer> {
   //برای کارهایی که مقداری را برمی گرداند، منطق در اینجا مشابه است، با این تفاوت که نتیجه هر زیرکار در یک نتیجه واحد متحد می شود:
//    در این مثال، کار با یک آرایه ذخیره شده در قسمت arr کلاس CustomRecursiveTask نمایش داده می شود. متد ()createSubtasks به صورت بازگشتی کار را به قطعات کوچکتر تقسیم می کند تا زمانی که هر قطعه از آستانه کوچکتر شود. سپس، متد invokeAll() زیرکارها را به pool مشترک ارسال می کند و لیستی از Future را برمی گرداند.
//
//برای شروع اجرا، متد join() برای هر زیرکار فراخوانی می شود.
//
//در این مثال، این کار با استفاده از جاوا 8 استریم API انجام می شود. روش sum() به عنوان نمایشی از ترکیب نتایج فرعی در نتیجه نهایی استفاده می شود.

//            متد invoke() وظیفه را فورک می کند و منتظر نتیجه می ماند و نیازی به اتصال دستی ندارد:
    private int[] arr;

    private static final int THRESHOLD = 20;

    public CustomRecursiveTask(int[] arr) {
        this.arr = arr;
    }

    @Override
    protected Integer compute() {
        if (arr.length > THRESHOLD) {
//            متد invokeAll() راحت ترین راه برای ارسال دنباله ای از ForkJoinTasks به ForkJoinPool است. وظایف را به عنوان پارامتر (دو کار، var args یا یک مجموعه) می گیرد، سپس forks مجموعه ای از اشیاء Future را به ترتیبی که تولید شده اند برمی گرداند.
//
//همچنین می‌توانید از متدهای fork() و join() جداگانه استفاده کنید. متد fork() یک کار را به یک pool ارسال می کند، اما اجرای آن را آغاز نمی کند. برای این منظور باید از متد join() استفاده شود. در مورد RecursiveAction، join() چیزی جز null برمی گرداند. برای RecursiveTask<V>، نتیجه اجرای کار را برمی گرداند:
            return ForkJoinTask.invokeAll(createSubtasks()).stream().mapToInt(ForkJoinTask::join).sum();
        }
        return null;
    }

    private Collection<CustomRecursiveTask> createSubtasks() {
        List<CustomRecursiveTask> dividedTasks = new ArrayList<>();
        dividedTasks.add(new CustomRecursiveTask(Arrays.copyOfRange(arr, 0, arr.length / 2)));
        dividedTasks.add(new CustomRecursiveTask(Arrays.copyOfRange(arr, arr.length / 2, arr.length)));
        return dividedTasks;
    }

    private Integer processing(int[] arr) {
        return Arrays.stream(arr).filter(a -> a > 10 && a < 27).map(a -> a * 10).sum();
    }
}
