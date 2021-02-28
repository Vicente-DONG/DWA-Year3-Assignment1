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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Vicen
 */
public class swimmerMenuPanel extends JFrame implements ActionListener{
    private JPanel swimmerMenuPanel;
    private final Font text = new Font("Arial", Font.PLAIN, 14);
    private JButton seeEventResultsButton;
    private JButton seeSwimmingMeetsButton;
    private JButton logOffButton;
    public swimmerMenuPanel(){
        swimmerMenuPanel = new JPanel();
        swimmerMenuPanel.setLayout(new BorderLayout());
        
        JPanel logOffPanel = new JPanel();
        logOffButton = new JButton("Log off");
        logOffButton.addActionListener(this);
        logOffPanel.add(logOffButton);
        
        swimmerMenuPanel.add(logOffPanel, BorderLayout.NORTH);
        swimmerMenuPanel.add(buttonsPanel(), BorderLayout.CENTER);
    }
    
    private JPanel buttonsPanel()
    {
        JPanel buttonsPanel = new JPanel(new GridLayout(2, 2));
        
        buttonsPanel.add(new JPanel());
        buttonsPanel.add(new JPanel());
        buttonsPanel.add(searchResultPanel());
        buttonsPanel.add(searchEventsPanel());
        
        return buttonsPanel;
    }
    
    private JPanel searchResultPanel() {
        JPanel searchResultPanel = new JPanel();
        
        seeSwimmingMeetsButton = new JButton("See meetings");
        seeSwimmingMeetsButton.setFont(text);
        seeSwimmingMeetsButton.setPreferredSize(new Dimension(175, 50));
        seeSwimmingMeetsButton.addActionListener(this);
        
        searchResultPanel.add(seeSwimmingMeetsButton, BorderLayout.NORTH);
        
        return searchResultPanel;
    }
    
    private JPanel searchEventsPanel() {
        JPanel searchEventsPanel = new JPanel();
        
        seeEventResultsButton = new JButton("See events");
        seeEventResultsButton.setFont(text);
        seeEventResultsButton.setPreferredSize(new Dimension(175, 50));
        seeEventResultsButton.addActionListener(this);
        
        searchEventsPanel.add(seeEventResultsButton, BorderLayout.SOUTH);
        
        return searchEventsPanel;
    }
    
    public JPanel getswimmerMenuPanel() {
        return swimmerMenuPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == seeSwimmingMeetsButton) {
            swimmerMenuPanel.setVisible(false);
            swimmerMenuPanel.getParent().add(new searchMeetsPanelSwimmer().getSearchMeetsPanelSwimmer());
            swimmerMenuPanel.getParent().remove(swimmerMenuPanel);
        } else if (e.getSource() == seeEventResultsButton) {
            swimmerMenuPanel.setVisible(false);
            swimmerMenuPanel.getParent().add(new searchSwimmingEventsPanelSwimmer().getSearchSwimmingEventsPanelSwimmer());
            swimmerMenuPanel.getParent().remove(swimmerMenuPanel);
        } else if (e.getSource() == logOffButton) {
            logInFrame logInFrame = new logInFrame();
            SwingUtilities.getWindowAncestor(swimmerMenuPanel).dispose();
        }
    }
}
