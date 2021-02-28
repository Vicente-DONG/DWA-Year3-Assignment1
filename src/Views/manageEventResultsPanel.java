/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Controllers.clubOfficialController;
import Models.eventModel;
import Models.eventResultModel;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JCheckBox;
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

/**
 *
 * @author Vicen
 */
public class manageEventResultsPanel implements ActionListener {

    private JPanel manageEventResultsPanel;
    private JButton backButton;
    private JButton logOffButton;
    private Font f = new Font("Arial", Font.BOLD, 16);
    private JList eventResultsSelection;
    private JTextArea eventResultsDisplay;
    private JButton addEventResultButton;
    private JButton modifyEventResult;
    private JTextArea reasonInput;
    private JTextField milisecondsInput;
    private JTextField secondsInput;
    private JTextField minutesInput;
    private JCheckBox haveTheyFinished;
    private JTextField eventIDInput;
    private JTextField roundInput;
    private JTextField laneNumberInput;
    private JTextField swimmerIDInput;
    private clubOfficialController controller = clubOfficialController.getInstance();

    public manageEventResultsPanel() {
        manageEventResultsPanel = new JPanel(new BorderLayout());

        manageEventResultsPanel.add(navigationPanel(), BorderLayout.NORTH);
        manageEventResultsPanel.add(mainContentPanel(), BorderLayout.CENTER);
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

        informationDisplayPanel.add(eventResultSelectionPanel());
        informationDisplayPanel.add(eventResultsDisplayPanel());

        return informationDisplayPanel;
    }

    private JPanel eventResultSelectionPanel() {
        JPanel eventResultSelectionPanel = new JPanel(new BorderLayout());

        JLabel selectEventResultLabel = new JLabel("Select event result");
        selectEventResultLabel.setFont(new Font("Arial", Font.BOLD, 18));
        selectEventResultLabel.setHorizontalAlignment(JLabel.CENTER);
        selectEventResultLabel.setBorder(new EmptyBorder(15, 0, 15, 0));
        ArrayList<eventResultModel> eventResults = controller.getAllEventResults();

        DefaultListModel<eventResultModel> eventResultList = new DefaultListModel<eventResultModel>();

        for (int i = 0; i < eventResults.size(); i++) {
            eventResultList.add(i, eventResults.get(i));
        }

        eventResultsSelection = new JList(eventResultList);
        eventResultsSelection.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                eventResultModel eventResult = (eventResultModel) value;

                setText("Event result ID: " + eventResult.getEventResultID() + " - Event ID:  " + eventResult.getEventID()); //Make this more readable with manager

                setFont(new Font("Arial", Font.BOLD, 14));

                return this;
            }
        });

        eventResultsSelection.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                JList list = (JList) e.getSource();

                eventResultModel eventResult = (eventResultModel) list.getSelectedValue();

                //CHANGE THIS TO BE MORE READABLE WITH THE MANAGER
                eventResultsDisplay.setText(eventResult.toString());
            }
        });

        JScrollPane scrollable = new JScrollPane(eventResultsSelection);
        scrollable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        eventResultSelectionPanel.add(selectEventResultLabel, BorderLayout.NORTH);
        eventResultSelectionPanel.add(scrollable, BorderLayout.CENTER);

        return eventResultSelectionPanel;
    }

    private JPanel eventResultsDisplayPanel() {
        JPanel eventResultsDisplayPanel = new JPanel(new BorderLayout());

        JLabel eventResultsInformationLabel = new JLabel("Event result Information");
        eventResultsInformationLabel.setFont(new Font("Arial", Font.BOLD, 18));
        eventResultsInformationLabel.setHorizontalAlignment(JLabel.CENTER);
        eventResultsInformationLabel.setBorder(new EmptyBorder(15, 0, 15, 0));

        eventResultsDisplay = new JTextArea("Event results information will display here when selected");
        eventResultsDisplay.setEditable(false);

        JScrollPane scrollable = new JScrollPane(eventResultsDisplay);
        scrollable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        eventResultsDisplayPanel.add(eventResultsInformationLabel, BorderLayout.NORTH);
        eventResultsDisplayPanel.add(scrollable, BorderLayout.CENTER);

        return eventResultsDisplayPanel;
    }

    private JPanel managementPanel() {
        JPanel managementPanel = new JPanel(new GridLayout(10, 1));
        managementPanel.setBorder(BorderFactory.createTitledBorder(new EmptyBorder(50, 15, 15, 15), "Input event results information", TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 16)));

        managementPanel.add(eventIDInputPanel());
        managementPanel.add(swimmerIDInputPanel());
        managementPanel.add(roundInputPanel());
        managementPanel.add(laneNumberInputPanel());
        managementPanel.add(haveTheyFinishedInputPanel());
        managementPanel.add(minutesInputPanel());
        managementPanel.add(secondsInputPanel());
        managementPanel.add(milisecondsInputPanel());
        managementPanel.add(reasonInputPanel());
        managementPanel.add(managementButtonsPanel());

        return managementPanel;
    }

    private JPanel eventIDInputPanel() {
        JPanel eventIDInputPanel = new JPanel();

        JLabel eventIDLabel = new JLabel("Event ID: ");
        eventIDInput = new JTextField();
        eventIDInput.setPreferredSize(new Dimension(170, 25));

        eventIDInputPanel.add(eventIDLabel);
        eventIDInputPanel.add(eventIDInput);

        return eventIDInputPanel;
    }

    private JPanel swimmerIDInputPanel() {
        JPanel swimmerIDInputPanel = new JPanel();

        JLabel swimmerIDLabel = new JLabel("Swimmer ID: ");
        swimmerIDInput = new JTextField();
        swimmerIDInput.setPreferredSize(new Dimension(170, 25));

        swimmerIDInputPanel.add(swimmerIDLabel);
        swimmerIDInputPanel.add(swimmerIDInput);

        return swimmerIDInputPanel;
    }

    private JPanel roundInputPanel() {
        JPanel roundInputPanel = new JPanel();

        JLabel roundLabel = new JLabel("Round: ");
        roundInput = new JTextField();
        roundInput.setPreferredSize(new Dimension(170, 25));

        roundInputPanel.add(roundLabel);
        roundInputPanel.add(roundInput);

        return roundInputPanel;
    }

    private JPanel laneNumberInputPanel() {
        JPanel laneNumberInputPanel = new JPanel();

        JLabel laneNumberLabel = new JLabel("Lane number: ");
        laneNumberInput = new JTextField();
        laneNumberInput.setPreferredSize(new Dimension(170, 25));

        laneNumberInputPanel.add(laneNumberLabel);
        laneNumberInputPanel.add(laneNumberInput);

        return laneNumberInputPanel;
    }

    private JPanel haveTheyFinishedInputPanel() {
        JPanel haveTheyFinishedInputPanel = new JPanel();

        haveTheyFinished = new JCheckBox("Have they finished the event?");

        haveTheyFinishedInputPanel.add(haveTheyFinished);

        return haveTheyFinishedInputPanel;
    }

    private JPanel minutesInputPanel() {
        JPanel minutesInputPanel = new JPanel();

        JLabel minutesLabel = new JLabel("Minutes taken: ");
        minutesInput = new JTextField();
        minutesInput.setPreferredSize(new Dimension(170, 25));

        minutesInputPanel.add(minutesLabel);
        minutesInputPanel.add(minutesInput);

        return minutesInputPanel;
    }

    private JPanel secondsInputPanel() {
        JPanel secondsInputPanel = new JPanel();

        JLabel secondsLabel = new JLabel("Seconds taken: ");
        secondsInput = new JTextField();
        secondsInput.setPreferredSize(new Dimension(170, 25));

        secondsInputPanel.add(secondsLabel);
        secondsInputPanel.add(secondsInput);

        return secondsInputPanel;
    }

    private JPanel milisecondsInputPanel() {
        JPanel milisecondsInputPanel = new JPanel();

        JLabel milisecondsLabel = new JLabel("Miliseconds taken: ");
        milisecondsInput = new JTextField();
        milisecondsInput.setPreferredSize(new Dimension(170, 25));

        milisecondsInputPanel.add(milisecondsLabel);
        milisecondsInputPanel.add(milisecondsInput);

        return milisecondsInputPanel;
    }

    private JPanel reasonInputPanel() {
        JPanel reasonInputPanel = new JPanel();

        JLabel reasonLabel = new JLabel("Reason (if they havent finished): ");
        reasonInput = new JTextArea();
        reasonInput.setPreferredSize(new Dimension(170, 60));
        reasonInput.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        reasonInputPanel.add(reasonLabel);
        reasonInputPanel.add(reasonInput);

        return reasonInputPanel;
    }

    private JPanel managementButtonsPanel() {
        JPanel managementButtonsPanel = new JPanel();

        addEventResultButton = new JButton("Create event result");
        addEventResultButton.setPreferredSize(new Dimension(175, 50));
        addEventResultButton.addActionListener(this);

        modifyEventResult = new JButton("Modify event result");
        modifyEventResult.setPreferredSize(new Dimension(175, 50));
        modifyEventResult.addActionListener(this);

        managementButtonsPanel.add(addEventResultButton);
        managementButtonsPanel.add(modifyEventResult);

        return managementButtonsPanel;
    }

    public JPanel getManageEnvetResultsPanel() {
        return manageEventResultsPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addEventResultButton || e.getSource() == modifyEventResult) {
            boolean readyForAddition = false;
            boolean readyForModification = false;
            eventResultModel eventResult = (eventResultModel) eventResultsSelection.getSelectedValue();

            if (!eventIDInput.getText().isBlank() && !roundInput.getText().isBlank() && !swimmerIDInput.getText().isBlank() && !laneNumberInput.getText().isBlank()) {
                if (haveTheyFinished.isSelected() == true) {
                    if (!milisecondsInput.getText().isBlank() && !secondsInput.getText().isBlank() && !minutesInput.getText().isBlank()) {
                        readyForAddition = true;
                    } else {
                        JOptionPane.showMessageDialog(manageEventResultsPanel, "Please input the timing for the swimmer", "ERROR - No timing inputted", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    if (!reasonInput.getText().isBlank()) {
                        readyForAddition = true;
                    } else {
                        JOptionPane.showMessageDialog(manageEventResultsPanel, "Please input a reason why the swimmer hasnt finished", "ERROR - No reason inputted", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

            if (!eventIDInput.getText().isBlank() || !roundInput.getText().isBlank() || !swimmerIDInput.getText().isBlank() || !laneNumberInput.getText().isBlank()) {
                readyForModification = true;
            } else if (!reasonInput.getText().isBlank()) {
                if (haveTheyFinished.isSelected() == false) {
                    JOptionPane.showMessageDialog(manageEventResultsPanel, "To add or change the reason the swimmer hasnt finished please tick if they have finished or not", "ERROR - Missing input", JOptionPane.ERROR_MESSAGE);
                } else {
                    readyForModification = true;
                }
            } else if (!milisecondsInput.getText().isBlank() || !secondsInput.getText().isBlank() || !minutesInput.getText().isBlank()) {
                if (haveTheyFinished.isSelected() == false) {
                    JOptionPane.showMessageDialog(manageEventResultsPanel, "To add or change the swimmer's timing please tick if they have finished or not", "ERROR - Missing input", JOptionPane.ERROR_MESSAGE);
                } else {
                    readyForModification = true;
                }
            }

            if (e.getSource() == addEventResultButton) {

                if (readyForAddition) {
                    try {
                        boolean swimmerExists = controller.checkSwimmerExists(Integer.parseInt(swimmerIDInput.getText()));

                        if (swimmerExists) {
                            try {
                                if (haveTheyFinished.isSelected()) {
                                    controller.addEventResultToEvent(new eventResultModel(Integer.parseInt(eventIDInput.getText()), controller.getSwimmerByID(Integer.parseInt(swimmerIDInput.getText())).getName(),
                                            Integer.parseInt(roundInput.getText()), Integer.parseInt(laneNumberInput.getText()), true, Integer.parseInt(minutesInput.getText()), Integer.parseInt(secondsInput.getText()),
                                            Integer.parseInt(milisecondsInput.getText()), ""));
                                    ArrayList<eventResultModel> eventResults = controller.getAllEventResults();

                                    DefaultListModel<eventResultModel> eventResultList = new DefaultListModel<eventResultModel>();

                                    for (int i = 0; i < eventResults.size(); i++) {
                                        eventResultList.add(i, eventResults.get(i));
                                    }
                                    eventResultsSelection.clearSelection();
                                    eventResultsSelection.setModel(eventResultList);
                                    eventResultsSelection.clearSelection();
                                } else {
                                    controller.addEventResultToEvent(new eventResultModel(Integer.parseInt(eventIDInput.getText()), controller.getSwimmerByID(Integer.parseInt(swimmerIDInput.getText())).getName(),
                                            Integer.parseInt(roundInput.getText()), Integer.parseInt(laneNumberInput.getText()), false, 0, 0, 0, reasonInput.getText()));
                                    ArrayList<eventResultModel> eventResults = controller.getAllEventResults();
                                    DefaultListModel<eventResultModel> eventResultList = new DefaultListModel<eventResultModel>();

                                    for (int i = 0; i < eventResults.size(); i++) {
                                        eventResultList.add(i, eventResults.get(i));
                                    }
                                    eventResultsSelection.clearSelection();
                                    eventResultsSelection.setModel(eventResultList);
                                    eventResultsSelection.clearSelection();
                                }

                            } catch (NumberFormatException nfe) {
                                JOptionPane.showMessageDialog(manageEventResultsPanel, "Please fill the following fields only with numbers: round, minutes, seconds and miliseconds", "ERROR - Missing inputs", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(manageEventResultsPanel, "We couldnt find that swimmer", "ERROR - Swimmer missing", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException nfe) {
                        JOptionPane.showMessageDialog(manageEventResultsPanel, "Please only use numbers for swimmer ID", "ERROR - Missing input", JOptionPane.ERROR_MESSAGE);
                    }

                } else {
                    JOptionPane.showMessageDialog(manageEventResultsPanel, "Please fill all fields if you want to create a new event result", "ERROR - Missing inputs", JOptionPane.ERROR_MESSAGE);
                }
            } else if (e.getSource() == modifyEventResult) {
                if (readyForModification) {
                    eventResultModel newEventResult = (eventResultModel) eventResultsSelection.getSelectedValue();

                    if (!eventIDInput.getText().isBlank()) {
                        try {
                            boolean exists = controller.checkEventExists(Integer.parseInt(eventIDInput.getText()));

                            if (exists) {
                                newEventResult.setEventID(Integer.parseInt(eventIDInput.getText()));
                            } else {
                                JOptionPane.showMessageDialog(manageEventResultsPanel, "Event id not found, please try again", "ERROR - Event not found", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (NumberFormatException nfe) {
                            JOptionPane.showMessageDialog(manageEventResultsPanel, "Please only put numbers in the event ID field", "ERROR - Wrong input", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                    if (!swimmerIDInput.getText().isBlank()) {
                        try {
                            boolean exists = controller.checkSwimmerExists(Integer.parseInt(swimmerIDInput.getText()));

                            if (exists) {
                                newEventResult.setSwimmerName(controller.getSwimmerByID(Integer.parseInt(swimmerIDInput.getText())).getName());
                            } else {
                                JOptionPane.showMessageDialog(manageEventResultsPanel, "Swimmer id not found, please try again", "ERROR - Swimmer not found", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (NumberFormatException nfe) {
                            JOptionPane.showMessageDialog(manageEventResultsPanel, "Please only put numbers in the swimmer ID field", "ERROR - Wrong input", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                    if (!roundInput.getText().isBlank()) {
                        try {
                            newEventResult.setRound(Integer.parseInt(roundInput.getText()));
                        } catch (NumberFormatException nfe) {
                            JOptionPane.showMessageDialog(manageEventResultsPanel, "Please only put numbers in the round number field", "ERROR - Wrong input", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                    if (!laneNumberInput.getText().isBlank()) {
                        try {
                            newEventResult.setLaneNumber(Integer.parseInt(laneNumberInput.getText()));
                        } catch (NumberFormatException nfe) {
                            JOptionPane.showMessageDialog(manageEventResultsPanel, "Please only put numbers in the lane number field", "ERROR - Wrong input", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                    if (haveTheyFinished.isSelected()) {
                        if (!minutesInput.getText().isBlank() || !secondsInput.getText().isBlank() || !milisecondsInput.getText().isBlank()) {
                            try {
                                newEventResult.setMinutes(Integer.parseInt(minutesInput.getText()));
                                newEventResult.setSeconds(Integer.parseInt(secondsInput.getText()));
                                newEventResult.setMiliseconds(Integer.parseInt(milisecondsInput.getText()));
                                newEventResult.setHaveTheyFinished(true);
                                newEventResult.setReason("");
                            } catch (NumberFormatException nfe) {
                                JOptionPane.showMessageDialog(manageEventResultsPanel, "Please only put numbers in the minutes, seconds and miliseconds field", "ERROR - Wrong input", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    } else {
                        newEventResult.setMinutes(0);
                        newEventResult.setSeconds(0);
                        newEventResult.setMiliseconds(0);
                        newEventResult.setHaveTheyFinished(false);
                        newEventResult.setReason(reasonInput.getText());
                    }

                    controller.modifyEventResult(newEventResult);
                    eventResultsSelection.validate();
                    eventResultsDisplay.setText(newEventResult.toString());

                } else {
                    JOptionPane.showMessageDialog(manageEventResultsPanel, "Please fill at least one field to modify", "ERROR - Missing input", JOptionPane.ERROR_MESSAGE);
                }
            }

        } else if (e.getSource()
                == backButton) {
            manageEventResultsPanel.setVisible(false);
            manageEventResultsPanel.getParent().add(new clubOfficialMenuPanel().getClubOfficialMenuPanel());
            manageEventResultsPanel.getParent().remove(manageEventResultsPanel);
        } else if (e.getSource()
                == logOffButton) {
            logInFrame logInFrame = new logInFrame();
            SwingUtilities.getWindowAncestor(manageEventResultsPanel).dispose();
        }
    }
}
