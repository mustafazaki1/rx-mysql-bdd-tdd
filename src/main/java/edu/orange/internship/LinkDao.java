package edu.orange.internship;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mustafa on 8/4/2016.
 */
public class LinkDao {
    private static String root="root";
    private static String password="";

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
	
    public static List<Link> getUserHistory(User user) throws SQLException {

        List<Link> linkList = new ArrayList<Link>();
        final String connectionString = "jdbc:mysql://localhost:3306/urldata";
        final String checkStatement = "SELECT Url,Date FROM Links WHERE USER_ID = ?";
        Connection connection = null;
        PreparedStatement checkPreparedStatement = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(connectionString,root,password);
            checkPreparedStatement = connection.prepareStatement(checkStatement);
            checkPreparedStatement.setString(1,String.valueOf(user.getId()));
            ResultSet resultSet = checkPreparedStatement.executeQuery();
            while(resultSet.next()){
                Link tmp = new Link();
                tmp.setUrl(resultSet.getString("Url"));
                tmp.setDate(resultSet.getDate("Date"));
                linkList.add(tmp);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if(checkPreparedStatement != null)
                checkPreparedStatement.close();
            if(connection != null)
                connection.close();
        }
        return linkList;
    }
}
