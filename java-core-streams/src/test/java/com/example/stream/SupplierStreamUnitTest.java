package com.example.stream;

import org.junit.Test;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.junit.Assert.fail;

public class SupplierStreamUnitTest {
    //پس از یکبار استفاده از findAny امکان استفاده آن برای findFirst وجود ندارد در اصل stram بسته میشود
//    @Test(expected = IllegalStateException.class)
    @Test()
    public void givenStream_whenStreamUsedTwice_thenThrowException() {
        Stream<String> stringStream = Stream.of("A", "B", "C", "D");
        Optional<String> result1 = stringStream.findAny();
        System.out.println(result1.get());
        Optional<String> result2 = stringStream.findFirst();
        System.out.println(result2.get());
    }

    @Test
    public void givenStream_whenUsingSupplier_thenNoExceptionIsThrown() {
        try {
            Supplier<Stream<String>> streamSupplier = () -> Stream.of("A", "B", "C", "D");
            //برای حل مشکل بالا میتوانیم از متد get استفاده کنیم که یک stream  بصورت فرش برمیگرداند
            Optional<String> result1 = streamSupplier.get().findAny();
            System.out.println(result1.get());
            Optional<String> result2 = streamSupplier.get().findFirst();
            System.out.println(result2.get());
        } catch (IllegalStateException e) {
            fail();
        }
    }

}