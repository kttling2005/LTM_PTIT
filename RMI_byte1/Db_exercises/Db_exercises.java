package com.mycompany.db_exercises;

import RMI.ByteService;
import java.net.*;
import java.util.*;
import java.io.*;
import java.rmi.registry.*;
        
public class Db_exercises {
    public static void main(String[] args) {
        try{
            //Ket noi Registry Server
            Registry r = LocateRegistry.getRegistry("36.50.135.242",1099);
            
            //Lay doi tuong tu xa 
            ByteService bs = (ByteService) r.lookup("RMIByteService");
            
            //nhan du lieu tu server 
            byte[] data = bs.requestData("B23DCCN483", "oufAAme3");
            System.out.println("Du lieu nhan: "+ java.util.Arrays.toString(data));
            
            Map<Byte,Integer> m = new HashMap<>();
            
            for(byte a: data){
                m.put(a, m.getOrDefault(a, 0)+1);
            }
            
            //tim phan tu xuat hien it nhat 
            byte minElement = 0;
            int minCount = Integer.MAX_VALUE;
            
            for(byte a: data){
                if(m.get(a)<minCount){
                    minCount = m.get(a);
                    minElement = a;
                }
            }
            
            byte[] result = new byte[]{
                minElement,
                (byte) minCount
            };
            
            System.out.println("Du lieu gui: "+ java.util.Arrays.toString(result));
            
            bs.submitData("B23DCCN483", "oufAAme3", result);
            
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
