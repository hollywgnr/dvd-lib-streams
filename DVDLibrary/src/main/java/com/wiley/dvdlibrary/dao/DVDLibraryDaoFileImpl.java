/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wiley.dvdlibrary.dao;

import com.wiley.dvdlibrary.dto.DVD;
import com.wiley.dvdlibrary.dto.DVD.ContentRating;
import com.wiley.dvdlibrary.dto.UserRating;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author Jennifer Ly
 */
public class DVDLibraryDaoFileImpl implements DVDLibraryDao {

    public static String DVD_FILE;
    public static final String DELIMITER = "::";
    private Map<Integer, DVD> dvds = new HashMap<>();

    // Read and Save data from an existing text file (will give an error if the file does not exist)
    public DVDLibraryDaoFileImpl() {
        // dvd.txt in the working directory is the default location
        DVD_FILE = "dvd.txt";
    }

    public DVDLibraryDaoFileImpl(String dvdTextFile) {
        // allows specifying to another directory
        DVD_FILE = dvdTextFile;
    }

    @Override
    public DVD addDVD(DVD dvd) throws DVDLibraryPersistenceException {
        loadDVDs();
        DVD newDVD = dvds.put(dvd.getId(), dvd);
        writeDVDs();
        return newDVD;
    }

    @Override
    public DVD removeDVD(int dvdId) throws DVDLibraryPersistenceException {
        loadDVDs();
        DVD removedDVD = dvds.remove(dvdId);
        writeDVDs();
        return removedDVD;
    }

    @Override
    public DVD updateDVD(DVD dvd) throws DVDLibraryPersistenceException {
        loadDVDs();
        DVD updatedDVD = dvds.replace(dvd.getId(), dvd);
        writeDVDs();
        return updatedDVD;
    }

    @Override
    public DVD getDVD(int dvdId) throws DVDLibraryPersistenceException {
        loadDVDs();
        return dvds.get(dvdId);
    }

    @Override
    public List<DVD> getAllDVDs() {
        return new ArrayList(dvds.values());
    }

    @Override
    public List<DVD> searchDVDsByTitle(String title) throws DVDLibraryPersistenceException {
        loadDVDs();
        List<DVD> searchResults = new ArrayList<>();
        for (DVD dvd : dvds.values()) {
            // searches based on if any of the DVDs contains the searched keyword
            if (dvd.getTitle().toLowerCase().contains(title.toLowerCase())) {
                searchResults.add(dvd);
            }
        }
        return searchResults;
    }

    private void loadDVDs() throws DVDLibraryPersistenceException {
        Scanner scanner = null;

        try {
            scanner = new Scanner(new BufferedReader(new FileReader(DVD_FILE)));
        } catch (FileNotFoundException e) {
            throw new DVDLibraryPersistenceException("Could not load dvd data into memory.", e);
        }
        String currentLine;
        DVD currentDVD;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentDVD = unmarshallDVD(currentLine);

            dvds.put(currentDVD.getId(), currentDVD);
        }
        scanner.close();
    }

    private void writeDVDs() throws DVDLibraryPersistenceException {
        PrintWriter out = null;

        try {
            out = new PrintWriter(new FileWriter(DVD_FILE));
        } catch (IOException e) {
            throw new DVDLibraryPersistenceException("Could not load dvd data into memory.", e);
        }

        String dvdAsText;
        List<DVD> dvdList = this.getAllDVDs();
        for (DVD currentDVD : dvdList) {
            dvdAsText = marshallDVD(currentDVD);
            out.println(dvdAsText);
            out.flush();
        }
        out.close();
    }

    // parses through a dvd as a line of text by splitting it into tokens based on the delimiter
    private DVD unmarshallDVD(String dvdAsText) {
        String[] dvdTokens = dvdAsText.split(DELIMITER);
        DVD dvdFromFile = new DVD();
        dvdFromFile.setId(Integer.parseInt(dvdTokens[0]));
        dvdFromFile.setTitle(dvdTokens[1]);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        dvdFromFile.setReleaseDate(LocalDate.parse(dvdTokens[2], formatter));
        dvdFromFile.setContentRating(ContentRating.valueOf(dvdTokens[3]));
        dvdFromFile.setDirector(dvdTokens[4]);
        dvdFromFile.setStudio(dvdTokens[5]);
        // the last 2 tokens are stored as a UserRating
        UserRating userRating = new UserRating(Integer.parseInt(dvdTokens[6]), dvdTokens[7]);

        dvdFromFile.setUserRating(userRating);

        return dvdFromFile;
    }

    private String marshallDVD(DVD aDVD) {
        String dvdAsText = aDVD.getId() + DELIMITER;

        dvdAsText += aDVD.getTitle() + DELIMITER;
        dvdAsText += aDVD.getReleaseDate() + DELIMITER;
        dvdAsText += aDVD.getContentRating() + DELIMITER;
        dvdAsText += aDVD.getDirector() + DELIMITER;
        dvdAsText += aDVD.getStudio() + DELIMITER;
        dvdAsText += aDVD.getUserRating().getStarRating() + DELIMITER;
        dvdAsText += aDVD.getUserRating().getComment();

        return dvdAsText;
    }
    
    // new stream methods
    @Override
    public List<DVD> getDVDsLastNYears(int N) {
        LocalDate today = LocalDate.now();
        int thisYear = today.getYear();
        int startYear = thisYear - N;
        String todayStr = today.toString().split("-")[1] + "-" + today.toString().split("-")[2];
        LocalDate startDate = LocalDate.parse(startYear + "-" + todayStr);
        
        List<DVD> dvdList = getAllDVDs();
        List<DVD> resultList = dvdList.stream()
                .filter((d) -> d.getReleaseDate().isAfter(startDate))
                .collect(Collectors.toList());
        
        return resultList;
    }
    
    @Override
    public List<DVD> getDVDsWithRating(ContentRating cr) {
        
        List<DVD> dvdList = getAllDVDs();
        List<DVD> resultList = dvdList.stream()
                .filter((d) -> d.getContentRating().equals(cr))
                .collect(Collectors.toList());
        
        return resultList;
    }
    
    @Override
    public List<DVD> getDVDsWithDirector(String director) {
        
        List<DVD> dvdList = getAllDVDs();
        List<DVD> resultList = dvdList.stream()
                .filter((d) -> d.getDirector().equals(director))
                .collect(Collectors.toList());
        
        return resultList;
    }    

}
