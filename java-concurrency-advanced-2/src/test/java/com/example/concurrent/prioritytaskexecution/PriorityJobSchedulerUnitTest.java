package com.example.concurrent.prioritytaskexecution;

import com.example.concurrent.priorityTaskExecution.Job;
import com.example.concurrent.priorityTaskExecution.JobPriority;
import com.example.concurrent.priorityTaskExecution.PriorityJobScheduler;
import org.junit.Test;

public class PriorityJobSchedulerUnitTest {
    private static final int POOL_SIZE = 1;
    private static final int QUEUE_SIZE = 10;

    @Test
    public void whenMultiplePriorityJobsQueued_thenHighestPriorityJobIsPicked() {
//        به منظور نمایش اینکه کارها به ترتیب اولویت اجرا می شوند، POOL_SIZE را به عنوان 1 نگه داشته ایم، حتی اگر QUEUE_SIZE 10 باشد. ما کارهایی را با اولویت های متفاوت به زمان بندی ارائه می دهیم.
        Job job1 = new Job("Job1", JobPriority.LOW);
        Job job2 = new Job("Job2", JobPriority.MEDIUM);
        Job job3 = new Job("Job3", JobPriority.HIGH);
        Job job4 = new Job("Job4", JobPriority.MEDIUM);
        Job job5 = new Job("Job5", JobPriority.LOW);
        Job job6 = new Job("Job6", JobPriority.HIGH);

        PriorityJobScheduler pjs = new PriorityJobScheduler(POOL_SIZE, QUEUE_SIZE);

        pjs.scheduleJob(job1);
        pjs.scheduleJob(job2);
        pjs.scheduleJob(job3);
        pjs.scheduleJob(job4);
        pjs.scheduleJob(job5);
        pjs.scheduleJob(job6);

        // ensure no tasks is pending before closing the scheduler
        while (pjs.getQueuedTaskCount() != 0);

        // delay to avoid job sleep (added for demo) being interrupted
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }

        pjs.closeScheduler();
    }
}
