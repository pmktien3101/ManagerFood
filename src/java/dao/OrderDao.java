/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Order;
import model.Payment;
import utils.DatabaseUtils;

/**
 *
 * @author MSI PC
 */
public class OrderDao {

    private static String GET_ALL_company = "SELECT * FROM MyOrder";

    public ArrayList<Order> selectAll() {
        ArrayList<Order> result = new ArrayList<Order>();
        try {
            Connection conn = DatabaseUtils.getConnection();
            PreparedStatement st = conn.prepareStatement(GET_ALL_company);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int supplierid = rs.getInt("id");
                Date odate = rs.getDate("orderdate");
                Double total = rs.getDouble("ordertotal");
                Date ddate = rs.getDate("deliverydate");
                String ostatus = rs.getString("orderstatus");
                String address = rs.getString("deliveryaddress");
                int paymentid = rs.getInt("paymentid");
                String email = rs.getString("email");
                Account account = new AccountDao().selectByEmail(new Account("", email));

                Payment category = new PaymentDao().selectById(new Payment(paymentid, ""));
                Order brand = new Order(supplierid, odate, total, ddate, ostatus, address, category, account);
                result.add(brand);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OrderDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    private static String INSERT_S = "INSERT INTO MyOrder (ordertotal, orderstatus, deliveryaddress,paymentid,email) VALUES (?,'In progress', ?,?,?)";

    public static void insert(double ordertotal, String deliveryaddress, String paymentid, String email) {
        try {
            Connection con = DatabaseUtils.getConnection();
            PreparedStatement st = con.prepareStatement(INSERT_S);
            st.setDouble(1, ordertotal);
            st.setString(2, deliveryaddress);
            st.setString(3, paymentid);
            st.setString(4, email);

            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OrderDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        // Replace 'user@email.com' with the actual email you want to test
        String email = "alipaz@pt.com";

        OrderDao orderDao = new OrderDao();
        List<Order> orders = orderDao.getOrdersByEmail(email);

        System.out.println("Orders for email: " + email);
        for (Order order : orders) {
            System.out.println("Order ID: " + order.getId());
            System.out.println("Order Date: " + order.getOrderDate());
            System.out.println("Order Total: " + order.getOrderTotal());
            System.out.println("Delivery Date: " + order.getDeliveryDate());
            System.out.println("Order Status: " + order.getOrderStatus());
            System.out.println("Delivery Address: " + order.getDeliveryAddress());
            System.out.println("Payment Method: " + order.getPaymentForm().getPaymentForm());
            System.out.println();
        }
    }
    private static String UPDATE_ORDER_STATUS = "UPDATE MyOrder SET orderstatus = 'Deliveried' WHERE id = ? AND orderstatus = 'In progress'";

    public static void updateOrderStatus(String orderId) {
        try {
            Connection con = DatabaseUtils.getConnection();
            PreparedStatement st = con.prepareStatement(UPDATE_ORDER_STATUS);
            st.setString(1, orderId);

            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OrderDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private static String getOrdersByEmailQuery = "SELECT * FROM MyOrder WHERE email = ?";

    public List<Order> getOrdersByEmail(String email) {
        List<Order> orders = new ArrayList<>();

        try (Connection con = DatabaseUtils.getConnection(); PreparedStatement st = con.prepareStatement(getOrdersByEmailQuery)) {

            st.setString(1, email);

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    int orderId = rs.getInt("id");
                    Date orderDate = rs.getDate("orderdate");
                    Double orderTotal = rs.getDouble("ordertotal");
                    Date deliveryDate = rs.getDate("deliverydate");
                    String orderStatus = rs.getString("orderstatus");
                    String deliveryAddress = rs.getString("deliveryaddress");
                    int paymentId = rs.getInt("paymentid");

                    Payment payment = new PaymentDao().selectById(new Payment(paymentId, ""));

                    Order order = new Order(orderId, orderDate, orderTotal, deliveryDate, orderStatus, deliveryAddress, payment);
                    orders.add(order);
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return orders;
    }

    private static String CANCEL = "UPDATE MyOrder SET orderstatus = 'Cancel' WHERE id = ?";

    public void cancelOrder(String orderId) {
        try {
            Connection con = DatabaseUtils.getConnection();
            PreparedStatement st = con.prepareStatement(CANCEL);
            st.setString(1, orderId);

            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OrderDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
