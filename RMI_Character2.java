package com.mycompany.db_exercises;

import java.rmi.registry.*;
import java.io.*;
import java.net.*;
import java.util.*;
import RMI.CharacterService;

public class Db_exercises {
    public static void main(String[] args) {
        try{
           Registry r = LocateRegistry.getRegistry("36.50.135.242",1099);
           CharacterService cs = (CharacterService) r.lookup("RMICharacterService");
           
           String data = cs.requestCharacter("B23DCCN483", "XhnUeZMb");
           
           System.out.println("Du lieu nhan: "+ data);
           
           int I = 1, V = 5, X = 10, L = 50, C = 100, D = 500, M = 1000;
           Map<String, Integer> m = new HashMap<>();
           m.put("I", 1);
           m.put("V", 5);
           m.put("IV", 4);
           m.put("X", 10);
           m.put("IX", 9);
           m.put("L", 50);
           m.put("XL", 40);
           m.put("C", 100);
           m.put("XC", 90);
           m.put("D", 500);
           m.put("CD", 400);
           m.put("M",1000);
           m.put("CM",900);
           String[] a = {"I","IV","V","IX","X","XL","L","XC","C","CD","D","CM","M"};
           String ans="";
           int i = Integer.parseInt(data);
           while(i>0){
               for(int j = a.length - 1; j >= 0; j--){
                   if(i>= m.get(a[j])){
                       ans+=a[j];
                       i-=m.get(a[j]);
                       j = -1;
                   }
               }
           }
           System.out.println(ans);
           cs.submitCharacter("B23DCCN483", "XhnUeZMb", ans);
            
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
