package model;

public class Kurir {
	private int idKurirKota;
	private int idKurir;
	private String nama;
	private int biaya;
	private String idKota;
	private String kota;

	
	public int getIdKurirKota() {
		return idKurirKota;
	}
	public void setIdKurirKota(int idKurirKota) {
		this.idKurirKota = idKurirKota;
	}
	public int getIdKurir() {
		return idKurir;
	}
	public void setIdKurir(int idKurir) {
		this.idKurir = idKurir;
	}
	public String getIdKota() {
		return idKota;
	}
	public void setIdKota(String idKota) {
		this.idKota = idKota;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public String getBiayaString() {
		return helper.FormatRupiah.konversiRupiah(biaya);
	}
	
	public int getBiaya() {
		return biaya;
	}
	
	public void setBiaya(int biaya) {
		this.biaya = biaya;
	}
	public String getKota() {
		return kota;
	}
	public void setKota(String kota) {
		this.kota = kota;
	}
	
	
}
