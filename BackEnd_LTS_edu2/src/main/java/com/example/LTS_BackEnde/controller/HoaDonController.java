package com.example.spring01.controller;

import com.example.spring01.entity.ChiTietHoaDon;
import com.example.spring01.entity.HoaDon;
import com.example.spring01.service.HoaDonService;
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
@RequestMapping("hoadon")
public class HoaDonController {

    @Autowired
    private HoaDonService hoaDonService;

    @GetMapping("getAllHoaDon")
    public List<HoaDon> getAllHoaDon() {
        return hoaDonService.getAll();
    }

    @GetMapping("getALlCtHoadon")
    public List<ChiTietHoaDon> getAllCtHoadon() {
        return hoaDonService.getAllCTHoadon();
    }

    @PostMapping("addHoaDon")
    public ResponseEntity<HoaDon> addHoaDon(@RequestBody HoaDon hoaDon) {
        return ResponseEntity.ok(hoaDonService.addHoaDon(hoaDon));
    }

    @PostMapping("addCtHoaDon")
    public ResponseEntity<ChiTietHoaDon> addChiTietHoaDon(@RequestBody ChiTietHoaDon chiTietHoaDon) {
        return ResponseEntity.ok(hoaDonService.addCtHoaDon(chiTietHoaDon));
    }

    @DeleteMapping("deleteHoaDon/{id}")
    public ResponseEntity<String> deleteHoaDon(@PathVariable Integer id) {
        hoaDonService.deleteHoaDon(id);
        return ResponseEntity.ok("HoaDon successfully deleted");
    }

    @PutMapping("editCTHoaDon")
    public ResponseEntity<ChiTietHoaDon> editcthd(@RequestBody ChiTietHoaDon chiTietHoaDon) {
//        hoaDonService.editCtHoaDon(id);
        return ResponseEntity.ok(hoaDonService.editCtHoaDon(chiTietHoaDon));
    }

    @GetMapping("searchThoiGianTao")
    public List<HoaDon> searchThoiGianTao(@RequestParam int nam, @RequestParam int thang) {
        return hoaDonService.searchThoiGianTao(nam, thang);
    }

    @GetMapping("searchThGiantaoForDay")
    public List<HoaDon> searchThGiantao(@RequestParam int daystar, @RequestParam int dayEnd) {
        return hoaDonService.searchThoiGianTaoForDay(daystar, dayEnd);
    }

    @GetMapping("searchThGiantaoForTTien")
    public List<HoaDon> searchThGiantao2(@RequestParam int tTienStart, @RequestParam int tTienhEnd) {
        return hoaDonService.searchThoiGianTaoForTongTien(tTienStart, tTienhEnd);
    }

    @GetMapping("searchMagdOrTenHd")
    public List<HoaDon> searchThGiantao3(@RequestParam String ma, @RequestParam String tenHoaDon) {
        return hoaDonService.searchMaHoaDonOrTenHoaDon(ma, tenHoaDon);
    }

    @GetMapping("phanTrang")
    public List<HoaDon> phanTrang(Integer number, Integer size) {
        if (number == null) {
            number = 1;
        }
        if (size == null) {
            size = 1;
        }
        Pageable pageable = PageRequest.of(number, size);
        return hoaDonService.getHoaDonList(pageable);
    }
}
