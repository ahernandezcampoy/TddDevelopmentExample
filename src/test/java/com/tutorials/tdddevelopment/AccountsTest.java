package com.tutorials.tdddevelopment;

import com.tutorials.tdddevelopment.model.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class AccountsTest extends TddDevelopmentApplicationTests {

    @Test
    void createNewAccountWithZeroBalance() {
        Account account = new Account();
        Assertions.assertEquals(BigDecimal.ZERO, account.getBalance());
    }

    @Test
    void ingress100InEmptyAccountBalanceIs100() {
        Account account = new Account();
        account.doIngress(new BigDecimal("100.0"));

        Assertions.assertEquals(new BigDecimal("100.0"), account.getBalance());
    }

    @Test
    void ingress3000InEmptyAccountBalanceIs3000() {
        Account account = new Account();
        account.doIngress(new BigDecimal("3000.0"));

        Assertions.assertEquals(new BigDecimal("3000.0"), account.getBalance());
    }

}
