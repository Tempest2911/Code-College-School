// java
package org.example.ontap3_backend.controller;


import org.example.ontap3_backend.entity.NhanVien;
import org.example.ontap3_backend.repository.NhanVienResponse;
import org.example.ontap3_backend.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/nhan-vien")
@CrossOrigin("*")
public class NhanVienController {

    @Autowired
    NhanVienService nhanVienService;

    @GetMapping
    public List<NhanVienResponse> getAll() {
        return nhanVienService.getAll();
    }

    @PostMapping
    public NhanVien add(@RequestBody @Valid NhanVien NhanVien) {
        return nhanVienService.add(NhanVien);
    }

    @PutMapping("/{id}")
    public NhanVien update(@PathVariable Integer id, @RequestBody @Valid NhanVien NhanVien) {
        NhanVien.setId(id);
        return nhanVienService.update(NhanVien);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        nhanVienService.delete(id);
    }

    @GetMapping("/page")
    public Page<NhanVienResponse> getPage(@RequestParam(defaultValue = "0") int page) {
        return nhanVienService.getPage(PageRequest.of(page, 5));
    }

    @GetMapping("/{id}")
    public NhanVien detail(@PathVariable Integer id) {
        return nhanVienService.detail(id);
    }
}
