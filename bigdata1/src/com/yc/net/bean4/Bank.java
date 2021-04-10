package com.yc.net.bean4;

import java.util.ArrayList;
import java.util.List;

/**
 * program:bigdata1
 * description:银行类
 * author:lsj
 * create:2021-03-08 22:43
 */
public class Bank {
    private List<BankAccount> bas = new ArrayList<BankAccount>();

    public Bank(){
        for(int i=1;i<10;i++){
            bas.add(new BankAccount(i,10));
        }
    }
    public BankAccount search(int id){
        BankAccount ba = null;
        for(BankAccount t:bas){
            if(t.getId()==id){
                ba = t;
                break;
            }
        }
        if(ba==null){
            throw new RuntimeException("bankaccount"+id+"does not exists");
        }
        return ba;
    }
    public BankAccount deposite(int id,double money){
        BankAccount ba = search(id);
        synchronized (this){
            ba.setBalance(ba.getBalance()+money);
        }
        return ba;
    }
    public BankAccount withdraw(int id,double money){
        BankAccount ba = search(id);
        if(ba.getBalance()<money){
            throw new RuntimeException("bankaccount"+id+"does not have enought money");
        }
        synchronized (this){
            ba.setBalance(ba.getBalance()-money);
        }
        return ba;
    }
}
