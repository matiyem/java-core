package com.example.hashmapvshashtable;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.*;
import java.util.Map.Entry;

import static org.junit.Assert.assertEquals;

public class HashmapVsHashtableDifferenceUnitTest {
//    Hashtable و HashMap کاملا مشابه هستند - هر دو مجموعه هایی هستند که رابط نقشه را پیاده سازی می کنند.
//
//همچنین متدهای put()، get()، remove()، و containKey() عملکرد O(1) در زمان ثابت را ارائه می دهند. در داخل، این روش ها بر اساس مفهوم کلی هش با استفاده از سطل ها برای ذخیره داده ها کار می کنند.
//
//هیچ یک از کلاس ها ترتیب درج عناصر را حفظ نمی کنند. به عبارت دیگر، زمانی که مقادیر را تکرار می کنیم، اولین مورد اضافه شده ممکن است اولین مورد نباشد.
//
//اما آنها همچنین دارای تفاوت هایی هستند که در برخی موقعیت ها یکی را بهتر از دیگری می کند. بیایید بیشتر به این تفاوت ها نگاه کنیم.


//    اولاً، Hashtable از نظر موضوع ایمن است و می تواند بین چندین رشته در برنامه به اشتراک گذاشته شود.
//
//از سوی دیگر، HashMap همگام نیست و نمی توان با چندین رشته بدون کد همگام سازی اضافی به آن دسترسی داشت. ما می‌توانیم از Collections.synchronizedMap() برای ساختن نسخه ایمن از HashMap استفاده کنیم. ما همچنین می‌توانیم فقط کد قفل سفارشی ایجاد کنیم یا کد را با استفاده از کلمه کلیدی همگام‌سازی شده ایمن کنیم.
//
//HashMap همگام نیست، بنابراین سریعتر است و از حافظه کمتری نسبت به Hashtable استفاده می کند. به طور کلی، اشیاء غیرهمگام سریعتر از موارد همگام در یک برنامه رشته ای واحد هستند.
//    تفاوت دیگر در هندلینگ پوچ است. HashMap اجازه می دهد تا یک ورودی با کلید null و همچنین تعداد زیادی ورودی با مقدار null اضافه کنید. در مقابل، Hashtable به هیچ وجه اجازه null را نمی دهد. بیایید نمونه ای از null و HashMap را ببینیم:


//    HashMap از Iterator برای تکرار روی مقادیر استفاده می کند، در حالی که Hashtable دارای Enumerator برای همان است. Iterator جانشین Enumerator است که ایرادات اندک آن را برطرف می کند. به عنوان مثال، Iterator یک متد remove() برای حذف عناصر از مجموعه های زیرین دارد.
//
//Iterator یک تکرار کننده سریع است. به عبارت دیگر، هنگامی که مجموعه زیربنایی در حین تکرار اصلاح می شود، یک ConcurrentModificationException ایجاد می کند. بیایید مثال Fail-fast را ببینیم:

//    ما باید از HashMap برای یک برنامه غیرهمگام یا تک رشته ای استفاده کنیم.
//
//شایان ذکر است که از JDK 1.8، Hashtable منسوخ شده است. با این حال، ConcurrentHashMap یک جایگزین عالی Hashtable است. ما باید ConcurrentHashMap را برای استفاده در برنامه هایی با موضوعات متعدد در نظر بگیریم.








    // null values
    @Test(expected = NullPointerException.class)
    public void givenHashtable_whenAddNullKey_thenNullPointerExceptionThrown() {
        Hashtable<String, String> table = new Hashtable<String, String>();        
        table.put(null, "value");
    }
    
    @Test(expected = NullPointerException.class)
    public void givenHashtable_whenAddNullValue_thenNullPointerExceptionThrown() {
        Hashtable<String, String> table = new Hashtable<String, String>();        
        table.put("key", null);
    }
    
    @Test
    public void givenHashmap_whenAddNullKeyAndValue_thenObjectAdded() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put(null, "value");
        map.put("key1", null);
        map.put("key2", null);
        
        assertEquals(3, map.size());
    }
    
    // fail-fast iterator
    @Test(expected = ConcurrentModificationException.class)
    public void givenHashmap_whenModifyUnderlyingCollection_thenConcurrentModificationExceptionThrown() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        
        Iterator<String> iterator = map.keySet().iterator();
        while(iterator.hasNext()){ 
            iterator.next();
            map.put("key4", "value4");
        }        
    }

    @Test
    public void givenHashtable_whenModifyUnderlyingCollection_thenItHasNoEffectOnIteratedCollection() {
        Hashtable<String, String> table = new Hashtable<String, String>();        
        table.put("key1", "value1");
        table.put("key2", "value2");
        
        List<String> keysSelected = Lists.newArrayList(); 
        Enumeration<String> keys = table.keys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            keysSelected.add(key);
            
            if (key.equals("key1")) {
                table.put("key3", "value3");
            }
        }
        
        assertEquals(2, keysSelected.size());
    }
    
    // synchronized map
    @Test
    public void givenHashmap_thenCreateSynchronizedMap() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        
        Set<Entry<String, String>> set = map.entrySet();
        synchronized (map) {
            Iterator<Entry<String, String>> it = set.iterator();
            while(it.hasNext()) {
                Entry<String, String> elem = (Entry<String, String>)it.next();
             }            
        }
        
        Map<String, String> syncMap = Collections.synchronizedMap(map);
    }
}
