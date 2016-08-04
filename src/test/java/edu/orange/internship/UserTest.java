package edu.orange.internship;

import org.junit.Test;

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
    public  void addUserTest() throws Exception {
        String usernames[] = {"zr3", "ts1", "mwq2a"};
        for (String username : usernames) {
            Boolean result = false;

            result = UserDao.addUser(username);
            assertTrue("Add Failed", result);
        }
    }

}
