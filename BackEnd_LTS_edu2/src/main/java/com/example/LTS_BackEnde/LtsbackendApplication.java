package com.example.spring01;

import com.example.spring01.entity.KhachHang;
import com.example.spring01.entity.LoaiSanPham;
import com.example.spring01.entity.SanPham;
import com.example.spring01.repository.KhachHangRepo;
import com.example.spring01.repository.LoaiSanPhamRepo;
import com.example.spring01.repository.SanPhamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class Jpa05MonAnApplication implements CommandLineRunner {

    @Autowired
    private LoaiSanPhamRepo loaiSanPhamRepo;
    @Autowired
    private SanPhamRepo sanPhamRepo;
    @Autowired
    private KhachHangRepo khachHangRepo;
    @Override
    public void run(String... args) throws Exception {
        LocalDate localDate = LocalDate.now();
        for (int i = 0; i < 30; i++) {
            KhachHang khachHang = new KhachHang();
            khachHang.setHoTen("KhachHang" + i);
            khachHang.setNgaySinh(localDate.plusDays(i));
            khachHang.setSdt("0921"+i+"452"+i+"8");
            khachHangRepo.save(khachHang);
        }
        for (int i = 0; i < 20; i++) {
            LoaiSanPham loaiSanPham = new LoaiSanPham();
            loaiSanPham.setTenLoaiSanPham("Loai San Pham" + i);
            loaiSanPhamRepo.save(loaiSanPham);
        }
        for (int i = 0; i < 30; i++) {
            SanPham sanPham = new SanPham();
            sanPham.setTenSanPham("San Pham" + i);
            sanPham.setGiaThanh(5000 + (i * 100.0));
            sanPham.setNgayHetHan(localDate.plusDays(i));
            sanPham.setKyHieuSanPham("San Pham" + i + "UDPM");
            sanPham.setLoaiSanPhamId(ThreadLocalRandom.current().nextInt(1 , 20));
            sanPhamRepo.save(sanPham);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(Jpa05MonAnApplication.class, args);
    }

}
