package com.example.BackEnd_LTS_edu.controller;

import com.example.BackEnd_LTS_edu.entity.LoaiKhoaHoc;
import com.example.BackEnd_LTS_edu.service.loaikhoahoc.LoaiKhoaHocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping("loaikhoahoc")
public class LoaikhoahocController {

    @Autowired
    private LoaiKhoaHocService loaiKhoaHocService;

    @GetMapping("getAlllkh")
    public List<LoaiKhoaHoc> getLoaiKhoaHoc() {
        return loaiKhoaHocService.getAllLoaiKhoaHoc();
    }

    @PostMapping("addLkh")
    public ResponseEntity<LoaiKhoaHoc> addLKh(@RequestBody LoaiKhoaHoc loaiKhoaHoc) {
        return new ResponseEntity<>(loaiKhoaHocService.addLKhoaHoc(loaiKhoaHoc), HttpStatus.CREATED);
    }

    @PutMapping("updateLkh")
    public LoaiKhoaHoc updatelkh(@RequestBody LoaiKhoaHoc loaiKhoaHoc) {
        return loaiKhoaHocService.updateLkhoaHoc(loaiKhoaHoc);
    }

    @DeleteMapping("deleteLkh/{id}")
    public ResponseEntity<String> deleteLkh(@PathVariable int id) {
        loaiKhoaHocService.deleteLKHoc(id);
        return ResponseEntity.ok("Delete success");
    }
}
