package com.tutorials.tdddevelopment.model;

import java.math.BigDecimal;

public class Account {

    private BigDecimal balance;

    public Account() {
        this.balance = BigDecimal.ZERO;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void doIngress(BigDecimal value) {
        if(value.signum() >= 0) {
            this.balance = this.balance.add(value);
        } else {
            this.balance = BigDecimal.ZERO;
        }
    }

}
