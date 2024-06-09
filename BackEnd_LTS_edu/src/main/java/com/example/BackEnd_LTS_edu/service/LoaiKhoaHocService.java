package com.example.BackEnd_LTS_edu.service;

import com.example.BackEnd_LTS_edu.entity.LoaiKhoaHoc;
import com.example.BackEnd_LTS_edu.repository.LoaiKhoaHocRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoaiKhoaHocService {

    @Autowired
    private LoaiKhoaHocRepo loaiKhoaHocRepo;

    public List<LoaiKhoaHoc> getAllLoaiKhoaHoc() {
        return loaiKhoaHocRepo.findAll();
    }

    public LoaiKhoaHoc addLKhoaHoc(LoaiKhoaHoc loaiKhoaHoc) {
        if (loaiKhoaHoc.getTenLoai() == null || loaiKhoaHoc.getTenLoai().trim().isEmpty()) {
            throw new IllegalArgumentException("Tên loại không được để trống");
        }
        return loaiKhoaHocRepo.save(loaiKhoaHoc);
    }

    public LoaiKhoaHoc updateLkhoaHoc(LoaiKhoaHoc loaiKhoaHoc) {
        Optional<LoaiKhoaHoc> kh = loaiKhoaHocRepo.findById(loaiKhoaHoc.getLoaiKhoaHocId());
        LoaiKhoaHoc khoaHoc = kh.get();
//        khoaHoc.setLoaiKhoaHocId(kh.get().getLoaiKhoaHocId());
        khoaHoc.setTenLoai(loaiKhoaHoc.getTenLoai());
        loaiKhoaHocRepo.save(loaiKhoaHoc);
        return loaiKhoaHoc;
    }

    public String deleteLKHoc(int id) {
        if (loaiKhoaHocRepo.findById(id).isPresent()) {
            loaiKhoaHocRepo.deleteById(id);
        } else {
            throw new IllegalArgumentException("Id không tồi tại");
        }
        return "Loai Khoa Hoc Deleted Successfully";
    }
}
