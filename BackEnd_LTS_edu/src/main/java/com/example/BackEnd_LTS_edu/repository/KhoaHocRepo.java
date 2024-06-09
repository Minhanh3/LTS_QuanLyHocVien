package com.example.BackEnd_LTS_edu.repository;

import com.example.BackEnd_LTS_edu.entity.KhoaHoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KhoaHocRepo extends JpaRepository<KhoaHoc, Integer> {

    List<KhoaHoc> findByTenKhoaHoc(String tenKhoaHoc);
}
