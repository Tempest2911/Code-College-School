package org.example.demau3.Service;

import org.example.demau3.Model.SanPham;
import org.example.demau3.Repository.SanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SanPhamService {

    @Autowired
    private SanPhamRepository sanphamRepository;

    public List<SanPham> getAll() {
        return sanphamRepository.findAll();
    }

    public Page<SanPham> getAllPaged(int pageNo, int pageSize, String sortField, String sortDir, String keyword) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ?
                Sort.by(sortField).ascending() : Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

        if (keyword != null && !keyword.trim().isEmpty()) {
            // Nếu Entity có trường tenSanPham:
            return sanphamRepository.findBytenSanPhamContainingIgnoreCase(keyword, pageable);
        }
        return sanphamRepository.findAll(pageable);
    }

    public List<SanPham> search(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return sanphamRepository.findAll();
        }
        // Nếu Entity có trường tenSanPham:
        return sanphamRepository.searchBytenSanPham(keyword.trim());
        //return sanphamRepository.findAll();
    }

    public List<SanPham> sortByField(String field, String direction) {
        Sort sort = direction.equalsIgnoreCase("asc") ?
                Sort.by(Sort.Direction.ASC, field) :
                Sort.by(Sort.Direction.DESC, field);
        return sanphamRepository.findAll(sort);
    }

    public void save(SanPham sanpham) {
        // Nếu Entity có trường tenSanPham:
        // sanpham .settenSanPham(sanpham .gettenSanPham().trim());
        sanphamRepository.save(sanpham);
    }

    public SanPham getById(Integer id) {
        return sanphamRepository.findById(id).orElse(null);
    }

    public void delete(Integer id) {
        sanphamRepository.deleteById(id);
    }
}
