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
import model.Region;
import utils.DatabaseUtils;

/**
 *
 * @author MSI PC
 */
public class RegionDao {
    private static String GET_ALL_REGION_BY_NAME = "SELECT * FROM Region WHERE name = ?";

    public static boolean isNameExisted(String name) throws ClassNotFoundException, SQLException {
        Connection conn = DatabaseUtils.getConnection();
        PreparedStatement stmt = conn.prepareStatement(GET_ALL_REGION_BY_NAME);
        stmt.setString(1, name);
        ResultSet rs = stmt.executeQuery();
        return rs.next();
    }
    private static String GET_ALL_REGION = "SELECT * FROM Region";

    public ArrayList<Region> selectAll() {
        ArrayList<Region> result = new ArrayList<Region>();
        try {
            Connection conn = DatabaseUtils.getConnection();
            PreparedStatement st = conn.prepareStatement(GET_ALL_REGION);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int brandid = rs.getInt("id");
                String name = rs.getString("name");

                Region region = new Region(brandid, name);
                result.add(region);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegionDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    private static String GET_REGION_BY_ID = "SELECT * FROM Region WHERE id=?";

    public Region selectById(Region t) {
        Region result = null;
        try {
            Connection conn = DatabaseUtils.getConnection();
            PreparedStatement st = conn.prepareStatement(GET_REGION_BY_ID);
            st.setInt(1, t.getId());
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int regionId = rs.getInt("id");
                String name = rs.getString("name");

                result = new Region(regionId, name);
                break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegionDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

 public static void main(String[] args) {
    RegionDao regionDao = new RegionDao();

    // Replace these values with the actual supplier details you want to insert
    String regionName = "gion";

    // Call the insert method
    int result = regionDao.insert(regionName);

    if (result > 0) {
        System.out.println("Region inserted successfully.");
    } else {
        System.out.println("Failed to insert region.");
    }
}



     private static String INSERT_R = "INSERT INTO Region (name) VALUES (?)";

    public int insert(String name) {
        int result = 0;
        try {
            Connection con = DatabaseUtils.getConnection();
            PreparedStatement st = con.prepareStatement(INSERT_R);
            st.setString(1, name);
            result = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SupplierDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    private static String DELETE_R = "DELETE FROM Region WHERE id=?";

    public void deleteRegionId(String id) {
        int result = 0;
        try {
            Connection con = DatabaseUtils.getConnection();
            PreparedStatement st = con.prepareStatement(DELETE_R);
            st.setString(1, id);
            result = st.executeUpdate();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegionDao.class.getName()).log(Level.SEVERE, null, ex);
        }

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
            Logger.getLogger(RegionDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

}
