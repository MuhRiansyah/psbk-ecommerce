/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package koneksi;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author muhriansyah
 */
public class Koneksi {
    static Connection conn;
    public static Connection connection(){
        try{                  
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/e-commerce",
                    "postgres",
                    "bijibijian");                        
        }catch(ClassNotFoundException | SQLException ee){
            ee.printStackTrace();
        }
        return conn;
    }

}
