package aidian3k.springframework.studentrestapi.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Repository {
    private String name;
    private String html_url;
    private String clone_url;
    private String language;
    private String description;
}
