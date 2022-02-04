package com.example.threadPool;

import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/*
    Create by Atiye Mousavi 
    Date: 2/1/2022
    Time: 10:09 AM
**/
public class CountingTask extends RecursiveTask<Integer> {

    private final TreeNode node;

    public CountingTask(TreeNode node) {
        this.node = node;
    }


    @Override
    protected Integer compute() {

        return node.getValue() + node.getChildren().stream()
                .map(childNode -> new CountingTask(childNode).fork())
                .mapToInt(ForkJoinTask::join).sum();
    }
}
