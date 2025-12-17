// java
package org.example.ontap2backend.controller;

import org.example.ontap2backend.entity.ChamCong;
import org.example.ontap2backend.repository.ChamCongResponse;
import org.example.ontap2backend.service.ChamCongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/chamCong")
@CrossOrigin("*")
public class ChamCongController {

    @Autowired
    ChamCongService chamCongService;

    @GetMapping
    public List<ChamCongResponse> getAll() {
        return chamCongService.getAll();
    }

    @PostMapping
    public ChamCong add(@RequestBody @Valid ChamCong chamCong) {
        return chamCongService.add(chamCong);
    }

    @PutMapping("/{id}")
    public ChamCong update(@PathVariable Integer id, @RequestBody @Valid ChamCong chamCong) {
        chamCong.setId(id);
        return chamCongService.update(chamCong);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        chamCongService.delete(id);
    }

    @GetMapping("/page")
    public Page<ChamCongResponse> getPage(@RequestParam(defaultValue = "0") int page) {
        return chamCongService.getPage(PageRequest.of(page, 5));
    }

    @GetMapping("/{id}")
    public ChamCong detail(@PathVariable Integer id) {
        return chamCongService.detail(id);
    }
}
