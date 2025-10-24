package org.example.final_sof3032.Service;

import org.example.final_sof3032.Model.Xe;
import org.example.final_sof3032.Repository.XeRepository;
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

    public Page<Xe> getAllPaged(int pageNo, int pageSize) {

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);

        return xeRepository.findAll(pageable);
    }

    public void save(Xe xe) {
        xeRepository.save(xe);
    }

    public Xe getById(Integer id) {
        return xeRepository.findById(id).orElse(null);
    }

}
