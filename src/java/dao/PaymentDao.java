/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Payment;
import utils.DatabaseUtils;

/**
 *
 * @author MSI PC
 */
public class PaymentDao {
    public ArrayList<Payment> selectAll() {
        ArrayList<Payment> result = new ArrayList<Payment>();
        try {
            Connection con = DatabaseUtils.getConnection();

            String sql = "SELECT * FROM Payment";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int categoryid = rs.getInt("id");
                String name = rs.getString("paymentform");

                Payment tl = new Payment(categoryid, name);
                result.add(tl);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PaymentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static void main(String[] args) {
        PaymentDao productDao = new PaymentDao();
        int supplierName = 1;
        String supplierNation = "COD";
        Payment n = new Payment(supplierName, supplierNation);
        Payment allProducts = productDao.selectById(n);

        System.err.println(allProducts);
        
    }
    
    public Payment selectById(Payment t) {
        Payment ketQua = null;
        try {
            Connection con = DatabaseUtils.getConnection();
            String sql = "SELECT * FROM Payment WHERE id=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t.getId());
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int categoryid = rs.getInt("id");
                String name = rs.getString("paymentform");

                ketQua = new Payment(categoryid, name);
                break;
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PaymentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ketQua;
    }
}
