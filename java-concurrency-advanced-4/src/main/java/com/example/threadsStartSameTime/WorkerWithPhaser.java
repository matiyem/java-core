package com.example.threadsStartSameTime;

import java.time.Instant;
import java.util.concurrent.Phaser;

/*
    Create by Atiye Mousavi 
    Date: 3/15/2022
    Time: 12:38 PM
**/
public class WorkerWithPhaser extends Thread{
    private Phaser phaser;

    public WorkerWithPhaser(String name,Phaser phaser){
        this.phaser=phaser;
        phaser.register();
        setName(name);
    }

    @Override
    public void run() {
        try {
            System.out.printf("[ %s ] created, blocked by the phaser\n", getName());
            phaser.arriveAndAwaitAdvance();
            System.out.printf("[ %s ] starts at: %s\n", getName(), Instant.now());
            // do actual work here...
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }
}
