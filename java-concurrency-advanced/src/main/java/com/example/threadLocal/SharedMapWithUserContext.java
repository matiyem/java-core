package com.example.threadLocal;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*
    Create by Atiye Mousavi 
    Date: 2/1/2022
    Time: 9:24 AM
**/
public class SharedMapWithUserContext implements Runnable {
//    ما می خواهیم برای هر شناسه کاربری یک رشته داشته باشیم. ما یک کلاس SharedMapWithUserContext ایجاد می کنیم که رابط Runnable را پیاده سازی می کند. پیاده سازی در متد run() برخی پایگاه داده را از طریق کلاس UserRepository فراخوانی می کند که یک شی Context را برای یک userId معین برمی گرداند.
//
//در مرحله بعد، آن زمینه را در ConcurentHashMap که توسط userId کلید شده است ذخیره می کنیم:
    public final static Map<Integer,Context> userContextPerUserId=new ConcurrentHashMap<>();
    private final Integer userId;
    private UserRepository userRepository=new UserRepository();

    public SharedMapWithUserContext(Integer userId) {
        this.userId = userId;
    }

    @Override
    public void run() {
        String userName=userRepository.getUserNameForUserId(userId);
        userContextPerUserId.put(userId,new Context(userName));

    }
}
