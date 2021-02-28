package Views;

import Controllers.clubOfficialController;
import Models.meetingModel;
import Models.parentModel;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
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

public class manageMeetingsPanel implements ActionListener {

    private JPanel manageMeetingsPanel;
    private JButton backButton;
    private JButton logOffButton;
    private Font f = new Font("Arial", Font.BOLD, 16);
    private JList meetingsSelection;
    private JTextArea meetingsDisplay;
    private JDateChooser meetingDateInput;
    private JButton modifyMeetingButton;
    private JButton addMeetingButton;
    private JTextField poolLengthInput;
    private JTextField venueNameInput;
    private JTextField meetingNameInput;
    private clubOfficialController controller = clubOfficialController.getInstance();

    public manageMeetingsPanel() {
        manageMeetingsPanel = new JPanel(new BorderLayout());

        manageMeetingsPanel.add(navigationPanel(), BorderLayout.NORTH);
        manageMeetingsPanel.add(mainContentPanel(), BorderLayout.CENTER);
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

        informationDisplayPanel.add(meetingSelectionPanel());
        informationDisplayPanel.add(meetingsDisplayPanel());

        return informationDisplayPanel;
    }

    private JPanel meetingSelectionPanel() {
        JPanel meetingSelectionPanel = new JPanel(new BorderLayout());

        JLabel selectMeetingLabel = new JLabel("Select meeting");
        selectMeetingLabel.setFont(new Font("Arial", Font.BOLD, 18));
        selectMeetingLabel.setHorizontalAlignment(JLabel.CENTER);
        selectMeetingLabel.setBorder(new EmptyBorder(15, 0, 15, 0));
        ArrayList<meetingModel> meetings = controller.getAllMeetings();

        DefaultListModel<meetingModel> meetingList = new DefaultListModel<meetingModel>();

        for (int i = 0; i < meetings.size(); i++) {
            meetingList.add(i, meetings.get(i));
        }
        
        meetingsSelection = new JList(meetingList);
        meetingsSelection.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                meetingModel meeting = (meetingModel) value;

                setText("Meeting ID: " + meeting.getMeetingID() + " - Meeting name:  " + meeting.getMeetingName());

                setFont(new Font("Arial", Font.BOLD, 14));

                return this;
            }
        });

        meetingsSelection.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                JList list = (JList) e.getSource();

                meetingModel meeting = (meetingModel) list.getSelectedValue();

                Calendar date = Calendar.getInstance();
                date.setTime(java.sql.Date.valueOf(meeting.getMeetingDate()));

                meetingDateInput.setCalendar(date);
                //CHANGE THIS TO BE MORE READABLE WITH THE MANAGER
                meetingsDisplay.setText(meeting.toString());
            }
        });

        JScrollPane scrollable = new JScrollPane(meetingsSelection);
        scrollable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        meetingSelectionPanel.add(selectMeetingLabel, BorderLayout.NORTH);
        meetingSelectionPanel.add(scrollable, BorderLayout.CENTER);

        return meetingSelectionPanel;
    }

    private JPanel meetingsDisplayPanel() {
        JPanel meetingsDisplayPanel = new JPanel(new BorderLayout());

        JLabel meetingsInformationLabel = new JLabel("Meeting Information");
        meetingsInformationLabel.setFont(new Font("Arial", Font.BOLD, 18));
        meetingsInformationLabel.setHorizontalAlignment(JLabel.CENTER);
        meetingsInformationLabel.setBorder(new EmptyBorder(15, 0, 15, 0));

        meetingsDisplay = new JTextArea("Meeting information will display here when selected");
        meetingsDisplay.setEditable(false);

        JScrollPane scrollable = new JScrollPane(meetingsDisplay);
        scrollable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        meetingsDisplayPanel.add(meetingsInformationLabel, BorderLayout.NORTH);
        meetingsDisplayPanel.add(scrollable, BorderLayout.CENTER);

        return meetingsDisplayPanel;
    }

    private JPanel managementPanel() {
        JPanel managementPanel = new JPanel(new GridLayout(5, 1));
        managementPanel.setBorder(BorderFactory.createTitledBorder(new EmptyBorder(50, 15, 15, 15), "Input meeting information", TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 16)));

        managementPanel.add(nameInputPanel());
        managementPanel.add(venueInputPanel());
        managementPanel.add(dateInputPanel());
        managementPanel.add(poolLengthInputPanel());
        managementPanel.add(managementButtonsPanel());

        return managementPanel;
    }

    private JPanel nameInputPanel() {
        JPanel nameInputPanel = new JPanel();

        JLabel meetingNameLabel = new JLabel("Meeting name: ");
        meetingNameInput = new JTextField();
        meetingNameInput.setPreferredSize(new Dimension(170, 25));

        nameInputPanel.add(meetingNameLabel);
        nameInputPanel.add(meetingNameInput);

        return nameInputPanel;
    }

    private JPanel venueInputPanel() {
        JPanel venueInputPanel = new JPanel();

        JLabel venueNameLabel = new JLabel("Venue name: ");
        venueNameInput = new JTextField();
        venueNameInput.setPreferredSize(new Dimension(170, 25));

        venueInputPanel.add(venueNameLabel);
        venueInputPanel.add(venueNameInput);

        return venueInputPanel;
    }

    private JPanel dateInputPanel() {
        JPanel dateInputPanel = new JPanel();

        JLabel dateLabel = new JLabel("Meeting date: ");
        meetingDateInput = new JDateChooser();
        meetingDateInput.setPreferredSize(new Dimension(170, 25));

        dateInputPanel.add(dateLabel);
        dateInputPanel.add(meetingDateInput);

        return dateInputPanel;
    }

    private JPanel poolLengthInputPanel() {
        JPanel poolLengthInputPanel = new JPanel();

        JLabel poolLengthLabel = new JLabel("Pool length: ");
        poolLengthInput = new JTextField();
        poolLengthInput.setPreferredSize(new Dimension(170, 25));

        poolLengthInputPanel.add(poolLengthLabel);
        poolLengthInputPanel.add(poolLengthInput);

        return poolLengthInputPanel;
    }

    private JPanel managementButtonsPanel() {
        JPanel managementButtonsPanel = new JPanel();

        addMeetingButton = new JButton("Create meeting");
        addMeetingButton.setPreferredSize(new Dimension(175, 50));
        addMeetingButton.addActionListener(this);

        modifyMeetingButton = new JButton("Modify meeting");
        modifyMeetingButton.setPreferredSize(new Dimension(175, 50));
        modifyMeetingButton.addActionListener(this);

        managementButtonsPanel.add(addMeetingButton);
        managementButtonsPanel.add(modifyMeetingButton);

        return managementButtonsPanel;
    }

    public JPanel getManageMeetingsPanel() {
        return manageMeetingsPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addMeetingButton || e.getSource() == modifyMeetingButton) {
            boolean readyForAddition = false;
            boolean readyForModification = false;

            if (!meetingNameInput.getText().isBlank() || !venueNameInput.getText().isBlank() || !meetingDateInput.toString().isBlank() || !poolLengthInput.getText().isBlank()) {
                readyForModification = true;
            }

            if (!meetingNameInput.getText().isBlank() && !venueNameInput.getText().isBlank() && !meetingDateInput.getDate().toString().isBlank() && !poolLengthInput.getText().isBlank()) {
                readyForAddition = true;
            }
            
            if(e.getSource() == modifyMeetingButton)
            {
                if (readyForModification) {
                        meetingModel newMeeting = (meetingModel)meetingsSelection.getSelectedValue();
                        
                        if(!meetingNameInput.getText().isBlank())
                        {
                            newMeeting.setMeetingName(meetingNameInput.getText());
                        }
                        
                        if(!venueNameInput.getText().isBlank())
                        {
                            newMeeting.setVenueName(venueNameInput.getText());
                        }
                        
                        if(!meetingDateInput.getDate().toString().isBlank())
                        {
                            newMeeting.setMeetingDate(meetingDateInput.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                        }
                        
                        if(!poolLengthInput.getText().isBlank())
                        {
                            try {
                            newMeeting.setPoolLength(Double.parseDouble(poolLengthInput.getText()));
                            } catch (NumberFormatException nFE)
                            {
                                JOptionPane.showMessageDialog(manageMeetingsPanel, "Please only use numbers when defining pool length", "ERROR - Missing field", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        
                        controller.modifyMeeting(newMeeting);
                        meetingsDisplay.setText(newMeeting.toString());
                        meetingsSelection.validate();
                } else {
                    JOptionPane.showMessageDialog(manageMeetingsPanel, "Please fill at least one field to modify this meeting", "ERROR - Missing field", JOptionPane.ERROR_MESSAGE);
                }
            }
            
            if(e.getSource() == addMeetingButton) {
                if (readyForAddition) {
                    boolean nameAvailable = controller.checkForMeetingNameAvailability(meetingNameInput.getText());
                    
                    if(nameAvailable)
                    {
                        try {
                        controller.addMeeting(new meetingModel(meetingNameInput.getText(), venueNameInput.getText(), meetingDateInput.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), Double.parseDouble(poolLengthInput.getText())));
                            meetingsSelection.validate();
                        } catch (NumberFormatException nFE)
                            {
                                JOptionPane.showMessageDialog(manageMeetingsPanel, "Please only use numbers when defining pool length", "ERROR - Missing field", JOptionPane.ERROR_MESSAGE);
                            }
                    } else {
                        JOptionPane.showMessageDialog(manageMeetingsPanel, "That meeting name is already taken, please chose another one or modify the existing meeting", "ERROR - Meeting name taken", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(manageMeetingsPanel, "Please fill all fields to add a meeting", "ERROR - Missing fields", JOptionPane.ERROR_MESSAGE);
                }
            }

        } else if (e.getSource()
                == backButton) {
            manageMeetingsPanel.setVisible(false);
            manageMeetingsPanel.getParent().add(new clubOfficialMenuPanel().getClubOfficialMenuPanel());
            manageMeetingsPanel.getParent().remove(manageMeetingsPanel);
        } else if (e.getSource()
                == logOffButton) {
            logInFrame logInFrame = new logInFrame();
            SwingUtilities.getWindowAncestor(manageMeetingsPanel).dispose();
        }
    }

}
