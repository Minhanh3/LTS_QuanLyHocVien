package com.example.BackEnd_LTS_edu.controller;

import com.example.BackEnd_LTS_edu.entity.BaiViet;
import com.example.BackEnd_LTS_edu.service.BaiVietService;
import com.example.BackEnd_LTS_edu.service.LoaiBaiVietService;
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
@RequestMapping("baiviet")
public class BaiVietController {

    @Autowired
    private BaiVietService baiVietService;

    @Autowired
    private LoaiBaiVietService loaiBaiVietService;

    //done
    @GetMapping("getAllBaiViet")
    public List<BaiViet> getAllBaiViet() {
        return baiVietService.getAllBaiViet();
    }

    @PostMapping("addBaiViet")
    public ResponseEntity<String> addBaiViet(@RequestBody BaiViet baiViet) {
        baiVietService.addBaiViet(baiViet);
        return ResponseEntity.ok("Add Bài Viết thành công");
    }

    @PutMapping("updateBaiViet")
    public ResponseEntity<String> updateBaiViet(@RequestBody BaiViet baiViet) {
        baiVietService.updateBaiViet(baiViet);
        return ResponseEntity.ok("Update success");
    }

    @DeleteMapping("deleteBaiViet/{id}")
    public ResponseEntity<String> deleteBaiViet(@PathVariable int id) {
        baiVietService.deleteBaiViet(id);
        return ResponseEntity.ok("Delete success");
    }

    @GetMapping("timKiemBaiVietTheoTen")
    public ResponseEntity<List<BaiViet>> timKiemBaiVietTheoTen(@RequestParam String tenBaiViet) {
        List<BaiViet> ketQua = baiVietService.timKiemBaiVietTheoTen(tenBaiViet);
        return ResponseEntity.ok(ketQua);
    }

    @GetMapping("phantrang")
    public List<BaiViet> phantrang(Integer number, Integer size) {
        if (number == null) {
            number = 0;
        }
        if (size == null) {
            size = 10;
        }
        Pageable pageable = PageRequest.of(number, size);
        return baiVietService.phantrang(pageable);
    }

}
