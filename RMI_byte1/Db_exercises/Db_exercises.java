package com.mycompany.db_exercises;
import RMI.ByteService;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.LinkedHashMap;
import java.util.Map;

public class Db_exercises {
    public static void main(String[] args) {
        try {
            // Thông tin sinh viên
            String studentCode = "B23DCCN483";
            String qCode = "oufAAme3";

            // Kết nối Registry Server
            Registry registry = LocateRegistry.getRegistry("36.50.135.242", 1099);

            // Lấy đối tượng từ xa
            ByteService service = (ByteService) registry.lookup("RMIByteService");

            // a. Nhận dữ liệu từ server
            byte[] data = service.requestData(studentCode, qCode);

            // b. Đếm số lần xuất hiện của từng phần tử
            Map<Byte, Integer> frequency = new LinkedHashMap<>();

            for (byte b : data) {
                frequency.put(b, frequency.getOrDefault(b, 0) + 1);
            }

            // Tìm phần tử xuất hiện ít nhất
            byte minElement = 0;
            int minCount = Integer.MAX_VALUE;

            for (Map.Entry<Byte, Integer> entry : frequency.entrySet()) {
                if (entry.getValue() < minCount) {
                    minCount = entry.getValue();
                    minElement = entry.getKey();
                }
            }

            // Tạo mảng kết quả [phần tử, số lần xuất hiện]
            byte[] result = new byte[] {
                minElement,
                (byte) minCount
            };

            // c. Gửi kết quả lên server
            service.submitData(studentCode, qCode, result);

            System.out.println("Du lieu nhan: " + java.util.Arrays.toString(data));
            System.out.println("Ket qua gui: " + java.util.Arrays.toString(result));

            // d. Kết thúc chương trình

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
