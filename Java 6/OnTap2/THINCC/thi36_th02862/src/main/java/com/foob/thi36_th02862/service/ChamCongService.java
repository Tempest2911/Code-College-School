package com.foob.thi36_th02862.service;

import java.util.List;

import com.foob.thi36_th02862.exception.ApiException;
import com.foob.thi36_th02862.model.ChamCong;
import com.foob.thi36_th02862.repo.ChamCongRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChamCongService {

    @Autowired
    private ChamCongRepo repo;

    public List<ChamCong> getAll() {
        return repo.findAll();
    }

    public void create(ChamCong chamCong) {
        repo.save(chamCong);
    }

    public void delete(Integer id) {
        ChamCong existing = repo.findById(id).orElseThrow(() -> new ApiException("ID Khong ton tai", "CC1"));
        repo.delete(existing);
    }

    public void update(Integer id, ChamCong chamCong) {
        ChamCong existing = repo.findById(id).orElseThrow(() -> new ApiException("ID Khong ton tai", "CC1"));
        chamCong.setId(existing.getId());
        repo.save(chamCong);
    }
}
