package contract.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;


@Component
public class ValueClient {
    @Autowired
    private RestOperations restOperations;
    private final String url;
    @Autowired
    public ValueClient(@Value("${csharp.url}")final String url)
    {
        this.url=url;
    }

    public contract.client.Value getContract(final int value)
    {
      return restOperations.getForObject(url, contract.client.Value.class,value);

    }
}
