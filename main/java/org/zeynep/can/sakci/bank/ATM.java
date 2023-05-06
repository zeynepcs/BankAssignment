package org.zeynep.can.sakci.bank;

import java.util.ArrayList;
import java.util.Scanner;

public class ATM {

    private ArrayList<User> userList;
    private ArrayList<Konto> kontoList;
    private String name;
    public ATM () {
        this.name = name;

        userList = new ArrayList<User>();
        kontoList = new ArrayList<Konto>();
    }
    public User addUser (String username, String password) {
    User newUser = new User(username,password);
    this.userList.add(newUser);

    Konto newKonto = new Konto("Default Account", newUser, 0 );
    newUser.addAccount(newKonto);
    this.kontoList.add(newKonto);

    Konto newKonto1 = new Konto("Saving Account", newUser, 0 );
    newUser.addAccount(newKonto1);
    this.kontoList.add(newKonto1);
    return newUser;
 }

    public ATM run(){
        Scanner scn = new Scanner(System.in);
        ATM StadBank = new ATM();
        User user1 = StadBank.addUser("Zeynep Can Sakci", "1234");
        User user2 = StadBank.addUser("Per Steinberg", "8906");


        User tUser;

        while (true) {
           tUser = ATM.login(StadBank, scn);
            ATM.usermenu(tUser, scn);
        }
    }
    public static User login ( ATM StadBank, Scanner scn ) {

        User authUser;

        do{
            authUser = loginUser(StadBank, scn);

            if (authUser == null) {
                System.out.println("Felaktig användarenamn eller lösenord." +
                        "Försök igen!");
            }
        }while(authUser==null);
        return authUser;
    }

    public static void usermenu (User tUser, Scanner scn  ){

        int chc;

        do{
            chc = usermenu(scn);

        }while(chc<1 || chc>4);

        switch(chc){

            case 1:
                sammandrag(tUser);
                break;

            case 2:
                int knt = depositmenu(tUser, scn);

                switch (knt){

                    case 1:
                        depositkonto1(tUser, scn);
                        break;

                    case 2:
                        depositkonto2(tUser, scn);
                        break;
                }
                chc = quit(tUser, scn);
                break;

            case 3:
                int wtr = withdrawmenu(tUser, scn);

                switch (wtr){

                    case 1:
                        withdrawkonto1(tUser, scn);
                        break;

                    case 2:
                        withdrawkonto2(tUser, scn);
                        break;
                }
                chc = quit(tUser, scn);
                break;

            case 4:
                scn.nextLine();
                break;
        }
        if(chc !=4) {
            ATM.usermenu(tUser,scn);
        }
    }

    private static void withdrawkonto2(User tUser, Scanner scn) {
        System.out.println("-----------------------------");
        System.out.println("1 Default Account:" + tUser.CheckAccountBalance(1) + "SEK");
        System.out.println("Enter an amount to withdraw: ");
        double rem2 = scn.nextInt();
        double currentAmount = tUser.CheckAccountBalance(1);
        if(currentAmount<rem2){
            System.out.println("Not enough money in account. Only " + currentAmount);
            return;

        }else if(rem2<0){
            System.out.println("Incorrect amount..");
            return;
        }
        tUser.withdrawMoney(1, rem2);
        System.out.println("1 Default Account become:" + tUser.CheckAccountBalance(1) + "SEK");
        System.out.println("-----------------------------");
    }

    private static  void withdrawkonto1(User tUser, Scanner scn) {
        System.out.println("-----------------------------");
        System.out.println("1 Default Account:" + tUser.CheckAccountBalance(0) + "SEK");
        System.out.println("Enter an amount to withdraw: ");
        double rem = scn.nextInt();
        double currentAmount = tUser.CheckAccountBalance(0);
        if(currentAmount<rem){
            System.out.println("Not enough money in account. Only " + currentAmount);

            return;

        }else if(rem<0){
            System.out.println("Incorrect amount..");
            return;
        }
        tUser.withdrawMoney(0, rem);
        System.out.println("1 Default Account become:" + tUser.CheckAccountBalance(0) + "SEK");
        System.out.println("-----------------------------");
        return;
    }

    private static int withdrawmenu(User tUser, Scanner scn) {
        System.out.println("-----------------------------");
        System.out.println("WITHDRAW MENU :");
        tUser.printAccountSummary();
        System.out.println("Choose account to withdraw: ");
        int wtr = scn.nextInt();
        return wtr;
    }

    private static int quit(User tUser, Scanner scn) {
        int chc;
        System.out.println("-----------------------------");
        System.out.println("Enter:4 - Quit");
        System.out.println("-----------------------------");
        chc = scn.nextInt();
        ATM.usermenu(tUser, scn);
        return chc;
    }

    private static void depositkonto2(User tUser, Scanner scn) {
        System.out.println("-----------------------------");
        System.out.println("1 Default Account:" + tUser.CheckAccountBalance(1) + "SEK");
        System.out.println("Enter an amount to add: ");
        double add2 = scn.nextInt();

        if(1000<add2){
            System.out.println("You cant add this amount. Only " + "   "  + "Maximum:" + "1000 SEK Daily");
            return;

        }
        else if(add2<0){
            System.out.println("Incorrect amount..");
            return;
        }
        tUser.depositMoney(1, add2);
        System.out.println("1 Default Account become :" + tUser.CheckAccountBalance(1) + "SEK");
        System.out.println("-----------------------------");
    }

    private static void depositkonto1(User tUser, Scanner scn) {
        System.out.println("-----------------------------");
        System.out.println("1 Default Account:" + tUser.CheckAccountBalance(0) + "SEK");
        System.out.println("Enter an amount to add: ");
        double add = scn.nextInt();


        if(1000<add){
            System.out.println("You cant add this amount. Only " + "   "  + "Maximum:" + "1000 SEK Daily");
            return;

        }
        else if(add<0){
            System.out.println("Incorrect amount..");
            return;
        }
        tUser.depositMoney(0, add);
        System.out.println("1 Default Account become:" + tUser.CheckAccountBalance(0) + "SEK");
        System.out.println("-----------------------------");
        return;
    }

    private static int depositmenu(User tUser, Scanner scn) {
        System.out.println("-----------------------------");
        System.out.println("DEPOSIT MENU :");
        tUser.printAccountSummary();
        System.out.println("Choose the account to deposit: ");
        int knt = scn.nextInt();
        System.out.println("-----------------------------");
        return knt;
    }

    private static void sammandrag(User tUser) {
        System.out.println("Välkomen Till Stad Bank----");
        System.out.println("Du har två konton: " + tUser.numAccounts() );
        tUser.printAccountSummary();
        System.out.println("-----------------------------");
    }

    private static int usermenu(Scanner scn) {
        int chc;
        System.out.println("-----------------------------");
        System.out.println("Vad skulle du vilja göra?");
        System.out.println("1 - Konto sammandrag");
        System.out.println("2 - Deposit");
        System.out.println("3 - Withdraw");
        System.out.println("4 - Quit");
        System.out.println();
        System.out.println("Enter the choice: ");
        chc = scn.nextInt();
        System.out.println("-----------------------------");
        return chc;
    }

    private static User loginUser(ATM StadBank, Scanner scn) {
        String password;
        User authUser;
        String username;
        System.out.println("-----------------------------");
        System.out.println("-----------------------------");
        System.out.println("\n\nVälkommen till Stadbank!\n\n");
        System.out.print("Skriv in ditt användarnamn : ");
        username = scn.nextLine();
        System.out.println();
        System.out.print("Skriv in ditt password: ");
        password = scn.nextLine();
        System.out.println("-----------------------------");
        System.out.println("-----------------------------");
        authUser = StadBank.Login(username,password);
        return authUser;
    }


    public User Login(String username, String password){
            for(User t:this.userList){
            if(t.getUsername().equals(username) && t.getPassword().equals(password)){
                return t;
            }
        }
        return null;
    }
}