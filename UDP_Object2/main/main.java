package com.mycompany.db_exercises;

import UDP.Customer;
import java.io.*;
import java.nio.*;
import java.net.*;
import java.util.*;

public class Db_exercises {

    public static void main(String[] args) throws IOException, ClassNotFoundException{
        try{
            DatagramSocket socket = new DatagramSocket(2209);
            InetAddress serverAddress = InetAddress.getByName("36.50.135.242");
            
            //gui du lieu
            String message = ";B23DCCN483;jJM9OCzK";
            byte[] data = message.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(
            data,
            data.length,
            serverAddress,
            2209);
            socket.send(sendPacket);
            
            //nhan du lieu
            byte[] receivedData = new byte[4096];
            DatagramPacket receivedPacket = new DatagramPacket(
            receivedData,
            receivedData.length);
            socket.receive(receivedPacket);
            
            String requestId = new String(
            receivedPacket.getData(),
            0,
            8);
            
            System.out.println("Server response: "+ requestId);
            
            ByteArrayInputStream bais = new ByteArrayInputStream(
            receivedPacket.getData(),
            8,
            receivedPacket.getLength()-8);
            
            ObjectInputStream ois = new ObjectInputStream(bais);
            
            Customer customer = (Customer) ois.readObject();
            System.out.println("Server response: "+ customer.show());
            ois.close();
            
            //xu ly
            String[] names = customer.getName().split("\\s+");
            String newName = names[names.length - 1].toUpperCase();
            if(names.length>1) newName+=",";
            
            for(int i = 0; i < names.length - 1; i++){
                newName = newName+" "+names[i].substring(0,1).toUpperCase()+names[i].substring(1).toLowerCase();
            }
            
            customer.setName(newName);
            
            String[] dob = customer.getDayOfBirth().split("-");
            String newDob = dob[1]+"/"+dob[0]+"/"+dob[2];
            
            customer.setDayOfBirth(newDob);
            
            String userName = "";
            for(int i = 0; i < names.length-1; i++){
                userName += names[i].substring(0,1).toLowerCase();
            }
            userName+=names[names.length-1].toLowerCase();
            customer.setUserName(userName);
            
            System.out.println("Sending: "+customer.show());
            
            //Serializable doi tuong da sua 
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            
            oos.writeObject(customer);
            oos.flush();
            oos.close();
            
            byte[] customerData = baos.toByteArray();
           
            //gop mang byte cua requesId va customerData vao 1 mang byte result 
            byte[] result = new byte[8+customerData.length];
            byte[] request = requestId.getBytes();
            System.arraycopy(request, 0, result, 0, 8);
            System.arraycopy(customerData, 0, result, 8, customerData.length);
            
            DatagramPacket resultPacket = new DatagramPacket(
            result,
                    result.length,
                    serverAddress,
                    2209
            );
            
            socket.send(resultPacket);
            
            System.out.println("finish.");
            
            
        }
        catch(SocketException e){
            System.out.println(e);
        }
    }
}
