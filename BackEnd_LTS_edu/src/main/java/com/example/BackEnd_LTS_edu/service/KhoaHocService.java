package com.example.BackEnd_LTS_edu.service;

import com.example.BackEnd_LTS_edu.entity.KhoaHoc;
import com.example.BackEnd_LTS_edu.entity.LoaiKhoaHoc;
import com.example.BackEnd_LTS_edu.repository.KhoaHocRepo;
import com.example.BackEnd_LTS_edu.repository.LoaiKhoaHocRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KhoaHocService {

    @Autowired
    private KhoaHocRepo khoaHocRepo;

    @Autowired
    private LoaiKhoaHocRepo loaiKhoaHocRepo;

    public List<KhoaHoc> getAllKhoaHoc() {
        return khoaHocRepo.findAll();
    }

    public KhoaHoc addKhoaHoc(KhoaHoc khoaHoc) {
        Optional<LoaiKhoaHoc> loaiKhoaHoc = loaiKhoaHocRepo.findById(khoaHoc.getKhoaHocId());
        if (khoaHoc == null) throw new NullPointerException("khoaHoc is null");
        if (!loaiKhoaHoc.isPresent()) throw new RuntimeException("KhoaHoc Not Found");
        return khoaHocRepo.save(khoaHoc);
    }

    public KhoaHoc updateKhoaHoc(KhoaHoc khoaHoc) {
        Optional<LoaiKhoaHoc> lkh = loaiKhoaHocRepo.findById(khoaHoc.getKhoaHocId());
        Optional<KhoaHoc> kh = khoaHocRepo.findById(khoaHoc.getKhoaHocId());
        if (lkh.isPresent() && kh.isPresent()) {
            KhoaHoc khoah = kh.get();
            khoah.setLoaiKhoaHoc(lkh.get());
            khoah.setLoaiKhoaHocId(lkh.get().getLoaiKhoaHocId());
            khoah.setTenKhoaHoc(kh.get().getTenKhoaHoc());
            khoah.setThoiGianHoc(kh.get().getThoiGianHoc());
            khoah.setGioiThieu(kh.get().getGioiThieu());
            khoah.setNoiDung(kh.get().getNoiDung());
            khoah.setHocPhi(kh.get().getHocPhi());
            khoah.setSoHocVien(kh.get().getSoHocVien());
            khoah.setSoLuongMon(kh.get().getSoLuongMon());
            khoah.setHinhAnh(kh.get().getHinhAnh());
            if (lkh.get().getLoaiKhoaHocId() == null) throw new NullPointerException("loaiKhoaHocId is null");
            if (kh.get().getLoaiKhoaHocId() == null) throw new NullPointerException("getLoaiKhoaHocId is null");
            if (kh.get().getTenKhoaHoc() == null) throw new NullPointerException("getTenKhoaHoc is null");
            if (kh.get().getThoiGianHoc() == null) throw new NullPointerException("getThoiGianHoc is null");
            if (kh.get().getGioiThieu() == null ) throw new NullPointerException("getGioiThieu is null");
            if (kh.get().getNoiDung() == null) throw new NullPointerException("getNoiDung is null");
            if (kh.get().getHocPhi() == null) throw new NullPointerException("getHocPhi is null");
            if (kh.get().getSoHocVien() == 0) throw new NullPointerException("getSoHocVien is null");
            if (kh.get().getSoLuongMon() == 0) throw new NullPointerException("getSoLuongMon is null");
            if (kh.get().getHinhAnh() == null) throw new NullPointerException("getHinhAnh is null");
            khoaHocRepo.save(khoah);
        }
        return khoaHoc;
    }

    public String deleteLKHoc(int id) {
        if (khoaHocRepo.findById(id).isPresent()) {
            khoaHocRepo.deleteById(id);
        } else {
            throw new IllegalArgumentException("Id không tồi tại");
        }
        return "Khoa Hoc Deleted Successfully";
    }
}
