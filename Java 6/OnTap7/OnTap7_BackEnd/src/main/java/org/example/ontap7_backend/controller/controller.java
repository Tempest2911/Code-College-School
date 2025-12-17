package org.example.ontap7_backend.controller;

import org.example.ontap7_backend.entity.DonHang;
import org.example.ontap7_backend.repository.DonHangResponse;
import org.example.ontap7_backend.service.DonHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/donHang")
@CrossOrigin("*")
public class controller {
    @Autowired
    DonHangService donHangService;

    @GetMapping
    public List<DonHangResponse> getAll(){
        return donHangService.getAllDonHang();
    }

    @PostMapping
    public DonHang add(@RequestBody @Valid DonHang donHang){
        return donHangService.add(donHang);
    }

    @PutMapping("{id}")
    public DonHang update(@PathVariable Integer id,@RequestBody @Valid DonHang donHang){
        donHang.setId(id);
        return donHangService.update(donHang);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Integer id){
        donHangService.delete(id);
    }

    @GetMapping("{id}")
    public DonHang detail(@PathVariable Integer id, @RequestBody @Valid DonHang lichChieu){
        lichChieu.setId(id);
        return donHangService.detail(id);
    }

    @GetMapping("/page")
    public Page<DonHangResponse> getPage(@RequestParam(defaultValue = "0") int page) {
        return donHangService.getPage(PageRequest.of(page, 5));
    }

}
