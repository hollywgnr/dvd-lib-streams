/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wiley.dvdlibrary.controller;

import com.wiley.dvdlibrary.dao.DVDLibraryDao;
import com.wiley.dvdlibrary.dao.DVDLibraryPersistenceException;
import com.wiley.dvdlibrary.dto.DVD;
import com.wiley.dvdlibrary.ui.DVDLibraryView;
import java.util.List;

/**
 *
 * @author Holly Wagner, Jennifer Ly
 */
public class DVDLibraryController {

    private DVDLibraryView view;
    private DVDLibraryDao dao;

    public DVDLibraryController(DVDLibraryView view, DVDLibraryDao dao) {
        this.view = view;
        this.dao = dao;
    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {

                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        addDVD();
                        break;
                    case 2:
                        removeDVD();
                        break;
                    case 3:
                        updateDVD();
                        break;
                    case 4:
                        listDVDs();
                        break;
                    case 5:
                        getDVD();
                        break;
                    case 6:
                        searchDVDByTitle();
                        break;
                    case 7:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
            }
            exitMessage();
        } catch (DVDLibraryPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void addDVD() throws DVDLibraryPersistenceException {
        view.displayCreateDVDBanner();
        DVD dvd = view.getNewDVDInfo();
        dao.addDVD(dvd);
        view.displayCreateSuccessBanner();
    }
    
    private void removeDVD() throws DVDLibraryPersistenceException {
        view.displayRemoveDVDBanner();
        int removeId = view.getDVDId();
        DVD dvd = dao.removeDVD(removeId);
        view.displayRemoveResult(dvd);
    }
    
    private void updateDVD() throws DVDLibraryPersistenceException {
        view.displayUpdateDVDBanner();
        int editId = view.getDVDId();
        DVD dvd = view.editDVDInfo(dao.getDVD(editId));
        dao.updateDVD(dvd);
        view.displayUpdateResult(dvd);
    }
    
    private void listDVDs() {
        view.displayListDVDsBanner();
        List<DVD> dvdList = dao.getAllDVDs();
        view.displayDVDList(dvdList);
    }
    
    private void getDVD() throws DVDLibraryPersistenceException {
        view.displayDVDInfoBanner();
        int dvdId = view.getDVDId();
        view.displayDVD(dao.getDVD(dvdId));
    }

    private void searchDVDByTitle() throws DVDLibraryPersistenceException {
        view.displaySearchDVDBanner();
        String title = view.getSearchTitle();
        view.displayDVDList(dao.searchDVDsByTitle(title));
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

}
