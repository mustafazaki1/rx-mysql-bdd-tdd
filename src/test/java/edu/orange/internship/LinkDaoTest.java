package edu.orange.internship;

import org.junit.Test;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.junit.Assert.*;

/**
 * Created by Yusuf on 8/3/2016.
 */
public class LinkDaoTest {

    @Test
    public  void addLink() throws SQLException {
        String[] urls={"saddsad","dasdasd","dasdsada"};
        LinkDao linkDao=new LinkDao();
        User user=new User();
        user.setId(1);
        user.setName("dasd");
        for(String url:urls){
            Boolean result =false;
            result = linkDao.getaBoolean(user, url);
            assertTrue(result);
        }
    }
}
