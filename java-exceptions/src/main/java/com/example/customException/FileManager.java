package com.example.customException;

/*
    created by Atiye Mousavi
    Date: 3/18/2022
    Time: 11:06 AM
*/


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileManager {
    public static String getFirstLine(String fileName) throws IncorrectFileNameException {
        try (Scanner file = new Scanner(new File(fileName))) {
            if (file.hasNextLine()) {
                return file.nextLine();
            } else {
                throw new IllegalAccessException("Non readable file");
            }
        } catch (FileNotFoundException e) {
            if (!isCorrectFileName(fileName)){
                throw new IncorrectFileNameException("incorrect filename : " + fileName,e);
            }
        } catch (IllegalAccessException e) {
            if (!containsExtension(fileName)){
                throw new IncorrectFileNameException("Filename does not contain extension : " + fileName,e);
            }
        }
        return "Default First Line";

    }
    private static boolean containsExtension(String fileName){
        if (fileName.contains(".txt") || fileName.contains(".doc"))
            return true;
        return false;
    }
    private static boolean isCorrectFileName(String fileName){
        if (fileName.equals("wrongFileName.txt"))
            return false;
        else
            return true;
    }
}
