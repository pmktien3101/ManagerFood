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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Brand;
import model.Category;
import model.Product;
import utils.DatabaseUtils;

/**
 *
 * @author MSI PC
 */
public class ProductDao {

    private static String GET_ALL_PRODUCT = "SELECT * FROM Product";

    public static ArrayList<Product> selectAll() {
        ArrayList<Product> result = new ArrayList<>();
        try (Connection con = DatabaseUtils.getConnection(); PreparedStatement st = con.prepareStatement(GET_ALL_PRODUCT); ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                int productId = rs.getInt("id");
                String productName = rs.getString("name");
                String image = rs.getString("image");
                double productPrice = rs.getDouble("price");
                int categoryId = rs.getInt("categoryid");
                String description = rs.getString("description");
                int quantity = rs.getInt("quantity");
                // Assuming you have a CategoryDao with a method selectById
                Category category = new CategoryDao().selectById(new Category(categoryId, ""));
                Product product = new Product(productId, productName, image, productPrice, category, description, quantity);
                result.add(product);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return result;
    }
    public static Product getProduct(int id) {
        
        List<Product> products = selectAll();
        Product product = null;
        for (Product p : products) {
            if (p.getId() == id) {
                product = p;
                break;
            }
        }
        return product;
    }
    
    private static String getProductByCategory = "SELECT * FROM product WHERE categoryid = ?";

    public ArrayList<Product> getProductByCId(String id) {
        ArrayList<Product> result = new ArrayList<>();

        try (Connection con = DatabaseUtils.getConnection(); PreparedStatement st = con.prepareStatement(getProductByCategory)) {

            st.setString(1, id);

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    int productId = rs.getInt("id");
                    String productName = rs.getString("name");
                    String image = rs.getString("image");
                    double productPrice = rs.getDouble("price");
                    int categoryId = rs.getInt("categoryid");
                    String description = rs.getString("description");

                    // Assuming you have a CategoryDao with a method selectById
                    Category category = new CategoryDao().selectById(new Category(categoryId, ""));

                    Product product = new Product(productId, productName, image, productPrice, category, description);
                    result.add(product);
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return result;
    }

    private static String getProductByBrand = "SELECT * FROM product WHERE brandid = ?";

    public ArrayList<Product> getProductByBId(String id) {
        ArrayList<Product> result = new ArrayList<>();

        try (Connection con = DatabaseUtils.getConnection(); PreparedStatement st = con.prepareStatement(getProductByBrand)) {

            st.setString(1, id);

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    int productId = rs.getInt("id");
                    String productName = rs.getString("name");
                    String image = rs.getString("image");
                    double productPrice = rs.getDouble("price");
                    int categoryId = rs.getInt("categoryid");
                    String description = rs.getString("description");
                    int brandId = rs.getInt("brandid");

                    // Assuming you have a CategoryDao with a method selectById
                    Category category = new CategoryDao().selectById(new Category(categoryId, ""));
                    Brand brand = new BrandDao().selectById(new Brand(brandId, ""));

                    Product product = new Product(productId, productName, image, productPrice, category, description, brand);
                    result.add(product);
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return result;
    }

    private static String getProductByName = "SELECT * FROM product WHERE name like ?";

    public ArrayList<Product> getProductByName(String name) {
        ArrayList<Product> result = new ArrayList<>();

        try (Connection con = DatabaseUtils.getConnection(); PreparedStatement st = con.prepareStatement(getProductByName)) {

            st.setString(1, "%" + name + "%");

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    int productId = rs.getInt("id");
                    String productName = rs.getString("name");
                    String image = rs.getString("image");
                    double productPrice = rs.getDouble("price");
                    int categoryId = rs.getInt("categoryid");
                    String description = rs.getString("description");
                    int brandid = rs.getInt("brandid");
                    // Assuming you have a CategoryDao with a method selectById
                    Category category = new CategoryDao().selectById(new Category(categoryId, ""));
                    Brand brand = new BrandDao().selectById(new Brand(brandid, ""));

                    Product product = new Product(productId, productName, image, productPrice, category, description, 0, brand);
                    result.add(product);
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return result;
    }
    private static String getProductByID = "SELECT * FROM product WHERE id = ?";

    public Product getProductById(String id) {
        Product result = null;

        try (Connection con = DatabaseUtils.getConnection(); PreparedStatement st = con.prepareStatement(getProductByID)) {

            st.setString(1, id);

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    int productId = rs.getInt("id");
                    String productName = rs.getString("name");
                    String image = rs.getString("image");
                    double productPrice = rs.getDouble("price");
                    int categoryId = rs.getInt("categoryid");
                    int quantity = rs.getInt("quantity");
                    String description = rs.getString("description");
                    int brandId = rs.getInt("brandid");

                    // Assuming you have a CategoryDao with a method selectById
                    Category category = new CategoryDao().selectById(new Category(categoryId, ""));
                    Brand brand = new BrandDao().selectById(new Brand(brandId, ""));

                    result = new Product(productId, productName, image, productPrice, category, description,quantity, brand);
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return result;
    }

    private static String DELETE_P = "DELETE FROM Product WHERE id=?";

    public void deleteProduct(String id) {
        int result = 0;
        try {
            Connection con = DatabaseUtils.getConnection();
            PreparedStatement st = con.prepareStatement(DELETE_P);
            st.setString(1, id);
            result = st.executeUpdate();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    private static String INSERT_P = "INSERT INTO Product (name, image, price, categoryid, description, quantity, brandid) VALUES (?, ?, ?, ?, ?, 0, ?)";

    public void insert(String name, String image, String price, String categoryid, String description, String brandid) {
        try {
            Connection con = DatabaseUtils.getConnection();
            PreparedStatement st = con.prepareStatement(INSERT_P);
            st.setString(1, name);
            st.setString(2, image);
            st.setString(3, price);
            st.setString(4, categoryid);
            st.setString(5, description);
            st.setString(6, brandid);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  public static void main(String[] args) {
        // Assuming you have a Product object with a valid ID for testing
        Product productToDelete = new Product();
        productToDelete.setId(1); // Replace with the actual ID you want to delete

        ProductDao productDao = new ProductDao();

        // Call the delete method
        int result = productDao.delete(productToDelete);

        if (result > 0) {
            System.out.println("Product deleted successfully.");
        } else {
            System.out.println("Failed to delete the product.");
        }
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
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
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
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }
}
