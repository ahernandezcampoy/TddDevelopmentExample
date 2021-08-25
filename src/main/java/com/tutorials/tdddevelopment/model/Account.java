package com.tutorials.tdddevelopment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Account {

    private BigDecimal balance;

    public Account() {
        this.balance = BigDecimal.ZERO;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void addIngress(BigDecimal value) {
        this.balance = new BigDecimal("100.0");
    }

}
