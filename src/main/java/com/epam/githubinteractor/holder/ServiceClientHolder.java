package com.epam.githubinteractor.holder;

import com.epam.githubinteractor.GitHubServiceClient;

public class ServiceClientHolder
{
    private static GitHubServiceClient instance;

    private ServiceClientHolder() {}

    public static GitHubServiceClient getInstance() {
        if(instance == null){
            instance = new GitHubServiceClient();
        }
        return instance;
    }
}
