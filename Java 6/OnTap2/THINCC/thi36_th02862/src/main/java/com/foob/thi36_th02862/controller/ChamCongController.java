package com.foob.thi36_th02862.controller;

import java.util.ArrayList;
import java.util.List;

import com.foob.thi36_th02862.model.ChamCong;
import com.foob.thi36_th02862.model.TheObject;
import com.foob.thi36_th02862.service.ChamCongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/cc")
@CrossOrigin(origins = "*")
public class ChamCongController {

    @Autowired
    private ChamCongService service;

    @GetMapping
    public List<TheObject> getAll() {
        ArrayList<TheObject> list = new ArrayList<>();
        for (ChamCong cc : service.getAll()) {
            TheObject obj = new TheObject();
            obj.setId(cc.getId());
            obj.setNgayCham(cc.getNgayCham());
            obj.setSoGioLam(cc.getSoGioLam());
            obj.setPhat(cc.getPhat());
            obj.setTenNhanVien(cc.getNhanVien().getTenNhanVien());
            obj.setPhongBan(cc.getNhanVien().getPhongBan());
            list.add(obj);
        }
        return list;
    }

    @GetMapping("/pt")
    public List<TheObject> phanTrang(@RequestParam Integer page, @RequestParam Integer size) {
        ArrayList<TheObject> list = new ArrayList<>();
        for (ChamCong cc : service.getAll()) {
            TheObject obj = new TheObject();
            obj.setId(cc.getId());
            obj.setNgayCham(cc.getNgayCham());
            obj.setSoGioLam(cc.getSoGioLam());
            obj.setPhat(cc.getPhat());
            obj.setTenNhanVien(cc.getNhanVien().getTenNhanVien());
            obj.setPhongBan(cc.getNhanVien().getPhongBan());
            list.add(obj);
        }
        List<TheObject> allChamCong = list;
        int start = (page - 1) * size;
        int end = Math.min(start + size, allChamCong.size());
        return allChamCong.subList(start, end);
    }

    @PostMapping
    public void add(@Valid @RequestBody ChamCong bh) {
        service.create(bh);
    }

    @PutMapping("{id}")
    public void putMethodName(@PathVariable Integer id, @Valid @RequestBody ChamCong bh) {
        service.update(id, bh);
    }

    @DeleteMapping("{id}")
    public void deleteMethodName(@PathVariable Integer id) {
        service.delete(id);
    }
}
