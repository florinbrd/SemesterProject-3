package javaModel;

import java.io.Serializable;

public class GroupMember implements Serializable {

    public User user;
    public String role;
    public int groupID;
    private boolean validated;


    public GroupMember () { }

    public boolean isValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    public GroupMember(User user, String role)
    {
        this.user = user;
        this.role = role;
        validated=false;
    }

    public User getUser(){
        return user;
    }

    public void setUser(User user){
        this.user = user;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    }

