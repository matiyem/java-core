package com.example.concurrent.cyclicbarrier;

import com.example.concurrent.cyclicBarrier.CyclicBarrierResetExample;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CyclicBarrierResetExampleUnitTest {
    
    @Test
    public void whenCyclicBarrier_reset() {
        //مشابه مثال بالا، یک CyclicBarrier را با تعداد 7 تعریف می کنیم و از 20 رشته مختلف منتظر می مانیم:
        CyclicBarrierResetExample ex = new CyclicBarrierResetExample(7,20);
        int lineCount = ex.countWaits();
        assertTrue(lineCount > 7);
    }
}
