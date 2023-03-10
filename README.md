
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

- Known Bugs
  - jdbc profile has all the data however entity creation not working as expected.
  - jpa profile Does not have any data however works perfectly
  - spring data profile does not have any data however works perfectly
  - Album and artist previews are temporarily disabled due to transition to ajax

  - Week 1
    - Retrieve Single Artsits Albums
        - #To trigger a 200 (OK) response status code:
        GET http://localhost:8080/api/artist/1/albums
       - #To trigger a 404 (Not Found) response status code:
        GET http://localhost:8080/api/artist/1000/albums
    - Retrieve Single Album Songs
        - #To trigger a 200 (OK) response status code:
        GET http://localhost:8080/api/album/1/songs
       - #To trigger a 404 (Not Found) response status code:
        GET http://localhost:8080/api/album/1000/songs
    - Retrieve Single Artist Songs
        - #To trigger a 200 (OK) response status code:
        GET http://localhost:8080/api/artist/1/songs
       - #To trigger a 404 (Not Found) response status code:
        GET http://localhost:8080/api/artist/1000/songs
    - Retrieve Single Song
        - #To trigger a 200 (OK) response status code:
        GET http://localhost:8080/api/song/1
       - #To trigger a 404 (Not Found) response status code:
        GET http://localhost:8080/api/song/1000
    - Retrieve Single Album
        - #To trigger a 200 (OK) response status code:
        GET http://localhost:8080/api/album/1
       - #To trigger a 404 (Not Found) response status code:
        GET http://localhost:8080/api/album/1000
    - Retrieve Single Artist
        - #To trigger a 200 (OK) response status code:
        GET http://localhost:8080/api/artist/1
       - #To trigger a 404 (Not Found) response status code:
        GET http://localhost:8080/api/artist/1000
    - Retrieve All Songs
        - #To trigger a 200 (OK) response status code:
        GET http://localhost:8080/api/songs
    - Retrieve All Albums
        - #To trigger a 200 (OK) response status code:
        GET http://localhost:8080/api/albums
    - Retrieve All Artists
        - #To trigger a 200 (OK) response status code:
        GET http://localhost:8080/api/artists
    - Create a Song
        - #To trigger a 201 (Created) response status code:
        POST http://localhost:8080/api/song
        - #To trigger a 400 (Bad Request) response status code:
        POST http://localhost:8080/api/song
        - #To trigger a 409 (Conflict) response status code:
        POST http://localhost:8080/api/song
    - Create an Album
        - #To trigger a 201 (Created) response status code:
        POST http://localhost:8080/api/album
        - #To trigger a 400 (Bad Request) response status code:
        POST http://localhost:8080/api/album
        - #To trigger a 409 (Conflict) response status code:
        POST http://localhost:8080/api/album
    - Create an Artist
        - #To trigger a 201 (Created) response status code:
        POST http://localhost:8080/api/artist
        - #To trigger a 400 (Bad Request) response status code:
        POST http://localhost:8080/api/artist
        - #To trigger a 409 (Conflict) response status code:
        POST http://localhost:8080/api/artist
    - Update a Song
        - #To trigger a 200 (OK) response status code:
        PUT http://localhost:8080/api/song/1
        - #To trigger a 400 (Bad Request) response status code:
        PUT http://localhost:8080/api/song/1
        - #To trigger a 404 (Not Found) response status code:
        PUT http://localhost:8080/api/song/1000
    - Update an Album
        - #To trigger a 200 (OK) response status code:
        PUT http://localhost:8080/api/album/1
        - #To trigger a 400 (Bad Request) response status code:
        PUT http://localhost:8080/api/album/1
        - #To trigger a 404 (Not Found) response status code:
        PUT http://localhost:8080/api/album/1000
    - Update an Artist
        - #To trigger a 200 (OK) response status code:
        PUT http://localhost:8080/api/artist/1
        - #To trigger a 400 (Bad Request) response status code:
        PUT http://localhost:8080/api/artist/1
        - #To trigger a 404 (Not Found) response status code:
        PUT http://localhost:8080/api/artist/1000
    - Delete a Song
        - #To trigger a 204 (No Content) response status code:
        DELETE http://localhost:8080/api/song/1
        - #To trigger a 404 (Not Found) response status code:
        DELETE http://localhost:8080/api/song/1000
    - Delete an Album
        - #To trigger a 204 (No Content) response status code:
        DELETE http://localhost:8080/api/album/1
        - #To trigger a 404 (Not Found) response status code:
        DELETE http://localhost:8080/api/album/1000
    - Delete an Artist
        - #To trigger a 204 (No Content) response status code:
        DELETE http://localhost:8080/api/artist/1
        - #To trigger a 404 (Not Found) response status code:
        DELETE http://localhost:8080/api/artist/1000
