package edu.orange.internship;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * Created by KEE on 05-Aug-16.
 */
public class Main {

    public static void main(String [] arg){
        Manager manager = new Manager();
        try {
            System.setErr(new PrintStream(new FileOutputStream("log.txt")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            manager.work(System.in, System.out);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

}
