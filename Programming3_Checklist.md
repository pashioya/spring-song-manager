# Checklist for the project for programming 3

- Deadline: *Friday, January 13th 23:59*
- 3 entities according to specs in assignment 1
- Option to save data to a JSON file
- Layered architecture
- presentation - service - repository - domain → separate packages
- Interfaces for loose coupling between layers
- Logging:
  - loggers in each class - log important methods 
  - set loglevel to debug in application.properties
- presentation layer:
  - spring MVC application
    - pages:
      - to show 2 main entities in some overview (table)
      - to show details of main entities
      - to show relationships between the entities
    - possibility to add entities - delete entities44
    - add session history page to show history of visited pages in the session
    - Menu on top using fragments
    - Footer at the bottom using fragments
    - thymeleaf files in templates folder
    - static files in static folder
    - bootstrap
      - is used to work out the views
      - is loaded using webjars
      - use bootstrap to
          - make pages are responsive
          - design the tables
          - add navbar (thymeleaf fragments!)
          - add footers (thymeleaf fragments!)
    - support for second language
    - validation: 
      - use view-models for the forms, add validation there 
      - use validation messages, show them in your views
      - some minimal client side validation
- repository layer: 4 implementations 
  - using collections
  - using JdbcTemplate 
  - using ORM (JPA) 
  - using JpaRepository (Spring Data) 
    - at least two method queries
    - at least one custom query
  - database: H2 or Postgresql  
  - profiles 
    - to switch between repository implementations
- service layer:
  - 2 implementations (spring-data and other) --> use profiles or git tag  
- exception handling:
  - Provide two custom error pages  
  - Implement a custom Exception for your application (extends RuntimeException)  
  - Use a @ControllerAdvise class to handle all database exceptions  
  - Handle the custom Exception at the Controller level  
  - Be sure to log all exceptions using the logging framework  
- Add a README.MD file to the root of the project  
  - In this file:
    - Your name
    - Explanation of your domain and the relations between the entities (high level explanation)
    - Explanation of your profiles
    - What database do you use
    - Any extra information needed to be able to run the project smoothly