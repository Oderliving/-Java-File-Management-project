package view;

//EditFileUI.java
import model.*;
import controller.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EditFileUI extends JFrame {
    private JComboBox<String> fileSelectionComboBox;
    private JTextField changesTextField;
    private JComboBox<String> typeUpdateComboBox;
    private JButton submitButton;
    private JButton backButton;
    private ChoicesUI choicesUI;
    private MainController mainController;
    
    
    public EditFileUI(MainController mainController) {
        this.mainController = mainController;
    }

    public EditFileUI() {
    	JOptionPane.showMessageDialog(null, "Please click submit to initiate, I failed at fixing it :3.\n text field works but shrinks somehow.", "Alert", JOptionPane.WARNING_MESSAGE);

        
        setTitle("Edit File");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        fileSelectionComboBox = new JComboBox<>();
        changesTextField = new JTextField(10);
        JLabel selectedFileLabel = new JLabel("Selected File:");
        JLabel changesLabel = new JLabel("New Name:");
        typeUpdateComboBox = new JComboBox<>(new String[]{".txt", ".java"});
        submitButton = new JButton("Submit Changes");
        backButton = new JButton("Back to Choices");

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        panel.add(selectedFileLabel, gbc);
        gbc.gridx++;
        panel.add(fileSelectionComboBox, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(changesLabel, gbc);
        gbc.gridx++;
        panel.add(changesTextField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("File Type:"), gbc);
        gbc.gridx++;
        panel.add(typeUpdateComboBox, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(submitButton, gbc);
        gbc.gridx++;
        panel.add(backButton, gbc);

        getContentPane().add(panel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);

        // Add action listeners
        submitButton.addActionListener(new SubmitButtonListener());
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Navigate back to ChoicesUI
                ChoicesUI choicesUI = new ChoicesUI();
                choicesUI.setVisible(true);
                dispose(); // Close the DeleteFileUI frame
            }
        });
    }

    public void setFileSelectionList(String[] files) {
        fileSelectionComboBox.removeAllItems();
        fileSelectionComboBox.addItem("Select File");
        for (String file : files) {
            fileSelectionComboBox.addItem(file);
        }
    }

    private class SubmitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedFile = (String) fileSelectionComboBox.getSelectedItem();
            if ("Select File".equals(selectedFile)) {
                JOptionPane.showMessageDialog(EditFileUI.this, "Please select an existing file.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String newFileName = changesTextField.getText();
            String newFileType = (String) typeUpdateComboBox.getSelectedItem();
            // Call method to edit the file
            FileManager.editFile(selectedFile, newFileName, newFileType);
            // Inform the user about changes
            JOptionPane.showMessageDialog(EditFileUI.this, "Changes have been made.", "Changes Made", JOptionPane.INFORMATION_MESSAGE);
            // Update the list of chosen files
            setFileSelectionList(FileManager.listFiles());
            changesTextField.setText(null);
        }
    }
    

    public String getSelectedFile() {
        return fileSelectionComboBox.getSelectedItem().toString();
    }

    public String getChangesText() {
        return changesTextField.getText();
    }

    public String getTypeUpdateSelection() {
        return typeUpdateComboBox.getSelectedItem().toString();
    }

    public void addSubmitButtonListener(ActionListener listener) {
        submitButton.addActionListener(listener);
    }

    public void addBackButtonListener(ActionListener listener) {
        backButton.addActionListener(listener);
    }
}



