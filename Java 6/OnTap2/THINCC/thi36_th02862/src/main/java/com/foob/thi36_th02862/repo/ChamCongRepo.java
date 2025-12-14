package com.foob.thi36_th02862.repo;

import com.foob.thi36_th02862.model.ChamCong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChamCongRepo extends JpaRepository<ChamCong, Integer> {
}
