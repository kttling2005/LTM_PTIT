package com.mycompany.db_exercises;

import java.rmi.registry.*;
import java.io.*;
import java.net.*;
import java.util.*;
import RMI.DataService;

public class Db_exercises {
    public static void main(String[] args) {
        try{
           Registry r = LocateRegistry.getRegistry("36.50.135.242",1099);
           DataService ds = (DataService) r.lookup("RMIDataService");
           
           int[] a = (int[]) ds.requestData("B23DCCN483", "xMSOTSvu");
           
           List<Integer> b = new ArrayList<>();
           
           if(a[0]>a[1]) b.add(1);
           for(int i = 1; i < a.length-1; i++){
               if(a[i]>a[i-1] && a[i]>a[i+1]) b.add(i+1);
           }
           
           if(a[a.length-1]>a[a.length-2]) b.add(a.length);
           
            for(int i: b){
                System.out.println(i);
            }
            
            ds.submitData("B23DCCN483", "xMSOTSvu", b);
            
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
