package edu.fraga.quarkus.domain;

import edu.fraga.quarkus.exception.InvalidInvestmentException;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("investment: all unit tests")
class InvestmentTest {


    @DisplayName("Should be return investment from valid payload")
    @Test
    void validate_WhenValidPayload_ExpectPassToValidation() {
        var investment = Investment.builder()
                .type("PIX")
                .amount(new BigDecimal("30.23"))
                .putMoneyDate(LocalDate.now())
                .build();

        assertDoesNotThrow(investment::validate);
    }

    @DisplayName("Should be return exception because investment amount is less than 0.50")
    @Test
    void validate_WhenAmountIsLessThanLimit_ExpectThrowException() {
        var investment = Investment.builder()
                .type("PIX")
                .amount(new BigDecimal("0.20"))
                .putMoneyDate(LocalDate.now())
                .build();

        assertThrows(InvalidInvestmentException.class, investment::validate);
    }

    @DisplayName("Should be return exception because putMoneyDate is greater today")
    @Test
    void validate_WhenPutMoneyDateIsGreaterThanToday_ExpectThrowException() {
        var investment = Investment.builder()
                .type("PIX")
                .amount(new BigDecimal("10.00"))
                .putMoneyDate(LocalDate.now().plusDays(1))
                .build();

        assertThrows(InvalidInvestmentException.class, investment::validate);
    }
}
