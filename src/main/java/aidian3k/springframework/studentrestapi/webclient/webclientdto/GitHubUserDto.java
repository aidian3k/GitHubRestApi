package aidian3k.springframework.studentrestapi.webclient.webclientdto;

import lombok.Getter;

@Getter
public class GitHubUserDto {
    private String login;
    private Long id;
    private String html_url;
}
