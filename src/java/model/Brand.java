/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author MSI PC
 */
public class Brand {
    public int id;
    public String name;

    public Brand() {
    }

    public Brand(int id, String Name) {
        this.id = id;
        this.name = Name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String Name) {
        this.name = Name;
    }

    @Override
    public String toString() {
        return "Brand{" + "id=" + id + ", name=" + name + '}';
    }
    
    
}
