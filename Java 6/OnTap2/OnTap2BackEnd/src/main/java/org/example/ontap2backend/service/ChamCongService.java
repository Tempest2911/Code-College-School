package org.example.ontap2backend.service;

import lombok.Setter;
import org.example.ontap2backend.entity.ChamCong;
import org.example.ontap2backend.exception.ApiException;
import org.example.ontap2backend.repository.ChamCongRepo;
import org.example.ontap2backend.repository.ChamCongResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChamCongService {
    @Autowired
    ChamCongRepo repo;

    public List<ChamCongResponse> getAll(){
        return repo.getAllCustom();
    }

    public Page<ChamCongResponse> getPage(Pageable p) {
        return repo.getPageCustom(p);
    }

    public ChamCong add(ChamCong cc){
        return repo.save(cc);
    }

    public ChamCong update(ChamCong cc){
        repo.findById(cc.getId());
        return repo.save(cc);
    }

    public void delete(Integer id){
        repo.deleteById(id);
    }

    public ChamCong detail(Integer id) {
        return repo.findById(id).get();
    }

}
