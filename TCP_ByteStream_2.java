package javaapplication1;

import java.io.*;
import java.net.*;
import java.util.*;

public class JavaApplication1 {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("36.50.135.242",2206);
        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();
        String ma = "B23DCCN483;CqQQD4St";
        out.write(ma.getBytes());
        out.flush();
        
        byte buffer[] = new byte[1024];
        int len = in.read(buffer);
        String s = new String(buffer,0,len);
        s = s.trim();
        String a[] = s.split(",");
        
        ArrayList<Long> b = new ArrayList<>();
        for(String x: a){
            b.add(Long.parseLong(x));
        }
        
        Long a1 = 1L*(-1);//so lon nhat
        Long a2 = 1L*(-1);//so lon thu hai 
        Long res = 0L;
        
        for(int i = 0; i < b.size(); i++)//tim so lon nhat
            if(b.get(i)>a1) a1 = b.get(i);
        
        for(int i = 0; i < b.size(); i++)
            if((b.get(i)>a2)&&(b.get(i)<a1)){
                res = i*1L;
                a2 = b.get(i);
            }
        
        String ans = a2+","+res;
        System.out.println(ans);
        out.write(ans.getBytes());
        out.flush();
        
        in.close();
        out.close();
        socket.close();
    }
}
