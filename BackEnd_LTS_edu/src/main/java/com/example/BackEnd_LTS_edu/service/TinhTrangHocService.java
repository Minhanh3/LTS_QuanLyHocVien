package com.example.BackEnd_LTS_edu.service;

import com.example.BackEnd_LTS_edu.entity.HocVien;
import com.example.BackEnd_LTS_edu.entity.TinhTrangHoc;
import com.example.BackEnd_LTS_edu.repository.HocVienRepo;
import com.example.BackEnd_LTS_edu.repository.KhoaHocRepo;
import com.example.BackEnd_LTS_edu.repository.LoaiKhoaHocRepo;
import com.example.BackEnd_LTS_edu.repository.TinhTrangHocRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class TinhTrangHocService {

    @Autowired
    private TinhTrangHocRepo tinhTrangHocRepo;

    @Autowired
    private HocVienRepo hocVienRepo;

    public List<TinhTrangHoc> getAllTinhTrangHocs() {
        return tinhTrangHocRepo.findAll();
    }

    public TinhTrangHoc addTthoc(TinhTrangHoc tinhTrangHoc) {
        String[] ten = {"Chờ duyệt", "Đang học chính", "Học xong", "Chưa hoàn thành"};
        List<String> list = Arrays.asList(ten);
        if (!list.contains(tinhTrangHoc.getTenTinhTrang())) {
            throw new IllegalArgumentException("Tên tình trạng học phải là một trong các tên: " + list);
        }
        return tinhTrangHocRepo.save(tinhTrangHoc);
    }

    public TinhTrangHoc updateTth(TinhTrangHoc tinhTrangHocUpdate) {
        Optional<TinhTrangHoc> existingTinhTrangOptional = tinhTrangHocRepo.findById(tinhTrangHocUpdate.getTinhTrangHocId());
        if (existingTinhTrangOptional.isPresent()) {
            TinhTrangHoc tinhTrangHoc = existingTinhTrangOptional.get();
            tinhTrangHoc.setTenTinhTrang(tinhTrangHocUpdate.getTenTinhTrang());
            return tinhTrangHocRepo.save(tinhTrangHoc);
        } else {
            throw new RuntimeException("Tình trạng học Not Found");
        }
    }

    public String deletetinhTrangH(int id) {
        TinhTrangHoc tinhTrangHoc = tinhTrangHocRepo.findById(id).get();
        tinhTrangHocRepo.delete(tinhTrangHoc);
        System.out.println(tinhTrangHoc.getTinhTrangHocId());
        return "Khoa Hoc Deleted Successfully";
    }
}
