package beans;

import java.util.*;

import utils.JsfUtil;
import model.Item;
import model.Produk;

public class ShoppingCart {
	private List<Item> cart = new ArrayList<Item>();
	private int total;
	private Produk produk;

	public Produk getProduk() {
		return produk;
	}

	public void setProduk(Produk produk) {
		this.produk = produk;
	}

	public List<Item> getCart() {
		return cart;
	}

	public void setCart(List<Item> cart) {
		this.cart = cart;
	}

	public String getTotal(int kuantitas, int harga) {
		total = kuantitas * harga;
		return helper.FormatRupiah.konversiRupiah(total);
	}

	public String getTotalBelanjaan() {
		total = 0;
		for (Item item : cart) {
			total = total
					+ (item.getKuantitas() * item.getProduk().getHargaInt());
		}
		return helper.FormatRupiah.konversiRupiah(total);
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String addToCart(Produk produk) {
		for (Item item : cart) {
			if (item.getProduk().getIdproduk() == produk.getIdproduk()) {
				item.setKuantitas(item.getKuantitas() + 1);
			}
		}
		Item item = new Item();
		item.setKuantitas(1);
		item.setProduk(produk);
		cart.add(item);
		return "pembelianProduk";
	}

	public void mencatatTransaksi() {

	}

	public int getJumlahProduk() {
		return cart.size();
	}

	public void remove(Item itemSelected) {
		for (Item item : cart) {
			if (item.equals(itemSelected)) {
				cart.remove(itemSelected);
				break;
			}
		}
	}

	public void removeCart() {

		cart.removeAll(cart);
	}

	public void updateCart() {
	}

}
