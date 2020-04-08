package contract.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import org.springframework.stereotype.Component;

@Component
public class ClientProvider {

    @Autowired
    private BacklogClient backlogClient;
    @Autowired
    private UserClient userClient;
    @Autowired
    private GroupClient groupClient;


    public UserClient getUserClient()
    {
        return userClient;
    }

    public GroupClient getGroupClient ()
    {
        return groupClient;
    }

    public BacklogClient getBacklogClient ()
    {
        return backlogClient;
    }


}
