package com.example.BackEnd_LTS_edu.controller;

import com.example.BackEnd_LTS_edu.entity.QuyenHan;
import com.example.BackEnd_LTS_edu.entity.TinhTrangHoc;
import com.example.BackEnd_LTS_edu.service.HocVienService;
import com.example.BackEnd_LTS_edu.service.QuyenHanService;
import com.example.BackEnd_LTS_edu.service.TinhTrangHocService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("quyenhan")
public class QuyenHanController {

    @Autowired
    private TinhTrangHocService tinhTrangHocService;

    @Autowired
    private QuyenHanService quyenHanService;

    @GetMapping("getAllQHan")
    public List<QuyenHan> getAllTtHoc() {
        return quyenHanService.getAllQuyenHan();
    }

    @PostMapping("addQuyenHan")
    public ResponseEntity<String> addQh(@RequestBody QuyenHan quyenHan) {
        quyenHanService.addQuyenHan(quyenHan);
        return ResponseEntity.ok("Add Quyền Hạn thành công");
    }

    @PutMapping("updateQHan")
    public ResponseEntity<String> updatelkh(@RequestBody QuyenHan quyenHan) {
        quyenHanService.updateQHan(quyenHan);
        return ResponseEntity.ok("Update success");
    }

    @DeleteMapping("deleteQHan/{id}")
    public ResponseEntity<String> deleteLkh(@PathVariable int id) {
        quyenHanService.deletetQuyenH(id);
        return ResponseEntity.ok("Delete success");
    }

}
