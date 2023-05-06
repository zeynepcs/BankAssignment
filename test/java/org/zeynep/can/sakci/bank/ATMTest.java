package org.zeynep.can.sakci.bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ATMTest {

private ATM Stadbank;

@BeforeEach

public void setup () {

   Stadbank = new ATM();

}



    @Test
    void testLogIn_CorrectNameAndCorrectPassword() {

        User user1 = Stadbank.addUser("Test", "1234");

        assertEquals("Test", user1.getUsername());
        assertEquals("1234", user1.getPassword());

    }

    @Test
    void testLogIn_CorrectNameAndIncorrectPassword() {
        User user1 = Stadbank.addUser("Test", "1234");

        assertEquals(null, Stadbank.Login(user1.getUsername(), "fakepasswd"));

    }

     @Test
     void testLogin_UserDoesNotExist(){
         User user1 = Stadbank.addUser("Test", "1234");

    assertEquals(null, Stadbank.Login("fake", user1.getPassword()));

}

    @Test
    void checkAccountBalance() {
        User user1 = Stadbank.addUser("Test", "1234");

        assertEquals(500, user1.CheckAccountBalance(0));
    }



    @Test
    void withdrawMoney() {
        //each user has by default 500 in their both saving and default account
        User user1 = Stadbank.addUser("Test", "1234");

        System.out.println("current account balance:" + user1.CheckAccountBalance(0));

        double rem = 400;
        user1.withdrawMoney(0, rem);
        assertEquals(100, user1.CheckAccountBalance(0));
    }

    @Test
    void WithdrawMoneyMoreThanBalance(){
        //each user has by default 500 in their both saving and default account
        //If you want to take more, this will not be reduced from account
        User user1 = Stadbank.addUser("Test", "1234");


        double rem = 600;
        assertNull(user1.withdrawMoney(0, rem));

    }

    @Test
    void WithdrawMoneyIncorrectAmount(){

        //each user has by default 500 in their both saving and default account

        User user1 = Stadbank.addUser("Test", "1234");

        double rem = -50;

        assertNull(user1.withdrawMoney(0, rem));
    }


    @Test
    void depositMoney() {
        //each user has by default 500 in their both saving and default account
        User user1 = Stadbank.addUser("Test", "1234");
        System.out.println("Before balance:" + user1.CheckAccountBalance(0));

        double add = 400;
       user1.depositMoney(0, add);
        assertEquals(900, user1.CheckAccountBalance(0));

        System.out.println("New balance:" + user1.CheckAccountBalance(0));

    }


    @Test
    void depositMoneyinccorectnumber() {
        //each user has by default 500 in their both saving and default account
        User user1 = Stadbank.addUser("Test", "1234");

        double add = -400;
        assertNull(user1.depositMoney(0, add));

    }


    @Test
    void depositMoneyExceedLimit() {
        //Max limit is 1000
        User user1 = Stadbank.addUser("Test", "1234");

        double add = 1500;
        assertNull(user1.depositMoney(0, add));

    }

}