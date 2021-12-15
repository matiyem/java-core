package com.example.strategy;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static com.example.strategy.Discounter.*;
import static org.assertj.core.api.Assertions.assertThat;

public class StrategyDesignPatternUnitTest {
    @Test
    public void shouldDivideByTwo_WhenApplyingStaffDiscounter() {
//        این بسیار خوب عمل می کند، اما مشکل اینجاست که ایجاد یک کلاس مشخص برای هر استراتژی می تواند کمی دردسرساز باشد. گزینه جایگزین استفاده از انواع داخلی ناشناس است، اما این روش هنوز کاملاً پرمخاطب است و از راه حل قبلی خیلی راحت تر نیست:
        Discounter staffDiscounter = new EasterDiscounter();

        final BigDecimal discountedValue = staffDiscounter
          .apply(BigDecimal.valueOf(100));

        assertThat(discountedValue)
          .isEqualByComparingTo(BigDecimal.valueOf(50));
    }

    @Test
    public void shouldDivideByTwo_WhenApplyingStaffDiscounterWithAnonyousTypes() {
//        پیاده سازی بصورت anonymous کلاس ها
        //این پیاده سازی و پیاده سازی بالا هر دو در استراتژی های قبل از جاوا 8 است
        Discounter staffDiscounter = new Discounter() {
            @Override
            public BigDecimal apply(BigDecimal amount) {
                return amount.multiply(BigDecimal.valueOf(0.5));
            }
        };

        final BigDecimal discountedValue = staffDiscounter
          .apply(BigDecimal.valueOf(100));

        assertThat(discountedValue)
          .isEqualByComparingTo(BigDecimal.valueOf(50));
    }

    @Test
    public void shouldDivideByTwo_WhenApplyingStaffDiscounterWithLamda() {
//        در اصل، لامبدا را می توان به عنوان جایگزینی برای نوع داخلی anonymous در نظر گرفت.
        Discounter staffDiscounter = amount -> amount.multiply(BigDecimal.valueOf(0.5));

        final BigDecimal discountedValue = staffDiscounter
          .apply(BigDecimal.valueOf(100));

        assertThat(discountedValue)
          .isEqualByComparingTo(BigDecimal.valueOf(50));
    }

    @Test
    public void shouldApplyAllDiscounts() {
//        List<Discounter> discounters = newArrayList(
//  amount -> amount.multiply(BigDecimal.valueOf(0.9)),
//  amount -> amount.multiply(BigDecimal.valueOf(0.8)),
//  amount -> amount.multiply(BigDecimal.valueOf(0.5))
//);
        //عبارت بالا در اصل همین عبارت پایین است
        List<Discounter> discounters = Arrays.asList(christmas(), newYear(), easter());

        BigDecimal amount = BigDecimal.valueOf(100);

        final Discounter combinedDiscounter = discounters
          .stream()
          .reduce(v -> v, Discounter::combine);

        combinedDiscounter.apply(amount);
    }

    @Test
    public void shouldChainDiscounters() {
        final Function<BigDecimal, BigDecimal> combinedDiscounters = christmas()
          .andThen(newYear());

        combinedDiscounters.apply(BigDecimal.valueOf(100));
    }
}
