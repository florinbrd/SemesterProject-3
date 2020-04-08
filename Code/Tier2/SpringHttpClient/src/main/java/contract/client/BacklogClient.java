package contract.client;


import javaModel.ProductBacklog;
import javaModel.UserStory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;


@Component
public class BacklogClient {
    @Autowired
   RestOperations restOperations;



    private final String backlogUrl = "https://localhost:5001/api/backlog/{groupID}";
    private final String storyUrl = "https://localhost:5001/api/backlog";


    public BacklogClient() {
    }


    public ProductBacklog getBacklog(int groupID) {
        return restOperations.getForObject(backlogUrl, ProductBacklog.class, groupID);

    }

    public UserStory addUserStory(UserStory userStory) {
        return restOperations.postForObject(storyUrl, userStory, UserStory.class);
    }
}