/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.eventModel;
import Models.eventResultModel;
import Models.meetingModel;
import Models.swimmerModel;
import java.util.ArrayList;

/**
 *
 * @author Vicen
 */
public class swimmerController {

    private static final swimmerController instance = new swimmerController();
    private final databaseHelper dbHelper = new databaseHelper();
    private swimmerModel user;

    private swimmerController() {
    }

    ;
    
    public boolean authenticateUser(String password, String username) {
        user = dbHelper.authenticateSwimmerAccount(username, password);

        if (user == null) {
            return false;
        } else {
            return true;
        }
    }

    public boolean swimmerAuthenticated() {
        return user.isArchived();
    }

    public ArrayList<eventModel> getEventsForSwimmer() {
        ArrayList<eventResultModel> eventResultArray = dbHelper.getAllEventResults();
        ArrayList<eventResultModel> swimmerEventResults = new ArrayList<eventResultModel>();
        ArrayList<eventModel> eventsForSwimmer = new ArrayList<eventModel>();

        for (int i = 0; i < eventResultArray.size(); i++) {
            if (eventResultArray.get(i).getSwimmerName().equalsIgnoreCase(user.getName())) {
                swimmerEventResults.add(eventResultArray.get(i));
            }
        }

        if (swimmerEventResults.size() > 0) {

            for (int i = 0; i < swimmerEventResults.size(); i++) {
                eventsForSwimmer.add(dbHelper.getEventbyID(swimmerEventResults.get(i).getEventID()));
            }
        }
        return eventsForSwimmer;
    }

    public ArrayList<meetingModel> getMeetingsForSwimmer() {
        ArrayList<eventModel> swimmerEvents = getEventsForSwimmer();
        ArrayList<meetingModel> swimmerMeeting = new ArrayList<>();

        for (int i = 0; i < swimmerEvents.size(); i++) {
            swimmerMeeting.add(dbHelper.getMeetingbyID(swimmerEvents.get(i).getMeetingID()));
        }

        return swimmerMeeting;
    }

    public ArrayList<eventModel> getEventsWithoutTheSwimmer() {
        ArrayList<eventModel> swimmerEvents = getEventsForSwimmer();
        ArrayList<eventModel> allEvents = dbHelper.getAllEvents();

        for (int i = 0; i < allEvents.size(); i++) {
            for (int j = 0; j < swimmerEvents.size(); j++) {
                if (allEvents.get(i).getEventID() == swimmerEvents.get(j).getEventID()) {
                    allEvents.remove(i);
                }
            }
        }

        return allEvents;
    }

    public ArrayList<meetingModel> getMeetingsWithoutTheSwimmer() {
        ArrayList<meetingModel> swimmerModel = getMeetingsForSwimmer();
        ArrayList<meetingModel> allModels = dbHelper.getAllMeetings();
        ArrayList<meetingModel> returnArray = dbHelper.getAllMeetings();
        
        int meetingID;
        
        for (int i = 0; i < swimmerModel.size(); i++) {
            meetingID = swimmerModel.get(i).getMeetingID();
            for (int ii = 0; ii < allModels.size(); ii++) {
                if(allModels.get(ii).getMeetingID() != meetingID)
                {
                    returnArray.remove(allModels.get(ii));
                }
            }
        }
        
        return returnArray;
    }

    public String stringifyMeetingArray(ArrayList<meetingModel> swimmerMeetingArray, ArrayList<meetingModel> otherMeetingArray) {
        String yourMeetings = "================================ YOUR MEETINGS ===================================";

        String meetingInfo = "";

        for (int i = 0; i < swimmerMeetingArray.size(); i++) {
            meetingInfo += swimmerMeetingArray.get(i).toString() + "\n";
        }

        String altMeetings = "\n================================ OTHER MEETINGS ===================================";

        String altMeetingInfo = "";

        for (int i = 0; i < otherMeetingArray.size(); i++) {
            altMeetingInfo += otherMeetingArray.get(i).toString() + "\n";
        }

        String finalString = yourMeetings + meetingInfo + altMeetings + altMeetingInfo;

        return finalString;
    }

    public ArrayList<eventModel> getAllEvents() {
        return dbHelper.getAllEvents();
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

    public ArrayList<eventModel> getSwimmerEventsFromArray(ArrayList<eventModel> events) {
        ArrayList<eventModel> returnArray = new ArrayList<>();
        ArrayList<eventResultModel> eventResults = dbHelper.getAllEventResults();

        for (int i = 0; i < events.size(); i++) {
            for (int j = 0; j < eventResults.size(); j++) {
                if (events.get(i).getEventID() == eventResults.get(j).getEventID() && eventResults.get(j).getSwimmerName().equalsIgnoreCase(user.getName())) {
                    returnArray.add(events.get(i));
                }
            }
        }

        return returnArray;
    }

    public ArrayList<eventModel> getNonSwimmerEventsFromArray(ArrayList<eventModel> events) {
        ArrayList<eventModel> returnArray = new ArrayList<>();
        ArrayList<eventResultModel> eventResults = dbHelper.getAllEventResults();

        for (int i = 0; i < events.size(); i++) {
            for (int j = 0; j < eventResults.size(); j++) {
                if (events.get(i).getEventID() == eventResults.get(j).getEventID() && !eventResults.get(j).getSwimmerName().equalsIgnoreCase(user.getName())) {
                    returnArray.add(events.get(i));
                }
            }
        }

        return returnArray;
    }

    public ArrayList<eventResultModel> getAllEventResults() {
        return dbHelper.getAllEventResults();
    }

    public String getUsersName() {
        return user.getName();
    }

    public static swimmerController getInstance() {
        return instance;
    }
}
