package com.tradinggames.api.models;
/**
 * Classe para modelo do User
 */

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document
public class UserModel {

    @Id
    private String userId;

    private String userName;

    private String secondName;

    @NotNull
    @Indexed(unique = true)
    private String userEmail;

    @NotNull
    private String userPassword;

    public UserModel() {
    }

    public UserModel(String userId, String userName, String secondName, @NotNull String userEmail, @NotNull String userPassword) {
        this.userId = userId;
        this.userName = userName;
        this.secondName = secondName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
