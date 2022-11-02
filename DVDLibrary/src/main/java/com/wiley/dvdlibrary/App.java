/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wiley.dvdlibrary;

import com.wiley.dvdlibrary.controller.DVDLibraryController;
import com.wiley.dvdlibrary.dao.DVDLibraryDao;
import com.wiley.dvdlibrary.dao.DVDLibraryDaoFileImpl;
import com.wiley.dvdlibrary.ui.DVDLibraryView;
import com.wiley.dvdlibrary.ui.UserIO;
import com.wiley.dvdlibrary.ui.UserIOConsoleImpl;

/**
 *
 * @author Holly Wagner, Jennifer Ly
 */
public class App {

    public static void main(String[] args) {
        UserIO myIo = new UserIOConsoleImpl();
        DVDLibraryView myView = new DVDLibraryView(myIo);
        // currently uses DAO File Implementation
        DVDLibraryDao myDao = new DVDLibraryDaoFileImpl();
        DVDLibraryController controller = new DVDLibraryController(myView, myDao);
        controller.run();
    }
    
}
