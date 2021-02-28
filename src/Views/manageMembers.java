/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Controllers.clubOfficialController;
import Controllers.databaseHelper;
import Models.Member;
import Models.eventModel;
import Models.parentModel;
import Models.swimmerModel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Vicen
 */
public class manageMembers implements ActionListener {

    private JPanel manageEventsMembers;
    private JButton backButton;
    private JButton logOffButton;
    private JList usersDisplay;
    private JTextArea eventInformationDisplay;
    private eventModel selectedEvent;
    private clubOfficialController controller = clubOfficialController.getInstance();
    private JButton archiveButton;
    private JTextField swimmerIDField;
    private JTextField roundNumberField;

    public manageMembers() {
        manageEventsMembers = new JPanel(new BorderLayout());

        manageEventsMembers.add(navigationPanel(), BorderLayout.NORTH);
        manageEventsMembers.add(mainContentPanel(), BorderLayout.CENTER);
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

        mainContentPanel.add(userManagementPanel());
        mainContentPanel.add(selectionPanel());

        return mainContentPanel;
    }

    private JPanel selectionPanel() {
        JPanel selectionPanel = new JPanel(new BorderLayout());

        JLabel currentEventsLabel = new JLabel("Current event's");
        currentEventsLabel.setFont(new Font("Arial", Font.BOLD, 18));
        currentEventsLabel.setHorizontalAlignment(JLabel.CENTER);
        currentEventsLabel.setBorder(new EmptyBorder(15, 0, 15, 0));
        ArrayList<eventModel> events = controller.getAllEvents();

        DefaultListModel<eventModel> eventModels = new DefaultListModel<eventModel>();

        for (int i = 0; i < events.size(); i++) {
            eventModels.add(i, events.get(i));
        }

        usersDisplay = new JList(eventModels);
        usersDisplay.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                eventModel event = (eventModel) value;

                setText("Meeting name: " + controller.getMeetingByID(event.getMeetingID()).getMeetingName() + ". Event ID: " + event.getEventID());

                setFont(new Font("Arial", Font.BOLD, 14));

                return this;
            }
        });
        usersDisplay.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                JList list = (JList) e.getSource();

                eventModel event = (eventModel) list.getSelectedValue();

                String outputStr;

                selectedEvent = (eventModel) event;
                outputStr = "\n====================== EVENT INFORMATION ======================\n\n"
                        + selectedEvent.toString();
                eventInformationDisplay.setText(outputStr);
                eventInformationDisplay.validate();
            }
        });

        JScrollPane scrollable = new JScrollPane();
        scrollable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollable.setViewportView(usersDisplay);

        selectionPanel.add(currentEventsLabel, BorderLayout.NORTH);
        selectionPanel.add(scrollable, BorderLayout.CENTER);

        return selectionPanel;
    }

    private JPanel userManagementPanel() {
        JPanel userManagementPanel = new JPanel(new BorderLayout());

        userManagementPanel.add(displayEventInformationPanel(), BorderLayout.CENTER);
        userManagementPanel.add(archiveButtonPanel(), BorderLayout.SOUTH);

        return userManagementPanel;
    }

    private JPanel displayEventInformationPanel() {
        JPanel displayEventInformationPanel = new JPanel();
        displayEventInformationPanel.setBorder(new EmptyBorder(46, 0, 0, 0));

        eventInformationDisplay = new JTextArea("Information will go here once you select a user");
        eventInformationDisplay.setPreferredSize(new Dimension(600, 550));
        eventInformationDisplay.setMinimumSize(eventInformationDisplay.getPreferredSize());
        eventInformationDisplay.setEditable(false);

        JScrollPane scrollable = new JScrollPane(eventInformationDisplay);
        scrollable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        displayEventInformationPanel.add(scrollable);

        return displayEventInformationPanel;
    }

    private JPanel archiveButtonPanel() {
        JPanel archiveButtonPanel = new JPanel(new GridLayout(2, 1));
        archiveButtonPanel.setBorder(new EmptyBorder(0, 0, 100, 0));

        JPanel archiveButtonPanel1 = new JPanel();
        archiveButton = new JButton("Add user to event");
        archiveButton.setPreferredSize(new Dimension(175, 50));
        archiveButton.addActionListener(this);
        archiveButtonPanel1.add(archiveButton);

        JPanel bsPanel = new JPanel(new GridLayout(1, 2));
        
        JPanel swimmerIDPanel = new JPanel();
        JLabel swimmerIDLabel = new JLabel("Swimmer ID: ");
        swimmerIDField = new JTextField();
        swimmerIDField.setPreferredSize(new Dimension(170, 25));
        swimmerIDField.setMinimumSize(swimmerIDField.getPreferredSize());
        swimmerIDPanel.add(swimmerIDLabel);
        swimmerIDPanel.add(swimmerIDField);
        
        JPanel roundInputPanel = new JPanel();
        JLabel roundLabel = new JLabel("Event round: ");
        roundNumberField = new JTextField();
        roundNumberField.setPreferredSize(new Dimension(170, 25));
        roundInputPanel.add(roundLabel);
        roundInputPanel.add(roundNumberField);
        
        bsPanel.add(swimmerIDPanel);
        bsPanel.add(roundInputPanel);
        
        archiveButtonPanel.add(bsPanel);
        archiveButtonPanel.add(archiveButtonPanel1);

        return archiveButtonPanel;
    }

    public JPanel getManageMembers() {
        return manageEventsMembers;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == archiveButton) {
            if (!swimmerIDField.getText().isBlank() && !roundNumberField.getText().isBlank()) {
                if (!usersDisplay.getSelectedValue().toString().isBlank()) {
                    try {
                        controller.addSwimmerToEvent((eventModel) usersDisplay.getSelectedValue(), Integer.parseInt(swimmerIDField.getText()), Integer.parseInt(roundNumberField.getText()));
                    } catch (NumberFormatException nfe) {
                        JOptionPane.showMessageDialog(manageEventsMembers, "Please only input numbers in the ID or lane number fields", "ERROR - Wrong input", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(manageEventsMembers, "Please select the event you want to add this swimmer to", "ERROR - No event", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(manageEventsMembers, "Please input the ID of the swimmer and the round number you want to add", "ERROR - No swimmer", JOptionPane.ERROR_MESSAGE);
            }

        } else if (e.getSource()
                == backButton) {
            manageEventsMembers.setVisible(false);
            manageEventsMembers.getParent().add(new clubOfficialMenuPanel().getClubOfficialMenuPanel());
            manageEventsMembers.getParent().remove(manageEventsMembers);
        } else if (e.getSource()
                == logOffButton) {
            logInFrame logInFrame = new logInFrame();
            SwingUtilities.getWindowAncestor(manageEventsMembers).dispose();
        }
    }
}
