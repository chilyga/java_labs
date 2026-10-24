package edu.course.lab02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void createsAccountWithValidBalance() {
        BankAccount account = new BankAccount(100);

        assertEquals(100, account.getBalance());
    }

    @Test
    void rejectsNegativeInitialBalance() {
        assertThrows(IllegalArgumentException.class,
                () -> new BankAccount(-1));
    }

    @Test
    void depositsMoney() {
        BankAccount account = new BankAccount(100);

        account.deposit(50);

        assertEquals(150, account.getBalance());
    }

    @Test
    void rejectsNonPositiveDeposit() {
        BankAccount account = new BankAccount(100);

        assertThrows(IllegalArgumentException.class,
                () -> account.deposit(0));
    }

    @Test
    void withdrawsMoneySuccessfully() {
        BankAccount account = new BankAccount(100);

        account.withdraw(40);

        assertEquals(60, account.getBalance());
    }

    @Test
    void rejectsWithdrawalExceedingBalance() {
        BankAccount account = new BankAccount(100);

        assertThrows(IllegalArgumentException.class,
                () -> account.withdraw(150));

        assertEquals(100, account.getBalance());
    }

    @Test
    void rejectsNonPositiveWithdrawal() {
        BankAccount account = new BankAccount(100);

        assertThrows(IllegalArgumentException.class,
                () -> account.withdraw(0));
    }
}
