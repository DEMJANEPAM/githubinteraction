package com.epam.githubinteractor;

public class Application
{
    public static void main(String[] args) {
        new GitHubServiceCallManager().callSearchRepositoryEndpoint();
    }
}
