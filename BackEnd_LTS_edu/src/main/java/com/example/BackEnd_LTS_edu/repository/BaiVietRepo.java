package com.example.BackEnd_LTS_edu.repository;

import com.example.BackEnd_LTS_edu.entity.BaiViet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaiVietRepo extends JpaRepository<BaiViet, Integer> {
    List<BaiViet> findByTenBaiVietContaining(String tenBaiViet);
}
