/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import koneksi.Koneksi;
import model.Kurir;
import model.Produk;
import model.Transaksi;

public class TransaksiDao {

	Connection conn;

	private static TransaksiDao transaksiDao;

	public static TransaksiDao getInstance() {
		if (transaksiDao == null) {
			transaksiDao = new TransaksiDao();
		}
		return transaksiDao;
	}

	public static void main(String[] args) {
		// Transaksi t = new Transaksi(0, 1, 3, 3, null, null, true);
		// getInstance().simpan(t);
//		getInstance().getListTransaksiAdmin();
	}

	public TransaksiDao() {
		conn = Koneksi.connection();
	}

	public boolean simpan(Transaksi Transaksi) {
		boolean toReturn;
		try {
			Statement st = conn.createStatement();
			// waktu pengiriman diset null dan status pemesanan diset false
			// selama bukti pembayaran belum dikirimkan customer
			st.execute("INSERT INTO transaksi "
					+ "(idtransaksi, idkurir, idpelanggan, idkota, waktu_pemesanan, waktu_pengiriman,status_pemesanan) 	"
					+ "VALUES (default," + Transaksi.getKurir().getIdKota()
					+ "," + Transaksi.getIdPelanggan() + ","
					+ Transaksi.getKurir().getKota()
					+ ",default,default,default)");
			toReturn = true;
		} catch (SQLException ee) {
			toReturn = false;
			ee.printStackTrace();
		}

		return toReturn;
	}
	
	public boolean menyetujuiPemesanan(Transaksi Transaksi) {
		try {
			Statement st = conn.createStatement();
			st.execute("UPDATE Transaksi SET waktu_pengiriman = default,status_pemesanan = true"
					+ " WHERE idtransaksi = " + Transaksi.getIdTransaksi());
			return true;
		} catch (SQLException ee) {
			ee.printStackTrace();
			return false;
		}

	}

	// perubahan data transaksi,asumsi nya fitur pengubahan data transaksi
	// ditiadakan dulu
	// public boolean ubahKota(Transaksi Transaksi) {
	// try {
	// Statement st = conn.createStatement();
	// st.execute("UPDATE Transaksi SET idkota = "+ Transaksi.getIdKota() +
	// " WHERE idtransaksi = "
	// + Transaksi.getIdTransaksi());
	// return true;
	// } catch (SQLException ee) {
	// ee.printStackTrace();
	// return false;
	// }
	// }
	public List<Transaksi> getListTransaksiAdmin() {
		List<Transaksi> listTransaksi = new ArrayList<Transaksi>();		
		try {
			Statement st = conn.createStatement();
			String sql = "SELECT t.idtransaksi ,pr.nama as produk,pr.harga as harga_produk,kuantitas, "
					+ "(pt.kuantitas*pr.harga) as biaya_pembayaran_produk, kk.biaya as biaya_kurir, ((pt.kuantitas*pr.harga) + kk.biaya) as total_pembayaran, "
					+ "k.nama as kurir, p.nama as pelanggan,kota,waktu_pemesanan,"
					+ "waktu_pengiriman, status_pemesanan FROM transaksi t "
					+ "LEFT JOIN produk_transaksi pt ON pt.idtransaksi = t.idtransaksi "
					+ "LEFT JOIN produk pr ON pr.idproduk = pt.idproduk "
					+ "LEFT JOIN pelanggan p ON t.idpelanggan = p.idpelanggan "
					+ "LEFT JOIN kurir_kota kk ON kk.id_kurir_kota = t.id_kurir_kota "
					+ "LEFT JOIN kurir k ON k.idkurir = kk.idkurir "
					+ "LEFT JOIN kota kt ON kt.idkota = kk.idkota ";			
			sql += "ORDER BY t.idtransaksi,waktu_pemesanan";			
			ResultSet query = st.executeQuery(sql);			
			while (query.next()) {
				String status = "tertunda";
				if (query.getBoolean("status_pemesanan")) {
					status = "disetujui";
				}
				Produk produk = new Produk();
				produk.setNama(query.getString("produk"));
				produk.setHarga(query.getInt("harga_produk"));
				Kurir kurir = new Kurir();
				kurir.setNama(query.getString("kurir"));
				kurir.setBiaya(query.getInt("biaya_kurir"));
				kurir.setKota(query.getString("kota"));				
				listTransaksi.add(new Transaksi(query.getInt("idtransaksi"), produk,
						query.getInt("kuantitas"),
						query.getInt("biaya_pembayaran_produk"), kurir,
						query.getInt("total_pembayaran"),
						query.getString("pelanggan"),
						query.getString("waktu_pemesanan"),
						query.getString("waktu_pengiriman"), status)
				);				
			}
		} catch (SQLException ee) {
			ee.printStackTrace();
		}
		return listTransaksi;
	}

	public Transaksi getDetailTransaksi(int idTransaksi) {
		Transaksi transaksi = null;
		try {			
			Statement st = conn.createStatement();
			String sql = "SELECT t.idtransaksi,pr.nama as produk,pr.harga as harga_produk,kuantitas, "
					+ "(pt.kuantitas*pr.harga) as biaya_pembayaran_produk, kk.biaya as biaya_kurir, ((pt.kuantitas*pr.harga) + kk.biaya) as total_pembayaran, "
					+ "k.nama as kurir, p.nama as pelanggan,kota,waktu_pemesanan,"
					+ "waktu_pengiriman, status_pemesanan FROM transaksi t "
					+ "LEFT JOIN produk_transaksi pt ON pt.idtransaksi = t.idtransaksi "
					+ "LEFT JOIN produk pr ON pr.idproduk = pt.idproduk "
					+ "LEFT JOIN pelanggan p ON t.idpelanggan = p.idpelanggan "
					+ "LEFT JOIN kurir_kota kk ON kk.id_kurir_kota = t.id_kurir_kota "
					+ "LEFT JOIN kurir k ON k.idkurir = kk.idkurir "
					+ "LEFT JOIN kota kt ON kt.idkota = kk.idkota ";			
			sql += "WHERE t.idtransaksi = " + idTransaksi;
			ResultSet query = st.executeQuery(sql);
			while (query.next()) {
				String status = "tertunda";
				if (query.getBoolean("status_pemesanan")) {
					status = "disetujui";
				}
				Produk produk = new Produk();
				produk.setNama(query.getString("produk"));
				produk.setHarga(query.getInt("harga_produk"));
				Kurir kurir = new Kurir();
				kurir.setNama(query.getString("kurir"));
				kurir.setBiaya(query.getInt("biaya_kurir"));
				kurir.setKota(query.getString("kota"));
				transaksi = new Transaksi(query.getInt("idtransaksi"), produk,
						query.getInt("kuantitas"),
						query.getInt("biaya_pembayaran_produk"), kurir,
						query.getInt("total_pembayaran"),
						query.getString("nama_pelanggan"),
						query.getString("waktu_pemesanan"),
						query.getString("waktu_pengiriman"), status);
			}
		} catch (SQLException ee) {
			ee.printStackTrace();
		}
		return transaksi;
	}

	public boolean hapus(Transaksi Transaksi) {
		boolean toReturn;
		try {
			Statement st = conn.createStatement();
			st.execute("DELETE FROM Transaksi WHERE idTransaksi = "
					+ Transaksi.getIdTransaksi());
			toReturn = true;
		} catch (SQLException ee) {
			toReturn = false;
		}

		return toReturn;
	}
}
