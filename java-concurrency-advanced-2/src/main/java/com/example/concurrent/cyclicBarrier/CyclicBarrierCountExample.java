package com.example.concurrent.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * created by Atiye Mousavi
 * Date: 2/5/2022
 * Time: 10:44 AM
 **/


public class CyclicBarrierCountExample {
//    اولین تفاوت در اینجا این است که رشته هایی که منتظر هستند خود مانع هستند.
//
//دوم، و مهمتر از آن، await() دوم بی فایده است. یک رشته نمی تواند یک مانع را دو بار شمارش معکوس کند.
//
//در واقع، از آنجا که t باید منتظر رشته دیگری باشد تا await() را فراخوانی کند – تا تعداد آن به دو برسد – دومین تماس t به await() در واقع فراخوانی نمی شود تا زمانی که مانع از قبل شکسته شود!
//
//در آزمایش ما، مانع رد نشده است، زیرا ما فقط یک نخ در انتظار داریم و نه دو رشته ای که برای قطع شدن مانع لازم است. این از روش cyclicBarrier.isBroken() نیز مشهود است که false را برمی گرداند.
    private int count;

    public CyclicBarrierCountExample(int count) {
        this.count = count;
    }

    public boolean callTwiceInSameThread() {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(count);
        Thread t = new Thread(() -> {
            try {
                cyclicBarrier.await();
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        t.start();
        return cyclicBarrier.isBroken();
    }

    public static void main(String[] args) {
        CyclicBarrierCountExample ex=new CyclicBarrierCountExample(7);
        System.out.println("Count : " + ex.callTwiceInSameThread());
    }
}
