package dto;

import error.ValidationError;

import java.util.ArrayList;
import java.util.List;

public class ChangePassDto implements DtoBase {

    private String oldPassword;
    private String newPassword;
    private String confirm;

    public ChangePassDto() {
    }

    public ChangePassDto(String oldPassword, String newPassword, String confirm) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.confirm = confirm;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
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

        // Validate password format
        if (newPassword == null || newPassword.trim().isEmpty()) {
            ValidationError error = new ValidationError();
            error.setName("password");
            error.setMessage("Password is required.");
            error.setValue(newPassword);
            errors.add(error);
        } else if (newPassword.length() < 6) {
            ValidationError error = new ValidationError();
            error.setName("password");
            error.setMessage("Password must be at least 6 characters long.");
            error.setValue(newPassword);
            errors.add(error);
        } else if (!containsUppercase(newPassword)) {
            ValidationError error = new ValidationError();
            error.setName("password");
            error.setMessage("Password must contain at least one uppercase letter.");
            error.setValue(newPassword);
            errors.add(error);
        } else if (!containsDigit(newPassword)) {
            ValidationError error = new ValidationError();
            error.setName("password");
            error.setMessage("Password must contain at least one digit.");
            error.setValue(newPassword);
            errors.add(error);
        }

        // Validate new password not equal to old password
        if (newPassword != null && newPassword.equals(oldPassword)) {
            ValidationError error = new ValidationError();
            error.setName("password");
            error.setMessage("New password is not the same as the old password.");
            error.setValue(newPassword);
            errors.add(error);
        }

        // Validate new password matches confirm
        if (!newPassword.equals(confirm)) {
            ValidationError error = new ValidationError();
            error.setName("password");
            error.setMessage("Password does not match the confirmation.");
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
