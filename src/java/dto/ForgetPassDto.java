package dto;

import error.ValidationError;

import java.util.ArrayList;
import java.util.List;
import utils.Encode;

public class ForgetPassDto implements DtoBase {

    private String email;
    private String newPassword;
    private String confirm;

    public ForgetPassDto() {
    }

    public ForgetPassDto(String email, String newPassword, String confirm) {
        this.email = email;
        this.newPassword = newPassword;
        this.confirm = confirm;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    @Override
    public List<ValidationError> validate() {
        List<ValidationError> errors = new ArrayList<>();

        // Validate password
        if (newPassword == null || newPassword.length() < 6) {
            ValidationError error = new ValidationError();
            error.setName("password");
            error.setMessage("Must be at least 6 characters");
            error.setValue(newPassword);
            errors.add(error);
        } else if (!newPassword.matches(".*[A-Z].*")) {
            ValidationError error = new ValidationError();
            error.setName("password");
            error.setMessage("Must contain at least 1 uppercase letter");
            error.setValue(newPassword);
            errors.add(error);
        } else if (!newPassword.matches(".*\\d.*")) {
            ValidationError error = new ValidationError();
            error.setName("password");
            error.setMessage("Must contain at least 1 digit");
            error.setValue(newPassword);
            errors.add(error);
        }

        // Validate password confirmation
        if (!newPassword.equals(confirm)) {
            ValidationError error = new ValidationError();
            error.setName("confirm");
            error.setMessage("Password does not match");
            error.setValue(confirm);
            errors.add(error);
        } 

        return errors;
    }

    private boolean containsUppercase(String str) {
        for (char c : str.toCharArray()) {
            if (Character.isUpperCase(c)) {
                return true;
            }
        }
        return false;
    }

    private boolean containsDigit(String str) {
        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }
}
