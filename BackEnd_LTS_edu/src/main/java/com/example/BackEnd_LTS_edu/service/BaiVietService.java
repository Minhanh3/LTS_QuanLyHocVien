package com.example.BackEnd_LTS_edu.service;

import com.example.BackEnd_LTS_edu.entity.BaiViet;
import com.example.BackEnd_LTS_edu.entity.ChuDe;
import com.example.BackEnd_LTS_edu.entity.TaiKhoan;
import com.example.BackEnd_LTS_edu.repository.BaiVietRepo;
import com.example.BackEnd_LTS_edu.repository.ChuDeRepo;
import com.example.BackEnd_LTS_edu.repository.TaiKhoanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BaiVietService {

    @Autowired
    private ChuDeRepo chuDeRepo;

    @Autowired
    private TaiKhoanRepo taiKhoanRepo;

    @Autowired
    private BaiVietRepo baiVietRepo;

    public List<BaiViet> getAllBaiViet() {
        return baiVietRepo.findAll();
    }

    public boolean checkDuLieu(BaiViet baiViet) {
        if (baiViet.getTenBaiViet() == null || baiViet.getTenBaiViet().isEmpty()) {
            throw new NullPointerException("Tên bài viết không được null");
        }
        if (baiViet.getTenTacGia() == null || baiViet.getTenTacGia().isEmpty()) {
            throw new NullPointerException("Tên tác giả không được null");
        }
        if (baiViet.getNoiDung() == null || baiViet.getNoiDung().isEmpty()) {
            throw new NullPointerException("Nội dung không được null");
        }
        if (baiViet.getNoiDungNgan() == null || baiViet.getNoiDungNgan().isEmpty()) {
            throw new NullPointerException("Nội dung ngắn không được null");
        }
        if (baiViet.getHinhAnh() == null || baiViet.getHinhAnh().isEmpty()) {
            throw new NullPointerException("Hình Ảnh không được null");
        }
        return true;
    }

    public boolean addBaiViet(BaiViet baiViet) {
        Optional<ChuDe> chuDeOptional = chuDeRepo.findById(baiViet.getChuDeId());
        Optional<TaiKhoan> taiKhoanOptional = taiKhoanRepo.findById(baiViet.getTaiKhoanId());
        if (chuDeOptional.isPresent() && taiKhoanOptional.isPresent()) {
            if (!checkDuLieu(baiViet)) {
                return false;
            }
            baiViet.setThoiGianTao(LocalDate.now());
            baiVietRepo.save(baiViet);
            return true;
        }
        throw new NullPointerException("ChuDeId hoặc TaiKhoanId không hợp lệ");
    }

    public void updateBaiViet(BaiViet baiVietUpdate) {
        Optional<BaiViet> baiVietOptional = baiVietRepo.findById(baiVietUpdate.getBaiVietId());
        if (baiVietOptional.isPresent()) {
            BaiViet baiViet = baiVietOptional.get();
            baiViet.setHinhAnh(baiVietUpdate.getHinhAnh());
            baiViet.setNoiDung(baiVietUpdate.getNoiDung());
            baiViet.setNoiDungNgan(baiVietUpdate.getNoiDungNgan());
            baiViet.setTenBaiViet(baiVietUpdate.getTenBaiViet());
            baiViet.setTenTacGia(baiVietUpdate.getTenTacGia());
            baiViet.setThoiGianTao(baiVietOptional.get().getThoiGianTao());
            boolean chuDeChanged = !baiViet.getChuDeId().equals(baiVietUpdate.getChuDeId());
            boolean taiKhoanChanged = !baiViet.getTaiKhoanId().equals(baiVietUpdate.getTaiKhoanId());
            if (chuDeChanged || taiKhoanChanged) {
                Optional<ChuDe> chuDeOptional = chuDeRepo.findById(baiVietUpdate.getChuDeId());
                Optional<TaiKhoan> taiKhoanOptional = taiKhoanRepo.findById(baiVietUpdate.getTaiKhoanId());
                if (chuDeOptional.isPresent() && taiKhoanOptional.isPresent()) {
                    baiViet.setChuDe(chuDeOptional.get());
                    baiViet.setChuDeId(chuDeOptional.get().getChuDeId());
                    baiViet.setTaiKhoan(taiKhoanOptional.get());
                    baiViet.setTaiKhoanId(taiKhoanOptional.get().getTaiKhoanId());
                } else {
                    throw new IllegalArgumentException("Invalid ChuDeId or TaiKhoanId");
                }
            }
            baiVietRepo.save(baiViet);
            return;
        }
        throw new IllegalArgumentException("BaiViet with given ID not found");
    }

    public void deleteBaiViet(int id) {
        Optional<BaiViet> baiVietOptional = baiVietRepo.findById(id);
        if (baiVietOptional.isPresent()) {
            baiVietRepo.deleteById(id);
        } else {
            throw new IllegalArgumentException("Bài viết không tồn tại với ID: " + id);
        }
    }

    public List<BaiViet> phantrang(Pageable pageable) {
        return baiVietRepo.findAll(pageable).getContent();
    }

    public List<BaiViet> timKiemBaiVietTheoTen(String tenBaiViet) {
        return baiVietRepo.findByTenBaiVietContaining(tenBaiViet);
    }
}
