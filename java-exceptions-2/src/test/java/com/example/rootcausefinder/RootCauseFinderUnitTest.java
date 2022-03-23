package com.example.rootcausefinder;

import com.example.rootCauseFinder.RootCauseFinder;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.assertj.core.util.Throwables;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests the {@link RootCauseFinder}.
 */
public class RootCauseFinderUnitTest {

    @Test
    public void givenBirthDate_whenCalculatingAge_thenAgeReturned() {
        try {
            int age = RootCauseFinder.AgeCalculator.calculateAge("1990-01-01");
            Assertions.assertEquals(1990, LocalDate
              .now()
              .minus(age, ChronoUnit.YEARS)
              .getYear());
        } catch (RootCauseFinder.CalculationException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    public void givenWrongFormatDate_whenFindingRootCauseUsingJava_thenRootCauseFound() {
        try {
            RootCauseFinder.AgeCalculator.calculateAge("010102");
        } catch (RootCauseFinder.CalculationException ex) {
            assertTrue(RootCauseFinder.findCauseUsingPlainJava(ex) instanceof DateTimeParseException);
        }
    }

    @Test
    public void givenOutOfRangeDate_whenFindingRootCauseUsingJava_thenRootCauseFound() {
        try {
            RootCauseFinder.AgeCalculator.calculateAge("2020-04-04");
        } catch (RootCauseFinder.CalculationException ex) {
            assertTrue(RootCauseFinder.findCauseUsingPlainJava(ex) instanceof RootCauseFinder.DateOutOfRangeException);
        }
    }

    @Test
    public void givenNullDate_whenFindingRootCauseUsingJava_thenRootCauseFound() {
        try {
            RootCauseFinder.AgeCalculator.calculateAge(null);
        } catch (Exception ex) {
            assertTrue(RootCauseFinder.findCauseUsingPlainJava(ex) instanceof IllegalArgumentException);
        }
    }

    @Test
    public void givenWrongFormatDate_whenFindingRootCauseUsingApacheCommons_thenRootCauseFound() {
        try {
            RootCauseFinder.AgeCalculator.calculateAge("010102");
        } catch (RootCauseFinder.CalculationException ex) {
            assertTrue(ExceptionUtils.getRootCause(ex) instanceof DateTimeParseException);
        }
    }

    @Test
    public void givenOutOfRangeDate_whenFindingRootCauseUsingApacheCommons_thenRootCauseFound() {
        try {
            RootCauseFinder.AgeCalculator.calculateAge("2020-04-04");
        } catch (RootCauseFinder.CalculationException ex) {
            assertTrue(ExceptionUtils.getRootCause(ex) instanceof RootCauseFinder.DateOutOfRangeException);
        }
    }

    @Test
    public void givenWrongFormatDate_whenFindingRootCauseUsingGuava_thenRootCauseFound() {
        try {
            RootCauseFinder.AgeCalculator.calculateAge("010102");
        } catch (RootCauseFinder.CalculationException ex) {
            assertTrue(Throwables.getRootCause(ex) instanceof DateTimeParseException);
        }
    }

    @Test
    public void givenOutOfRangeDate_whenFindingRootCauseUsingGuava_thenRootCauseFound() {
        try {
            RootCauseFinder.AgeCalculator.calculateAge("2020-04-04");
        } catch (RootCauseFinder.CalculationException ex) {
            assertTrue(Throwables.getRootCause(ex) instanceof RootCauseFinder.DateOutOfRangeException);
        }
    }

}
