package org.example.ontap5_backend.Controller;

import org.example.ontap5_backend.Entity.DonDatHang;
import org.example.ontap5_backend.Repository.DonDatHangResponse;
import org.example.ontap5_backend.Service.DonDatHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public List<DonDatHangResponse> getAll(){
        return donDatHangService.getAllDonDatHang();
    }

    @PostMapping
    public DonDatHang add(@RequestBody @Valid DonDatHang donDatHang){
        return donDatHangService.add(donDatHang);
    }

    @PutMapping("/{id}")
    public DonDatHang update(@PathVariable Integer id ,@RequestBody @Valid DonDatHang donDatHang){
        donDatHang.setId(id);
        return donDatHangService.update(donDatHang);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        donDatHangService.delete(id);
    }

    @GetMapping("/{id}")
    public DonDatHang detail(@PathVariable Integer id){
        return donDatHangService.detail(id);
    }

    @GetMapping("/page")
    public Page<DonDatHangResponse> getPage(@RequestParam(defaultValue = "0") int page){
        return donDatHangService.getPage(org.springframework.data.domain.PageRequest.of(page,5));
    }

}
