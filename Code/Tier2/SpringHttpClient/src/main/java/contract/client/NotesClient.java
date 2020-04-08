package contract.client;
import javaModel.Login;
import javaModel.MeetingNotes;
import javaModel.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;


@Component
public class NotesClient {

    @Autowired
    private RestOperations restOperations;
    private  String url= "https://localhost:5001/api/notes";


    public NotesClient()
    {

    }

    public MeetingNotes postNotes(MeetingNotes meetingNotes)
    {
        return restOperations.postForObject(url,meetingNotes,MeetingNotes.class);
    }


    public MeetingNotes loadMeetingNotes(int groupId) {
        String url="https://localhost:5001/api/notes/{groupId}";
        return restOperations.getForObject(url,MeetingNotes.class,groupId);
    }
}