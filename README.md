
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
  - The profiles are switched from the application.properties files by commenting out the appropriate blocks.
  - the application.properties file is setup in blocks to ensure smooth transition between profiles.
- Database being used
  - I only use postgres for my database. however each repository implementation has its own set of data.

- Known Bugs
  - jdbc profile has all the data however entity creation not working as expected.
  - jpa profile Does not have any data however works perfectly 
  - springSata profile is not functioning as expected
    - will be repaired in due time 