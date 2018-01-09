/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdlibraryspringmvc.dao;

import com.mycompany.dvdlibraryspringmvc.model.Dvd;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author DavidKing
 */
public class DvdLibraryDaoDbImpl implements DvdLibraryDao {

    private static final String SQL_INSERT_DVD
            = "insert into dvd "
            + "(title, releaseYear, director, rating, notes) "
            + "values (?, ?, ?, ?, ?)";
    private static final String SQL_DELETE_DVD
            = "delete from dvd where dvdId = ?";
    private static final String SQL_SELECT_DVD
            = "select * from dvd where dvdId = ?";
    private static final String SQL_UPDATE_DVD
            = "update dvd set "
            + "title = ?, releaseYear = ?, director = ?, "
            + "rating = ?, notes = ? "
            + "where dvdId = ?";
    private static final String SQL_SELECT_ALL_DVD
            = "select * from dvd";
    private static final String SQL_SELECT_DVD_BY_TITLE
            = "select * from dvd where title = ?";
    private static final String SQL_SELECT_DVD_BY_DIRECTOR
            = "select * from dvd where director = ?";
    private static final String SQL_SELECT_DVD_BY_RELEASEYEAR
            = "select * from dvd where releaseYear = ?";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Dvd addDvd(Dvd dvd) {
        jdbcTemplate.update(SQL_INSERT_DVD,
                dvd.getTitle(),
                dvd.getReleaseYear(),
                dvd.getDirector(),
                dvd.getRating(),
                dvd.getNotes());

        int newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                Integer.class);

        dvd.setDvdId(newId);
        return dvd;
    }

    @Override
    public void removeDvd(long dvdId) {
        jdbcTemplate.update(SQL_DELETE_DVD, dvdId);
    }

    @Override
    public void updateDvd(Dvd dvd) {
        jdbcTemplate.update(SQL_UPDATE_DVD,
                dvd.getTitle(),
                dvd.getReleaseYear(),
                dvd.getDirector(),
                dvd.getRating(),
                dvd.getNotes(),
                dvd.getDvdId());
    }

    @Override
    public List<Dvd> getAllDvds() {
        return jdbcTemplate.query(SQL_SELECT_ALL_DVD,
                new DvdMapper());
    }

    @Override
    public Dvd getDvdById(long dvdId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_DVD,
                    new DvdMapper(), dvdId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Dvd> searchDvd(Map<SearchTerm, String> criteria) {
        if (criteria.isEmpty()) {
            return getAllDvds();
        } else {
            StringBuilder sQuery
                    = new StringBuilder("select * from dvd where ");
            int numParams = criteria.size();
            int paramPosition = 0;

            String[] paramVals = new String[numParams];
            Set<SearchTerm> keySet = criteria.keySet();
            Iterator<SearchTerm> iter = keySet.iterator();

            while (iter.hasNext()) {
                SearchTerm currentKey = iter.next();
                
                if (paramPosition > 0) {
                    sQuery.append(" and ");
                }
                sQuery.append(currentKey);
                sQuery.append(" = ? ");
                
                paramVals[paramPosition] = criteria.get(currentKey);
                paramPosition++;
            }

            return jdbcTemplate.query(sQuery.toString(),
                    new DvdMapper(),
                    paramVals);
        }
    }

    private static final class DvdMapper implements RowMapper<Dvd> {

        public Dvd mapRow(ResultSet rs, int rowNum) throws SQLException {
            Dvd dvd = new Dvd();
            dvd.setDvdId(rs.getLong("dvdId"));
            dvd.setTitle(rs.getString("title"));
            dvd.setReleaseYear(rs.getInt("releaseYear"));
            dvd.setDirector(rs.getString("director"));
            dvd.setRating(rs.getString("rating"));
            dvd.setNotes(rs.getString("notes"));
            return dvd;
        }
    }

}
