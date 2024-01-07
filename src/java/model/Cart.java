/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class Cart {
    private int id;
    private Product product;
    private int quantity;
    private double subtotal;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   public Product getProduct() {
        return product;
    }

    public Cart(Product product) {
        this.product = product;
    }

    public void setProduct(Product product) {
        this.product = product;
        this.subtotal = product.getPrice() * quantity;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }



    public int getQuantity() {
        return quantity;
    }


    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.subtotal = product.getPrice()*quantity;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public Cart(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.subtotal = product.getPrice()*quantity;
    }

    public Cart() {
    }
    
}
