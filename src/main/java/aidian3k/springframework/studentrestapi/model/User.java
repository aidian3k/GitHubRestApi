package aidian3k.springframework.studentrestapi.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class User {
    private Long id;
    private String login;
    private String html_url;
}
