package com.example.charStack;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * created by Atiye Mousavi
 * Date: 1/7/2022
 * Time: 4:44 PM
 **/


public class CharStack {
    private LinkedList<Character> items;

    public CharStack() {
        this.items = new LinkedList<Character>();
    }

    public void push(Character item){items.push(item);}

    public Character peek(){return items.getFirst();}

    public Character pop(){
        Iterator<Character> iter=items.iterator();
        Character item=iter.next();
        if (item !=null){
            iter.remove();
            return item;
        }
        return null;
    }
    public int size(){
        return items.size();
    }
}
