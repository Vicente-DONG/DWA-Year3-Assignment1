/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Controllers.guestController;
import Models.eventModel;
import Models.eventResultModel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
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
public class searchSwimmingEventsPanelGuest implements ActionListener {

    private JPanel searchSwimmingEventsPanelGuest;
    private final Font text = new Font("Arial", Font.PLAIN, 14);
    private JButton logOffButton;
    private JButton backButton;
    private JButton searchButton;
    private JTextArea displayTextArea;
    private JTextField swimmerNameField;
    private JComboBox strokeSelection;
    private JComboBox maximumAgeSelection;
    private JComboBox minimumAgeSelection;
    private JButton seeEventResultsButton;
    private guestController controller = new guestController();

    public searchSwimmingEventsPanelGuest() {
        searchSwimmingEventsPanelGuest = new JPanel(new BorderLayout());

        searchSwimmingEventsPanelGuest.add(navigationPanel(), BorderLayout.NORTH);
        searchSwimmingEventsPanelGuest.add(mainContentPanel(), BorderLayout.CENTER);
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
        mainContentPanel.add(filtersPanel());

        return mainContentPanel;
    }

    private JPanel displayPanel() {
        JPanel displayPanel = new JPanel();

        displayTextArea = new JTextArea("Information will go here once search button is selected");
        displayTextArea.setPreferredSize(new Dimension(640, 800));
        displayTextArea.setMinimumSize(displayTextArea.getPreferredSize());
        displayTextArea.setEditable(false);

        JScrollPane scrollable = new JScrollPane(displayTextArea);
        scrollable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        displayPanel.add(scrollable);

        return displayPanel;
    }

    private JPanel filtersPanel() {
        JPanel filtersPanel = new JPanel(new GridLayout(4, 1));
        filtersPanel.setBorder(BorderFactory.createTitledBorder(null, "Filters", TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 16)));

        filtersPanel.add(ageFilterPanel());
        filtersPanel.add(strokeFilterPanel());
        filtersPanel.add(swimmerNamePanel());
        filtersPanel.add(searchButtonPanel());

        return filtersPanel;
    }

    private JPanel ageFilterPanel() {
        JPanel ageFilterPanel = new JPanel();

        String ageRange[] = {"", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16"};

        JLabel minimumAgeLabel = new JLabel("Minimum age: ");
        minimumAgeSelection = new JComboBox(ageRange);
        minimumAgeSelection.setPreferredSize(new Dimension(100, 30));

        JLabel maximumAgeLabel = new JLabel("Maximum age: ");
        maximumAgeSelection = new JComboBox(ageRange);
        maximumAgeSelection.setPreferredSize(new Dimension(100, 30));

        ageFilterPanel.add(minimumAgeLabel);
        ageFilterPanel.add(minimumAgeSelection);
        ageFilterPanel.add(maximumAgeLabel);
        ageFilterPanel.add(maximumAgeSelection);

        return ageFilterPanel;
    }

    private JPanel strokeFilterPanel() {
        JPanel strokeFilterPanel = new JPanel();

        String strokes[] = {"", "Front crawl", "Trudgen", "Butterfly", "Breaststroke", "Backstroke", "Forward backstroke", "Sidestroke", "Other"};

        JLabel strokeSelectionLabel = new JLabel("Stroke type: ");
        strokeSelection = new JComboBox(strokes);
        strokeSelection.setPreferredSize(new Dimension(100, 30));

        strokeFilterPanel.add(strokeSelectionLabel);
        strokeFilterPanel.add(strokeSelection);

        return strokeFilterPanel;
    }

    private JPanel swimmerNamePanel() {
        JPanel swimmerNamePanel = new JPanel();

        JLabel swimmerNameLabel = new JLabel("First name: ");
        swimmerNameField = new JTextField();
        swimmerNameField.setPreferredSize(new Dimension(170, 25));

        swimmerNamePanel.add(swimmerNameLabel);
        swimmerNamePanel.add(swimmerNameField);;

        return swimmerNamePanel;
    }

    private JPanel searchButtonPanel() {
        JPanel searchButtonPanel = new JPanel();

        searchButton = new JButton("Search event");
        searchButton.setPreferredSize(new Dimension(175, 50));
        searchButton.addActionListener(this);

        seeEventResultsButton = new JButton("Get event results");
        seeEventResultsButton.setPreferredSize(new Dimension(175, 50));
        seeEventResultsButton.addActionListener(this);

        searchButtonPanel.add(seeEventResultsButton);
        searchButtonPanel.add(searchButton);

        return searchButtonPanel;
    }

    public JPanel getSearchSwimmingEventsPanelGuest() {
        return searchSwimmingEventsPanelGuest;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton || e.getSource() == seeEventResultsButton) {
            ArrayList<eventModel> events = controller.getAllEvents();

            if (!minimumAgeSelection.getSelectedItem().toString().isBlank() && !maximumAgeSelection.getSelectedItem().toString().isBlank()) {
                ArrayList<eventModel> filteredByAge = controller.getEventsFilteredByAge(Integer.parseInt(minimumAgeSelection.getSelectedItem().toString()), Integer.parseInt(maximumAgeSelection.getSelectedItem().toString()));
                for (int i = 0; i < events.size(); i++) {
                    for (int j = 0; j < filteredByAge.size(); j++) {
                        if (events.get(i).getEventID() != filteredByAge.get(j).getEventID()) {
                            events.remove(i);
                        }
                    }
                }
            }
            if (!strokeSelection.getSelectedItem().toString().isBlank()) {
                ArrayList<eventModel> filteredByStroke = controller.getEventsFilteredByStroke(strokeSelection.getSelectedItem().toString());
                for (int i = 0; i < events.size(); i++) {
                    for (int j = 0; j < filteredByStroke.size(); j++) {
                        if (events.get(i).getEventID() != filteredByStroke.get(j).getEventID()) {
                            events.remove(i);
                        }
                    }
                }
            }
            if (!swimmerNameField.getText().isBlank()) {
                ArrayList<eventModel> filteredByName = controller.getEventsFilteredByName(swimmerNameField.getText());
                for (int i = 0; i < events.size(); i++) {
                    for (int j = 0; j < filteredByName.size(); j++) {
                        if (events.get(i).getEventID() != filteredByName.get(j).getEventID()) {
                            events.remove(i);
                        }
                    }
                }
            }

            if (e.getSource() == searchButton) {

                String outputString = "\n================================ EVENTS ===================================\n";

                for (int i = 0; i < events.size(); i++) {
                    outputString += events.get(i).toString() +"\n\n";
                }

                displayTextArea.setText(outputString);
            } else if (e.getSource() == seeEventResultsButton) {

                String outputString = "\n================================ EVENT RESULTS ===================================\n";
                ArrayList<eventResultModel> otherResults = controller.getAllEventResults();

                for (int i = 0; i < events.size(); i++) {
                    for (int j = 0; j < otherResults.size(); j++) {
                        if (otherResults.get(j).getEventID() != events.get(i).getEventID()) {
                            otherResults.remove(j);
                        }
                    }
                }

                for (int i = 0; i < otherResults.size(); i++) {
                    outputString += otherResults.get(i).toString()+"\n\n";
                }

                displayTextArea.setText(outputString);
            }
        } else if (e.getSource() == backButton) {
            searchSwimmingEventsPanelGuest.setVisible(false);
            searchSwimmingEventsPanelGuest.getParent().add(new guestMenuPanel().getGuestMenuPanel());
            searchSwimmingEventsPanelGuest.getParent().remove(searchSwimmingEventsPanelGuest);
        } else if (e.getSource() == logOffButton) {
            logInFrame logInFrame = new logInFrame();
            SwingUtilities.getWindowAncestor(searchSwimmingEventsPanelGuest).dispose();
        }
    }
}
