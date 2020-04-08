package contract.network;



import network.IClient;

import java.util.HashMap;

public class RMIClientHandler {


    private HashMap<String, IClient> clients;
    public RMIClientHandler() {clients= new HashMap<>();}
    public void addClient (String clientID,IClient client)
    {
        clients.put(clientID,client);
    }
    public IClient getClient(String clientID)
    {
        return clients.get(clientID);
    }


}
