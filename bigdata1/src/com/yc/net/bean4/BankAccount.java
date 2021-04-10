package com.yc.net.bean4;

/**
 * program:bigdata1
 * description:银行账户类
 * author:lsj
 * create:2021-03-08 22:50
 */
public class BankAccount {
    private int id;
    private double balance;

    public BankAccount(int id,double balance){
        this.id = id;
        this.balance = balance;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "id=" + id +
                ", balance=" + balance +
                '}';
    }

    public int getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }
}
