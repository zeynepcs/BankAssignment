package org.zeynep.can.sakci.bank;
import java.util.ArrayList;
public class User {

    private String username;
    private String password;
    private  int balance;
    private ArrayList<Konto> userkontos;
    public User (String username, String password){
        this.username = username;
        this.password = password;
        this.userkontos= new ArrayList<Konto>();
    }
    public String getPassword() {
        return password;
    }
    public String getUsername() {
        return username;
    }
    public int numAccounts() {
        return this.userkontos.size();
    }
    public double CheckAccountBalance(int acctId) {
        return this.userkontos.get(acctId).getBalance();
    }
    public Object depositMoney(int acctId, double belopp) {

        if(belopp<0){

            return null;
        }
        else {
            this.userkontos.get(acctId).addmoney(belopp);
        }
        return null;

    }public Object withdrawMoney(int acctId, double belopp2) {

        if(belopp2<0){

            return null;
        }
        else {
        this.userkontos.get(acctId).remMoney(belopp2);
        }
        return null;
    }
    public void addAccount(Konto konto) {
        this.userkontos.add(konto);
    }
    public void printAccountSummary(){
        System.out.println(this.username+"'s account Summary :");
        for(int a = 0;a<this.userkontos.size();a++){
            System.out.println((a+1)+" "+this.userkontos.get(a).getSummaryLine());
        }
        System.out.println();
    }

}