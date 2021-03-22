package com.example.kate.rentafriend.Firebase;

public class Comment {

    String comment;

    User user;

    public Comment() {
    }

    public Comment(String comment, User user) {
        this.comment = comment;
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString(){
        return user.toString() + ": " + "\n" + "      " +  comment;
    }
}
