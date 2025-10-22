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
    private NhanVienRepository nhanvienRepository;

    public List<NhanVien> getAll() {
        return nhanvienRepository.findAll();
    }

    public Page<NhanVien> getAllPaged(int pageNo, int pageSize, String sortField, String sortDir, String keyword) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ?
                Sort.by(sortField).ascending() : Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

        if (keyword != null && !keyword.trim().isEmpty()) {
            // Nếu Entity có trường hoTen:
            return nhanvienRepository.findByHoTenContainingIgnoreCase(keyword, pageable);
        }
        return nhanvienRepository.findAll(pageable);
    }

    public List<NhanVien> search(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return nhanvienRepository.findAll();
        }
        // Nếu Entity có trường hoTen:
        return nhanvienRepository.searchByHoTen(keyword.trim());
        //return nhanvienRepository.findAll();
    }

    public List<NhanVien> sortByField(String field, String direction) {
        Sort sort = direction.equalsIgnoreCase("asc") ?
                Sort.by(Sort.Direction.ASC, field) :
                Sort.by(Sort.Direction.DESC, field);
        return nhanvienRepository.findAll(sort);
    }

    public void save(NhanVien nhanvien) {
        // Nếu Entity có trường hoTen:
        // nhanvien .setHoTen(nhanvien .getHoTen().trim());
        nhanvienRepository.save(nhanvien);
    }

    public NhanVien getById(Integer id) {
        return nhanvienRepository.findById(id).orElse(null);
    }

    public void delete(Integer id) {
        nhanvienRepository.deleteById(id);
    }
}
