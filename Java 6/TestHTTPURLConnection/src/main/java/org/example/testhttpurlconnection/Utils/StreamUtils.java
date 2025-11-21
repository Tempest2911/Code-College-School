package org.example.testhttpurlconnection.Utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

//Tiện ích xử lý xữ liệu
public class StreamUtils {
    public static String readData(InputStream inputStream) throws IOException {
//        Sử dụng ByteArrayOutputStream để đọc dữ liệu từ InputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        // Khai báo bộ đệm 4kb
        byte[] buffer = new byte[4096];
        while (true) {
            int n = inputStream.read(buffer); //Đọc vào buffer
            if(n < 0) break;                    //Nếu đọc hết thì dừng vòng lặp
            outputStream.write(buffer, 0, n);  //Ghi dữ liệu đọc được vào outputStream
        }
        return outputStream.toString();
    }
}
