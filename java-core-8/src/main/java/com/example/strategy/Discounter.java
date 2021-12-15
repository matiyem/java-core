package com.example.strategy;

import java.util.function.UnaryOperator;

import java.math.BigDecimal;

/**
 * Create by Atiye Mousavi
 * Date: 12/13/2021
 * Time: 3:19 PM
 **/
public interface Discounter extends UnaryOperator<BigDecimal> {
    //unaryOperator  به اینا میگن a++ یا a-- و....
    default Discounter combine(Discounter after) {
        return value -> after.apply(this.apply(value));
    }
    static Discounter christmas() {return (amount) -> amount.multiply(BigDecimal.valueOf(0.9));}
    static Discounter newYear() {return (amount) -> amount.multiply(BigDecimal.valueOf(0.8));}
    static Discounter easter() {return (amount) -> amount.multiply(BigDecimal.valueOf(0.5));}
}
