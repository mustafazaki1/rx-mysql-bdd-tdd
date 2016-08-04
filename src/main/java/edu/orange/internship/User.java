package edu.orange.internship;

import java.sql.*;

/**
 * Created by Mustafa on 8/2/2016.
 */
public class User {
    private  String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private  int id;
    public String getName() {
        return  name;
    }

    public void setName(String newName){
        name=newName;
    }
}
