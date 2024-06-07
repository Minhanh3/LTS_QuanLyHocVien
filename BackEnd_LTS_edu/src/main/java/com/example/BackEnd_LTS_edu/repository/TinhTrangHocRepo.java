package com.example.BackEnd_LTS_edu.repository;

import com.example.BackEnd_LTS_edu.entity.TinhTrangHoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TinhTrangHocRepo extends JpaRepository<TinhTrangHoc, Integer> {
}
