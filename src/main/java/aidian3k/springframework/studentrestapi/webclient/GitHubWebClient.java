package aidian3k.springframework.studentrestapi.webclient;

import aidian3k.springframework.studentrestapi.exception.RequestLimitExceededException;
import aidian3k.springframework.studentrestapi.exception.UserNotFoundException;
import aidian3k.springframework.studentrestapi.model.GitHubApiResponse;
import aidian3k.springframework.studentrestapi.model.Repository;
import aidian3k.springframework.studentrestapi.model.User;
import aidian3k.springframework.studentrestapi.webclient.webclientdto.GitHubMainDto;
import aidian3k.springframework.studentrestapi.webclient.webclientdto.GitHubUserDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

@Component
public class GitHubWebClient {
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String GIT_HUB_API_URL = "https://api.github.com/users/";

    public GitHubApiResponse getUserStats(String username) {
        GitHubMainDto[] gitHubMainDto;

        try {
            gitHubMainDto = restTemplate.
                    getForObject(GIT_HUB_API_URL + "{username}" + "/repos", GitHubMainDto[].class, username);

            assert gitHubMainDto != null;

            User user = mapSingleUser(gitHubMainDto[0].getOwner());
            Repository [] gitHubRepositories = new Repository[gitHubMainDto.length];

            if(user == null) {
                throw new UserNotFoundException("User with username = %s has not been found!".formatted(username), GIT_HUB_API_URL + username + "/repos");
            }

            for(int i = 0 ; i < gitHubMainDto.length ; ++i) {
                GitHubMainDto response = gitHubMainDto[i];
                gitHubRepositories[i] = mapSingleRepository(response);
            }

            return GitHubApiResponse.builder()
                    .user(user)
                    .repositories(gitHubRepositories)
                    .build();


        } catch (RestClientResponseException exception) {
            if (exception.getRawStatusCode() == 404) {
                throw new UserNotFoundException("The username " + username + "has not been found!", (GIT_HUB_API_URL + "%s" + "/repos").formatted(username));
            } else if (exception.getRawStatusCode() == 403) {
                throw new RequestLimitExceededException("Request Limit has been exceeded!", (GIT_HUB_API_URL + "%s" + "/repos").formatted(username));
            }
        }

        return null;
    }

    private static Repository mapSingleRepository(GitHubMainDto gitHubMainDto) {
        return Repository.builder()
                .name(gitHubMainDto.getName())
                .clone_url(gitHubMainDto.getClone_url())
                .html_url(gitHubMainDto.getHtml_url())
                .language(gitHubMainDto.getLanguage() == null ? "READ.ME" : gitHubMainDto.getLanguage())
                .clone_url(gitHubMainDto.getClone_url())
                .description(gitHubMainDto.getDescription())
                .build();
    }

    private static User mapSingleUser(GitHubUserDto gitHubUserDto) {
        return User.builder()
                .id(gitHubUserDto.getId())
                .login(gitHubUserDto.getLogin())
                .html_url(gitHubUserDto.getHtml_url())
                .build();
    }

}
