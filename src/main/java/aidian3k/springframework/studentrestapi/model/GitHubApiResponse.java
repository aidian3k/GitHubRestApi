package aidian3k.springframework.studentrestapi.model;

import lombok.*;

@Getter
@Builder
public class GitHubApiResponse {
    private User user;
    private Repository [] repositories;
}
