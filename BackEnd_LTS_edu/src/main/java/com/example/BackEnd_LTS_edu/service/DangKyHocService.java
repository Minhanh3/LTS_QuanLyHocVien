package com.example.BackEnd_LTS_edu.service;

import com.example.BackEnd_LTS_edu.entity.BaiViet;
import com.example.BackEnd_LTS_edu.entity.ChuDe;
import com.example.BackEnd_LTS_edu.entity.DangKyHoc;
import com.example.BackEnd_LTS_edu.entity.KhoaHoc;
import com.example.BackEnd_LTS_edu.entity.TaiKhoan;
import com.example.BackEnd_LTS_edu.entity.TinhTrangHoc;
import com.example.BackEnd_LTS_edu.repository.BaiVietRepo;
import com.example.BackEnd_LTS_edu.repository.ChuDeRepo;
import com.example.BackEnd_LTS_edu.repository.DangKyHocRepo;
import com.example.BackEnd_LTS_edu.repository.HocVienRepo;
import com.example.BackEnd_LTS_edu.repository.KhoaHocRepo;
import com.example.BackEnd_LTS_edu.repository.TaiKhoanRepo;
import com.example.BackEnd_LTS_edu.repository.TinhTrangHocRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DangKyHocService {

    @Autowired
    private HocVienRepo hocVienRepo;

    @Autowired
    private DangKyHocRepo dangKyHocRepo;

    @Autowired
    private KhoaHocRepo khoaHocRepo;

    @Autowired
    private TaiKhoanRepo taiKhoanRepo;

    @Autowired
    private TinhTrangHocRepo tinhTrangHocRepo;

    public List<DangKyHoc> getAllDangKyHoc() {
        return dangKyHocRepo.findAll();
    }

    public void validateIds(DangKyHoc dangKyHoc) {
        if (!hocVienRepo.existsById(dangKyHoc.getHocVienId())) {
            throw new IllegalArgumentException("HocVien with given ID not found");
        }
        if (!khoaHocRepo.existsById(dangKyHoc.getKhoaHocId())) {
            throw new IllegalArgumentException("KhoaHoc with given ID not found");
        }
        if (!taiKhoanRepo.existsById(dangKyHoc.getTaiKhoanId())) {
            throw new IllegalArgumentException("TaiKhoan with given ID not found");
        }
        if (!tinhTrangHocRepo.existsById(dangKyHoc.getTinhTrangHocId())) {
            throw new IllegalArgumentException("TinhTrangHoc with given ID not found");
        }
    }

    public boolean addDangKyHoc(DangKyHoc dangKyHoc) {
        validateIds(dangKyHoc);
        dangKyHoc.setNgayDangKy(LocalDate.now());
        TinhTrangHoc tinhTrangHoc = tinhTrangHocRepo.findById(dangKyHoc.getTinhTrangHocId())
                .orElseThrow(() -> new IllegalArgumentException("TinhTrangHoc with given ID not found"));
        if (tinhTrangHoc.getTenTinhTrang().equals("Đang học chính") ||
                tinhTrangHoc.getTenTinhTrang().equals("Học xong") ||
                tinhTrangHoc.getTenTinhTrang().equals("Chưa hoàn thành")) {
            // Cập nhật số lượng học viên trong khóa học
            KhoaHoc khoaHoc = khoaHocRepo.findById(dangKyHoc.getKhoaHocId())
                    .orElseThrow(() -> new IllegalArgumentException("KhoaHoc with given ID not found"));

            if (tinhTrangHoc.getTenTinhTrang().equals("Đang học chính")) {
                dangKyHoc.setNgayBatDau(dangKyHoc.getNgayDangKy());
                if (dangKyHoc.getNgayBatDau() != null) {
                    dangKyHoc.setNgayKetThuc(dangKyHoc.getNgayBatDau().plusDays(khoaHoc.getThoiGianHoc()));
                } else {
                    throw new IllegalArgumentException("NgayBatDau is required");
                }
            }
            // Cập nhật số lượng học viên
            int soHocVien = dangKyHocRepo.countByKhoaHocIdAndTinhTrangHocId(khoaHoc.getKhoaHocId(), tinhTrangHoc.getTinhTrangHocId());
            khoaHoc.setSoHocVien(soHocVien);
            khoaHocRepo.save(khoaHoc);

            dangKyHoc.setTinhTrangHoc(tinhTrangHoc);
            dangKyHocRepo.save(dangKyHoc);
            return true;
        } else {
            throw new IllegalArgumentException("Invalid TinhTrangHoc");
        }
    }

    public void updateDangKyHoc(DangKyHoc dangKyHocUpdate) {
        Optional<DangKyHoc> dangKyHocOptional = dangKyHocRepo.findById(dangKyHocUpdate.getId());
        if (dangKyHocOptional.isPresent()) {
            DangKyHoc dangKyHoc = dangKyHocOptional.get();
            dangKyHoc.setKhoaHocId(dangKyHocUpdate.getKhoaHocId());
            dangKyHoc.setHocVienId(dangKyHocUpdate.getHocVienId());
            dangKyHoc.setNgayDangKy(dangKyHocUpdate.getNgayDangKy());
            dangKyHoc.setNgayBatDau(dangKyHocUpdate.getNgayBatDau());
            dangKyHoc.setNgayKetThuc(dangKyHocUpdate.getNgayKetThuc());
            dangKyHoc.setTinhTrangHocId(dangKyHocUpdate.getTinhTrangHocId());
            dangKyHoc.setTaiKhoanId(dangKyHocUpdate.getTaiKhoanId());
            boolean khoaHocChanged = !dangKyHoc.getKhoaHocId().equals(dangKyHocUpdate.getKhoaHocId());
            boolean hocVienChanged = !dangKyHoc.getHocVienId().equals(dangKyHocUpdate.getHocVienId());
            boolean taiKhoanChanged = !dangKyHoc.getTaiKhoanId().equals(dangKyHocUpdate.getTaiKhoanId());
            boolean tinhTrangHocChanged = !dangKyHoc.getTinhTrangHocId().equals(dangKyHocUpdate.getTinhTrangHocId());
            if (khoaHocChanged || hocVienChanged || taiKhoanChanged || tinhTrangHocChanged) {
                validateIds(dangKyHocUpdate);
                dangKyHoc.setKhoaHocId(dangKyHocUpdate.getKhoaHocId());
                dangKyHoc.setHocVienId(dangKyHocUpdate.getHocVienId());
                dangKyHoc.setTaiKhoanId(dangKyHocUpdate.getTaiKhoanId());
                dangKyHoc.setTinhTrangHocId(dangKyHocUpdate.getTinhTrangHocId());
            }
            dangKyHocRepo.save(dangKyHoc);
        } else {
            throw new IllegalArgumentException("DangKyHoc with given ID not found");
        }
    }

    public void deleteDangKyHoc(int id) {
        Optional<DangKyHoc> dangKyHocOptional = dangKyHocRepo.findById(id);
        if (dangKyHocOptional.isPresent()) {
            dangKyHocRepo.deleteById(id);
        } else {
            throw new IllegalArgumentException("DangKyHoc with given ID not found");
        }
    }

    public List<DangKyHoc> phantrang(Pageable pageable) {
        return dangKyHocRepo.findAll(pageable).getContent();
    }

}
