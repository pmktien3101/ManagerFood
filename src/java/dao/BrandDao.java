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
import model.Brand;
import utils.DatabaseUtils;

/**
 *
 * @author MSI PC
 */
public class BrandDao {

    private static String GET_ALL_BRAND = "SELECT * FROM Brand";

    public ArrayList<Brand> selectAll() {
        ArrayList<Brand> result = new ArrayList<Brand>();
        try {
            Connection conn = DatabaseUtils.getConnection();
            PreparedStatement st = conn.prepareStatement(GET_ALL_BRAND);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int brandid = rs.getInt("id");
                String name = rs.getString("name");

                Brand brand = new Brand(brandid, name);
                result.add(brand);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BrandDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    private static String GET_BRAND_BY_ID = "SELECT * FROM Brand WHERE id=?";

    public Brand selectById(Brand t) {
        Brand result = null;
        try {
            Connection conn = DatabaseUtils.getConnection();
            PreparedStatement st = conn.prepareStatement(GET_BRAND_BY_ID);
            st.setInt(1, t.getId());
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int brandid = rs.getInt("id");
                String name = rs.getString("name");

                result = new Brand(brandid, name);
                break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BrandDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
 public static void main(String[] args) {
        BrandDao productDao = new BrandDao();

        ArrayList<Brand> allProducts = productDao.selectAll();

        if (!allProducts.isEmpty()) {
            for (Brand product : allProducts) {
                System.out.println(product);
            }
        } else {
            System.out.println("No products found");
        }
    }
    private static String INSERT_BRAND = "INSERT INTO Brand (id, name) VALUES (?, ?)";

    public int insert(Brand t) {
        int result = 0;
        try {
            Connection conn = DatabaseUtils.getConnection();
            PreparedStatement st = conn.prepareStatement(INSERT_BRAND);
            st.setInt(1, t.getId());
            st.setString(2, t.getName());
            result = st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BrandDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public int insertAll(ArrayList<Brand> arr) {
        int count = 0;
        for (Brand brand : arr) {
            count += this.insert(brand);
        }
        return count;
    }

    private static String DELETE_BRAND = "DELETE FROM Brand WHERE id=?";

    public int delete(Brand t) {
        int result = 0;
        try {
            Connection con = DatabaseUtils.getConnection();
            PreparedStatement st = con.prepareStatement(DELETE_BRAND);
            st.setInt(1, t.getId());
            result = st.executeUpdate();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BrandDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public int deleteAll(ArrayList<Brand> arr) {
        int count = 0;
        for (Brand brand : arr) {
            count += this.delete(brand);
        }
        return count;
    }
    
    private static String UPDATE_BRAND = "UPDATE Brand SET name=? WHERE id=?";
    public int update(Brand t) {
        int result = 0;
        try {
            Connection con = DatabaseUtils.getConnection();
            PreparedStatement st = con.prepareStatement(UPDATE_BRAND);
            st.setString(1, t.getName());
            st.setInt(2, t.getId());
            result = st.executeUpdate();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BrandDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

}
