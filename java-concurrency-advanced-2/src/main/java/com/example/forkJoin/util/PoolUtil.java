package com.example.forkJoin.util;

import java.util.concurrent.ForkJoinPool;

/**
 * created by Atiye Mousavi
 * Date: 2/5/2022
 * Time: 5:00 PM
 **/


public class PoolUtil {
    //    با سازنده‌های ForkJoinPool، می‌توان یک Thread Pool سفارشی با سطح خاصی از موازی‌سازی، کارخانه نخ و کنترل‌کننده استثنا ایجاد کرد. در مثال بالا، استخر دارای سطح موازی 2 است. این بدان معناست که استخر از 2 هسته پردازنده استفاده می کند.
    public static ForkJoinPool forkJoinPool = new ForkJoinPool(2);

}
