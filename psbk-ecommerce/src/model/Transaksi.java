package model;



public class Transaksi {
	private int idPelanggan;	
	private String kota;
	private String pelanggan;
	
	private int idTransaksi;
	private Produk produk;
	private int kuantitas;
	private int biayaPembayaranProduk;
	private Kurir kurir;//biaya kurir,kota,dan nama kurir melalui objek ini
	private int totalPembayaran;
	private String nama_pelanggan;
	private String waktu_pemesanan;	
	private String waktu_pengiriman;	
	private String status_pemesanan;	
	
	//untuk input data transaksi ke database
	public Transaksi(int idTransaksi, int idKurir, int idPelanggan, int idKota,
			String waktu_pemesanan, String waktu_pengiriman,
			String status_pemesanan) {
		
		this.idTransaksi = idTransaksi;		
		this.idPelanggan = idPelanggan;		
		this.waktu_pemesanan = waktu_pemesanan;
		this.waktu_pengiriman = waktu_pengiriman;
		this.status_pemesanan = status_pemesanan;
	}
	
	//retrive untuk halaman daftar
	public Transaksi(int idTransaksi, Produk produk, int biayaPembayaranProduk,
			String nama_pelanggan, String waktu_pemesanan,
			String status_pemesanan) {
		this.idTransaksi = idTransaksi;
		this.produk = produk;
		this.biayaPembayaranProduk = biayaPembayaranProduk;
		this.nama_pelanggan = nama_pelanggan;
		this.waktu_pemesanan = waktu_pemesanan;
		this.status_pemesanan = status_pemesanan;
	}
	
	//retrive untuk halaman detail
	public Transaksi(int idTransaksi, Produk produk, int kuantitas,
			int biayaPembayaranProduk, Kurir kurir, int totalPembayaran,
			String nama_pelanggan, String waktu_pemesanan,
			String waktu_pengiriman, String status_pemesanan) {
		this.idTransaksi = idTransaksi;
		this.produk = produk;
		this.kuantitas = kuantitas;
		this.biayaPembayaranProduk = biayaPembayaranProduk;
		this.kurir = kurir;
		this.totalPembayaran = totalPembayaran;
		this.nama_pelanggan = nama_pelanggan;
		this.waktu_pemesanan = waktu_pemesanan;
		this.waktu_pengiriman = waktu_pengiriman;
		this.status_pemesanan = status_pemesanan;
	}

	

	public int getIdPelanggan() {
		return idPelanggan;
	}

	public void setIdPelanggan(int idPelanggan) {
		this.idPelanggan = idPelanggan;
	}

	public String getKota() {
		return kota;
	}

	public void setKota(String kota) {
		this.kota = kota;
	}

	public Produk getProduk() {
		return produk;
	}

	public void setProduk(Produk produk) {
		this.produk = produk;
	}

	public int getKuantitas() {
		return kuantitas;
	}

	public void setKuantitas(int kuantitas) {
		this.kuantitas = kuantitas;
	}

	public int getBiayaPembayaranProduk() {
		return biayaPembayaranProduk;
	}
	
	public String getBiayaPembayaranProdukString() {
		return helper.FormatRupiah.konversiRupiah(biayaPembayaranProduk);
	}

	public void setBiayaPembayaranProduk(int biayaPembayaranProduk) {
		this.biayaPembayaranProduk = biayaPembayaranProduk;
	}

	public Kurir getKurir() {
		return kurir;
	}

	public void setKurir(Kurir kurir) {
		this.kurir = kurir;
	}

	public int getTotalPembayaran() {
		return totalPembayaran;
	}
	
	public String getTotalPembayaranString() {
		return helper.FormatRupiah.konversiRupiah(totalPembayaran);
	}

	public void setTotalPembayaran(int totalPembayaran) {
		this.totalPembayaran = totalPembayaran;
	}

	public String getNama_pelanggan() {
		return nama_pelanggan;
	}

	public void setNama_pelanggan(String nama_pelanggan) {
		this.nama_pelanggan = nama_pelanggan;
	}

	public String getPelanggan() {
		return pelanggan;
	}

	public void setPelanggan(String pelanggan) {
		this.pelanggan = pelanggan;
	}

	public int getIdTransaksi() {
		return idTransaksi;
	}
	public void setIdTransaksi(int idTransaksi) {
		this.idTransaksi = idTransaksi;
	}
	public String getWaktu_pemesanan() {
		return waktu_pemesanan;
	}
	public void setWaktu_pemesanan(String waktu_pemesanan) {
		this.waktu_pemesanan = waktu_pemesanan;
	}
	public String getWaktu_pengiriman() {
		return waktu_pengiriman;
	}
	public void setWaktu_pengiriman(String waktu_pengiriman) {
		this.waktu_pengiriman = waktu_pengiriman;
	}


	public String getStatus_pemesanan() {
		return status_pemesanan;
	}


	public void setStatus_pemesanan(String status_pemesanan) {
		this.status_pemesanan = status_pemesanan;
	}

	
     
}
