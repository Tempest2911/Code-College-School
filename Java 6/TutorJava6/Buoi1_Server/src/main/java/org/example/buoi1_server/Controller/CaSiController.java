package org.example.buoi1_server.Controller;

import org.example.buoi1_server.Model.Request.CaSiRequest;
import org.example.buoi1_server.Model.Response.CaSiResponse;
import org.example.buoi1_server.Service.CaSiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/singer/management")
public class CaSiController {

    @Autowired
    private CaSiService caSiService;

    @GetMapping
    public List<CaSiResponse> hienThiDanhSachCaSi() {
        return caSiService.getAllCaSi();
    }

    @GetMapping("phan-trang")
    public List<CaSiResponse> phanTrang(@RequestParam("pageNo1") int pageNo,
                                        @RequestParam("pageSize1") int pageSize) {
        return caSiService.phanTrang(pageNo, pageSize).getContent();
    }

    @DeleteMapping("delete/{id}")
    public void xoaCaSi(@PathVariable("id") int id) {
        caSiService.deleteCaSi(id);
    }

    @GetMapping("detail/{id}")
    public CaSiResponse detail(@PathVariable("id") int id) {
        return caSiService.detailCaSi(id);
    }

    @PostMapping("add")
    public void add(@RequestBody CaSiRequest caSiRequest) {
        caSiService.add(caSiRequest);
    }

    @PutMapping("update/{id}")
    public void update(@RequestBody CaSiRequest caSiRequest, @PathVariable("id") int id) {
        caSiService.update(caSiRequest, id);
    }

}
