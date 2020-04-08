package contract.client;

import javaModel.Sprint;
import javaModel.SprintList;
import javaModel.Task;
import javaModel.UserStory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;

import javax.print.StreamPrintServiceFactory;


@Component
public class SprintClient {

    @Autowired
    private RestOperations restOperations;
    private final String urlDuration = "https://localhost:5001/api/sprintbacklog/{code}";
    private final String urlNumber = "https://localhost:5001/api/sprintbacklog/{code}";
    private final String url = "https://localhost:5001/api/sprintbacklog/{operation}/{branch}";
    private final String urlTask = "https://localhost:5001/api/userstory";
    private final String urlGetTask = "https://localhost:5001/api/userstory/{storyId}";



    public SprintClient() {

    }


    public SprintList setSprintDuration(SprintList sprintList) {
       return  restOperations.postForObject(urlDuration, sprintList, SprintList.class,0);
    }

    public SprintList setSprintNumber(SprintList sprintList) {
        return restOperations.postForObject(urlNumber, sprintList, SprintList.class,1);
    }


    public UserStory postStoryToSprint(UserStory userStory) {

        return restOperations.postForObject(url, userStory, UserStory.class,0,0);
    }

    public UserStory postFromSprintToBacklog(UserStory userStory) {
        return restOperations.postForObject(url, userStory, UserStory.class,1,0);
    }

    public Task addTaskToUserStory(Task task) {

        return restOperations.postForObject(urlTask, task, Task.class);
    }
    public UserStory getTasks(int storyId)
    {
        return restOperations.getForObject(urlGetTask,UserStory.class,storyId);
    }


    public Sprint getSprintBacklog(int sprintNumber, int groupId) {

        String url ="https://localhost:5001/api/sprintbacklog/{sprintNumber}/{groupId}";
        Sprint sprint = restOperations.getForObject(url,Sprint.class,sprintNumber,groupId);
        return sprint;
    }
}