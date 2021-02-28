package Views;

import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class mainFrame extends JFrame {

    private GraphicsConfiguration gc;
    private JFrame mainFrame;
    private final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

    public mainFrame(String userType) {
        mainFrame = new JFrame(gc);
        mainFrame.setTitle("Swimming club");
        mainFrame.setSize(1400, 900);
        mainFrame.setLocation(dim.width / 2 - mainFrame.getSize().width / 2, dim.height / 2 - mainFrame.getSize().height / 2);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);
        
        if (userType.equalsIgnoreCase("swimmer")) {
            mainFrame.add(new swimmerMenuPanel().getswimmerMenuPanel());
        } else if (userType.equalsIgnoreCase("parent")) {
            mainFrame.add(new parentMenuPanel().getParentMenuPanel());
        } else if (userType.equalsIgnoreCase("club official")) {
            mainFrame.add(new clubOfficialMenuPanel().getClubOfficialMenuPanel());
        } else if (userType.equalsIgnoreCase("guest")) {
            mainFrame.add(new guestMenuPanel().getGuestMenuPanel());
        } else {
            System.out.println("THERE IS A PROBLEM IN THE SYSTEM");
        }

        mainFrame.setVisible(true);
    }

    public void closeFrame() {
        mainFrame.dispose();
    }
}
