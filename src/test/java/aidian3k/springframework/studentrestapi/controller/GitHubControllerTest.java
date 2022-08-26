package aidian3k.springframework.studentrestapi.controller;

import aidian3k.springframework.studentrestapi.exception.UserNotFoundException;
import aidian3k.springframework.studentrestapi.model.GitHubApiResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GitHubControllerTest {
    @InjectMocks
    private GitHubController gitHubController;

    @Autowired
    private TestRestTemplate testRestTemplate;
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(gitHubController).build();
    }

    @Test
    void getDefaultMapping() throws Exception {
        //given
        String responseString = "Application is working!";

        //then
        mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(responseString)));
    }

    @Test
    void should_returnProperResponse_when_RequestUserIsNotGiven() {
        //given
        String requestedPath = "http://localhost:8080/statistics";

        //when
        String expectedUserName = "aidian3k";
        int expectedNumberOfRepos = 13;

        //then
        ResponseEntity<GitHubApiResponse> response = testRestTemplate.getForEntity(requestedPath, GitHubApiResponse.class);

        GitHubApiResponse apiResponse = response.getBody();

        Assertions.assertNotNull(apiResponse);
        Assertions.assertEquals(apiResponse.getUser().getLogin(), expectedUserName);
        Assertions.assertEquals(apiResponse.getRepositories().length, expectedNumberOfRepos);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void should_returnProperResponse_when_RequestUserIsGiven() {
        //given
        String requestedPath = "http://localhost:8080/statistics?username={name}";

        //when
        String expectedUserName = "octokit";
        int expectedNumberOfRepos = 30;
        int expectedResponse = HttpStatus.OK.value();

        //then
        ResponseEntity<GitHubApiResponse> responseEntity = testRestTemplate
                .getForEntity(requestedPath, GitHubApiResponse.class, expectedUserName);

        GitHubApiResponse apiResponse = responseEntity.getBody();

        Assertions.assertNotNull(apiResponse);
        Assertions.assertEquals(expectedResponse, responseEntity.getStatusCode().value());
        Assertions.assertEquals(expectedUserName, apiResponse.getUser().getLogin());
        Assertions.assertEquals(expectedNumberOfRepos, apiResponse.getRepositories().length);
    }

    @Test
    void should_throwAnException_when_RequestedUserDoesNotExist() {
        //when
        String requestedPath = "http://localhost:8080/statistics?username={name}";
        String userName = "aidian3";

        //then
        ResponseEntity<UserNotFoundException> responseEntity = testRestTemplate
                .getForEntity(requestedPath, UserNotFoundException.class, userName);

        UserNotFoundException userNotFoundException = responseEntity.getBody();

        Assertions.assertNotNull(userNotFoundException);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}