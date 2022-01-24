package com.example.executor;

import java.util.concurrent.Executor;

/*
    Create by Atiye Mousavi 
    Date: 1/17/2022
    Time: 11:35 AM
**/
public class ExecutorDemo {
    public void execute(){
        Executor executor=new Invoker();
        executor.execute(()->{
            //task to be performed
        });
    }
}
