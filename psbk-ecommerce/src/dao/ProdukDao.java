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
import model.Produk;

public class ProdukDao {

	Connection conn;

	private static ProdukDao produkDao;

	public static ProdukDao getInstance() {
		if (produkDao == null) {
			produkDao = new ProdukDao();
		}
		return produkDao;
	}

	public static void main(String[] args) {
		 ProdukDao pd = new ProdukDao();
//		 for(Integer merek : pd.getListMerek().values() ){
//			 System.out.println(merek);
//		 }
//		for(Produk p: pd.getListProduk(1,1,"nama ") ){
//			System.out.println(p.getNama());
//		}				
		 pd.insertTb();
	}

	public ProdukDao() {
		conn = Koneksi.connection();
	}
	
	
    public boolean simpan(Produk produk) {
        boolean toReturn;
        try {
        	Statement st = conn.createStatement();
        	st.execute("INSERT INTO produk VALUES (default,"
        			+ "'"+produk.getNama()+"',"+produk.getHargaInt()
        			+ ","+produk.getIdKategori()+","+produk.getIdMerek()
        			+ ",'"+produk.getGambar()+"'"
        			+ ")");                    
            toReturn = true;
        } catch (SQLException ee) {
        	toReturn = false;
			ee.printStackTrace();
		}

        return toReturn;
    }

    public boolean ubah(Produk produk) {
        boolean toReturn;
        try {
        	Statement st = conn.createStatement();
        	st.execute("UPDATE produk SET nama = "
        			+ "'"+produk.getNama()+"',harga = "+produk.getHargaInt()
        			+ ",idKategori = "+produk.getIdKategori()+",idMerek ="+produk.getIdMerek()
        			+ ",gambar = '"+produk.getGambar()+"' "
        					+ "WHERE idproduk = "+produk.getIdproduk());                    
            toReturn = true;
        } catch (SQLException ee) {
        	toReturn = false;
			ee.printStackTrace();
		}

        return toReturn;
    }
        
	public void insertTb() {
		try {
			Statement st = conn.createStatement();
			String sql = "INSERT INTO produk (idproduk, nama, harga, idkategori, idmerek, gambar)"
					+ "VALUES ";
			for (int n = 1; n <= 10; n++) {
				sql += "(default,'Macbook Air " + n + " ', " + n + "1000000,1,1,'default.jpg'), ";
				sql += "(default,'LENOVO 42" + n + " '," + n + "100000,1,2,'default.jpg' ), ";
				sql += "(default,'COMPAQ 23" + n + " ', 1" + n + "00000,1,5,'default.jpg' ), ";
				sql += "(default,'IPHONE " + n + " ', 6" + n + "00000,2,1,'default.jpg'), ";
				sql += "(default,'Galaxy Tab 3 " + n + " ', 9" + n
						+ "0000,2,3,'default.jpg' ), ";
				sql += "(default,'Galaxy Tab 6 " + n + " ', 6" + n
						+ "0000,1,3,'default.jpg' ), ";
			}
			sql += "(default,'Macbook',12231200,1,1,'default.jpg' ), ";
			sql += "(default,'LENOVO 3221 ',1201200,1,2,'default.jpg' ), ";
			sql += "(default,'COMPAQ 2131 ',122200,1,5,'default.jpg' ), ";
			sql += "(default,'Lenovo Vibe 11 ',1201200,2,3,'default.jpg' ) ";			
			st.execute(sql);
		} catch (SQLException ee) {
			ee.printStackTrace();
		}

	}

	public Produk getDetailProduk(int idProduk) {
		try {
			// jika spesifikasi masih 3 maka nilai n pada autoincrement dibatasi
			// 3
			int spekProduk = 3;
			Statement st = conn.createStatement();
			String sql = "SELECT idproduk,p.nama,harga,gambar,pm.nama as merek,pk.nama as kategori,"
					+ "sp1.s spesifikasi1,sp2.s spesifikasi2,sp3.s spesifikasi3,"
					+ "sp1.a atribut1,sp2.a atribut2,sp3.a atribut3 "
					+ "FROM produk p LEFT JOIN produk_merek pm ON pm.idmerek = p.idmerek "
					+ "LEFT JOIN produk_kategori pk ON pk.idkategori = p.idkategori "
					+ "LEFT OUTER JOIN ";
			for (int n = 1; n <= spekProduk; n++) {
				sql += "("
						+ "SELECT  p.idproduk id,nilai_spesifikasi s,pas.atribut_spesifikasi a FROM "
						+ "_produk_spesifikasi ps "
						+ "JOIN produk_atribut_spesifikasi pas ON ps.id_atribut_spesifikasi = pas.id_atribut_spesifikasi "
						+ "JOIN produk p ON p.idproduk = ps.idproduk "
						+ "JOIN produk_nilai_spesifikasi pns ON pns.id_nilai_spesifikasi = ps.id_nilai_spesifikasi "
						+ "LIMIT 1 OFFSET " + (n - 1) + ")";
				if (n < spekProduk) {
					sql += "sp" + n + " ON p.idproduk = sp" + n + ".id "
							+ "LEFT OUTER JOIN ";
				} else {
					sql += "sp" + n + " ON p.idproduk = sp" + n + ".id ";
				}
			}
			sql += "WHERE idproduk = " + idProduk;
			st.execute(sql);
			ResultSet query = st.executeQuery(sql);
			while (query.next()) {
				Produk p = new Produk(query.getInt("idproduk"),
						query.getString("gambar"), query.getString("merek"),
						query.getString("kategori"),
						query.getString("nama"),
						query.getInt("harga"), query.getString("spesifikasi1"),
						query.getString("spesifikasi2"),
						query.getString("spesifikasi3"),
						query.getString("atribut1"),
						query.getString("atribut2"),
						query.getString("atribut3"));
				return p;
			}
		} catch (SQLException ee) {
			ee.printStackTrace();
		}
		return null;
	}
	
	public Map<String, Integer> getListMerek() {
		Map<String, Integer> listMerek = new LinkedHashMap<String, Integer>();
		try {
			// jika spesifikasi masih 3 maka nilai n pada autoincrement dibatasi 3
			//menset nilai default idkategori dengan idmerek 				
			Statement st = conn.createStatement();
			String sql = "SELECT * FROM produk_merek";
			st.execute(sql);
			ResultSet query = st.executeQuery(sql);
			while (query.next()) {
				listMerek.put(query.getString("nama"),query.getInt("idmerek"));
			}
		} catch (SQLException ee) {
			ee.printStackTrace();
		}
		return listMerek;
	}
	
	public Map<String, Integer> getListKategori() {
		Map<String, Integer> listKategori = new LinkedHashMap<String, Integer>();
		try {
			// jika spesifikasi masih 3 maka nilai n pada autoincrement dibatasi 3
			//menset nilai default idkategori dengan idmerek 				
			Statement st = conn.createStatement();
			String sql = "SELECT * FROM produk_kategori";
			st.execute(sql);
			ResultSet query = st.executeQuery(sql);
			while (query.next()) {
				listKategori.put(query.getString("nama"),query.getInt("idkategori"));
			}
		} catch (SQLException ee) {
			ee.printStackTrace();
		}
		return listKategori;
	}
	
	public List<Produk> getListProduk() {
		List<Produk> listProduk = new ArrayList<Produk>();
		try {
			// jika spesifikasi masih 3 maka nilai n pada autoincrement dibatasi 3
			//menset nilai default idkategori dengan idmerek 				
			Statement st = conn.createStatement();
			String sql = "SELECT idproduk,p.nama,pk.nama as kategori, pm.nama as merek,harga,gambar FROM produk p  "
					+ "LEFT JOIN  produk_kategori pk ON pk.idkategori = p.idkategori "
					+ "LEFT JOIN  produk_merek pm ON pm.idmerek = p.idmerek "					
					+ "ORDER BY merek DESC";						
			ResultSet query = st.executeQuery(sql);
			while (query.next()) {
				listProduk.add(new Produk(query.getInt("idproduk"), query
						.getString("gambar"), query.getString("nama"), query
						.getInt("harga"), query.getString("merek"),
						query.getString("kategori")));
			}
		} catch (SQLException ee) {
			ee.printStackTrace();
		}
		return listProduk;
	}

	// method rekomendasi produk untuk halaman detail
	// (hanya menampilkan 5 produk saja dengan atribut nama,gambar dan harga)
	public List<Produk> getPembelianProdukTerbanyak(int idKategori, int idMerek) {
		List<Produk> listProduk = new ArrayList<Produk>();
		try {
			int spekProduk = 3;
			Statement st = conn.createStatement();
			String sql = " WITH jumlah_kuantitas AS("
					+ "SELECT p.idproduk, nama,SUM(kuantitas) AS jumlah "
					+ "FROM produk_transaksi pk JOIN produk p ON pk.idproduk = p.idproduk "
					+ "GROUP BY nama,p.idproduk)"
					+ ",joinlain AS"
					+ "("
						+ "SELECT p.idproduk,p.nama,p.harga,p.gambar,jk.jumlah,sp1.s spesifikasi1,sp2.s spesifikasi2,sp3.s spesifikasi3 "
						+ "FROM produk p LEFT OUTER JOIN ";
			for (int n = 1; n <= spekProduk; n++) {
				sql += "("
						+ "SELECT  p.idproduk id,nilai_spesifikasi s FROM "
						+ "_produk_spesifikasi ps "
						+ "JOIN produk_atribut_spesifikasi pas ON ps.id_atribut_spesifikasi = pas.id_atribut_spesifikasi "
						+ "JOIN produk p ON p.idproduk = ps.idproduk "
						+ "JOIN produk_nilai_spesifikasi pns ON pns.id_nilai_spesifikasi = ps.id_nilai_spesifikasi "
						+ "LIMIT 1 OFFSET " + (n - 1) + ")";
				if (n < spekProduk) {
					sql += "sp" + n + " ON p.idproduk = sp" + n + ".id "
							+ "LEFT OUTER JOIN ";
				} else {
					sql += "sp" + n + " ON p.idproduk = sp" + n + ".id "
							+ "LEFT OUTER JOIN jumlah_kuantitas jk ON p.idproduk = jk.idproduk ";
				}
			}
			sql += " WHERE idkategori = " + idKategori + " AND idmerek = "
					+ idMerek;
			sql += "),urutkan AS("
					+ " SELECT * FROM joinlain ORDER BY jumlah ASC)"
					+ " SELECT * FROM urutkan";
			st.execute(sql);
			ResultSet query = st.executeQuery(sql);
			while (query.next()) {
				listProduk.add(new Produk(query.getInt("idproduk"), query
						.getString("gambar"), query.getString("nama"), query
						.getInt("harga"), query.getString("spesifikasi1"),
						query.getString("spesifikasi2"), query
								.getString("spesifikasi3")));
			}
		} catch (SQLException ee) {
			ee.printStackTrace();
		}

		return listProduk;
	}

	// method rekomendasi produk untuk halaman detail
	// (hanya menampilkan 5 produk saja dengan atribut nama,gambar dan harga)
	public List<Produk> getRecommendProduk(int idKategori, int idMerek,
			String sortby) {
		List<Produk> listProduk = new ArrayList<Produk>();
		if(idKategori == 0) idKategori = 1;
		if(idMerek == 0) idMerek = 1;
		try {
			// jika spesifikasi masih 3 maka nilai n pada autoincrement dibatasi 3
			String urut = sortby;
			Statement st = conn.createStatement();
			String sql = "SELECT idproduk,nama,harga,gambar "
					+ " FROM produk  WHERE idkategori = " + idKategori
					+ " AND idmerek = " + idMerek + "  ORDER BY " + urut
					+ " LIMIT 5 OFFSET 0";
			ResultSet query = st.executeQuery(sql);
			while (query.next()) {
				listProduk.add(new Produk(query.getInt("idproduk"), query
						.getString("gambar"), query.getString("nama"), query
						.getInt("harga")));
			}
		} catch (SQLException ee) {
			ee.printStackTrace();
		}
		return listProduk;
	}

	// method yang menampilkan daftar produk untuk halaman list produk
	// (menampilkan banyak produk dengan atribut nama,gambar,harga, 3 spek
	// teratas)
	public List<Produk> getListProduk(int idKategori, int idMerek, String sortby) {
		List<Produk> listProduk = new ArrayList<Produk>();
		try {
			// jika spesifikasi masih 3 maka nilai n pada autoincrement dibatasi 3
			//menset nilai default idkategori dengan idmerek 
			if(idKategori ==0){
				idKategori = 1;
			}
			if(idMerek ==0){
				idMerek = 1;
			}
			int spekProduk = 3;
						
			
			Statement st = conn.createStatement();
			String sql = "SELECT idproduk,nama,harga,gambar,sp1.s spesifikasi1,sp2.s spesifikasi2,sp3.s spesifikasi3 "
					+ "FROM produk p LEFT OUTER JOIN ";
			for (int n = 1; n <= spekProduk; n++) {
				sql += "("
						+ "SELECT  p.idproduk id,nilai_spesifikasi s FROM "
						+ "_produk_spesifikasi ps "
						+ "JOIN produk_atribut_spesifikasi pas ON ps.id_atribut_spesifikasi = pas.id_atribut_spesifikasi "
						+ "JOIN produk p ON p.idproduk = ps.idproduk "
						+ "JOIN produk_nilai_spesifikasi pns ON pns.id_nilai_spesifikasi = ps.id_nilai_spesifikasi "
						+ "LIMIT 1 OFFSET " + (n - 1) + ")";
				if (n < spekProduk) {
					sql += "sp" + n + " ON p.idproduk = sp" + n + ".id "
							+ "LEFT OUTER JOIN ";
				} else {
					sql += "sp" + n + " ON p.idproduk = sp" + n + ".id ";
				}
			}
			sql += " WHERE idkategori = " + idKategori + " AND idmerek = "
					+ idMerek;	
			if(sortby!=null && !sortby.equals("")){
				sql += " ORDER BY " + sortby;				
			}
						
			ResultSet query = st.executeQuery(sql);
			while (query.next()) {
				listProduk.add(new Produk(query.getInt("idproduk"), query
						.getString("gambar"), query.getString("nama"), query
						.getInt("harga"), query.getString("spesifikasi1"),
						query.getString("spesifikasi2"), query
								.getString("spesifikasi3")));
			}
		} catch (SQLException ee) {
			ee.printStackTrace();
		}
		return listProduk;
	}

	public List<Produk> getRecommendBarangDibeliBersamaan(int idProduk) {
		List<Produk> listProduk = new ArrayList<Produk>();
		try {
			Statement st = conn.createStatement();
			String sql = "WITH transaksi AS("
					+ "SELECT idproduk FROM produk_transaksi WHERE idtransaksi IN "
					+ " (SELECT idtransaksi FROM produk_transaksi "
					+ " WHERE idproduk = "+idProduk+")"
					+ "),"
					+" idproduk_teratas AS("
					+" SELECT idproduk,count(idproduk) AS frekuensi_produk FROM transaksi" 
					+" WHERE idproduk <> "+idProduk+" GROUP BY idproduk "
					+" ORDER BY frekuensi_produk DESC LIMIT 5"
					+" )"
					+" SELECT p.idproduk,nama,harga,gambar" 
					+" FROM produk p JOIN idproduk_teratas pa ON p.idproduk = pa.idproduk";
			st.execute(sql);
			ResultSet query = st.executeQuery(sql);
			while (query.next()) {
				listProduk.add(new Produk(query.getInt("idproduk"), query
						.getString("gambar"), query.getString("nama"), query
						.getInt("harga")));
			}
		} catch (SQLException ee) {
			ee.printStackTrace();
		}
		return listProduk;
	}

	public boolean hapus(Produk produk){
        boolean toReturn;
        try {
        	Statement st = conn.createStatement();
        	st.execute("DELETE FROM produk WHERE idproduk = "+produk.getIdproduk());            
            toReturn = true;
        } catch (SQLException ee) {       
            toReturn = false;
        }

        return toReturn;		
	}
}
