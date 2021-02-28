
package Models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

//Parent is a child of the Member class and only contains ID for 
//parent and ID for family group HOWEVER, unlike swimmer the ID
public class parentModel extends Member{
    private int parentID;
    private int familyGroupID;

    public parentModel(int parentID, int familyGroupID, String username, String password, String name, boolean areTheyMale, String address, String contactNumber, String email, LocalDate DOB, boolean archived) {
        super(username, password, name, areTheyMale, address, contactNumber, email, DOB, archived);
        this.parentID = parentID;
        this.familyGroupID = familyGroupID;
    }

    public parentModel(int familyGroupID, String username, String password, String name, boolean areTheyMale, String address, String contactNumber, String email, LocalDate DOB, boolean archived) {
        super(username, password, name, areTheyMale, address, contactNumber, email, DOB, archived);
        this.familyGroupID = familyGroupID;
    }

    //Constructor for when parent is first created.
    public parentModel(String username, String password, String name, boolean areTheyMale, String address, String contactNumber, String email, LocalDate DOB, boolean archived) {
        super(username, password, name, areTheyMale, address, contactNumber, email, DOB, archived);
    }
    
    //Standard getters and setters
    public int getParentID() {
        return parentID;
    }

    public void setParentID(int parentID) {    
        this.parentID = parentID;
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
        hash = 59 * hash + this.parentID;
        hash = 59 * hash + this.familyGroupID;
        return hash;
    }
    
    @Override
    public String toString() {
        return  "\nParent ID: " + parentID + "\n" +
                "Family group ID: " + familyGroupID + "\n" +
                "Parent username: " + this.getUsername()+ "\n" +
                "Parent password: " + this.getPassword()+ "\n" +
                "Parent name: " + this.getName()+ "\n" +
                "Parent gender: " + this.areTheyMaleToGender()+ "\n" +
                "Parent address: " + this.getAddress()+ "\n" +
                "Parent email: " + this.getEmail() + "\n" +
                "Parent contact number: " + this.getContactNumber()+ "\n" +
                "Parent date of birth: " + this.getDOB().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG))+ "\n" +
                "Archived: " + this.isArchived();
    }
}
