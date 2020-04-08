package contract;

import javaModel.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.http.converter.json.GsonFactoryBean;

import javax.annotation.PostConstruct;
import java.rmi.RemoteException;


public class Testing implements CommandLineRunner {

    @Autowired
    App app;
    static App staticApp;


    @PostConstruct
    public void init() {
        staticApp = app;
    }


    public static void main(String[] args) throws RemoteException {
        SpringApplication.run(Testing.class, args);


    }


    @Override
    public void run(String... strings) throws Exception {

    }

    public static void Login() throws RemoteException {
        staticApp.Login(new Login("admin", "admin"), "clientId");
    }


}


