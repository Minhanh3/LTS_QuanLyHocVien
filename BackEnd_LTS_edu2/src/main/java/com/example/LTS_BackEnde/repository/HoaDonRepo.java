package com.example.spring01.repository;

import com.example.spring01.entity.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HoaDonRepo extends JpaRepository<HoaDon, Integer> {

    @Query(value = "select hd from HoaDon hd order by hd.thoiGianTao desc"
     //       , nativeQuery = true
    )
    List<HoaDon> getAllMax();

    @Query(value = "select hd from HoaDon hd where year(hd.thoiGianTao) = ?1 and month(hd.thoiGianTao) = ?2")
    List<HoaDon> getAllByThoiGianTao(int nam , int thang);

    @Query(value = "select hd from HoaDon hd where day(hd.thoiGianTao) between ?1 and ?2 ")
    List<HoaDon> getAllByThoiGianTaoForDay(int star , int end);

    @Query(value = "select hd from HoaDon hd where hd.tongTien between ?1 and ?2 ")
    List<HoaDon> getAllByTongtienForDay(int star , int end);

//    @Query(value = "")
    List<HoaDon> searchHoaDonByMaGiaoDichOrTenHoaDon(String maGiaoDich , String TenHoaDon);
}
