package aidian3k.springframework.studentrestapi.webclient;

import aidian3k.springframework.studentrestapi.exception.RequestLimitExceededException;
import aidian3k.springframework.studentrestapi.exception.UserNotFoundException;
import aidian3k.springframework.studentrestapi.model.GitHubApiResponse;
import aidian3k.springframework.studentrestapi.model.Repository;
import aidian3k.springframework.studentrestapi.model.User;
import aidian3k.springframework.studentrestapi.webclient.webclientdto.GitHubMainDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GitHubWebClient {
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String GIT_HUB_API_URL = "https://api.github.com/users/";

    public List<GitHubApiResponse> getUserStats(String username) {
        GitHubMainDto[] gitHubMainDto;

        try {
            gitHubMainDto = restTemplate.
                    getForObject(GIT_HUB_API_URL + "{username}" + "/repos", GitHubMainDto[].class, username);

            assert gitHubMainDto != null;
            return Arrays.
                    stream(gitHubMainDto).
                    map(GitHubWebClient::gitHubApiResponseConverter).
                    collect(Collectors.toList());

        } catch (RestClientResponseException exception) {
            if (exception.getRawStatusCode() == 404) {
                throw new UserNotFoundException("The username " + username + "has not been found!", (GIT_HUB_API_URL + "%s" + "/repos").formatted(username));
            } else if (exception.getRawStatusCode() == 403) {
                throw new RequestLimitExceededException("Request Limit has been exceeded!", (GIT_HUB_API_URL + "%s" + "/repos").formatted(username));
            }
        }

        return null;
    }

    private static GitHubApiResponse gitHubApiResponseConverter(GitHubMainDto gitHubMainDto) {
        User user = User.builder()
                .id(gitHubMainDto.getOwner().getId())
                .login(gitHubMainDto.getOwner().getLogin())
                .html_url(gitHubMainDto.getOwner().getHtml_url())
                .build();

        Repository repository = Repository.
                builder()
                .name(gitHubMainDto.getName())
                .clone_url(gitHubMainDto.getClone_url())
                .language(gitHubMainDto.getLanguage() == null ? "READ.ME" : gitHubMainDto.getLanguage())
                .html_url(gitHubMainDto.getHtml_url())
                .build();

        return GitHubApiResponse.
                builder().
                user(user).
                repository(repository)
                .build();
    }
}
