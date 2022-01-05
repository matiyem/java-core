package com.example.debug;

import com.example.debug.entity.Customer;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class Example2 {
    @Test
    public void whenDebugging_thenInformationIsShown() {
        List<Optional<Customer>> customers = Arrays.asList(
            Optional.of(new Customer("John P.", 15)),
            Optional.of(new Customer("Sarah M.", 78)),
            Optional.empty(),
            Optional.of(new Customer("Mary T.", 20)),
            Optional.empty(),
            Optional.of(new Customer("Florian G.", 89)),
            Optional.empty()
        );

        long numberOf65PlusCustomers = customers.stream()
                //flatMap برای تبدیل کردن چندین لیست تو در تو یا استریم تو در تو به یک لیست یا استریم سطح یک.تبدیل [[]] به []
            .flatMap(c -> c.map(Stream::of)
              .orElseGet(Stream::empty))
            .mapToInt(Customer::getAge)
            .filter(c -> c > 65)
            .count();

        assertThat(numberOf65PlusCustomers).isEqualTo(2);
    }
}
