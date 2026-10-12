package com.mycompany.db_exercises;

import UDP.Student;
import java.io.*;
import java.nio.*;
import java.util.*;
import java.net.*;

public class Db_exercises {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        try{
            DatagramSocket socket = new DatagramSocket(2209);
            InetAddress serverAddress = InetAddress.getByName("36.50.135.242");
            
            //gui du lieu 
            String message = ";B23DCCN483;CsVqUiXO";
            byte[] data = message.getBytes();
            
            DatagramPacket sendPacket = new DatagramPacket(
            data,
            data.length,
                    serverAddress,
                    2209
            );
            socket.send(sendPacket);
            
            //nhan du lieu
            byte[] receivedData = new byte[4096];
            DatagramPacket receivedPacket =  new DatagramPacket(
            receivedData,
                    receivedData.length
            );
            socket.receive(receivedPacket);
            
            String requestId = new String(
            receivedPacket.getData(),
                    0,
                    8
            );
            System.out.println("Server Response: "+requestId);
            //doc doi tuong student
            ByteArrayInputStream bais = new ByteArrayInputStream(
            receivedPacket.getData(),
                    8,
                    receivedPacket.getLength()-8
            );
            
            ObjectInputStream ois = new ObjectInputStream(bais);
            
            Student student = (Student) ois.readObject();
            ois.close();
            System.out.println("Server response: "+ student.show());
            
            String[] names = student.getName().split("\\s+");
            String newName = "";
            for(String x: names){
                x = x.substring(0,1).toUpperCase()+x.substring(1).toLowerCase();
                if(newName.length()>0) newName = newName+" ";
                newName = newName+x; 
            }
            
            student.setName(newName);
            
            String email = names[names.length-1].toLowerCase();
            for(int i = 0; i < names.length-1; i++){
                email = email + names[i].substring(0,1).toLowerCase();
            }
            email+="@ptit.edu.vn";
            
            student.setEmail(email);
            
            System.out.println("Student: "+student.show());
            
            //gui result 
            
            //Serialization doi tuong da sua 
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            
            oos.writeObject(student);
            oos.flush();
            oos.close();
            
            byte[] studentData = baos.toByteArray();
            
            byte[] result = new byte[8+studentData.length];
            
            //gop mang byte cua result va studentData vao mang byte result
            byte[] request = requestId.getBytes();
            System.arraycopy(request, 0, result, 0, 8);
            System.arraycopy(studentData, 0, result, 8, studentData.length);
            
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
