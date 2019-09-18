package com.epam.githubinteractor;

import static java.util.Collections.singletonList;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.epam.githubinteractor.interceptor.LoggingRequestInterceptor;
import com.fasterxml.jackson.databind.JsonNode;


public class GitHubServiceClient
{
    private static final String SEARCH_REPOSITORY_URL = "https://api.github.com/search/repositories";
    private RestTemplate restTemplate = initializeHttpClient();

    private RestTemplate initializeHttpClient()
    {
        RestTemplate restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new LoggingRequestInterceptor());
        restTemplate.setInterceptors(interceptors);
        return restTemplate;
    }

    private HttpEntity<String> initializeHeaders()
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(singletonList(MediaType.APPLICATION_JSON));
        return new HttpEntity<>(headers);
    }

    public ResponseEntity<JsonNode> searchRepositoryByName(MultiValueMap<String, String> searchRepositoryQueryParametersMap)
    {
        HttpEntity<String> entity = initializeHeaders();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(SEARCH_REPOSITORY_URL).queryParams(searchRepositoryQueryParametersMap);
        return restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, JsonNode.class);
    }
}
