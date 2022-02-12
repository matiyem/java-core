package com.example.concurrent.priorityTaskExecution;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * created by Atiye Mousavi
 * Date: 2/5/2022
 * Time: 3:05 PM
 **/


public class Job implements Runnable {
//    برای اهداف نمایشی، نام کار و اولویت را در متد run() چاپ می کنیم.
//
//ما همچنین sleep() را اضافه کردیم تا یک کار طولانی‌تر را شبیه‌سازی کنیم. در حالی که کار در حال اجرا است، کارهای بیشتری در صف اولویت انباشته می شوند.
//
//در نهایت، JobPriority یک شماره ساده است:
//
    private static final Logger LOGGER = LoggerFactory.getLogger(Job.class);

    private final String jobName;
    private final JobPriority jobPriority;

    public Job(String jobName, JobPriority jobPriority) {
        this.jobName = jobName;
        this.jobPriority = jobPriority;
    }

    public JobPriority getJobPriority() {
        return jobPriority;
    }

    @Override
    public void run() {
        LOGGER.debug("Job:{} Priority:{}", jobName, jobPriority);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }

    }
}
