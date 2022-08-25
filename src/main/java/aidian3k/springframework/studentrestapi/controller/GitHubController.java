package aidian3k.springframework.studentrestapi.controller;

import aidian3k.springframework.studentrestapi.model.GitHubApiResponse;
import aidian3k.springframework.studentrestapi.service.GitHubApiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GitHubController {
    private final GitHubApiService gitHubApiService;
    private static final String DEFAULT_USER = "aidian3k";

    public GitHubController(GitHubApiService gitHubApiService) {
        this.gitHubApiService = gitHubApiService;
    }

    @GetMapping({"", "/"})
    public String getDefaultMapping() {
        return "Application is working!";
    }

    @GetMapping("statistics")
    public List<GitHubApiResponse> getSingleUser(@RequestParam(required = false, defaultValue = DEFAULT_USER) String username) {
        return gitHubApiService.getSingleUser(username);
    }
}
