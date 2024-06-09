package com.example.BackEnd_LTS_edu.repository;

import com.example.BackEnd_LTS_edu.entity.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaiKhoanRepo extends JpaRepository<TaiKhoan, Integer> {
    boolean existsByTaiKhoan(String taiKhoan);
    List<TaiKhoan> findByTaiKhoan(String tentaiKhoan);
}
