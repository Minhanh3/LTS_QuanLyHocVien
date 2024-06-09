package com.example.BackEnd_LTS_edu.service;

import com.example.BackEnd_LTS_edu.entity.QuyenHan;
import com.example.BackEnd_LTS_edu.entity.TinhTrangHoc;
import com.example.BackEnd_LTS_edu.repository.HocVienRepo;
import com.example.BackEnd_LTS_edu.repository.QuyenHanRepo;
import com.example.BackEnd_LTS_edu.repository.TinhTrangHocRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class QuyenHanService {

    @Autowired
    private QuyenHanRepo quyenHanRepo;

    public List<QuyenHan> getAllQuyenHan() {
        return quyenHanRepo.findAll();
    }

    public QuyenHan addQuyenHan(QuyenHan quyenHan) {
        if (quyenHan == null) {
            throw new NullPointerException("quyenHan is null");
        }
        return quyenHanRepo.save(quyenHan);
    }

    public QuyenHan updateQHan(QuyenHan quyenHanUpdate) {
        Optional<QuyenHan> existingQuyenHanOptional = quyenHanRepo.findById(quyenHanUpdate.getQuyenHanId());
        if (existingQuyenHanOptional.isPresent()) {
            QuyenHan quyenHan = existingQuyenHanOptional.get();
            quyenHan.setTenQuyenHan(quyenHanUpdate.getTenQuyenHan());
            return quyenHanRepo.save(quyenHan);
        } else {
            throw new IllegalArgumentException("Quyền hạn Not Found");
        }
    }

    public String deletetQuyenH(int id) {
        QuyenHan tinhTrangHoc = quyenHanRepo.findById(id).get();
        quyenHanRepo.delete(tinhTrangHoc);
        System.out.println(tinhTrangHoc.getQuyenHanId());
        return "Khoa Hoc Deleted Successfully";
    }
}
