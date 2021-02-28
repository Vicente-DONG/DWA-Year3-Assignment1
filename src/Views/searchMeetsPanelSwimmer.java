package Views;

import Controllers.swimmerController;
import Models.meetingModel;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

public class searchMeetsPanelSwimmer implements ActionListener {

    private JPanel searchMeetsPanelSwimmer;
    private final Font text = new Font("Arial", Font.PLAIN, 14);
    private JButton searchButton;
    private JButton logOffButton;
    private JButton backButton;
    private JTextArea displayTextArea;
    private JDateChooser meetingStartDate;
    private JDateChooser meetingEndDate;
    private JTextField venueField;
    private swimmerController controller = swimmerController.getInstance();

    public searchMeetsPanelSwimmer() {
        searchMeetsPanelSwimmer = new JPanel(new BorderLayout());

        searchMeetsPanelSwimmer.add(navigationPanel(), BorderLayout.NORTH);
        searchMeetsPanelSwimmer.add(mainContentPanel(), BorderLayout.CENTER);
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

        displayTextArea = new JTextArea("Text will appear here when you have selected some filters");
        displayTextArea.setPreferredSize(new Dimension(640, 800));
        displayTextArea.setMinimumSize(displayTextArea.getPreferredSize());
        displayTextArea.setEditable(false);

        JScrollPane scrollable = new JScrollPane(displayTextArea);
        scrollable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        displayPanel.add(scrollable);

        return displayPanel;
    }

    private JPanel filtersPanel() {
        JPanel filtersPanel = new JPanel(new GridLayout(3, 1));
        filtersPanel.setBorder(BorderFactory.createTitledBorder(null, "Filters", TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 16)));

        filtersPanel.add(venueFilterPanel());
        filtersPanel.add(dateFilterPanel());
        filtersPanel.add(searchButtonPanel());

        return filtersPanel;
    }

    private JPanel venueFilterPanel() {
        JPanel venueFilterPanel = new JPanel();

        JLabel venueLabel = new JLabel("Venue name: ");
        venueField = new JTextField();
        venueField.setPreferredSize(new Dimension(170, 25));

        venueFilterPanel.add(venueLabel);
        venueFilterPanel.add(venueField);

        return venueFilterPanel;
    }

    private JPanel dateFilterPanel() {
        JPanel dateFilterPanel = new JPanel();

        JLabel startDateLabel = new JLabel("Start date: ");
        meetingStartDate = new JDateChooser();
        meetingStartDate.setPreferredSize(new Dimension(170, 25));

        JLabel endDateLabel = new JLabel("End date: ");
        meetingEndDate = new JDateChooser();
        meetingEndDate.setPreferredSize(new Dimension(170, 25));
        Calendar date = Calendar.getInstance();

        meetingEndDate.setCalendar(date);
        meetingStartDate.setCalendar(date);

        dateFilterPanel.add(startDateLabel);
        dateFilterPanel.add(meetingStartDate);
        dateFilterPanel.add(endDateLabel);
        dateFilterPanel.add(meetingEndDate);

        return dateFilterPanel;
    }

    private JPanel searchButtonPanel() {
        JPanel searchButtonPanel = new JPanel();

        searchButton = new JButton("Search event");
        searchButton.setPreferredSize(new Dimension(175, 50));
        searchButton.addActionListener(this);

        searchButtonPanel.add(searchButton);

        return searchButtonPanel;
    }

    public JPanel getSearchMeetsPanelSwimmer() {
        return searchMeetsPanelSwimmer;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            ArrayList<meetingModel> swimmerMeetings = controller.getMeetingsForSwimmer();
            ArrayList<meetingModel> otherMeetings = controller.getMeetingsWithoutTheSwimmer();

            boolean searchByVenue = false;
            boolean searchByDate = false;

            if (!venueField.getText().isBlank()) {
                searchByVenue = true;
            }

            if (!meetingStartDate.getDate().toString().isBlank() && !meetingEndDate.getDate().toString().isBlank()) {
                searchByDate = true;
            }

            if (searchByVenue == true || searchByDate == true) {
                if (searchByVenue == true && searchByDate == false) {
                    for (int i = 0; i < swimmerMeetings.size(); i++) {
                        if (!swimmerMeetings.get(i).getVenueName().equalsIgnoreCase(venueField.getText())) {
                            swimmerMeetings.remove(i);
                        }
                    }

                    for (int i = 0; i < otherMeetings.size(); i++) {
                        if (!otherMeetings.get(i).getVenueName().equalsIgnoreCase(venueField.getText())) {
                            otherMeetings.remove(i);
                        }
                    }

                } else if (searchByVenue == false && searchByDate == true) {
                    for (int i = 0; i < swimmerMeetings.size(); i++) {
                        if (swimmerMeetings.get(i).getMeetingDate().isBefore(meetingStartDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                                || swimmerMeetings.get(i).getMeetingDate().isAfter(meetingEndDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())) {
                            swimmerMeetings.remove(i);
                        }
                    }

                    for (int i = 0; i < otherMeetings.size(); i++) {
                        if (otherMeetings.get(i).getMeetingDate().isBefore(meetingStartDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                                || otherMeetings.get(i).getMeetingDate().isAfter(meetingEndDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())) {
                            otherMeetings.remove(i);
                        }
                    }
                } else if (searchByVenue == true && searchByDate == true) {
                    for (int i = 0; i < swimmerMeetings.size(); i++) {
                        if (!swimmerMeetings.get(i).getVenueName().equalsIgnoreCase(venueField.getText())) {
                            swimmerMeetings.remove(i);
                        }
                    }

                    for (int i = 0; i < swimmerMeetings.size(); i++) {
                        if (swimmerMeetings.get(i).getMeetingDate().isBefore(meetingStartDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                                || swimmerMeetings.get(i).getMeetingDate().isAfter(meetingEndDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())) {
                            swimmerMeetings.remove(i);
                        }
                    }

                    for (int i = 0; i < otherMeetings.size(); i++) {
                        if (!otherMeetings.get(i).getVenueName().equalsIgnoreCase(venueField.getText())) {
                            otherMeetings.remove(i);
                        }
                    }

                    for (int i = 0; i < otherMeetings.size(); i++) {
                        if (otherMeetings.get(i).getMeetingDate().isBefore(meetingStartDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                                || otherMeetings.get(i).getMeetingDate().isAfter(meetingEndDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())) {
                            otherMeetings.remove(i);
                        }
                    }
                }
            }

            String startOfString = "\n========================= YOUR EVENTS =========================\n";

            String yourEvents = "";
            for (int i = 0; i < swimmerMeetings.size(); i++) {
                yourEvents += swimmerMeetings.get(i).toString();
            }

            String middleString = "\n========================= OTHER EVENTS =========================\n";

            String otherEvents = "";
            for (int i = 0; i < otherMeetings.size(); i++) {
                otherEvents += otherMeetings.get(i).toString();
            }

            String finalString = startOfString + yourEvents + middleString + otherEvents;

            displayTextArea.setText(finalString);

        } else if (e.getSource() == backButton) {
            searchMeetsPanelSwimmer.setVisible(false);
            searchMeetsPanelSwimmer.getParent().add(new swimmerMenuPanel().getswimmerMenuPanel());
            searchMeetsPanelSwimmer.getParent().remove(searchMeetsPanelSwimmer);
        } else if (e.getSource() == logOffButton) {
            logInFrame logInFrame = new logInFrame();
            SwingUtilities.getWindowAncestor(searchMeetsPanelSwimmer).dispose();
        }
    }
}
