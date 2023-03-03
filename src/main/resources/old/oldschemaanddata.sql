

drop table if exists songs;
CREATE TABLE songs(
                      url VARCHAR(255),
                      songTitle VARCHAR(255) NOT NULL,
                      artistName VARCHAR(255) NOT NULL,
                      albumName VARCHAR(255) NOT NULL,
                      trackNumber INT NOT NULL,
                      durationMS INT NOT NULL,
                      explicit BOOLEAN NOT NULL
);
ALTER TABLE public.songs ADD COLUMN if not exists id bigserial PRIMARY KEY;


drop table if exists albums cascade ;
CREATE TABLE albums(

                       artistName VARCHAR(255) NOT NULL,
                       albumName VARCHAR(255) NOT NULL,
                       tracksInAlbum INT NOT NULL,
                       albumStatus VARCHAR(255) NOT NULL,
                       genre VARCHAR(255) NOT NULL,
                       releaseDate VARCHAR(255) NOT NULL
);
ALTER TABLE public.albums ADD COLUMN if not exists id bigserial PRIMARY KEY;


drop table if exists artists cascade ;
CREATE TABLE artists(

                        artistName VARCHAR(255) NOT NULL,
                        artistFollowers INT NOT NULL
);
ALTER TABLE public.artists ADD COLUMN if not exists id bigserial PRIMARY KEY;

/* drop all tables, types and sequences

DO $$ DECLARE
    r RECORD;
BEGIN
    FOR r IN (SELECT tablename FROM pg_tables WHERE schemaname = current_schema()) LOOP
        EXECUTE 'DROP TABLE IF EXISTS ' || quote_ident(r.tablename) || ' CASCADE';
    END LOOP;
END $$;

DO $$ DECLARE
    r RECORD;
BEGIN
    FOR r IN (SELECT relname FROM pg_class where relkind = 'S') LOOP
        EXECUTE 'DROP SEQUENCE IF EXISTS ' || quote_ident(r.relname) || ' CASCADE';
    END LOOP;
END $$;

DELETE FROM pg_type WHERE typnamespace=17548
*/


drop table if exists temp;
create table temp (data json);
copy temp (data) from
    'S:\Development\kdg\Programming\3\Projects\spring-proj-t3\src\main\resources\json\songEdit.json'
;
insert into songs
select (data->>'url'),
       (data->>'songTitle'),
       (data->>'artistName'),
       (data->>'albumName'),
       (data->>'trackNumber')::int,
       (data->>'durationMS')::double precision,
       (data->>'explicit')::boolean
from temp;
drop table temp;
ALTER TABLE public.songs ADD COLUMN if not exists id bigserial PRIMARY KEY;

create table if not exists temp (data json);
copy temp (data) from
    'S:\Development\kdg\Programming\3\Projects\spring-proj-t3\src\main\resources\json\albumsEdit.json'
;
insert into albums
select (data->>'artistName'),
       (data->>'albumName'),
       (data->>'tracksInAlbum')::int,
       (data->>'albumStatus'),
       (data->>'genre'),
       (data->>'releaseDate')
from temp;
drop table temp;
ALTER TABLE public.albums ADD COLUMN if not exists id bigserial PRIMARY KEY;

create table if not exists temp (data json);
copy temp (data) from
    'S:\Development\kdg\Programming\3\Projects\spring-proj-t3\src\main\resources\json\artistsEdit.json'
;
insert into artists
select (data->>'artistName'),
       (data->>'artistFollowers')::double precision
from temp;
drop table temp;
ALTER TABLE public.artists ADD COLUMN if not exists id bigserial PRIMARY KEY;

