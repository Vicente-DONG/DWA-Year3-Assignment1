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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Vicen
 */
public class parentMenuPanel extends JFrame implements ActionListener{
    private JPanel parentMenuPanel;
    private final Font text = new Font("Arial", Font.PLAIN, 14);
    private JButton modifyFamilyGroupButton;
    private JButton logOffButton;
    public parentMenuPanel(){
        parentMenuPanel = new JPanel();
        parentMenuPanel.setLayout(new BorderLayout());
        
        JPanel logOffPanel = new JPanel();
        logOffButton = new JButton("Log off");
        logOffButton.addActionListener(this);
        logOffPanel.add(logOffButton);
        
        parentMenuPanel.add(logOffPanel, BorderLayout.NORTH);
        parentMenuPanel.add(modifyFamilyGroupPanel(), BorderLayout.CENTER);
    }
    
    private JPanel modifyFamilyGroupPanel() {
        JPanel searchEventsPanel = new JPanel(new BorderLayout());
        
        JPanel ModifyButtonPanel = new JPanel();
        modifyFamilyGroupButton = new JButton("Manage family group");
        modifyFamilyGroupButton.setFont(text);
        modifyFamilyGroupButton.setPreferredSize(new Dimension(175, 50));
        modifyFamilyGroupButton.addActionListener(this);
        modifyFamilyGroupButton.setMinimumSize(getPreferredSize());
        modifyFamilyGroupButton.setMaximumSize(getPreferredSize());
        ModifyButtonPanel.add(modifyFamilyGroupButton);
        
        searchEventsPanel.add(ModifyButtonPanel, BorderLayout.CENTER);
        
        return searchEventsPanel;
    }
    
    public JPanel getParentMenuPanel() {
        return parentMenuPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == modifyFamilyGroupButton) {
            parentMenuPanel.setVisible(false);
            parentMenuPanel.getParent().add(new manageFamilyGroupPanel().getManageFamilyGroupPanel());
            parentMenuPanel.getParent().remove(parentMenuPanel);
        } else if (e.getSource() == logOffButton) {
            logInFrame logInFrame = new logInFrame();
            SwingUtilities.getWindowAncestor(parentMenuPanel).dispose();
        }
    }
}
