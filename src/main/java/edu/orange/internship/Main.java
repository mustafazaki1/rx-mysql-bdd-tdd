package edu.orange.internship;

/**
 * Created by KEE on 05-Aug-16.
 */
public class Main {

    public static void main(String [] arg){
        Manager manager = new Manager();
        try {
            manager.work(System.in, System.out);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

}
