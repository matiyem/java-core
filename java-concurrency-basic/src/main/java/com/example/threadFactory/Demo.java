package com.example.threadFactory;


/*
    Create by Atiye Mousavi 
    Date: 1/18/2022
    Time: 11:37 AM
**/
public class Demo {
    public void execute(){
        BaeldungThreadFactory factory=new BaeldungThreadFactory("BaeldungThreadFactory");
        for (int i=0 ; i<10 ; i++){
            Thread t=factory.newThread(new Task());
            t.start();
        }
    }
}
