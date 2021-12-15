package com.example.strategy;

import java.math.BigDecimal;

/**
 * Create by Atiye Mousavi
 * Date: 12/13/2021
 * Time: 3:17 PM
 **/
public class ChristmasDiscounter implements Discounter{

    @Override
    public BigDecimal apply(BigDecimal amount) {
        return amount.multiply(BigDecimal.valueOf(0.9));
    }
}
