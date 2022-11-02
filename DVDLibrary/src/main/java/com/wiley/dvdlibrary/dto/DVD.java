/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wiley.dvdlibrary.dto;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Jennifer Ly
 */
public class DVD {

    private static final AtomicInteger uniqueId = new AtomicInteger();
    private int id;
    private String title;
    private LocalDate releaseDate;
    private ContentRating contentRating;
    private String director;
    private String studio;
    private UserRating userRating;

    public enum ContentRating {
        G,
        PG,
        R,
        X
    }
    
    // creates a unique id number each time the constructor is called
    // id is not always assigned the smallest unused integer
    public DVD() {
        id = uniqueId.getAndIncrement();
    }

    public DVD(String title, LocalDate releaseDate, ContentRating contentRating, String director, String studio, UserRating userRating) {
        id = uniqueId.getAndIncrement();
        this.title = title;
        this.releaseDate = releaseDate;
        this.director = director;
        this.studio = studio;
        this.userRating = userRating;
        this.contentRating = contentRating;
    }

    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public UserRating getUserRating() {
        return userRating;
    }

    public void setUserRating(UserRating userRating) {
        this.userRating = userRating;
    }

    public ContentRating getContentRating() {
        return contentRating;
    }

    public void setContentRating(ContentRating contentRating) {
        this.contentRating = contentRating;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\nTitle: " + title + "\nRelease Date: " + releaseDate 
                + "\nContent Rating: " + contentRating + "\nDirector: " + director 
                + "\nStudio: " + studio + "\nUser Rating: " + userRating;
    }
    
    
}
