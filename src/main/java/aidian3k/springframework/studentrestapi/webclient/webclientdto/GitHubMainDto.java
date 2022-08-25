package aidian3k.springframework.studentrestapi.webclient.webclientdto;

import lombok.Getter;
@Getter
public class GitHubMainDto {
    private GitHubUserDto owner;
    private String name;
    private String html_url;
    private String clone_url;
    private String language;
}
