/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Controllers.parentController;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Vicen
 */
public class manageFamilyGroupPanel implements ActionListener {

    private JPanel manageFamilyGroupPanel;
    private JButton backButton;
    private JButton logOffButton;
    private JTextArea displayTextArea;
    private JTextField addressInput;
    private JTextField contactNumberInput;
    private JTextField emailInput;
    private JButton modifyButton;
    private parentController controller = parentController.getInstance();
    
    public manageFamilyGroupPanel() {
        manageFamilyGroupPanel = new JPanel(new BorderLayout());
        
        manageFamilyGroupPanel.add(navigationPanel(), BorderLayout.NORTH);
        manageFamilyGroupPanel.add(mainContentPanel(), BorderLayout.CENTER);
    }
    
    private JPanel navigationPanel() {
        JPanel navigationPanel = new JPanel();
        
        backButton = new JButton("Back");
        backButton.addActionListener(this);
        navigationPanel.add(backButton);
        
        logOffButton = new JButton("Log off");
        logOffButton.addActionListener(this);
        navigationPanel.add(logOffButton);
        
        return navigationPanel;
    }
    
    private JPanel mainContentPanel() {
        JPanel mainContentPanel = new JPanel(new GridLayout(1, 2));
        
        mainContentPanel.add(displayPanel());
        mainContentPanel.add(modificationsPanel());
        
        return mainContentPanel;
    }
    
    private JPanel modificationsPanel() {
        JPanel modificationsPanel = new JPanel(new GridLayout(4, 1));
        modificationsPanel.setBorder(BorderFactory.createTitledBorder(null, "Modifications", TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 16)));
        
        modificationsPanel.add(addressModificationPanel());
        modificationsPanel.add(contactNumberModificationPanel());
        modificationsPanel.add(emailModificationPanel());
        modificationsPanel.add(modifyButtonPanel());
        
        return modificationsPanel;
    }
    
    private JPanel addressModificationPanel() {
        JPanel addressModificationPanel = new JPanel();
        
        JLabel addressLabel = new JLabel("Address: ");
        addressInput = new JTextField();
        addressInput.setPreferredSize(new Dimension(170, 25));
        
        addressModificationPanel.add(addressLabel);
        addressModificationPanel.add(addressInput);
        return addressModificationPanel;
    }
    
    private JPanel contactNumberModificationPanel() {
        JPanel contactNumberModificationPanel = new JPanel();
        
        JLabel contactNumberLabel = new JLabel("Contact Number: ");
        contactNumberInput = new JTextField();
        contactNumberInput.setPreferredSize(new Dimension(170, 25));
        
        contactNumberModificationPanel.add(contactNumberLabel);
        contactNumberModificationPanel.add(contactNumberInput);
        return contactNumberModificationPanel;
    }
    
    private JPanel emailModificationPanel() {
        JPanel emailModificationPanel = new JPanel();
        
        JLabel emailLabel = new JLabel("Email: ");
        emailInput = new JTextField();
        emailInput.setPreferredSize(new Dimension(170, 25));
        
        emailModificationPanel.add(emailLabel);
        emailModificationPanel.add(emailInput);
        return emailModificationPanel;
    }
    
    private JPanel modifyButtonPanel() {
        JPanel modifyButtonPanel = new JPanel();
        
        modifyButton = new JButton("Modify family group");
        modifyButton.setPreferredSize(new Dimension(175, 50));
        modifyButton.addActionListener(this);
        
        modifyButtonPanel.add(modifyButton);
        return modifyButtonPanel;
    }
    
    private JPanel displayPanel() {
        JPanel displayPanel = new JPanel();
        
        displayTextArea = new JTextArea(controller.stringifyFamilyGroup());
        displayTextArea.setPreferredSize(new Dimension(640, 800));
        displayTextArea.setMinimumSize(displayTextArea.getPreferredSize());
        displayTextArea.setEditable(false);
        
        JScrollPane scrollable = new JScrollPane(displayTextArea);
        scrollable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        displayPanel.add(scrollable);
        
        return displayPanel;
    }
    
    public JPanel getManageFamilyGroupPanel() {
        return manageFamilyGroupPanel;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == modifyButton) {
            if (!addressInput.getText().isBlank()) {
                controller.updateFamilyGroupAddress(addressInput.getText());
            }
            
            if (!emailInput.getText().isBlank()) {
                controller.updateFamilyGroupEmail(emailInput.getText());
            }
            
            if (!contactNumberInput.getText().isBlank()) {
                controller.updateFamilyGroupContactNumber(contactNumberInput.getText());
            }
            
            if (addressInput.getText().isBlank() && emailInput.getText().isBlank() && contactNumberInput.getText().isBlank()) {
                JOptionPane.showMessageDialog(manageFamilyGroupPanel, "Please select some modifiers before you press the modify buttons", "ERROR - No modifiers", JOptionPane.ERROR_MESSAGE);
            }
            
            displayTextArea.setText(controller.stringifyFamilyGroup());
        } else if (e.getSource() == backButton) {
            manageFamilyGroupPanel.setVisible(false);
            manageFamilyGroupPanel.getParent().add(new parentMenuPanel().getParentMenuPanel());
            manageFamilyGroupPanel.getParent().remove(manageFamilyGroupPanel);
        } else if (e.getSource() == logOffButton) {
            logInFrame logInFrame = new logInFrame();
            SwingUtilities.getWindowAncestor(manageFamilyGroupPanel).dispose();
        }
    }
}
