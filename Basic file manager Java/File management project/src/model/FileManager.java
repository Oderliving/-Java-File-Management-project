package model;

//FileManager.java (Model)

import java.io.File;

public class FileManager {
    private static final String FILE_MANAGEMENT_FOLDER = "File_Management_Folder";
    private static final String FILE_DIRECTORY = "File_Management_Folder";


    // Create folder if not exists
    public static void createFolder() {
        File folder = new File(FILE_MANAGEMENT_FOLDER);
        if (!folder.exists()) {
            folder.mkdir();
        }
    }

    // Create file
    public static boolean createFile(String fileName, String fileType) {
        createFolder();
        try {
            String filePath = FILE_MANAGEMENT_FOLDER + File.separator + fileName + fileType;
            File file = new File(filePath);
            return file.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete file
    public static boolean deleteFile(String fileName) {
        String filePath = FILE_MANAGEMENT_FOLDER + File.separator + fileName;
        File file = new File(filePath);
        if (file.exists()) {
            return file.delete();
        } else {
            return false;
        }
    }

    // Delete folder and its contents recursively
    public static boolean deleteFolder() {
        return deleteFolder(new File(FILE_MANAGEMENT_FOLDER));
    }

    private static boolean deleteFolder(File folder) {
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    deleteFolder(file);
                }
            }
        }
        return folder.delete();
    }

    // List files
    public static String[] listFiles() {
        File folder = new File(FILE_MANAGEMENT_FOLDER);
        return folder.list();
    }

 // Method to edit a file
    public static boolean editFile(String fileName, String newFileName, String newFileType) {
        File oldFile = new File(FILE_DIRECTORY + File.separator + fileName);
        File newFile = new File(FILE_DIRECTORY + File.separator + newFileName + newFileType);

        if (oldFile.exists() && !newFile.exists()) {
            return oldFile.renameTo(newFile);
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        // Test the methods
        createFolder();
        System.out.println("Folder created.");

        createFile("testFile.txt", ".txt");
        System.out.println("File created.");

        System.out.println("Files in the folder:");
        String[] files = listFiles();
        for (String file : files) {
            System.out.println(file);
        }
    }
}




