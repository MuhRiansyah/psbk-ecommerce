/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 *
 * @author RiansyahT
 */
public class FormatRupiah {

    public static String konversiRupiah(int biaya) {
        DecimalFormat kurensiRP = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRP = new DecimalFormatSymbols();
        formatRP.setCurrencySymbol("Rp. ");
        formatRP.setGroupingSeparator('.');
        formatRP.setMonetaryDecimalSeparator(',');
        kurensiRP.setDecimalFormatSymbols(formatRP);
        return kurensiRP.format(biaya);
    }
}
