# GitHubRestApi

# About a project
My goal was to create simple restApi, which would make it easier to use gitHubApi. Response from orginal gitHubApi is quite long and informative. 
That is why, I wanted to simplify the response just to information about user repositories.


# Technology Stack
* JDK 17.0.1 (Language Level Java 17),
* Apache Maven 4.0.0,
* JUnit 5.8.2,
* Lombok 1.18.24,
* Spring Boot 2.7.1,
* GitHubApi

# RestApi Docs

* Here I present simple response of the api, which consits of every user repository on GitHub and its information.

![Zrzut ekranu 2022-08-25 o 20 53 53](https://user-images.githubusercontent.com/93425971/186745737-ecc3a132-a12a-49ad-8e40-8be943a1a5fc.png)

# How to run project? 

* Simply you have to run the project on any IDE. I stronly recommend running it on IntelliJ IDEA, but other should also work. 
By default server-port set in application is 8080, therefore after running the application mapping should be done like that: localhost:8080/{yourMapping}. 

* Application is based on one simple mapping /statistics?=user{wantedUser}. {WantedUser} here is a parameter, which we should pass to get the api response
of wanted user. By default wantedUser = aidian3k, therefore after not adding any parameter in the mapping, aidian3k gitHubProfile information will appear. 

* It is fair to add, that the application does not support token authorization. Without token authorization, gitHubApi provides user with only 60 responses
every hour. I did not implement any possibility to add such token authorization. After reaching 60 responses, the proper api exception will be appeared.

* Application also supports caching and swagger. Caching is a mechanism, which saves the response for some time in the caching memory. In order to see more 
information about the api, go to: localhost:8080/swagger-ui.html after running the aplication.

* Sample api response after localhost:8080/statistics:
![Zrzut ekranu 2022-08-26 o 13 51 09](https://user-images.githubusercontent.com/93425971/186897346-891b0064-7f66-4050-a777-199761a6f71b.png)

