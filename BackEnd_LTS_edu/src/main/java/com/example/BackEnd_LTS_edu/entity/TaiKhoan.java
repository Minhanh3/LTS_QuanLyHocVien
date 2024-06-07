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

import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TaiKhoan")
public class TaiKhoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "taikhoanid")
    private Integer taiKhoanId;

    @Column(name = "tennguoidung")
    private String tenNguoiDung;

    @Column(name = "taikhoan")
    private String taiKhoan;

    @Column(name = "matkhau")
    private String matKhau;

    @Column(name = "quyenhanid")
    private Integer quyenHanId;

    @ManyToOne
    @JoinColumn(name = "quyenhanid", insertable = false, updatable = false)
    @JsonIgnore
    private QuyenHan quyenHan;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "taiKhoan")
    @JsonIgnoreProperties
    private Set<DangKyHoc> dangKyHocs;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "taiKhoan")
    @JsonIgnoreProperties
    private Set<BaiViet> baiViets;
}
