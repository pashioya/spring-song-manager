# Relevant Information

- Name: Paul Ashioya
- KdG Email: paul.ashioya@student.kdg.be
- Student Number: 0152925-53
- Course Name: Programming 5

- Domain Explanation:
    - The domain consists of 3 entities:
        - Artist
        - Album
        - Song
    - The entities are related as follows:
        - Artist has many albums (Many to Many)
        - Album has many songs (One to many)
        - Album has many Artists (Many to Many)
        - Song has one album
    - You cannot create songs without an album to be associated with
    - You cannot create an album without an artist to be associated with
    - Artists can be created on its own and with an album to be associated with

- Explanation of the profiles:
    - We have 4 profiles:
        - springData
        - lists (collections)
        - Jdbc (This is the main profile utilizing a massive database)
        - jpa
    - The profiles are switched from the application.properties file by changing the active profile
    - The application.properties file is set up in blocks to ensure a smooth transition between profiles.
    - Database being used:
        - I only use Postgres for my database. However, each repository implementation has its own set of data.

- Relevant Information:
    - Example users are:
        - User:
            - username: 2
            - password: 2
        - Admin:
            - username: 3
            - password: 2
- To run the application:
    - You need to have Docker installed
    - You need to have Docker running
    - Run the following command in the root directory of the project:

```shell
./gradlew bootRun
```

### Known Bugs

- json print methods aren't functioning as intended
- Tests aren't working as intended

# Weekly Progress

## Week 1

```HttpRequests
      ###
      # @no-cookie-jar
      GET http://localhost:8080/api/albums/1
      
      Response:
            {
              "id": 1,
              "albumName": "Abbey Road",
              "artistName": null,
              "genre": "Rock",
              "officialTrackCount": 12,
              "albumStatus": "SINGLE",
              "releaseDate": "1969-09-26"
            }
      
      ###
      # @no-cookie-jar
      DELETE http://localhost:8080/api/albums/1/delete
```

## Week 2

```HttpRequests

```

## Week 3

- All done

## Week 4

- Users Added in seeding Step
- username: john_smith
    - password: user
    - role : USER
- username: admin
    - password: admin
    - role : ADMIN
- admin page is locked out for users url : http://localhost:8080/adminPage
- only admins can create new Artists. users create albums and songs for existing artists

## Week 5

    - Profile for test is added: "test"
    - To Execute tests run the following command

## Week 6

    -

## Week9:

    - Build Instructions:
        - run 'npm install'
        - run 'npm build'
        - run 'npm start'

### Bootstrap icons added:

    - <i class="bi bi-file-music"></i>
    - added on top of the all songs Page title but under the header
    - URL : http://localhost:9000/
    - Javascript Dependencies:
        - Lodash
            - lodash's debounce function is used to prevent the search function from executing too frequently when the user types in the search bar.
            - The debounce function wraps the event listener callback and ensures that it is only called after a certain delay (300 milliseconds in this case) since the last keyup event.
            - URL: https://www.npmjs.com/package/lodash
        - Axios
            - Used for all API calls. simplifies them and makes them more readable
            - URL: https://www.npmjs.com/package/axios?activeTab=versions

### Client Side Validation

    - I used validator for client side validation.
    - The validated form is on songModule.ts
    - the page is located on allSongs.html
    - URL: https://www.npmjs.com/package/validator