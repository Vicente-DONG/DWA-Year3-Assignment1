package Models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

//Club official extends Member but unlike other members it doesnt need a connection to family groupings
public class clubOfficialModel extends Member {
    private int clubOfficialID;

    //Environment constructor
    public clubOfficialModel(String username, String password, String name, boolean areTheyMale, String address, String contactNumber, String email, LocalDate DOB, boolean archived) {
        super(username, password, name, areTheyMale, address, contactNumber, email, DOB, archived);
    }
    

    //Database constructor
    public clubOfficialModel(int clubOfficialID, String username, String password, String name, boolean areTheyMale, String address, String contactNumber, String email, LocalDate DOB, boolean archived) {
        super(username, password, name, areTheyMale, address, contactNumber, email, DOB, archived);
        this.clubOfficialID = clubOfficialID;
    }

    //Standard getters and setters
    public int getClubOfficialID() {
        return clubOfficialID;
    }

    public void setClubOfficialID(int clubOfficialID) {
        this.clubOfficialID = clubOfficialID;
    }    

    //Overriding equals and hashCode for testing
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
        hash = 71 * hash + this.clubOfficialID;
        return hash;
    }
    
    @Override
    public String toString()
    {
        return  "\nClub official ID: " + clubOfficialID + "\n" +
                "Club official username: " + this.getUsername()+ "\n" +
                "Club official password: " + this.getPassword()+ "\n" +
                "Club official name: " + this.getName()+ "\n" +
                "Club official gender: " + this.areTheyMaleToGender()+ "\n" +
                "Club official address: " + this.getAddress()+ "\n" +
                "Club official contact number: " + this.getContactNumber()+ "\n" +
                "Club official date of birth: " + this.getDOB().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
    }
}
