/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllerTests;

import Controllers.databaseHelper;
import Controllers.swimmerController;
import org.junit.Test;
import static org.junit.Assert.*;
import Models.*;

public class swimmerControllerTests {
    databaseHelper dbHelper = new databaseHelper();
    swimmerController controller = swimmerController.getInstance();
    
    @Test
    public void testAuthenticateCorrect()
    {
        boolean actual = controller.authenticateUser("swimmer1", "swimmer1");
        
        assertEquals(true, actual);
    }
    
    @Test
    public void testAuthenticateWrong()
    {
        boolean actual = controller.authenticateUser("wrongUsername", "wrongPassword");
        
        assertEquals(false, actual);
    }
}
