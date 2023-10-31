/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baocaokinangnghe;

/**
 *
 * @author LCH
 */
public class Xe {

    private int maxe;
    private String tenxe;
    private String loaixe;
    private String mauxe;
    private String nhasanxuat;
    public Xe(int Maxe, String TenXe, String LoaiXe,String MauXe,String NhaSanXuat) {
        this.maxe = Maxe;
        this.tenxe = TenXe;
        this.loaixe = LoaiXe;
        this.mauxe = MauXe;
        this.nhasanxuat = NhaSanXuat;
    }

  

    
    public int getMaXe() {
        return maxe;
    }

    public void setMaXe(int Maxe) {
        this.maxe = Maxe;
    }

    public String getTenXe() {
        return tenxe;
    }

    public void setTenXe(String TenXe) {
        this.tenxe = TenXe;
    }

    public String getLoaiXe() {
        return loaixe;
    }

    public void setLoaiXe(String LoaiXe) {
        this.loaixe = LoaiXe;
    }

    public String getMauXe() {
        return mauxe;
    }

    public void setMauXe(String MauXe) {
        this.mauxe = MauXe;
    }

    public String getNhaSanXuat() {
        return nhasanxuat;
    }

    public void setNhaSanXuat(String NhaSanXuat) {
        this.nhasanxuat = NhaSanXuat;
    }

    public Object toObject() {
        return new Object[]{
            this.getMaXe(), this.getTenXe(), this.getLoaiXe(), this.getMauXe(), this.getNhaSanXuat()
        };

    }

}
