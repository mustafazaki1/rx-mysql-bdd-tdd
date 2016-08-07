package edu.orange.internship;

import org.junit.Test;

import java.sql.SQLException;
import java.util.Date;

import static org.junit.Assert.assertTrue;

/**
 * Created by Yusuf on 8/3/2016.
 */
public class LinkDaoTest {

    @Test
    public  void addLink() throws SQLException {
        String[] urls={"saddsad","dasdasd","dasdsada"};
        LinkDao linkDao=new LinkDao();
        Link link = new Link();
        User user=new User();
        user.setId(1);
        user.setName("dasd");
        link.setUser(user);
        for(String url:urls){
            link.setUrl(url);
            link.setDate(new Date());
            Boolean result = linkDao.addLink(link);
            assertTrue(result);
        }
    }
}
