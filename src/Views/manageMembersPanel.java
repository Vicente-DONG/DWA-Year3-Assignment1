package Views;

import Controllers.clubOfficialController;
import Models.*;
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
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class manageMembersPanel implements ActionListener {

    private JPanel manageMembersPanel;
    private JButton backButton;
    private JButton logOffButton;
    private JList usersDisplay;
    private JTextArea userInformationDisplay;
    private swimmerModel selectedSwimmer;
    private parentModel selectedParent;
    private JButton archiveButton;
    private clubOfficialController controller = clubOfficialController.getInstance();

    public manageMembersPanel() {
        manageMembersPanel = new JPanel(new BorderLayout());

        manageMembersPanel.add(navigationPanel(), BorderLayout.NORTH);
        manageMembersPanel.add(mainContentPanel(), BorderLayout.CENTER);
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

        JLabel clubUsersLabel = new JLabel("Club's users");
        clubUsersLabel.setFont(new Font("Arial", Font.BOLD, 18));
        clubUsersLabel.setHorizontalAlignment(JLabel.CENTER);
        clubUsersLabel.setBorder(new EmptyBorder(15, 0, 15, 0));

        ArrayList<swimmerModel> swimmers = controller.getAllSwimmers();
        ArrayList<parentModel> parents = controller.getAllParents();
        ArrayList<Member> members = new ArrayList<>();

        members.addAll(swimmers);
        members.addAll(parents);

        DefaultListModel<Member> membersList = new DefaultListModel<Member>();

        for (int i = 0; i < members.size(); i++) {
            membersList.add(i, members.get(i));
        }

        usersDisplay = new JList(membersList);
        usersDisplay.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                Member member = (Member) value;
                String userType = "";

                if (swimmerModel.class.equals(value.getClass())) {
                    userType = "swimmer";
                } else if (parentModel.class.equals(value.getClass())) {
                    userType = "parent";
                }

                setText("User type: " + userType + " - Name: " + member.getName());

                setFont(new Font("Arial", Font.BOLD, 14));

                return this;
            }
        });
        usersDisplay.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                JList list = (JList) e.getSource();

                Member member = (Member) list.getSelectedValue();

                String outputStr;

                if (swimmerModel.class.equals(member.getClass())) {
                    selectedSwimmer = (swimmerModel) member;
                    outputStr = "\n====================== SWIMMER INFORMATION ======================\n\n"
                            + selectedSwimmer.toString();
                    userInformationDisplay.setText(outputStr);
                    userInformationDisplay.validate();
                } else if (parentModel.class.equals(member.getClass())) {
                    selectedParent = (parentModel) member;
                    outputStr = "\n====================== PARENT INFORMATION ======================\n\n"
                            + selectedParent.toString();
                    userInformationDisplay.setText(outputStr);
                    userInformationDisplay.validate();
                }
            }
        });

        JScrollPane scrollable = new JScrollPane();
        scrollable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollable.setViewportView(usersDisplay);

        selectionPanel.add(clubUsersLabel, BorderLayout.NORTH);
        selectionPanel.add(scrollable, BorderLayout.CENTER);

        return selectionPanel;
    }

    private JPanel userManagementPanel() {
        JPanel userManagementPanel = new JPanel(new BorderLayout());

        userManagementPanel.add(displayUserInformationPanel(), BorderLayout.CENTER);
        userManagementPanel.add(archiveButtonPanel(), BorderLayout.SOUTH);

        return userManagementPanel;
    }

    private JPanel displayUserInformationPanel() {
        JPanel displayUserInformationPanel = new JPanel();
        displayUserInformationPanel.setBorder(new EmptyBorder(46, 0, 0, 0));

        userInformationDisplay = new JTextArea("Information will go here once you select a user");
        userInformationDisplay.setPreferredSize(new Dimension(600, 550));
        userInformationDisplay.setMinimumSize(userInformationDisplay.getPreferredSize());
        userInformationDisplay.setEditable(false);

        JScrollPane scrollable = new JScrollPane(userInformationDisplay);
        scrollable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        displayUserInformationPanel.add(scrollable);

        return displayUserInformationPanel;
    }

    private JPanel archiveButtonPanel() {
        JPanel archiveButtonPanel = new JPanel();
        archiveButtonPanel.setBorder(new EmptyBorder(0, 0, 100, 0));

        archiveButton = new JButton("Archive/Unarchive user");
        archiveButton.setPreferredSize(new Dimension(175, 50));
        archiveButton.addActionListener(this);

        archiveButtonPanel.add(archiveButton);

        return archiveButtonPanel;
    }

    public JPanel getManageMemberPanel() {
        return manageMembersPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == archiveButton) {
            if (!usersDisplay.getSelectedValue().toString().isBlank()) {
                Member user = (Member) usersDisplay.getSelectedValue();

                if (user.isArchived()) {
                    user.setArchived(false);
                    String outputStr = "";
                    if (swimmerModel.class.equals(user.getClass())) {
                        selectedSwimmer = (swimmerModel) user;
                        outputStr = "\n====================== SWIMMER INFORMATION ======================\n\n"
                                + selectedSwimmer.toString();
                        userInformationDisplay.setText(outputStr);
                        userInformationDisplay.validate();
                    } else if (parentModel.class.equals(user.getClass())) {
                        selectedParent = (parentModel) user;
                        outputStr = "\n====================== PARENT INFORMATION ======================\n\n"
                                + selectedParent.toString();
                        userInformationDisplay.setText(outputStr);
                        userInformationDisplay.validate();
                    }
                } else {
                    user.setArchived(true);
                    String outputStr = "";
                    if (swimmerModel.class.equals(user.getClass())) {
                        selectedSwimmer = (swimmerModel) user;
                        outputStr = "\n====================== SWIMMER INFORMATION ======================\n\n"
                                + selectedSwimmer.toString();
                        userInformationDisplay.setText(outputStr);
                        userInformationDisplay.validate();
                    } else if (parentModel.class.equals(user.getClass())) {
                        selectedParent = (parentModel) user;
                        outputStr = "\n====================== PARENT INFORMATION ======================\n\n"
                                + selectedParent.toString();
                        userInformationDisplay.setText(outputStr);
                        userInformationDisplay.validate();
                    }
                }

                if (swimmerModel.class.equals(user)) {
                    controller.updateSwimmer((swimmerModel) user);
                } else if (parentModel.class.equals(user)) {
                    controller.updateParent((parentModel) user);
                }

            } else {
                JOptionPane.showMessageDialog(manageMembersPanel, "You need to select a swimmer to archive first", "ERROR - No user selected", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == backButton) {
            manageMembersPanel.setVisible(false);
            manageMembersPanel.getParent().add(new clubOfficialMenuPanel().getClubOfficialMenuPanel());
            manageMembersPanel.getParent().remove(manageMembersPanel);
        } else if (e.getSource() == logOffButton) {
            logInFrame logInFrame = new logInFrame();
            SwingUtilities.getWindowAncestor(manageMembersPanel).dispose();
        }
    }
}
