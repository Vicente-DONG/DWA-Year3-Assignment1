package Views;

import Controllers.guestController;
import Models.meetingModel;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
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

public class searchMeetsPanelGuest implements ActionListener{
    private JPanel searchMeetsPanelGuest;
    private guestController controller = new guestController();
    private final Font text = new Font("Arial", Font.PLAIN, 14);
    private JButton logOffButton;
    private JButton backButton;
    private JTextArea displayTextArea;
    private JDateChooser meetingStartDate;
    private JDateChooser meetingEndDate;
    private JButton searchButton;
    private JTextField venueField;
    
    public searchMeetsPanelGuest()
    {
        searchMeetsPanelGuest = new JPanel(new BorderLayout());
        
        searchMeetsPanelGuest.add(navigationPanel(), BorderLayout.NORTH);
        searchMeetsPanelGuest.add(mainContentPanel(), BorderLayout.CENTER);
    }
    
    private JPanel navigationPanel(){
        JPanel navigationPanel = new JPanel();
        
        backButton = new JButton("Back");
        backButton.addActionListener(this);
        navigationPanel.add(backButton);
        
        logOffButton = new JButton("Log off");
        logOffButton.addActionListener(this);
        navigationPanel.add(logOffButton);
        
        return navigationPanel;
    }
    
    private JPanel mainContentPanel(){
        JPanel mainContentPanel = new JPanel(new GridLayout(1, 2));
        
        mainContentPanel.add(displayPanel());
        mainContentPanel.add(filtersPanel());
        
        return mainContentPanel;
    }
    
    private JPanel displayPanel()
    {
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
    
    private JPanel filtersPanel()
    {
        JPanel filtersPanel = new JPanel(new GridLayout(3, 1));
        filtersPanel.setBorder(BorderFactory.createTitledBorder(null, "Filters", TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 16)));

        filtersPanel.add(venueFilterPanel());
        filtersPanel.add(dateFilterPanel());
        filtersPanel.add(searchButtonPanel());
        
        return filtersPanel;
    }
    
    private JPanel venueFilterPanel()
    {
        JPanel venueFilterPanel = new JPanel();
        
        JLabel venueLabel = new JLabel("Venue name: ");
        venueField = new JTextField();
        venueField.setPreferredSize(new Dimension(170, 25));
        
        venueFilterPanel.add(venueLabel);
        venueFilterPanel.add(venueField);
        
        return venueFilterPanel;
    }
    
    private JPanel dateFilterPanel()
    {
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
    
    private JPanel searchButtonPanel()
    {
        JPanel searchButtonPanel = new JPanel();
        
        searchButton = new JButton("Search event");
        searchButton.setPreferredSize(new Dimension(175, 50));
        searchButton.addActionListener(this);
        
        searchButtonPanel.add(searchButton);
        
        return searchButtonPanel;
    }
    
    public JPanel getSearchMeetsPanelGuest(){
        return searchMeetsPanelGuest;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == searchButton) {
            boolean searchByVenue = false;
            boolean searchByDate = false;
             
            if (!venueField.getText().isBlank()) {
                searchByVenue = true;
            } 
            
            if (!meetingStartDate.getDateFormatString().isBlank() && !meetingEndDate.getDateFormatString().isBlank()) {
                searchByDate = true;
            }
            
            if(searchByDate == true || searchByVenue == true)
            {
                if(searchByDate == true && searchByVenue == false){
                    ArrayList<meetingModel> displayArray = controller.getMeetingsFilteredByVenue(meetingStartDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), meetingEndDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                    String outputString = "";
                    
                    for (int i = 0; i < displayArray.size(); i++) {
                        outputString += displayArray.get(i).toString();
                    }
                    
                    displayTextArea.setText("========================================== Meetings ==========================================\n" + 
                            outputString);
                    
                } else if(searchByDate == false && searchByVenue == true){
                    ArrayList<meetingModel> displayArray = controller.getMeetingsFilteredByVenue(venueField.getText());
                    String outputString = "";
                    
                    for (int i = 0; i < displayArray.size(); i++) {
                        outputString += displayArray.get(i).toString();
                    }
                    
                    displayTextArea.setText("========================================== Meetings ==========================================\n" + 
                            outputString);
                } else if(searchByDate == true && searchByVenue == true){
                    ArrayList<meetingModel> displayArray = controller.getMeetingsFilteredByVenueAndDate(venueField.getText(), 
                            meetingStartDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), meetingEndDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                    String outputString = "";
                    
                    for (int i = 0; i < displayArray.size(); i++) {
                        outputString += displayArray.get(i).toString();
                    }
                    
                    displayTextArea.setText("========================================== Meetings ==========================================\n" + 
                            outputString);
                }
            } else {
                ArrayList<meetingModel> displayArray = controller.getAllMeetings();
                    String outputString = "";
                    
                    for (int i = 0; i < displayArray.size(); i++) {
                        outputString += displayArray.get(i).toString();
                    }
                    
                    displayTextArea.setText("========================================== Meetings ==========================================\n" + 
                            outputString);
            }
        } else if (e.getSource() == backButton) {
            searchMeetsPanelGuest.setVisible(false);
            searchMeetsPanelGuest.getParent().add(new guestMenuPanel().getGuestMenuPanel());
            searchMeetsPanelGuest.getParent().remove(searchMeetsPanelGuest);
        } else if (e.getSource() == logOffButton) {
            logInFrame logInFrame = new logInFrame();
            SwingUtilities.getWindowAncestor(searchMeetsPanelGuest).dispose();
        }
    }
}
