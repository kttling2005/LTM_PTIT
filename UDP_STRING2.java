package com.mycompany.db_exercises;

import java.io.*;
import java.nio.*;
import java.net.*;
import java.util.*;

public class Db_exercises {

    public static void main(String[] args) throws IOException{
        try{
            DatagramSocket socket = new DatagramSocket(2208);
            InetAddress serverAddress = InetAddress.getByName("36.50.135.242");
            
            //gui du lieu 
            String message = ";B23DCCN483;WeIN9tle";
            byte[] data = message.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(
            data,
                    data.length,
                    serverAddress,
                    2208
            );
            socket.send(sendPacket);
            
            //nhan du lieu 
            byte[] receivedData = new byte[4096];
            DatagramPacket receivedPacket = new DatagramPacket(
            receivedData,
                    receivedData.length
            );
            socket.receive(receivedPacket);
            String input = new String(
            receivedPacket.getData(),
                    0,
                    receivedPacket.getLength()
            );
            System.out.println("Server Response: "+ input);
            String[] parts = input.split(";");
            
            String requestId = parts[0];
            String str = parts[1];
            
            String[] a = str.split("\\s+");
            String ans = "";
            for(String x : a){
                if(ans.length()>0) ans = ans+" ";
                ans+=(x.substring(0, 1).toUpperCase()+x.substring(1).toLowerCase());
            }
            ans = requestId+";"+ans;
            System.out.println("Sending: "+ans);
            
            //gui du lieu 
            byte[] result = ans.getBytes();
            DatagramPacket resultPacket = new DatagramPacket(
            result,
                    result.length,
                    serverAddress,
                    2208
            );
            socket.send(resultPacket);
            
            System.out.println("finish");
        }
        catch(SocketException e){
            System.out.println(e);
        }
    }
}
