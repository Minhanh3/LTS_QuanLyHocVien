package com.example.spring01.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "HoaDon")
public class HoaDon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hoadonid")
    private Integer hoaDonId;

    @Column(name = "khachhangid")
    private Integer khachHangId;

    @ManyToOne
    @JoinColumn(name = "khachhangid", insertable = false, updatable = false)
    @JsonIgnore
    private KhachHang khachHang;

    @Column(name = "tenhoadon")
    private String tenHoaDon;

    @Column(name = "magiaodich")
    private String maGiaoDich;

    @Column(name = "thoigiantao")
    private LocalDate thoiGianTao;

    @Column(name = "thoiGianCapNhat")
    private LocalDate thoiGianCapNhat;

    @Column(name = "ghighu")
    private String ghiChu;

    @Column(name = "tongtien")
    private Double tongTien;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hoaDon")
    @JsonIgnoreProperties
    private Set<ChiTietHoaDon> chiTietHoaDonsnList;

}
