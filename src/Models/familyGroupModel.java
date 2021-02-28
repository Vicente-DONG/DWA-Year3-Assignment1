
package Models;

//A family group will have a limit of 2 parents and 5 children and the parents will be able to modify their own family group
public class familyGroupModel {
    private int familyGroupID;
    private int parentOneID;
    private int parentTwoID;
    private int childOneID;
    private int childTwoID;
    private int childThreeID;
    private int childFourID;
    private int childFiveID;

    public familyGroupModel(int familyGroupID, int parentOneID, int parentTwoID, int childOneID, int childTwoID, int childThreeID, int childFourID, int childFiveID) {
        this.familyGroupID = familyGroupID;
        this.parentOneID = parentOneID;
        this.parentTwoID = parentTwoID;
        this.childOneID = childOneID;
        this.childTwoID = childTwoID;
        this.childThreeID = childThreeID;
        this.childFourID = childFourID;
        this.childFiveID = childFiveID;
    }

    public familyGroupModel(int parentOneID, int parentTwoID, int childOneID, int childTwoID, int childThreeID, int childFourID, int childFiveID) {
        this.parentOneID = parentOneID;
        this.parentTwoID = parentTwoID;
        this.childOneID = childOneID;
        this.childTwoID = childTwoID;
        this.childThreeID = childThreeID;
        this.childFourID = childFourID;
        this.childFiveID = childFiveID;
    }

    public int getFamilyGroupID() {
        return familyGroupID;
    }

    public void setFamilyGroupID(int familyGroupID) {
        this.familyGroupID = familyGroupID;
    }

    public int getParentOneID() {
        return parentOneID;
    }

    public void setParentOneID(int parentOneID) {
        this.parentOneID = parentOneID;
    }

    public int getParentTwoID() {
        return parentTwoID;
    }

    public void setParentTwoID(int parentTwoID) {
        this.parentTwoID = parentTwoID;
    }

    public int getChildOneID() {
        return childOneID;
    }

    public void setChildOneID(int childOneID) {
        this.childOneID = childOneID;
    }

    public int getChildTwoID() {
        return childTwoID;
    }

    public void setChildTwoID(int childTwoID) {
        this.childTwoID = childTwoID;
    }

    public int getChildThreeID() {
        return childThreeID;
    }

    public void setChildThreeID(int childThreeID) {
        this.childThreeID = childThreeID;
    }

    public int getChildFourID() {
        return childFourID;
    }

    public void setChildFourID(int childFourID) {
        this.childFourID = childFourID;
    }

    public int getChildFiveID() {
        return childFiveID;
    }

    public void setChildFiveID(int childFiveID) {
        this.childFiveID = childFiveID;
    }
    
    @Override
    public String toString()
    {
        return  "Family group ID: " + familyGroupID + "\n" +
                "First parent ID: " + parentOneID + "\n" + 
                "Second parent ID: " + parentTwoID + "\n" + 
                "First child ID: " + childOneID + "\n" + 
                "Second child ID: " + childTwoID + "\n" + 
                "Third child ID: " + childThreeID + "\n" + 
                "Fourth child ID: " + childFourID + "\n" + 
                "Fifth child ID: " + childFiveID;
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
        hash = 59 * hash + this.familyGroupID;
        hash = 59 * hash + this.parentOneID;
        hash = 59 * hash + this.parentTwoID;
        hash = 59 * hash + this.childOneID;
        hash = 59 * hash + this.childTwoID;
        hash = 59 * hash + this.childThreeID;
        hash = 59 * hash + this.childFourID;
        hash = 59 * hash + this.childFiveID;
        return hash;
    }
}
