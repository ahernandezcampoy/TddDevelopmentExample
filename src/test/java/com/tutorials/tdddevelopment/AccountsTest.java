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
        ingressQuantityInEmptyAccountCheckBalance(new BigDecimal("100"), new BigDecimal("100"));
    }

    @Test
    void ingress3000InEmptyAccountBalanceIs3000() {
        ingressQuantityInEmptyAccountCheckBalance(new BigDecimal("3000"), new BigDecimal("3000"));
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
        account.doIngress(new BigDecimal("100"));

        // Act
        account.doIngress(new BigDecimal("3000"));

        // Assert
        Assertions.assertEquals(new BigDecimal("3100"), account.getBalance());
    }

    @Test
    void cannotIngressNegativeImports() {
        ingressQuantityInEmptyAccountCheckBalance(new BigDecimal("-100"), new BigDecimal("0"));
    }

    /*
    * This test worked directly (without code modifications) because we have directly defined
    * the balance as a BigDecimal instead of int. So we went (unconsciously) beyond was requested since the beginning
    */
    @Test
    void ingressOkWith2Decimals() {
        ingressQuantityInEmptyAccountCheckBalance(new BigDecimal("100.45"), new BigDecimal("100.45"));
    }

    private void ingressQuantityInEmptyAccountCheckBalance(BigDecimal quantity, BigDecimal balance) {
        // Arrange
        Account account = new Account();

        // Act
        account.doIngress(quantity);

        // Assert
        Assertions.assertEquals(balance, account.getBalance());
    }

}
