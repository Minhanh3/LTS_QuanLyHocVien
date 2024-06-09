package com.example.BackEnd_LTS_edu.service;

import com.example.BackEnd_LTS_edu.entity.QuyenHan;
import com.example.BackEnd_LTS_edu.entity.TaiKhoan;
import com.example.BackEnd_LTS_edu.repository.QuyenHanRepo;
import com.example.BackEnd_LTS_edu.repository.TaiKhoanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaiKhoanService {

    @Autowired
    private TaiKhoanRepo taiKhoanRepo;
    @Autowired
    private QuyenHanRepo quyenHanRepo;

    public List<TaiKhoan> getAllTaiKhoan() {
        return taiKhoanRepo.findAll();
    }

    public TaiKhoan addTaiKhoan(TaiKhoan taiKhoan) {
        Optional<QuyenHan> quyenHan = quyenHanRepo.findById(taiKhoan.getQuyenHanId());
        if (quyenHan.isPresent()) {
            return taiKhoanRepo.save(taiKhoan);
        }
        throw new NullPointerException("Tai Khoan is null");
    }

    public TaiKhoan updateTaiKhoan(TaiKhoan taiKHoanUpdate) {
        Optional<TaiKhoan> existingTaiKhoanOptional = taiKhoanRepo.findById(taiKHoanUpdate.getTaiKhoanId());
        if (existingTaiKhoanOptional.isPresent()) {
            TaiKhoan taiKhoan = existingTaiKhoanOptional.get();
            taiKhoan.setTaiKhoan(taiKHoanUpdate.getTaiKhoan());
            taiKhoan.setMatKhau(taiKHoanUpdate.getMatKhau());
            taiKhoan.setTenNguoiDung(taiKHoanUpdate.getTenNguoiDung());
            if (!taiKhoan.getQuyenHanId().equals(taiKHoanUpdate.getQuyenHanId())) {
                Optional<QuyenHan> quyenHan = quyenHanRepo.findById(taiKHoanUpdate.getQuyenHanId());
                if (quyenHan.isPresent()) {
                    taiKhoan.setQuyenHan(quyenHan.get());
                    taiKhoan.setQuyenHanId(quyenHan.get().getQuyenHanId());
                } else {
                    throw new IllegalArgumentException("QuyenHan Not Found");
                }
            }
            return taiKhoanRepo.save(taiKhoan);
        } else {
            throw new IllegalArgumentException("Tài Khoản Not Found");
        }
    }

    public String deletetTaiKhoan(int id) {
        Optional<TaiKhoan> taiKhoanOptional = taiKhoanRepo.findById(id);
        if (taiKhoanOptional.isPresent()) {
            TaiKhoan taiKhoan = taiKhoanOptional.get();
            quyenHanRepo.delete(taiKhoan.getQuyenHan());
            taiKhoanRepo.delete(taiKhoan);
            return "Tài Khoản Deleted Successfully";
        } else {
            throw new IllegalArgumentException("Tài Khoản Not Found");
        }
    }
}
