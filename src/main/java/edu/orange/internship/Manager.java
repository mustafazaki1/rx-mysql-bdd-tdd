package edu.orange.internship;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Created by KEE on 05-Aug-16.
 */
public class Manager {

    private User user;
    private final String WELCOME = "Welcome, ";
    private final String WELCOMEBACK = "Welcome back, ";
    private Downloader downloader;
    private Scanner scanner;

    public Manager() throws Exception {
        user = new User();
        downloader = new Downloader();
        scanner = new Scanner(System.in);
    }

    public void work() throws Exception {

        String username;
        System.out.print("Please enter your name:");
        username = scanner.next();
        logging(username);

        while (scanner.hasNext()) {
            String inputCommand = scanner.next();
            switch (inputCommand) {
                case "download": {
                    String type = scanner.next();
                    if ("file".equals(type)) {
                        downloadFile(scanner.nextLine());
                    } else if ("url".equals(type)) {
                        downloadURL(scanner.nextLine());
                    } else
                        help();
                    break;
                }
                case "list":
                case "history": {
                    history(scanner.nextLine());
                    break;
                }
                case "help":
                case "/?":
                default:
                    help();
            }
        }
    }

    public void logging(String username) throws SQLException {
        if (!UserDao.findName(username, user)) {
            UserDao.addUser(username);
            UserDao.findName(username, user);
            System.out.println(WELCOME + user.getName());
        } else {
            System.out.println(WELCOMEBACK + user.getName());
        }
    }

    public Boolean downloadURL(String command) {
        String arguments[] = command.split("\\s+");
        LinkDao linkDao = new LinkDao();
        Link link = new Link();
        link.setUrl(arguments[0]);
        link.setUser(user);
        Request request = new Request();
        request.setFilename(arguments[1]);
        try {
            request.setUri(arguments[0]);
        } catch (URISyntaxException ex) {
            System.out.println("Invalid url!");
            System.err.println(ex.getMessage());
            return false;
        }
        try {
            List<DownloadedBytes> downloaded = downloader.download(request);
            for (DownloadedBytes downloadedBytes : downloaded) {
                downloader.write(downloadedBytes);
            }
        } catch (IOException ex) {
            System.out.println("An error occurred while downloading!");
            System.err.println(ex.getMessage());
            return false;
        }

        link.setDate(new Date());
        try {
            linkDao.addLink(link);
        } catch (SQLException ex) {
            System.out.println("File downloaded, but an error occurred while saving!");
            System.err.println(ex.getMessage());
        }
        return true;
    }

    public void downloadFile(String command) {
        // ToDo rx-java
    }

    public void history(String command) {
        // ToDo put some code here :v
    }

    public void help() {
        List<String> helpText;
        try {
            helpText = Files.readAllLines(Paths.get(this.getClass().getResource("help.txt").toURI()));
        }catch (Exception ex){
            System.out.println("An error occurred while reading help file!");
            System.err.println(ex.getMessage());
            return;
        }
        helpText.forEach(System.out::println);
    }
}
