package aidian3k.springframework.studentrestapi.service;

import aidian3k.springframework.studentrestapi.model.GitHubApiResponse;
import aidian3k.springframework.studentrestapi.webclient.GitHubWebClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GitHubApiService {
    private final GitHubWebClient gitHubWebClient;

    public GitHubApiService(GitHubWebClient gitHubWebClient) {
        this.gitHubWebClient = gitHubWebClient;
    }

    @Cacheable(cacheNames = "getSingleUser")
    public GitHubApiResponse getSingleUser(String username) {

        return gitHubWebClient.getUserStats(username);
    }
}
