package org.itstep.onlinemarketplace.models;

import lombok.Data;

@Data
public class UserModel {

    private String email;

    private String fullName;

    private String password;

    private String confirmPassword;

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

}
