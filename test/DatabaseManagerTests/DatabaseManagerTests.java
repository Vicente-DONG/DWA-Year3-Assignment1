
package DatabaseManagerTests;

import Database.databaseManager;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import Models.*;

public class DatabaseManagerTests {
    databaseManager dbManager = new databaseManager();
    
    @Test
    public void testInsertEventResult()
    {
        eventResultModel newEventResult = new eventResultModel(1, "Jack", 1, 1, true, 12, 3, 265, "");
        
        boolean worked = dbManager.insertEventResults(newEventResult);
        
        assertEquals(true, worked);
    }
    
    @Test
    public void testInsertEvent()
    {
        ArrayList<eventResultModel> eventResults = new ArrayList<>();
        eventModel newEvent = new eventModel(1, 8, 13, 10.50, "Butterfly", eventResults);
        
        boolean worked = dbManager.insertEvents(newEvent);
        
        assertEquals(true, worked);
    }
    
    @Test
    public void testInsertFamilyGroup()
    {
        familyGroupModel newFamilyGroup = new familyGroupModel(1, 0, 1, 0, 0, 0, 0);
        
        boolean worked = dbManager.insertFamilyGroup(newFamilyGroup);
        
        assertEquals(true, worked);
    }
    
    @Test
    public void testInsertMeeting()
    {
        ArrayList<eventModel> meetingEvents = new ArrayList<>();
        meetingModel newMeeting = new meetingModel("testMeeting", "testVenue", LocalDate.now(), 20.75, meetingEvents);
        
        boolean worked = dbManager.insertMeeting(newMeeting);
        
        assertEquals(true, worked);
    }
    
    @Test
    public void testInsertParent()
    {
        parentModel newParent = new parentModel(1, "parent", "parent", "Sarah", false, "Sarah's address n1", "00000001234", "sarah@parent.com", LocalDate.of(1954, 10, 25), false);
        
        boolean worked = dbManager.insertParent(newParent);
        
        assertEquals(true, worked);
    }
    
    @Test
    public void testInsertSwimmer()
    {
        swimmerModel newSwimmer = new swimmerModel(1, "swimmer", "swimmer", "Johnathan", true, "Joanathan street n1", "01205348253", "jonathan@swimmer.com", LocalDate.of(1996, 3, 3), false);
        
        boolean worked = dbManager.insertSwimmer(newSwimmer);
        
        assertEquals(true, worked);
    }
    
    @Test
    public void testUpdateEventResults()
    {
        eventResultModel newEventResult = new eventResultModel(1, 1, "test", 3, 6, true, 12, 56, 899, "");
        eventResultModel oldEventResult = new eventResultModel(1, 1, "Jack", 1, 1, true, 2, 13, 275, "");
        
        boolean worked = dbManager.updateEventResults(newEventResult);
        
        assertEquals(true, worked);
        
        dbManager.updateEventResults(oldEventResult);
    }
    
    @Test
    public void testUpdateEvents()
    {
        ArrayList<eventResultModel> eventResults = new ArrayList<>();
        eventResults.add(new eventResultModel(1, 1, "Jack", 1, 1, true, 2, 13, 275, ""));
        
        eventModel newEvent = new eventModel(1, 1, 23, 25, 50.20, "Freestyle", eventResults);
        eventModel oldEvent = new eventModel(1, 1, 12, 15, 15, "Butterfly", eventResults);
        
        boolean worked = dbManager.updateEvents(newEvent);
        
        assertEquals(true, worked);
        
        dbManager.updateEvents(oldEvent);
    }
    
    @Test
    public void testUpdateFamilyGroups()
    {
        familyGroupModel newFamilyGroup = new familyGroupModel(1, 1, 1, 1, 1, 1, 1, 1);
        familyGroupModel oldFamilyGroup = new familyGroupModel(1, 1, 0, 1, 0, 0, 0, 0);
        
        boolean worked = dbManager.updateFamilyGroups(newFamilyGroup);
        
        assertEquals(true, worked);
        
        dbManager.updateFamilyGroups(oldFamilyGroup);
    }
    
    @Test
    public void testUpdateMeetings()
    {
        ArrayList<eventModel> meetingEvents = new ArrayList<>();
        
        meetingModel newMeeting = new meetingModel(1, "test", "test", LocalDate.now(), 10, meetingEvents);
        meetingModel oldMeeting = new meetingModel(1, "XMAS meeting 2021", "Sydney Opera House", LocalDate.of(2021, 12, 15), 20.5, meetingEvents);
        
        boolean worked = dbManager.updateMeetings(newMeeting);
        
        dbManager.updateMeetings(oldMeeting);
    }
    
    @Test
    public void testUpdateParent()
    {
        parentModel newParent = new parentModel(1, 1, "test", "test", "test", true, "test", "test", "test@test.com", LocalDate.now(), false);
        parentModel oldParent = new parentModel(1, 1, "parent1", "parent1", "Alex", true, "Alex Street n1", "00000000025", "alex@parent.com", LocalDate.of(1977, 1, 28), false);
        
        boolean worked = dbManager.updateParents(newParent);
        
        dbManager.updateParents(oldParent);
    }
    
    @Test
    public void testUpdateSwimmers()
    {
        swimmerModel newSwimmer = new swimmerModel(1, 1, "test", "test", "test", true, "test", "test", "test@test.com", LocalDate.now(), false);
        swimmerModel oldSwimmer = new swimmerModel(1, 1, "swimmer1", "swimmer1", "Jack", true, "Jack Street n1", "00000000001", "jack@swimmer.com", LocalDate.of(2008, 9, 23), false);
        
        boolean worked = dbManager.updateSwimmers(newSwimmer);
        
        dbManager.updateSwimmers(oldSwimmer);
    }
    
    @Test
    public void testQueryEventResultByID()
    {
        eventResultModel expectedEventResult = new eventResultModel(1, 1, "Jack", 1, 1, true, 2, 13, 275, "");
        
        eventResultModel actualEventModel = dbManager.queryEventResultByID(1);
        
        assertEquals(expectedEventResult, actualEventModel);
    }
    
    @Test
    public void testQueryEventsByID()
    {
        eventResultModel expectedEventResult = new eventResultModel(1, 1, "Jack", 1, 1, true, 2, 13, 275, "");
        ArrayList<eventResultModel> expectedArray = new ArrayList<>();
        expectedArray.add(expectedEventResult);
        
        eventModel expectedEvent = new eventModel(1, 1, 12, 15, 15, "Butterfly", expectedArray);
        
        eventModel actualEvent = dbManager.queryEventsByID(1);
        
        assertEquals(expectedEvent, actualEvent);
    }
    
    @Test
    public void testQueryFamilyGroupsByID()
    {
        familyGroupModel expectedFamilyGroup = new familyGroupModel(1, 1, 0, 1, 0, 0, 0, 0);
        
        familyGroupModel actualFamilyGroup = dbManager.queryFamilyGroupByID(1);
        
        assertEquals(expectedFamilyGroup, actualFamilyGroup);
    }
    
    @Test
    public void testQueryMeetingsByID()
    {
        eventResultModel expectedEventResult = new eventResultModel(1, 1, "Jack", 1, 1, true, 2, 13, 275, "");
        ArrayList<eventResultModel> expectedEventResultArray = new ArrayList<>();
        expectedEventResultArray.add(expectedEventResult);
        
        eventModel expectedEvent = new eventModel(1, 1, 12, 15, 15, "Butterfly", expectedEventResultArray);
        ArrayList<eventModel> expectedEventArray = new ArrayList<>();
        expectedEventArray.add(expectedEvent);
        
        meetingModel expectedMeeting = new meetingModel(1, "XMAS meeting 2021", "Sydney Opera House", LocalDate.of(2021, 12, 15), 20.5, expectedEventArray);
        
        meetingModel actualMeeting = dbManager.queryMeetingByID(1);
        
        assertEquals(expectedMeeting, actualMeeting);
    }
    
    @Test
    public void testQueryParentsByID()
    {
        parentModel expectedParent = new parentModel(1, 1, "parent1", "parent1", "Alex", true, "Alex Street n1", "00000000025", "alex@parent.com", LocalDate.of(1977, 1, 28), false);
        
        parentModel actualParent = dbManager.queryParentByID(1);
        
        assertEquals(expectedParent, actualParent);
    }
    
    @Test
    public void testQuerySwimmersByID()
    {
        swimmerModel expectedSwimmer = new swimmerModel(1, 1, "swimmer1", "swimmer1", "Jack", true, "Jack Street n1", "00000000001", "jack@swimmer.com", LocalDate.of(2008, 9, 23), false);
        
        swimmerModel actualSwimmer = dbManager.querySwimmerByID(1);
        
        assertEquals(expectedSwimmer, actualSwimmer);
    }
    
    @Test
    public void testQueryAllEventResults()
    {
        eventResultModel expectedEventResult = new eventResultModel(1, 1, "Jack", 1, 1, true, 2, 13, 275, "");
        ArrayList<eventResultModel> expectedArray = new ArrayList<>();
        
        expectedArray.add(expectedEventResult);
        
        ArrayList<eventResultModel> actualArray = dbManager.queryAllEventResults();
        
        assertArrayEquals(expectedArray.toArray(), actualArray.toArray());
    }
    
    @Test
    public void testQueryAllEvents()
    {
        eventResultModel expectedEventResult = new eventResultModel(1, 1, "Jack", 1, 1, true, 2, 13, 275, "");
        ArrayList<eventResultModel> expectedEventResultArray = new ArrayList<>();
        expectedEventResultArray.add(expectedEventResult);
        
        eventModel expectedEvent = new eventModel(1, 1, 12, 15, 15, "Butterfly", expectedEventResultArray);
        ArrayList<eventModel> expectedArray = new ArrayList<>();
        
        expectedArray.add(expectedEvent);
        
        ArrayList<eventModel> actualArray = dbManager.queryAllEvents();
        
        assertArrayEquals(expectedArray.toArray(), actualArray.toArray());
    }
    
    @Test
    public void testQueryAllFamilyGroups()
    {
        ArrayList<familyGroupModel> expectedArray = new ArrayList<>();
        familyGroupModel expectedFamilyGroup = new familyGroupModel(1, 1, 0, 1, 0, 0, 0, 0);
        
        expectedArray.add(expectedFamilyGroup);
        
        ArrayList<familyGroupModel> actualArray = dbManager.queryAllFamilyGroups();
        
        assertEquals(expectedArray, actualArray);
    }
    
    @Test
    public void testQueryAllMeetings()
    {
        eventResultModel expectedEventResult = new eventResultModel(1, 1, "Jack", 1, 1, true, 2, 13, 275, "");
        ArrayList<eventResultModel> expectedEventResultArray = new ArrayList<>();
        expectedEventResultArray.add(expectedEventResult);
        
        eventModel expectedEvent = new eventModel(1, 1, 12, 15, 15, "Butterfly", expectedEventResultArray);
        ArrayList<eventModel> expectedEventArray = new ArrayList<>();
        expectedEventArray.add(expectedEvent);
        
        meetingModel expectedMeeting = new meetingModel(1, "XMAS meeting 2021", "Sydney Opera House", LocalDate.of(2021, 12, 15), 20.5, expectedEventArray);
        ArrayList<meetingModel> expectedArray = new ArrayList<>();
        expectedArray.add(expectedMeeting);
        
        ArrayList<meetingModel> actualArray = dbManager.queryAllMeetings();
        
        assertArrayEquals(expectedArray.toArray(), actualArray.toArray());
    }
    
    @Test
    public void testQueryAllParents()
    {
        parentModel expectedParent = new parentModel(1, 1, "parent1", "parent1", "Alex", true, "Alex Street n1", "00000000025", "alex@swimmer.com", LocalDate.of(1977, 1, 28), false);
        ArrayList<parentModel> expectedArray = new ArrayList<>();
        expectedArray.add(expectedParent);
        
        ArrayList<parentModel> actualArray = dbManager.queryAllParents();
        
        assertArrayEquals(expectedArray.toArray(), actualArray.toArray());
    }
    
    @Test
    public void testQueryAllSwimmers()
    {
        swimmerModel expectedSwimmer = new swimmerModel(1, 1, "swimmer1", "swimmer1", "Jack", true, "Jack Street n1", "00000000001", "jack@swimmer.com", LocalDate.of(2008, 9, 23), false);
        ArrayList<swimmerModel> expectedArray = new ArrayList<>();
        expectedArray.add(expectedSwimmer);
        
        ArrayList<swimmerModel> actualArray = dbManager.queryAllSwimmers();
        
        assertArrayEquals(expectedArray.toArray(), actualArray.toArray());
    }
    
    //Method to clean after tests
    @After
    public void cleanUpTests()
    {
        dbManager.cleanUpTests();;
    }
}
