/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.sql.Date;
/**
 *
 * @author MSI PC
 */
public class Invoice {
    private int id;
    private Date invoiceDate;
    private Supplier supplier;
    

    public Invoice() {
    }

    public Invoice(int id, Date invoiceDate, Supplier supplier) {
        this.id = id;
        this.invoiceDate = invoiceDate;
        this.supplier = supplier;
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    
    
}
