/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author MSI PC
 */
public class Account {

    private String name;
    private String email;
    private String phone;
    private Region region;
    private String taxCode;
    private String password;

    public Account() {
    }

    public Account(String name, String email, String phone, Region region, String taxCode) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.region = region;
        this.taxCode = taxCode;
    }

    public Account(String name, String email) {
        this.name = name;
        this.email = email;
    }



    public Account(String name, String email, String phone, Region region, String taxCode, String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.region = region;
        this.taxCode = taxCode;
        this.password = password;
    }

 
  

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    @Override
    public String toString() {
        return "Account{" + "name=" + name + ", email=" + email + ", phone=" + phone + ", region=" + region + ", taxCode=" + taxCode + ", password=" + password + '}';
    }

}
