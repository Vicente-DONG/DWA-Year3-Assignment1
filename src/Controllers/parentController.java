/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.familyGroupModel;
import Models.parentModel;
import Models.swimmerModel;

/**
 *
 * @author Vicen
 */
public class parentController {

    private static final parentController instance = new parentController();
    private final databaseHelper dbHelper = new databaseHelper();
    private parentModel user;

    private parentController() {
    }

    ;
    
    public boolean authenticateUser(String password, String username) {
        user = dbHelper.authenticateParentAccount(username, password);

        if (user == null) {
            return false;
        } else {
            return true;
        }
    }
    
    public boolean parentArchived() {
        return user.isArchived();
    }

    public void updateFamilyGroupAddress(String newAddress) {
        familyGroupModel familyGroup = dbHelper.getFamilyGroupbyID(user.getFamilyGroupID());

        parentModel parent1 = dbHelper.getParentbyID(familyGroup.getParentOneID());
        swimmerModel child1 = dbHelper.getSwimmerbyID(familyGroup.getChildOneID());
        
        if (familyGroup.getParentTwoID() != 0) {
            parentModel parent2 = dbHelper.getParentbyID(familyGroup.getParentOneID());

            parent2.setAddress(newAddress);

            dbHelper.modifyParent(parent2);
        }

        if (familyGroup.getChildTwoID() != 0) {
            swimmerModel child2 = dbHelper.getSwimmerbyID(familyGroup.getChildTwoID());

            child2.setAddress(newAddress);

            dbHelper.modifySwimmer(child2);
        }

        if (familyGroup.getChildThreeID() != 0) {
            swimmerModel child3 = dbHelper.getSwimmerbyID(familyGroup.getChildThreeID());

            child3.setAddress(newAddress);

            dbHelper.modifySwimmer(child3);
        }

        if (familyGroup.getChildFourID() != 0) {
            swimmerModel child4 = dbHelper.getSwimmerbyID(familyGroup.getChildFourID());

            child4.setAddress(newAddress);

            dbHelper.modifySwimmer(child4);
        }

        if (familyGroup.getChildFiveID() != 0) {
            swimmerModel child5 = dbHelper.getSwimmerbyID(familyGroup.getChildFiveID());

            child5.setAddress(newAddress);

            dbHelper.modifySwimmer(child5);
        }

        parent1.setAddress(newAddress);
        dbHelper.modifyParent(parent1);
        child1.setAddress(newAddress);
        dbHelper.modifySwimmer(child1);
    }

    public void updateFamilyGroupEmail(String newEmail) {
        familyGroupModel familyGroup = dbHelper.getFamilyGroupbyID(user.getFamilyGroupID());

        parentModel parent1 = dbHelper.getParentbyID(familyGroup.getParentOneID());
        swimmerModel child1 = dbHelper.getSwimmerbyID(familyGroup.getChildOneID());
        if (familyGroup.getParentTwoID() != 0) {
            parentModel parent2 = dbHelper.getParentbyID(familyGroup.getParentOneID());

            parent2.setEmail(newEmail);

            dbHelper.modifyParent(parent2);
        }

        if (familyGroup.getChildTwoID() != 0) {
            swimmerModel child2 = dbHelper.getSwimmerbyID(familyGroup.getChildTwoID());

            child2.setEmail(newEmail);

            dbHelper.modifySwimmer(child2);
        }

        if (familyGroup.getChildThreeID() != 0) {
            swimmerModel child3 = dbHelper.getSwimmerbyID(familyGroup.getChildThreeID());

            child3.setEmail(newEmail);

            dbHelper.modifySwimmer(child3);
        }

        if (familyGroup.getChildFourID() != 0) {
            swimmerModel child4 = dbHelper.getSwimmerbyID(familyGroup.getChildFourID());

            child4.setEmail(newEmail);

            dbHelper.modifySwimmer(child4);
        }

        if (familyGroup.getChildFiveID() != 0) {
            swimmerModel child5 = dbHelper.getSwimmerbyID(familyGroup.getChildFiveID());

            child5.setEmail(newEmail);

            dbHelper.modifySwimmer(child5);
        }

        parent1.setEmail(newEmail);
        dbHelper.modifyParent(parent1);
        child1.setEmail(newEmail);
        dbHelper.modifySwimmer(child1);
    }

    public void updateFamilyGroupContactNumber(String newContactNumber) {
        familyGroupModel familyGroup = dbHelper.getFamilyGroupbyID(user.getFamilyGroupID());

        parentModel parent1 = dbHelper.getParentbyID(familyGroup.getParentOneID());
        swimmerModel child1 = dbHelper.getSwimmerbyID(familyGroup.getChildOneID());
        if (familyGroup.getParentTwoID() != 0) {
            parentModel parent2 = dbHelper.getParentbyID(familyGroup.getParentOneID());

            parent2.setContactNumber(newContactNumber);

            dbHelper.modifyParent(parent2);
        }

        if (familyGroup.getChildTwoID() != 0) {
            swimmerModel child2 = dbHelper.getSwimmerbyID(familyGroup.getChildTwoID());

            child2.setContactNumber(newContactNumber);

            dbHelper.modifySwimmer(child2);
        }

        if (familyGroup.getChildThreeID() != 0) {
            swimmerModel child3 = dbHelper.getSwimmerbyID(familyGroup.getChildThreeID());

            child3.setContactNumber(newContactNumber);

            dbHelper.modifySwimmer(child3);
        }

        if (familyGroup.getChildFourID() != 0) {
            swimmerModel child4 = dbHelper.getSwimmerbyID(familyGroup.getChildFourID());

            child4.setContactNumber(newContactNumber);

            dbHelper.modifySwimmer(child4);
        }

        if (familyGroup.getChildFiveID() != 0) {
            swimmerModel child5 = dbHelper.getSwimmerbyID(familyGroup.getChildFiveID());

            child5.setContactNumber(newContactNumber);

            dbHelper.modifySwimmer(child5);
        }

        parent1.setContactNumber(newContactNumber);
        dbHelper.modifyParent(parent1);
        child1.setContactNumber(newContactNumber);
        dbHelper.modifySwimmer(child1);
    }

    public String stringifyFamilyGroup() {
        familyGroupModel familyGroup = dbHelper.getFamilyGroupbyID(user.getFamilyGroupID());

        parentModel parent1 = dbHelper.getParentbyID(familyGroup.getParentOneID());
        swimmerModel child1 = dbHelper.getSwimmerbyID(familyGroup.getChildOneID());
        String returnString = "================================ YOUR FAMILY GROUP ===================================\n"
                + "---------------  PARENTS  ---------------\n";

        returnString += parent1.toString();
        
        if (familyGroup.getParentTwoID() != 0) {
            parentModel parent2 = dbHelper.getParentbyID(familyGroup.getParentOneID());

            returnString += parent2.toString();
        }

        returnString += "\n---------------  CHILDREN  ---------------\n";

        if (familyGroup.getChildTwoID() != 0) {
            swimmerModel child2 = dbHelper.getSwimmerbyID(familyGroup.getChildTwoID());

            returnString += child2.toString();
        }

        if (familyGroup.getChildThreeID() != 0) {
            swimmerModel child3 = dbHelper.getSwimmerbyID(familyGroup.getChildThreeID());

            returnString += child3.toString();
        }

        if (familyGroup.getChildFourID() != 0) {
            swimmerModel child4 = dbHelper.getSwimmerbyID(familyGroup.getChildFourID());

            returnString += child4.toString();
        }

        if (familyGroup.getChildFiveID() != 0) {
            swimmerModel child5 = dbHelper.getSwimmerbyID(familyGroup.getChildFiveID());

            returnString += child5.toString();
        }

        return returnString;
    }

    public static parentController getInstance() {
        return instance;
    }
}
