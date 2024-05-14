package view;
import model.FileManager;	

// StartUpUI.java

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartUpUI extends JFrame {
    private JButton getStartedButton;

    public StartUpUI() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Start_Up");
        setLayout(new FlowLayout());

        getStartedButton = new JButton("Get Started");
        getStartedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileManager.createFolder(); // Call createFolder() method
                openChoicesUI(); // Open ChoicesUI after creating the folder
            }
        });

        add(getStartedButton);
        pack();
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
    }
    public void addGetStartedButtonListener(ActionListener listener) {
        getStartedButton.addActionListener(listener);
    }

    private void openChoicesUI() {
        ChoicesUI choicesUI = new ChoicesUI();
        choicesUI.setVisible(true);
        dispose(); // Close the StartUpUI frame after opening ChoicesUI
    }
    

    // Main method for testing
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StartUpUI();
            }
        });
    }
}




