
package Models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Objects;

//Meetings are organised by the swimmingClub where multiple race events happen

public class meetingModel {
    private int meetingID;
    private String meetingName;
    private String venueName;
    private LocalDate meetingDate;
    private double poolLength;
    private ArrayList<eventModel> meetingEvents;

    public meetingModel(int meetingID, String meetingName, String venueName, LocalDate meetingDate, double poolLength, ArrayList<eventModel> meetingEvents) {
        this.meetingID = meetingID;
        this.meetingName = meetingName;
        this.venueName = venueName;
        this.meetingDate = meetingDate;
        this.poolLength = poolLength;
        this.meetingEvents = meetingEvents;
    }

    public meetingModel(String meetingName, String venueName, LocalDate meetingDate, double poolLength, ArrayList<eventModel> meetingEvents) {
        this.meetingName = meetingName;
        this.venueName = venueName;
        this.meetingDate = meetingDate;
        this.poolLength = poolLength;
        this.meetingEvents = meetingEvents;
    }

    //Constructor for when meeting is first created
    public meetingModel(String meetingName, String venueName, LocalDate meetingDate, double poolLength) {
        this.meetingName = meetingName;
        this.venueName = venueName;
        this.meetingDate = meetingDate;
        this.poolLength = poolLength;
    }
    
    public int getMeetingID() {
        return meetingID;
    }

    public void setMeetingID(int meetingID) {
        this.meetingID = meetingID;
    }

    public String getMeetingName() {
        return meetingName;
    }

    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public LocalDate getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(LocalDate meetingDate) {
        this.meetingDate = meetingDate;
    }

    public double getPoolLength() {
        return poolLength;
    }

    public void setPoolLength(double poolLength) {
        this.poolLength = poolLength;
    }

    public ArrayList<eventModel> getMeetingEvents() {
        return meetingEvents;
    }

    public void setMeetingEvents(ArrayList<eventModel> meetingEvents) {
        this.meetingEvents = meetingEvents;
    }
    
    //Overriding equals method mostly for assertEquals tests
    @Override
    public boolean equals(Object o) {
        if (getClass() == o.getClass()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.meetingID;
        hash = 61 * hash + Objects.hashCode(this.meetingName);
        hash = 61 * hash + Objects.hashCode(this.venueName);
        hash = 61 * hash + Objects.hashCode(this.meetingDate);
        hash = 61 * hash + (int) (Double.doubleToLongBits(this.poolLength) ^ (Double.doubleToLongBits(this.poolLength) >>> 32));
        hash = 61 * hash + Objects.hashCode(this.meetingEvents);
        return hash;
    }
    
    //Overriding to string method so its easier to read
    @Override
    public String toString()
    {
        return  "\nMeeting ID: " + meetingID + "\n" +
                "Meeting name: " + meetingName + "\n" + 
                "Venue name: " + venueName + "\n" +
                "Meeting date: " + meetingDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)) + "\n" +
                "Meeting pool length: " + poolLength;
    }
}
