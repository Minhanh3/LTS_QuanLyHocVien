package com.example.BackEnd_LTS_edu.repository;

import com.example.BackEnd_LTS_edu.entity.DangKyHoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DangKyHocRepo extends JpaRepository<DangKyHoc, Integer> {

    int countByKhoaHocIdAndTinhTrangHocId(int khoaHocId, int tinhTrangHocId);

}
