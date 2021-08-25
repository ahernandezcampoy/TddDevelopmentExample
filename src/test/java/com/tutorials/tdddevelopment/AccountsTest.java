package com.tutorials.tdddevelopment;

import com.tutorials.tdddevelopment.model.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class AccountsTest {

    @Test
    void createNewAccountWithZeroBalance() {
        Account account = new Account();
        Assertions.assertEquals(BigDecimal.ZERO, account.getBalance());
    }

}
