package com.kdg.springprojt5.repository.jdbc;


import com.kdg.springprojt5.domain.Song;
import com.kdg.springprojt5.repository.SongRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Profile("jdbc")
@Repository
public class JDBCSongRepository implements SongRepository {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    public JDBCSongRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("songs")
                .usingGeneratedKeyColumns("id");
    }

    private Song mapSongRow(ResultSet rs, int rowid)
    throws SQLException {
        return new Song(
                rs.getLong("id"),
                rs.getLong("album_id"),
                rs.getString("url"),
                rs.getString("song_title"),
                rs.getInt("track_number"),
                rs.getDouble("duration_ms"),
                rs.getBoolean("explicit")
        );
    }

    @Override
    public Song save(Song song) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("album_id", song.getAlbumId());
        parameters.put("url", song.getUrl());
        parameters.put("song_title", song.getSongTitle());
        parameters.put("track_number", song.getTrackNumber());
        parameters.put("duration_ms", song.getDurationMS());
        parameters.put("explicit", song.isExplicit());
        jdbcInsert.execute(parameters);
        return song;
    }
    @Override
    public List<Song> getAllSongs() {
        String sql = "SELECT * FROM songs ORDER BY id DESC ";
        return jdbcTemplate.query(sql, this::mapSongRow);
    }

    @Override
    public Song getSongById(Long id) {
        String sql = "SELECT * FROM songs WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, this::mapSongRow, id);
    }

    @Override
    public List<Song> getSongsByAlbumId(Long id) {
        String sql = "SELECT * FROM songs WHERE album_id = ?";
        return jdbcTemplate.query(sql, this::mapSongRow, id);
    }


    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM songs WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}

