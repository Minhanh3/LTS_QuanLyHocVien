package com.example.BackEnd_LTS_edu.service;

import com.example.BackEnd_LTS_edu.entity.ChuDe;
import com.example.BackEnd_LTS_edu.entity.LoaiBaiViet;
import com.example.BackEnd_LTS_edu.repository.ChuDeRepo;
import com.example.BackEnd_LTS_edu.repository.LoaiBaiVietRepo;
import com.example.BackEnd_LTS_edu.repository.TaiKhoanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChuDeService {

    @Autowired
    private ChuDeRepo chuDeRepo;

    @Autowired
    private LoaiBaiVietRepo loaiBaiVietRepo;

    public List<ChuDe> getAllChude() {
        return chuDeRepo.findAll();
    }

    public ChuDe addChude(ChuDe chuDe) {
        if (chuDe.getTenChuDe() == null) {
            throw new IllegalArgumentException("Tên Chủ đề không được Null");
        }
        return chuDeRepo.save(chuDe);
    }

    public ChuDe updateChude(ChuDe chuDeUpdate) {
        Optional<ChuDe> chuDeOptional = chuDeRepo.findById(chuDeUpdate.getChuDeId());
        if (chuDeOptional.isPresent()) {
            ChuDe chuDe = chuDeOptional.get();
            chuDe.setTenChuDe(chuDeUpdate.getTenChuDe());
            chuDe.setNoiDung(chuDeUpdate.getNoiDung());
            if (!chuDeUpdate.getLoaiBaiVietId().equals(chuDe.getLoaiBaiVietId())) {
                Optional<LoaiBaiViet> loaiBaiVietOptional = loaiBaiVietRepo.findById(chuDe.getLoaiBaiVietId());
                if (loaiBaiVietOptional.isPresent()) {
                    chuDe.setLoaiBaiViet(loaiBaiVietOptional.get());
                    chuDe.setLoaiBaiVietId(loaiBaiVietOptional.get().getLoaiBaiVietId());
                }
            }
            return chuDeRepo.save(chuDe);
        }
        throw new NullPointerException("Id not exists");
    }

    public String deletetChude(int id) {
        Optional<ChuDe> chuDeOptional = chuDeRepo.findById(id);
        if (chuDeOptional.isPresent()) {
            chuDeRepo.deleteById(id);
            return "Chủ đề Deleted Successfully";
        } else {
            throw new IllegalArgumentException("Chủ đề Not Found");
        }
    }

    public List<ChuDe> phantrang(Pageable pageable) {
        return chuDeRepo.findAll(pageable).getContent();
    }
}
