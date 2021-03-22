package com.example.kate.rentafriend.Firebase;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Users {

    private List<User> users;

    public Users(List<User> users) {
        this.users = users;
    }

    public Users(){
        users = new ArrayList<>();
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void addUser(User user){
        users.add(user);
    }

    public int numberOfUsers(){
        return users.size();
    }
}
