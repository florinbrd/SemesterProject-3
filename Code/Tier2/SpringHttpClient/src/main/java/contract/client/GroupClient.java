package contract.client;

import com.google.gson.Gson;
import javaModel.Group;
import javaModel.GroupMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;


@Component
public class GroupClient {
    @Autowired
    private RestOperations restOperations;

    private final String url = "https://localhost:5001/api/group";
    private final String url2 = "https://localhost:5001/api/group/{groupId}";

    public GroupClient() {


    }


    public Group postGroup(Group group) {
        return restOperations.postForObject(url, group, Group.class);

    }

    public GroupMember postMember(GroupMember groupMember) {

        return restOperations.postForObject(url2, groupMember, GroupMember.class, 0);
    }

    public GroupMember changeGroupRole(GroupMember groupMember) {
        return restOperations.postForObject(url2, groupMember, GroupMember.class, 1);
    }

    public boolean deleteMember(GroupMember groupMember) {
        String url3 = "https://localhost:5001/api/group/{username}";
        String username = groupMember.user.getUsername();
        restOperations.delete(url3, username);
        return true;
    }

    public Group getGroup(int groupID) {
         return restOperations.getForObject(url2, Group.class, groupID);

    }
}