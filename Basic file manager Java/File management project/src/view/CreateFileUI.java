package view;

//CreateFileUI.java

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class CreateFileUI extends JFrame {
    private JTextField fileNameTextField;
    private JComboBox<String> fileTypeComboBox;
    private JButton createButton;
    private JButton backButton;

    public CreateFileUI() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Create File");
        setLayout(new FlowLayout());

        fileNameTextField = new JTextField(20);
        fileTypeComboBox = new JComboBox<>(new String[]{".txt", ".java"});
        createButton = new JButton("Create File");
        backButton = new JButton("Back to Choices");

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fileName = fileNameTextField.getText(); // Get the file name
                String fileType = (String) fileTypeComboBox.getSelectedItem(); // Get the file type

                // Check if file name is empty
                if (fileName.isEmpty()) {
                    JOptionPane.showMessageDialog(CreateFileUI.this, "Please enter a file name.", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Exit the method
                }

                // Call the createFile() method without assigning its result to any variable
                FileManager.createFile(fileName, fileType);

                // Validate whether the file was created
                if (isFileCreated(fileName, fileType)) {
                    JOptionPane.showMessageDialog(CreateFileUI.this, "File created successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    fileNameTextField.setText(""); // Clear the text field
                } else {
                    JOptionPane.showMessageDialog(CreateFileUI.this, "Failed to create file. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Navigate back to ChoicesUI
                ChoicesUI choicesUI = new ChoicesUI();
                choicesUI.setVisible(true);
                dispose(); // Close the CreateFileUI frame
            }
        });

        add(new JLabel("File Name:"));
        add(fileNameTextField);
        add(new JLabel("File Type:"));
        add(fileTypeComboBox);
        add(createButton);
        add(backButton);

        pack();
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
    }

    // Method to validate whether the file was created
    private boolean isFileCreated(String fileName, String fileType) {
        // Construct the file path
        String filePath = "File_Management_Folder" + File.separator + fileName + fileType;

        // Check if the file exists
        File file = new File(filePath);
        return file.exists();
    }

    // Method to register listener for the "Create File" button
    public void addCreateFileButtonListener(ActionListener listener) {
        createButton.addActionListener(listener);
    }

    // Method to register listener for the "Back to Choices" button
    public void addBackButtonListener(ActionListener listener) {
        backButton.addActionListener(listener);
    }

    // Method to get the file name entered by the user
    public String getFileName() {
        return fileNameTextField.getText();
    }

    // Method to get the file type selected by the user
    public String getFileType() {
        return (String) fileTypeComboBox.getSelectedItem();
    }

    // Main method for testing
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CreateFileUI();
            }
        });
    }
}







