package org.example.buoi1_server.Buoi2_CRUD_2Bang.Controller;

import org.example.buoi1_server.Buoi1_CRUD_1Bang.Model.Response.CaSiResponse;
import org.example.buoi1_server.Buoi2_CRUD_2Bang.Model.Request.PhieuGiamGiaRequest;
import org.example.buoi1_server.Buoi2_CRUD_2Bang.Model.Response.PhieuGiamGiaResponse;
import org.example.buoi1_server.Buoi2_CRUD_2Bang.Repository.PhieuGiamGiaRepository;
import org.example.buoi1_server.Buoi2_CRUD_2Bang.Service.PhieuGiamGiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/phieu-giam-gia")
public class PhieuGiamGiaController {

    @Autowired
    private PhieuGiamGiaService pggService;

    @GetMapping
    public List<PhieuGiamGiaResponse> hienThiDanhSach() {
        return pggService.getAll();
    }


    @GetMapping("paging")
    public List<PhieuGiamGiaResponse> phanTrangDanhSach(@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
                                                        @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        return pggService.phanTrang(pageNo, pageSize).getContent();
    }

    @GetMapping("detail")
    public PhieuGiamGiaResponse detailPhieuGiamGia(@RequestParam("id") Integer id) {
        return pggService.getOne(id);
    }

    @PostMapping("add")
    public void add(@RequestBody PhieuGiamGiaRequest request) {
        pggService.addPhieuGiamGia(request);
    }

    @PutMapping("update/{id}")
    public void update(@RequestBody PhieuGiamGiaRequest request, @PathVariable("id") Integer id) {
        pggService.updatePhieuGiamGia(request, id);
    }
}
