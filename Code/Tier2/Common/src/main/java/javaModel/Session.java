package javaModel;

import java.io.Serializable;

public class Session  implements Serializable {

    private int groupId;
    private String groupRole;
    private String username;
    private boolean validated;
    private int numberOfSprints;
    private int activeSprint;

    public int getNumberOfSprints() {
        return numberOfSprints;
    }

    public void setNumberOfSprints(int numberOfSprints) {
        this.numberOfSprints = numberOfSprints;
    }

    public int getActiveSprint() {
        return activeSprint;
    }

    public void setActiveSprint(int activeSprint) {
        this.activeSprint = activeSprint;
    }

    public boolean isValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    public Session() {

    }

    public Session(int groupId, String groupRole, String username) {
        this.groupId = groupId;
        this.groupRole = groupRole;
        this.username = username;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupRole() {
        return groupRole;
    }

    public void setGroupRole(String groupRole) {
        this.groupRole = groupRole;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
