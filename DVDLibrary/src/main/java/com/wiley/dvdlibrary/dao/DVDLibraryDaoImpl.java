/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wiley.dvdlibrary.dao;

import com.wiley.dvdlibrary.dto.DVD;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Holly Wagner, Jennifer Ly
 */
public class DVDLibraryDaoImpl implements DVDLibraryDao {

    private Map<Integer, DVD> dvds = new HashMap<>();
    
    @Override
    public DVD addDVD(DVD dvd) {
        DVD newDvd = dvds.put(dvd.getId(), dvd);
        return newDvd;
    }

    @Override
    public DVD removeDVD(int dvdId) {
        return dvds.remove(dvdId);
    }

    @Override
    public DVD updateDVD(DVD dvd) {
        return dvds.replace(dvd.getId(), dvd);
    }

    @Override
    public DVD getDVD(int dvdId) {
        return dvds.get(dvdId);
    }

    @Override
    public List<DVD> getAllDVDs() {
        return new ArrayList(dvds.values());
    }

    @Override
    public List<DVD> searchDVDsByTitle(String title) {
        List<DVD> searchResults = new ArrayList<>();
        for (DVD dvd : dvds.values()) {
            // searches based on if any of the DVDs contains the searched keyword
            if (dvd.getTitle().toLowerCase().contains(title.toLowerCase())) {
                searchResults.add(dvd);
            }
        }
        return searchResults;
    }
    
}
