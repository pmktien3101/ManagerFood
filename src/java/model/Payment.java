/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author MSI PC
 */
public class Payment {
    private int id;
    private String paymentForm;

    public Payment() {
    }

    public Payment(int id, String PaymentForm) {
        this.id = id;
        this.paymentForm = PaymentForm;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPaymentForm() {
        return paymentForm;
    }

    public void setPaymentForm(String PaymentForm) {
        this.paymentForm = PaymentForm;
    }

    @Override
    public String toString() {
        return "Payment{" + "id=" + id + ", paymentForm=" + paymentForm + '}';
    }
    
}
