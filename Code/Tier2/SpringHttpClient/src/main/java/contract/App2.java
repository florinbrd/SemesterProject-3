//package contract;
//
//
//import contract.client.ClientProvider;
//import javaModel.User;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import contract.client.Value;
//import contract.client.ValueClient;
//
//@SpringBootApplication
//public class App2 implements CommandLineRunner {
//
//    public static void main(String[] args) {
//        SpringApplication.run(App2.class, args);
//    }
//
//
//    private static final Logger logger = LoggerFactory.getLogger(App2.class);
//
//    @Autowired
//    private ValueClient valueClient;
//
//    @Autowired
//    ClientProvider clientProvider;
//
//    @Override
//    public void run(String... strings) throws Exception {
//        Value value = valueClient.getContract(2);
//        logger.info("Response: {}", value);
//        clientProvider.getUserClient().CreateUser(new User());
//
//
//    }
//
//
//}
