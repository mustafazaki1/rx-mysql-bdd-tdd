package edu.orange.internship;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * Created by Mustafa on 8/2/2016.
 */
public class UserTest {
    @Test
    public void findExistNameTest() throws Exception {
        String usernames[] = {"rem", "mustafa", "kee"};
        for (String username : usernames) {
            Boolean result = UserDao.findName(username);
            assertTrue(username, result);
        }
    }

    @Test
    public void findNonExistNameTest() throws Exception {
        String usernames[] = {"zetamoo", "tsunami", "moha"};
        for (String username : usernames) {
            Boolean result = UserDao.findName(username);
            assertFalse(username, result);
        }
    }

    @Test
    public void addUserTest() throws Exception {
        String usernames[] = {"zetamoo", "tsunami", "moha"};
        for (String username : usernames) {
            Boolean result = false;

            final String connectionString = "jdbc:mysql://localhost:3306/urldata";
            final String checkStatement = " insert into User (Name)"
                    + " values (?)";
            Connection connection = null;
            PreparedStatement checkPreparedStatement = null;
            try {
                connection = DriverManager.getConnection(connectionString, "root", "root");
                checkPreparedStatement = connection.prepareStatement(checkStatement);
                checkPreparedStatement.setString(1, username);
                ResultSet foundUsers = checkPreparedStatement.executeQuery();
                result = foundUsers.next();
                result = true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                result = false;
            } finally {
                if (checkPreparedStatement != null)
                    checkPreparedStatement.close();
                if (connection != null)
                    connection.close();
            }
            assertTrue("Add Failed", result);
        }
    }
}
