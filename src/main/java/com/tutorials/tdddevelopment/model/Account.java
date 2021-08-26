package com.tutorials.tdddevelopment.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Account {

    private final BigDecimal maxIngressWithdrawQuantity = new BigDecimal("6000");
    private final BigDecimal maxTransferQuantity = new BigDecimal("3000");

    private BigDecimal balance;
    private BigDecimal transferredInDay;

    public Account() {
        this.balance = BigDecimal.ZERO;
        this.transferredInDay = BigDecimal.ZERO;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void setBalance(BigDecimal value) {
        this.balance = value;
    }

    public void doIngress(BigDecimal quantity) {
        if (isValidQuantityToIngress(quantity)) {
            this.balance = this.balance.add(quantity);
        }
    }

    public void doWithdrawal(BigDecimal quantity) {
        if (isValidQuantityToWithdraw(quantity)) {
            this.balance = this.balance.subtract(quantity);
        }
    }

    public void doTransfer(Account destinationAccount, BigDecimal quantity) {
        transferredInDay = transferredInDay.add(quantity);
        if (isValidQuantityToTransfer(quantity)) {
            this.doWithdrawal(quantity);
            destinationAccount.doIngress(quantity);
        } else {
            transferredInDay = transferredInDay.subtract(quantity);
        }
    }

    private boolean isValidQuantityToIngress(BigDecimal quantity) {
        return decimalPlacesAllowed(quantity)
                && quantity.signum() >= 0
                && quantity.compareTo(maxIngressWithdrawQuantity) <= 0;
    }

    private boolean isValidQuantityToWithdraw(BigDecimal quantity) {
        return quantity.compareTo(this.balance) <= 0
                && quantity.signum() >= 0
                && decimalPlacesAllowed(quantity)
                && quantity.compareTo(maxIngressWithdrawQuantity) <= 0;
    }

    private boolean decimalPlacesAllowed(BigDecimal quantity) {
        return quantity.compareTo(quantity.setScale(2, RoundingMode.CEILING)) == 0;
    }

    private boolean isValidQuantityToTransfer(BigDecimal quantity) {
        return quantity.signum() >= 0
                && quantity.compareTo(maxTransferQuantity) <= 0
                && transferredInDay.compareTo(maxTransferQuantity) <= 0;
    }

}
