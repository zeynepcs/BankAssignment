package org.zeynep.can.sakci.bank;

public class Konto {
    private String kontoname;
    private double amount;
    private double amount3;
    private User holder;


    public Konto (String kontoname, User holder, int amount2 ){

        this.kontoname = kontoname;
        this.holder = holder;
        this.amount = amount2;
    }
    public String getSummaryLine(){
        double balance = this.getBalance();
        if(balance>=0){
            return String.format(this.kontoname+" : "+balance+ "   SEK" +  " : " );
        }else{
            return String.format(this.kontoname+" : "+balance+ "   SEK" +  " : "  );
        }
    }
    public double getBalance(){
        double balance = 500;
        if (amount >0)
        {
    balance += amount;
        }

        if (amount3 >0)
        {
            balance -= amount3;
        }

            return balance;
    }
        public double addmoney(double amount) {
        this.amount = amount;
        return amount;
    }
        public double remMoney(double amount3) {
        this.amount3 = amount3;
        return amount3;
    }
}