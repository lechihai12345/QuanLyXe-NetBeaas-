/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baocaokinangnghe;

/**
 *
 * @author LCH
 */
public class LoaiXe {

    private int maloaixe;
    private String tenloaixe;
    private String hangxe;

    public LoaiXe(int MaLoaiXe, String TenLoaiXe, String HangXe) {
        this.maloaixe = MaLoaiXe;
        this.tenloaixe = TenLoaiXe;
        this.hangxe = HangXe;
    }

    public int getMaLoaixe() {
        return maloaixe;
    }

    public void setMaLoaixe(int MaLoaiXe) {
        this.maloaixe = MaLoaiXe;
    }

    public String getTenLoaiXe() {
        return tenloaixe;
    }

    public void setTenLoaiXe(String TenLoaiXe) {
        this.tenloaixe = TenLoaiXe;
    }

    public String getHangXe() {
        return hangxe;
    }

    public void setHangXe(String HangXe) {
        this.hangxe = HangXe;
    }

    public Object toObject() {
        return new Object[]{
            this.getMaLoaixe(), this.getTenLoaiXe(), this.getHangXe()
        };
    }

}
