/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wiley.dvdlibrary.dao;

import com.wiley.dvdlibrary.dto.DVD;
import com.wiley.dvdlibrary.dto.UserRating;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Jenn
 */
public class DVDLibraryDaoFileImplTest {
    
    private DVDLibraryDao testDao;
    
    public DVDLibraryDaoFileImplTest() {
    }

    @BeforeEach
    public void setUp() throws Exception {
        String testFile = "dvd.txt";
        // Use the FileWriter to quickly blank the file
        new FileWriter(testFile);
        testDao = new DVDLibraryDaoFileImpl(testFile);
    }

    @Test
    public void testAddGetDVD() throws Exception {
        DVD dvd = new DVD();
        dvd.setTitle("Cool DVD");
        dvd.setReleaseDate(LocalDate.MAX);
        dvd.setContentRating(DVD.ContentRating.G);
        dvd.setDirector("Smelly McGee");
        dvd.setStudio("Studio Coolzone");
        dvd.setUserRating(new UserRating(5, "Cool!"));

        testDao.addDVD(dvd);
        DVD retrievedDVD = testDao.getDVD(dvd.getId());

        assertEquals(dvd.getId(),retrievedDVD.getId(), "Checking dvd Id.");
        assertEquals(dvd.getTitle(),retrievedDVD.getTitle(), "Checking dvd Id.");
        assertEquals(dvd.getReleaseDate(),retrievedDVD.getReleaseDate(), "Checking dvd Id.");
        assertEquals(dvd.getContentRating(),retrievedDVD.getContentRating(), "Checking dvd Id.");
        assertEquals(dvd.getDirector(),retrievedDVD.getDirector(), "Checking dvd Id.");
        assertEquals(dvd.getStudio(),retrievedDVD.getStudio(), "Checking dvd Id.");
        assertEquals(dvd.getUserRating(),retrievedDVD.getUserRating(), "Checking dvd Id.");
    }

    /*
    @Test
    public void testAddGetAllDVDs() throws Exception {
        // Create our first dvd
        DVD firstDVD = new DVD("0001");
        firstDVD.setFirstName("Ada");
        firstDVD.setLastName("Lovelace");
        firstDVD.setCohort("Java-May-1845");

        // Create our second dvd
        DVD secondDVD = new DVD("0002");
        secondDVD.setFirstName("Charles");
        secondDVD.setLastName("Babbage");
        secondDVD.setCohort(".NET-May-1845");

        // Add both our dvds to the DAO
        testDao.addDVD(firstDVD.getDVDId(), firstDVD);
        testDao.addDVD(secondDVD.getDVDId(), secondDVD);

        // Retrieve the list of all dvds within the DAO
        List<DVD> allDVDs = testDao.getAllDVDs();

        // First check the general contents of the list
        assertNotNull(allDVDs, "The list of dvds must not null");
        assertEquals(2, allDVDs.size(), "List of dvds should have 2 dvds.");

        // Then the specifics
        assertTrue(testDao.getAllDVDs().contains(firstDVD),
                "The list of dvds should include Ada.");
        assertTrue(testDao.getAllDVDs().contains(secondDVD),
                "The list of dvds should include Charles.");

    }

    @Test
    public void testRemoveDVD() throws Exception {
        // Create two new dvds
        DVD firstDVD = new DVD("0001");
        firstDVD.setFirstName("Ada");
        firstDVD.setLastName("Lovelace");
        firstDVD.setCohort("Java-May-1945");

        DVD secondDVD = new DVD("0002");
        secondDVD.setFirstName("Charles");
        secondDVD.setLastName("Babbage");
        secondDVD.setCohort(".NET-May-1945");

        // Add both to the DAO
        testDao.addDVD(firstDVD.getDVDId(), firstDVD);
        testDao.addDVD(secondDVD.getDVDId(), secondDVD);

        // remove the first dvd - Ada
        DVD removedDVD = testDao.removeDVD(firstDVD.getDVDId());

        // Check that the correct object was removed.
        assertEquals(removedDVD, firstDVD, "The removed dvd should be Ada.");

        // Get all the dvds
        List<DVD> allDVDs = testDao.getAllDVDs();

        // First check the general contents of the list
        assertNotNull(allDVDs, "All dvds list should be not null.");
        assertEquals(1, allDVDs.size(), "All dvds should only have 1 dvd.");

        // Then the specifics
        assertFalse(allDVDs.contains(firstDVD), "All dvds should NOT include Ada.");
        assertTrue(allDVDs.contains(secondDVD), "All dvds should NOT include Charles.");

        // Remove the second dvd
        removedDVD = testDao.removeDVD(secondDVD.getDVDId());
        // Check that the correct object was removed.
        assertEquals(removedDVD, secondDVD, "The removed dvd should be Charles.");

        // retrieve all of the dvds again, and check the list.
        allDVDs = testDao.getAllDVDs();

        // Check the contents of the list - it should be empty
        assertTrue(allDVDs.isEmpty(), "The retrieved list of dvds should be empty.");

        // Try to 'get' both dvds by their old id - they should be null!
        DVD retrievedDVD = testDao.getDVD(firstDVD.getDVDId());
        assertNull(retrievedDVD, "Ada was removed, should be null.");

        retrievedDVD = testDao.getDVD(secondDVD.getDVDId());
        assertNull(retrievedDVD, "Charles was removed, should be null.");

    }
    */
}
