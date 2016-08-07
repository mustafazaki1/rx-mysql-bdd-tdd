package edu.orange.internship;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Mustafa on 8/4/2016.
 */
public class LinkDao {
    private static String root="root";
    private static String password="01092933945Sasa";

    public Boolean getaBoolean(User user, String url) throws SQLException {
        final String connectionString = "jdbc:mysql://localhost:3306/urldata";
        final String addString = "INSERT INTO urldata.links (USER_ID,Url,Date) VALUES ((?),(?),(?));";
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        try {
            connection= DriverManager.getConnection(connectionString,root,password);
            preparedStatement=connection.prepareStatement(addString);
            preparedStatement.setString(1,user.getId()+"");
            preparedStatement.setString(2,url);
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Calendar cal = Calendar.getInstance();
            preparedStatement.setString(3,dateFormat.format(cal.getTime()));
            preparedStatement.execute();
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
