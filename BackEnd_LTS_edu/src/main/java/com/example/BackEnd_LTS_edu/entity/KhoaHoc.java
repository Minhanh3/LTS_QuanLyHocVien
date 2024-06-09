package com.example.BackEnd_LTS_edu.entity;

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
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "KhoaHoc")
public class KhoaHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "khoahocid")
    private Integer KhoaHocId;

    @Column(name = "tenkhoahoc")
    private String tenKhoaHoc;

    @Column(name = "thoigianhoc")
    private int thoiGianHoc;

    @Column(name = "gioithieu")
    private String gioiThieu;

    @Column(name = "noidung")
    private String noiDung;

    @Column(name = "hocphi")
    private Float hocPhi;

    @Column(name = "sohocvien")
    private int soHocVien;

    @Column(name = "soluongmon")
    private int soLuongMon;

    @Column(name = "hinhanh")
    private String hinhAnh;

    @Column(name = "loaikhoahocid")
    private Integer loaiKhoaHocId;

    @ManyToOne
    @JoinColumn(name = "loaikhoahocid", insertable = false, updatable = false)
    @JsonIgnore
    private LoaiKhoaHoc loaiKhoaHoc;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "khoaHoc")
    @JsonIgnoreProperties
    private Set<DangKyHoc> dangKyHocs;
}
