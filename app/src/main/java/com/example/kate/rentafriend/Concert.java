package com.example.kate.rentafriend;

import com.example.kate.rentafriend.Firebase.Comment;
import com.example.kate.rentafriend.Firebase.User;
import com.example.kate.rentafriend.Firebase.Users;

import java.util.ArrayList;
import java.util.List;

public class Concert {

    private String title;
    private String location;
    private String date;
    private String id; // id
    private ArrayList<Comment> comments;
    private ArrayList<User> users;
    public float lat, lng;

    public Concert() {
        this.title = "";
        this.location = "";
        this.date = "";
        this.id = "";
        comments = new ArrayList<>();
        users = new ArrayList<>();
    }

    public Concert(String title, String location, String date, String info) {
        this.title = title;
        this.location = location;
        this.date = date;
        this.id = info;
        comments = new ArrayList<>();
        users = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }

    public void addComment(Comment comment){
        comments.add(comment);
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public String usersToString(){
        String s = "";
        for(User user : users){
            s += user.toString() + "\n";
        }
        return s;
    }

    public String commentsToString(){
        String s = "";
        for(Comment comment : comments){
            s += comment.toString() + "\n\n";
        }
        return s;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public void addUser(User user){
        this.users.add(user);
    }
}
