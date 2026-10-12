package com.mycompany.db_exercises;

import java.util.*;
import java.net.*;
import java.io.*;
import java.nio.*;

public class Db_exercises {

    public static void main(String[] args) throws IOException {
        try{
            InetAddress serverAddress = InetAddress.getByName("36.50.135.242");
            DatagramSocket socket = new DatagramSocket(2208);
            
            String message = ";B23DCCN483;d2vT7K2x";
            byte[] data = message.getBytes();
            
            DatagramPacket sendPacket = new DatagramPacket(
            data,
            data.length,
            serverAddress,
            2208);
            
            socket.send(sendPacket);
            
            //nhan du lieu tu server 
            byte[] receivedData = new byte[4096];
            DatagramPacket receivedPacket = new DatagramPacket(
            receivedData, receivedData.length
            );
            socket.receive(receivedPacket);
            
            String response = new String(
            receivedPacket.getData(),
            0,
            receivedPacket.getLength());
            
            System.out.println("Server response: "+response);
            
            String[] parts = response.split(";");
            
            String requestId = parts[0].trim();
            String input = parts[1].trim();
            
            //Dem so lan xuat hien = map//hashmap 
            Map<Character, Integer> f = new HashMap<>();
            //hashmap la 1 lop giup viec tim kiem, them hoac cap nhat so lan dem dien ra cuc ky nhanh nho co che bam (hashing) 
            for (char c : input.toCharArray()){
                f.put(c, f.getOrDefault(c, 0)+1);
            }
            
            char maxChar = ' ';
            int maxCount = 0;
            
            for (char c : input.toCharArray()){
                if(f.get(c)>maxCount && c != ' '){
                    maxChar = c;
                    maxCount = f.get(c);
                }
            }
            String ans = requestId +";" + maxChar+":";
            //tim vi tri c 
            int index = 0;
            for (char c : input.toCharArray()){
                index++;
                if(c==maxChar){
                    ans += (index+",");
                }
            }
            System.out.println("Sending: "+ans);
            //gui ket qua 
            byte[] ansData = ans.getBytes();
            DatagramPacket resultPacket = new DatagramPacket(
            ansData,
            ansData.length,
            serverAddress,
                    2208
            );
            socket.send(resultPacket);
            //socket tu dong dong 
            System.out.println("finish.");
        }
            
        catch (SocketException e){
            System.out.println(e);
        }
    }
}
