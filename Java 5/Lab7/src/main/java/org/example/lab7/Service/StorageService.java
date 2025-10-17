package org.example.lab7.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class StorageService {

    // ✅ Đường dẫn thư mục thật (ngoài target/)
    private final String uploadDir = System.getProperty("user.dir") + "/uploads/";

    public String saveFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) return null;

        File dir = new File(uploadDir);
        if (!dir.exists()) dir.mkdirs();

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        File dest = new File(uploadDir + fileName);
        file.transferTo(dest);

        // ✅ Đường dẫn hiển thị web: bạn map thư mục này trong cấu hình
        return "/uploads/" + fileName;
    }
}
