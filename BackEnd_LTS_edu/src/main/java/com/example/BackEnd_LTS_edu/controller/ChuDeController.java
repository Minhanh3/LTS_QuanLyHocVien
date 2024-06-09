package com.example.BackEnd_LTS_edu.controller;

import com.example.BackEnd_LTS_edu.entity.ChuDe;
import com.example.BackEnd_LTS_edu.entity.LoaiBaiViet;
import com.example.BackEnd_LTS_edu.service.ChuDeService;
import com.example.BackEnd_LTS_edu.service.LoaiBaiVietService;
import com.example.BackEnd_LTS_edu.service.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("chude")
public class ChuDeController {

    @Autowired
    private ChuDeService chuDeService;

    @Autowired
    private LoaiBaiVietService loaiBaiVietService;

    //done
    @GetMapping("getAllChude")
    public List<ChuDe> getAllChude() {
        return chuDeService.getAllChude();
    }

    //done
    @PostMapping("addChude")
    public ResponseEntity<String> addChude(@RequestBody ChuDe chuDe) {
        chuDeService.addChude(chuDe);
        return ResponseEntity.ok("Add Chủ Đề thành công");
    }

    @PutMapping("updateChude")
    public ResponseEntity<String> updateLoaiBV(@RequestBody ChuDe chuDe) {
        chuDeService.updateChude(chuDe);
        return ResponseEntity.ok("Update success");
    }

    @DeleteMapping("deletetChude/{id}")
    public ResponseEntity<String> deletetChude(@PathVariable int id) {
        chuDeService.deletetChude(id);
        return ResponseEntity.ok("Delete success");
    }

    @GetMapping("phantrang")
    public List<ChuDe> phantrang(Integer number, Integer size) {
        if (number == null) {
            number = 0;
        }
        if (size == null) {
            size = 10;
        }
        Pageable pageable = PageRequest.of(number, size);
        return chuDeService.phantrang(pageable);
    }

}
