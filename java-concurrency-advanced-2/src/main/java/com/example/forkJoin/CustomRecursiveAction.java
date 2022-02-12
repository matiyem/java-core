package com.example.forkJoin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

/**
 * created by Atiye Mousavi
 * Date: 2/5/2022
 * Time: 5:01 PM
 **/


public class CustomRecursiveAction extends RecursiveAction {
//    ما با java.util.concurrent.Semaphore شروع می کنیم. می‌توانیم از سمافورها برای محدود کردن تعداد رشته‌های همزمان که به یک منبع خاص دسترسی دارند، استفاده کنیم.
//
//در مثال زیر، یک صف ورود ساده برای محدود کردن تعداد کاربران در سیستم پیاده سازی می کنیم

//ForkJoinTask نوع پایه برای کارهایی است که در داخل ForkJoinPool اجرا می شوند. در عمل، یکی از دو زیر کلاس آن باید گسترش یابد: RecursiveAction برای وظایف خالی و RecursiveTask<V> برای وظایفی که مقداری را برمی‌گردانند. هر دو دارای یک متد انتزاعی compute() هستند که در آن منطق وظیفه تعریف شده است.

//    از این الگو می توان برای توسعه کلاس های RecursiveAction خود استفاده کرد. برای انجام این کار، یک شی ایجاد کنید که نمایانگر مقدار کل کار باشد، یک آستانه مناسب انتخاب کنید، یک روش برای تقسیم کار تعریف کنید، و یک روش برای انجام کار تعریف کنید.

    final Logger logger = LoggerFactory.getLogger(CustomRecursiveAction.class);

    private String workLoad = "";
    private static final int THRESHOLD = 4;

    public CustomRecursiveAction(String workLoad) {
        this.workLoad = workLoad;
    }

    @Override
    protected void compute() {
        if (workLoad.length() > THRESHOLD) {
            ForkJoinTask.invokeAll(createSubtasks());
        }else {
            processing(workLoad);
        }
    }

    private Collection<CustomRecursiveAction> createSubtasks() {
        List<CustomRecursiveAction> subtasks = new ArrayList<>();

        String partOne = workLoad.substring(0, workLoad.length() / 2);
        String partTwo = workLoad.substring(workLoad.length() / 2, workLoad.length());

        return subtasks;

    }
    private void processing(String work){
        String result=work.toUpperCase();
        logger.debug("This result - (" + result + ") - was processed by " + Thread.currentThread().getName());
    }
}
