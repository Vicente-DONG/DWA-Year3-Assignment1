/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;
/**
 *
 * @author Vicen
 */
public class clubOfficialMenuPanel implements ActionListener{
    private JPanel clubOfficialMenuPanel;
    private final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    private final Font f = new Font("Arial", Font.PLAIN, 14);
    private JButton logOffButton;
    private JButton manageSwimmersButton;
    private JButton manageParentsButton;
    private JButton manageFamilyGroupsButton;
    private JButton manageMeetingsButton;
    private JButton manageEventsButton;
    private JButton manageEventResultsButton;
    private JButton manageFamilyGroups;
    private JButton archiveUsersButton;
    private JButton addSwimmerToEventButton;
    
    public clubOfficialMenuPanel(){
        clubOfficialMenuPanel = new JPanel(new BorderLayout());
        
        clubOfficialMenuPanel.add(logOffButtonPanel(), BorderLayout.NORTH);
        clubOfficialMenuPanel.add(buttonsPanel(), BorderLayout.CENTER);
    }
    
    private JPanel logOffButtonPanel()
    {
        JPanel logOffButtonPanel = new JPanel();
        
        logOffButton = new JButton("Log off");
        logOffButton.addActionListener(this);
        
        logOffButtonPanel.add(logOffButton);
        
        return logOffButtonPanel;
    }
    
    private JPanel buttonsPanel()
    {
        JPanel buttonsPanel = new JPanel(new GridLayout(2, 1));
        
        buttonsPanel.add(managementPanel());
        buttonsPanel.add(administrationPanel());
        
        return buttonsPanel;
    }

    private JPanel managementPanel()
    {
        JPanel managementPanel = new JPanel();
        
        manageSwimmersButton = new JButton("Manage swimmers");
        manageSwimmersButton.addActionListener(this);
        
        manageParentsButton = new JButton("Manage parents");
        manageParentsButton.addActionListener(this);
        
        manageFamilyGroupsButton = new JButton("Manage family groups");
        manageFamilyGroupsButton.addActionListener(this);
        
        manageMeetingsButton = new JButton("Manage meetings");
        manageMeetingsButton.addActionListener(this);
        
        manageEventsButton = new JButton("Manage events");
        manageEventsButton.addActionListener(this);
        
        manageEventResultsButton = new JButton("Manage event results");
        manageEventResultsButton.addActionListener(this);
        
        manageFamilyGroups = new JButton("Manage family groups");
        manageFamilyGroups.addActionListener(this);
        
        managementPanel.add(manageSwimmersButton);
        managementPanel.add(manageParentsButton);
        managementPanel.add(manageMeetingsButton);
        managementPanel.add(manageEventsButton);
        managementPanel.add(manageEventResultsButton);
        managementPanel.add(manageFamilyGroups);
        
        return managementPanel;
    }
    
    private JPanel administrationPanel()
    {
        JPanel administrationPanel = new JPanel();
        
        archiveUsersButton = new JButton("Archive users");
        archiveUsersButton.addActionListener(this);
        administrationPanel.add(archiveUsersButton);
        
        addSwimmerToEventButton = new JButton ("Add swimmer to event");
        addSwimmerToEventButton.addActionListener(this);
        administrationPanel.add(addSwimmerToEventButton);
        
        return administrationPanel;
    }
    
    public JPanel getClubOfficialMenuPanel()
    {
        return clubOfficialMenuPanel;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == manageSwimmersButton) {
            clubOfficialMenuPanel.setVisible(false);
            clubOfficialMenuPanel.getParent().add(new manageSwimmersPanel().getManageSwimmersPanel());
            clubOfficialMenuPanel.getParent().remove(clubOfficialMenuPanel);
        } else if (e.getSource() == manageParentsButton) {
            clubOfficialMenuPanel.setVisible(false);
            clubOfficialMenuPanel.getParent().add(new manageParentsPanel().getManageParentsPanel());
            clubOfficialMenuPanel.getParent().remove(clubOfficialMenuPanel);
        } else if (e.getSource() == manageMeetingsButton) {
            clubOfficialMenuPanel.setVisible(false);
            clubOfficialMenuPanel.getParent().add(new manageMeetingsPanel().getManageMeetingsPanel());
            clubOfficialMenuPanel.getParent().remove(clubOfficialMenuPanel);
        } else if (e.getSource() == manageEventsButton) {
            clubOfficialMenuPanel.setVisible(false);
            clubOfficialMenuPanel.getParent().add(new manageEventsPanel().getManageEventsPanel());
            clubOfficialMenuPanel.getParent().remove(clubOfficialMenuPanel);
        } else if (e.getSource() == manageEventResultsButton) {
            clubOfficialMenuPanel.setVisible(false);
            clubOfficialMenuPanel.getParent().add(new manageEventResultsPanel().getManageEnvetResultsPanel());
            clubOfficialMenuPanel.getParent().remove(clubOfficialMenuPanel);
        } else if (e.getSource() == manageFamilyGroups) {
            clubOfficialMenuPanel.setVisible(false);
            clubOfficialMenuPanel.getParent().add(new manageFamilyGroupingsPanel().getManageFamilyGroupingsPanel());
            clubOfficialMenuPanel.getParent().remove(clubOfficialMenuPanel);
        } else if (e.getSource() == archiveUsersButton) {
            clubOfficialMenuPanel.setVisible(false);
            clubOfficialMenuPanel.getParent().add(new manageMembersPanel().getManageMemberPanel());
            clubOfficialMenuPanel.getParent().remove(clubOfficialMenuPanel);
        } else if (e.getSource() == addSwimmerToEventButton) {
            clubOfficialMenuPanel.setVisible(false);
            clubOfficialMenuPanel.getParent().add(new manageMembers().getManageMembers());
            clubOfficialMenuPanel.getParent().remove(clubOfficialMenuPanel);
        } else if (e.getSource() == logOffButton) {
            logInFrame logInFrame = new logInFrame();
            SwingUtilities.getWindowAncestor(clubOfficialMenuPanel).dispose();
        }
    }
}
