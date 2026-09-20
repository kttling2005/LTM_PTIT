package javaapplication1;

import java.io.*;
import java.net.*;
import java.util.*;

public class JavaApplication1 {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("36.50.135.242",2208);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        out.write("B23DCCN483;ffYckyPF");
        out.newLine();
        out.flush();
        
        String s = in.readLine();
        s = s.trim();
        
        String a[] = s.split(",");
        StringBuilder b = new StringBuilder();
        
        for(String x: a){
            if(x.endsWith(".edu"))
            {
                if(b.length()>0) b.append(",");
                b.append(x);
            }
        }
        System.out.println(b);
        out.write(b.toString());
        out.newLine();
        out.flush();
        
        in.close();
        out.close();
        socket.close();
        
    }
}
