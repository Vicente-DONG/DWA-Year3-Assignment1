/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllerTests;

import Controllers.clubOfficialController;
import Controllers.databaseHelper;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Vicen
 */
public class clubOfficialControllerTests {
    databaseHelper dbHelper = new databaseHelper();
    clubOfficialController controller = clubOfficialController.getInstance();
    
    @Test
    public void testAuthenticateCorrect()
    {
        boolean actual = controller.authenticateUser("admin1", "admin1");
        
        assertEquals(true, actual);
    }
    
    @Test
    public void testAuthenticateWrong()
    {
        boolean actual = controller.authenticateUser("wrongUsername", "wrongPassword");
        
        assertEquals(false, actual);
    }
}
