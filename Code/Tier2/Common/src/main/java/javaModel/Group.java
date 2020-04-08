package javaModel;


import java.io.Serializable;
import java.util.ArrayList;

public class Group implements Serializable {

    public SprintList sprintList;
    public ProductBacklog productBacklog;
    public ArrayList<GroupMember> groupMembers;
    private boolean validated;
    private int groupId;

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public SprintList getSprintList() {
        return sprintList;
    }

    public void setSprintList(SprintList sprintList) {
        this.sprintList = sprintList;
    }

    public ProductBacklog getProductBacklog() {
        return productBacklog;
    }

    public void setProductBacklog(ProductBacklog productBacklog) {
        this.productBacklog = productBacklog;
    }

    public void setGroupMembers(ArrayList<GroupMember> groupMembers) {
        this.groupMembers = groupMembers;
    }

    public boolean isValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    public Group() {
        groupMembers= new ArrayList<>();
        sprintList= new SprintList();
        productBacklog = new ProductBacklog();
    }


    public Group(SprintList sprintList, ProductBacklog productBacklog,int groupId) {
        this.sprintList = sprintList;
        this.productBacklog = productBacklog;
        groupMembers = new ArrayList<>();
        validated =false;
        this.groupId = groupId;
    }

    public void addMember(GroupMember e) {
        groupMembers.add(e);
    }

    public void removeMemberByMember(GroupMember e) {
        for (int i = 0; i < groupMembers.size(); i++) {
            if (groupMembers.get(i).equals(e)) {
                groupMembers.remove(i);
            }
        }
    }

    public void removeGroupMemberByIndex(int index) {
        groupMembers.remove(index);
    }

    public GroupMember[] getGroupMembers() {
        GroupMember[] membersArray = new GroupMember[groupMembers.size()];
        membersArray = groupMembers.toArray(membersArray);
        return membersArray;
    }



}
