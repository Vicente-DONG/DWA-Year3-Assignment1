/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllerTests;

import Controllers.databaseHelper;
import Controllers.parentController;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Vicen
 */
public class parentControllerTests {
    databaseHelper dbHelper = new databaseHelper();
    parentController controller = parentController.getInstance();
    
    @Test
    public void testAuthenticateCorrect()
    {
        boolean actual = controller.authenticateUser("parent1", "parent1");
        
        assertEquals(true, actual);
    }
    
    @Test
    public void testAuthenticateWrong()
    {
        boolean actual = controller.authenticateUser("wrongUsername", "wrongPassword");
        
        assertEquals(false, actual);
    }
}
