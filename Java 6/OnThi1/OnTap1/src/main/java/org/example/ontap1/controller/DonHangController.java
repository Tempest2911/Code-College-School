package org.example.ontap1.controller;

import jakarta.validation.Valid; // Lưu ý: Spring Boot 3 dùng 'jakarta', không dùng 'javax'
import org.example.ontap1.entity.DonHang;
import org.example.ontap1.service.DonHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/don-hang")
@CrossOrigin("*")
public class DonHangController {

    @Autowired
    DonHangService service;

    @GetMapping
    public ResponseEntity<?> getAll() { return ResponseEntity.ok(service.getAll()); }

    @GetMapping("/page")
    public ResponseEntity<?> getPage(@RequestParam(defaultValue = "0") int page) {
        return ResponseEntity.ok(service.getPage(PageRequest.of(page, 5)));
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody @Valid DonHang donHang) {
        donHang.setId(null); // Đảm bảo thêm mới
        return ResponseEntity.ok(service.add(donHang));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody @Valid DonHang donHang) {
        if (!service.existsById(id)) return ResponseEntity.badRequest().body("ID không tồn tại");

        donHang.setId(id);
        return ResponseEntity.ok(service.update(donHang));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        if (!service.existsById(id)) return ResponseEntity.badRequest().build();
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}