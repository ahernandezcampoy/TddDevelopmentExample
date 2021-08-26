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
    void cannotIngressNegativeQuantities() {
        ingressQuantityInEmptyAccountCheckBalance(new BigDecimal("-100"), new BigDecimal("0"));
    }

    /*
     * This test worked directly (without code modifications) because we have directly defined
     * the balance as a BigDecimal instead of int. So we went (unconsciously) beyond was requested since the beginning
     */
    @Test
    void ingressOkWith2DecimalPlaces() {
        ingressQuantityInEmptyAccountCheckBalance(new BigDecimal("100.45"), new BigDecimal("100.45"));
    }

    @Test
    void ingressNotOkWith3DecimalPlaces() {
        ingressQuantityInEmptyAccountCheckBalance(new BigDecimal("100.457"), new BigDecimal("0"));
    }

    /*
     * This test doesn't adds any new functionality, so we could delete it
     */
    @Test
    void ingressMaxQuantityAllowed() {
        ingressQuantityInEmptyAccountCheckBalance(new BigDecimal("6000.00"), new BigDecimal("6000.00"));
    }

    @Test
    void ingressMoreThanMaxQuantityAllowed() {
        ingressQuantityInEmptyAccountCheckBalance(new BigDecimal("6000.01"), new BigDecimal("0"));
    }

    @Test
    void ingressMoreThanMaxQuantityAllowedInNonEmptyAccount() {
        // Arrange
        Account account = new Account();
        account.doIngress(new BigDecimal("2350"));

        // Act
        account.doIngress(new BigDecimal("7000"));

        // Assert
        Assertions.assertEquals(new BigDecimal("2350"), account.getBalance());
    }

    private void ingressQuantityInEmptyAccountCheckBalance(BigDecimal quantity, BigDecimal balance) {
        // Arrange
Account account = new Account();

        // Act
        account.doIngress(quantity);

        // Assert
        Assertions.assertEquals(balance, account.getBalance());
    }

    @Test
    void withdrawal100WithBalance500MakesBalance400() {
        withdrawQuantityInAccountCheckBalance(new BigDecimal("100"), new BigDecimal("500"), new BigDecimal("400"));
    }

    @Test
    void withdrawal200WithBalance900MakesBalance700() {
        withdrawQuantityInAccountCheckBalance(new BigDecimal("200"), new BigDecimal("900"), new BigDecimal("700"));
    }

    @Test
    void doubleWithdrawalWithBalance1200MakesFinalBalance850() {
        // Arrange
        Account account = new Account();
        account.doIngress(new BigDecimal("1200"));

        // Act
        account.doWithdrawal(new BigDecimal("200"));

        // Assert
        Assertions.assertEquals(new BigDecimal("1000"), account.getBalance());

        // Act
        account.doWithdrawal(new BigDecimal("150"));

        // Assert
        Assertions.assertEquals(new BigDecimal("850"), account.getBalance());
    }

    @Test
    void withdrawal500WithBalance200MakesBalance200() {
        withdrawQuantityInAccountCheckBalance(new BigDecimal("500"), new BigDecimal("200"), new BigDecimal("200"));
    }

    @Test
    void cannotWithdrawNegativeQuantities() {
        // Arrange
        Account account = new Account();
        account.doIngress(new BigDecimal("500"));

        // Act
        account.doWithdrawal(new BigDecimal("-100"));

        // Assert
        Assertions.assertEquals(new BigDecimal("500"), account.getBalance());
    }

    @Test
    void withdrawOkWith2DecimalPlaces() {
        withdrawQuantityInAccountCheckBalance(new BigDecimal("100.45"), new BigDecimal("500"), new BigDecimal("399.55"));
    }

    @Test
    void withdrawNotOkWith3DecimalPlaces() {
        withdrawQuantityInAccountCheckBalance(new BigDecimal("100.457"), new BigDecimal("500"), new BigDecimal("500"));
    }

    @Test
    void withDrawMaxQuantityAllowed() {
        // Arrange
        Account account = new Account();
        account.doIngress(new BigDecimal("3000"));
        account.doIngress(new BigDecimal("4000"));

        // Act
        account.doWithdrawal(new BigDecimal("6000.00"));

        // Assert
        Assertions.assertEquals(new BigDecimal("1000.00"), account.getBalance());
    }

    @Test
    void withdrawMoreThanMaxQuantityAllowed() {
        // Arrange
        Account account = new Account();
        account.doIngress(new BigDecimal("3000"));
        account.doIngress(new BigDecimal("4000"));

        // Act
        account.doWithdrawal(new BigDecimal("6000.01"));

        // Assert
        Assertions.assertEquals(new BigDecimal(7000), account.getBalance());
    }

    private void withdrawQuantityInAccountCheckBalance(BigDecimal quantity, BigDecimal startingBalance, BigDecimal endingBalance) {
        // Arrange
        Account account = new Account();
        account.doIngress(startingBalance);

        // Act
        account.doWithdrawal(quantity);

        // Assert
        Assertions.assertEquals(endingBalance, account.getBalance());
    }

    @Test
    void transfer100WithBalance500ToAccountWithBalance50MakesBalances400And150() {
        // Arrange
        Account accountA = new Account();
        accountA.doIngress(new BigDecimal("500"));
        Account accountB = new Account();
        accountB.doIngress(new BigDecimal("50"));

        // Act
        accountA.doTransfer(accountB, new BigDecimal("100"));

        // Assert
        Assertions.assertEquals(new BigDecimal("400"), accountA.getBalance());
        Assertions.assertEquals(new BigDecimal("150"), accountB.getBalance());
    }

    @Test
    void transfer200WithBalance700ToAccountWithBalance150MakesBalances500And350() {
        // Arrange
        Account accountA = new Account();
        accountA.doIngress(new BigDecimal("700"));
        Account accountB = new Account();
        accountB.doIngress(new BigDecimal("150"));

        // Act
        accountA.doTransfer(accountB, new BigDecimal("200"));

        // Assert
        Assertions.assertEquals(new BigDecimal("500"), accountA.getBalance());
        Assertions.assertEquals(new BigDecimal("350"), accountB.getBalance());
    }

    @Test
    void cannotTransferNegativeQuantity() {
        // Arrange
        Account accountA = new Account();
        accountA.doIngress(new BigDecimal("500"));
        Account accountB = new Account();
        accountB.doIngress(new BigDecimal("50"));

        // Act
        accountA.doTransfer(accountB, new BigDecimal("-100"));

        // Assert
        Assertions.assertEquals(new BigDecimal("500"), accountA.getBalance());
        Assertions.assertEquals(new BigDecimal("50"), accountB.getBalance());
    }

    @Test
    void transfer3000WithBalance3500ToAccountWithBalance50MakesBalances500And3050() {
        // Arrange
        Account accountA = new Account();
        accountA.doIngress(new BigDecimal("3500"));
        Account accountB = new Account();
        accountB.doIngress(new BigDecimal("50"));

        // Act
        accountA.doTransfer(accountB, new BigDecimal("3000"));

        // Assert
        Assertions.assertEquals(new BigDecimal("500"), accountA.getBalance());
        Assertions.assertEquals(new BigDecimal("3050"), accountB.getBalance());
    }

    @Test
    void transferMoreThanMaxQuantityAllowed() {
        // Arrange
        Account accountA = new Account();
        accountA.doIngress(new BigDecimal("3500"));
        Account accountB = new Account();
        accountB.doIngress(new BigDecimal("50"));

        // Act
        accountA.doTransfer(accountB, new BigDecimal("3000.01"));

        // Assert
        Assertions.assertEquals(new BigDecimal("3500"), accountA.getBalance());
        Assertions.assertEquals(new BigDecimal("50"), accountB.getBalance());
    }

    @Test
    void transferMoreThanMaxQuantityPerDayAllowed() {
        // Arrange
        Account accountA = new Account();
        accountA.doIngress(new BigDecimal("3500"));
        Account accountB = new Account();
        accountB.doIngress(new BigDecimal("50"));

        // Act
        accountA.doTransfer(accountB, new BigDecimal("2000"));
        accountA.doTransfer(accountB, new BigDecimal("1200"));

        // Assert
        Assertions.assertEquals(new BigDecimal("1500"), accountA.getBalance());
        Assertions.assertEquals(new BigDecimal("2050"), accountB.getBalance());
    }

    @Test
    void transferOkWith2DecimalPlaces() {
        // Arrange
        Account accountA = new Account();
        accountA.doIngress(new BigDecimal("500"));
        Account accountB = new Account();
        accountB.doIngress(new BigDecimal("50"));

        // Act
        accountA.doTransfer(accountB, new BigDecimal("100.45"));

        // Assert
        Assertions.assertEquals(new BigDecimal("399.55"), accountA.getBalance());
        Assertions.assertEquals(new BigDecimal("150.45"), accountB.getBalance());
    }

    @Test
    void transferNotOkWith3DecimalPlaces() {
        // Arrange
        Account accountA = new Account();
        accountA.doIngress(new BigDecimal("500"));
        Account accountB = new Account();
        accountB.doIngress(new BigDecimal("50"));

        // Act
        accountA.doTransfer(accountB, new BigDecimal("100.457"));

        // Assert
        Assertions.assertEquals(new BigDecimal("500"), accountA.getBalance());
        Assertions.assertEquals(new BigDecimal("50"), accountB.getBalance());
    }

}
