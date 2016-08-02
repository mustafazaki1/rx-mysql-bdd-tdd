package edu.orange.internship;

import java.sql.*;

/**
 * Created by Mustafa on 8/2/2016.
 */
public class User {
    private  String name;

    public String getName() {
        return  name;
    }

    public Boolean findName(String root, String password, String username) throws SQLException {
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

    public void setName(String newName){
        name=newName;
    }
}
