/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import error.ValidationError;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MSI PC
 */
public class ChangeNameDto implements DtoBase{
     private String oldName;
    private String newName;

    public ChangeNameDto() {
    }

    public ChangeNameDto(String oldName, String newName) {
        this.oldName = oldName;
        this.newName = newName;
    }

    public String getOldName() {
        return oldName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }
    
     @Override
    public List<ValidationError> validate() {
        List<ValidationError> errors = new ArrayList<>();

        // Validate name
        if (newName == null || newName.trim().length() < 2) {
            ValidationError error = new ValidationError();
            error.setName("name");
            error.setMessage("Name must be at least 2 characters");
            error.setValue(newName);
            errors.add(error);
        }
        // Check if old name is the same as new name
        if (newName != null && newName.equals(oldName)) {
            ValidationError error = new ValidationError();
            error.setName("name");
            error.setMessage("New name must be different from the old name");
            error.setValue(newName);
            errors.add(error);
        }
        return errors;
    }
    
}
