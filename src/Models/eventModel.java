
package Models;

import java.util.ArrayList;
import java.util.Objects;

//Event has a many to one relationship to Meeting and also a many to one relationship with eventResults. An event will have one result for each lane that competed in the event
//hence to get all the results of an event you need to retrieve eventResult

public class eventModel {
    private int eventID;
    private int meetingID;
    private int minAge;
    private int maxAge;
    private double distance;
    private String stroke;
    private ArrayList<eventResultModel> eventResult;

    //This constructor is only for database use
    public eventModel(int eventID, int meetingID, int minAge, int maxAge, double distance, String stroke, ArrayList<eventResultModel> eventResult) {
        this.eventID = eventID;
        this.meetingID = meetingID;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.distance = distance;
        this.stroke = stroke;
        this.eventResult = eventResult;
    }
    
    //This constructor is for environment instantiation 
    public eventModel(int meetingID, int minAge, int maxAge, double distance, String stroke, ArrayList<eventResultModel> eventResult) {
        this.meetingID = meetingID;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.distance = distance;
        this.stroke = stroke;
        this.eventResult = eventResult;
    }


    //Standard getters and setters
    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public int getMeetingID() {
        return meetingID;
    }

    public void setMeetingID(int meetingID) {
        this.meetingID = meetingID;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getStroke() {
        return stroke;
    }

    public void setStroke(String stroke) {
        this.stroke = stroke;
    }

    public ArrayList<eventResultModel> getEventResult() {
        return eventResult;
    }

    public void setEventResult(ArrayList<eventResultModel> eventResult) {
        this.eventResult = eventResult;
    }
    
    //Override equals method, mostly for testing
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
        int hash = 5;
        hash = 97 * hash + this.eventID;
        hash = 97 * hash + this.meetingID;
        hash = 97 * hash + this.minAge;
        hash = 97 * hash + this.maxAge;
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.distance) ^ (Double.doubleToLongBits(this.distance) >>> 32));
        hash = 97 * hash + Objects.hashCode(this.stroke);
        hash = 97 * hash + Objects.hashCode(this.eventResult);
        return hash;
    }
    
    //Overriding to string method to be readable
    @Override
    public String toString()
    {
        return  "\nEvent ID: " + eventID + "\n" + 
                "Meeting ID: " + meetingID + "\n" +
                "Event minimum age: " + minAge + "\n" +
                "Event maximum age: " + maxAge + "\n" +
                "Event swimming distance: " + distance + "\n" +
                "Event swimming stroke: " + stroke;
    }
}
