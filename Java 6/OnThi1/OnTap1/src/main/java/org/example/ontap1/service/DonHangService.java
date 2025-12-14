package org.example.ontap1.service;


import org.example.ontap1.entity.DonHang;
import org.example.ontap1.exception.ApiException;
import org.example.ontap1.repository.DonHangRepository;
import org.example.ontap1.repository.DonHangResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DonHangService {
    @Autowired
    DonHangRepository repo;

    public List<DonHangResponse> getAll() {
        return repo.getAllCustom();
    }

    public Page<DonHangResponse> getPage(Pageable p) {
        return repo.getPageCustom(p);
    }

    public DonHang add(DonHang dh) {
        return repo.save(dh);
    }

    public DonHang detail(Integer id) {
        return repo.findById(id).orElseThrow(() -> new ApiException("ID Khong ton tai", "CC1"));
    }

    public DonHang update(DonHang dh) {
        repo.findById(dh.getId()).orElseThrow(() -> new ApiException("ID Khong ton tai", "CC1"));
        return repo.save(dh);
    }

    public void delete(Integer id) {
        DonHang dh = repo.findById(id).orElseThrow(() -> new ApiException("ID Khong ton tai", "CC1"));
        repo.delete(dh);
    }

    public boolean existsById(Integer id) {
        return repo.existsById(id);
    }
}