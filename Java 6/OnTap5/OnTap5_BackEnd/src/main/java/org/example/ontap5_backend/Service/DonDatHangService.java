package org.example.ontap5_backend.Service;

import org.example.ontap5_backend.Entity.DonDatHang;
import org.example.ontap5_backend.Repository.DonDatHangRepo;
import org.example.ontap5_backend.Repository.DonDatHangResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonDatHangService {

    @Autowired
    DonDatHangRepo donDatHangRepo;

    public List<DonDatHangResponse> getAllDonDatHang() {
        return donDatHangRepo.getAllDon();
    }

    public DonDatHang add(DonDatHang donDatHang) {
        return donDatHangRepo.save(donDatHang);
    }

    public DonDatHang update(DonDatHang donDatHang) {
        donDatHangRepo.findById(donDatHang.getId());
        return donDatHangRepo.save(donDatHang);
    }

    public void delete(Integer id){
        DonDatHang exit = donDatHangRepo.findById(id).orElseThrow();
        donDatHangRepo.delete(exit);
    }

    public DonDatHang detail(Integer id){
        return donDatHangRepo.findById(id).get();
    }

    public Page<DonDatHangResponse> getPage(Pageable pageable) {
        return donDatHangRepo.getPage(pageable);
    }
}
