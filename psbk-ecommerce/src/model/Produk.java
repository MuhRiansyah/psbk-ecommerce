package model;

public class Produk {
	
	//data-data untuk ditampilkan pada list barang
	private int idproduk;
	private String gambar;
	private String nama;	
	private int harga;	
	private int idKategori;
	private int idMerek;

	private String atributSpesifikasi1; 
	private String atributSpesifikasi2;
	private String atributSpesifikasi3;
	
	private String spesifikasi1; 
	private String spesifikasi2;
	private String spesifikasi3;
	private String merek;
	private String kategori;
	

	public Produk(){
		
	}
	
	//konstruktor untuk penyimpanan data produk ke tabel database produk
	public Produk(String nama, int harga, int idKategori,
			int idMerek,String gambar) {		
		this.gambar = gambar;
		this.nama = nama;
		this.harga = harga;
		this.idKategori = idKategori;
		this.idMerek = idMerek;
	}
	
	//konstruktor untuk menampilkan daftar produk beserta merek dan kategorinya
	public Produk(int idproduk, String gambar, String nama, int harga,String merek,String kategori){
		this.idproduk = idproduk;
		this.gambar = gambar;
		this.nama = nama;
		this.harga = harga;
		this.merek = merek;
		this.kategori = kategori;
	}	
	//konstruktor untuk menampilkan list produk semua kategori
	public Produk(int idproduk, String gambar, String nama, int harga,
			String spesifikasi1, String spesifikasi2, String spesifikasi3) {
	
		this.idproduk = idproduk;
		this.gambar = gambar;
		this.nama = nama;
		this.harga = harga;
		this.spesifikasi1 = spesifikasi1;
		this.spesifikasi2 = spesifikasi2;
		this.spesifikasi3 = spesifikasi3;
	}
	


	//konstruktor untuk menampilkan detail produk semua kategori
	public Produk(int idproduk, String gambar, String merek,String kategori,String nama, int harga,
			String spesifikasi1, String spesifikasi2, String spesifikasi3,
			String atributSpesifikasi1,String atributSpesifikasi2,String atributSpesifikasi3
			) {
	
		this.idproduk = idproduk;
		this.gambar = gambar;
		this.merek = merek;
		this.kategori = kategori;
		this.nama = nama;
		this.harga = harga;
		this.spesifikasi1 = spesifikasi1;
		this.spesifikasi2 = spesifikasi2;
		this.spesifikasi3 = spesifikasi3;
		this.atributSpesifikasi1 = atributSpesifikasi1;
		this.atributSpesifikasi2 = atributSpesifikasi2;
		this.atributSpesifikasi3 = atributSpesifikasi3;		
	}
	
	//konstruktor untuk menampilkan rekomendasi produk
	public Produk(int idproduk, String gambar, String nama, int harga) {	
		this.idproduk = idproduk;
		this.gambar = gambar;
		this.nama = nama;
		this.harga = harga;
	}
	
	public String getMerek() {
		return merek;
	}

	public void setMerek(String merek) {
		this.merek = merek;
	}

	public String getKategori() {
		return kategori;
	}

	public void setKategori(String kategori) {
		this.kategori = kategori;
	}

	public int getIdKategori() {
		return idKategori;
	}

	public void setIdKategori(int idKategori) {
		this.idKategori = idKategori;
	}

	public int getIdMerek() {
		return idMerek;
	}

	public void setIdMerek(int idMerek) {
		this.idMerek = idMerek;
	}

	public String getAtributSpesifikasi1() {
		return atributSpesifikasi1;
	}

	public void setAtributSpesifikasi1(String atributSpesifikasi1) {
		this.atributSpesifikasi1 = atributSpesifikasi1;
	}

	public String getAtributSpesifikasi2() {
		return atributSpesifikasi2;
	}

	public void setAtributSpesifikasi2(String atributSpesifikasi2) {
		this.atributSpesifikasi2 = atributSpesifikasi2;
	}

	public String getAtributSpesifikasi3() {
		return atributSpesifikasi3;
	}

	public void setAtributSpesifikasi3(String atributSpesifikasi3) {
		this.atributSpesifikasi3 = atributSpesifikasi3;
	}

	public int getIdproduk() {
		return idproduk;
	}
	
	public void setIdproduk(int idproduk) {
		this.idproduk = idproduk;
	}
	public String getGambar() {
		return gambar;
	}
	public void setGambar(String gambar) {
		this.gambar = gambar;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	
	public void setHargaInt(int hargaInt) {
		this.harga = hargaInt;
	}	
	
	public int getHargaInt(){
		return harga;
	}
	
	//untuk mengembalikan harga dalam tampilan Rupiah
	public String getHarga() {
		return helper.FormatRupiah.konversiRupiah(harga);
	}
	
	public void setHarga(int harga) {
		this.harga = harga;
	}
	
	public String getspesifikasi1() {
		return spesifikasi1;
	}

	public void setspesifikasi1(String spesifikasi1) {
		this.spesifikasi1 = spesifikasi1;
	}

	public String getspesifikasi2() {
		return spesifikasi2;
	}

	public void setspesifikasi2(String spesifikasi2) {
		this.spesifikasi2 = spesifikasi2;
	}

	public String getspesifikasi3() {
		return spesifikasi3;
	}

	public void setspesifikasi3(String spesifikasi3) {
		this.spesifikasi3 = spesifikasi3;
	}
	
	
	
}

