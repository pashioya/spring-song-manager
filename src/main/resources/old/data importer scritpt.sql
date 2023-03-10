DELETE FROM albums
WHERE id IN (
    select id
    from (SELECT  s.id  as id, a.id as album_id, s.url, s.songtitle as song_title, s.tracknumber as track_number, s.durationms as duration_ms, s.explicit
          FROM songs s
                   left JOIN albums a ON s.albumname = a.albumname
          GROUP BY s.id, a.id, s.url, s.songtitle, s.tracknumber, s.durationms, s.explicit) s
    GROUP BY id
    HAVING COUNT(*) > 1);



SELECT distinct MIN(s.id)  as id, a.id as album_id, s.url, s.songtitle as song_title, s.tracknumber as track_number, s.durationms as duration_ms, s.explicit
FROM songs s
         JOIN albums a ON s.albumname = a.albumname
GROUP BY s.id, a.id, s.url, s.songtitle, s.tracknumber, s.durationms, s.explicit;


Select distinct id, albumname as album_title, tracksinalbum as official_track_count, albumstatus as album_status, genre, releasedate as release_date
from albums;

Select distinct id, artistname as artist_name, artistfollowers as artist_followers
from artists;




select distinct a.id
from songs s
join albums a on s.albumname = a.albumname;

select distinct s.id
from songs s
join albums a on s.albumname = a.albumname;



SELECT DISTINCT min(a.id)  AS albumid, s.id AS songid
FROM songs s
         JOIN albums a ON s.albumname = a.albumname
group by s.id;


SELECT DISTINCT s.id AS id, MIN(a.id) AS album_id, s.url, s.songtitle as song_title, s.tracknumber as track_number, s.durationms as duration_ms, s.explicit
FROM songs s
         JOIN albums a ON s.albumname = a.albumname
GROUP BY s.id;






select id, albumname, tracksinalbum, albumstatus, genre, releasedate from albums;

select id, artistname, artistfollowers from artists;


SELECT albums.id AS album_id, artists.id AS artist_id
FROM artists
         JOIN albums ON artists.artistname = albums.artistname;

SELECT ROW_NUMBER() OVER (ORDER BY artists.id) AS id, albums.id AS album_id, artists.id AS artist_id
FROM artists
         JOIN albums ON artists.artistname = albums.artistname;


