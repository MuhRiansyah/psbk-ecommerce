package beans;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import utils.JsfUtil;
import dao.ProdukDao;
import dao.TransaksiDao;
import model.Produk;
import model.Transaksi;

public class TransaksiBean {
	private Transaksi selected;
	public static void main(String[] args) {
//		TransaksiBean tb = new TransaksiBean();
//		
//		for (Transaksi t : tb.getDaftarTransaksiAdmin()) {
//			System.out.println(t.getIdTransaksi());
//			System.out.println(t.getProduk().getNama());
//		}
	}
	
	public Transaksi getSelected() {
		return selected;
	}

	public void setSelected(Transaksi selected) {
		this.selected = selected;
	}

	public List<Transaksi> getDaftarTransaksiAdmin(){
		return TransaksiDao.getInstance().getListTransaksiAdmin();
	}
	
	public void simpan(ActionEvent actionEvent) {
//		gambar = getFilename(file);
//		idKategori = Integer.parseInt(kategori);
//		idMerek = Integer.parseInt(merek);
//		Produk produk = new Produk(nama, harga, idKategori, idMerek, gambar);
//		
//		if (ProdukDao.getInstance().simpan(produk)) {
//			JsfUtil.addSuccessMessage("Simpan berhasil!");
//		} else {
//			JsfUtil.addErrorMessage("Simpan gagal!");
//		}
//
//		setNama(null);
//		setGambar(null);
//		setHarga(0);
	}
	
	public void ubah() {
//		Produk produk = selected;
		// System.out.println(produk.getNama()
		// +",idkategori : "+kategori
		// +",idproduk : "+produk.getIdproduk());

//		produk.setIdMerek(Integer.parseInt(merek));
//		produk.setIdKategori(Integer.parseInt(kategori));
//
//		if (ProdukDao.getInstance().ubah(produk)) {
//			JsfUtil.addSuccessMessage("Ubah berhasil!");
//		} else {
//			JsfUtil.addErrorMessage("Ubah gagal!");
//		}

		selected = null;
	}
	
}
