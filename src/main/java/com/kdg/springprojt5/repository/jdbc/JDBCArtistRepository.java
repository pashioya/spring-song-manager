package com.kdg.springprojt5.repository.jdbc;

import com.kdg.springprojt5.domain.AlbumArtist;
import com.kdg.springprojt5.domain.Artist;
import com.kdg.springprojt5.repository.ArtistRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Profile("jdbc")
@Repository
public class JDBCArtistRepository implements ArtistRepository {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert inserter;

    public JDBCArtistRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.inserter = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("artists")
                .usingGeneratedKeyColumns("id");
    }

    private Artist mapArtistRow(ResultSet rs, long rowid)
    throws SQLException {
        return new Artist(
                rs.getLong("id"),
                rs.getLong("user_id"),
                rs.getString("artist_name"),
                rs.getDouble("artist_followers")
                );
    }


    @Override
    public Artist save(Artist artist) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("artistName", artist.getArtistName());
        parameters.put("artistFollowers", artist.getArtistFollowers());
        artist.setId(inserter.executeAndReturnKey(new MapSqlParameterSource(parameters)).longValue());
        return artist;
    }

    @Override
    public List<Artist> getAllArtists(){
        String sql = "SELECT * FROM artists ORDER BY id DESC";
        return jdbcTemplate.query(sql, this::mapArtistRow);
    }

    @Override
    public Artist getArtistById(Long id) {
        String sql = "SELECT * FROM artists WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, this::mapArtistRow, id);
    }

    @Override
    public List<Artist> getAlbumsArtists(Long id) {
        String sql = "SELECT artist_id FROM album_artist WHERE album_id = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Long artistId = rs.getLong("artist_id");
            return getArtistById(artistId);
        }, id);
    }

    @Override
    public void updateAlbumArtist(AlbumArtist albumArtist) {
        String sql = "UPDATE album_artist SET artist_id = ? WHERE album_id = ?";
        jdbcTemplate.update(sql, albumArtist.getAlbum().getId(), albumArtist.getArtist().getId());
    }


    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM artists WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Artist> getAllArtistsForAlbum(Long albumId) {
        String sql = "SELECT * FROM artists WHERE id IN (SELECT artist_id FROM album_artist WHERE album_id = ?)";
        return jdbcTemplate.query(sql, this::mapArtistRow, albumId);
    }

}

