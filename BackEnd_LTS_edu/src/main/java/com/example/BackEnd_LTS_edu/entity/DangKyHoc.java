package com.example.BackEnd_LTS_edu.entity;

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

import java.time.LocalDate;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "DangKyHoc")
public class DangKyHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "khoahocid")
    private Integer khoaHocId;

    @ManyToOne
    @JoinColumn(name = "khoahocid" , insertable = false , updatable = false)
    @JsonIgnore
    private KhoaHoc khoaHoc;

    @Column(name = "hocvienid")
    private Integer hocVienId;

    @ManyToOne
    @JoinColumn(name = "hocvienid" , insertable = false , updatable = false)
    @JsonIgnore
    private HocVien hocVien;

    @Column(name = "ngaydangky")
    private LocalDate ngayDangKy;

    @Column(name = "ngaybatdau")
    private LocalDate ngayBatDau;

    @Column(name = "ngayketthuc")
    private LocalDate ngayKetThuc ;

    @Column(name = "tinhtranghocid")
    private Integer tinhTrangHocId;

    @ManyToOne
    @JoinColumn(name = "tinhtranghocid" , insertable = false , updatable = false)
    @JsonIgnore
    private TinhTrangHoc tinhTrangHoc;

    @Column(name = "taikhoanid")
    private Integer taiKhoanId;

    @ManyToOne
    @JoinColumn(name = "taikhoanid" , insertable = false , updatable = false)
    @JsonIgnore
    private TaiKhoan taiKhoan;
}
