/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wiley.dvdlibrary.dto;

import java.util.Objects;

/**
 *
 * @author Jennifer Ly
 */
public class UserRating {
    
    private int starRating;
    private String comment;

    public UserRating(int starRating) {
        this.starRating = starRating;
        // default comment if no comment is provided
        this.comment = "No comment.";
    }
    
    public UserRating(int starRating, String comment) {
        this.starRating = starRating;
        this.comment = comment;
    }

    public int getStarRating() {
        return starRating;
    }

    public void setStarRating(int starRating) {
        this.starRating = starRating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return starRating + " stars" + "\nComment: " + comment;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.starRating;
        hash = 67 * hash + Objects.hashCode(this.comment);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserRating other = (UserRating) obj;
        if (this.starRating != other.starRating) {
            return false;
        }
        if (!Objects.equals(this.comment, other.comment)) {
            return false;
        }
        return true;
    }
    
    
}
