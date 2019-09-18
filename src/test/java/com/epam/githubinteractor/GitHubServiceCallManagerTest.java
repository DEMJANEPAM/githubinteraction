package com.epam.githubinteractor;

import static com.epam.githubinteractor.TestUtils.prepareResponseEntity;
import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.epam.githubinteractor.bean.RepositoryRankingBean;

@RunWith(MockitoJUnitRunner.class)
public class GitHubServiceCallManagerTest
{
    private static final String REPOSITORY_NAME_SEARCH_PARAMETER = "TestNG";
    private static final String SORT_BY_SEARCH_PARAMETER = "stars";
    private static final String ORDER_BY_SEARCH_PARAMETER = "desc";
    @Mock
    private GitHubServiceClient gitHubServiceClient;
    @InjectMocks
    private GitHubServiceCallManager gitHubServiceCallManager = new GitHubServiceCallManager();

    @Test
    public void callSearchRepositoryEndpointTest()
    {
        when(gitHubServiceClient.searchRepositoryByName(any(MultiValueMap.class))).thenReturn(prepareResponseEntity());
        gitHubServiceCallManager.callSearchRepositoryEndpoint();
        verify(gitHubServiceClient).searchRepositoryByName(gitHubServiceCallManager.prepareSearchCriteriaParameters());
    }

    @Test
    public void prepareSearchCriteriaParametersTest()
    {
        MultiValueMap <String, String> actualMap = gitHubServiceCallManager.prepareSearchCriteriaParameters();
        MultiValueMap <String, String> expectedMap = new LinkedMultiValueMap<>();
        expectedMap.put("q", singletonList(REPOSITORY_NAME_SEARCH_PARAMETER));
        expectedMap.put("sort", singletonList(SORT_BY_SEARCH_PARAMETER));
        expectedMap.put("order", singletonList(ORDER_BY_SEARCH_PARAMETER));
        assertThat("Verify maps are identical", actualMap, is(expectedMap));
    }

    @Test
    public void getTopResultTest()
    {
        RepositoryRankingBean resultBean = gitHubServiceCallManager.getTopResult(prepareResponseEntity());
        RepositoryRankingBean expectedBean = new RepositoryRankingBean(1345, "testng");
        assertThat("Verify test top results are identical", resultBean, is(expectedBean));
    }
}