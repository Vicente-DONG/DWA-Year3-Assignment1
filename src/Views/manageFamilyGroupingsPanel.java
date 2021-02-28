package Views;

import Models.familyGroupModel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class manageFamilyGroupingsPanel implements ActionListener {

    private JPanel manageFamilyGroupingsPanel;
    private JButton backButton;
    private JButton logOffButton;
    private Font f = new Font("Arial", Font.BOLD, 16);
    private JList familyGroupSelection;
    private JTextArea familyGroupDisplay;

    public manageFamilyGroupingsPanel() {
        manageFamilyGroupingsPanel = new JPanel(new BorderLayout());

        manageFamilyGroupingsPanel.add(navigationPanel(), BorderLayout.NORTH);
        manageFamilyGroupingsPanel.add(mainContentPanel(), BorderLayout.CENTER);
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

        informationDisplayPanel.add(familyGroupSelectionPanel());
        informationDisplayPanel.add(familyGroupDisplayPanel());

        return informationDisplayPanel;
    }

    private JPanel familyGroupSelectionPanel() {
        JPanel familyGroupSelectionPanel = new JPanel(new BorderLayout());

        JLabel selectFamilyGroupLabel = new JLabel("Select family group");
        selectFamilyGroupLabel.setFont(new Font("Arial", Font.BOLD, 18));
        selectFamilyGroupLabel.setHorizontalAlignment(JLabel.CENTER);
        selectFamilyGroupLabel.setBorder(new EmptyBorder(15, 0, 15, 0));

        DefaultListModel<familyGroupModel> familyGroupList = new DefaultListModel<familyGroupModel>();

        familyGroupSelection = new JList(familyGroupList);
        familyGroupSelection.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                familyGroupModel familyGroup = (familyGroupModel) value;

                setText("Family group ID: " + familyGroup.getFamilyGroupID() + " - Parents ID are "
                        + familyGroup.getParentOneID() + " and " + familyGroup.getParentTwoID());

                setFont(new Font("Arial", Font.BOLD, 14));

                return this;
            }
        });

        familyGroupSelection.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                JList list = (JList) e.getSource();

                familyGroupModel familyGroup = (familyGroupModel) list.getSelectedValue();

                //CHANGE THIS TO BE MORE READABLE WITH THE MANAGER
                familyGroupDisplay.setText(familyGroup.toString());
            }
        });

        JScrollPane scrollable = new JScrollPane(familyGroupSelection);
        scrollable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        familyGroupSelectionPanel.add(selectFamilyGroupLabel, BorderLayout.NORTH);
        familyGroupSelectionPanel.add(scrollable, BorderLayout.CENTER);

        return familyGroupSelectionPanel;
    }

    private JPanel familyGroupDisplayPanel() {
        JPanel familyGroupDisplayPanel = new JPanel(new BorderLayout());

        JLabel familyGroupInformation = new JLabel("Family Group Information");
        familyGroupInformation.setFont(new Font("Arial", Font.BOLD, 18));
        familyGroupInformation.setHorizontalAlignment(JLabel.CENTER);
        familyGroupInformation.setBorder(new EmptyBorder(15, 0, 15, 0));
        
        familyGroupDisplay = new JTextArea("Family group information will display here when selected");
        familyGroupDisplay.setEditable(false);
        
        JScrollPane scrollable = new JScrollPane(familyGroupDisplay);
        scrollable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        familyGroupDisplayPanel.add(familyGroupInformation, BorderLayout.NORTH);
        familyGroupDisplayPanel.add(scrollable, BorderLayout.CENTER);

        return familyGroupDisplayPanel;
    }

    private JPanel managementPanel() {
        JPanel managementPanel = new JPanel(new GridLayout(8, 1));
        managementPanel.setBorder(BorderFactory.createTitledBorder(new EmptyBorder(50, 15, 15, 15), "Input family group information", TitledBorder.LEFT, TitledBorder.TOP, new Font("Arial", Font.BOLD, 16)));
        
        managementPanel.add(parentOneInputPanel());
        managementPanel.add(parentTwoInputPanel());
        managementPanel.add(childOneInputPanel());
        managementPanel.add(childTwoInputPanel());
        managementPanel.add(childThreeInputPanel());
        managementPanel.add(childFourInputPanel());
        managementPanel.add(childFiveInputPanel());
        managementPanel.add(managementButtonsPanel());
        
        return managementPanel;
    }
    
    private JPanel parentOneInputPanel()
    {
        JPanel parentOneInputPanel = new JPanel();
        
        JLabel parentOneLabel = new JLabel("First parent user ID: ");
        JTextField parentOneIDInput = new JTextField();
        parentOneIDInput.setPreferredSize(new Dimension(170, 25));
        
        parentOneInputPanel.add(parentOneLabel);
        parentOneInputPanel.add(parentOneIDInput);
        
        return parentOneInputPanel;
    }
    
    private JPanel parentTwoInputPanel()
    {
        JPanel parentTwoInputPanel = new JPanel();
        
        JLabel parentTwoLabel = new JLabel("Second parent user ID: ");
        JTextField parentTwoIDInput = new JTextField();
        parentTwoIDInput.setPreferredSize(new Dimension(170, 25));
        
        parentTwoInputPanel.add(parentTwoLabel);
        parentTwoInputPanel.add(parentTwoIDInput);
        
        return parentTwoInputPanel;
    }
    
    private JPanel childOneInputPanel()
    {
        JPanel childOneInputPanel = new JPanel();
        
        JLabel childOneLabel = new JLabel("First child user ID: ");
        JTextField childOneIDInput = new JTextField();
        childOneIDInput.setPreferredSize(new Dimension(170, 25));
        
        childOneInputPanel.add(childOneLabel);
        childOneInputPanel.add(childOneIDInput);
        
        return childOneInputPanel;
    }
    
    private JPanel childTwoInputPanel()
    {
        JPanel childTwoInputPanel = new JPanel();
        
        JLabel childTwoLabel = new JLabel("Second child user ID: ");
        JTextField childTwoIDInput = new JTextField();
        childTwoIDInput.setPreferredSize(new Dimension(170, 25));
        
        childTwoInputPanel.add(childTwoLabel);
        childTwoInputPanel.add(childTwoIDInput);
        
        return childTwoInputPanel;
    }
    
    private JPanel childThreeInputPanel()
    {
        JPanel childThreeInputPanel = new JPanel();
        
        JLabel childThreeLabel = new JLabel("Third child user ID: ");
        JTextField parentOneIDInput = new JTextField();
        parentOneIDInput.setPreferredSize(new Dimension(170, 25));
        
        childThreeInputPanel.add(childThreeLabel);
        childThreeInputPanel.add(parentOneIDInput);
        
        return childThreeInputPanel;
    }
    
    private JPanel childFourInputPanel()
    {
        JPanel childFourInputPanel = new JPanel();
        
        JLabel childFourLabel = new JLabel("Fourth child user ID: ");
        JTextField childFourIDInput = new JTextField();
        childFourIDInput.setPreferredSize(new Dimension(170, 25));
        
        childFourInputPanel.add(childFourLabel);
        childFourInputPanel.add(childFourIDInput);
        
        return childFourInputPanel;
    }
    
    private JPanel childFiveInputPanel()
    {
        JPanel childFiveInputPanel = new JPanel();
        
        JLabel childFiveLabel = new JLabel("Fifth child user ID: ");
        JTextField childFiveIDInput = new JTextField();
        childFiveIDInput.setPreferredSize(new Dimension(170, 25));
        
        childFiveInputPanel.add(childFiveLabel);
        childFiveInputPanel.add(childFiveIDInput);
        
        return childFiveInputPanel;
    }
    
    private JPanel managementButtonsPanel()
    {
        JPanel managementButtonsPanel = new JPanel();
        
        JButton addFamilyGroupButton = new JButton("Create family group");
        addFamilyGroupButton.setPreferredSize(new Dimension(175, 50));
        addFamilyGroupButton.addActionListener(this);
        
        JButton modifyFamilyGroupButton = new JButton("Modify family group");
        modifyFamilyGroupButton.setPreferredSize(new Dimension(175, 50));
        modifyFamilyGroupButton.addActionListener(this);
        
        managementButtonsPanel.add(addFamilyGroupButton);
        managementButtonsPanel.add(modifyFamilyGroupButton);
        
        return managementButtonsPanel;
    }
    
    public JPanel getManageFamilyGroupingsPanel()
    {
        return manageFamilyGroupingsPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
