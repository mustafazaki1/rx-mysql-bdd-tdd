package edu.orange.internship;

import java.sql.*;

/**
 * Created by Yusuf on 8/3/2016.
 */
public class UserDao {
    private static String root="root";
    private static String password="root";
    public static Boolean findName( String username) throws SQLException {
        Boolean result = false;
        final String connectionString = "jdbc:mysql://localhost:3306/urldata";
        final String checkStatement = "SELECT * FROM user WHERE name = ?";
        Connection connection = null;
        PreparedStatement checkPreparedStatement = null;
        try{
            connection = DriverManager.getConnection(connectionString,root,password);
            checkPreparedStatement = connection.prepareStatement(checkStatement);
            checkPreparedStatement.setString(1,username);
            ResultSet foundUsers = checkPreparedStatement.executeQuery();
            result = foundUsers.next();
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
}
