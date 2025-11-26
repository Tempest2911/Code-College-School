package org.example.buoi1_server.Buoi1_CRUD_1Bang.Service;

import org.example.buoi1_server.Buoi1_CRUD_1Bang.Enity.CaSi;
import org.example.buoi1_server.Buoi1_CRUD_1Bang.Model.Request.CaSiRequest;
import org.example.buoi1_server.Buoi1_CRUD_1Bang.Model.Response.CaSiResponse;
import org.example.buoi1_server.Buoi1_CRUD_1Bang.Repository.CaSiRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaSiService {

    @Autowired
    private CaSiRepository caSiRepository;

    public List<CaSiResponse> getAllCaSi() {
        return caSiRepository.hienThiDanhSachCaSi();
    }

    public void deleteCaSi(int id) {
        caSiRepository.deleteById(id);
    }

    public CaSiResponse detailCaSi(int id) {
        return caSiRepository.detailCaSi(id);
    }

    public Page<CaSiResponse> phanTrang(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return caSiRepository.hienThiDanhSachPhanTrang(pageable);
    }

    public void add(CaSiRequest caSiRequest) {
        // co request => can entity
        // Cach 1: tu viet ham chuyen doi
        CaSi caSi = new CaSi();
        BeanUtils.copyProperties(caSiRequest, caSi);
        caSiRepository.save(caSi);
    }

    public void update(CaSiRequest caSiRequest, int id) {
        CaSi caSi = caSiRepository.findById(id).get();
        BeanUtils.copyProperties(caSiRequest, caSi); // TEN REQUEST PHAI TRUNG TEN ENTITY
        caSiRepository.save(caSi);
    }

    //    private CaSi convertRequestToEntity(CaSiRequest caSiRequest) {
//        //Chuyen doi tu request -> entity
//        CaSi caSi = new CaSi();
//        caSi.setId(caSiRequest.getId());
//        caSi.setTenCaSi(caSiRequest.getTenCaSi());
//        caSi.setQueQuan(caSiRequest.getQueQuan());
//        caSi.setTuoi(caSiRequest.getTuoi());
//        caSi.setCongTy(caSiRequest.getCongTy());
//        return caSi;
//    }
}
