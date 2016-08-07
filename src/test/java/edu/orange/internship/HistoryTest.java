package edu.orange.internship;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yusuf on 8/6/2016.
 */
public class HistoryTest {
    @Test
    public  void addUserTest() throws Exception {
        User user= new User();
        List<Link> expected = new ArrayList<Link>();
        Link tmpLink =new Link();
        tmpLink.setUrl("fdsfgsdf");
        tmpLink.setDate(java.sql.Date.valueOf("2008-11-11"));
        expected.add(tmpLink);
        tmpLink =new Link();
        tmpLink.setUrl("fdsgvwe");
        tmpLink.setDate(java.sql.Date.valueOf("2008-11-11"));
        expected.add(tmpLink);
        tmpLink =new Link();
        tmpLink.setUrl("fdwgvxznt");
        tmpLink.setDate(java.sql.Date.valueOf("2008-11-11"));
        expected.add(tmpLink);
        List<Link> result = LinkDao.getUserHistory(user);
        assertEquals("Wrong List Size :",expected.size(),result.size());
        for(int i=0;i<expected.size();++i){
            System.out.println(expected.get(i).getUrl());
            System.out.println(expected.get(i).getDate());
            assertEquals("Invalid Value :",expected.get(i).getUrl(),result.get(i).getUrl());
            assertEquals("Invalid Value :",expected.get(i).getDate(),result.get(i).getDate());
        }
    }

}
