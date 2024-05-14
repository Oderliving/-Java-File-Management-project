package view;
//ChoicesUI.java
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChoicesUI extends JFrame {
    private JButton createFileButton;
    private JButton deleteFileButton;
    private JButton editFileButton;
    private JButton endButton;

    public ChoicesUI() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Choices");
        setLayout(new FlowLayout());

        createFileButton = new JButton("Create a File");
        deleteFileButton = new JButton("Delete a File");
        editFileButton = new JButton("Edit a File");
        endButton = new JButton("End Experience");

        createFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open CreateFileUI
                CreateFileUI createFileUI = new CreateFileUI();
                createFileUI.setVisible(true);
                dispose(); // Close the ChoicesUI frame
            }
        });

        deleteFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open DeleteFileUI
                DeleteFileUI deleteFileUI = new DeleteFileUI();
                deleteFileUI.setVisible(true);
                dispose(); // Close the ChoicesUI frame
            }
        });

        editFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open EditFileUI
                EditFileUI editFileUI = new EditFileUI();
                editFileUI.setVisible(true);
                dispose(); // Close the ChoicesUI frame
            }
        });

        endButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Delete the folder and close the application
                FileManager.deleteFolder();
                JOptionPane.showMessageDialog(null, "Folder deleted. Exiting the application.", "End Experience", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }
        });

        add(createFileButton);
        add(deleteFileButton);
        add(editFileButton);
        add(endButton);

        pack();
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
    }
    public void addCreateFileButtonListener(ActionListener listener) {
        createFileButton.addActionListener(listener);
    }

    public void addDeleteFileButtonListener(ActionListener listener) {
        deleteFileButton.addActionListener(listener);
    }

    public void addEditFileButtonListener(ActionListener listener) {
        editFileButton.addActionListener(listener);
    }

    public void addEndButtonListener(ActionListener listener) {
        endButton.addActionListener(listener);
    }

    }




