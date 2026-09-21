package javaapplication1;

import java.io.*;
import java.net.*;
import TCP.Laptop;

public class JavaApplication1 {
    public static void main(String[] args) {
        String host = "36.50.135.242";
        int port = 2209;

        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(host, port), 5000);
            socket.setSoTimeout(5000);

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.flush();

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            // 1. Gửi mã sinh viên và mã câu hỏi
            String request = "B23DCCN483;zPMXKwql";
            oos.writeObject(request);
            oos.flush();

            // 2. Nhận Laptop
            Laptop laptop = (Laptop) ois.readObject();

            // 3. Sửa tên sản phẩm
            String[] words = laptop.getName().trim().split("\\s+");
            
            for(int i = 0; i < words.length; i++)
            {
                System.out.println(words[i]);
            }

            if (words.length >= 2) {
                String temp = words[0];
                words[0] = words[words.length - 1];
                words[words.length - 1] = temp;
            }

            laptop.setName(String.join(" ", words));

            // Sửa số lượng
            int quantity = laptop.getQuantity();
            boolean negative = quantity < 0;

            String reversed = new StringBuilder(
                    String.valueOf(Math.abs(quantity))
            ).reverse().toString();

            int newQuantity = Integer.parseInt(reversed);

            if (negative) {
                newQuantity = -newQuantity;
            }

            laptop.setQuantity(newQuantity);

            // Gửi Laptop đã sửa
            oos.writeObject(laptop);
            oos.flush();

            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
