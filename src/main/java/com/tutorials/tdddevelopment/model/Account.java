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
        this.balance = this.balance.add(value);
    }

}
