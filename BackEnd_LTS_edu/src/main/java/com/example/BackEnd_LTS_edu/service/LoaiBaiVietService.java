package com.example.BackEnd_LTS_edu.service;

import com.example.BackEnd_LTS_edu.entity.LoaiBaiViet;
import com.example.BackEnd_LTS_edu.entity.QuyenHan;
import com.example.BackEnd_LTS_edu.entity.TaiKhoan;
import com.example.BackEnd_LTS_edu.repository.LoaiBaiVietRepo;
import com.example.BackEnd_LTS_edu.repository.QuyenHanRepo;
import com.example.BackEnd_LTS_edu.repository.TaiKhoanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoaiBaiVietService {

    @Autowired
    private TaiKhoanRepo taiKhoanRepo;

    @Autowired
    private LoaiBaiVietRepo loaiBaiVietRepo;

    public List<LoaiBaiViet> getAllLoaiBV() {
        return loaiBaiVietRepo.findAll();
    }

    public LoaiBaiViet addLoaiBV(LoaiBaiViet loaiBaiViet) {
        if (loaiBaiViet.getTenLoai() == null) {
            throw new IllegalArgumentException("Ten loai không được Null");
        }
        return loaiBaiVietRepo.save(loaiBaiViet);
    }

    public LoaiBaiViet updateLoaiBV(LoaiBaiViet loaiBaiVietUpdate) {
            Optional<LoaiBaiViet> loaiBaiVietOptional = loaiBaiVietRepo.findById(loaiBaiVietUpdate.getLoaiBaiVietId());
            if (loaiBaiVietOptional.isPresent()) {
                LoaiBaiViet loaiBaiViet = loaiBaiVietOptional.get();
                loaiBaiViet.setTenLoai(loaiBaiVietUpdate.getTenLoai());
                return loaiBaiVietRepo.save(loaiBaiViet);
            }
            throw new NullPointerException("Id not exists");
    }

    public String deletetLoaiBV(int id) {
        Optional<LoaiBaiViet> loaiBaiVietOptional = loaiBaiVietRepo.findById(id);
        if (loaiBaiVietOptional.isPresent()) {
            loaiBaiVietRepo.deleteById(id);
            return "Loại bài viết Deleted Successfully";
        } else {
            throw new IllegalArgumentException("Loại bài viết Not Found");
        }
    }

    public List<LoaiBaiViet> phantrang(Pageable pageable) {
        return loaiBaiVietRepo.findAll(pageable).getContent();
    }
}
