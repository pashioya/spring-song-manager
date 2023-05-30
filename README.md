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

- You might get a "No such object: spring_postgres_container" error
    - simply rerun bootRun
- If the errors don't stop
- delete
    - the container 'spring_postgres_container'
    - the image 'spring_postgres'
- from docker and rerun the application

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

``` HttpRequests
#create an artist
POST http://localhost:8080/api/artists
//@no-cookie-jar
Accept: application/json
Content-Type: application/json
Cookie: JSESSIONID=75400D8A8763ED4F6B3220A14694DA23
X-Csrf-Token: uF8Rtj43_P6eBCcfoApcjNYHOqJ4_tbRV_sWMW4jKTsFW8x8iD0p1V0Ez8uzMR4mlydo6bVhF5sdnLD8NsovUl4QTQg9Y_9M

{
  "artistName": "DongDing",
  "artistFollowers": 1
}

###Response:
//Response code: 201; Time: 8ms (8 ms); Content length: 70 bytes (70 B)
#ResponseBody:
//{
//  "id": 1,
//  "name": "The Beatles",
//  "artistFollowers": 1231.0
//}

//Response code: 404; Time: 8ms (8 ms); Content length: 70 bytes (70 B)

//Response code: 500; Time: 8ms (8 ms); Content length: 70 bytes (70 B)

###
#get an artist
GET http://localhost:8080/api/artists/1
//@no-cookie-jar
Accept: application/json
Content-Type: application/json
Cookie: JSESSIONID=75400D8A8763ED4F6B3220A14694DA23

//Response:

//{
//  "id": 1,
//  "name": "The Beatles",
//  "artistFollowers": 1231.0
//}


###
#delete an artist
# @no-cookie-jar
DELETE http://localhost:8080/api/artists/1/
Accept: application/json
Content-Type: application/json
Cookie: JSESSIONID=75400D8A8763ED4F6B3220A14694DA23

//Response:
//Response code: 404; Time: 3ms (3 ms); Content length: 0 bytes (0 B)
//Response code: 200; Time: 3ms (3 ms); Content length: 0 bytes (0 B)
//Response code: 500; Time: 3ms (3 ms); Content length: 0 bytes (0 B)

###

#Get All Artists
# @no-cookie-jar
GET http://localhost:8080/api/artists
Accept: application/json
Content-Type: application/json
Cookie: JSESSIONID=75400D8A8763ED4F6B3220A14694DA23

#resposnse
#[
#  {
#    "id": 1,
#    "name": "The Beatles",
#    "artistFollowers": 1231.0
#  },
#  {
#    "id": 2,
#    "name": "The Rolling Stones",
#    "artistFollowers": 1231.0
#  },
#  {
#    "id": 3,
#    "name": "The Doors",
#    "artistFollowers": 1231.0
#  },
#....
//Response code: 200; Time: 3ms (3 ms); Content length: 0 bytes (0 B)
//Response code: 204; Time: 3ms (3 ms); Content length: 0 bytes (0 B)

###
#get a album
# @no-cookie-jar
GET http://localhost:8080/api/albums/1
Accept: application/json
Content-Type: application/json
Cookie: JSESSIONID=75400D8A8763ED4F6B3220A14694DA23

//Response:
#{
#  "id": 1,
#  "albumName": "Abbey Road",
#  "artistName": null,
#  "genre": "Rock",
#  "officialTrackCount": 12,
#  "albumStatus": "SINGLE",
#  "releaseDate": "1969-09-26"
#}
#Response file saved.
#> 2023-03-26T215223.200.json
#
//Response code: 200; Time: 8ms (8 ms); Content length: 140 bytes (140 B)

###
#delete a album
# @no-cookie-jar
DELETE http://localhost:8080/api/albums/1
Accept: application/json
Content-Type: application/json
Cookie: JSESSIONID=75400D8A8763ED4F6B3220A14694DA23

//Response:
//Response code: 200; Time: 3ms (3 ms); Content length: 0 bytes (0 B)
//Response code: 404; Time: 3ms (3 ms); Content length: 0 bytes (0 B)
//Response code: 401; Time: 3ms (3 ms); Content length: 0 bytes (0 B)
//Response code: 500; Time: 3ms (3 ms); Content length: 0 bytes (0 B)

###
#Get All Albums
# @no-cookie-jar
GET http://localhost:8080/api/albums
Accept: application/json
Content-Type: application/json
Cookie: JSESSIONID=75400D8A8763ED4F6B3220A14694DA23

#resposnse
#[
#  {
#    "id": 1,
#    "albumName": "Abbey Road",
#    "artistName": "The Beatles",
#    "genre": "Rock",
#    "officialTrackCount": 12,
#    "albumStatus": "SINGLE",
#    "releaseDate": "1969-09-26"
#  },
#  {
#    "id": 2,
#    "albumName": "Let It Bleed",
#    "artistName": "The Rolling Stones",
#    "genre": "Rock",
#    "officialTrackCount": 12,
#    "albumStatus": "SINGLE",
#    "releaseDate": "1969-08-05"
#  },
#.....
//Response code: 200; Time: 24ms (24 ms); Content length: 1414 bytes (1.41 kB)
//Response code: 404; Time: 3ms (3 ms); Content length: 0 bytes (0 B)


###
# get a artists albums
# @no-cookie-jar
GET http://localhost:8080/api/artists/1
Accept: application/json
Content-Type: application/json
Cookie: JSESSIONID=75400D8A8763ED4F6B3220A14694DA23

#resposnse
#[
#  {
#    "id": 1,
#    "albumName": "Abbey Road",
#    "artistName": "The Beatles",
#    "genre": "Rock",
#    "officialTrackCount": 12,
#    "albumStatus": "SINGLE",
#    "releaseDate": "1969-09-26"
#  }
#]
//Response code: 200; Time: 8ms (8 ms); Content length: 140 bytes (140 B)
//Response code: 401; Time: 3ms (3 ms); Content length: 0 bytes (0 B)
//Response code: 401; Time: 3ms (3 ms); Content length: 0 bytes (0 B)


###
#get a albums songs
# @no-cookie-jar
GET http://localhost:8080/api/songs/album/1
Accept: application/json
Content-Type: application/json
Cookie: JSESSIONID=75400D8A8763ED4F6B3220A14694DA23

#resposnse
#[
#  {
#    "id": 1,
#    "songTitle": "Tantrum",
#    "trackNumber": 2,
#    "durationMs": 134112.0,
#    "explicit": true,
#    "url": "https://open.spotify.com/playlist/37i9dQZF1DX4sWSpwq3LiO"
#  },
#  {
#    "id": 2,
#    "songTitle": "I Don't Wanna Dance",
#    "trackNumber": 2,
#    "durationMs": 134112.0,
#    "explicit": true,
#    "url": "https://open.spotify.com/playlist/37i9dQZF1DX4sWSpwq3LiO"
#  },
//.....
//Response code: 200; Time: 8ms (8 ms); Content length: 140 bytes (140 B)
//Response code: 400; Time: 3ms (3 ms); Content length: 0 bytes (0 B)
###
#get a song
# @no-cookie-jar
GET http://localhost:8080/api/songs/1
Accept: application/json
Content-Type: application/json
Cookie: JSESSIONID=75400D8A8763ED4F6B3220A14694DA23

#resposnse
#{
#  "id": 1,
#  "songTitle": "Tantrum",
#  "trackNumber": 2,
#  "durationMs": 134112.0,
#  "explicit": true,
#  "url": "https://open.spotify.com/playlist/37i9dQZF1DX4sWSpwq3LiO"
#}
//Response code: 200; Time: 8ms (8 ms); Content length: 140 bytes (140 B)

###
#get all songs
# @no-cookie-jar
GET http://localhost:8080/api/songs
Accept: application/json
Content-Type: application/json
Cookie: JSESSIONID=75400D8A8763ED4F6B3220A14694DA23

#resposnse
#[
#  {
#    "id": 1,
#    "songTitle": "Tantrum",
#    "trackNumber": 2,
#    "durationMs": 134112.0,
#    "explicit": true,
#    "url": "https://open.spotify.com/playlist/37i9dQZF1DX4sWSpwq3LiO"
#  },
#  {
#    "id": 2,
#    "songTitle": "I Don't Wanna Dance",
#    "trackNumber": 2,
#    "durationMs": 134112.0,
#    "explicit": true,
#    "url": "https://open.spotify.com/playlist/37i9dQZF1DX4sWSpwq3LiO"
#  },
#....
//Response code: 200; Time: 8ms (8 ms); Content length: 140 bytes (140 B)

###
#get all artists for an album
# @no-cookie-jar
GET http://localhost:8080/api/artists/album/1
Accept: application/json
Content-Type: application/json
Cookie: JSESSIONID=75400D8A8763ED4F6B3220A14694DA23

#resposnse
#[
#  {
#    "id": 1,
#    "name": "The Beatles",
#    "artistFollowers": 1231.0
#  }
#]
//Response code: 200; Time: 8ms (8 ms); Content length: 140 bytes (140 B)



###
#create a album for an artist
# @no-cookie-jar
POST http://localhost:8080/api/albums/artist/1
Accept: application/json
Content-Type: application/json
Cookie: JSESSIONID=75400D8A8763ED4F6B3220A14694DA23
X-CSRF-TOKEN: 8d8b076f-2ac7-4929-9427-0acf24d1e928

{
  "albumName": "The Dark Side of the Moon",
  "trackCount": 1,
  "albumStatus": "SINGLE",
  "genre": "Progressive Rock",
  "releaseDate": "1973-03-01"
}

### response
//Response code: 401; Time: 4ms (4 ms); Content length: 0 bytes (0 B)
//Response code: 400; Time: 4ms (4 ms); Content length: 0 bytes (0 B)
//Response code: 201; Time: 4ms (4 ms); Content length: 0 bytes (0 B)


###
#create a song for an album
# @no-cookie-jar
POST http://localhost:8080/api/songs/album/1
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

### response
//Response code: 401; Time: 4ms (4 ms); Content length: 0 bytes (0 B)
//Response code: 400; Time: 4ms (4 ms); Content length: 0 bytes (0 B)
//Response code: 201; Time: 4ms (4 ms); Content length: 0 bytes (0 B)

###
#delete a song
# @no-cookie-jar
DELETE http://localhost:8080/api/songs/1
Accept: application/json
Content-Type: application/json
Cookie: JSESSIONID=B282F54D6CEB53A022232BB1799DE503

### response
//Response code: 401; Time: 4ms (4 ms); Content length: 0 bytes (0 B)
//Response code: 200; Time: 4ms (4 ms); Content length: 0 bytes (0 B)
//Response code: 404; Time: 4ms (4 ms); Content length: 0 bytes (0 B)



GET http://localhost:8080/login

###
POST http://localhost:8080/register

###
GET http://localhost:8080/api/users/1
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
- admin page is locked out for users url : http://localhost:8080/allUsers
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