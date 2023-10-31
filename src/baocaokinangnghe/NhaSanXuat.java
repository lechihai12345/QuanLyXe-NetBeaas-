/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baocaokinangnghe;

/**
 *
 * @author LCH
 */
public class NhaSanXuat {

    private int masanxuat;
    private String tensanxuat;
    private String diachisanxuat;
    public NhaSanXuat(int MaSanXuat, String TenSanXuat, String DiaChiSanXuat) {
        this.masanxuat = MaSanXuat;
        this.tensanxuat = TenSanXuat;
        this.diachisanxuat = DiaChiSanXuat;
    }
    public int getMaSanXuat() {
        return masanxuat;
    }

    public void setMaSanXuat(int MaSanXuat) {
        this.masanxuat = MaSanXuat;
    }

    public String getTenSanXuat() {
        return tensanxuat;
    }

    public void setTenSanXuat(String TenSanXuat) {
        this.tensanxuat = TenSanXuat;
    }

    public String getDiaChiSanXuat() {
        return diachisanxuat;
    }

    public void setDiaChiSanXuat(String DiaChiSanXuat) {
        this.diachisanxuat = DiaChiSanXuat;
    }

    public Object toObject() {
        return new Object[]{
            this.getMaSanXuat(), this.getTenSanXuat(), this.getDiaChiSanXuat()
        };

    }


}
