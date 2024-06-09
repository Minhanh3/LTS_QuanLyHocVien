package com.example.BackEnd_LTS_edu.controller;

import com.example.BackEnd_LTS_edu.entity.LoaiBaiViet;
import com.example.BackEnd_LTS_edu.entity.TaiKhoan;
import com.example.BackEnd_LTS_edu.service.LoaiBaiVietService;
import com.example.BackEnd_LTS_edu.service.QuyenHanService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("loaibaiviet")
public class LoaiBaiVietController {

    @Autowired
    private LoaiBaiVietService loaiBaiVietService;

    //done
    @GetMapping("getAllLoaiBV")
    public List<LoaiBaiViet> getAllLoaiBV() {
        return loaiBaiVietService.getAllLoaiBV();
    }

    //done
    @PostMapping("addLoaiBV")
    public ResponseEntity<String> addLoaiBV(@RequestBody LoaiBaiViet loaiBaiViet) {
        loaiBaiVietService.addLoaiBV(loaiBaiViet);
        return ResponseEntity.ok("Add Loại bài viết thành công");
    }

    @PutMapping("updateLoaiBV")
    public ResponseEntity<String> updateLoaiBV(@RequestBody LoaiBaiViet loaiBaiViet) {
        loaiBaiVietService.updateLoaiBV(loaiBaiViet);
        return ResponseEntity.ok("Update success");
    }

    @DeleteMapping("deletetLoaiBV/{id}")
    public ResponseEntity<String> deletetLoaiBV(@PathVariable int id) {
        loaiBaiVietService.deletetLoaiBV(id);
        return ResponseEntity.ok("Delete success");
    }

    @GetMapping("phantrang")
    public List<LoaiBaiViet> phantrang(Integer number, Integer size) {
        if (number == null) {
            number = 0;
        }
        if (size == null) {
            size = 10;
        }
        Pageable pageable = PageRequest.of(number, size);
        return loaiBaiVietService.phantrang(pageable);
    }

}
