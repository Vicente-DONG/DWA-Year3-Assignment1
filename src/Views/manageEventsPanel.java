package Views;

import Controllers.clubOfficialController;
import Models.eventModel;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class manageEventsPanel implements ActionListener {

    private JPanel manageEventsPanel;
    private JButton backButton;
    private JButton logOffButton;
    private Font f = new Font("Arial", Font.BOLD, 16);
    private JList eventsSelection;
    private JTextArea eventsDisplay;
    private JDateChooser eventDateInput;
    private JComboBox strokeSelection;
    private JButton modifyMeetingButton;
    private JButton addMeetingButton;
    private JTextField poolDistanceInput;
    private JTextField maxAgeInput;
    private JTextField minAgeInput;
    private JTextField meetingIDInput;
    private clubOfficialController controller = clubOfficialController.getInstance();

    public manageEventsPanel() {
        manageEventsPanel = new JPanel(new BorderLayout());

        manageEventsPanel.add(navigationPanel(), BorderLayout.NORTH);
        manageEventsPanel.add(mainContentPanel(), BorderLayout.CENTER);
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

        mainContentPanel.add(managementPanel());
        mainContentPanel.add(informationDisplayPanel());

        return mainContentPanel;
    }

    private JPanel informationDisplayPanel() {
        JPanel informationDisplayPanel = new JPanel(new GridLayout(2, 1));

        informationDisplayPanel.add(eventSelectionPanel());
        informationDisplayPanel.add(eventsDisplayPanel());

        return informationDisplayPanel;
    }

    private JPanel eventSelectionPanel() {
        JPanel eventSelectionPanel = new JPanel(new BorderLayout());

        JLabel selectEventLabel = new JLabel("Select event");
        selectEventLabel.setFont(new Font("Arial", Font.BOLD, 18));
        selectEventLabel.setHorizontalAlignment(JLabel.CENTER);
        selectEventLabel.setBorder(new EmptyBorder(15, 0, 15, 0));

        ArrayList<eventModel> events = controller.getAllEvents();

        DefaultListModel<eventModel> eventList = new DefaultListModel<eventModel>();

        for (int i = 0; i < events.size(); i++) {
            eventList.add(i, events.get(i));
        }

        eventsSelection = new JList(eventList);
        eventsSelection.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                eventModel event = (eventModel) value;

                setText("Event ID: " + event.getEventID() + " - Meeting ID:  " + controller.getMeetingByID(event.getMeetingID()).getMeetingName());//CHANGE MEETING ID FOR MEETING NAME

                setFont(new Font("Arial", Font.BOLD, 14));

                return this;
            }
        });

        eventsSelection.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                JList list = (JList) e.getSource();

                eventModel event = (eventModel) list.getSelectedValue();

                //CHANGE THIS TO BE MORE READABLE WITH THE MANAGER
                eventsDisplay.setText(event.toString());
            }
        });

        JScrollPane scrollable = new JScrollPane(eventsSelection);
        scrollable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        eventSelectionPanel.add(selectEventLabel, BorderLayout.NORTH);
        eventSelectionPanel.add(scrollable, BorderLayout.CENTER);

        return eventSelectionPanel;
    }

    private JPanel eventsDisplayPanel() {
        JPanel eventsDisplayPanel = new JPanel(new BorderLayout());

        JLabel eventsInformationLabel = new JLabel("Event Information");
        eventsInformationLabel.setFont(new Font("Arial", Font.BOLD, 18));
        eventsInformationLabel.setHorizontalAlignment(JLabel.CENTER);
        eventsInformationLabel.setBorder(new EmptyBorder(15, 0, 15, 0));

        eventsDisplay = new JTextArea("Event information will display here when selected");
        eventsDisplay.setEditable(false);

        JScrollPane scrollable = new JScrollPane(eventsDisplay);
        scrollable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        eventsDisplayPanel.add(eventsInformationLabel, BorderLayout.NORTH);
        eventsDisplayPanel.add(scrollable, BorderLayout.CENTER);

        return eventsDisplayPanel;
    }

    private JPanel managementPanel() {
        JPanel managementPanel = new JPanel(new GridLayout(5, 1));
        managementPanel.setBorder(BorderFactory.createTitledBorder(new EmptyBorder(50, 15, 15, 15), "Input event information", TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 16)));

        managementPanel.add(meetingIDInput());
        managementPanel.add(ageInputPanel());
        managementPanel.add(distanceInputPanel());
        managementPanel.add(strokeInputPanel());
        managementPanel.add(managementButtonsPanel());

        return managementPanel;
    }

    private JPanel meetingIDInput() {
        JPanel meetingIDInputPanel = new JPanel();

        JLabel meetingIDLabel = new JLabel("Meeting ID: ");
        meetingIDInput = new JTextField();
        meetingIDInput.setPreferredSize(new Dimension(170, 25));

        meetingIDInputPanel.add(meetingIDLabel);
        meetingIDInputPanel.add(meetingIDInput);

        return meetingIDInputPanel;
    }

    private JPanel ageInputPanel() {
        JPanel ageInputPanel = new JPanel();

        JLabel minAgeLabel = new JLabel("Minimum age: ");
        minAgeInput = new JTextField();
        minAgeInput.setPreferredSize(new Dimension(80, 25));

        JLabel maxAgeLabel = new JLabel("Maximum age: ");
        maxAgeInput = new JTextField();
        maxAgeInput.setPreferredSize(new Dimension(80, 25));

        ageInputPanel.add(minAgeLabel);
        ageInputPanel.add(minAgeInput);
        ageInputPanel.add(maxAgeLabel);
        ageInputPanel.add(maxAgeInput);

        return ageInputPanel;
    }

    private JPanel distanceInputPanel() {
        JPanel distanceInputPanel = new JPanel();

        JLabel poolDistanceLabel = new JLabel("Pool distance: ");
        poolDistanceInput = new JTextField();
        poolDistanceInput.setPreferredSize(new Dimension(170, 25));

        distanceInputPanel.add(poolDistanceLabel);
        distanceInputPanel.add(poolDistanceInput);

        return distanceInputPanel;
    }

    private JPanel strokeInputPanel() {
        JPanel meetingIDInputPanel = new JPanel();

        String strokes[] = {"", "Front crawl", "Trudgen", "Butterfly stroke", "Breaststroke", "Backstroke", "Forward backstroke", "Sidestroke", "Other"};

        JLabel strokeSelectionLabel = new JLabel("Stroke type: ");
        strokeSelection = new JComboBox(strokes);
        strokeSelection.setPreferredSize(new Dimension(100, 30));

        meetingIDInputPanel.add(strokeSelectionLabel);
        meetingIDInputPanel.add(strokeSelection);

        return meetingIDInputPanel;
    }

    private JPanel managementButtonsPanel() {
        JPanel managementButtonsPanel = new JPanel();

        addMeetingButton = new JButton("Create event");
        addMeetingButton.setPreferredSize(new Dimension(175, 50));
        addMeetingButton.addActionListener(this);

        modifyMeetingButton = new JButton("Modify event");
        modifyMeetingButton.setPreferredSize(new Dimension(175, 50));
        modifyMeetingButton.addActionListener(this);

        managementButtonsPanel.add(addMeetingButton);
        managementButtonsPanel.add(modifyMeetingButton);

        return managementButtonsPanel;
    }

    public JPanel getManageEventsPanel() {
        return manageEventsPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addMeetingButton || e.getSource() == modifyMeetingButton) {
            boolean readyForAddition = false;
            boolean readyForModification = false;

            if (!meetingIDInput.getText().isBlank() && !minAgeInput.getText().isBlank() && !maxAgeInput.getText().isBlank()
                    && !poolDistanceInput.getText().isBlank() && !strokeSelection.getSelectedItem().toString().isBlank()) {
                readyForAddition = true;
            }

            if (!meetingIDInput.getText().isBlank() || !minAgeInput.getText().isBlank() || !maxAgeInput.getText().isBlank()
                    || !poolDistanceInput.getText().isBlank() || !strokeSelection.getSelectedItem().toString().isBlank()) {
                readyForModification = true;
            }

            if (e.getSource() == addMeetingButton) {
                if (readyForAddition) {
                    boolean IDExists = false;
                    try {
                        IDExists = controller.checkForMeetingID(Integer.parseInt(meetingIDInput.getText()));
                    } catch (NumberFormatException nFe) {
                        JOptionPane.showMessageDialog(manageEventsPanel, "Please only put numbers on the ID field", "ERROR - Letters or spaces in ID", JOptionPane.ERROR_MESSAGE);
                    }

                    if (IDExists) {
                        if (!strokeSelection.getSelectedItem().toString().equalsIgnoreCase("") || strokeSelection.getSelectedItem().toString() != null) {
                            try {
                                controller.addEventToMeeting(new eventModel(Integer.parseInt(meetingIDInput.getText()), Integer.parseInt(minAgeInput.getText()), Integer.parseInt(maxAgeInput.getText()),
                                        Double.parseDouble(poolDistanceInput.getText()), strokeSelection.getSelectedItem().toString(), new ArrayList<>()));
                                eventsSelection.validate();
                            } catch (NumberFormatException nFE) {
                                JOptionPane.showMessageDialog(manageEventsPanel, "Please make sure that you are using numbers instead of letters in all possible fields", "ERROR - Wrong input", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(manageEventsPanel, "Please select a stroke type to add an event, if unsure select 'Other'", "ERROR - Stroke missing", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(manageEventsPanel, "Meeting id doesnt exist", "ERROR - Meeting not found", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(manageEventsPanel, "Please fill all fields to add a new event to a meeting", "ERROR - Fill all fields", JOptionPane.ERROR_MESSAGE);
                }
            } else if (e.getSource() == modifyMeetingButton) {
                if (readyForModification) {
                    eventModel newEvent = (eventModel) eventsSelection.getSelectedValue();

                    if (!meetingIDInput.getText().isBlank()) {
                        try {
                            boolean IDExists = controller.checkForMeetingID(Integer.parseInt(meetingIDInput.getText()));
                            if (IDExists) {
                                newEvent.setMeetingID(Integer.parseInt(meetingIDInput.toString()));
                            } else {
                                JOptionPane.showMessageDialog(manageEventsPanel, "Meeting id doesnt exist", "ERROR - Meeting not found", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (NumberFormatException nfe) {
                            JOptionPane.showMessageDialog(manageEventsPanel, "Please make sure you only input numbers when writing an ID", "ERROR - Wrong input", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                    try {
                        if (!minAgeInput.getText().isBlank()) {
                            newEvent.setMinAge(Integer.parseInt(minAgeInput.getText()));
                        }
                    } catch (NumberFormatException nfe) {
                        JOptionPane.showMessageDialog(manageEventsPanel, "Please make sure you only input numbers when writing minimum distance", "ERROR - Wrong input", JOptionPane.ERROR_MESSAGE);
                    }

                    try {
                        if (!maxAgeInput.getText().isBlank()) {
                            newEvent.setMaxAge(Integer.parseInt(maxAgeInput.getText()));
                        }
                    } catch (NumberFormatException nfe) {
                        JOptionPane.showMessageDialog(manageEventsPanel, "Please make sure you only input numbers when writing maximum distance", "ERROR - Wrong input", JOptionPane.ERROR_MESSAGE);
                    }

                    try {
                        if (!poolDistanceInput.getText().isBlank()) {
                            newEvent.setDistance(Double.parseDouble(poolDistanceInput.getText()));
                        }
                    } catch (NumberFormatException nfe) {
                        JOptionPane.showMessageDialog(manageEventsPanel, "Please make sure you only input numbers when changing the pool distance", "ERROR - Wrong input", JOptionPane.ERROR_MESSAGE);
                    }

                    if (!strokeSelection.getSelectedItem().toString().isBlank()) {
                        newEvent.setStroke(strokeSelection.getSelectedItem().toString());
                    }

                    controller.modifyEvent(newEvent);
                    eventsDisplay.setText(newEvent.toString());
                } else {
                    JOptionPane.showMessageDialog(manageEventsPanel, "Please fill at least one field to modify a selected event", "ERROR - No fields filled", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (e.getSource()
                == backButton) {
            manageEventsPanel.setVisible(false);
            manageEventsPanel.getParent().add(new clubOfficialMenuPanel().getClubOfficialMenuPanel());
            manageEventsPanel.getParent().remove(manageEventsPanel);
        } else if (e.getSource()
                == logOffButton) {
            logInFrame logInFrame = new logInFrame();
            SwingUtilities.getWindowAncestor(manageEventsPanel).dispose();
        }
    }

}
