
package Controllers;

import Database.databaseManager;
import Models.*;
import java.util.ArrayList;

public class databaseHelper {
    databaseManager dbManager = new databaseManager();
    
    //ALL ADD METHODS START HERE
    
    public boolean addEventResult(eventResultModel newEventResult)
    {
        return dbManager.insertEventResults(newEventResult);
    }
    
    public boolean addEvent(eventModel newEvent)
    {
        return dbManager.insertEvents(newEvent);
    }
    
    public boolean addFamilyGroup(familyGroupModel newFamilyGroup)
    {
        return dbManager.insertFamilyGroup(newFamilyGroup);
    }
    
    public boolean addMeeting(meetingModel newMeeting)
    {
        return dbManager.insertMeeting(newMeeting);
    }
    
    public boolean addParent(parentModel newParent)
    {
        return dbManager.insertParent(newParent);
    }
    
    public boolean addSwimmer(swimmerModel newSwimmer)
    {
        return dbManager.insertSwimmer(newSwimmer);
    }
    
    //ALL ADD METHODS END HERE -------------------------------------------------------------------------------------------------------------------------------------------------
    
    //ALL MODIFY METHODS START HERE --------------------------------------------------------------------------------------------------------------------------------------------
    
    public boolean modifyEventResult(eventResultModel newEventResult)
    {
        return dbManager.updateEventResults(newEventResult);
    }
    
    public boolean modifyEvent(eventModel newEvent)
    {
        return dbManager.updateEvents(newEvent);
    }
    
    public boolean modifyFamilyGroup(familyGroupModel newFamilyGroup)
    {
        return dbManager.updateFamilyGroups(newFamilyGroup);
    }
    
    public boolean modifyMeeting(meetingModel newMeeting)
    {
        return dbManager.updateMeetings(newMeeting);
    }
    
    public boolean modifyParent(parentModel newParent)
    {
        return dbManager.updateParents(newParent);
    }
    
    public boolean modifySwimmer(swimmerModel newSwimmer)
    {
        return dbManager.updateSwimmers(newSwimmer);
    }
    
    //ALL MODIFY METHODS END HERE -----------------------------------------------------------------------------------------------------------------------------------------------
    
    //ALL GET METHODS BY ID START HERE ------------------------------------------------------------------------------------------------------------------------------------------
    
    public eventResultModel getEventResultbyID(int eventResultID)
    {
        return dbManager.queryEventResultByID(eventResultID);
    }
    
    public eventModel getEventbyID(int eventID)
    {
        return dbManager.queryEventsByID(eventID);
    }
    
    public familyGroupModel getFamilyGroupbyID(int familyGroupID)
    {
        return dbManager.queryFamilyGroupByID(familyGroupID);
    }
    
    public meetingModel getMeetingbyID(int meetingID)
    {
        return dbManager.queryMeetingByID(meetingID);
    }
    
    public parentModel getParentbyID(int parentID)
    {
        return dbManager.queryParentByID(parentID);
    }
    
    public swimmerModel getSwimmerbyID(int swimmerID)
    {
        return dbManager.querySwimmerByID(swimmerID);
    }
    
    //ALL GET METHODS BY ID END HERE ----------------------------------------------------------------------------------------------------------------------------------------------
    
    //ALL GET ALLDATA METHODS START HERE ------------------------------------------------------------------------------------------------------------------------------------------
    
    public ArrayList<eventResultModel> getAllEventResults()
    {
        return dbManager.queryAllEventResults();
    }
    
    public ArrayList<eventModel> getAllEvents()
    {
        return dbManager.queryAllEvents();
    }
    
    public ArrayList<familyGroupModel> getAllFamilyGroups()
    {
        return dbManager.queryAllFamilyGroups();
    }
    
    public ArrayList<meetingModel> getAllMeetings()
    {
        return dbManager.queryAllMeetings();
    }
    
    public ArrayList<parentModel> getAllParents()
    {
        return dbManager.queryAllParents();
    }
    
    public ArrayList<swimmerModel> getAllSwimmers()
    {
        return dbManager.queryAllSwimmers();
    }
    
    //ALL GET ALLDATA METHODS END HERE ------------------------------------------------------------------------------------------------------------------------------------------
    
    //ALL LOG IN AUTHENTICATION METHODS START HERE ------------------------------------------------------------------------------------------------------------------------------
    
    public clubOfficialModel authenticateClubOfficialAccount(String username, String password)
    {
        return dbManager.authenticateClubOfficial(username, password);
    }
    
    public parentModel authenticateParentAccount(String username, String password)
    {
        return dbManager.authenticateParent(username, password);
    }
    
    public swimmerModel authenticateSwimmerAccount(String username, String password)
    {
        return dbManager.authenticateSwimmer(username, password);
    }
    
    //ALL LOG IN AUTHENTICATION METHODS END HERE ----------------------------------------------------------------------------------------------------------------------------------
}
    
