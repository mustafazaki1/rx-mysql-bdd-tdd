package edu.orange.internship;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by KEE on 05-Aug-16.
 */
public class Main {

    public static void main(String [] arg){
        try {
            if(Files.notExists(Paths.get("log.txt"))){
                Files.createFile(Paths.get("log.txt"));
            }
            System.setErr(new PrintStream(new FileOutputStream("log.txt")));
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        try {
            Manager manager = new Manager();
            manager.work();
        }catch (Exception ex){
            System.err.println(ex.getMessage());
        }
    }

}
