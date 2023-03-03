package com.kdg.springprojt5.repository.jdbc;


import com.kdg.springprojt5.domain.Album;
import com.kdg.springprojt5.domain.AlbumArtist;
import com.kdg.springprojt5.domain.StatusEnum;
import com.kdg.springprojt5.repository.AlbumRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Profile("jdbc")
@Repository
public class JDBCAlbumRepository implements AlbumRepository {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert inserter;

    public JDBCAlbumRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.inserter = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("albums")
                .usingGeneratedKeyColumns("id");
    }

    private Album mapAlbumRow(ResultSet rs, long rowid)
    throws SQLException {
        try{
            String dateString = rs.getString("release_date");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date = LocalDate.parse(dateString, formatter);

            return new Album(
                    rs.getLong("id"),
                    rs.getString("album_title"),
                    rs.getInt("official_track_count"),
                    StatusEnum.valueOf(rs.getString("album_status").toUpperCase()),
                    rs.getString("genre"),
                    date
            );

        } catch (SQLException e) {
            throw e;
        }
    }
    @Override
    public Album save(Album album) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("album_title", album.getAlbumName());
        parameters.put("official_track_count", album.getOfficialTrackCount());
        parameters.put("album_status", album.getAlbumStatus().toString());
        parameters.put("genre", album.getGenre());
        parameters.put("release_date", album.getReleaseDate());
        long id = inserter.executeAndReturnKey(parameters).longValue();
        album.setId(id);
        return album;
    }

    @Override
    public List<Album> getAllAlbums() {
        String sql = "SELECT * FROM albums ORDER BY id DESC";
        return jdbcTemplate.query(sql, this::mapAlbumRow);
    }

    @Override
    public Album getAlbumById(long id) {
        String sql = "SELECT * FROM albums WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, this::mapAlbumRow, id);
    }

    @Override
    public List<Album> getAlbumsByArtistId(long id) {
        String sql = "SELECT * FROM albums WHERE id IN (SELECT album_id FROM album_artist WHERE artist_id = ?)";
        return jdbcTemplate.query(sql, this::mapAlbumRow, id);
    }

    @Override
    public void updateAlbumArtist(AlbumArtist albumArtist) {
        String sql = "INSERT INTO album_artist (album_id, artist_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, albumArtist.getAlbum().getId(),albumArtist.getArtist().getId());
    }

    @Override
    public void deleteAlbumArtist(AlbumArtist albumArtist) {
        String sql = "DELETE FROM album_artist WHERE album_id = ? AND artist_id = ?";
        jdbcTemplate.update(sql, albumArtist.getAlbum().getId(),albumArtist.getArtist().getId());
    }

    @Override
    public void deleteById(long id) {
        String sql = "DELETE FROM albums WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

}
