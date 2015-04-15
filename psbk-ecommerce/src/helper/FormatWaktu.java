package helper;

import java.text.DateFormat;
import java.util.Date; 
import java.text.DateFormat; 
import java.text.SimpleDateFormat; 
 
import model.Transaksi;

public class FormatWaktu {
	//fungsi ntuk memasukkan tanggal sekarang
		private String getTanggal() { 
	        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	        Date date = new Date(); 
	        return dateFormat.format(date); 
	    } 
		
	    public static void main(String Args[]){ 
	    	FormatWaktu fw = new FormatWaktu();
	        System.out.println("Tanggal dan waktu sekarang : "+fw.getTanggal()); 
	    } 	
}
