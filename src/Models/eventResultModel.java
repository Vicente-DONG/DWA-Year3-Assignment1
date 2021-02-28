/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.Objects;

/**
 *
 * @author Vicen
 */
public class eventResultModel {
    private int eventResultID;
    private int eventID;
    private String swimmerName;
    private int round;
    private int laneNumber;
    private boolean haveTheyFinished;
    private int minutes;
    private int seconds;
    private int miliseconds;
    private String reason;

    public eventResultModel(int eventResultID, int eventID, String swimmerName, int round, int laneNumber, boolean haveTheyFinished, int minutes, int seconds, int miliseconds, String reason) {
        this.eventResultID = eventResultID;
        this.eventID = eventID;
        this.swimmerName = swimmerName;
        this.round = round;
        this.laneNumber = laneNumber;
        this.haveTheyFinished = haveTheyFinished;
        this.minutes = minutes;
        this.seconds = seconds;
        this.miliseconds = miliseconds;
        this.reason = reason;
    }

    //Constructor for environment instantiation
    public eventResultModel(int eventID, String swimmerName, int round, int laneNumber, boolean haveTheyFinished, int minutes, int seconds, int miliseconds, String reason) {
        this.eventID = eventID;
        this.swimmerName = swimmerName;
        this.round = round;
        this.laneNumber = laneNumber;
        this.haveTheyFinished = haveTheyFinished;
        this.minutes = minutes;
        this.seconds = seconds;
        this.miliseconds = miliseconds;
        this.reason = reason;
    }
    
    public int getEventResultID() {
        return eventResultID;
    }

    public void setEventResultID(int eventResultID) {
        this.eventResultID = eventResultID;
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public String getSwimmerName() {
        return swimmerName;
    }

    public void setSwimmerName(String swimmerName) {
        this.swimmerName = swimmerName;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int getLaneNumber() {
        return laneNumber;
    }

    public void setLaneNumber(int laneNumber) {
        this.laneNumber = laneNumber;
    }

    public boolean isHaveTheyFinished() {
        return haveTheyFinished;
    }

    public void setHaveTheyFinished(boolean haveTheyFinished) {
        this.haveTheyFinished = haveTheyFinished;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getMiliseconds() {
        return miliseconds;
    }

    public void setMiliseconds(int miliseconds) {
        this.miliseconds = miliseconds;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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
        hash = 37 * hash + this.eventResultID;
        hash = 37 * hash + this.eventID;
        hash = 37 * hash + Objects.hashCode(this.swimmerName);
        hash = 37 * hash + this.round;
        hash = 37 * hash + this.laneNumber;
        hash = 37 * hash + (this.haveTheyFinished ? 1 : 0);
        hash = 37 * hash + this.minutes;
        hash = 37 * hash + this.seconds;
        hash = 37 * hash + this.miliseconds;
        hash = 37 * hash + Objects.hashCode(this.reason);
        return hash;
    }
    
    //Overriding toString method so its more readable
    @Override
    public String toString(){
        if(haveTheyFinished = true)
        {
            return "Event result ID: " + eventResultID + "\n" + 
                "Event ID: " + eventID + "\n" +
                "Swimmer name: " + swimmerName + "\n" + 
                "Event round: " + round + "\n" + 
                "Swimmer lane: " + laneNumber + "\n" + 
                "Have they finished: Yes\n" + 
                "Time: " + minutes + ":" + seconds + "." + String.format("%03d", miliseconds);
        } else {
            return "Event result ID: " + eventResultID + "\n" + 
                "Event ID: " + eventID + "\n" +
                "Swimmer name: " + swimmerName + "\n" + 
                "Event round: " + round + "\n" + 
                "Swimmer lane: " + laneNumber + "\n" + 
                "Have they finished: No\n" + 
                "Reason for not finishing: " + reason;
        }
    }
}
