	package controller;

//MainController.java (Controller)

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.*;
import model.*;
import javax.swing.JOptionPane;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class MainController {
    private StartUpUI startUpUI;
    private ChoicesUI choicesUI;
    private CreateFileUI createFileUI;
    private DeleteFileUI deleteFileUI;
    private EditFileUI editFileUI;

    public MainController() {
    	choicesUI = new ChoicesUI();
        startUpUI = new StartUpUI();
        choicesUI = new ChoicesUI();
        createFileUI = new CreateFileUI();
        deleteFileUI = new DeleteFileUI();
        

        startUpUI.addGetStartedButtonListener(new GetStartedButtonListener());
        choicesUI.addCreateFileButtonListener(new CreateFileButtonListener());
        choicesUI.addDeleteFileButtonListener(new DeleteFileButtonListener());
        choicesUI.addEditFileButtonListener(new EditFileButtonListener());
        choicesUI.addEndButtonListener(new EndButtonListener());
        createFileUI.addCreateFileButtonListener(new CreateFileSubmitButtonListener());
        createFileUI.addBackButtonListener(new CreateFileBackButtonListener());
        deleteFileUI.addDeleteButtonListener(new DeleteFileDeleteButtonListener());
        deleteFileUI.addBackButtonListener(new DeleteFileBackButtonListener());
        editFileUI.addSubmitButtonListener(new EditFileSubmitChangesButtonListener());
        editFileUI.addBackButtonListener(new EditFileBackButtonListener());
    }

    public void start() {
        startUpUI.setVisible(true);
    }

    // Action listeners for StartUpUI
    class GetStartedButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            startUpUI.setVisible(false);
            choicesUI.setVisible(true);
        }
    }

    // Action listeners for ChoicesUI
    class CreateFileButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            choicesUI.setVisible(false);
            createFileUI.setVisible(true);
        }
    }

    class DeleteFileButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            choicesUI.setVisible(false);
            deleteFileUI.setVisible(true);
            // Populate delete file UI with existing files
            deleteFileUI.setFileList(FileManager.listFiles());
        }
    }


 // Action listeners for EditFileUI
    class EditFileButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            choicesUI.setVisible(false);
            editFileUI.setVisible(true);
            
         // Populate the dropdown with existing files
            editFileUI.setFileSelectionList(FileManager.listFiles());
        }
    }

    class EndButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            FileManager.deleteFolder(); // Delete the folder
            System.exit(0); // Close the application
        }
    }

    // Action listeners for CreateFileUI
    class CreateFileSubmitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String fileName = createFileUI.getFileName();
            String fileType = createFileUI.getFileType();
            FileManager.createFile(fileName, fileType);
            createFileUI.setVisible(false);
            choicesUI.setVisible(true);
        }
    }

    class CreateFileBackButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            createFileUI.setVisible(false);
            choicesUI.setVisible(true);
        }
    }

    // Action listeners for DeleteFileUI
    class DeleteFileDeleteButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedFile = deleteFileUI.getSelectedFile();
            FileManager.deleteFile(selectedFile);
            deleteFileUI.setFileList(FileManager.listFiles()); // Update the list
        }
    }

    class DeleteFileBackButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            deleteFileUI.setVisible(false);
            choicesUI.setVisible(true);
        }
    }

    // Action listeners for EditFileUI
 // Action listener for submitting changes in EditFileUI
    class EditFileSubmitChangesButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedFile = editFileUI.getSelectedFile();
            String changesText = editFileUI.getChangesText();
            String typeUpdate = editFileUI.getTypeUpdateSelection();

            // Check if a file is selected and changes are made
            if (selectedFile.equals("Select File") || changesText.isEmpty()) {
                JOptionPane.showMessageDialog(editFileUI, "Please select an existing file and make changes.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Get the current file type from the selected file name
            String currentFileType = selectedFile.substring(selectedFile.lastIndexOf('.'));

            // Determine the new file name and file type
            String newFileName = changesText.isEmpty() ? selectedFile : changesText;
            String newFileType = typeUpdate.isEmpty() ? currentFileType : typeUpdate;

            // Call FileManager to edit the file
            FileManager.editFile(selectedFile, newFileName, newFileType);

            // Inform the user about changes
            JOptionPane.showMessageDialog(editFileUI, "Changes have been made.", "Changes Made", JOptionPane.INFORMATION_MESSAGE);

            // Update the list of files in the dropdown
            editFileUI.setFileSelectionList(FileManager.listFiles());
        }
    }


    class EditFileBackButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            editFileUI.setVisible(false);
            choicesUI.setVisible(true);
        }
    }

    public static void main(String[] args) {
        MainController controller = new MainController();
        controller.start();
    }
}




