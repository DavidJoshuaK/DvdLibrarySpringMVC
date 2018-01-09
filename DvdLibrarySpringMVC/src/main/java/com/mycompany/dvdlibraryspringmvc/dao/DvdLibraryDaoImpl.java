/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdlibraryspringmvc.dao;

import com.mycompany.dvdlibraryspringmvc.model.Dvd;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 * @author DavidKing
 */
public class DvdLibraryDaoImpl implements DvdLibraryDao {

    private Map<Long, Dvd> dvdMap = new HashMap<>();

    private static long dvdIdCounter = 1;

    @Override
    public Dvd addDvd(Dvd dvd) {
        dvd.setDvdId(dvdIdCounter);
        dvdIdCounter++;
        dvdMap.put(dvd.getDvdId(), dvd);
        return dvd;
    }

    @Override
    public void removeDvd(long dvdId) {
        dvdMap.remove(dvdId);
    }

    @Override
    public void updateDvd(Dvd dvd) {
        dvdMap.put(dvd.getDvdId(), dvd);
    }

    @Override
    public List<Dvd> getAllDvds() {
        Collection<Dvd> d = dvdMap.values();
        return new ArrayList(d);
    }

    @Override
    public Dvd getDvdById(long dvdId) {
        return dvdMap.get(dvdId);
    }

    @Override
    public List<Dvd> searchDvd(Map<SearchTerm, String> criteria) {
        // Get all the search term values from the map
        String titleSearchCriteria
                = criteria.get(SearchTerm.TITLE);
        String releaseYearSearchCriteria
                = criteria.get(SearchTerm.RELEASEYEAR);
        String directorSearchCriteria
                = criteria.get(SearchTerm.DIRECTOR);
        String ratingSearchCriteria
                = criteria.get(SearchTerm.RATING);
        String notesSearchCriteria
                = criteria.get(SearchTerm.NOTES);
        
        Predicate<Dvd> titleMatchPredicate;
        Predicate<Dvd> releaseYearMatchPredicate;
        Predicate<Dvd> directorMatchPredicate;
        Predicate<Dvd> ratingMatchPredicate;
        Predicate<Dvd> notesMatchPredicate;

        Predicate<Dvd> truePredicate = (d) -> {
            return true;
        };

        if (titleSearchCriteria == null
                || titleSearchCriteria.isEmpty()) {
            titleMatchPredicate = truePredicate;
        } else {
            titleMatchPredicate
                    = (d) -> d.getTitle().equals(titleSearchCriteria);
        }

        if (releaseYearSearchCriteria == null
                || releaseYearSearchCriteria.isEmpty()) 
                {
            releaseYearMatchPredicate = truePredicate;
        } else {
              int b = Integer.parseInt(releaseYearSearchCriteria);
            releaseYearMatchPredicate 
                    = (d) -> d.getReleaseYear() == Integer.parseInt(releaseYearSearchCriteria);                         
        }

        if (directorSearchCriteria == null
                || directorSearchCriteria.isEmpty()) {
            directorMatchPredicate = truePredicate;
        } else {
            directorMatchPredicate
                    = (d) -> d.getDirector().equals(directorSearchCriteria);
        }

        if (ratingSearchCriteria == null
                || ratingSearchCriteria.isEmpty()) {
            ratingMatchPredicate = truePredicate;
        } else {
            ratingMatchPredicate
                    = (d) -> d.getRating().equals(ratingSearchCriteria);
        }
        
        if (notesSearchCriteria == null
                || notesSearchCriteria.isEmpty()) {
            notesMatchPredicate = truePredicate;
        } else {
            notesMatchPredicate
                    = (d) -> d.getNotes().equals(notesSearchCriteria);
        }

        return dvdMap.values().stream()
                .filter(titleMatchPredicate
                        .and(releaseYearMatchPredicate)
                        .and(directorMatchPredicate)
                        .and(ratingMatchPredicate)
                        .and(notesMatchPredicate))
                .collect(Collectors.toList());
    }

}
