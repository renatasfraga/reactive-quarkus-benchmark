package edu.fraga.quarkus.domain;

import edu.fraga.quarkus.exception.InvalidInvestmentException;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public class Investment {

    private final String type;

    private final BigDecimal amount;

    private final LocalDate putMoneyDate;

    public void validate() {
       if (amount.compareTo(new BigDecimal("0.50")) < 0) {
           throw new InvalidInvestmentException("investment cannot be less than U$ 0.50");
       }

       if (putMoneyDate.isAfter(LocalDate.now())) {
           throw new InvalidInvestmentException("put money date cannot be greater than today");
       }
    }
}
