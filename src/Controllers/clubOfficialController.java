/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.clubOfficialModel;
import Models.eventModel;
import Models.eventResultModel;
import Models.familyGroupModel;
import Models.meetingModel;
import Models.parentModel;
import Models.swimmerModel;
import java.util.ArrayList;

/**
 *
 * @author Vicen
 */
public class clubOfficialController {

    private static final clubOfficialController instance = new clubOfficialController();
    private final databaseHelper dbHelper = new databaseHelper();
    private clubOfficialModel user;

    private clubOfficialController() {};
    
    public boolean authenticateUser(String password, String username) {
        user = dbHelper.authenticateClubOfficialAccount(username, password);

        if (user == null) {
            return false;
        } else {
            return true;
        }
    }
    
    public boolean addSwimmer(swimmerModel newSwimmer){
        return dbHelper.addSwimmer(newSwimmer);
    }
    
    public boolean checkForFamilyID(int ID) {
        ArrayList<familyGroupModel> families = dbHelper.getAllFamilyGroups();
        boolean exists = false;
        
        for (int i = 0; i < families.size(); i++) {
            if(families.get(i).getFamilyGroupID() == ID){
                exists = true;
            }
        }
        
        return exists;
    }
    
    public boolean modifySwimmer(swimmerModel modifiedSwimmer) {
         return dbHelper.modifySwimmer(modifiedSwimmer);
    }
    
    public boolean addParent(parentModel newParent){
        return dbHelper.addParent(newParent);
    }
    
    public boolean modifyParent(parentModel modifiedParent){
        return dbHelper.modifyParent(modifiedParent);
    }
    
    public boolean checkForUsernameAvailabilitySwimmer(String username){
        ArrayList<swimmerModel> swimmers = dbHelper.getAllSwimmers();
        boolean available = true;
        
        for (int i = 0; i < swimmers.size(); i++) {
            if(swimmers.get(i).getName().equalsIgnoreCase(username))
            {
                available = false;
            }
        }
        
        return available;
    }
    
    public boolean checkForUsernameAvailabilityParent(String username){
        ArrayList<parentModel> swimmers = dbHelper.getAllParents();
        boolean available = true;
        
        for (int i = 0; i < swimmers.size(); i++) {
            if(swimmers.get(i).getName().equalsIgnoreCase(username))
            {
                available = false;
            }
        }
        
        return available;
    }
    
    public boolean checkForMeetingNameAvailability (String meetingName){
        ArrayList<meetingModel> meetings = dbHelper.getAllMeetings();
        boolean available = true;
        
        for (int i = 0; i < meetings.size(); i++) {
            if(meetings.get(i).getMeetingName() == meetingName)
            {
                available = false;
            }
        }
        
        return available;
    }
    
    public boolean areTheyMale(String gender)
    {
        if(gender.equalsIgnoreCase("male"))
        {
            return true;
        } 
        
        return false;
    }
    
    public void addSwimmerToEvent(eventModel event, int swimmerID, int round){
        ArrayList<eventResultModel> eventResults = dbHelper.getAllEventResults();
        ArrayList<eventResultModel> relevantResults = new ArrayList<>();
        for (int i = 0; i < eventResults.size(); i++) {
            if(eventResults.get(i).getEventID() == event.getEventID())
            {
                relevantResults.add(eventResults.get(i));
            }
        }
        
        int laneNumber = 0;
        if(relevantResults.size() != 0)
        {
            for (int i = 0; i < relevantResults.size(); i++) {
                if(laneNumber <= relevantResults.get(i).getLaneNumber())
                {
                    laneNumber = relevantResults.get(i).getLaneNumber() + 1;
                }
            }
        } else {
            laneNumber = 1;
        }
        
        eventResultModel resultToInput = new eventResultModel(event.getEventID(), dbHelper.getSwimmerbyID(swimmerID).getName(), round, laneNumber, false, 0, 0, 0, "Event hasnt finished yet");
        
        dbHelper.addEventResult(resultToInput);
    }
    
    public boolean checkForMeetingID(int meetingID){
        ArrayList <meetingModel> meetings = dbHelper.getAllMeetings();
        boolean meetingExists = false;
        
        for (int i = 0; i < meetings.size(); i++) {
            if(meetings.get(i).getMeetingID() == meetingID)
            {
                meetingExists = true;
            }
        }
        
        return meetingExists;
    }
    
    public boolean addEventResultToEvent(eventResultModel eventResult)
    {
        return dbHelper.addEventResult(eventResult);
    }
    
    public boolean modifyEventResult(eventResultModel eventResult) {
        return dbHelper.modifyEventResult(eventResult);
    }
    
    public boolean checkEventExists(int eventID)
    {
        ArrayList<eventResultModel> eventResults = dbHelper.getAllEventResults();
        boolean exists = false;
        
        for (int i = 0; i < eventResults.size(); i++) {
            if(eventResults.get(i).getEventID() == eventID)
            {
                exists = true;
            }
        }
        
        return exists;
    }
    
    public swimmerModel getSwimmerByID(int ID)
    {
        return dbHelper.getSwimmerbyID(ID);
    }
    
    public boolean checkSwimmerExists(int ID)
    {
        ArrayList<swimmerModel> swimmers = dbHelper.getAllSwimmers();
        boolean exists = false;
        
        for (int i = 0; i < swimmers.size(); i++) {
            if(swimmers.get(i).getSwimmerID() == ID)
            {
                return true;
            }
        }   
        
        return false;
    }
    
    public boolean addEventToMeeting(eventModel event)
    {
        return dbHelper.addEvent(event);
    }
    
    public boolean modifyEvent(eventModel event) {
        return dbHelper.modifyEvent(event);
    }
    
    public meetingModel getMeetingByID(int ID)
    {
        return dbHelper.getMeetingbyID(ID);
    }
    
    public boolean addMeeting (meetingModel newMeeting){
        return dbHelper.addMeeting(newMeeting);
    }
    
    public boolean updateParent(parentModel newParent){
        return dbHelper.modifyParent(newParent);
    }
    
    public boolean updateSwimmer(swimmerModel newSwimmer){
        return dbHelper.modifySwimmer(newSwimmer);
    }
    
    public boolean modifyMeeting(meetingModel newMeeting){
        return dbHelper.modifyMeeting(newMeeting);
    }

    public static clubOfficialController getInstance() {
        return instance;
    }
    
    public ArrayList<swimmerModel> getAllSwimmers(){
        return dbHelper.getAllSwimmers();
    }
    
    public ArrayList<parentModel> getAllParents(){
        return dbHelper.getAllParents();
    }
    
    public ArrayList<meetingModel> getAllMeetings(){
        return dbHelper.getAllMeetings();
    }
    
    public ArrayList<eventModel> getAllEvents(){
        return dbHelper.getAllEvents();
    }
    
    public ArrayList<eventResultModel> getAllEventResults(){
        return dbHelper.getAllEventResults();
    }
    
    
}
