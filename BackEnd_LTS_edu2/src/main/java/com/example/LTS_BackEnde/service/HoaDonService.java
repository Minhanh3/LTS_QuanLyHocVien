package com.example.spring01.service;

import com.example.spring01.entity.ChiTietHoaDon;
import com.example.spring01.entity.HoaDon;
import com.example.spring01.entity.KhachHang;
import com.example.spring01.entity.SanPham;
import com.example.spring01.repository.ChiTietHoaDonRepo;
import com.example.spring01.repository.HoaDonRepo;
import com.example.spring01.repository.KhachHangRepo;
import com.example.spring01.repository.LoaiSanPhamRepo;
import com.example.spring01.repository.SanPhamRepo;
import jakarta.validation.constraints.Max;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class HoaDonService {
    @Autowired
    private LoaiSanPhamRepo loaiSanPhamRepo;

    @Autowired
    private HoaDonRepo hoaDonRepo;

    @Autowired
    private KhachHangRepo khachHangRepo;

    @Autowired
    private ChiTietHoaDonRepo chiTietHoaDonRepo;

    @Autowired
    private SanPhamRepo sanPhamRepo;

    public List<HoaDon> getAll() {
        return hoaDonRepo.getAllMax();
    }

    public List<ChiTietHoaDon> getAllCTHoadon() {
        return chiTietHoaDonRepo.findAll();
    }

    /*
    mã giao dịch được tạo ra tự động theo quy tắc YYYYMMDD_00X,
    trong đó YYYY là năm hiện tại (Vd: 2020, 2021),
     MM là tháng hiện tại (Vd: 01, 02),
     DD là ngày hiện tại (Vd: 15, 25)
     X là chỉ số thể hiện hóa đơn là hóa đơn thứ bao nhiêu trong ngày
     Ví dụ: ngày 20 tháng 10 năm 2020 hóa đơn đầu tiên => Mã giao dịch = 20201020_001
     */
    public String formatGiaoDich() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        var res = LocalDate.now().format(formatter) + "_";
        var countSoGiaoDichHomNay = 0;
        for (HoaDon hd : hoaDonRepo.findAll()) {
            if (hd.getThoiGianTao().equals(LocalDate.now())) {
                countSoGiaoDichHomNay++;
            }
        }
        if (countSoGiaoDichHomNay > 0) {
            int tmp = countSoGiaoDichHomNay + 1;
            if (tmp < 10) res = res + "00" + tmp;
            else if (tmp < 100) res = res + "0" + tmp;
            else return res + tmp;
        } else res = res + "001";
        return res;
    }

    public HoaDon addHoaDon(HoaDon hoaDon) {
        Optional<KhachHang> khachHangOptional = khachHangRepo.findById(hoaDon.getKhachHangId());
        LocalDate localDateT = LocalDate.now();
        if (khachHangOptional.isPresent()) {
            hoaDon.setMaGiaoDich(formatGiaoDich());
            hoaDon.setThoiGianTao(localDateT);
            hoaDonRepo.save(hoaDon);
        }
        return hoaDon;
    }

    public ChiTietHoaDon addCtHoaDon(ChiTietHoaDon chiTietHoaDon) {
        Optional<HoaDon> hoaDonOptional = hoaDonRepo.findById(chiTietHoaDon.getHoaDonId());
        Optional<SanPham> sanPhamOptional = sanPhamRepo.findById(chiTietHoaDon.getSanPhamId());
        try {
            if (hoaDonOptional.isPresent() && sanPhamOptional.isPresent()) {
                chiTietHoaDon.setHoaDonId(hoaDonOptional.get().getHoaDonId());
                chiTietHoaDon.setHoaDon(hoaDonOptional.get());
                chiTietHoaDon.setSanPhamId(sanPhamOptional.get().getSanPhamId());
                chiTietHoaDon.setSanPham(sanPhamOptional.get());
                chiTietHoaDon.setThanhTien(chiTietHoaDon.getSoLuong() * sanPhamOptional.get().getGiaThanh());
                hoaDonOptional.get().setTongTien(chiTietHoaDon.getThanhTien());
                hoaDonRepo.save(hoaDonOptional.get());
                chiTietHoaDonRepo.save(chiTietHoaDon);
            } else {
                hoaDonRepo.deleteById(chiTietHoaDon.getHoaDonId());
                System.out.println("Sản phẩm chưa tồn tại. Vui lòng kiểm tra lại!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chiTietHoaDon;
    }

    public ChiTietHoaDon editCtHoaDon(ChiTietHoaDon chiTietHoaDon) {
        Optional<ChiTietHoaDon> chiTietHoaDonOptional = chiTietHoaDonRepo.findById(chiTietHoaDon.getChiTietHoaDonId());
        Optional<HoaDon> hoaDonOptional = hoaDonRepo.findById(chiTietHoaDon.getHoaDonId());
        Optional<SanPham> sanPhamOptional = sanPhamRepo.findById(chiTietHoaDon.getSanPhamId());
        if (hoaDonOptional.isPresent() && sanPhamOptional.isPresent() && chiTietHoaDonOptional.isPresent()) {
            ChiTietHoaDon tietHoaDon = chiTietHoaDonOptional.get();
//            chiTietHoaDon.setChiTietHoaDonId(idCTHoaDon);
            tietHoaDon.setHoaDonId(hoaDonOptional.get().getHoaDonId());
            tietHoaDon.setSanPhamId(sanPhamOptional.get().getSanPhamId());
            tietHoaDon.setHoaDon(hoaDonOptional.get());
            tietHoaDon.setSanPham(sanPhamOptional.get());
            // hóa đơn chi tiet
            tietHoaDon.setSoLuong(chiTietHoaDon.getSoLuong());
            tietHoaDon.setDVT(chiTietHoaDon.getDVT());
            tietHoaDon.setThanhTien(chiTietHoaDon.getSoLuong() * sanPhamOptional.get().getGiaThanh());
            chiTietHoaDonRepo.save(tietHoaDon);
            return tietHoaDon;
        } else {
            throw new RuntimeException("Lỗi id ");
        }
    }

    public HoaDon deleteHoaDon(int hoaDonId) {
        Optional<HoaDon> hoaDonOptional = hoaDonRepo.findById(hoaDonId);
        if (hoaDonOptional.isPresent()) {
            Set<ChiTietHoaDon> chiTietHoaDons = hoaDonOptional.get().getChiTietHoaDonsnList();
            chiTietHoaDonRepo.deleteAll(chiTietHoaDons);
            hoaDonRepo.deleteById(hoaDonId);
        }
        return hoaDonOptional.get();
    }

    public List<HoaDon> searchThoiGianTao(int year, int month) {
        LocalDate hoaDonLocalDate = LocalDate.now();
        if (year < 0 || month < 0 || month > 12 || year > hoaDonLocalDate.getYear()) {
            throw new RuntimeException("Sai định dạng năm or tháng");
        }
        return hoaDonRepo.getAllByThoiGianTao(year, month);
    }

    public List<HoaDon> searchThoiGianTaoForDay(int dayStart, int dayEnd) {
        if (dayStart > dayEnd) {
            int back = dayStart;
            dayStart = dayEnd;
            dayEnd = back;
        }
        if (dayStart < 0 || dayEnd > 31) {
            throw new RuntimeException("Sai định dạng");
        }
        return hoaDonRepo.getAllByThoiGianTaoForDay(dayStart, dayEnd);
    }

    public List<HoaDon> searchThoiGianTaoForTongTien(int tTienStart, int tTienhEnd) {
        if (tTienStart > tTienhEnd) {
            int back = tTienStart;
            tTienStart = tTienhEnd;
            tTienhEnd = back;
        }
        if (tTienStart < 0 || tTienhEnd > 999999999) {
            throw new RuntimeException("Sai định dạng");
        }
        return hoaDonRepo.getAllByTongtienForDay(tTienStart, tTienhEnd);
    }

    //Tìm kiếm hóa đơn theo Mã giao dịch hoặc tên hóa đơn
    public List<HoaDon> searchMaHoaDonOrTenHoaDon(String ma, String tenHoaDon) {
        if (ma == null || tenHoaDon == null) {
            throw new RuntimeException("Lỗi định dạng");
        }
        return hoaDonRepo.searchHoaDonByMaGiaoDichOrTenHoaDon(ma, tenHoaDon);
    }

    public List<HoaDon> getHoaDonList(Pageable pageable) {
        return hoaDonRepo.findAll(pageable).getContent();
    }
}
