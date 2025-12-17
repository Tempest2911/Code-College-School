package org.example.ontap6_backend.Controller;

import org.example.ontap6_backend.Entity.LichChieu;
import org.example.ontap6_backend.Repository.LichChieuResponse;
import org.example.ontap6_backend.Service.LichChieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/lichChieu")
@CrossOrigin("*")
public class Controller {

    @Autowired
    LichChieuService lichChieuService;

    @GetMapping
    public List<LichChieuResponse> getAll(){
        return lichChieuService.getAllLichChieu();
    }

    @PostMapping
    public LichChieu add(@RequestBody @Valid LichChieu lichChieu){
        return lichChieuService.add(lichChieu);
    }

    @PutMapping("{id}")
    public LichChieu update(@PathVariable Integer id, @RequestBody @Valid LichChieu lichChieu){
        lichChieu.setId(id);
        return lichChieuService.update(lichChieu);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Integer id){
        lichChieuService.delete(id);
    }

    @GetMapping("{id}")
    public LichChieu detail(@PathVariable Integer id, @RequestBody @Valid LichChieu lichChieu){
        lichChieu.setId(id);
        return lichChieuService.detail(id);
    }

    @GetMapping("/page")
    public Page<LichChieuResponse> getPage(@RequestParam(defaultValue = "0") int page){
        return lichChieuService.getPage(PageRequest.of(page, 5));
    }


}
