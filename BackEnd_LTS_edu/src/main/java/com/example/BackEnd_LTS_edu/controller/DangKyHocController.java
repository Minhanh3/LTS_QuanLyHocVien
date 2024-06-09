package com.example.BackEnd_LTS_edu.controller;

import com.example.BackEnd_LTS_edu.entity.DangKyHoc;
import com.example.BackEnd_LTS_edu.service.DangKyHocService;
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
@RequestMapping("dangkyhoc")
public class DangKyHocController {

    @Autowired
    private DangKyHocService dangKyHocService;

    @GetMapping("getAllDangKyHoc")
    public List<DangKyHoc> getAllBaiViet() {
        return dangKyHocService.getAllDangKyHoc();
    }

    @PostMapping("addDangKyHoc")
    public ResponseEntity<String> addDangKyHoc(@RequestBody DangKyHoc dangKyHoc) {
        try {
            dangKyHocService.addDangKyHoc(dangKyHoc);
            return ResponseEntity.ok("Add DangKyHoc thành công");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("updateDangKyHoc")
    public ResponseEntity<String> updateDangKyHoc(@RequestBody DangKyHoc dangKyHoc) {
        try {
            dangKyHocService.updateDangKyHoc(dangKyHoc);
            return ResponseEntity.ok("Update DangKyHoc thành công");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("deleteDangKyHoc/{id}")
    public ResponseEntity<String> deleteDangKyHoc(@PathVariable int id) {
        try {
            dangKyHocService.deleteDangKyHoc(id);
            return ResponseEntity.ok("Delete DangKyHoc thành công");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("phantrang")
    public List<DangKyHoc> phantrang(Integer number, Integer size) {
        if (number == null) {
            number = 0;
        }
        if (size == null) {
            size = 10;
        }
        Pageable pageable = PageRequest.of(number, size);
        return dangKyHocService.phantrang(pageable);
    }

}
