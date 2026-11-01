package com.mycompany.db_exercises;

import java.rmi.registry.*;
import java.net.*;
import java.io.*;
import java.util.*;
import RMI.ByteService;
        
public class Db_exercises {
    public static void main(String[] args) {
        try{
            Registry r = LocateRegistry.getRegistry("36.50.135.242",1099);
            ByteService bs = (ByteService) r.lookup("RMIByteService");
            
            byte[] data = bs.requestData("B23DCCN483", "Yll5e5Re");
            System.out.println("Du lieu nhan: "+ java.util.Arrays.toString(data));
            
            Map<Byte,Integer> m = new HashMap<>();
            for(byte a: data){
                m.put(a, m.getOrDefault(a, 0)+1);
            }
            
            byte max1 = 0;
            int max2 = Integer.MIN_VALUE;
            for(byte a: data){
                if(m.get(a)>max2){
                    max1 = a;
                    max2 = m.get(a);
                }
            }
            
            byte[] result = new byte[]{
                max1,
                (byte) max2
            };
            System.out.println("Du lieu gui: "+ java.util.Arrays.toString(result));
            
            bs.submitData("B23DCCN483","Yll5e5Re",result);
            
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
