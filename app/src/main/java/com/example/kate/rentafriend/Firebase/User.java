package com.example.kate.rentafriend.Firebase;

public class User {

    private String UserID;
    private String UserName;
    private String UserEmail;

    public User() {
    }

    public User(String userID, String userEmail, String userName) {
        UserID = userID;
        UserName = userName;
        UserEmail = userEmail;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }

    @Override
    public String toString(){
        return getUserName() + " | " + getUserEmail();
    }
}
