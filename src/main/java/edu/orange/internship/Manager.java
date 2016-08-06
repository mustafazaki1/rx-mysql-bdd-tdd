package edu.orange.internship;

import java.io.InputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

/**
 * Created by KEE on 05-Aug-16.
 */
public class Manager {

    private User user;
    private final String welcome = "Welcome, ";
    private final String welcomeBack = "Welcome back, ";
    public Manager(){
        user = new User();
    }

    public void work(InputStream inputStream, PrintStream outputStream) throws Exception{
        try(Scanner scanner = new Scanner(inputStream)) {
            System.setOut(outputStream);

            String username = "";
            System.out.print("Please enter your name:");
            username = scanner.next();
            if (!UserDao.findName(username, user)) {
                UserDao.addUser(username);
                UserDao.findName(username, user);
                System.out.println(welcome + user.getName());
            }else{
                System.out.println(welcomeBack + user.getName());
            }

            while (scanner.hasNext()) {
                String inputCommand = scanner.next();
                switch (inputCommand) {
                    case "download":
                    {

                        break;
                    }
                    case "list":
                    case "history":
                    {
                        // ToDo: history
                        break;
                    }
                    case "help":
                    case "/?":
                    default:
                        List<String> helpText = Files.readAllLines(Paths.get(this.getClass().getResource("help.txt").toURI()));
                        for (String line : helpText)
                            System.out.println(line);
                }
            }
        }
    }
}
