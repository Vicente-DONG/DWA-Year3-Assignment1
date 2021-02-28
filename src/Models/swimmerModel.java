
package Models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

//Swimmer is a child of Member and only contains the ID for swimmer
//and its familyGroup

public class swimmerModel extends Member{
    int swimmerID;
    int familyGroupID;

    //Database constructor
    public swimmerModel(int swimmerID, int familyGroupID, String username, String password, String name, boolean areTheyMale, String address, String contactNumber, String email, LocalDate DOB, boolean archived) {
        super(username, password, name, areTheyMale, address, contactNumber, email, DOB, archived);
        this.swimmerID = swimmerID;
        this.familyGroupID = familyGroupID;
    } 

    //Constructor for instantiating during execution
    public swimmerModel(int familyGroupID, String username, String password, String name, boolean areTheyMale, String address, String contactNumber, String email, LocalDate DOB, boolean archived) {
        super(username, password, name, areTheyMale, address, contactNumber, email, DOB, archived);
        this.familyGroupID = familyGroupID;
    }
    

    //Constructor for when first creating swimmer
    public swimmerModel(String username, String password, String name, boolean areTheyMale, String address, String contactNumber, String email, LocalDate DOB, boolean archived) {    
        super(username, password, name, areTheyMale, address, contactNumber, email, DOB, archived);
    }

    //Standard getters and setters
    public int getSwimmerID() {
        return swimmerID;
    }

    public void setSwimmerID(int swimmerID) {
        this.swimmerID = swimmerID;
    }

    public int getFamilyGroupID() {
        return familyGroupID;
    }

    public void setFamilyGroupID(int familyGroupID) {
        this.familyGroupID = familyGroupID;
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
        hash = 79 * hash + this.swimmerID;
        hash = 79 * hash + this.familyGroupID;
        return hash;
    }
    
    //Overriding toString method
    @Override
    public String toString()
    {
        return  "\nSwimmer ID: " + swimmerID + "\n" +
                "Family group ID: " + familyGroupID + "\n" +
                "Swimmer username: " + this.getUsername()+ "\n" +
                "Swimmer password: " + this.getPassword()+ "\n" +
                "Swimmer name: " + this.getName()+ "\n" +
                "Swimmer gender: " + this.areTheyMaleToGender()+ "\n" +
                "Swimmer address: " + this.getAddress()+ "\n" +
                "Swimmer contact number: " + this.getContactNumber()+ "\n" +
                "Swimmer date of birth: " + this.getDOB().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)) + "\n" +
                "Archived: " + this.isArchived();
    }
}
