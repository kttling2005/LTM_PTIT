package javaapplication1;

import java.io.*;
import java.net.*;
import java.util.*;
import TCP.Customer;

public class JavaApplication1 {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
       Socket socket = new Socket("36.50.135.242",2209);
       ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
       ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
       
       oos.writeObject("B23DCCN483;mnsRWC2D");
       oos.flush();
       
       Customer customer = (Customer) ois.readObject();
       
       String[] name = customer.getName().trim().split("\\s+");
       
       String newName = name[name.length-1].toUpperCase()+", ";
       
       if(name.length>=2)
       {
           for(int i = 0; i < name.length - 1; i++){
               newName = newName + name[i].substring(0,1).toUpperCase() + name[i].substring(1).toLowerCase();
               if(i<name.length-2) newName+=" ";
           }
       }
       
       customer.setName(newName);
       
       System.out.println(newName);
       
       String[] DoB = customer.getDayOfBirth().trim().split("-");
       String tmp = DoB[0];
       DoB[0] = DoB[1];
       DoB[1] = tmp;
       String newDoB = String.join("/", DoB);
       
       customer.setDayOfBirth(newDoB);
       System.out.println(newDoB);
       
       String newUserName = "";
       if(name.length>=2){
           for(int i = 0; i < name.length-1; i++){
               newUserName += name[i].substring(0,1).toLowerCase();
           }
       }
       newUserName+=name[name.length-1].toLowerCase();
       
       customer.setUserName(newUserName);
       
       System.out.println(customer.getUserName());
       
       oos.writeObject(customer);
       oos.flush();
       oos.close();
       ois.close();
       socket.close();
    }
}
