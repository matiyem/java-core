package com.example.concurrent.threadsafety;

import com.example.threadSafety.mathutils.MathUtils;
import org.junit.Test;

import java.math.BigInteger;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MathUtilsUnitTest {

    @Test
    public void whenCalledFactorialMethod_thenCorrect() {
        assertThat(MathUtils.factorial(2)).isEqualTo(new BigInteger("2"));
    }
}
