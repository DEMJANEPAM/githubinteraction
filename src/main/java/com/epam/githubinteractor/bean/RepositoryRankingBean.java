package com.epam.githubinteractor.bean;

import java.util.Objects;

public class RepositoryRankingBean
{
    private int repositoryStarRank;
    private String repositoryName;

    public RepositoryRankingBean(int repositoryStarRank, String repositoryName)
    {
        this.repositoryStarRank = repositoryStarRank;
        this.repositoryName = repositoryName;
    }

    public int getRepositoryStarRank()
    {
        return repositoryStarRank;
    }

    public void setRepositoryStarRank(int repositoryStarRank)
    {
        this.repositoryStarRank = repositoryStarRank;
    }

    public String getRepositoryName()
    {
        return repositoryName;
    }

    public void setRepositoryName(String repositoryName)
    {
        this.repositoryName = repositoryName;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        RepositoryRankingBean that = (RepositoryRankingBean) o;
        return repositoryStarRank == that.repositoryStarRank &&
                Objects.equals(repositoryName, that.repositoryName);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(repositoryStarRank, repositoryName);
    }
}
