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
    void ingressQuantityInEmptyAccountAddsQuantityToAccountBalance() {
        Account account = new Account();
        account.addIngress(new BigDecimal("100.0"));

        Assertions.assertEquals(new BigDecimal("100.0"), account.getBalance());
    }

}
