package com.tutorials.tdddevelopment;

import com.tutorials.tdddevelopment.model.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class AccountsTest extends TddDevelopmentApplicationTests {

    @Test
    void createNewAccountWithZeroBalance() {
        // Arrange

        // Act
        Account account = new Account();

        // Assert
        Assertions.assertEquals(BigDecimal.ZERO, account.getBalance());
    }

    @Test
    void ingress100InEmptyAccountBalanceIs100() {
        // Arrange
        Account account = new Account();

        // Act
        account.doIngress(new BigDecimal("100.0"));

        // Assert
        Assertions.assertEquals(new BigDecimal("100.0"), account.getBalance());
    }

    @Test
    void ingress3000InEmptyAccountBalanceIs3000() {
        // Arrange
        Account account = new Account();

        // Act
        account.doIngress(new BigDecimal("3000.0"));

        // Assert
        Assertions.assertEquals(new BigDecimal("3000.0"), account.getBalance());
    }

    @Test
    void ingress3000WithBalance100MakesAccountBalance3100() {
        // Arrange
        Account account = new Account();
        /*
            We could had make other solutions to achieve balance=100
            - new constructor with parameters
            - new method to set the balance
            Instead, we choose to use the existing method doIngress to achieve it.
            Minimum code to achieve the target.
         */
        account.doIngress(new BigDecimal("100.0"));

        // Act
        account.doIngress(new BigDecimal("3000.0"));

        // Assert
        Assertions.assertEquals(new BigDecimal("3100.0"), account.getBalance());
    }

}
