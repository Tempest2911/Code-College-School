package org.example.demau.Service;

import org.example.demau.Model.NhanVien;
import org.example.demau.Repository.NhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NhanVienService {

    @Autowired
    private NhanVienRepository nhanVienRepository;

    public List<NhanVien> getAll() {
        return nhanVienRepository.findAll();
    }

    public Page<NhanVien> getAllPaged(int pageNo, int pageSize, String sortField, String sortDir, String keyword) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ?
                Sort.by(sortField).ascending() : Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

        if (keyword != null && !keyword.trim().isEmpty()) {
            return nhanVienRepository.findByHoTenContainingIgnoreCase(keyword, pageable);
        }
        return nhanVienRepository.findAll(pageable);
    }

    // Tìm kiếm theo họ tên
    public List<NhanVien> search(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return nhanVienRepository.findAll();
        }
        return nhanVienRepository.searchByHoTen(keyword.trim());
    }

    // Sắp xếp theo trường chỉ định
    public List<NhanVien> sortByField(String field, String direction) {
        Sort sort = direction.equalsIgnoreCase("asc") ?
                Sort.by(Sort.Direction.ASC, field) :
                Sort.by(Sort.Direction.DESC, field);
        return nhanVienRepository.findAll(sort);
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
