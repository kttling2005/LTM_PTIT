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
            
            String data = cs.requestCharacter("B23DCCN483", "tepqTpaG");
            System.out.println("Du lieu nhan: "+ data);
            
            //URL Encoding theo quy tac URI path 
            String mh = new java.net.URI(null, null, data, null).toASCIIString();
            
            System.out.println("Sau ma hoa: "+ mh);
            
            cs.submitCharacter("B23DCCN483","tepqTpaG", mh);
            
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
