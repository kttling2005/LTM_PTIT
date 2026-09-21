package javaapplication1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class JavaApplication1 {

    private static final String HOST = "36.50.135.242";
    private static final int PORT = 2211;

    public static void main(String[] args) {
        String studentCode = "B23DCCN483";
        String qCode = "HIAn78TX";

        try (SocketChannel channel = SocketChannel.open()) {

            channel.configureBlocking(true);
            channel.socket().setSoTimeout(5000);
            channel.connect(new InetSocketAddress(HOST, PORT));

            // a. Gửi studentCode;qCode
            String request = studentCode + ";" + qCode+"\n";
            writeFrame(channel, request);

            // b. Đọc đúng 3 frame
            StringBuilder httpRequest = new StringBuilder();

            for (int i = 0; i < 3; i++) {
                String payload = readFrame(channel);
                httpRequest.append(payload);
            }

            String http = httpRequest.toString();

            System.out.println("HTTP request:");
            System.out.println(http);

            // c. Parse METHOD;PATH;HOST
            String result = parseHttpRequest(http);

            System.out.println("Ket qua: " + result);

            writeFrame(channel, result);

            // d. đóng kết nối
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Gửi 1 frame: [4 byte length][payload UTF-8]
    private static void writeFrame(SocketChannel channel, String data)
            throws IOException {

        byte[] payload = data.getBytes(StandardCharsets.UTF_8);

        ByteBuffer buffer = ByteBuffer.allocate(4 + payload.length);

        buffer.putInt(payload.length);
        buffer.put(payload);

        buffer.flip();

        while (buffer.hasRemaining()) {
            channel.write(buffer);
        }
    }

    // Đọc 1 frame: [4 byte length][payload UTF-8]
    private static String readFrame(SocketChannel channel)
            throws IOException {

        // Đọc đủ 4 byte length
        ByteBuffer lengthBuffer = ByteBuffer.allocate(4);

        readFully(channel, lengthBuffer);

        lengthBuffer.flip();

        int length = lengthBuffer.getInt();

        // Đọc đủ payload
        ByteBuffer payloadBuffer = ByteBuffer.allocate(length);

        readFully(channel, payloadBuffer);

        payloadBuffer.flip();

        return StandardCharsets.UTF_8.decode(payloadBuffer).toString();
    }

    // Đảm bảo đọc đủ buffer
    private static void readFully(SocketChannel channel, ByteBuffer buffer)
            throws IOException {

        while (buffer.hasRemaining()) {
            int n = channel.read(buffer);

            if (n == -1) {
                throw new IOException("Server closed connection");
            }
        }
    }

    private static String parseHttpRequest(String http) {

        // Tách các dòng bằng CRLF
        String[] lines = http.split("\\r\\n");

        // Request line:
        // GET /path?id=123 HTTP/1.1
        String[] requestLine = lines[0].split(" ");

        String method = requestLine[0];
        String path = requestLine[1];

        String host = "";

        // Tìm Host:
        for (String line : lines) {

            if (line.regionMatches(true, 0, "Host:", 0, 5)) {
                host = line.substring(5).trim();
                break;
            }
        }

        return method + ";" + path + ";" + host;
    }
}
