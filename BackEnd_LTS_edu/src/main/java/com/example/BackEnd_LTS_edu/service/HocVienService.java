package com.example.BackEnd_LTS_edu.service;

import com.example.BackEnd_LTS_edu.entity.HocVien;
import com.example.BackEnd_LTS_edu.repository.HocVienRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HocVienService {

    @Autowired
    private HocVienRepo hocVienRepo;

    public List<HocVien> getAllHocVien() {
        return hocVienRepo.findAll();
    }

    public HocVien addHocien(HocVien hocVien) {
        HocVien hocViens = hocVienRepo.findById(hocVien.getHocVienId()).get();
        if (hocVien.getEmail().equalsIgnoreCase(hocViens.getEmail()))
            throw new RuntimeException("Email đã tồi tại");
        if (hocVien.getSdt().equalsIgnoreCase(hocViens.getSdt()))
            throw new RuntimeException("Số điẹn thoại đã tồi tại");
        return hocVienRepo.save(hocVien);
    }

    public HocVien updateHocVien(HocVien updatedHocVien) {
        Optional<HocVien> existingHocVienOptional = hocVienRepo.findById(updatedHocVien.getHocVienId());
        if (existingHocVienOptional.isPresent()) {
            HocVien hocVien = existingHocVienOptional.get();
            hocVien.setHocVienId(updatedHocVien.getHocVienId());
            hocVien.setHinhAnh(updatedHocVien.getHinhAnh());
            hocVien.setHoTen(updatedHocVien.getHoTen());
            hocVien.setNgaySinh(updatedHocVien.getNgaySinh());
            hocVien.setSdt(updatedHocVien.getSdt());
            hocVien.setEmail(updatedHocVien.getEmail());
            hocVien.setTinhThanh(updatedHocVien.getTinhThanh());
            hocVien.setQuanHuyen(updatedHocVien.getQuanHuyen());
            hocVien.setPhuongXa(updatedHocVien.getPhuongXa());
            hocVien.setSoNha(updatedHocVien.getSoNha());
            return hocVienRepo.save(hocVien);
        } else {
            throw new RuntimeException("HocVien Not Found");
        }
    }

    public String deleteHocvien(int id) {
        HocVien hocVienOptional = hocVienRepo.findById(id).get();
        hocVienRepo.delete(hocVienOptional);
        System.out.println(hocVienOptional.getHocVienId());
        return "Khoa Hoc Deleted Successfully";
    }

    public List<HocVien> findByTenVaEmail(String hoTen, String email) {

        return hocVienRepo.findByHoTenOrEmail(hoTen, email);
    }

    public List<HocVien> phanTrang(Pageable pageable) {
        return hocVienRepo.findAll(pageable).getContent();
    }
}
