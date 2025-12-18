package org.example.thicuoimon_backend.controller;

import org.example.thicuoimon_backend.entity.DonDatHang;
import org.example.thicuoimon_backend.repository.DonDatHangResponse;
import org.example.thicuoimon_backend.service.DonDatHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/donDatHang")
@CrossOrigin("*")
public class DonDatHangController {

    @Autowired
    DonDatHangService donDatHangService;

    @GetMapping
    public List<DonDatHangResponse> getAllDonDatHang() {
       return donDatHangService.getAllDonDatHang();
    }

    @PostMapping
    public DonDatHang add(@RequestBody @Valid DonDatHang donDatHang) {
        return donDatHangService.add(donDatHang);
    }

    @PutMapping("{id}")
    public DonDatHang update(@PathVariable Integer id, @RequestBody @Valid DonDatHang donDatHang) {
        donDatHang.setId(id);
        return donDatHangService.update(donDatHang);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Integer id) {
        donDatHangService.delete(id);
    }

    @GetMapping("/page")
    public Page<DonDatHangResponse> getPage(@RequestParam(defaultValue = "1") int page) {
        return donDatHangService.getPage(PageRequest.of(page, 5));
    }
}
