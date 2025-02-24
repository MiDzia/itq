package by.walker.orders.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalApiService {

    private final RestTemplate restTemplate = new RestTemplate();

    public String getNumber(String url) {
        return restTemplate.getForObject(url, String.class);
    }

}
