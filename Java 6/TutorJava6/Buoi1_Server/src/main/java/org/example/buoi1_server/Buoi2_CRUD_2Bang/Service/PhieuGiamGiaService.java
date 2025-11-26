// language: java
package org.example.buoi1_server.Buoi2_CRUD_2Bang.Service;

import org.example.buoi1_server.Buoi2_CRUD_2Bang.Enity.LoaiPhieuGiamGia;
import org.example.buoi1_server.Buoi2_CRUD_2Bang.Enity.PhieuGiamGia;
import org.example.buoi1_server.Buoi2_CRUD_2Bang.Model.Request.PhieuGiamGiaRequest;
import org.example.buoi1_server.Buoi2_CRUD_2Bang.Model.Response.PhieuGiamGiaResponse;
import org.example.buoi1_server.Buoi2_CRUD_2Bang.Repository.LoaiPhieuGiamGiaRepo;
import org.example.buoi1_server.Buoi2_CRUD_2Bang.Repository.PhieuGiamGiaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhieuGiamGiaService {

    @Autowired
    private PhieuGiamGiaRepository phieuGiamGiaRepository;

    @Autowired
    private LoaiPhieuGiamGiaRepo loaiPhieuGiamGiaRepo;

    public List<PhieuGiamGiaResponse> getAll() {
        return phieuGiamGiaRepository.hienThiDanhSachPhieuGiamGia();
    }

    public PhieuGiamGiaResponse getOne(int id) {
        return phieuGiamGiaRepository.detailPhieuGiamGia(id);
    }

    public Page<PhieuGiamGiaResponse> phanTrang(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return phieuGiamGiaRepository.phanTrangPhieuGiamGia(pageable);
    }

    public void removePhieuGiamGia(int id) {
        phieuGiamGiaRepository.deleteById(id);
    }

    public void addPhieuGiamGia(PhieuGiamGiaRequest request) {
        PhieuGiamGia pgg = new PhieuGiamGia();
        BeanUtils.copyProperties(request,pgg); // Mapping thong qua ten cua thuoc tinh
// id cua loai phieu giam gia => tim duoc loai phieu giam gia trong csdl roi set lai vè object phieu giam gia
        LoaiPhieuGiamGia loaiPhieuGiamGia = loaiPhieuGiamGiaRepo.findById(request.getLoaiPhieuID()).get();
        // set lai object loai phieu giam gia vai doi tuong phieu giam gia
        pgg.setLoaiPhieu(loaiPhieuGiamGia);
        phieuGiamGiaRepository.save(pgg);
    }

    public void updatePhieuGiamGia(PhieuGiamGiaRequest request, Integer idCanUpdate) {
        PhieuGiamGia pgg = phieuGiamGiaRepository.findById(idCanUpdate).get();
        BeanUtils.copyProperties(request,pgg);

        LoaiPhieuGiamGia loaiPhieuGiamGia = loaiPhieuGiamGiaRepo.findById(request.getLoaiPhieuID()).get();
        pgg.setLoaiPhieu(loaiPhieuGiamGia);
        phieuGiamGiaRepository.save(pgg);
    }
}
