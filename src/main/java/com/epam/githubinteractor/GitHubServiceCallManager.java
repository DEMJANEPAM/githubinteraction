package com.epam.githubinteractor;

import static java.util.Collections.singletonList;

import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.epam.githubinteractor.bean.RepositoryRankingBean;
import com.epam.githubinteractor.holder.ServiceClientHolder;
import com.fasterxml.jackson.databind.JsonNode;


public class GitHubServiceCallManager
{
    private static final Logger logger = Logger.getLogger(GitHubServiceCallManager.class);
    private static final String REPOSITORY_NAME_SEARCH_PARAMETER = "TestNG";
    private static final String SORT_BY_SEARCH_PARAMETER = "stars";
    private static final String ORDER_BY_SEARCH_PARAMETER = "desc";
    private GitHubServiceClient gitHubServiceClient = ServiceClientHolder.getInstance();

    public void callSearchRepositoryEndpoint() {
        logger.info("Start connection to Git hub....");
        ResponseEntity<JsonNode> response = gitHubServiceClient.searchRepositoryByName(prepareSearchCriteriaParameters());
        RepositoryRankingBean repositoryRankingBean = getTopResult(response);
        logger.info("Finished connection to Git hub.");
        logger.info("Top repository name: " + repositoryRankingBean.getRepositoryName());
        logger.info("Top repository stars count: " + repositoryRankingBean.getRepositoryStarRank());
    }

    public MultiValueMap <String, String> prepareSearchCriteriaParameters() {
        MultiValueMap <String, String> params = new LinkedMultiValueMap<>();
        params.put("q", singletonList(REPOSITORY_NAME_SEARCH_PARAMETER));
        params.put("sort", singletonList(SORT_BY_SEARCH_PARAMETER));
        params.put("order", singletonList(ORDER_BY_SEARCH_PARAMETER));
        return params;
    }

    public RepositoryRankingBean getTopResult(ResponseEntity<JsonNode> response) {
        JsonNode topResultMap = response.getBody().get("items").get(0);
        return new RepositoryRankingBean(topResultMap.get("stargazers_count").asInt(), topResultMap.get("name").asText());
    }
}
