package com.epam.githubinteractor;

import static com.epam.githubinteractor.TestUtils.prepareResponseEntity;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;

@RunWith(MockitoJUnitRunner.class)
public class GitHubServiceClientTest
{
    @Mock
    private RestTemplate restTemplate;
    @InjectMocks
    private GitHubServiceClient gitHubServiceClient;
    private GitHubServiceCallManager gitHubServiceCallManager = new GitHubServiceCallManager();
    private ResponseEntity<JsonNode> responseEntity;

    @Before
    public void setUp()
    {
        responseEntity = prepareResponseEntity();
    }

    @Test
    public void searchRepositoryByNameTest()
    {
        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), any(Class.class))).thenReturn(responseEntity);
        ResponseEntity<JsonNode> actualResponse = gitHubServiceClient.searchRepositoryByName(gitHubServiceCallManager.prepareSearchCriteriaParameters());
        assertThat("Verify maps are identical", actualResponse, is(responseEntity));
    }
}