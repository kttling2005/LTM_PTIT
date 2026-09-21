package javaapplication1;

import java.io.*;
import java.net.*;

public class JavaApplication1 {

    public static void main(String[] args) throws IOException {

        String serverHost = "36.50.135.242";
        int serverPort = 2207;

        String studentCode = "B23DCCN483";
        String qCode = "oGLiKLtX";

        Socket socket = new Socket(serverHost, serverPort);

        DataInputStream in = new DataInputStream(
                socket.getInputStream());

        DataOutputStream out = new DataOutputStream(
                socket.getOutputStream());

        // a. Gửi mã sinh viên và mã câu hỏi
        String request = studentCode + ";" + qCode;
        out.writeUTF(request);
        out.flush();

        // b. Nhận chuỗi Caesar và giá trị dịch chuyển s
        String encrypted = in.readUTF();
        int s = in.readInt();

        System.out.println("Encrypted: " + encrypted);
        System.out.println("s = " + s);

        // c. Giải mã Caesar
        StringBuilder decrypted = new StringBuilder();

        for (int i = 0; i < encrypted.length(); i++) {

            char ch = encrypted.charAt(i);

            if (ch >= 'A' && ch <= 'Z') {
                ch = (char) ((ch - 'A' - s + 26) % 26 + 'A');
            }
            else if (ch >= 'a' && ch <= 'z') {
                ch = (char) ((ch - 'a' - s + 26) % 26 + 'a');
            }

            decrypted.append(ch);
        }

        String result = decrypted.toString();

        System.out.println("Decrypted: " + result);

        // Gửi thông điệp đã giải mã
        out.writeUTF(result);
        out.flush();

        // d. Đóng kết nối
        socket.close();
    }
}
