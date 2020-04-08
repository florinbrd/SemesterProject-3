package javaModel;

import java.io.Serializable;

public class Login implements Serializable {
    private String username;
    private String password;
    private Boolean validated;


    public Login()
    {

    }

    public Boolean isValidated() {
        return validated;
    }

    public void setValidated(Boolean validated) {
        this.validated = validated;
    }

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
        validated=false;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
