package org.example.demau.Service;

import org.example.demau.Model.NhanVien;
import org.example.demau.Repository.NhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NhanVienService {

    @Autowired
    private NhanVienRepository nhanVienRepository;

    public List<NhanVien> getAll() {
        return nhanVienRepository.findAll();
    }

    public void save(NhanVien nv) {
        // xử lý logic trước khi lưu
        nv.setHoTen(nv.getHoTen().trim());
        nhanVienRepository.save(nv);
    }

    public NhanVien getById(Integer id) {
        return nhanVienRepository.findById(id).orElse(null);
    }

    public void delete(Integer id) {
        nhanVienRepository.deleteById(id);
    }
}
