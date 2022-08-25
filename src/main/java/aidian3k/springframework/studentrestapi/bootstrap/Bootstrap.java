package aidian3k.springframework.studentrestapi.bootstrap;

import aidian3k.springframework.studentrestapi.webclient.GitHubWebClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {
    GitHubWebClient gitHubWebClient;

    public Bootstrap(GitHubWebClient gitHubWebClient) {
        this.gitHubWebClient = gitHubWebClient;
    }

    @Override
    public void run(String... args) throws Exception {
    }
}
