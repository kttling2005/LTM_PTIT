package javaapplication1;

import java.io.*;
import java.net.*;

public class JavaApplication1 {

    public static void main(String[] args) throws IOException {

        String serverHost = "36.50.135.242";
        int serverPort = 2207;

        String studentCode = "B23DCCN483";
        String qCode = "zviAFgcl";

        Socket socket = new Socket(serverHost, serverPort);

        DataInputStream in = new DataInputStream(
                socket.getInputStream());

        DataOutputStream out = new DataOutputStream(
                socket.getOutputStream());

        // a. Gửi mã sinh viên và mã câu hỏi
        String request = studentCode + ";" + qCode;
        out.writeUTF(request);
        out.flush();

        // b. Nhận hai số nguyên a và b
        int a = in.readInt();
        int b = in.readInt();

        System.out.println("a = " + a);
        System.out.println("b = " + b);

        // c. Tính tổng và tích
        int sum = a + b;
        int product = a * b;

        // Gửi lần lượt tổng rồi tích
        out.writeInt(sum);
        out.writeInt(product);
        out.flush();

        System.out.println("Sum = " + sum);
        System.out.println("Product = " + product);

        // d. Đóng kết nối
        socket.close();
    }
}
