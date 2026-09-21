package javaapplication1;

import java.io.*;
import java.net.*;

public class JavaApplication1 {

    public static void main(String[] args) throws IOException {

        String serverHost = "36.50.135.242";
        int serverPort = 2208;

        String studentCode = "B23DCCN483";
        String qCode = "T4gexAb9";

        Socket socket = new Socket(serverHost, serverPort);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));

        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(socket.getOutputStream()));

        // a. Gửi mã sinh viên và mã câu hỏi
        String request = studentCode + ";" + qCode;
        out.write(request);
        out.newLine();
        out.flush();

        // b. Nhận chuỗi từ server
        String data = in.readLine();

        System.out.println("Received from server:");
        System.out.println(data);

        // c. Đếm số lần xuất hiện của các ký tự chữ hoặc số
        int[] count = new int[256];

        for (int i = 0; i < data.length(); i++) {
            char ch = data.charAt(i);

            if (Character.isLetterOrDigit(ch)) {
                count[ch]++;
            }
        }

        // Tạo kết quả
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < data.length(); i++) {
            char ch = data.charAt(i);

            if (Character.isLetterOrDigit(ch) && count[ch] > 1) {

                // Tránh ghi cùng một ký tự nhiều lần
                boolean alreadyAdded = false;

                for (int j = 0; j < i; j++) {
                    if (data.charAt(j) == ch) {
                        alreadyAdded = true;
                        break;
                    }
                }

                if (!alreadyAdded) {
                    result.append(ch)
                          .append(":")
                          .append(count[ch])
                          .append(",");
                }
            }
        }

        System.out.println("Sent to server:");
        System.out.println(result);

        // Gửi kết quả
        out.write(result.toString());
        out.newLine();
        out.flush();

        // d. Đóng kết nối
        socket.close();
    }
}
