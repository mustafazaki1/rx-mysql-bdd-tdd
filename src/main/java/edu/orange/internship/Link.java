package edu.orange.internship;

import java.sql.Date;

/**
 * Created by Mustafa on 8/3/2016.
 */
public class Link {
    private String url;
    private Date date;
    private User user;
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
