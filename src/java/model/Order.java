/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.sql.Date;
import java.util.List;
/**
 *
 * @author MSI PC
 */
public class Order{
    private int id;
    private Date orderDate;
    private double orderTotal;
    private Date deliveryDate;
    private String orderStatus;
    private String deliveryAddress;
    private Payment payment;
    private Account account;
    private List<Cart> cartList;
    
    
    public Order() {
    }

    public Order(int id, Date orderDate, double orderTotal, Date deliveryDate, String orderStatus, String deliveryAddress, Payment paymentForm, Account account, List<Cart> cartList) {
        this.id = id;
        this.orderDate = orderDate;
        this.orderTotal = orderTotal;
        this.deliveryDate = deliveryDate;
        this.orderStatus = orderStatus;
        this.deliveryAddress = deliveryAddress;
        this.payment = paymentForm;
        this.account = account;
        this.cartList = cartList;
    }

    public Order(int id, Date orderDate, double orderTotal, Date deliveryDate, String orderStatus, String deliveryAddress, Payment paymentForm, Account account) {
        this.id = id;
        this.orderDate = orderDate;
        this.orderTotal = orderTotal;
        this.deliveryDate = deliveryDate;
        this.orderStatus = orderStatus;
        this.deliveryAddress = deliveryAddress;
        this.payment = paymentForm;
        this.account = account;
    }

    public Order(int id, Date orderDate, double orderTotal, Date deliveryDate, String orderStatus, String deliveryAddress, Payment paymentForm) {
        this.id = id;
        this.orderDate = orderDate;
        this.orderTotal = orderTotal;
        this.deliveryDate = deliveryDate;
        this.orderStatus = orderStatus;
        this.deliveryAddress = deliveryAddress;
        this.payment = paymentForm;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Cart> getCartList() {
        return cartList;
    }

    public void setCartList(List<Cart> cartList) {
        this.cartList = cartList;
    }

    
   


    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Payment getPaymentForm() {
        return payment;
    }

    public void setPaymentForm(Payment paymentForm) {
        this.payment = paymentForm;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", orderDate=" + orderDate + ", orderTotal=" + orderTotal + ", deliveryDate=" + deliveryDate + ", orderStatus=" + orderStatus + ", deliveryAddress=" + deliveryAddress + ", paymentForm=" + payment + ", account=" + account + ", cartList=" + cartList + '}';
    }

    
   
    
}
