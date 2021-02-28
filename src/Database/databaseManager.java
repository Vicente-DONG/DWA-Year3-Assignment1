package Database;

import Models.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

public class databaseManager {

    private final String url = "jdbc:derby://localhost:1527/DWA - Assignment 1";
    private Connection con = null;

    public boolean insertEventResults(eventResultModel newEventResult) {
        openConnection();

        PreparedStatement stmt;
        String sqlStr = "INSERT INTO EventResults(EventID, SwimmerName, EventResultRound, EventResultLane, HaveTheyFinished, EventResultMinutes, EventResultSeconds, EventResultMiliseconds,"
                + "EventResultReason) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int rowsCount;

        try {
            stmt = con.prepareStatement(sqlStr);
            stmt.setInt(1, newEventResult.getEventID());
            stmt.setString(2, newEventResult.getSwimmerName());
            stmt.setInt(3, newEventResult.getRound());
            stmt.setInt(4, newEventResult.getLaneNumber());
            stmt.setBoolean(5, newEventResult.isHaveTheyFinished());
            stmt.setInt(6, newEventResult.getMinutes());
            stmt.setInt(7, newEventResult.getSeconds());
            stmt.setInt(8, newEventResult.getMiliseconds());
            stmt.setString(9, newEventResult.getReason());

            rowsCount = stmt.executeUpdate();
            System.out.println("Rows INSERTED into EventResults: " + rowsCount);

            stmt.close();

            closeConnection();

            return true;
        } catch (Exception e) {
            e.printStackTrace();

            closeConnection();

            return false;
        }
    }

    public boolean insertEvents(eventModel newEvent) {
        openConnection();

        PreparedStatement stmt;
        String sqlStr = "INSERT INTO Events(meetingID, eventMinAge, eventMaxAge, eventDistance, eventStroke) "
                + "VALUES (?, ?, ?, ?, ?)";
        int rowsCount;

        try {
            stmt = con.prepareStatement(sqlStr);
            stmt.setInt(1, newEvent.getMeetingID());
            stmt.setInt(2, newEvent.getMinAge());
            stmt.setInt(3, newEvent.getMaxAge());
            stmt.setDouble(4, newEvent.getDistance());
            stmt.setString(5, newEvent.getStroke());

            rowsCount = stmt.executeUpdate();
            System.out.println("Rows INSERTED into Events: " + rowsCount);
            stmt.close();

            closeConnection();

            return true;
        } catch (Exception e) {

            e.printStackTrace();

            closeConnection();

            return false;
        }
    }

    public boolean insertFamilyGroup(familyGroupModel newFamilyGroup) {
        openConnection();

        PreparedStatement stmt;
        String sqlStr = "INSERT INTO FamilyGroups(ParentOneID, ParentTwoID, SwimmerOneID, SwimmerTwoID, SwimmerThreeID, SwimmerFourID, SwimmerFiveID) VALUES "
                + "(?, ?, ?, ?, ?, ?, ?)";
        int rowsCount;

        try {
            stmt = con.prepareStatement(sqlStr);
            stmt.setInt(1, newFamilyGroup.getParentOneID());
            stmt.setInt(2, newFamilyGroup.getParentTwoID());
            stmt.setInt(3, newFamilyGroup.getChildOneID());
            stmt.setInt(4, newFamilyGroup.getChildTwoID());
            stmt.setInt(5, newFamilyGroup.getChildThreeID());
            stmt.setInt(6, newFamilyGroup.getChildFourID());
            stmt.setInt(7, newFamilyGroup.getChildFiveID());

            rowsCount = stmt.executeUpdate();
            System.out.println("Rows INSERTED into familyGroups: " + rowsCount);
            stmt.close();

            closeConnection();

            return true;
        } catch (Exception e) {
            e.printStackTrace();

            closeConnection();

            return false;
        }
    }

    public boolean insertMeeting(meetingModel newMeeting) {
        openConnection();

        PreparedStatement stmt;
        String sqlStr = "INSERT INTO Meetings(MeetingName, VenueName, MeetingDate, PoolLength) VALUES (?, ?, ?, ?)";
        int rowsCount;

        try {
            stmt = con.prepareStatement(sqlStr);
            stmt.setString(1, newMeeting.getMeetingName());
            stmt.setString(2, newMeeting.getVenueName());
            stmt.setDate(3, java.sql.Date.valueOf(newMeeting.getMeetingDate()));
            stmt.setDouble(4, newMeeting.getPoolLength());

            rowsCount = stmt.executeUpdate();
            System.out.println("Rows INSERTED into Meetings: " + rowsCount);
            stmt.close();

            closeConnection();

            return true;
        } catch (Exception e) {
            e.printStackTrace();

            closeConnection();

            return false;
        }
    }

    public boolean insertParent(parentModel newParent) {
        openConnection();

        PreparedStatement stmt;
        String sqlStr = "INSERT INTO Parents(FamilyGroupID, ParentUsername, ParentPassword, ParentName, areTheyMale, parentAddress, parentContactNumber, parentEmail, parentDateOfBirth, archived) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int rowsCount;

        try {
            stmt = con.prepareStatement(sqlStr);
            stmt.setInt(1, newParent.getFamilyGroupID());
            stmt.setString(2, newParent.getUsername());
            stmt.setString(3, newParent.getPassword());
            stmt.setString(4, newParent.getName());
            stmt.setBoolean(5, newParent.getAreTheyMale());
            stmt.setString(6, newParent.getAddress());
            stmt.setString(7, newParent.getContactNumber());
            stmt.setString(8, newParent.getEmail());
            stmt.setDate(9, java.sql.Date.valueOf(newParent.getDOB()));
            stmt.setBoolean(10, newParent.isArchived());

            rowsCount = stmt.executeUpdate();
            System.out.println("Rows INSERTED into Parents: " + rowsCount);
            stmt.close();

            closeConnection();

            return true;
        } catch (Exception e) {
            e.printStackTrace();

            closeConnection();

            return false;
        }
    }

    public boolean insertSwimmer(swimmerModel newSwimmer) {
        openConnection();

        PreparedStatement stmt;
        String sqlStr = "INSERT INTO Swimmers(FamilyGroupID, SwimmerUsername, SwimmerPassword, SwimmerName, AreTheyMale, SwimmerAddress, SwimmerContactNumber, SwimmerEmail, SwimmerDateOfBirth, Archived) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int rowsCount;

        try {
            stmt = con.prepareStatement(sqlStr);
            stmt.setInt(1, newSwimmer.getFamilyGroupID());
            stmt.setString(2, newSwimmer.getUsername());
            stmt.setString(3, newSwimmer.getPassword());
            stmt.setString(4, newSwimmer.getName());
            stmt.setBoolean(5, newSwimmer.getAreTheyMale());
            stmt.setString(6, newSwimmer.getAddress());
            stmt.setString(7, newSwimmer.getContactNumber());
            stmt.setString(8, newSwimmer.getEmail());
            stmt.setDate(9, java.sql.Date.valueOf(newSwimmer.getDOB()));
            stmt.setBoolean(10, newSwimmer.isArchived());

            rowsCount = stmt.executeUpdate();
            System.out.println("Rows INSERTED into Swimmers: " + rowsCount);
            stmt.close();

            closeConnection();

            return true;
        } catch (Exception e) {
            e.printStackTrace();

            closeConnection();

            return false;
        }
    }

    public boolean updateEventResults(eventResultModel newEventResult) {
        openConnection();

        PreparedStatement stmt;
        String sqlStr = "UPDATE EventResults SET EventID = ?, SwimmerName = ?, EventResultRound = ?, EventResultLane = ?, HaveTheyFinished = ?, EventResultMinutes = ?, EventResultSeconds = ?, EventResultMiliseconds = ?, "
                + "EventResultReason = ? WHERE EventResultID = ?";
        int rowsCount;

        try {
            stmt = con.prepareStatement(sqlStr);
            stmt.setInt(1, newEventResult.getEventID());
            stmt.setString(2, newEventResult.getSwimmerName());
            stmt.setInt(3, newEventResult.getRound());
            stmt.setInt(4, newEventResult.getLaneNumber());
            stmt.setBoolean(5, newEventResult.isHaveTheyFinished());
            stmt.setInt(6, newEventResult.getMinutes());
            stmt.setInt(7, newEventResult.getSeconds());
            stmt.setInt(8, newEventResult.getMiliseconds());
            stmt.setString(9, newEventResult.getReason());
            stmt.setInt(10, newEventResult.getEventResultID());

            rowsCount = stmt.executeUpdate();
            System.out.println("Rows UPDATED in EventResults: " + rowsCount);
            stmt.close();

            closeConnection();

            return true;
        } catch (Exception e) {
            e.printStackTrace();

            closeConnection();

            return false;
        }
    }

    public boolean updateEvents(eventModel newEvent) {
        openConnection();

        PreparedStatement stmt;
        String sqlStr = "UPDATE Events SET MeetingID = ?, EventMinAge = ?, EventMaxAge = ?, EventDistance = ?, EventStroke = ? WHERE EventID = ?";
        int rowsCount;

        try {
            stmt = con.prepareStatement(sqlStr);
            stmt.setInt(1, newEvent.getMeetingID());
            stmt.setInt(2, newEvent.getMinAge());
            stmt.setInt(3, newEvent.getMaxAge());
            stmt.setDouble(4, newEvent.getDistance());
            stmt.setString(5, newEvent.getStroke());
            stmt.setInt(6, newEvent.getEventID());

            rowsCount = stmt.executeUpdate();
            System.out.println("Rows UPDATED in Events: " + rowsCount);
            stmt.close();

            closeConnection();

            return true;
        } catch (Exception e) {
            e.printStackTrace();

            closeConnection();

            return false;
        }
    }

    public boolean updateFamilyGroups(familyGroupModel newFamilyGroup) {
        openConnection();

        PreparedStatement stmt;
        String sqlStr = "UPDATE FamilyGroups SET ParentOneID = ?, ParentTwoID = ?, SwimmerOneID = ?, SwimmerTwoID = ?, SwimmerThreeID = ?, SwimmerFourID = ?, SwimmerFiveID = ? WHERE FamilyGroupID = ?";
        int rowsCount;

        try {
            stmt = con.prepareStatement(sqlStr);
            stmt.setInt(1, newFamilyGroup.getParentOneID());
            stmt.setInt(2, newFamilyGroup.getParentTwoID());
            stmt.setInt(3, newFamilyGroup.getChildOneID());
            stmt.setInt(4, newFamilyGroup.getChildTwoID());
            stmt.setInt(5, newFamilyGroup.getChildThreeID());
            stmt.setInt(6, newFamilyGroup.getChildFourID());
            stmt.setInt(7, newFamilyGroup.getChildFiveID());
            stmt.setInt(8, newFamilyGroup.getFamilyGroupID());

            rowsCount = stmt.executeUpdate();
            System.out.println("Rows UPDATED in FamilyGroups: " + rowsCount);
            stmt.close();

            closeConnection();

            return true;
        } catch (Exception e) {
            e.printStackTrace();

            closeConnection();

            return false;
        }
    }

    public boolean updateMeetings(meetingModel newMeeting) {
        openConnection();

        PreparedStatement stmt;
        String sqlStr = "UPDATE Meetings SET MeetingName = ?, VenueName = ?, MeetingDate = ?, PoolLength = ? WHERE MeetingID = ?";
        int rowsCount;

        try {
            stmt = con.prepareStatement(sqlStr);
            stmt.setString(1, newMeeting.getMeetingName());
            stmt.setString(2, newMeeting.getVenueName());
            stmt.setDate(3, java.sql.Date.valueOf(newMeeting.getMeetingDate()));
            stmt.setDouble(4, newMeeting.getPoolLength());
            stmt.setInt(5, newMeeting.getMeetingID());

            rowsCount = stmt.executeUpdate();
            System.out.println("Rows UPDATED in Meetings: " + rowsCount);
            stmt.close();

            closeConnection();

            return true;
        } catch (Exception e) {
            e.printStackTrace();

            closeConnection();

            return false;
        }
    }

    public boolean updateParents(parentModel newParent) {
        openConnection();

        PreparedStatement stmt;
        String sqlStr = "UPDATE Parents SET FamilyGroupID = ?, ParentUsername = ?, ParentPassword = ?, ParentName = ?, AreTheyMale = ?, ParentAddress = ?, ParentContactNumber = ?, ParentEmail = ?, ParentDateOfBirth = ?, Archived = ? WHERE ParentID = ?";
        int rowsCount;

        try {
            stmt = con.prepareStatement(sqlStr);
            stmt.setInt(1, newParent.getFamilyGroupID());
            stmt.setString(2, newParent.getUsername());
            stmt.setString(3, newParent.getPassword());
            stmt.setString(4, newParent.getName());
            stmt.setBoolean(5, newParent.getAreTheyMale());
            stmt.setString(6, newParent.getAddress());
            stmt.setString(7, newParent.getContactNumber());
            stmt.setString(8, newParent.getEmail());
            stmt.setDate(9, java.sql.Date.valueOf(newParent.getDOB()));
            stmt.setBoolean(10, newParent.isArchived());
            stmt.setInt(11, newParent.getParentID());

            rowsCount = stmt.executeUpdate();
            System.out.println("Rows UPDATED in Parents: " + rowsCount);
            stmt.close();

            closeConnection();

            return true;
        } catch (Exception e) {
            e.printStackTrace();

            closeConnection();

            return false;
        }
    }

    public boolean updateSwimmers(swimmerModel newSwimmer) {
        openConnection();

        PreparedStatement stmt;
        String sqlStr = "UPDATE Swimmers SET FamilyGroupID = ?, SwimmerUsername = ?, SwimmerPassword = ?, SwimmerName = ?, AreTheyMale = ?, SwimmerAddress = ?, SwimmerContactNumber = ?, SwimmerEmail = ?, SwimmerDateOfBirth = ?, Archived = ? WHERE SwimmerID = ?";
        int rowsCount;

        try {
            stmt = con.prepareStatement(sqlStr);
            stmt.setInt(1, newSwimmer.getFamilyGroupID());
            stmt.setString(2, newSwimmer.getUsername());
            stmt.setString(3, newSwimmer.getPassword());
            stmt.setString(4, newSwimmer.getName());
            stmt.setBoolean(5, newSwimmer.getAreTheyMale());
            stmt.setString(6, newSwimmer.getAddress());
            stmt.setString(7, newSwimmer.getContactNumber());
            stmt.setString(8, newSwimmer.getEmail());
            stmt.setDate(9, java.sql.Date.valueOf(newSwimmer.getDOB()));
            stmt.setBoolean(10, newSwimmer.isArchived());
            stmt.setInt(11, newSwimmer.getSwimmerID());

            rowsCount = stmt.executeUpdate();
            System.out.println("Rows UPDATED in Swimmers: " + rowsCount);
            stmt.close();

            closeConnection();

            return true;
        } catch (Exception e) {
            e.printStackTrace();

            closeConnection();

            return false;
        }
    }

    public eventResultModel queryEventResultByID(int eventResultID) {
        openConnection();

        PreparedStatement stmt;
        String sqlStr = "SELECT * FROM EventResults WHERE EventResultID = ?";
        ResultSet rs;
        eventResultModel newEventResult = null;
        
        try {
            stmt = con.prepareStatement(sqlStr);
            stmt.setInt(1, eventResultID);
            rs = stmt.executeQuery();

            while (rs.next()) {
                newEventResult = new eventResultModel(rs.getInt("eventResultID"), rs.getInt("eventID"),
                        rs.getString("swimmerName"), rs.getInt("eventResultRound"), rs.getInt("eventResultLane"),
                        rs.getBoolean("haveTheyFinished"), rs.getInt("eventResultMinutes"), rs.getInt("eventResultSeconds"),
                        rs.getInt("eventResultMiliseconds"), rs.getString("eventResultReason"));
            }
            rs.close();
            stmt.close();

            closeConnection();

            return newEventResult;
        } catch (Exception e) {
            e.printStackTrace();

            closeConnection();

            return null;
        }
    }

    public eventModel queryEventsByID(int eventID) {
        openConnection();

        PreparedStatement stmt;
        String sqlStr = "SELECT * FROM Events WHERE EventID = ?";
        ResultSet rs;
        eventModel newEvent = null;
        
        try {
            stmt = con.prepareStatement(sqlStr);
            stmt.setInt(1, eventID);
            rs = stmt.executeQuery();

            while (rs.next()) {
                newEvent = new eventModel(rs.getInt("eventID"), rs.getInt("meetingID"), rs.getInt("eventMinAge"), 
                        rs.getInt("eventMaxAge"), rs.getInt("eventDistance"), rs.getString("eventStroke"), 
                        queryEventResultsByEventID(eventID));
            }
            rs.close();
            stmt.close();

            closeConnection();

            return newEvent;
        } catch (Exception e) {
            e.printStackTrace();

            closeConnection();

            return null;
        }
    }

    public familyGroupModel queryFamilyGroupByID(int familyGroupID) {
        openConnection();

        PreparedStatement stmt;
        String sqlStr = "SELECT * FROM FamilyGroups WHERE FamilyGroupID = ?";
        ResultSet rs;
        familyGroupModel newFamilyGroup = null;
        
        try {
            stmt = con.prepareStatement(sqlStr);
            stmt.setInt(1, familyGroupID);
            rs = stmt.executeQuery();

            while (rs.next()) {
                newFamilyGroup = new familyGroupModel(rs.getInt("familyGroupID"), rs.getInt("parentOneID"), 
                        rs.getInt("parentTwoID"), rs.getInt("swimmerOneID"), rs.getInt("swimmerTwoID"), 
                        rs.getInt("swimmerThreeID"), rs.getInt("swimmerFourID"), rs.getInt("swimmerFiveID"));
            }
            rs.close();
            stmt.close();

            closeConnection();

            return newFamilyGroup;
        } catch (Exception e) {
            e.printStackTrace();

            closeConnection();

            return null;
        }
    }

    public meetingModel queryMeetingByID(int meetingID) {
        openConnection();

        PreparedStatement stmt;
        String sqlStr = "SELECT * FROM Meetings WHERE MeetingID = ?";
        ResultSet rs;
        meetingModel newMeeting = null;

        try {
            stmt = con.prepareStatement(sqlStr);
            stmt.setInt(1, meetingID);
            rs = stmt.executeQuery();

            while (rs.next()) {
                newMeeting = new meetingModel(rs.getInt("meetingID"), rs.getString("meetingName"), rs.getString("venueName"), 
                        rs.getDate("meetingDate").toLocalDate(), rs.getDouble("poolLength"), queryEventsByMeetingID(meetingID));
            }
            rs.close();
            stmt.close();

            closeConnection();

            return newMeeting;
        } catch (Exception e) {
            e.printStackTrace();

            closeConnection();

            return null;
        }
    }

    public parentModel queryParentByID(int parentID) {
        openConnection();

        PreparedStatement stmt;
        String sqlStr = "SELECT * FROM Parents WHERE ParentID = ?";
        ResultSet rs;
        parentModel newParent = null;

        try {
            stmt = con.prepareStatement(sqlStr);
            stmt.setInt(1, parentID);
            rs = stmt.executeQuery();

            while (rs.next()) {
                newParent = new parentModel(rs.getInt("parentID"), rs.getInt("familyGroupID"), rs.getString("parentUsername"), 
                        rs.getString("parentPassword"), rs.getString("parentName"), rs.getBoolean("areTheyMale"), 
                        rs.getString("parentAddress"), rs.getString("parentContactNumber"), rs.getString("parentEmail"),
                        rs.getDate("parentDateOfBirth").toLocalDate(), rs.getBoolean("Archived"));
            }
            rs.close();
            stmt.close();

            closeConnection();

            return newParent;
        } catch (Exception e) {
            e.printStackTrace();

            closeConnection();

            return null;
        }
    }

    public swimmerModel querySwimmerByID(int swimmerID) {
        openConnection();

        PreparedStatement stmt;
        String sqlStr = "SELECT * FROM Swimmers WHERE SwimmerID = ?";
        ResultSet rs;
        swimmerModel newSwimmer = null;

        try {
            stmt = con.prepareStatement(sqlStr);
            stmt.setInt(1, swimmerID);
            rs = stmt.executeQuery();

            while (rs.next()) {
                newSwimmer = new swimmerModel(rs.getInt("swimmerID"), rs.getInt("familyGroupID"), 
                        rs.getString("swimmerUsername"), rs.getString("swimmerPassword"), rs.getString("swimmerName"), 
                        rs.getBoolean("areTheyMale"), rs.getString("swimmerAddress"), rs.getString("swimmerContactNumber"), 
                        rs.getString("swimmerEmail"), rs.getDate("swimmerDateOfBirth").toLocalDate(), rs.getBoolean("Archived"));
            }
            rs.close();
            stmt.close();

            closeConnection();

            return newSwimmer;
        } catch (Exception e) {
            e.printStackTrace();

            closeConnection();

            return null;
        }
    }

    public ArrayList<eventResultModel> queryAllEventResults() {
        openConnection();

        PreparedStatement stmt;
        String sqlStr = "SELECT * FROM EventResults";
        ResultSet rs;
        ArrayList<eventResultModel> eventResults = new ArrayList<>();

        try {
            stmt = con.prepareStatement(sqlStr);
            rs = stmt.executeQuery();

            while (rs.next()) {
                eventResultModel newEventResult = new eventResultModel(rs.getInt("eventResultID"), rs.getInt("eventID"),
                        rs.getString("swimmerName"), rs.getInt("eventResultRound"), rs.getInt("eventResultLane"),
                        rs.getBoolean("haveTheyFinished"), rs.getInt("eventResultMinutes"), rs.getInt("eventResultSeconds"),
                        rs.getInt("eventResultMiliseconds"), rs.getString("eventResultReason"));
                
                eventResults.add(newEventResult);
            }
            rs.close();
            stmt.close();

            closeConnection();

            return eventResults;
        } catch (Exception e) {
            e.printStackTrace();

            closeConnection();

            return null;
        }
    }

    public ArrayList<eventModel> queryAllEvents() {
        openConnection();

        PreparedStatement stmt;
        String sqlStr = "SELECT * FROM Events";
        ResultSet rs;
        ArrayList<eventModel> events = new ArrayList<>();

        try {
            stmt = con.prepareStatement(sqlStr);
            rs = stmt.executeQuery();

            while (rs.next()) {
                eventModel newEvent = new eventModel(rs.getInt("eventID"), rs.getInt("meetingID"), rs.getInt("eventMinAge"), 
                        rs.getInt("eventMaxAge"), rs.getInt("eventDistance"), rs.getString("eventStroke"), 
                        queryEventResultsByEventID(rs.getInt("eventID")));
                
                events.add(newEvent);
            }
            rs.close();
            stmt.close();

            closeConnection();

            return events;
        } catch (Exception e) {
            e.printStackTrace();

            closeConnection();

            return null;
        }
    }

    public ArrayList<familyGroupModel> queryAllFamilyGroups() {
        openConnection();

        PreparedStatement stmt;
        String sqlStr = "SELECT * FROM FamilyGroups";
        ResultSet rs;
        ArrayList<familyGroupModel> familyGroups = new ArrayList<>();

        try {
            stmt = con.prepareStatement(sqlStr);
            rs = stmt.executeQuery();

            while (rs.next()) {
                familyGroupModel newFamilyGroup = new familyGroupModel(rs.getInt("familyGroupID"), rs.getInt("parentOneID"), 
                        rs.getInt("parentTwoID"), rs.getInt("swimmerOneID"), rs.getInt("swimmerTwoID"), 
                        rs.getInt("swimmerThreeID"), rs.getInt("swimmerFourID"), rs.getInt("swimmerFiveID"));
                
                familyGroups.add(newFamilyGroup);
            }
            rs.close();
            stmt.close();

            closeConnection();

            return familyGroups;
        } catch (Exception e) {
            e.printStackTrace();

            closeConnection();

            return null;
        }
    }

    public ArrayList<meetingModel> queryAllMeetings() {
        openConnection();

        PreparedStatement stmt;
        String sqlStr = "SELECT * FROM Meetings";
        ResultSet rs;
        ArrayList<meetingModel> meetings = new ArrayList<>();

        try {
            stmt = con.prepareStatement(sqlStr);
            rs = stmt.executeQuery();

            while (rs.next()) {
                meetingModel newMeeting = new meetingModel(rs.getInt("meetingID"), rs.getString("meetingName"), 
                        rs.getString("venueName"), rs.getDate("meetingDate").toLocalDate(), 
                        rs.getDouble("poolLength"), queryEventsByMeetingID(rs.getInt("meetingID")));
                
                meetings.add(newMeeting);
            }
            rs.close();
            stmt.close();

            closeConnection();

            return meetings;
        } catch (Exception e) {
            e.printStackTrace();

            closeConnection();

            return null;
        }
    }

    public ArrayList<parentModel> queryAllParents() {
        openConnection();

        PreparedStatement stmt;
        String sqlStr = "SELECT * FROM Parents";
        ResultSet rs;
        ArrayList<parentModel> parents = new ArrayList<>();

        try {
            stmt = con.prepareStatement(sqlStr);
            rs = stmt.executeQuery();

            while (rs.next()) {
                parentModel newParent = new parentModel(rs.getInt("parentID"), rs.getInt("familyGroupID"), rs.getString("parentUsername"), 
                        rs.getString("parentPassword"), rs.getString("parentName"), rs.getBoolean("areTheyMale"), 
                        rs.getString("parentAddress"), rs.getString("parentContactNumber"), rs.getString("parentEmail"),
                        rs.getDate("parentDateOfBirth").toLocalDate(), rs.getBoolean("Archived"));
                
                parents.add(newParent);
            }
            rs.close();
            stmt.close();

            closeConnection();

            return parents;
        } catch (Exception e) {
            e.printStackTrace();

            closeConnection();

            return null;
        }
    }

    public ArrayList<swimmerModel> queryAllSwimmers() {
        openConnection();

        PreparedStatement stmt;
        String sqlStr = "SELECT * FROM Swimmers";
        ResultSet rs;
        ArrayList<swimmerModel> swimmers = new ArrayList<>();

        try {
            stmt = con.prepareStatement(sqlStr);
            rs = stmt.executeQuery();

            while (rs.next()) {
                swimmerModel newSwimmer = new swimmerModel(rs.getInt("swimmerID"), rs.getInt("familyGroupID"), 
                        rs.getString("swimmerUsername"), rs.getString("swimmerPassword"), rs.getString("swimmerName"), 
                        rs.getBoolean("areTheyMale"), rs.getString("swimmerAddress"), rs.getString("swimmerContactNumber"), 
                        rs.getString("swimmerEmail"), rs.getDate("swimmerDateOfBirth").toLocalDate(), rs.getBoolean("Archived"));
                
                swimmers.add(newSwimmer);
            }
            rs.close();
            stmt.close();

            closeConnection();

            return swimmers;
        } catch (Exception e) {
            e.printStackTrace();

            closeConnection();

            return null;
        }
    }
    
    //↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ FROM HERE ON DOWNARDS IT IS SPECIFIC METHODS INSTEAD OF CRUD OPERATIONS ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
    
    public clubOfficialModel authenticateClubOfficial(String username, String password)
    {
        openConnection();

        PreparedStatement stmt;
        String sqlStr = "SELECT * FROM ClubOfficials WHERE ClubOfficialUsername = ? AND ClubOfficialPassword = ?";
        ResultSet rs;
        clubOfficialModel newClubOfficial = null;
        
        try {
            stmt = con.prepareStatement(sqlStr);
            stmt.setString(1, username);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

            while (rs.next()) {
                newClubOfficial = new clubOfficialModel(rs.getInt("clubOfficialID"), username, password, rs.getString("ClubOfficialName"), rs.getBoolean("areTheyMale"), 
                        rs.getString("clubOfficialAddress"), rs.getString("clubOfficialContactNumber"), rs.getString("clubOfficialEmail"), 
                        rs.getDate("clubOfficialDateOfBirth").toLocalDate(), rs.getBoolean("Archived"));
            }
            rs.close();
            stmt.close();

            closeConnection();

            return newClubOfficial;
        } catch (Exception e) {
            e.printStackTrace();

            closeConnection();

            return null;
        }
    }
    public parentModel authenticateParent(String username, String password)
    {
        openConnection();

        PreparedStatement stmt;
        String sqlStr = "SELECT * FROM Parents WHERE parentUsername = ? AND parentPassword = ?";
        ResultSet rs;
        parentModel newParent = null;
        
        try {
            stmt = con.prepareStatement(sqlStr);
            stmt.setString(1, username);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

            while (rs.next()) {
                newParent = new parentModel(rs.getInt("parentID"), rs.getInt("familyGroupID"), username, password, rs.getString("parentName"), rs.getBoolean("areTheyMale"), 
                        rs.getString("parentAddress"), rs.getString("parentContactNumber"), rs.getString("parentEmail"), rs.getDate("parentDateOfBirth").toLocalDate(), rs.getBoolean("Archived"));
            }
            rs.close();
            stmt.close();

            closeConnection();

            return newParent;
        } catch (Exception e) {
            e.printStackTrace();

            closeConnection();

            return null;
        }
    }
    
    public swimmerModel authenticateSwimmer(String username, String password)
    {
        openConnection();

        PreparedStatement stmt;
        String sqlStr = "SELECT * FROM Swimmers WHERE SwimmerUsername = ? AND SwimmerPassword = ?";
        ResultSet rs;
        swimmerModel newSwimmerModel = null;
        
        try {
            stmt = con.prepareStatement(sqlStr);
            stmt.setString(1, username);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

            while (rs.next()) {
                newSwimmerModel = new swimmerModel(rs.getInt("swimmerID"), rs.getInt("familyGroupID"), username, password, rs.getString("swimmerName"), rs.getBoolean("areTheyMale"), 
                        rs.getString("swimmerAddress"), rs.getString("swimmerContactNumber"), rs.getString("swimmerEmail"), 
                        rs.getDate("swimmerDateOfBirth").toLocalDate(), rs.getBoolean("Archived"));
            }
            rs.close();
            stmt.close();

            closeConnection();

            return newSwimmerModel;
        } catch (Exception e) {
            e.printStackTrace();

            closeConnection();

            return null;
        }
    }
    
    //These methods is for the creation of objects within the query statements
    
    private ArrayList<eventResultModel> queryEventResultsByEventID(int eventID)
    {
        openConnection();

        PreparedStatement stmt;
        String sqlStr = "SELECT * FROM EventResults WHERE EventID = ?";
        ResultSet rs;
        ArrayList<eventResultModel> eventResults = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement(sqlStr);
            stmt.setInt(1, eventID);
            rs = stmt.executeQuery();

            while (rs.next()) {
                eventResultModel newEventResult = new eventResultModel(rs.getInt("eventResultID"), rs.getInt("eventID"),
                        rs.getString("swimmerName"), rs.getInt("eventResultRound"), rs.getInt("eventResultLane"),
                        rs.getBoolean("haveTheyFinished"), rs.getInt("eventResultMinutes"), rs.getInt("eventResultSeconds"),
                        rs.getInt("eventResultMiliseconds"), rs.getString("eventResultReason"));
                
                eventResults.add(newEventResult);
            }
            rs.close();
            stmt.close();

            closeConnection();

            return eventResults;
        } catch (Exception e) {
            e.printStackTrace();

            closeConnection();

            return null;
        }
    }
    
    private ArrayList<eventModel> queryEventsByMeetingID(int meetingID)
    {
        openConnection();

        PreparedStatement stmt;
        String sqlStr = "SELECT * FROM Events WHERE meetingID = ?";
        ResultSet rs;
        ArrayList<eventModel> events = new ArrayList<>();

        try {
            stmt = con.prepareStatement(sqlStr);
            stmt.setInt(1, meetingID);
            rs = stmt.executeQuery();

            while (rs.next()) {
                eventModel newEvent = new eventModel(rs.getInt("eventID"), rs.getInt("meetingID"), rs.getInt("eventMinAge"), 
                        rs.getInt("eventMaxAge"), rs.getInt("eventDistance"), rs.getString("eventStroke"), 
                        queryEventResultsByEventID(rs.getInt("eventID")));
                
                events.add(newEvent);
            }
            rs.close();
            stmt.close();

            closeConnection();

            return events;
        } catch (Exception e) {
            e.printStackTrace();

            closeConnection();

            return null;
        }
    }

    //Cleans up the database after testing so there isnt too much dummy data in the database;
    public void cleanUpTests() {
        openConnection();

        PreparedStatement stmt;
        String sqlStr = "DELETE FROM ClubOfficials WHERE NOT ClubOfficialID = 1";
        PreparedStatement stmt1;
        String sqlStr1 = "DELETE FROM EventResults WHERE NOT EventResultID = 1";
        PreparedStatement stmt2;
        String sqlStr2 = "DELETE FROM Events WHERE NOT EventID = 1";
        PreparedStatement stmt3;
        String sqlStr3 = "DELETE FROM FamilyGroups WHERE NOT FamilyGroupID = 1";
        PreparedStatement stmt4;
        String sqlStr4 = "DELETE FROM Meetings WHERE NOT MeetingID = 1";
        PreparedStatement stmt5;
        String sqlStr5 = "DELETE FROM Parents WHERE NOT ParentID = 1";
        PreparedStatement stmt6;
        String sqlStr6 = "DELETE FROM Swimmers WHERE NOT SwimmerID = 1";

        try {
            stmt = con.prepareStatement(sqlStr);
            stmt1 = con.prepareStatement(sqlStr1);
            stmt2 = con.prepareStatement(sqlStr2);
            stmt3 = con.prepareStatement(sqlStr3);
            stmt4 = con.prepareStatement(sqlStr4);
            stmt5 = con.prepareStatement(sqlStr5);
            stmt6 = con.prepareStatement(sqlStr6);

            stmt.executeUpdate();
            stmt1.executeUpdate();
            stmt2.executeUpdate();
            stmt3.executeUpdate();
            stmt4.executeUpdate();
            stmt5.executeUpdate();
            stmt6.executeUpdate();

            stmt.close();
            stmt1.close();
            stmt2.close();
            stmt3.close();
            stmt4.close();
            stmt5.close();
            stmt6.close();

            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();

            closeConnection();
        }
    }

    //Creates a connection to the database
    private void openConnection() {
        try {
            DriverManager.registerDriver(
                    new org.apache.derby.jdbc.ClientDriver());
            con = DriverManager.getConnection(url, "user1", "user1");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Closes connection so not too many connections exist at once 
    private void closeConnection() {
        try {
            con = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
