package com.example.BackEnd_LTS_edu.service;

import com.example.BackEnd_LTS_edu.entity.DangKyHoc;
import com.example.BackEnd_LTS_edu.entity.KhoaHoc;
import com.example.BackEnd_LTS_edu.entity.LoaiKhoaHoc;
import com.example.BackEnd_LTS_edu.repository.DangKyHocRepo;
import com.example.BackEnd_LTS_edu.repository.KhoaHocRepo;
import com.example.BackEnd_LTS_edu.repository.LoaiKhoaHocRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class KhoaHocService {

    @Autowired
    private KhoaHocRepo khoaHocRepo;
    @Autowired
    private LoaiKhoaHocRepo loaiKhoaHocRepo;
    @Autowired
    private DangKyHocRepo dangKyHocRepo;

    public List<KhoaHoc> getAllKhoaHoc() {
        return khoaHocRepo.findAll();
    }

    public KhoaHoc addKhoaHoc(KhoaHoc khoaHoc) {
        Optional<LoaiKhoaHoc> loaiKhoaHocOptional = loaiKhoaHocRepo.findById(khoaHoc.getLoaiKhoaHocId());
        if (loaiKhoaHocOptional.isPresent()) {
            khoaHoc.setLoaiKhoaHocId(loaiKhoaHocOptional.get().getLoaiKhoaHocId());
            khoaHoc.setLoaiKhoaHoc(loaiKhoaHocOptional.get());
            return khoaHocRepo.save(khoaHoc);
        } else {
            throw new RuntimeException("LoaiKhoaHoc with ID " + khoaHoc.getLoaiKhoaHocId() + " Not Found");
        }
    }

    public KhoaHoc updateKhoaHoc(KhoaHoc updatedKhoaHoc) {
        Optional<KhoaHoc> existingKhoaHocOptional = khoaHocRepo.findById(updatedKhoaHoc.getKhoaHocId());
        if (existingKhoaHocOptional.isPresent()) {
            KhoaHoc existingKhoaHoc = existingKhoaHocOptional.get();
            existingKhoaHoc.setTenKhoaHoc(updatedKhoaHoc.getTenKhoaHoc());
            existingKhoaHoc.setThoiGianHoc(updatedKhoaHoc.getThoiGianHoc());
            existingKhoaHoc.setGioiThieu(updatedKhoaHoc.getGioiThieu());
            existingKhoaHoc.setNoiDung(updatedKhoaHoc.getNoiDung());
            existingKhoaHoc.setHocPhi(updatedKhoaHoc.getHocPhi());
            existingKhoaHoc.setSoHocVien(updatedKhoaHoc.getSoHocVien());
            existingKhoaHoc.setSoLuongMon(updatedKhoaHoc.getSoLuongMon());
            existingKhoaHoc.setHinhAnh(updatedKhoaHoc.getHinhAnh());
            if (!existingKhoaHoc.getLoaiKhoaHocId().equals(updatedKhoaHoc.getLoaiKhoaHocId())) {
                Optional<LoaiKhoaHoc> loaiKhoaHocOptional = loaiKhoaHocRepo.findById(updatedKhoaHoc.getLoaiKhoaHocId());
                if (loaiKhoaHocOptional.isPresent()) {
                    existingKhoaHoc.setLoaiKhoaHoc(loaiKhoaHocOptional.get());
                    existingKhoaHoc.setLoaiKhoaHocId(loaiKhoaHocOptional.get().getLoaiKhoaHocId());
                } else {
                    throw new RuntimeException("LoaiKhoaHoc Not Found");
                }
            }
            return khoaHocRepo.save(existingKhoaHoc);
        } else {
            throw new RuntimeException("KhoaHoc Not Found");
        }
    }

    public String deleteKHoc(int id) {
        Optional<KhoaHoc> khoaHocOptional = khoaHocRepo.findById(id);
        if (khoaHocOptional.isPresent()) {
            KhoaHoc khoaHoc = khoaHocOptional.get();
            dangKyHocRepo.deleteAll(khoaHoc.getDangKyHocs());
            khoaHocRepo.deleteById(id);
            return "Khoa Hoc Deleted Successfully";
        } else {
            throw new IllegalArgumentException("Id không tồn tại");
        }
    }
    public List<KhoaHoc> findByTenKhoaHoc(String tenKhoaHoc) {
        if (tenKhoaHoc == null || tenKhoaHoc.isEmpty()) {
            throw new RuntimeException("TenKhoaHoc is null or empty");
        }
        return khoaHocRepo.findByTenKhoaHoc(tenKhoaHoc);
    }
    public List<KhoaHoc> getAllKhoaHocList(Pageable pageable) {
        return khoaHocRepo.findAll(pageable).getContent();
    }
}
