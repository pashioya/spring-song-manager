drop table if exists artists cascade;
CREATE TABLE artists(
                        id SERIAL PRIMARY KEY ,
                        artist_name VARCHAR(255) NOT NULL,
                        artist_followers INT NOT NULL
);

drop table if exists albums cascade ;
CREATE TABLE albums(
                        id SERIAL PRIMARY KEY ,
                        album_title VARCHAR(255) NOT NULL,
                        official_track_count INT NOT NULL,
                        album_status VARCHAR(255) NOT NULL,
                        genre VARCHAR(255) NOT NULL,
                        release_date VARCHAR(255) NOT NULL
);

drop table if exists songs cascade;
CREATE TABLE songs(
                        id SERIAL PRIMARY KEY ,
                        album_id BIGINT NOT NULL,
                        url VARCHAR(255),
                        song_title VARCHAR(255) NOT NULL,
                        track_number INT NOT NULL,
                        duration_ms INT NOT NULL,
                        explicit BOOLEAN NOT NULL,
                        FOREIGN KEY (album_id) REFERENCES albums (id)
);

drop table if exists album_artist cascade ;
CREATE TABLE album_artist (
                        album_id BIGINT NOT NULL,
                        artist_id BIGINT NOT NULL,
                        PRIMARY KEY (album_id, artist_id),
                        FOREIGN KEY (album_id) REFERENCES albums (id),
                        FOREIGN KEY (artist_id) REFERENCES artists (id)
);
