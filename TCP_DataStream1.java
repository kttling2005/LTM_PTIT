package javaapplication1;
import java.io.*;
import java.net.*;
import java.util.*;

public class JavaApplication1 {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("36.50.135.242",2207);
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        
        dos.writeUTF("B23DCCN483;zviAFgcl");
        dos.flush();
        
        int a = dis.readInt();
        int b = dis.readInt();
        
        dos.writeInt(a+b);
        dos.writeInt(a*b);
        dos.flush();
        
        dos.close();
        dis.close();
        socket.close();
    }
}
