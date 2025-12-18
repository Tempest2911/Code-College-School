package org.example.thicuoimon_backend.service;

import org.example.thicuoimon_backend.entity.DonDatHang;
import org.example.thicuoimon_backend.repository.DonDatHangRepo;
import org.example.thicuoimon_backend.repository.DonDatHangResponse;
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
        return donDatHangRepo.getAllDonDatHang();
    }

    // Validate và trả về được mã trạng thái HTTP 400 Bad Request khi thêm và sửa
    // dùng ApiException
    public DonDatHang add(DonDatHang donDatHang) {
        return donDatHangRepo.save(donDatHang);
    }

    public DonDatHang update(DonDatHang donDatHang) {
        donDatHangRepo.findById(donDatHang.getId());
        return donDatHangRepo.save(donDatHang);
    }

    public void delete(Integer id) {
        donDatHangRepo.deleteById(id);
    }

    public Page<DonDatHangResponse> getPage(Pageable pageable) {
        return donDatHangRepo.getPageDonDatHang(pageable);
    }


}
