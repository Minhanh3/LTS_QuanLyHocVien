package com.example.BackEnd_LTS_edu.controller;

import com.example.BackEnd_LTS_edu.entity.KhoaHoc;
import com.example.BackEnd_LTS_edu.service.KhoaHocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("khoahoc")
public class KhoahocController {

    @Autowired
    private KhoaHocService khoaHocService;

    @GetMapping("getAllKh")
    public List<KhoaHoc> getLoaiKhoaHoc() {
        return khoaHocService.getAllKhoaHoc();
    }

    @PostMapping("addKh")
    public ResponseEntity<String> addLKh(@RequestBody KhoaHoc khoaHoc) {
        khoaHocService.addKhoaHoc(khoaHoc);
        return ResponseEntity.ok("Add Khoa học thành công");
    }

    @PutMapping("updateKh")
    public ResponseEntity<String> updatelkh(@RequestBody KhoaHoc khoaHoc) {
        khoaHocService.updateKhoaHoc(khoaHoc);
        return ResponseEntity.ok("Update success");
    }

//    @DeleteMapping("deleteLkh/{id}")
//    public ResponseEntity<String> deleteLkh(@PathVariable int id) {
//        loaiKhoaHocService.deleteLKHoc(id);
//        return ResponseEntity.ok("Delete success");
//    }
}
