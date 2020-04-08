package contract.config;

import contract.App;
import network.IServer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.remoting.rmi.RmiServiceExporter;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.rmi.RemoteException;

@Configuration
public class RestConfig {
    @Bean
    public RestOperations createRestTemplate()
    {
        return new RestTemplate();
    }
    @Bean
    IServer serverService() throws RemoteException {
        return new App();
    }
    @Bean
    RmiServiceExporter exporter(@Qualifier("serverService") IServer implementation) {
        Class<IServer> serviceInterface = IServer.class;
        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setServiceInterface(serviceInterface);
        exporter.setService(implementation);
        exporter.setServiceName(serviceInterface.getSimpleName());
        exporter.setRegistryPort(1099);
        return exporter;
    }

    @Bean
    public ClientHttpRequestFactory createClientHttpRequestFactory(@Value("${connect.timeout}")final int connectTimeout, @Value("${read.timeout}")final int readTimeout)
    {
        HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpComponentsClientHttpRequestFactory.setReadTimeout(readTimeout);
        httpComponentsClientHttpRequestFactory.setConnectTimeout(connectTimeout);
        return  httpComponentsClientHttpRequestFactory;
    }
}
