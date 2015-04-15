/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.ProdukDao;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import model.Produk;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
//import javax.faces.context.FacesContext;

import javax.faces.bean.SessionScoped;

import com.sun.javafx.beans.IDProperty;

import utils.JsfUtil;

import java.util.Map;

/**
 *
 * @author RiansyahT
 */
@SessionScoped
public class ProdukBean {
	private String opsiPengurutan;
	private List<Produk> listProduk;
	private Produk itemProduk;
	private Produk selected;
	private Produk selectedProduk;
	private String nama;
	private String merek;
	private String kategori;
	private int harga;
	private String gambar;
	private int idKategori;
	private int idMerek;

	String opsiKategori[] = new String[3];

	public void getURL() {
		Enumeration<String> lParameters;
		String sParameter = "";
		StringBuilder sbURL = new StringBuilder();
		Object oRequest = FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();

		// inisialisasi nilai awal kategori dan merek
		opsiKategori[0] = "1";
		int n = 0;

		try {
			if (oRequest instanceof HttpServletRequest) {
				sbURL.append(((HttpServletRequest) oRequest).getRequestURL()
						.toString());

				lParameters = ((HttpServletRequest) oRequest)
						.getParameterNames();

				if (lParameters.hasMoreElements()) {
					if (!sbURL.toString().contains("?")) {
						sbURL.append("?");
					} else {
						sbURL.append("&");
					}
				}

				if (sbURL.toString().contains("?")) {
					// membuat eror jika url setelah tanda ? kosong(misalkan url
					// daftarProduk.jsf?.... nah sperti itu eror)
					while (lParameters.hasMoreElements()) {

						sParameter = lParameters.nextElement();

						sbURL.append(sParameter);
						sbURL.append("=");
						opsiKategori[n] = URLEncoder.encode(
								((HttpServletRequest) oRequest)
										.getParameter(sParameter), "UTF-8");

						sbURL.append(opsiKategori[n]);

						if (lParameters.hasMoreElements()) {
							sbURL.append("&");
						}
						n++;
					}
					// testing url
					// System.out.println(sbURL.toString());
				}
			}

		} catch (Exception e) {
			// Do nothing
		}

	}

	// menggunakan cara ini , saat ganti jenis produk dan kategori malah ga
	// kebuka
	// menggunakan getUrl() didalam method get daftar produk malah ajax yang
	// ga jalan
	public void setURL(int idMerek, int idKategori) {
		this.idMerek = idMerek;
		this.idKategori = idKategori;
	}

	public List<Produk> getDaftarProduk() {
		return ProdukDao.getInstance().getListProduk(idKategori, idMerek,
				opsiPengurutan);
	}

	public List<Produk> getDaftarProdukMurah() {
		return ProdukDao.getInstance().getRecommendProduk(idKategori, idMerek,
				"harga ");
	}

	public List<Produk> getDaftarProdukAdmin() {
		listProduk = ProdukDao.getInstance().getListProduk();
		return listProduk;
	}

	public List<Produk> getDaftarMerek() {
		listProduk = ProdukDao.getInstance().getListProduk();
		return listProduk;
	}

	public void update() {

	}

	public List<Produk> getDaftarKategori() {
		listProduk = ProdukDao.getInstance().getListProduk();
		return listProduk;
	}

	// jangan dihapus, untuk memasukan nilai listopsi pengurutan
	public void setOpsiPengurutan(String opsiPengurutan) {
		this.opsiPengurutan = opsiPengurutan;
	}

	public String getOpsiPengurutan() {
		return opsiPengurutan;
	}

	public String getMerek() {
		return merek;
	}

	public void setMerek(String merek) {
		this.merek = merek;
	}

	public void setKategori(String kategori) {
		this.kategori = kategori;
	}

	public String getKategori() {
		return kategori;
	}

	private static Map<String, String> listOpsiPengurutan;
	private static Map<String, Integer> listOpsiKategori;
	private static Map<String, Integer> listOpsiMerek;

	static {
		listOpsiPengurutan = new LinkedHashMap<String, String>();
		listOpsiPengurutan.put("Nama A - Z", "nama ");
		listOpsiPengurutan.put("Nama Z - A", "nama DESC");
		listOpsiPengurutan.put("Harga Termurah", "harga ");
		listOpsiPengurutan.put("Harga Termahal", "harga DESC");

		listOpsiKategori = new LinkedHashMap<String, Integer>();
		listOpsiKategori = ProdukDao.getInstance().getListKategori();

		listOpsiMerek = new LinkedHashMap<String, Integer>();
		listOpsiMerek = ProdukDao.getInstance().getListMerek();
	}

	public Map<String, String> getListOpsiPengurutan() {
		return listOpsiPengurutan;
	}

	public Map<String, Integer> getListOpsiMerek() {
		return listOpsiMerek;
	}

	public Map<String, Integer> getListOpsiKategori() {
		return listOpsiKategori;
	}

	// list produk untuk halaman daftar produk

	public List<Produk> getUrutanProduk() {
		listProduk = ProdukDao.getInstance().getListProduk(idKategori, idMerek,
				opsiPengurutan);
		return listProduk;
	}

	public void setIdKategori(int idKategori) {
		this.idKategori = idKategori;
	}

	public int getIdKategori() {
		this.idKategori = Integer.parseInt(opsiKategori[1]);
		return idKategori;
	}

	public int getIdMerek() {
		this.idMerek = Integer.parseInt(opsiKategori[0]);
		return idMerek;
	}

	public void setIdMerek(int idMerek) {
		this.idMerek = idMerek;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public int getHarga() {
		return harga;
	}

	public void setHarga(int harga) {
		this.harga = harga;
	}

	public String getGambar() {
		return gambar;
	}

	public void setGambar(String gambar) {
		this.gambar = gambar;
	}

	public Produk getSelected() {
		return selected;
	}

	public void setSelected(Produk selected) {
		this.selected = selected;
	}

	public Produk getSelectedProduk() {
		return selectedProduk;
	}

	public void setSelectedProduk(Produk selectedProduk) {
		this.selectedProduk = selectedProduk;
	}

	// list produk untuk halaman daftar produk
	public List<Produk> getDaftarPembelianProduk(int idKategori, int idMerek) {
		listProduk = ProdukDao.getInstance().getPembelianProdukTerbanyak(
				idKategori, idMerek);
		return listProduk;
	}

	public void ubah() {
		Produk produk = selected;
		// System.out.println(produk.getNama()
		// +",idkategori : "+kategori
		// +",idproduk : "+produk.getIdproduk());

		produk.setIdMerek(Integer.parseInt(merek));
		produk.setIdKategori(Integer.parseInt(kategori));

		if (ProdukDao.getInstance().ubah(produk)) {
			JsfUtil.addSuccessMessage("Ubah berhasil!");
		} else {
			JsfUtil.addErrorMessage("Ubah gagal!");
		}

		selected = null;
	}

	public void delete() {
		if (ProdukDao.getInstance().hapus(selected)) {
			JsfUtil.addSuccessMessage("Hapus berhasil!");
		} else {
			JsfUtil.addSuccessMessage("Hapus gagal!");
		}
	}

//	public static void main(String[] args) {

//		 ProdukBean pb = new ProdukBean();
//		 List<Produk> listP =pb.getDaftarProdukMurah();
//		 for(Produk p : listP){
//		 System.out.println(p.getNama());
//		 }
//		try{
//			String path = FacesContext.getCurrentInstance().getExternalContext()
//					.getRealPath("gambarProduk");
//			path = path + "biji";
//			System.out.println(path);			
//		}catch(NullPointerException e){
//			System.err.println("kosong !");
//		}
//	}

	public void simpan(ActionEvent actionEvent) {
		gambar = getFilename(file);
		idKategori = Integer.parseInt(kategori);
		idMerek = Integer.parseInt(merek);
		String path ="";
		//melakukan pemotongan url sehingga url eclipse\apache\apache-tomcat-7.0.59\wtpwebapps\psbk-ecommerce dapat digantikans 
		path = FacesContext.getCurrentInstance().getExternalContext()
				.getRealPath("");		
		//pemotongan url dilakukan disini
		path = path.substring(0, path.indexOf("\\apache"));		
		//masukkan nama proyek
		path = path + "\\workspace\\psbk-ecommerce\\WebContent\\resources\\images\\gambarProduk\\";		
		try {
			InputStream in = file.getInputStream();
			byte[] data = new byte[in.available()];
			in.read(data);
			FileOutputStream out = new FileOutputStream(new File(path + gambar));
			out.write(data);
			in.close();
			out.close();
		} catch (IOException e) {
		} catch (NullPointerException e) {
			System.err.println("kosong !");
		}
		
		Produk produk = new Produk(nama, harga, idKategori, idMerek, gambar);
		
		if (ProdukDao.getInstance().simpan(produk)) {
			JsfUtil.addSuccessMessage("Simpan berhasil!");
		} else {
			JsfUtil.addErrorMessage("Simpan gagal!");
		}

		setNama(null);
		setGambar(null);
		setHarga(0);
	}

	// menangani inputan gambar
	private Part file;

	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
	}

	private static String getFilename(Part part) {
		for (String cd : part.getHeader("content-disposition").split(";")) {
			if (cd.trim().startsWith("filename")) {
				String filename = cd.substring(cd.indexOf('=') + 1).trim()
						.replace("\"", "");
				return filename.substring(filename.lastIndexOf('/') + 1)
						.substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
			}
		}
		return null;
	}

	public List<Produk> getDaftarPembelianProduk() {
		listProduk = ProdukDao.getInstance().getPembelianProdukTerbanyak(1, 1);
		return listProduk;
	}

	// list produk untuk halaman detail produk
	public List<Produk> getRekomendasiProduk(int idKategori, int idMerek,
			String sortby) {
		listProduk = ProdukDao.getInstance().getRecommendProduk(idKategori,
				idMerek, sortby);
		return listProduk;
	}

	public List<Produk> getProdukDibeliBersamaan(int idProduk) {
		listProduk = ProdukDao.getInstance().getRecommendBarangDibeliBersamaan(
				idProduk);
		return listProduk;
	}

	// get detail produk versi lama
	public Produk getDetailProduk(int i) {
		// cart tidak berfungsi jika ada geturl

		getURL();
		int idProduk = Integer.parseInt(opsiKategori[0]);
		itemProduk = ProdukDao.getInstance().getDetailProduk(idProduk);
		return itemProduk;
	}

	public String toDetailProduk(int idproduk) {
		itemProduk = ProdukDao.getInstance().getDetailProduk(idproduk);
		return "detailProduk";
	}

	public Produk getItemProduk() {
		return itemProduk;
	}

	public List<Produk> getListProduk() {
		return listProduk;
	}

}
