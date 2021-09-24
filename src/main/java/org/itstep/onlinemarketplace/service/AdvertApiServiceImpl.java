package org.itstep.onlinemarketplace.service;

import org.itstep.onlinemarketplace.entities.Advert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@Service
@EnableWebSecurity
public class AdvertApiServiceImpl implements AdvertApiService {

    private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(3);

    @Value("${api.market.adverts}")
    private String adverts_path;

    private WebClient localApiClient;

    @Autowired
    public void UserService(WebClient localApiClient) {
        this.localApiClient = localApiClient;
    }

    @Bean
    public WebClient localApiClient() {
        return WebClient.create(adverts_path);
    }

    @Override
    public List<Advert> getAdverts() {
        Mono<List<Advert>> response = localApiClient.get()
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Advert>>() {});
        List<Advert> readers = response.block();

        return readers.stream()
                .collect(Collectors.toList());
    }

    @Override
    public Mono<Advert> addAdvert(Advert advert) {
        return localApiClient.post()
                .uri("/")
                .body(Mono.just(advert), Advert.class)
                .retrieve()
                .bodyToMono(Advert.class);
    }
}
