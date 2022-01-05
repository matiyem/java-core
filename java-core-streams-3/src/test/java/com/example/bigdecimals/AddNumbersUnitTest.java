package com.example.bigdecimals;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class AddNumbersUnitTest {

    @Test
    public void givenIntStream_whenSum_thenResultIsCorrect() {
        //متد rang فقط برای int و long است و عدد انتهایی را شامل نمیشود
        IntStream intNumbers = IntStream.range(0, 3);
        assertEquals(3, intNumbers.sum());
    }

    @Test
    public void givenCollectionOfDouble_whenUsingMapToDoubleToSum_thenResultIsCorrect() {
        List<Double> doubleNumbers = Arrays.asList(23.48, 52.26, 13.5);
        double result = doubleNumbers.stream()
            .mapToDouble(Double::doubleValue)
            .sum();
        assertEquals(89.24, result, .1);
    }

}
