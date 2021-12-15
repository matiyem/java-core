package com.example.java_8_features;

/**
 * Create by Atiye Mousavi
 * Date: 12/13/2021
 * Time: 1:34 PM
 **/
public interface Vehicle {

    void MoveTo(long altitude,long logitude);
    //متدهای static که در داخل اینترفیس نوشته میشوند  امکان override آن وجود ندارد
    static String producer(){return "N&F Vehicles";}
    default long[] startPosition(){return new long[]{23,15};}
//    در جاوا 8 اینترفیس ها میتواندد بصورت دیفالت و static تعریف شوند
    default String getOverview(){return "ATV made by " + producer();}

}
