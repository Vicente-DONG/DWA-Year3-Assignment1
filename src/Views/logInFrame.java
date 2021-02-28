package Views;

import Controllers.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class logInFrame extends JFrame implements ActionListener {

    private JFrame logInFrame = new JFrame();
    private final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    private final Font f = new Font("Arial", Font.PLAIN, 14);
    private JButton logInButton;
    private JTextField usernameField;
    private JTextField passwordField;
    private JComboBox userTypeSelection;
    private JButton logInGuestButton;

    public logInFrame() {
        logInFrame.setTitle("Swimming club - Log In");
        logInFrame.setSize(600, 575);
        logInFrame.setLocation(dim.width / 2 - logInFrame.getSize().width / 2, dim.height / 2 - logInFrame.getSize().height / 2);
        logInFrame.setDefaultCloseOperation(logInFrame.EXIT_ON_CLOSE);
        logInFrame.setResizable(false);

        JPanel mainPanel = setupMainPanel();

        logInFrame.add(mainPanel);

        logInFrame.setVisible(true);
    }

    private JPanel setupMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel logInPanel = setupLoginPanel();
        JPanel logInAsGuest = setupGuestLoginPanel();

        JPanel logInLabelPanel = new JPanel();
        JLabel logInLabel = new JLabel("Log in");
        logInLabel.setFont(new Font("Arial", Font.BOLD, 28));
        logInLabelPanel.add(logInLabel);

        mainPanel.add(logInLabelPanel, BorderLayout.NORTH);
        mainPanel.add(logInPanel, BorderLayout.CENTER);
        mainPanel.add(logInAsGuest, BorderLayout.SOUTH);

        return mainPanel;
    }

    private JPanel setupLoginPanel() {
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));

        JPanel usernamePanel = new JPanel();
        JLabel usernameLabel = new JLabel("Username: ");
        usernameLabel.setFont(f);
        usernameField = new JTextField();
        usernameField.setFont(f);
        usernameField.setPreferredSize(new Dimension(170, 25));
        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameField);

        JPanel passwordPanel = new JPanel();
        JLabel passwordLabel = new JLabel("Password: ");
        passwordLabel.setFont(f);
        passwordField = new JTextField();
        passwordField.setFont(f);
        passwordField.setPreferredSize(new Dimension(170, 25));
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);

        JPanel userTypePanel = new JPanel();
        JLabel userTypeLabel = new JLabel("User type: ");
        userTypeLabel.setFont(f);
        String userTypes[] = {"Swimmer", "Parent", "Club Official"};
        userTypeSelection = new JComboBox(userTypes);
        userTypePanel.add(userTypeLabel);
        userTypePanel.add(userTypeSelection);

        JPanel logInButtonPanel = new JPanel();
        logInButton = new JButton("Log in");
        logInButton.setPreferredSize(new Dimension(150, 40));
        logInButton.setMinimumSize(getPreferredSize());
        logInButton.addActionListener(this);
        logInButtonPanel.add(logInButton);

        loginPanel.add(usernamePanel);
        loginPanel.add(passwordPanel);
        loginPanel.add(userTypePanel);
        loginPanel.add(logInButtonPanel);

        return loginPanel;
    }

    private JPanel setupGuestLoginPanel() {
        JPanel guestLoginPanel = new JPanel();

        JLabel logInAsGuestText = new JLabel("Or log in as a guest:   ");
        logInGuestButton = new JButton("Log in as guest");
        logInGuestButton.setPreferredSize(new Dimension(150, 40));
        logInGuestButton.setMinimumSize(getPreferredSize());
        logInGuestButton.addActionListener(this);

        guestLoginPanel.add(logInAsGuestText);
        guestLoginPanel.add(logInGuestButton);
        return guestLoginPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == logInButton) {
            String username = "";
            String password = "";

            if (!usernameField.getText().isBlank() && !passwordField.getText().isBlank()) {
                username = usernameField.getText();
                password = passwordField.getText();

                if (String.valueOf(userTypeSelection.getSelectedItem()).equalsIgnoreCase("swimmer")) {
                    swimmerController controller = swimmerController.getInstance();

                    if (!username.isBlank() || !password.isBlank()) {
                        boolean authenticated = controller.authenticateUser(password, username);
                        if (!authenticated) {
                            JOptionPane.showMessageDialog(logInFrame, "Username or password incorrect", "ERROR - Failed log in", JOptionPane.ERROR_MESSAGE);
                        } else {
                            boolean archived = controller.swimmerAuthenticated();
                            if (authenticated) {
                                mainFrame main = new mainFrame("swimmer");
                                logInFrame.setVisible(false);
                                logInFrame.dispose();
                            } else if (authenticated && archived) {
                                JOptionPane.showMessageDialog(logInFrame, "Your account has been archived, if this is a mistake please contact the swimming club", "ERROR - Archived user", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                } else if (String.valueOf(userTypeSelection.getSelectedItem()).equalsIgnoreCase("parent")) {
                    parentController controller = parentController.getInstance();

                    if (!username.isBlank() || !password.isBlank()) {
                        boolean authenticated = controller.authenticateUser(password, username);
                        boolean archived = controller.parentArchived();
                        if (!authenticated) {
                            JOptionPane.showMessageDialog(logInFrame, "Username or password incorrect", "ERROR - Failed log in", JOptionPane.ERROR_MESSAGE);
                        } else {
                            if (!archived) {
                                mainFrame main = new mainFrame("parent");
                                logInFrame.setVisible(false);
                                logInFrame.dispose();
                            } else if (authenticated && archived) {
                                JOptionPane.showMessageDialog(logInFrame, "Your account has been archived, if this is a mistake please contact the swimming club", "ERROR - Archived user", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                } else if (String.valueOf(userTypeSelection.getSelectedItem()).equalsIgnoreCase("club official")) {
                    clubOfficialController controller = clubOfficialController.getInstance();

                    if (!username.isBlank() || !password.isBlank()) {
                        boolean authenticated = controller.authenticateUser(password, username);

                        if (authenticated) {
                            mainFrame main = new mainFrame("club official");
                            logInFrame.setVisible(false);
                            logInFrame.dispose();
                        } else {
                            JOptionPane.showMessageDialog(logInFrame, "Username or password incorrect", "ERROR - Failed log in", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(logInFrame, "Either the password or the username is empty", "ERROR - Username or password is empty", JOptionPane.ERROR_MESSAGE);
            }

        } else if (e.getSource() == logInGuestButton) {
            mainFrame main = new mainFrame("guest");
            logInFrame.setVisible(false);
            logInFrame.dispose();
        }
    }
}
