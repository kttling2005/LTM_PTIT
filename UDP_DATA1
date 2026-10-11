package com.mycompany.db_exercises;

import java.io.IOException;
import java.net.*;
import java.util.*;
import java.nio.*;

public class Db_exercises {

    public static void main(String[] args) throws IOException {
        try{
            InetAddress serverAddress = InetAddress.getByName("36.50.135.242");//phan giai dia chi IP thanh 1 doi tuong trong java
            //InetAddress la mot lop trong java dai dien cho 1 dia chi IP hoac ten mien nhu google.com
            DatagramSocket socket = new DatagramSocket(2207);
            
            String message = ";B23DCCN483;dN7Fjll8";
            byte[] data = message.getBytes();
            
            DatagramPacket sendPacket = new DatagramPacket(
            data,data.length,serverAddress,2207);
            socket.send(sendPacket);
            
            //nhan du lieu tu server 
            byte[] receiveData = new byte[4096];
            DatagramPacket receivePacket = new DatagramPacket(
            receiveData, receiveData.length);
            socket.receive(receivePacket);
            
            String response = new String(
                receivePacket.getData(),//lay ra toan bo mang byte chua du lieu ben trong goi tin udp vua nhan duoc 
                0,//vi tri bat dau doc 
                receivePacket.getLength()// so luong byte thuc te can doc
            );
            
            System.out.println("Server response: "+response);
            
            //xu ly 
            String[] parts = response.split(";",-1); //neu co tham so -1, java se giu lai cac chuoi rong giua 2 dau ; 
            
            String requestId = parts[0];
            int n = Integer.parseInt(parts[1]);
            
            Set<Integer> receivedNumbers = new TreeSet<>();//TreeSet la Set nhung tu dong sap xep 
            if(parts.length > 2 && !parts[2].isEmpty()){
                String[] numbers = parts[2].split(",");
                
                for(String number : numbers){
                    receivedNumbers.add(Integer.parseInt(number.trim()));
                }
            }
            
            System.out.println(receivedNumbers);
            
            StringBuilder missingNumbers = new StringBuilder();
            
            for(int i = 1; i <= n; i++){
                if(!receivedNumbers.contains(i)){
                    if(missingNumbers.length()>0){
                        missingNumbers.append(",");
                    }
                    missingNumbers.append(i);
                }
            }
            
            //tao thong diep tra ve 
            String result = requestId +";" +missingNumbers;
            System.out.println("Sending: "+result);
            
            byte[] dataResult = result.getBytes();
            DatagramPacket resultPacket = new DatagramPacket(
            dataResult,
            dataResult.length,
            serverAddress,
            2207);
            socket.send(resultPacket);
            
            //socket tu dong ket noi 
            System.out.println("finish");
            
        }
        catch(SocketException e){
                System.out.println(e);
                } catch (UnknownHostException ex) {
            System.getLogger(Db_exercises.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
}
