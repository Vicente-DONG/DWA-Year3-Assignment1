/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Controllers.clubOfficialController;
import Models.parentModel;
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
public class manageParentsPanel implements ActionListener {

    private JPanel manageParentsPanel;
    private JButton backButton;
    private JButton logOffButton;
    private Font f = new Font("Arial", Font.BOLD, 16);
    private JList parentsSelection;
    private JTextArea parentsDisplay;
    private JDateChooser dateOfBirthInput;
    private JButton addParentButton;
    private JButton modifyParentButton;
    private JTextField usernameInput;
    private JTextField familyIDInput;
    private JComboBox genderInput;
    private JTextField addressInput;
    private JTextField contactNumberInput;
    private JTextField emailInput;
    private JTextField nameInput;
    private clubOfficialController controller = clubOfficialController.getInstance();

    public manageParentsPanel() {
        manageParentsPanel = new JPanel(new BorderLayout());

        manageParentsPanel.add(navigationPanel(), BorderLayout.NORTH);
        manageParentsPanel.add(mainContentPanel(), BorderLayout.CENTER);
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

        informationDisplayPanel.add(parentSelectionPanel());
        informationDisplayPanel.add(parentsDisplayPanel());

        return informationDisplayPanel;
    }

    private JPanel parentSelectionPanel() {
        JPanel ParentSelectionPanel = new JPanel(new BorderLayout());

        JLabel selectParentLabel = new JLabel("Select parent");
        selectParentLabel.setFont(new Font("Arial", Font.BOLD, 18));
        selectParentLabel.setHorizontalAlignment(JLabel.CENTER);
        selectParentLabel.setBorder(new EmptyBorder(15, 0, 15, 0));

        ArrayList<parentModel> parents = controller.getAllParents();
        
        DefaultListModel<parentModel> parentList = new DefaultListModel<parentModel>();

        for (int i = 0; i < parents.size(); i++) {
            parentList.add(i, parents.get(i));
        }
        
        parentsSelection = new JList(parentList);
        parentsSelection.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                parentModel parent = (parentModel) value;

                setText("Parent ID: " + parent.getParentID() + " - Name:  " + parent.getName());

                setFont(new Font("Arial", Font.BOLD, 14));

                return this;
            }
        });

        parentsSelection.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                JList list = (JList) e.getSource();

                parentModel parent = (parentModel) list.getSelectedValue();

                Calendar date = Calendar.getInstance();
                date.setTime(java.sql.Date.valueOf(parent.getDOB()));
                
                dateOfBirthInput.setCalendar(date);
                //CHANGE THIS TO BE MORE READABLE WITH THE MANAGER
                parentsDisplay.setText(parent.toString());
            }
        });

        JScrollPane scrollable = new JScrollPane(parentsSelection);
        scrollable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        ParentSelectionPanel.add(selectParentLabel, BorderLayout.NORTH);
        ParentSelectionPanel.add(scrollable, BorderLayout.CENTER);

        return ParentSelectionPanel;
    }

    private JPanel parentsDisplayPanel() {
        JPanel parentsDisplayPanel = new JPanel(new BorderLayout());

        JLabel parentsInformationLabel = new JLabel("Parent Information");
        parentsInformationLabel.setFont(new Font("Arial", Font.BOLD, 18));
        parentsInformationLabel.setHorizontalAlignment(JLabel.CENTER);
        parentsInformationLabel.setBorder(new EmptyBorder(15, 0, 15, 0));

        parentsDisplay = new JTextArea("Parent information will display here when selected");
        parentsDisplay.setEditable(false);

        JScrollPane scrollable = new JScrollPane(parentsDisplay);
        scrollable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        parentsDisplayPanel.add(parentsInformationLabel, BorderLayout.NORTH);
        parentsDisplayPanel.add(scrollable, BorderLayout.CENTER);

        return parentsDisplayPanel;
    }

    private JPanel managementPanel() {
        JPanel managementPanel = new JPanel(new GridLayout(10, 1));
        managementPanel.setBorder(BorderFactory.createTitledBorder(new EmptyBorder(50, 15, 15, 15), "Input parent information", TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 16)));

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
        familyIDInput = new JTextField();
        familyIDInput.setPreferredSize(new Dimension(170, 25));

        passwordInputPanel.add(passwordLabel);
        passwordInputPanel.add(familyIDInput);

        return passwordInputPanel;
    }
    
    private JPanel familyIDInputPanel() {
        JPanel familyIDInputPanel = new JPanel();

        JLabel familyIDLabel = new JLabel("Family id: ");
        familyIDInput = new JTextField();
        familyIDInput.setPreferredSize(new Dimension(170, 25));

        familyIDInputPanel.add(familyIDLabel);
        familyIDInputPanel.add(familyIDInput);

        return familyIDInputPanel;
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
        dateOfBirthInput.setPreferredSize(new Dimension(170, 25));

        dateOfBirthInputPanel.add(dateOfBirthLabel);
        dateOfBirthInputPanel.add(dateOfBirthInput);

        return dateOfBirthInputPanel;
    }

    private JPanel managementButtonsPanel() {
        JPanel managementButtonsPanel = new JPanel();

        addParentButton = new JButton("Create parent");
        addParentButton.setPreferredSize(new Dimension(175, 50));
        addParentButton.addActionListener(this);

        modifyParentButton = new JButton("Modify parent");
        modifyParentButton.setPreferredSize(new Dimension(175, 50));
        modifyParentButton.addActionListener(this);

        managementButtonsPanel.add(addParentButton);
        managementButtonsPanel.add(modifyParentButton);

        return managementButtonsPanel;
    }

    public JPanel getManageParentsPanel() {
        return manageParentsPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addParentButton || e.getSource() == modifyParentButton) {
            boolean readyForModification = false;
            boolean readyForAddition = false;

            if (!usernameInput.getText().isBlank() && !familyIDInput.getText().isBlank() && !familyIDInput.getText().isBlank() && !nameInput.getText().isBlank()
                    && !genderInput.getSelectedItem().toString().isBlank() && !addressInput.getText().isBlank()
                    && !contactNumberInput.getText().isBlank() && !emailInput.getText().isBlank()
                    && !dateOfBirthInput.getDate().toString().isBlank()) {
                readyForAddition = true;
            }

            if (!usernameInput.getText().isBlank() || !familyIDInput.getText().isBlank() || !familyIDInput.getText().isBlank() || !nameInput.getText().isBlank()
                    || !genderInput.getSelectedItem().toString().isBlank() || !addressInput.getText().isBlank()
                    || !contactNumberInput.getText().isBlank() || !emailInput.getText().isBlank()
                    || !dateOfBirthInput.getDate().toString().isBlank() && !parentsSelection.getSelectedValue().toString().isBlank()) {
                readyForModification = true;
            }

            if (e.getSource() == addParentButton) {
                if (readyForAddition) {
                    boolean UsernameAvailable = controller.checkForUsernameAvailabilityParent(usernameInput.getText());
                    if (UsernameAvailable) {
                        controller.addParent(new parentModel(Integer.parseInt(familyIDInput.getText()), usernameInput.getText(), familyIDInput.getText(), nameInput.getText(), controller.areTheyMale(genderInput.getSelectedItem().toString()), addressInput.getText(),
                                contactNumberInput.getText(), emailInput.getText(), dateOfBirthInput.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), false));
                        
                        parentsSelection.validate();
                    } else {
                        JOptionPane.showMessageDialog(manageParentsPanel, "Unfortunately, that username is already taken", "ERROR - Taken username", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(manageParentsPanel, "Please fill all fields to add a parent", "ERROR - Missing fields", JOptionPane.ERROR_MESSAGE);
                }
            } else if (e.getSource() == modifyParentButton) {
                if (readyForModification) {
                    boolean nameAvailable = false;
                    parentModel killMe = (parentModel) parentsSelection.getSelectedValue();

                    if (!usernameInput.getText().isBlank()) {
                        nameAvailable = controller.checkForUsernameAvailabilitySwimmer(usernameInput.getText());
                    }

                    if (!usernameInput.getText().isBlank() && nameAvailable) {
                        killMe.setUsername(usernameInput.getText());
                    } else if (!usernameInput.getText().isBlank() && !nameAvailable) {
                        JOptionPane.showMessageDialog(parentsSelection, "Unfortunately, that username is already taken", "ERROR - Taken username", JOptionPane.ERROR_MESSAGE);
                    }

                    if (!familyIDInput.getText().isBlank()) {
                        killMe.setPassword(familyIDInput.getText());
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

                    if (!dateOfBirthInput.getDate().toString().isBlank()) {
                        killMe.setDOB(dateOfBirthInput.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                    }

                    controller.modifyParent(killMe);
                    parentsDisplay.setText(killMe.toString());
                }
            }
        } else if (e.getSource()
                == backButton) {
            manageParentsPanel.setVisible(false);
            manageParentsPanel.getParent().add(new clubOfficialMenuPanel().getClubOfficialMenuPanel());
            manageParentsPanel.getParent().remove(manageParentsPanel);
        } else if (e.getSource()
                == logOffButton) {
            logInFrame logInFrame = new logInFrame();
            SwingUtilities.getWindowAncestor(manageParentsPanel).dispose();
        }

    }

}

