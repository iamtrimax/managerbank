package manage;
import java.io.Serializable;
public class DataInformation implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String HoTen;
	private String NamSinh;
	private String CCCD;
	private String gioiTinh;
	private String SDT;
	private String Sothe;
	public DataInformation(String SoThe, String hoTen, String namSinh, String cCCD, String gioiTinh, String sDT) {
		Sothe = SoThe;
		HoTen = hoTen;
		NamSinh = namSinh;
		CCCD = cCCD;
		this.gioiTinh = gioiTinh;
		SDT = sDT;
	}
	public String getSothe() {
		return Sothe;
	}
	public void setSothe(String sothe) {
		Sothe = sothe;
	}
	public String getHoTen() {
		return HoTen;
	}
	public void setHoTen(String hoTen) {
		HoTen = hoTen;
	}
	public String getNamSinh() {
		return NamSinh;
	}
	public void setNamSinh(String namSinh) {
		NamSinh = namSinh;
	}
	public String getCCCD() {
		return CCCD;
	}
	public void setCCCD(String cCCD) {
		CCCD = cCCD;
	}
	public String getGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public String getSDT() {
		return SDT;
	}
	public void setSDT(String sDT) {
		SDT = sDT;
	}
	
}
