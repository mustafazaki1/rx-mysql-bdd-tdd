package edu.orange.internship;

import java.sql.*;

/**
 * Created by Yusuf on 8/3/2016.
 */
public class UserDao {
    private static String root="root";
    private static String password="01092933945Sasa";
    public static Boolean findName( String username,User user) throws SQLException {
        Boolean result = false;
        final String connectionString = "jdbc:mysql://localhost:3306/urldata";
        final String checkStatement = "SELECT * FROM user WHERE name = ?";
        Connection connection = null;
        PreparedStatement checkPreparedStatement = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(connectionString,root,password);
            checkPreparedStatement = connection.prepareStatement(checkStatement);
            checkPreparedStatement.setString(1,username);
            ResultSet foundUsers = checkPreparedStatement.executeQuery();
            result = foundUsers.next();
            if(result) {
                user.setName(foundUsers.getString("name"));
                user.setId(foundUsers.getInt("id"));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            result = false;
        }finally {
            if(checkPreparedStatement != null)
                checkPreparedStatement.close();
            if(connection != null)
                connection.close();
        }
        return result;
    }

    public static Boolean addUser(String username) throws SQLException {
        Boolean result;
        final String connectionString = "jdbc:mysql://localhost:3306/urldata";
        final String checkStatement = " insert into User (Name)" + " values (?)";
        Connection connection = null;
        PreparedStatement checkPreparedStatement = null;
        try {
            connection = DriverManager.getConnection(connectionString, root , password );
            checkPreparedStatement = connection.prepareStatement(checkStatement);
            checkPreparedStatement.setString(1, username);
            checkPreparedStatement.execute();
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
        return result;
    }
}
