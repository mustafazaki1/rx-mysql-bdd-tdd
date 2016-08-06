package edu.orange.internship;

import java.sql.*;

/**
 * Created by Mustafa on 8/4/2016.
 */
public class LinkDao {
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
}
