--users
insert into public.app_users (password, user_role, username)
values ('$2a$10$5N8E6YkQ3YBFjkdpnvl.hecWCYT0TFJh3xhn2y1BNoxObmOb05req', 1, 'john_smith');
insert into public.app_users (password, user_role, username)
values ('$2a$10$.Teby0t/NJK0Oq42njspFONqSsbo.cIXRhEmAhmVzi6EWIl0bV25u', 0, 'admin');


--artists
insert into artists (artist_followers, artist_name, user_id)
values (1231, 'The Beatles', 2);
insert into artists (artist_followers, artist_name, user_id)
values (1231, 'The Rolling Stones', 2);
insert into artists (artist_followers, artist_name, user_id)
values (1231, 'The Doors', 2);
insert into artists (artist_followers, artist_name, user_id)
values (1231, 'The Who', 2);
insert into artists (artist_followers, artist_name, user_id)
values (1231, 'The Kinks', 2);
insert into artists (artist_followers, artist_name, user_id)
values (1231, 'The Animals', 2);
insert into artists (artist_followers, artist_name, user_id)
values (1231, 'The Yardbirds', 2);
insert into artists (artist_followers, artist_name, user_id)
values (1231, 'The Jimi Hendrix Experience', 2);
insert into artists (artist_followers, artist_name, user_id)
values (1231, 'The Velvet Underground', 2);


--albums

insert into albums (album_name, album_status, genre, official_track_count, release_date, user_id)
values ('Abbey Road', 'SINGLE', 'Rock', 12, '1969-09-26', 2);
insert into albums (album_name, album_status, genre, official_track_count, release_date, user_id)
values ('Let It Bleed', 'SINGLE', 'Rock', 12, '1969-08-05', 1);
insert into albums (album_name, album_status, genre, official_track_count, release_date, user_id)
values ('The Doors', 'SINGLE', 'Rock', 12, '1967-01-04', 2);
insert into albums (album_name, album_status, genre, official_track_count, release_date, user_id)
values ('Who''s Next', 'SINGLE', 'Rock', 12, '1971-08-25', 2);
insert into albums (album_name, album_status, genre, official_track_count, release_date, user_id)
values ('Face to Face', 'SINGLE', 'Rock', 12, '1965-12-01', 1);
insert into albums (album_name, album_status, genre, official_track_count, release_date, user_id)
values ('The Animals', 'SINGLE', 'Rock', 12, '1964-12-23', 2);
insert into albums (album_name, album_status, genre, official_track_count, release_date, user_id)
values ('For Your Love', 'SINGLE', 'Rock', 12, '1965-12-01', 1);
insert into albums (album_name, album_status, genre, official_track_count, release_date, user_id)
values ('Are You Experienced', 'SINGLE', 'Rock', 12, '1967-09-19', 2);
insert into albums (album_name, album_status, genre, official_track_count, release_date, user_id)
values ('The Velvet Underground & Nico', 'SINGLE', 'Rock', 12, '1967-03-12', 1);


--albumArtists

insert into public.album_artist (album_id, artist_id)
values (1, 1);
insert into public.album_artist (album_id, artist_id)
values (2, 2);
insert into public.album_artist (album_id, artist_id)
values (3, 3);
insert into public.album_artist (album_id, artist_id)
values (4, 4);
insert into public.album_artist (album_id, artist_id)
values (5, 5);
insert into public.album_artist (album_id, artist_id)
values (6, 6);
insert into public.album_artist (album_id, artist_id)
values (7, 7);
insert into public.album_artist (album_id, artist_id)
values (8, 8);
insert into public.album_artist (album_id, artist_id)
values (9, 9);


-- Songs
insert into songs (album_id, durationms, explicit, song_title, track_number, url, user_id)
values (1, 134112, true, 'Tantrum', 2, 'https://open.spotify.com/playlist/37i9dQZF1DX4sWSpwq3LiO', 1);
insert into songs (album_id, durationms, explicit, song_title, track_number, url, user_id)
values (1, 134112, true, 'I Don''t Wanna Dance', 2, 'https://open.spotify.com/playlist/37i9dQZF1DX4sWSpwq3LiO', 1);
insert into songs (album_id, durationms, explicit, song_title, track_number, url, user_id)
values (1, 134112, true, 'You''re Not There', 2, 'https://open.spotify.com/playlist/37i9dQZF1DX4sWSpwq3LiO', 1);
insert into songs (album_id, durationms, explicit, song_title, track_number, url, user_id)
values (1, 134112, true, 'I''m in Love', 2, 'https://open.spotify.com/playlist/37i9dQZF1DX4sWSpwq3LiO', 1);
insert into songs (album_id, durationms, explicit, song_title, track_number, url, user_id)
values (2, 134112, true, 'I''m a Loser', 2, 'https://open.spotify.com/playlist/37i9dQZF1DX4sWSpwq3LiO', 2);
insert into songs (album_id, durationms, explicit, song_title, track_number, url, user_id)
values (2, 134112, true, 'Baby''s in Black', 2, 'https://open.spotify.com/playlist/37i9dQZF1DX4sWSpwq3LiO', 2);
insert into songs (album_id, durationms, explicit, song_title, track_number, url, user_id)
values (2, 134112, true, 'Rock and Roll Music', 2, 'https://open.spotify.com/playlist/37i9dQZF1DX4sWSpwq3LiO', 2);
insert into songs (album_id, durationms, explicit, song_title, track_number, url, user_id)
values (2, 134112, true, 'I''ll Follow the Sun', 2, 'https://open.spotify.com/playlist/37i9dQZF1DX4sWSpwq3LiO', 1);
insert into songs (album_id, durationms, explicit, song_title, track_number, url, user_id)
values (2, 134112, true, 'Mr. Moonlight', 2, 'https://open.spotify.com/playlist/37i9dQZF1DX4sWSpwq3LiO', 2);
insert into songs (album_id, durationms, explicit, song_title, track_number, url, user_id)
values (2, 134112, true, 'Eight Days a Week', 2, 'https://open.spotify.com/playlist/37i9dQZF1DX4sWSpwq3LiO', 2);
insert into songs (album_id, durationms, explicit, song_title, track_number, url, user_id)
values (3, 134112, true, 'Words of Love', 2, 'https://open.spotify.com/playlist/37i9dQZF1DX4sWSpwq3LiO', 1);
insert into songs (album_id, durationms, explicit, song_title, track_number, url, user_id)
values (4, 134112, true, 'Honey Pie', 2, 'https://open.spotify.com/playlist/37i9dQZF1DX4sWSpwq3LiO', 2);
insert into songs (album_id, durationms, explicit, song_title, track_number, url, user_id)
values (5, 134112, true, 'Every Little Thing', 2, 'https://open.spotify.com/playlist/37i9dQZF1DX4sWSpwq3LiO', 1);
insert into songs (album_id, durationms, explicit, song_title, track_number, url, user_id)
values (6, 134112, true, 'I''ve Just Seen a Face', 2, 'https://open.spotify.com/playlist/37i9dQZF1DX4sWSpwq3LiO', 2);
insert into songs (album_id, durationms, explicit, song_title, track_number, url, user_id)
values (7, 134112, true, 'Wait', 2, 'https://open.spotify.com/playlist/37i9dQZF1DX4sWSpwq3LiO', 1);
insert into songs (album_id, durationms, explicit, song_title, track_number, url, user_id)
values (8, 134112, true, 'If I Needed Someone', 2, 'https://open.spotify.com/playlist/37i9dQZF1DX4sWSpwq3LiO', 2);
insert into songs (album_id, durationms, explicit, song_title, track_number, url, user_id)
values (8, 134112, true, 'What You''re Doing', 2, 'https://open.spotify.com/playlist/37i9dQZF1DX4sWSpwq3LiO', 2);
insert into songs (album_id, durationms, explicit, song_title, track_number, url, user_id)
values (8, 134112, true, 'And Your Bird Can Sing', 2, 'https://open.spotify.com/playlist/37i9dQZF1DX4sWSpwq3LiO', 2);
insert into songs (album_id, durationms, explicit, song_title, track_number, url, user_id)
values (9, 134112, true, 'Here, There and Everywhere', 2, 'https://open.spotify.com/playlist/37i9dQZF1DX4sWSpwq3LiO',
        1);

