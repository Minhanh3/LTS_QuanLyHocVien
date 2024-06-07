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
@Table(name = "HocVien")
public class HocVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hocvienid")
    private Integer hocVienId;

    @Column(name = "hinhanh")
    private String hinhAnh ;

    @Column(name = "hoten")
    private String hoTen ;

    @Column(name = "ngaysinh")
    private LocalDate ngaySinh ;

    private String sdt ;

    private String email ;

    @Column(name = "tinhthanh")
    private String tinhThanh ;

    @Column(name = "quanhuyen")
    private String quanHuyen ;

    @Column(name = "phuongxa")
    private String phuongXa ;

    @Column(name = "sonha")
    private String soNha ;

    @OneToMany(fetch = FetchType.LAZY , mappedBy = "hocVien")
    @JsonIgnoreProperties
    private Set<DangKyHoc> dangKyHocs;
}
