package com.example.BackEnd_LTS_edu.repository;

import com.example.BackEnd_LTS_edu.entity.HocVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HocVienRepo extends JpaRepository<HocVien, Integer> {
}
