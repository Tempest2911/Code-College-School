package org.example.ontap3_backend.service;

import org.example.ontap3_backend.entity.NhanVien;
import org.example.ontap3_backend.exeption.ApiException;
import org.example.ontap3_backend.repository.NhanVienRepo;
import org.example.ontap3_backend.repository.NhanVienResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NhanVienService {
@Autowired
    NhanVienRepo repo;

    public List<NhanVienResponse> getAll(){
        return repo.getAllCustom();
    }

    public NhanVien add(NhanVien nv) {
        NhanVien exit = repo.findById(nv.getId()).orElseThrow(() -> new ApiException("ID da ton tai", "CC1"));
        return repo.save(exit);
    }

    public NhanVien update(NhanVien nv){
        NhanVien exit = repo.findById(nv.getId()).orElseThrow(() -> new ApiException("ID khong ton tai", "CC1"));
        repo.findById(exit.getId());
        return repo.save(nv);
    }

    public void delete(Integer id){
        NhanVien exit = repo.findById(id).orElseThrow(() -> new ApiException("ID khong ton tai", "CC1"));
        repo.delete(exit);
    }

    public NhanVien detail(Integer id) {
        return repo.findById(id).get();
    }

    public Page<NhanVienResponse> getPage(Pageable p) {
        return repo.getPageCustom(p);
    }



}
