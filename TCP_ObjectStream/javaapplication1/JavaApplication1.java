package javaapplication1;
import java.io.*;
import java.net.*;
import java.util.*;
import TCP.Laptop;
public class JavaApplication1 {

    public static void main(String[] args) throws IOException,ClassNotFoundException {
        Socket socket = new Socket("36.50.135.242",2209);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        
        oos.writeObject("B23DCCN483;zPMXKwql");
        oos.flush();
        
        Laptop laptop = (Laptop) ois.readObject();
        
        String words[] = laptop.getName().trim().split("\\s+");
        for(int i = 0; i < words.length; i++){
            System.out.print(words[i]+" ");
        }
        System.out.println();
        
        String tmp = words[0];
        words[0] = words[words.length-1];
        words[words.length-1] = tmp;
        
        laptop.setName(String.join(" ", words));
        
        //sua so luong
        int quantity = laptop.getQuantity();
        int x = 0;
        while(quantity>0){
            x = x*10+quantity%10;
            quantity/=10;
        }
        System.out.print(laptop.getQuantity()+","+x);
        
        laptop.setQuantity(x);
        
        oos.writeObject(laptop);
        oos.flush();
        
        oos.close();
        ois.close();
        socket.close();
    }
}
