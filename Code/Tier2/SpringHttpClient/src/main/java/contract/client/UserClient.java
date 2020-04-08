package contract.client;
import javaModel.Login;
import javaModel.RequestWrapper;
import javaModel.Session;
import javaModel.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Component;

import org.springframework.web.client.RestOperations;

import java.util.ArrayList;


@Component
public class UserClient {

    @Autowired
    private RestOperations restOperations;
    private final String url;
    @Autowired
    public UserClient(@Value("${user.url}")final String url)
    {
        this.url=url;
    }

    public Session PostLogin(Login login)
    {
        RequestWrapper requestWrapper = new RequestWrapper();
        requestWrapper.setObj1(login);
        requestWrapper =restOperations.postForObject(url,requestWrapper,RequestWrapper.class);

       return  requestWrapper.getObj2();

    }

    public User CreateUser (User user)
    {

        String url="https://localhost:5001/api/user";


        return restOperations.postForObject(url,user,User.class);

    }

    public ArrayList loadAllUsernames() {

        String url="https://localhost:5001/api/user";
        return restOperations.getForObject(url,ArrayList.class);
    }

    public Session getSession(String username) {

        String url="https://localhost:5001/api/user/{username}";
        return restOperations.getForObject(url,Session.class);
    }
}