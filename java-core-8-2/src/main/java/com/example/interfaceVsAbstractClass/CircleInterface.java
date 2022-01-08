package com.example.interfaceVsAbstractClass;

import java.util.Arrays;
import java.util.List;

/**
 * Create by Atiye Mousavi
 * Date: 1/5/2022
 * Time: 1:22 PM
 **/
public interface CircleInterface {
//    کلاس abstract می تواند یک حالت داشته باشد و متدهای آن می توانند به وضعیت پیاده سازی دسترسی داشته باشند. اگرچه روش‌های پیش‌فرض در یک رابط مجاز هستند، اما نمی‌توانند به وضعیت پیاده‌سازی دسترسی داشته باشند.
    List<String> allowedColors= Arrays.asList("RED","GREEN","BLUE");
    String getColor();
    public default boolean isValid(){
        return allowedColors.contains(getColor());
    }
}
