package aidian3k.springframework.studentrestapi.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GitHubApiResponse {
    private Repository repository;
    private User user;
}
