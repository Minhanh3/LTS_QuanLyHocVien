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
@Table(name = "BaiViet")
public class BaiViet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "baivietid")
    private Integer baiVietId;

    @Column(name = "tenbaiviet")
    private String tenBaiViet;

    @Column(name = "thoigiantao")
    private LocalDate thoiGianTao;

    @Column(name = "tentacgia")
    private String tenTacGia;

    @Column(name = "noidung")
    private String noiDung;

    @Column(name = "noidungngan")
    private String noiDungNgan;

    @Column(name = "hinhanh")
    private String hinhAnh;

    @Column(name = "chudeid")
    private Integer chuDeId;

    @ManyToOne
    @JoinColumn(name = "chudeid", insertable = false, updatable = false)
    @JsonIgnore
    private ChuDe chuDe;

    @Column(name = "taikhoanid")
    private Integer taiKhoanId;

    @ManyToOne
    @JoinColumn(name = "taikhoanid", insertable = false, updatable = false)
    @JsonIgnore
    private TaiKhoan taiKhoan;
}
