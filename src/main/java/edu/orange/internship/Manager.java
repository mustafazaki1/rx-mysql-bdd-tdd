package edu.orange.internship;

import java.io.InputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
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
                        String type = scanner.next();
                        if("file".equals(type)){
                            downloadFile(scanner);
                        }else if("url".equals(type)){
                            try {
                                downloadURL(scanner);
                            }catch (IllegalArgumentException e){
                                System.err.println(e.getMessage());
                                scanner.nextLine();
                                help();
                            }
                        }else
                            help();
                        break;
                    }
                    case "list":
                    case "history":
                    {
                        history(scanner);
                        break;
                    }
                    case "help":
                    case "/?":
                    default:
                        help();
                }
            }
        }
    }

    private void downloadURL(Scanner inputScanner) throws Exception{
        Downloader downloader = new Downloader();
        LinkDao linkDao = new LinkDao();
        Link link = new Link();
        link.setUrl(inputScanner.next());
        link.setUser(user);
        if(!downloader.enterUrl(link.getUrl())){
            throw new IllegalArgumentException();
        }
        downloader.download(link.getUrl().substring(link.getUrl().lastIndexOf('/')+1, link.getUrl().length()));
        link.setDate(new Date());
    }

    private void downloadFile(Scanner inputScanner) throws Exception{
        // ToDo rx-java
    }

    private void history(Scanner inputScanner) throws Exception{
        // ToDo put some code here :v
    }

    private void help() throws Exception{
        List<String> helpText = Files.readAllLines(Paths.get(this.getClass().getResource("help.txt").toURI()));
        for (String line : helpText)
            System.out.println(line);
    }
}
