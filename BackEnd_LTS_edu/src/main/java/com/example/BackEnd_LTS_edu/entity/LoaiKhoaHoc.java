package com.example.BackEnd_LTS_edu.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "LoaiKhoaHoc")
public class LoaiKhoaHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loaikhoahocid")
    private Integer loaiKhoaHocId;

    @Column(name = "tenloai")
    private String tenLoai;

    @OneToMany(fetch = FetchType.LAZY , mappedBy = "loaiKhoaHoc")
    @JsonIgnoreProperties
    private Set<KhoaHoc> khoaHocs;
}
