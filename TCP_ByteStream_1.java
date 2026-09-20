package javaapplication1;

import java.io.*;
import java.net.*;
import java.util.*;

public class JavaApplication1 {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("36.50.135.242",2206);
        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();
        String ma = "B23DCCN483;9cblg7Tb";
        out.write(ma.getBytes());// hàm chuyển chuỗi string thành mảng Bytes.
        out.flush();
        byte buffer[] = new byte[1024];
        int len = in.read(buffer);
        String s = new String(buffer,0,len);
        s = s.trim();
        String a[] = s.split(",");
        ArrayList<Long> b = new ArrayList<>();
        for(String x: a)
        {
            b.add(Long.parseLong(x));
        }
        Long res = Long.MAX_VALUE;
        Long a1 = 1L*(-1);
        Long a2 = 1L*(-1);
        Collections.sort(b);
        for(int i = 1; i < b.size(); i++)
        {
            Long tam = b.get(i) - b.get(i-1);
            if(tam<res){
                res = tam;
                a1 = b.get(i);
                a2 = b.get(i-1);
            }
        }
        
        String ans = res +","+a2+","+a1;
        System.out.println(ans);
        out.write(ans.getBytes());
        out.flush();
        
        in.close();
        out.close();
        socket.close();
        
    }
}
