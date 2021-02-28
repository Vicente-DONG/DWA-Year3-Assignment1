/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Controllers.clubOfficialController;
import Models.swimmerModel;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
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

/**
 *
 * @author Vicen
 */
public class manageSwimmersPanel implements ActionListener {

    private JPanel manageSwimmersPanel;
    private JButton backButton;
    private JButton logOffButton;
    private Font f = new Font("Arial", Font.BOLD, 16);
    private JList swimmersSelection;
    private JTextArea swimmersDisplay;
    private JDateChooser dateOfBirthInput;
    private JButton modifySwimmerButton;
    private JButton addSwimmerButton;
    private JTextField emailInput;
    private JTextField usernameInput;
    private JTextField passwordInput;
    private JTextField familyIDInput;
    private JTextField nameInput;
    private JComboBox genderInput;
    private JTextField addressInput;
    private JTextField contactNumberInput;
    private clubOfficialController controller = clubOfficialController.getInstance();

    public manageSwimmersPanel() {
        manageSwimmersPanel = new JPanel(new BorderLayout());

        manageSwimmersPanel.add(navigationPanel(), BorderLayout.NORTH);
        manageSwimmersPanel.add(mainContentPanel(), BorderLayout.CENTER);
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

        informationDisplayPanel.add(swimmerSelectionPanel());
        informationDisplayPanel.add(swimmersDisplayPanel());

        return informationDisplayPanel;
    }

    private JPanel swimmerSelectionPanel() {
        JPanel swimmerSelectionPanel = new JPanel(new BorderLayout());

        JLabel selectSwimmerLabel = new JLabel("Select swimmer");
        selectSwimmerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        selectSwimmerLabel.setHorizontalAlignment(JLabel.CENTER);
        selectSwimmerLabel.setBorder(new EmptyBorder(15, 0, 15, 0));
        ArrayList<swimmerModel> swimmers = controller.getAllSwimmers();

        DefaultListModel<swimmerModel> swimmerList = new DefaultListModel<swimmerModel>();

        for (int i = 0; i < swimmers.size(); i++) {
            swimmerList.add(i, swimmers.get(i));
        }

        swimmersSelection = new JList(swimmerList);
        swimmersSelection.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                swimmerModel swimmer = (swimmerModel) value;

                setText("Swimmer ID: " + swimmer.getSwimmerID() + " - Name:  " + swimmer.getName());

                setFont(new Font("Arial", Font.BOLD, 14));

                return this;
            }
        });

        swimmersSelection.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                JList list = (JList) e.getSource();

                swimmerModel swimmer = (swimmerModel) list.getSelectedValue();

                Calendar date = Calendar.getInstance();
                date.setTime(java.sql.Date.valueOf(swimmer.getDOB()));

                dateOfBirthInput.setCalendar(date);

                swimmersDisplay.setText(swimmer.toString());
            }
        });

        JScrollPane scrollable = new JScrollPane(swimmersSelection);
        scrollable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        swimmerSelectionPanel.add(selectSwimmerLabel, BorderLayout.NORTH);
        swimmerSelectionPanel.add(scrollable, BorderLayout.CENTER);

        return swimmerSelectionPanel;
    }

    private JPanel swimmersDisplayPanel() {
        JPanel swimmersDisplayPanel = new JPanel(new BorderLayout());

        JLabel swimmersInformationLabel = new JLabel("Swimmer Information");
        swimmersInformationLabel.setFont(new Font("Arial", Font.BOLD, 18));
        swimmersInformationLabel.setHorizontalAlignment(JLabel.CENTER);
        swimmersInformationLabel.setBorder(new EmptyBorder(15, 0, 15, 0));

        swimmersDisplay = new JTextArea("swimmer information will display here when selected");
        swimmersDisplay.setEditable(false);

        JScrollPane scrollable = new JScrollPane(swimmersDisplay);
        scrollable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        swimmersDisplayPanel.add(swimmersInformationLabel, BorderLayout.NORTH);
        swimmersDisplayPanel.add(scrollable, BorderLayout.CENTER);

        return swimmersDisplayPanel;
    }

    private JPanel managementPanel() {
        JPanel managementPanel = new JPanel(new GridLayout(10, 1));
        managementPanel.setBorder(BorderFactory.createTitledBorder(new EmptyBorder(50, 15, 15, 15), "Input swimmer information", TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 16)));

        managementPanel.add(UsernameInputPanel());
        managementPanel.add(passwordInputPanel());
        managementPanel.add(familyIDInputPanel());
        managementPanel.add(NameInputPanel());
        managementPanel.add(genderInputPanel());
        managementPanel.add(addressInputPanel());
        managementPanel.add(contactNumberInputPanel());
        managementPanel.add(emailInputPanel());
        managementPanel.add(dateOfBirthInputPanel());
        managementPanel.add(managementButtonsPanel());

        return managementPanel;
    }

    private JPanel UsernameInputPanel() {
        JPanel UsernameInputPanel = new JPanel();

        JLabel usernameLabel = new JLabel("Username: ");
        usernameInput = new JTextField();
        usernameInput.setPreferredSize(new Dimension(170, 25));

        UsernameInputPanel.add(usernameLabel);
        UsernameInputPanel.add(usernameInput);

        return UsernameInputPanel;
    }

    private JPanel passwordInputPanel() {
        JPanel passwordInputPanel = new JPanel();

        JLabel passwordLabel = new JLabel("Password: ");
        passwordInput = new JTextField();
        passwordInput.setPreferredSize(new Dimension(170, 25));

        passwordInputPanel.add(passwordLabel);
        passwordInputPanel.add(passwordInput);

        return passwordInputPanel;
    }

    private JPanel familyIDInputPanel() {
        JPanel familyIDinputPanel = new JPanel();

        JLabel familyIDLabel = new JLabel("Family id: ");
        familyIDInput = new JTextField();
        familyIDInput.setPreferredSize(new Dimension(170, 25));

        familyIDinputPanel.add(familyIDLabel);
        familyIDinputPanel.add(familyIDInput);

        return familyIDinputPanel;
    }

    private JPanel NameInputPanel() {
        JPanel NameInputPanel = new JPanel();

        JLabel nameLabel = new JLabel("Name: ");
        nameInput = new JTextField();
        nameInput.setPreferredSize(new Dimension(170, 25));

        NameInputPanel.add(nameLabel);
        NameInputPanel.add(nameInput);

        return NameInputPanel;
    }

    private JPanel genderInputPanel() {
        JPanel genderInputPanel = new JPanel();

        JLabel genderLabel = new JLabel("Gender: ");
        String[] genders = {"Male", "Female"};
        genderInput = new JComboBox(genders);

        genderInputPanel.add(genderLabel);
        genderInputPanel.add(genderInput);

        return genderInputPanel;
    }

    private JPanel addressInputPanel() {
        JPanel addressInputPanel = new JPanel();

        JLabel addressLabel = new JLabel("Address: ");
        addressInput = new JTextField();
        addressInput.setPreferredSize(new Dimension(170, 25));

        addressInputPanel.add(addressLabel);
        addressInputPanel.add(addressInput);

        return addressInputPanel;
    }

    private JPanel contactNumberInputPanel() {
        JPanel contactNumberInputPanel = new JPanel();

        JLabel contactNumberLabel = new JLabel("Contact number: ");
        contactNumberInput = new JTextField();
        contactNumberInput.setPreferredSize(new Dimension(170, 25));

        contactNumberInputPanel.add(contactNumberLabel);
        contactNumberInputPanel.add(contactNumberInput);

        return contactNumberInputPanel;
    }

    private JPanel emailInputPanel() {
        JPanel emailInputPanel = new JPanel();

        JLabel emailLabel = new JLabel("Email: ");
        emailInput = new JTextField();
        emailInput.setPreferredSize(new Dimension(170, 25));

        emailInputPanel.add(emailLabel);
        emailInputPanel.add(emailInput);

        return emailInputPanel;
    }

    private JPanel dateOfBirthInputPanel() {
        JPanel dateOfBirthInputPanel = new JPanel();

        JLabel dateOfBirthLabel = new JLabel("Date of Birth: ");
        dateOfBirthInput = new JDateChooser();
        dateOfBirthInput.setCalendar(Calendar.getInstance());
        dateOfBirthInput.setPreferredSize(new Dimension(170, 25));

        dateOfBirthInputPanel.add(dateOfBirthLabel);
        dateOfBirthInputPanel.add(dateOfBirthInput);

        return dateOfBirthInputPanel;
    }

    private JPanel managementButtonsPanel() {
        JPanel managementButtonsPanel = new JPanel();

        addSwimmerButton = new JButton("Create swimmer");
        addSwimmerButton.setPreferredSize(new Dimension(175, 50));
        addSwimmerButton.addActionListener(this);

        modifySwimmerButton = new JButton("Modify swimmer");
        modifySwimmerButton.setPreferredSize(new Dimension(175, 50));
        modifySwimmerButton.addActionListener(this);

        managementButtonsPanel.add(addSwimmerButton);
        managementButtonsPanel.add(modifySwimmerButton);

        return managementButtonsPanel;
    }

    public JPanel getManageSwimmersPanel() {
        return manageSwimmersPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addSwimmerButton || e.getSource() == modifySwimmerButton) {
            boolean readyForModification = false;
            boolean readyForAddition = false;

            if (!usernameInput.getText().isBlank() && !passwordInput.getText().isBlank() && !nameInput.getText().isBlank() && !familyIDInput.getText().isBlank()
                    && !String.valueOf(genderInput.getSelectedItem()).isBlank() && !addressInput.getText().isBlank()
                    && !contactNumberInput.getText().isBlank() && !emailInput.getText().isBlank()
                    && !dateOfBirthInput.getDate().toString().isBlank()) {
                readyForAddition = true;
            }

            if (!usernameInput.getText().isBlank() || !passwordInput.getText().isBlank() || !nameInput.getText().isBlank() || !familyIDInput.getText().isBlank()
                    || !String.valueOf(genderInput.getSelectedItem()).isBlank() || !addressInput.getSelectedText().isBlank()
                    || !contactNumberInput.getText().isBlank() || !emailInput.getText().isBlank()
                    || !dateOfBirthInput.getDate().toString().isBlank() && !swimmersSelection.getSelectedValue().toString().isBlank()) {
                readyForModification = true;
            }

            if (e.getSource() == addSwimmerButton) {
                if (readyForAddition) {
                    boolean UsernameAvailable = controller.checkForUsernameAvailabilitySwimmer(usernameInput.getText());
                    if (UsernameAvailable) {
                        boolean familyIDExists = controller.checkForFamilyID(Integer.parseInt(familyIDInput.getText()));
                        if (familyIDExists) {
                            controller.addSwimmer(new swimmerModel(Integer.parseInt(familyIDInput.getText()), usernameInput.getText(), passwordInput.getText(), nameInput.getText(), controller.areTheyMale(genderInput.getSelectedItem().toString()), addressInput.getText(),
                                    contactNumberInput.getText(), emailInput.getText(), dateOfBirthInput.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), false));
                            swimmersSelection.validate();
                        } else {
                            JOptionPane.showMessageDialog(manageSwimmersPanel, "We couldnt find that family ID, please check again or create on if it doesnt exist", "ERROR - Family ID not found", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(manageSwimmersPanel, "Unfortunately, that username is already taken", "ERROR - Taken username", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(manageSwimmersPanel, "Please fill all fields to add a swimmer", "ERROR - Missing fields", JOptionPane.ERROR_MESSAGE);
                }
            } else if (e.getSource() == modifySwimmerButton) {
                if (readyForModification) {
                    boolean nameAvailable = false;
                    swimmerModel killMe = (swimmerModel) swimmersSelection.getSelectedValue();

                    if (!usernameInput.getText().isBlank()) {
                        nameAvailable = controller.checkForUsernameAvailabilitySwimmer(usernameInput.getText());
                    }

                    if (!usernameInput.getText().isBlank() && nameAvailable) {
                        killMe.setUsername(usernameInput.getText());
                    } else if (!usernameInput.getText().isBlank() && !nameAvailable) {
                        JOptionPane.showMessageDialog(manageSwimmersPanel, "Unfortunately, that username is already taken", "ERROR - Taken username", JOptionPane.ERROR_MESSAGE);
                    }

                    if (!familyIDInput.getText().isBlank()) {
                        killMe.setFamilyGroupID(Integer.parseInt(familyIDInput.getText()));
                    }

                    if (!passwordInput.getText().isBlank()) {
                        killMe.setPassword(passwordInput.getText());
                    }

                    if (!nameInput.getText().isBlank()) {
                        killMe.setName(nameInput.getText());
                    }

                    if (!genderInput.getSelectedItem().toString().isBlank()) {
                        killMe.setAreTheyMale(controller.areTheyMale(genderInput.getSelectedItem().toString()));
                    }

                    if (!emailInput.getText().isBlank()) {
                        killMe.setEmail(emailInput.getSelectedText());
                    }

                    if (!addressInput.getText().isBlank()) {
                        killMe.setAddress(addressInput.getText());
                    }

                    if (!contactNumberInput.getText().isBlank()) {
                        killMe.setContactNumber(contactNumberInput.getText());
                    }

                    if (dateOfBirthInput == null || !dateOfBirthInput.getDate().toString().isBlank()) {
                        killMe.setDOB(dateOfBirthInput.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                    }

                    controller.modifySwimmer(killMe);
                    swimmersDisplay.setText(killMe.toString());

                } else {
                    JOptionPane.showMessageDialog(manageSwimmersPanel, "Please fill at least one field and select a swimmer to change it", "ERROR - Missing fields", JOptionPane.ERROR_MESSAGE);
                }
            }

        } else if (e.getSource() == backButton) {
            manageSwimmersPanel.setVisible(false);
            manageSwimmersPanel.getParent().add(new clubOfficialMenuPanel().getClubOfficialMenuPanel());
            manageSwimmersPanel.getParent().remove(manageSwimmersPanel);
        } else if (e.getSource() == logOffButton) {
            logInFrame logInFrame = new logInFrame();
            SwingUtilities.getWindowAncestor(manageSwimmersPanel).dispose();
        }
    }
}
