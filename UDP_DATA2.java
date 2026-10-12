package com.mycompany.db_exercises;

import java.io.*;
import java.nio.*;
import java.net.*;
import java.util.*;

public class Db_exercises {

    public static void main(String[] args) throws IOException {
        try{
            DatagramSocket socket = new DatagramSocket(2207);
            InetAddress serverAddress = InetAddress.getByName("36.50.135.242");
            
            String message = ";B23DCCN483;OLK7WClr";
            byte[] data = message.getBytes();
            DatagramPacket sendData = new DatagramPacket(
            data,
            data.length,
            serverAddress,
                    2207
            );
            socket.send(sendData);
            
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
            String[] a = parts[1].split(",");
            
            long max = Long.MIN_VALUE;
            long min = Long.MAX_VALUE;
            
            for(int i = 0; i < a.length; i++){
                if(Integer.parseInt(a[i])>max) max = Integer.parseInt(a[i]);
                if(Integer.parseInt(a[i])<min) min = Integer.parseInt(a[i]);
            }
            
            String ans = requestId +";"+max+","+min;
            System.out.println("Sending: "+ans);
            byte[] ansData = ans.getBytes();
            DatagramPacket resultPacket = new DatagramPacket(
            ansData,
                    ansData.length,
                    serverAddress,
                    2207
            );
            socket.send(resultPacket);
            System.out.println("finish.");
         
           
        }
        catch(SocketException e){
            System.out.println(e);
        }
    }
}
