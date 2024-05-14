package view;

//DeleteFileUI.java
import model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class DeleteFileUI extends JFrame {
    private JList<String> fileList;
    private DefaultListModel<String> listModel;
    private JButton deleteButton;
    private JButton backButton;

    public DeleteFileUI() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Delete File");
        setLayout(new FlowLayout());

        listModel = new DefaultListModel<>();
        fileList = new JList<>(listModel);
        fileList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane listScrollPane = new JScrollPane(fileList);
        listScrollPane.setPreferredSize(new Dimension(200, 150));

        deleteButton = new JButton("Delete");
        backButton = new JButton("Back to Choices");

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = fileList.getSelectedIndex();
                if (selectedIndex != -1) {	
                    String fileName = listModel.getElementAt(selectedIndex);
                    if (FileManager.deleteFile(fileName)) {
                        listModel.remove(selectedIndex);
                        JOptionPane.showMessageDialog(DeleteFileUI.this, "File deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(DeleteFileUI.this, "Failed to delete file. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(DeleteFileUI.this, "Please select a file to delete.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Navigate back to ChoicesUI
                ChoicesUI choicesUI = new ChoicesUI();
                choicesUI.setVisible(true);
                dispose(); // Close the DeleteFileUI frame
            }
        });

        add(listScrollPane);
        add(deleteButton);
        add(backButton);

        pack();
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);

        // Populate file list
        setFileList(FileManager.listFiles());
    }

 // Update the method signature in DeleteFileUI class
    public void setFileList(String[] files) {
        listModel.clear();
        for (String file : files) {
            listModel.addElement(file);
        }
    }
    
    public String getSelectedFile() {
        return fileList.getSelectedValue(); // Return the selected file from the JList
    }


    // Method to register listener for the "Delete" button
    public void addDeleteButtonListener(ActionListener listener) {
        deleteButton.addActionListener(listener);
    }

    // Method to register listener for the "Back to Choices" button
    public void addBackButtonListener(ActionListener listener) {
        backButton.addActionListener(listener);
    }

    // Main method for testing
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DeleteFileUI();
            }
        });
    }
}




