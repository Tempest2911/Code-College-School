package org.example.testhttpurlconnection.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static org.example.testhttpurlconnection.Utils.StreamUtils.readData;

@Controller
public class HttpsUrlController {
    @GetMapping("/get-all")
    public String getAll() throws IOException, MalformedURLException {
        var url = "https://sd20202-3b7f7-default-rtdb.asia-southeast1.firebasedatabase.app/student.json";
        var connection = (HttpsURLConnection) new URL(url).openConnection();
        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        if (connection.getResponseCode() == 200) {
            String data = readData(connection.getInputStream()); //Gọi API thành công
            System.out.println(data);
            System.out.println("Kết nối thành công");
        }
        connection.disconnect();
        return "index";
    }

    @GetMapping("/get-by-key")
    public String getByKey() throws IOException, MalformedURLException {
        var url = "https://sd20202-3b7f7-default-rtdb.asia-southeast1.firebasedatabase.app/student.json";
        var connection = (HttpsURLConnection) new URL(url).openConnection();
        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        connection.setRequestMethod("GET");
        if (connection.getResponseCode() == 200) {
            String data = readData(connection.getInputStream()); //Gọi API thành công
            System.out.println(data);
            System.out.println("Kết nối thành công");
        }
        connection.disconnect();
        return "index";
    }

    @PostMapping("/create")
    public String HttpsUrlCreate() throws IOException, MalformedURLException {
        var url = "https://sd20202-3b7f7-default-rtdb.asia-southeast1.firebasedatabase.app/student.json";
        var connection = (HttpsURLConnection) new URL(url).openConnection();
        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        connection.setRequestMethod("POST");
        var postData = """
                {
                  "id": "SV20",
                  "name": "Nguyen Van C",
                  "mark": 10,
                  "gender": "true"
                }
                """;
        connection.setDoOutput(true);
        connection.getOutputStream().write(postData.getBytes());
        if (connection.getResponseCode() == 200) {
            String data = readData(connection.getInputStream()); //Gọi API thành công
            System.out.println(data);
            System.out.println("Thêm thành công");
        }
        connection.disconnect();
        return "index";
    }

    @PutMapping("/update")
    public String HttpsUrlUpdate() throws IOException, MalformedURLException {
        var url = "https://sd20202-3b7f7-default-rtdb.asia-southeast1.firebasedatabase.app/student.json";
        var connection = (HttpsURLConnection) new URL(url).openConnection();
        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        connection.setRequestMethod("PUT");
        var postData = """
                {
                  "id": "SV20",
                  "name": "Nguyen Van ABCLLL",
                  "mark": 10,
                  "gender": "false"
                }
                """;
        connection.setDoOutput(true);
        connection.getOutputStream().write(postData.getBytes());
        if (connection.getResponseCode() == 200) {
            String data = readData(connection.getInputStream()); //Gọi API thành công
            System.out.println(data);
            System.out.println("Thêm thành công");
        }
        connection.disconnect();
        return "index";
    }

    @DeleteMapping("/delete")
    public String HttpsUrlDelete() throws IOException, MalformedURLException {
        var url = "https://sd20202-3b7f7-default-rtdb.asia-southeast1.firebasedatabase.app/student.json";
        var connection = (HttpsURLConnection) new URL(url).openConnection();
        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        connection.setRequestMethod("DELETE");
        var postData = """
                {
                  "id": ,
                  "name": "Nguyen Van C",
                  "mark": 10,
                  "gender": "true"
                }
                """;
        connection.setDoOutput(true);
        connection.getOutputStream().write(postData.getBytes());
        if (connection.getResponseCode() == 200) {
            String data = readData(connection.getInputStream()); //Gọi API thành công
            System.out.println(data);
            System.out.println("Thêm thành công");
        }
        connection.disconnect();
        return "index";
    }
}
