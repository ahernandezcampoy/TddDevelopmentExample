package com.tutorials.tdddevelopment.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Account {

    private BigDecimal balance;

    public Account() {
        this.balance = BigDecimal.ZERO;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void doIngress(BigDecimal value) {
        if(isValidQuantity(value)) {
            this.balance = this.balance.add(value);
        } else {
            this.balance = BigDecimal.ZERO;
        }
    }

    private boolean isValidQuantity(BigDecimal value) {
        return decimalPlacesAllowed(value)
                && value.signum() >= 0
                && value.compareTo(new BigDecimal("6000")) <= 0;
    }

    private boolean decimalPlacesAllowed(BigDecimal value) {
        return value.compareTo(value.setScale(2, RoundingMode.CEILING)) == 0;
    }

}
