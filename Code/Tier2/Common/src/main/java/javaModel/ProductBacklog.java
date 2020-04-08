package javaModel;

import java.io.Serializable;
import java.util.ArrayList;

public class ProductBacklog implements Serializable {

    private int groupId;
    public ArrayList<UserStory> userStories;

    public void setUserStories(ArrayList<UserStory> userStories) {
        this.userStories = userStories;
    }

    public ProductBacklog() {
        userStories = new ArrayList<>();
    }

    public void removeUserStory(UserStory e) {
        for (int i = 0; i < userStories.size(); i++) {
            if (userStories.get(i).equals(e)) {
                userStories.remove(i);
            }
        }
    }

    public void removeUserStoryByIndex(int index) {
        userStories.remove(index);
    }

    public void addUserStory(UserStory e) {
        userStories.add(e);
    }

    public ArrayList<UserStory> getUserStories() {
        return userStories;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
}
