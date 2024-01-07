/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.ForgetPassDto;
import dto.RegisterDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Brand;
import model.Category;
import model.Message;
import model.Product;
import model.Region;
import utils.DatabaseUtils;

/**
 *
 * @author MSI PC
 */
public class AccountDao {

    private static String GET_ALL_ACCOUNT_BY_EMAIL = "SELECT * FROM Account WHERE email = ?";
    private static String GET_ALL_ACCOUNT_BY_TAXCODE = "SELECT * FROM Account WHERE taxcode = ?";
    private static String GET_ALL_ACCOUNT_BY_PHONE = "SELECT * FROM Account WHERE phone= ?";

    private static String INSERT_ACCOUNT_QUERY = "INSERT INTO Account (name, email, phone, regionid,taxcode, password) VALUES (?, ?, ?, ?, ?, ?)";

    public static boolean isEmailExisted(String email) throws ClassNotFoundException, SQLException {
        Connection conn = DatabaseUtils.getConnection();
        PreparedStatement stmt = conn.prepareStatement(GET_ALL_ACCOUNT_BY_EMAIL);
        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();
        return rs.next();
    }

    public static boolean isTaxCodeExisted(String taxCode) throws ClassNotFoundException, SQLException {
        Connection conn = DatabaseUtils.getConnection();
        PreparedStatement stmt = conn.prepareStatement(GET_ALL_ACCOUNT_BY_TAXCODE);
        stmt.setString(1, taxCode);
        ResultSet rs = stmt.executeQuery();
        return rs.next();
    }

    public static boolean isPhoneExisted(String phone) throws ClassNotFoundException, SQLException {
        Connection conn = DatabaseUtils.getConnection();
        PreparedStatement stmt = conn.prepareStatement(GET_ALL_ACCOUNT_BY_PHONE);
        stmt.setString(1, phone);
        ResultSet rs = stmt.executeQuery();
        return rs.next();
    }

    public static boolean isNameExisted(String phone) throws ClassNotFoundException, SQLException {
        Connection conn = DatabaseUtils.getConnection();
        PreparedStatement stmt = conn.prepareStatement(GET_ALL_ACCOUNT_BY_PHONE);
        stmt.setString(1, phone);
        ResultSet rs = stmt.executeQuery();
        return rs.next();
    }

    public static boolean createUser(RegisterDto dto) {
        try {
            if (isEmailExisted(dto.getEmail()) || isTaxCodeExisted(dto.getTaxCode()) || isPhoneExisted(dto.getPhone())) {
                return false;
            } else {
                Connection conn = DatabaseUtils.getConnection();
                PreparedStatement stmt = conn.prepareStatement(INSERT_ACCOUNT_QUERY);
                stmt.setString(1, dto.getName());
                stmt.setString(2, dto.getEmail());
                stmt.setString(3, dto.getPhone());
                stmt.setString(4, dto.getRegion());
                stmt.setString(5, dto.getTaxCode());
                stmt.setString(6, dto.getPassword());
                stmt.executeUpdate();
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }

    private static String GET_ACCOUNT = "SELECT * FROM Account WHERE email=? and password=?";

    public Account selectByEmailAndPass(Account t) {
        Account result = null;
        try {
            Connection con = DatabaseUtils.getConnection();

            PreparedStatement st = con.prepareStatement(GET_ACCOUNT);
            System.out.println(t.getEmail() + "/" + t.getPassword());
            st.setString(1, t.getEmail());
            st.setString(2, t.getPassword());
            System.out.println(GET_ACCOUNT);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                int regionid = rs.getInt("regionid");
                String taxCode = rs.getString("taxcode");
                String passWord = rs.getString("password");

                Region region = (new RegionDao().selectById(new Region(regionid, "")));
                result = new Account(name, email, phone, region, taxCode, passWord);

            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccountDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    private static String GET_Email = "SELECT * FROM Account WHERE email=?";

    public Account selectByEmail(Account t) {
        Account result = null;
        try {
            Connection con = DatabaseUtils.getConnection();

            PreparedStatement st = con.prepareStatement(GET_Email);
            System.out.println(t.getEmail());
            st.setString(1, t.getEmail());
            System.out.println(GET_Email);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                int regionid = rs.getInt("regionid");
                String userName = rs.getString("taxcode");
                String passWord = rs.getString("password");

                Region region = (new RegionDao().selectById(new Region(regionid, "")));
                result = new Account(name, email, phone, region, userName, passWord);

            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccountDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public boolean forgetPassword(Account t) {
        int ketQua = 0;
        try {
            Connection con = DatabaseUtils.getConnection();

            String sql = "UPDATE Account " + " SET " + " password=?" + " WHERE email=?";

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getPassword());
            st.setString(2, t.getEmail());
            System.out.println(sql);
            ketQua = st.executeUpdate();

            System.out.println("Bạn đã thực thi: " + sql);
            System.out.println("Có " + ketQua + " dòng bị thay đổi!");

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccountDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ketQua > 0;
    }

    public boolean changePassword(Account t) {
        int ketQua = 0;
        try {
            Connection con = DatabaseUtils.getConnection();

            String sql = "UPDATE Account " + " SET " + " password=?" + " WHERE email=?";

            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getPassword());
            st.setString(2, t.getEmail());
            System.out.println(sql);
            ketQua = st.executeUpdate();

            System.out.println("Bạn đã thực thi: " + sql);
            System.out.println("Có " + ketQua + " dòng bị thay đổi!");

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccountDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ketQua > 0;
    }

    private static String UPDATE_A = "UPDATE Account SET name=? WHERE email=?";

    public boolean changeInfor(String newName, String email) {
        int ketQua = 0;
        try {
            if (isNameExisted(newName)) {
                return false;
            }
            Connection con = DatabaseUtils.getConnection();
            PreparedStatement st = con.prepareStatement(UPDATE_A);
            st.setString(1, newName);
            st.setString(2, email);

            ketQua = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccountDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ketQua > 0;
    }

    private static String INSERT_M = "INSERT INTO Message (email,subject, body) VALUES (?,?,?)";

    public void insertM(String email, String subject, String body) {
        try {
            Connection con = DatabaseUtils.getConnection();
            PreparedStatement st = con.prepareStatement(INSERT_M);
            st.setString(1, email);
            st.setString(2, subject);
            st.setString(3, body);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccountDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private static String GET_ALL_Message = "SELECT * FROM Message";

    public ArrayList<Message> selectAllM() {
        ArrayList<Message> result = new ArrayList<Message>();
        try {
            Connection conn = DatabaseUtils.getConnection();
            PreparedStatement st = conn.prepareStatement(GET_ALL_Message);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String email = rs.getString("email");
                String subject = rs.getString("subject");
                String body = rs.getString("body");

                Message m = new Message(id, email, subject, body);
                result.add(m);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccountDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    private static String DELETE_A = "DELETE FROM Account WHERE regionid=?";

    public void deleteAccountByRId(int regionId) {
        int result = 0;
        try {
            Connection con = DatabaseUtils.getConnection();
            PreparedStatement st = con.prepareStatement(DELETE_A);
            st.setInt(1, regionId);  // Use setInt for integer parameters
            result = st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccountDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



    private static String GET_ALL_Account = "SELECT * FROM Account";

    public ArrayList<Account> selectAll() {
        ArrayList<Account> result = new ArrayList<>();
        try (Connection con = DatabaseUtils.getConnection(); PreparedStatement st = con.prepareStatement(GET_ALL_Account); ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                int regionid = rs.getInt("regionid");
                String taxCode = rs.getString("taxcode");

                // Assuming you have a CategoryDao with a method selectById
                Region region = new RegionDao().selectById(new Region(regionid, ""));
                Account product = new Account(name, email, phone, region, taxCode);
                result.add(product);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return result;
    }
    private static String getAccountByRegion = "SELECT * FROM Account WHERE regionid = ?";

    public ArrayList<Account> getAccountByRId(String id) {
        ArrayList<Account> result = new ArrayList<>();

        try (Connection con = DatabaseUtils.getConnection(); PreparedStatement st = con.prepareStatement(getAccountByRegion)) {

            st.setString(1, id);

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    String productName = rs.getString("name");
                    String image = rs.getString("email");
                    String productPrice = rs.getString("phone");
                    int regionid = rs.getInt("regionid");
                    String description = rs.getString("taxcode");

                    Region region = new RegionDao().selectById(new Region(regionid, ""));
                    Account account = new Account(productName, image, productPrice, region, description);
                    result.add(account);
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return result;
    }

    public Account selectById(Account t) {
        Account result = null;
        try {
            Connection con = DatabaseUtils.getConnection();

            PreparedStatement st = con.prepareStatement(GET_ALL_Account);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String accountid = rs.getString("id");
                String name = rs.getString("name");
                String userName = rs.getString("username");
                String passWord = rs.getString("password");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                int role = rs.getInt("role");

                //result = new Account(accountid, name, userName, passWord, email, phone, role);
                break;
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccountDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    private static String INSERT_Account = "INSERT INTO Account (name,username,password,email,phone) VALUES (?,?,?,?,?)";

    public int insert(Account t) {
        int result = 0;
        try {
            Connection con = DatabaseUtils.getConnection();
            PreparedStatement st = con.prepareStatement(INSERT_Account);
            st.setString(1, t.getName());
            //st.setString(2, t.getUsername());
            st.setString(3, t.getPassword());
            st.setString(4, t.getEmail());
            st.setString(5, t.getPhone());
            result = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccountDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public int insertAll(ArrayList<Account> arr) {
        int dem = 0;
        for (Account Sp : arr) {
            dem += this.insert(Sp);
        }
        return dem;
    }

//    public int delete(Account t) {
//        int result = 0;
//        try {
//            Connection con = DatabaseUtils.getConnection();
//            PreparedStatement st = con.prepareStatement(DELETE_Account);
//            result = st.executeUpdate();
//
//        } catch (SQLException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(AccountDao.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        return result;
//    }

  
    private static String UPDATE_Account = "UPDATE Account SET name=?,username=?,password=?,email=?,phone=?,role=? WHERE id=?";

    public int update(Account t) {
        int result = 0;
        try {
            Connection con = DatabaseUtils.getConnection();
            PreparedStatement st = con.prepareStatement(UPDATE_Account);
            st.setString(1, t.getName());
            //st.setString(2, t.getUsername());
            st.setString(3, t.getPassword());
            st.setString(4, t.getEmail());
            st.setString(5, t.getPhone());

            result = st.executeUpdate();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccountDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    private static String GET_ACCOUNT_BY_USERNAME = "SELECT * FROM Account WHERE username=?";

    public boolean checkUserName(String userName) {
        boolean result = false;
        try {
            Connection con = DatabaseUtils.getConnection();
            PreparedStatement st = con.prepareStatement(GET_ACCOUNT_BY_USERNAME);
            System.out.println(GET_ACCOUNT_BY_USERNAME);
            st.setString(1, userName);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                // If there is a result, then the username already exists
                result = true;
                break;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccountDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

}
