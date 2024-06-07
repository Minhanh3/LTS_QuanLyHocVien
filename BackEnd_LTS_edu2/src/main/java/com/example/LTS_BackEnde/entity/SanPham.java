package com.example.spring01.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SanPham")
public class SanPham {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sanphamid")
    private Integer sanPhamId;

    @Column(name = "loaisanphamid")
    private Integer loaiSanPhamId;

    @ManyToOne
    @JoinColumn(name = "loaisanphamid" , insertable = false , updatable = false)
    private LoaiSanPham loaiSanPham;

    @Column(name = "tensanpham")
    private String tenSanPham;

    @Column(name = "giathanh")
    private Double giaThanh;

    @Column(name = "mota")
    private String moTa;

    @Column(name = "ngayhethan")
    private LocalDate ngayHetHan;

    @Column(name = "kyhieusanpham")
    private String kyHieuSanPham;

    @OneToMany(fetch = FetchType.LAZY , mappedBy = "sanPham")
    private Set<ChiTietHoaDon> chiTietHoaDons;
}
