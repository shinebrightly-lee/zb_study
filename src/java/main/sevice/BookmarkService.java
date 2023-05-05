package main.sevice;

import main.model.dao.*;
import main.model.dto.*;

import java.util.*;

public class BookmarkService {
    BookmarkDAO bookmarkDAO;
    public void groupAdd(String name, int seq){
        bookmarkDAO = new BookmarkDAO();
        bookmarkDAO.insertBookmarkGroup(name, seq);
    }
    public ArrayList<BookmarkGroup> groupList(){
        bookmarkDAO = new BookmarkDAO();
        return bookmarkDAO.selectAllBookmarkGroup();
    }

    public void groupModify(int id, String name, int seq){
        bookmarkDAO = new BookmarkDAO();
        bookmarkDAO.inquiry_dateUpdate(id);
        bookmarkDAO.modifyBookmarkGroup(id, name, seq);
    }

    public void groupDelete(int id){
        bookmarkDAO = new BookmarkDAO();
        bookmarkDAO.selectGroupDelete(id);
    }

}
