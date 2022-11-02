/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wiley.dvdlibrary.dao;

import com.wiley.dvdlibrary.dto.DVD;
import com.wiley.dvdlibrary.dto.DVD.ContentRating;
import java.util.List;

/**
 *
 * @author Holly Wagner, Jennifer Ly
 */
public interface DVDLibraryDao {
    
    DVD addDVD(DVD dvd) throws DVDLibraryPersistenceException;
    DVD removeDVD(int dvdId) throws DVDLibraryPersistenceException;
    DVD updateDVD(DVD dvd) throws DVDLibraryPersistenceException;
    DVD getDVD(int dvdId) throws DVDLibraryPersistenceException;
    List<DVD> getAllDVDs();
    List<DVD> searchDVDsByTitle(String title) throws DVDLibraryPersistenceException;
    
    // new stream methods
    List<DVD> getDVDsLastNYears(int N);
    List<DVD> getDVDsWithRating(ContentRating cr);
    List<DVD> getDVDsWithDirector(String director);
}
