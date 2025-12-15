package org.example.ontap4_backend.service;

import org.example.ontap4_backend.entity.DonHang;
import org.example.ontap4_backend.exeption.ApiException;
import org.example.ontap4_backend.repository.DonHangRepo;
import org.example.ontap4_backend.repository.DonHangResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonHangService {
    @Autowired
    DonHangRepo donHangRepo;

    public List<DonHangResponse> getAll() {
        return donHangRepo.getAllCustom();
    }

    public DonHang add(DonHang donHang) {
        return donHangRepo.save(donHang);
    }

    public DonHang update(DonHang donHang) {
        donHangRepo.findById(donHang.getId());
        return donHangRepo.save(donHang);
    }

    public void delete(Integer id) {
        DonHang exit = donHangRepo.findById(id).orElseThrow();
        donHangRepo.delete(exit);
    }

    public DonHang detail(Integer id) {
        return donHangRepo.findById(id).get();
    }

    public Page<DonHangResponse> getPage(Pageable pageable) {
        return donHangRepo.getPageCustom(pageable);
    }
}
