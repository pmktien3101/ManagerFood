/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import error.ValidationError;
import java.util.ArrayList;
import java.util.List;
import model.Region;
import utils.Encode;

public class RegisterDto implements DtoBase {

    private String name;
    private String email;
    private String phone;
    private String region;
    private String taxCode;
    private String password;
    private String confirm;

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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirm;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirm = confirmPassword;
    }

    @Override
    public List<ValidationError> validate() {
        List<ValidationError> errors = new ArrayList<>();

        // Validate name
        if (name == null || name.trim().length() < 2) {
            ValidationError error = new ValidationError();
            error.setName("name");
            error.setMessage("Name must be at least 2 characters");
            error.setValue(name);
            errors.add(error);
        }

        // Validate phone
        if (phone == null || !phone.matches("\\d{10}")) {
            ValidationError error = new ValidationError();
            error.setName("phone");
            error.setMessage("Phone must be 10 digits");
            error.setValue(phone);
            errors.add(error);
        }

        // Validate password
        if (password == null || password.length() < 6) {
            ValidationError error = new ValidationError();
            error.setName("password");
            error.setMessage("Must be at least 6 characters");
            error.setValue(password);
            errors.add(error);
        } else if (!password.matches(".*[A-Z].*")) {
            ValidationError error = new ValidationError();
            error.setName("password");
            error.setMessage("Must contain at least 1 uppercase letter");
            error.setValue(password);
            errors.add(error);
        } else if (!password.matches(".*\\d.*")) {
            ValidationError error = new ValidationError();
            error.setName("password");
            error.setMessage("Must contain at least 1 digit");
            error.setValue(password);
            errors.add(error);
        }

        // Validate password confirmation
        if (!password.equals(confirm)) {
            ValidationError error = new ValidationError();
            error.setName("confirm");
            error.setMessage("Password does not match");
            error.setValue(confirm);
            errors.add(error);
        } else {
            password = Encode.toSHA1(password);
        }

        // Validate email format
        if (email == null || !email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            ValidationError error = new ValidationError();
            error.setName("email");
            error.setMessage("Email must be [name@gmail.com]");
            error.setValue(email);
            errors.add(error);
        }
        // Validate taxCode
        if (taxCode == null || !taxCode.matches("EIN-\\d{5}")) {
            ValidationError error = new ValidationError();
            error.setName("taxcode");
            error.setMessage("Wrong tax code");
            error.setValue(taxCode);
            errors.add(error);
        }

        return errors;
    }

}
