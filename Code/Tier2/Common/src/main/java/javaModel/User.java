package javaModel;

import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private String password;
    private int id;
    private boolean validated = false;

    public boolean isValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    public User() {

    }


    public User(String fName, String lName, String eMail, String username, String password, int id) {

        this.username = username;
        this.password = password;
        this.id = id;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String toString() {
        return "User{" +
                ", account='" + username + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                '}';
    }


    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id == user.id &&
                username.equals(user.username) &&
                password.equals(user.password);
    }
}


