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
@Table(name = "ChuDe")
public class ChuDe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chudeid")
    private Integer chuDeId;

    @Column(name = "tenchude")
    private String tenChuDe;

    @Column(name = "noidung")
    private String noiDung;

    @Column(name = "loaibaivietid")
    private Integer loaiBaiVietId;

    @ManyToOne
    @JoinColumn(name = "loaibaivietid", insertable = false, updatable = false)
    @JsonIgnore
    private LoaiBaiViet loaiBaiViet;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "chuDe")
    @JsonIgnoreProperties
    private Set<BaiViet> baiViets;


}
