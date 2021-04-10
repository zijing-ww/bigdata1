package com.yc.net.bean1;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * program:bigdata1
 * description:URLConnection
 * author:lsj
 * create:2021-01-29 12:36
 */
public class Test7URLConnection {
    public static void main(String[] args) throws Exception {
        String urlstr;
        if(args.length==1){
            urlstr = args[0];
        }else{
            urlstr = "http://www.baidu.com/index.html";
        }
        URL u = new URL(urlstr);
        System.out.println(u.getProtocol()+"\t"+u.getHost()+"\t"+u.getPort()+"\t"+u.getDefaultPort());

        //方案1
        /*
        URLConnection con = u.openConnection();
        System.out.println(con.getHeaderFields()+"\t"+con.getContentEncoding());
        InputStream iis = con.getInputStream();
        byte [] bs = genByteArray(iis);
        String str = new String(bs,"gbk");
        System.out.println(str);*/
        //方案2
        HttpURLConnection con2 = (HttpURLConnection) u.openConnection();
        System.out.println(con2.getContentEncoding()+"\t"+con2.getContentLength());
        System.out.println(con2.getResponseCode()+"\t"+con2.getResponseMessage());
        InputStream iis = con2.getInputStream();
        byte [] bs = genByteArray(iis);
        String str = new String(bs,"gbk");
        System.out.println(str);
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
