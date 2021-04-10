package com.yc.net.bean1;

import java.io.*;
import java.net.Socket;

/**
 * program:bigdata1
 * description:http客户端
 * author:lsj
 * create:2021-01-27 16:38
 */
public class Test6WebGetClient {
    public static void main(String[] args) throws IOException {
        String host;
        String resource;
        if(args.length == 2){
            host = args[0];
            resource = args[1];
        }else{
            host = "baidu.com";
            resource = "/index.html";
        }

        Socket s = new Socket(host,80);
        System.out.println("连接baidu服务器"+s.getRemoteSocketAddress()+"成功");
        InputStream is = s.getInputStream();
        OutputStream os = s.getOutputStream();
        PrintWriter pw = new PrintWriter(os);
        String command = "GET / HTTP/1.0";
        pw.println(command);
        pw.println("Host: www.baidu.com");
        pw.println("Connection: keep-alive");
        pw.println("Pragma: no-cache");
        pw.println("Cache-Control: no-cache");
        pw.print("\n");
        pw.flush();

        byte [] bs = genByteArray(is);
        String str = new String(bs,"gbk");
        System.out.println(str);
        s.close();
    }

    private static byte[] genByteArray(InputStream is) {
        byte [] bs = new byte[1024];
        int length = -1;
        try(ByteArrayOutputStream baos = new ByteArrayOutputStream();
            InputStream iis = is
        ){
            while ((length=iis.read(bs,0,bs.length))!=-1){
                baos.write(bs,0,length);
            }
            return baos.toByteArray();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
