package org.example.thicuoiki.Service;

import org.example.thicuoiki.Model.Xe;
import org.example.thicuoiki.Repository.XeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class XeService {

    @Autowired
    private XeRepository xeRepository;

    public List<Xe> getAll() {
        return xeRepository.findAll();
    }

    public Page<Xe> getAllPaged(int pageNo, int pageSize, String sortField, String sortDir, String keyword) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ?
                Sort.by(sortField).ascending() : Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

        if (keyword != null && !keyword.trim().isEmpty()) {
            return xeRepository.findByTenXeContainingIgnoreCase(keyword, pageable);
        }
        return xeRepository.findAll(pageable);
    }

    public List<Xe> search(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return xeRepository.findAll();
        }
        return xeRepository.searchByTenXe(keyword.trim());
    }

    public List<Xe> sortByField(String field, String direction) {
        Sort sort = direction.equalsIgnoreCase("asc") ?
                Sort.by(Sort.Direction.ASC, field) :
                Sort.by(Sort.Direction.DESC, field);
        return xeRepository.findAll(sort);
    }

    public void save(Xe xe) {
        xeRepository.save(xe);
    }

    public Xe getById(Integer id) {
        return xeRepository.findById(id).orElse(null);
    }

    public void delete(Integer id) {
        xeRepository.deleteById(id);
    }
}
