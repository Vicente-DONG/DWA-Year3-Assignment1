/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Member;
import Models.eventModel;
import Models.eventResultModel;
import Models.meetingModel;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

/**
 *
 * @author Vicen
 */
public class guestController {

    databaseHelper dbHelper = new databaseHelper();

    public ArrayList<eventModel> getAllEvents() {
        return dbHelper.getAllEvents();
    }
    
    public ArrayList<meetingModel> getAllMeetings() {
        return dbHelper.getAllMeetings();
    }
    
    public ArrayList<meetingModel> getMeetingsFilteredByVenue(String venueName)
    {
        ArrayList<meetingModel> allMeetings = dbHelper.getAllMeetings();
        ArrayList<meetingModel> returnArray = new ArrayList<>();
        
        for (int i = 0; i < allMeetings.size(); i++) {
            if(allMeetings.get(i).getVenueName().equalsIgnoreCase(venueName))
            {
                returnArray.add(allMeetings.get(i));
            }
        }
        
        return returnArray;
    }
    
    public ArrayList<meetingModel> getMeetingsFilteredByVenue(LocalDate startDate, LocalDate endDate)
    {
        ArrayList<meetingModel> allMeetings = dbHelper.getAllMeetings();
        ArrayList<meetingModel> returnArray = new ArrayList<>();
        
        for (int i = 0; i < allMeetings.size(); i++) {
            if(allMeetings.get(i).getMeetingDate().isAfter(startDate) || allMeetings.get(i).getMeetingDate().isBefore(endDate))
            {
                returnArray.add(allMeetings.get(i));
            }
        }
        
        return returnArray;
    }
    
    public ArrayList<meetingModel> getMeetingsFilteredByVenueAndDate(String venueName, LocalDate startDate, LocalDate endDate)
    {
        ArrayList<meetingModel> allMeetings = dbHelper.getAllMeetings();
        ArrayList<meetingModel> returnArray = new ArrayList<>();
        
        for (int i = 0; i < allMeetings.size(); i++) {
            if(allMeetings.get(i).getMeetingDate().isAfter(startDate) || allMeetings.get(i).getMeetingDate().isBefore(endDate))
            {
                if(allMeetings.get(i).getVenueName().equalsIgnoreCase(venueName))
                {
                    returnArray.add(allMeetings.get(i));
                }
            }
        }
        
        return returnArray;
    }
    
    public ArrayList<eventModel> getEventsFilteredByAge(int minAge, int maxAge) {
        ArrayList<eventModel> events = dbHelper.getAllEvents();
        ArrayList<eventModel> returnArray = new ArrayList<>();

        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).getMaxAge() <= maxAge && events.get(i).getMinAge() >= minAge) {
                returnArray.add(events.get(i));
            }
        }

        return returnArray;
    }

    public ArrayList<eventModel> getEventsFilteredByStroke(String stroke) {
        ArrayList<eventModel> events = dbHelper.getAllEvents();
        ArrayList<eventModel> returnArray = new ArrayList<>();

        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).getStroke().equalsIgnoreCase(stroke)) {
                returnArray.add(events.get(i));
            }
        }

        return returnArray;
    }

    public ArrayList<eventModel> getEventsFilteredByName(String name) {
        ArrayList<eventModel> returnArray = new ArrayList<>();
        ArrayList<eventResultModel> eventResults = dbHelper.getAllEventResults();

        for (int i = 0; i < eventResults.size(); i++) {
            if (eventResults.get(i).getSwimmerName().equalsIgnoreCase(name)) {
                returnArray.add(dbHelper.getEventbyID(eventResults.get(i).getEventID()));
            }
        }

        return returnArray;
    }
    
    public ArrayList<eventResultModel> getAllEventResults()
    {
        return dbHelper.getAllEventResults();
    }
}
