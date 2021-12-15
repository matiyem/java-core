package com.example.strategy;

import java.math.BigDecimal;

/**
 * Create by Atiye Mousavi
 * Date: 12/13/2021
 * Time: 3:35 PM
 **/
public class EasterDiscounter implements Discounter{
    @Override
    public BigDecimal apply(BigDecimal amount) {
        return amount.multiply(BigDecimal.valueOf(0.5));
    }
}
