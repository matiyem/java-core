package com.example.threadPool;

import java.util.Set;
import com.google.common.collect.Sets;


/*
    Create by Atiye Mousavi 
    Date: 2/1/2022
    Time: 10:11 AM
**/
public class TreeNode {
//    حال اگر بخواهیم تمام مقادیر یک درخت را به صورت موازی جمع کنیم، باید یک رابط RecursiveTask<Integer> پیاده سازی کنیم. هر وظیفه گره خود را دریافت می کند و ارزش خود را به مجموع مقادیر فرزندان خود اضافه می کند. برای محاسبه مجموع مقادیر فرزندان، اجرای کار به شرح زیر است:
//
//استریم های بچه ها را تنظیم می کند
//بر روی این جریان نقشه می‌کشد و یک CountingTask جدید برای هر عنصر ایجاد می‌کند
//هر زیرکار را با فوک کردن آن اجرا می کند
//نتایج را با فراخوانی متد join در هر وظیفه فورک شده جمع آوری می کند
//نتایج را با استفاده از جمع کننده Collectors.summingInt جمع می کند

    private int value;

    private Set<TreeNode> children;

    public TreeNode(int value, TreeNode... children) {
        this.value = value;
        this.children = Sets.newHashSet(children);
    }

    public int getValue() {
        return value;
    }

    public Set<TreeNode> getChildren() {
        return children;
    }
}
