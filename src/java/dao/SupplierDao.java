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
import model.Supplier;
import model.Category;
import model.Product;
import model.Brand;
import utils.DatabaseUtils;

/**
 *
 * @author MSI PC
 */
public class SupplierDao {

    private static String GET_ALL_SUPPLIER_BY_NAME = "SELECT * FROM SupplierCompany WHERE name = ?";

    public static boolean isNameExisted(String name) throws ClassNotFoundException, SQLException {
        Connection conn = DatabaseUtils.getConnection();
        PreparedStatement stmt = conn.prepareStatement(GET_ALL_SUPPLIER_BY_NAME);
        stmt.setString(1, name);
        ResultSet rs = stmt.executeQuery();
        return rs.next();
    }
    
    private static String GET_ALL_company = "SELECT * FROM SupplierCompany";

    public ArrayList<Supplier> selectAll() {
        ArrayList<Supplier> result = new ArrayList<Supplier>();
        try {
            Connection conn = DatabaseUtils.getConnection();
            PreparedStatement st = conn.prepareStatement(GET_ALL_company);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int supplierid = rs.getInt("id");
                String name = rs.getString("name");
                String nation = rs.getString("nation");

                Supplier brand = new Supplier(supplierid, name, nation);
                result.add(brand);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BrandDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    private static String DELETE_S = "DELETE FROM SupplierCompany WHERE id=?";

    public void deleteSupplierId(String id) {
        int result = 0;
        try {
            Connection con = DatabaseUtils.getConnection();
            PreparedStatement st = con.prepareStatement(DELETE_S);
            st.setString(1, id);
            result = st.executeUpdate();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SupplierDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static String INSERT_S = "INSERT INTO SupplierCompany (name, nation) VALUES (?, ?)";

    public int insert(String name, String nation) {
         int result = 0;
        try {
            Connection con = DatabaseUtils.getConnection();
            PreparedStatement st = con.prepareStatement(INSERT_S);
            st.setString(1, name);
            st.setString(2, nation);
            result = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SupplierDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    private static String UPDATE_P = "UPDATE Product SET name=?,image=?,price=?,categoryid=?,description=?,brandid=? WHERE id=?";

    public void updateProduct(String name, String image, String price, String categoryid, String description, String brandid, String id) {
        try {
            Connection con = DatabaseUtils.getConnection();
            PreparedStatement st = con.prepareStatement(UPDATE_P);
            st.setString(1, name);
            st.setString(2, image);
            st.setString(3, price);
            st.setString(4, categoryid);
            st.setString(5, description);
            st.setString(6, brandid);
            st.setString(7, id);

            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Supplier.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        SupplierDao supplierDao = new SupplierDao();

        // Replace these values with the actual supplier details you want to insert
        String supplierName = "Example Supplier";
        String supplierNation = "Example Nation";

        // Call the insert method
        supplierDao.insert(supplierName, supplierNation);

        System.out.println("Supplier inserted successfully.");
    }

    private static String GET_PRODUCT_BY_ID = "SELECT * FROM Product WHERE id=?";

    public Product selectById(Product t) {
        Product result = null;
        try {
            Connection con = DatabaseUtils.getConnection();

            PreparedStatement st = con.prepareStatement(GET_PRODUCT_BY_ID);
            st.setInt(1, t.getId());
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int produtid = rs.getInt("id");
                String name = rs.getString("name");
                String image = rs.getString("image");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                int brandid = rs.getInt("brandid");
                int categoryid = rs.getInt("categoryid");

                Brand brand = (new BrandDao().selectById(new Brand(brandid, "")));
                Category category = (new CategoryDao().selectById(new Category(categoryid, "")));

                //result = new Product(produtid, name, image, price, quantity, brand, category);
                break;
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Supplier.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    private static String INSERT_PRODUCT = "INSERT INTO Product (name,image,price,categoryid,description, quantity, brandid) VALUES (?,?,?,?,?,?,?)";

    public int insert(Product t) {
        int result = 0;
        try {
            Connection con = DatabaseUtils.getConnection();
            PreparedStatement st = con.prepareStatement(INSERT_PRODUCT);
            st.setString(1, t.getName());
            st.setString(2, t.getImage());
            st.setDouble(3, t.getPrice());
            st.setInt(4, t.getCategory().getId());
            st.setString(5, t.getDescription());
            st.setInt(6, t.getQuantity());
            st.setInt(7, t.getBrand().getId());
            result = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Supplier.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public int insertAll(ArrayList<Product> arr) {
        int dem = 0;
        for (Product Sp : arr) {
            dem += this.insert(Sp);
        }
        return dem;
    }

    private static String DELETE_Product = "DELETE FROM Product WHERE id=?";

    public int delete(Product t) {
        int result = 0;
        try {
            Connection con = DatabaseUtils.getConnection();
            PreparedStatement st = con.prepareStatement(DELETE_Product);
            st.setInt(1, t.getId());
            result = st.executeUpdate();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Supplier.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }
    
     private static String DELETE_A = "DELETE FROM Account WHERE regionid=?";

    public void deleteAccountByRId(int regionId) {
        int result = 0;
        try {
            Connection con = DatabaseUtils.getConnection();
            PreparedStatement st = con.prepareStatement(DELETE_A);
            st.setInt(1, regionId);  
            result = st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccountDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int deleteAll(ArrayList<Product> arr) {
        int count = 0;
        for (Product sp : arr) {
            count += this.delete(sp);
        }
        return count;
    }

    private static String UPDATE_Product = "UPDATE Product SET name=?,image=?,price=?,categoryid=?,description=?,quantity=?,brandid=? WHERE id=?";

    public int update(Product t) {
        int result = 0;
        try {
            Connection con = DatabaseUtils.getConnection();
            PreparedStatement st = con.prepareStatement(UPDATE_Product);
            st.setString(1, t.getName());
            st.setString(2, t.getImage());
            st.setDouble(3, t.getPrice());
            st.setInt(8, t.getCategory().getId());
            st.setString(5, t.getDescription());
//            st.setInt(6, t.getQuantity());
//            st.setInt(7, t.getBrand().getId());
            st.setInt(9, t.getId());

            result = st.executeUpdate();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Supplier.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }
}
