package contract;

import javaModel.User;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.rmi.RemoteException;

import static org.junit.jupiter.api.Assertions.*;


public class AppTest {
    @InjectMocks
    App app;
    User user = new User("admin","admin");

    @Test
    public void testAddAccount() throws RemoteException {
       boolean bol = app.addAccount(user,"clientId");
      assertEquals(false,bol);
    }


}