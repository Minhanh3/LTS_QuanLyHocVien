package com.example.BackEnd_LTS_edu.repository;

import com.example.BackEnd_LTS_edu.entity.QuyenHan;
import com.example.BackEnd_LTS_edu.entity.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuyenHanRepo extends JpaRepository<QuyenHan, Integer> {

}
