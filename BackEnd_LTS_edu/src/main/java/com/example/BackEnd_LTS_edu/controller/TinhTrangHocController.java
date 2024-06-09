package com.example.BackEnd_LTS_edu.controller;

import com.example.BackEnd_LTS_edu.entity.HocVien;
import com.example.BackEnd_LTS_edu.entity.TinhTrangHoc;
import com.example.BackEnd_LTS_edu.service.HocVienService;
import com.example.BackEnd_LTS_edu.service.KhoaHocService;
import com.example.BackEnd_LTS_edu.service.TinhTrangHocService;
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
@RequestMapping("tinhtranghoc")
public class TinhTrangHocController {

    @Autowired
    private TinhTrangHocService tinhTrangHocService;

    @Autowired
    private HocVienService hocVienService;

    @GetMapping("getAllTthoc")
    public List<TinhTrangHoc> getAllTtHoc() {
        return tinhTrangHocService.getAllTinhTrangHocs();
    }

    @PostMapping("addTthoc")
    public ResponseEntity<String> addKv(@RequestBody TinhTrangHoc tinhTrangHoc) {
        tinhTrangHocService.addTthoc(tinhTrangHoc);
        return ResponseEntity.ok("Add Tình Trạng Học thành công");
    }

    @PutMapping("updateTth")
    public ResponseEntity<String> updatelkh(@RequestBody TinhTrangHoc tinhTrangHoc) {
        tinhTrangHocService.updateTth(tinhTrangHoc);
        return ResponseEntity.ok("Update success");
    }

    @DeleteMapping("deleteTth/{id}")
    public ResponseEntity<String> deleteLkh(@PathVariable int id) {
        tinhTrangHocService.deletetinhTrangH(id);
        return ResponseEntity.ok("Delete success");
    }

}
