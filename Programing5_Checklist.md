- Week1:

- Update the README.md file with the following information in Markdown format:
  - Course name (Programming 5)
  - Your name, KdG email address, and student ID
  - Any important build or run-instructions
- Implement at least one GET endpoint and one DELETE endpoint.
- Ensure that the endpoints are called from JavaScript in a meaningful way.
- Add a .http file to your project with HTTP request messages for each endpoint and possible response status code.
- Add a heading to the README.md file called "Week 1" with the following information: 
  - Add each of the HTTP requests and responses to the markdown file.
  - Use a subtitle for each request/response combination to indicate the action taken and the response description or code. 
  - Display each request and response as preformatted text (Markdown: ``` ) 
- Ensure that AJAX principles are applied so that no page is refreshed. 
- Apply REST principles (status codes, verbs, URL patterns, etc.).
- Push your solution to the git repository of Programming 5.

- Week2:

- Implement two additional REST endpoints with different HTTP verbs (POST and either PATCH or PUT). *******************************************
- Use validation framework to add basic input checks and trigger a 400 status code if validation fails. 
- Add ModelMapper as a dependency and use it in your Web API backend code.
- Call your endpoints from JavaScript in a meaningful way, such as adding or updating records without page refresh.
- Edit your .http file to include request messages for each endpoint and possible response status codes, including 400 (handled by Spring). *******************************************
- Add content negotiation support for JSON and XML formats to your application. *******************************************
- Add two GET requests to your .http file, one for JSON and one for XML. *******************************************
- Under "Week 2" in your README.md file, add requests and responses for the two new GET requests.
- Ensure no pages are refreshed from the user's perspective and apply AJAX principles.
- Apply REST principles to your implementation, including status codes, verbs, and URL patterns.

- Week3:

- Add Spring Security to your application as a dependency.
- Configure Spring Security to enable authentication and authorization for your application.
- Create a custom login page for your application.
- Implement a means to register a new account for your application.
- Seed your application with multiple users.
- Disable CSRF protection for now.
- Ensure that passwords are hashed in the database for security.
- Implement a consistent and meaningful authentication and authorization system for your application.
- Ensure that all REST API and AJAX features work as before.
- List the users added in your seeding step in your README.md file. 
- Mention each user's login and password in the README.md file. 
- Specify which information is hidden for unauthenticated users, and include a link to this page.
- Mention each user's login and password on the login page using dummy passwords.

- Week4:

- Implement at least two different roles with different access rights within your application. These access rights should differ from unauthenticated users.
- Add users with different roles to your seeding routine. The user should be implemented as a persisted entity with at least one association to another domain entity. 
- Create meaningful features and functionality in your application so users can be associated with certain dataset records. Ensure that unauthenticated users can create no such associations or records.
- Visually, your application should hide actions/links/pages which a user cannot access. The backend must also verify if the client can take such actions.
- The REST API and Ajax features should work as before.
- At least one action of your web API should require authentication (one that modifies state).
- Protect your web application against CSRF.
- Create a consistent and meaningful implementation that makes sense to users and that can easily be verified for correctness.
- 
- README.md:
  - List the users that are added in your seeding step, including their login, password, and role.
  - Give an overview of the different roles in your application and which actions each type of user can (not) take: unauthenticated users, the first role, and the second role.
  - Specify which information you've hidden for unauthenticated users and provide a link to this page. 
  - Specify how users are related to other entities and who can update/access that information. This information may be already covered by explaining the different roles. Provide a link to this page. 

- Week5:

- Use Spring profiles to separate the seeding routine from tests. *******************************************
- Use test-specific setups to ensure consistency of tests.
- Document any Spring profiles added for this step in the README.md. 
- Document how tests should be executed from the command line in the README.md. *******************************************
- Write at least two tests for the repository layer.
- Write a test on how deletes are handled. 
- Write a test on some other Hibernate mapping (uniqueness, nullability, ...). 
- Write a test on eager/lazy loading. 
- Write tests for at least two methods of the service layer.
- Implement tests as integration tests.
- Choose interesting methods with logic to test.
- Write multiple testing methods for each method tested, covering success, failure1, and failure2. *******************************************


- Week6:

- Submission: 
  - Tag your GitLab repository for Programming 5 with highstake_1. 
  - Ensure that your README is rendered correctly on the GitLab project website. *******************************************
  - Use Spring profiles to ensure that your seeding routine doesn't interfere with your tests and document any Spring profiles you have added in the README.md file. *******************************************
  - Use test-specific setups (@BeforeAll / @BeforeEach / "Arrange") to ensure that your tests will remain consistent in the future. 
  - Document how tests should be executed from the command line in the README.md file. 
  - Ensure that your GitLab repository is pushed to the server correctly, and the latest version of your application is visible at the master or main branch. *******************************************
  - Upload a zip file containing the latest version of your application using the upload option below the GitLab project website. *******************************************
  - Press the download button from the GitLab project website to create your zip  *******************************************
- Testing:
  - Write tests for at least two methods of the REST API of the presentation layer, choosing interesting endpoints with some logic, different possible outcomes, parameters, etc. *******************************************
  - Write tests for at least two methods of the MVC part of the presentation layer, choosing interesting views with a model or parameters. *******************************************
  - Testing a single method will probably require writing several testing methods, including expect success, expect failure 1, expect failure 2, etc. *******************************************
