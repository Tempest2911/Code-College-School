package org.example.ontap6_backend.Service;

import org.example.ontap6_backend.Entity.LichChieu;
import org.example.ontap6_backend.Repository.LichChieuRepo;
import org.example.ontap6_backend.Repository.LichChieuResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LichChieuService {

    @Autowired
    LichChieuRepo lichChieuRepo;

    public List<LichChieuResponse> getAllLichChieu() {
        return lichChieuRepo.getAllLichChieu();
    }

    public LichChieu add(LichChieu lichChieu) {
        return lichChieuRepo.save(lichChieu);
    }

    public LichChieu update(LichChieu lichChieu) {
        lichChieuRepo.findById(lichChieu.getId());
        return lichChieuRepo.save(lichChieu);
    }

    public void delete(Integer id) {
        LichChieu exit = lichChieuRepo.findById(id).get();
        lichChieuRepo.delete(exit);
    }

    public LichChieu detail(Integer id){
        return lichChieuRepo.findById(id).get();
    }

    public Page<LichChieuResponse> getPage(Pageable pageable) {
        return lichChieuRepo.getPage(pageable);
    }

}
