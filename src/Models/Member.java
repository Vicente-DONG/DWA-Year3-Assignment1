package Models;

import java.time.LocalDate;

//Member is a parent class to any member of the swimmingClub,
//this is to make the code more efficient by reducing repetition
public class Member {
    private String username;
    private String password;
    private String name;
    private boolean areTheyMale;
    private String address;
    private String contactNumber;
    private String email;
    private LocalDate DOB;
    private boolean archived;

    public Member(String username, String password, String name, boolean areTheyMale, String address, String contactNumber, String email, LocalDate DOB, boolean archived) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.areTheyMale = areTheyMale;
        this.address = address;
        this.contactNumber = contactNumber;
        this.email = email;
        this.DOB = DOB;
        this.archived = archived;
    }

    //Standard getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getAreTheyMale() {
        return areTheyMale;
    }

    public void setAreTheyMale(Boolean areTheyMale) {
        this.areTheyMale = areTheyMale;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDOB() {
        return DOB;
    }

    public void setDOB(LocalDate DOB) {
        this.DOB = DOB;
    }

    public boolean isAreTheyMale() {
        return areTheyMale;
    }

    public void setAreTheyMale(boolean areTheyMale) {
        this.areTheyMale = areTheyMale;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }
    
    protected String areTheyMaleToGender()
    {
        if(areTheyMale = true)
        {
            return "Male";
        } else {
            return "Female";
        }
    }
}
