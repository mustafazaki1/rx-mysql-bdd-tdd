package edu.orange.internship;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * Created by Mustafa on 8/2/2016.
 */
public class UserTest {
    @Test
    public void findExistNameTest() throws Exception{
        String usernames [] = {"rem","mustafa","kee"};
        final String root = "root",password = "13KNWcoding";
        for(String username:usernames){
            Boolean result = new User().findName(root, password, username);
            assertTrue(username,result);
        }
    }

    @Test
    public void findNonExistNameTest() throws Exception{
        String usernames [] = {"zetamoo","tsunami","moha"};
        final String root = "root",password = "13KNWcoding";
        for(String username:usernames){
            Boolean result = new User().findName(root, password, username);
            assertFalse(username,result);
        }
    }

    @Test
    public void setExistentUserTest() throws Exception{

    }
}
