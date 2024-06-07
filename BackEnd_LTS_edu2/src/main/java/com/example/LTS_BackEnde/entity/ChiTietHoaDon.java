package com.example.spring01.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ChiTietHoaDon")
public class ChiTietHoaDon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chitiethoadonid")
    private Integer chiTietHoaDonId;

    @Column(name = "hoadonid")
    private Integer hoaDonId;

    @ManyToOne
    @JoinColumn(name = "hoadonid" , insertable = false , updatable = false)
    @JsonIgnore
    private HoaDon hoaDon;

    @Column(name = "sanphamid")
    private Integer sanPhamId;

    @ManyToOne
    @JoinColumn(name = "sanphamid" , insertable = false , updatable = false)
    @JsonIgnore
    private SanPham sanPham;

    @Column(name = "soluong")
    private int soLuong;

    @Column(name = "dvt")
    private String DVT;

    @Column(name = "thanhtien")
    private Double thanhTien;
}
