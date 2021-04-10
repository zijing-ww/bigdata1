package com.yc.net.bean1;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * program:bigdata1
 * description:InetAddress
 * author:lsj
 * create:2021-01-23 15:56
 */
public class Test1InetAddress {
    public static void main(String[] args) throws UnknownHostException {
        if(args.length>0){
            String host = args[0];
            InetAddress [] addresses = InetAddress.getAllByName(host);
            for(InetAddress a:addresses){
                System.out.println(a);
            }
        }else{
            InetAddress localHostAddress = InetAddress.getLocalHost();
            System.out.println(localHostAddress);
        }
    }
}
