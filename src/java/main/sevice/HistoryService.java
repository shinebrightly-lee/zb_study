package main.sevice;

import main.model.dao.*;
import main.model.dto.*;

import java.sql.*;
import java.util.*;

public class HistoryService {
    public void historySave(double lat, double lnt){
        HistoryDAO historyDAO = new HistoryDAO();
        historyDAO.insertHistory( lat, lnt );
    }

    public ArrayList<History> historyList() throws SQLException {
        HistoryDAO historyDAO = new HistoryDAO();
        return historyDAO.selectAllHistory();
    }

    public void historySelectDelete(int id){
        HistoryDAO historyDAO = new HistoryDAO();
        historyDAO.selectHistoryDelete(id);
    }

}
