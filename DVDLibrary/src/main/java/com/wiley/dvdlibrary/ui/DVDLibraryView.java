/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wiley.dvdlibrary.ui;

import com.wiley.dvdlibrary.dto.DVD;
import com.wiley.dvdlibrary.dto.UserRating;
import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author Holly Wagner, Jennifer Ly
 */
public class DVDLibraryView {

    private UserIO io;

    public DVDLibraryView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. Add a DVD");
        io.print("2. Remove a DVD");
        io.print("3. Edit DVD information");
        io.print("4. List DVDs");
        io.print("5. Get DVD information");
        io.print("6. Search for DVD by Title");
        io.print("7. Exit");

        return io.readInt("Please select from the above choices:", 1, 7);
    }

    public DVD getNewDVDInfo() {
        return editDVDInfo(new DVD());
    }

    public DVD editDVDInfo(DVD dvd) {
        dvd.setTitle(io.readString("Please enter title"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        dvd.setReleaseDate(LocalDate.parse(io.readString("Please enter release date (yyyy-MM-dd)"), formatter));
        String contentRatingValue = io.readString("Please enter content rating (G, PG, R, X)");
        dvd.setContentRating(DVD.ContentRating.valueOf(contentRatingValue.toUpperCase()));
        dvd.setDirector(io.readString("Please enter director"));
        dvd.setStudio(io.readString("Please enter studio"));
        int userRatingStar = Integer.parseInt(io.readString("Please enter user star rating"));
        UserRating userRating = new UserRating(userRatingStar);
        String userRatingComment = io.readString("Would you like to make a comment? (Y/N)");
        if (userRatingComment.toUpperCase().equals("Y")) {
            userRatingComment = io.readString("Please enter user rating comment");
            userRating.setComment(userRatingComment);
        }
        dvd.setUserRating(userRating);
        io.print(dvd.toString());

        return dvd;
    }

    public int getDVDId() {
        int id = io.readInt("Please enter the id of the DVD:");
        return id;
    }

    public String getSearchTitle() {
        String title = io.readString("Please enter the title of the DVD to search:");
        return title;
    }
    
    public void displayDVDList(List<DVD> dvdList) {
        for (DVD dvd : dvdList) {
            io.print(dvd.getId() + ": " + dvd.getTitle());
        }
        io.readString("\nPress Enter to continue.");
    }
    
    public void displayDVD(DVD dvd) {
        io.print(dvd.toString());
        io.readString("\nPress Enter to continue.");
    }

    public void displayCreateDVDBanner() {
        io.print("=== Create DVD ===");
    }

    public void displayCreateSuccessBanner() {
        io.readString("\nDVD successfully created.  Please hit enter to continue");
    }

    public void displayRemoveDVDBanner() {
        io.print("=== Remove DVD ===");
    }

    public void displayRemoveResult(DVD dvd) {
        if (dvd != null) {
            io.print("\nDVD successfully removed.");
        } else {
            io.print("\nNo such DVD.");
        }
        io.readString("\nPlease hit enter to continue.");
    }

    public void displayUpdateDVDBanner() {
        io.print("=== Update DVD ===");
    }

    public void displayUpdateResult(DVD dvd) {
        if (dvd != null) {
            io.print("\nDVD successfully updated.");
        } else {
            io.print("\nNo such DVD.");
        }
        io.readString("\nPlease hit enter to continue.");
    }

    public void displayListDVDsBanner() {
        io.print("=== List All DVDs ===");
    }

    public void displayDVDInfoBanner() {
        io.print("=== Display DVD Information ===");
    }

    public void displaySearchDVDBanner() {
        io.print("=== Search DVD By Title ===");
    }

    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
}
