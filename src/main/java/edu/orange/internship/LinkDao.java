package edu.orange.internship;

import java.sql.*;

/**
 * Created by Mustafa on 8/4/2016.
 */
public class LinkDao {
    private static String root="root";
    private static String password="root";
    public static ResultSet findName( User user) throws SQLException {
        Boolean result = false;
        final String connectionString = "jdbc:mysql://localhost:3306/urldata";
        final String checkStatement = "SELECT * FROM Links WHERE USER_ID = ?";
        Connection connection = null;
        PreparedStatement checkPreparedStatement = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(connectionString,root,password);
            checkPreparedStatement = connection.prepareStatement(checkStatement);
            checkPreparedStatement.setString(1,String.valueOf(user.getId()));
            return checkPreparedStatement.executeQuery();
        }catch (Exception e){
            System.out.println(e.getMessage());
            result = false;
        }finally {
            if(checkPreparedStatement != null)
                checkPreparedStatement.close();
            if(connection != null)
                connection.close();
        }
        return null;
    }
}
