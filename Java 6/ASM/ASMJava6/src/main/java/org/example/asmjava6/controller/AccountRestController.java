package org.example.asmjava6.controller;

import org.example.asmjava6.entity.Account;
import org.example.asmjava6.repository.AccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/accounts")
public class AccountRestController {
    @Autowired
    AccountDAO accountDAO;

    // 1. Lấy tất cả user (Dùng cho Admin quản lý)
    @GetMapping
    public java.util.List<Account> getAll() {
        return accountDAO.findAll();
    }

    // 2. Lấy thông tin 1 user (Dùng cho trang Hồ sơ cá nhân)
    @GetMapping("{username}")
    public Account getOne(@PathVariable("username") String username) {
        return accountDAO.findById(username).orElse(null);
    }

    // 3. Đăng nhập
    @PostMapping("/login")
    public java.util.Map<String, Object> login(@RequestBody Account loginData) {
        Optional<Account> userOpt = accountDAO.findById(loginData.getUsername());
        if (userOpt.isEmpty()) throw new RuntimeException("Sai tài khoản!");

        Account user = userOpt.get();
        if (!user.getActivated()) {
            throw new RuntimeException("Tài khoản đã bị khóa! Vui lòng liên hệ Admin.");
        }

        String dbPass = user.getPassword();
        if(dbPass.startsWith("{noop}")) dbPass = dbPass.substring(6);

        if (!dbPass.equals(loginData.getPassword())) throw new RuntimeException("Sai mật khẩu!");

        java.util.Map<String, Object> result = new java.util.HashMap<>();

        result.put("username", user.getUsername());
        result.put("fullname", user.getFullname());
        result.put("email", user.getEmail());
        result.put("photo", user.getPhoto());

        java.util.List<String> roles = user.getAuthorities().stream()
                .map(au -> au.getRole().getId()).collect(java.util.stream.Collectors.toList());
        result.put("roles", roles);


        return result;
    }

    // 4. Cập nhật thông tin (Dùng chung cho Admin khóa nick & User đổi pass/info)
    @PutMapping("{username}")
    public Account update(@PathVariable("username") String username, @RequestBody Account account) {
        Account inDB = accountDAO.findById(username).orElse(null);
        if(inDB == null) throw new RuntimeException("Không tìm thấy!");

        // Nếu có gửi password mới lên thì cập nhật, không thì giữ nguyên pass cũ
        if(account.getPassword() != null && !account.getPassword().isEmpty()) {
            inDB.setPassword(account.getPassword()); // Lưu ý: thực tế nên mã hóa
        }

        // Cập nhật các thông tin khác
        if(account.getFullname() != null) inDB.setFullname(account.getFullname());
        if(account.getEmail() != null) inDB.setEmail(account.getEmail());
        if(account.getPhoto() != null) inDB.setPhoto(account.getPhoto());
        if(account.getActivated() != null) inDB.setActivated(account.getActivated());

        return accountDAO.save(inDB);
    }
}