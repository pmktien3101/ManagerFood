package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Category;
import utils.DatabaseUtils;

public class CategoryDao{

    private ArrayList<Category> data = new ArrayList<>();

    public ArrayList<Category> selectAll() {
        ArrayList<Category> result = new ArrayList<Category>();
        try {
            Connection con = DatabaseUtils.getConnection();

            String sql = "SELECT * FROM Category";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int categoryid = rs.getInt("id");
                String name = rs.getString("name");

                Category tl = new Category(categoryid, name);
                result.add(tl);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static void main(String[] args) {
        CategoryDao productDao = new CategoryDao();

        ArrayList<Category> allProducts = productDao.selectAll();

        if (!allProducts.isEmpty()) {
            for (Category product : allProducts) {
                System.out.println(product);
            }
        } else {
            System.out.println("No products found");
        }
    }
    
    public Category selectById(Category t) {
        Category ketQua = null;
        try {
            Connection con = DatabaseUtils.getConnection();
            String sql = "SELECT * FROM Category WHERE id=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t.getId());
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int categoryid = rs.getInt("id");
                String name = rs.getString("name");

                ketQua = new Category(categoryid, name);
                break;
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ketQua;
    }

    public int insert(Category t) {
        int ketQua = 0;
        try {
            Connection con = DatabaseUtils.getConnection();

            String sql = "INSERT INTO Category (id, name) "
                    + " VALUES (?,?)";

            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t.getId());
            st.setString(2, t.getName());

            ketQua = st.executeUpdate();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ketQua;
    }

    public int insertAll(ArrayList<Category> arr) {
        int dem = 0;
        for (Category Category : arr) {
            dem += this.insert(Category);
        }
        return dem;
    }

    public int delete(Category t) {
        int ketQua = 0;
        try {
            Connection con = DatabaseUtils.getConnection();

            String sql = "DELETE from Category "
                    + " WHERE id=?";

            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t.getId());
            ketQua = st.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ketQua;
    }

    public int deleteAll(ArrayList<Category> arr) {
        int dem = 0;
        for (Category Category : arr) {
            dem += this.delete(Category);
        }
        return dem;
    }

    public int update(Category t) {
        int ketQua = 0;
        try {
            Connection con = DatabaseUtils.getConnection();

            String sql = "UPDATE Category "
                    + " SET "
                    + " name=?"
                    + " WHERE id=?";

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getName());
            st.setInt(2, t.getId());
            ketQua = st.executeUpdate();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ketQua;
    }
}
