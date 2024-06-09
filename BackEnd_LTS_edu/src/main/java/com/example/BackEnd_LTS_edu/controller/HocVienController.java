package com.example.BackEnd_LTS_edu.controller;

import com.example.BackEnd_LTS_edu.entity.HocVien;
import com.example.BackEnd_LTS_edu.entity.KhoaHoc;
import com.example.BackEnd_LTS_edu.service.HocVienService;
import com.example.BackEnd_LTS_edu.service.KhoaHocService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("hocvien")
public class HocVienController {

    @Autowired
    private KhoaHocService khoaHocService;

    @Autowired
    private HocVienService hocVienService;

    @GetMapping("getAllhv")
    public List<HocVien> getAllHocv() {
        return hocVienService.getAllHocVien();
    }

    @PostMapping("addHv")
    public ResponseEntity<String> addKv(@RequestBody HocVien hocVien) {
        hocVienService.addHocien(hocVien);
        return ResponseEntity.ok("Add Học viên thành công");
    }

    @PutMapping("updateKh")
    public ResponseEntity<String> updatelkh(@RequestBody HocVien hocVien) {
        hocVienService.updateHocVien(hocVien);
        return ResponseEntity.ok("Update success");
    }

    @DeleteMapping("deleteHv/{id}")
    public ResponseEntity<String> deleteLkh(@PathVariable int id) {
        hocVienService.deleteHocvien(id);
        return ResponseEntity.ok("Delete success");
    }

    @GetMapping("searchTenAndEmail")
    public List<HocVien> searchTenHocViens(@RequestParam String hoTen, @RequestParam String email) {
        return hocVienService.findByTenVaEmail(hoTen, email);
    }

    @GetMapping("phanTrang")
    public List<HocVien> phanTrang(Integer number, Integer size) {
        if (number == null) {
            number = 0;
        }
        if (size == null) {
            size = 5;
        }
        Pageable hocviens = PageRequest.of(number, size);
        return hocVienService.phanTrang(hocviens);
    }
}
