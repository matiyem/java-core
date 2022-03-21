package com.example.throwVsThrows;

import com.sun.mail.iap.ConnectionException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.SocketException;

/*
    Create by Atiye Mousavi 
    Date: 3/19/2022
    Time: 10:32 AM
**/
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        TryCatch tryCatch=new TryCatch();

        try {
            tryCatch.execute();
        } catch (ConnectionException | SocketException ex) {
            System.out.println("IOException");
        } catch (Exception ex) {
            System.out.println("General exception");
        }
        checkedException();
        checkedExceptionWithThrows();
    }
    private static void checkedExceptionWithThrows() throws FileNotFoundException {
        File file=new File("not_existing_file.txt");
        FileInputStream stream=new FileInputStream(file);
    }
    private static void checkedException(){
        File file=new File("not_exisiting_file.txt");

        try {
            FileInputStream stream=new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
