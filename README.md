
### Relevant Information
- Name - Paul Ashioya
- KdG Email - paul.ashioya@student.kdg.be
- Student Number - 
- Course Name - Programming 5
- 
- 

- Domain Explanation
    - The domain consists of 3 entities 
      - Artist
      - Album
      - Song
    - The entities are related as follows
    - Artist has many albums (Many to Many)
    - Album has many songs (One to many)
    - Album has many Arists (Many to Many)
    - Song has one album
    - You cannot create songs without a album to be associated with
    - you cannot create an album without an artist to be associated with
    - Artists can be created on its own, and with a album to be associated with
- Explanation of the profiles
  - We have 4 profiles
    - springData
    - lists (collections)
    - Jdbc  (This is the main profile utilizing a massive database)
    - jpa
  - The profiles are switched from the application.properties file by changing the active profile
  - the application.properties file is setup in blocks to ensure smooth transition between profiles.
  - Database being used
    - I only use postgres for my database. however each repository implementation has its own set of data.

- Relevant Information
  - Example users are
    - User: 
      - username: 2 
      - password: 2
    - Admin:
      - username: 3
      - password: 2


- Known Bugs
  - album preview is misbehaving 

- Week 1
```HttpRequests
      ###
      # @no-cookie-jar
      GET http://localhost:8080/api/album/1
      
      ###
      # @no-cookie-jar
      DELETE http://localhost:8080/api/album/1/delete
```
- Week 2
```HttpRequests
      POST http://localhost:8080/api/artist/create
      //@no-cookie-jar
      Accept: application/json
      Content-Type: application/json
      Cookie: JSESSIONID=75400D8A8763ED4F6B3220A14694DA23
      
          {
          "artistName" : "DongDing",
          "artistFollowers" : 1
          }
      
      ###
      GET http://localhost:8080/api/artist/1
      //@no-cookie-jar
      Accept: application/json
      Content-Type: application/json
      Cookie: JSESSIONID=75400D8A8763ED4F6B3220A14694DA23
      
      ###
      # @no-cookie-jar
      DELETE http://localhost:8080/api/artist/1/delete
      
      ###
      # @no-cookie-jar
      GET http://localhost:8080/api/artists
      
      ###
      # @no-cookie-jar
      GET http://localhost:8080/api/album/1
      
      ###
      # @no-cookie-jar
      DELETE http://localhost:8080/api/album/1/delete
      
      ###
      # @no-cookie-jar
      GET http://localhost:8080/api/albums
      ###
      # @no-cookie-jar
      GET http://localhost:8080/api/artist/1/albums
      ###
      # @no-cookie-jar
      GET http://localhost:8080/api/album/1/songs
      
      ###
      # @no-cookie-jar
      GET http://localhost:8080/api/song/1
      ###
      # @no-cookie-jar
      GET http://localhost:8080/api/songs
      
      ###
      # @no-cookie-jar
      GET http://localhost:8080/users/2
      ###
      # @no-cookie-jar
      GET http://localhost:8080/api/album/1/artists
      ###
      # @no-cookie-jar
      POST http://localhost:8080/api/artist/1/album/create
      Accept: application/json
      Content-Type: application/json
      Cookie: JSESSIONID=75400D8A8763ED4F6B3220A14694DA23
      
      {
        "albumName": "The Dark Side of the Moon",
        "trackCount": 1,
        "albumStatus": "SINGLE",
        "genre": "Progressive Rock",
        "releaseDate": "1973-03-01"
      }
      ###
      # @no-cookie-jar
      POST http://localhost:8080/api/album/1/song/create
      Accept: application/json
      Content-Type: application/json
      Cookie: JSESSIONID=75400D8A8763ED4F6B3220A14694DA23
      
      {
        "url": "https://open.spotify.com/track/2YFtpiy2WoAQVQbM1SIwES",
        "trackCount": 1,
        "albumStatus": "SINGLE",
        "genre": "Progressive Rock",
        "releaseDate": "1973-03-01"
      }
      ###
      # @no-cookie-jar
      DELETE http://localhost:8080/api/song/1/delete
      Accept: application/json
      Content-Type: application/json
      Cookie: JSESSIONID=75400D8A8763ED4F6B3220A14694DA23
```
- Week 3
  - 
- Week 4
  -  Users Added in seeding Step
  - username: john_smith 
    - password: user 
    - role : USER
  - username: admin 
    - password: admin  
    - role : ADMIN
  - admin page is locked out for users url : http://localhost:8080/adminPage
- Week 5
  - 
- Week 6

- Week 7
  - 