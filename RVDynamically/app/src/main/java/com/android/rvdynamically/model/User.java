package com.android.rvdynamically.model;

import java.util.ArrayList;

public class User {

    private int userId;
    private String userName;
    private String userEmailId;

    ArrayList<User> userList = new ArrayList<>();
    public User() {

    }

    public User(int userId, String userName, String userEmailId) {
        this.userId = userId;
        this.userName = userName;
        this.userEmailId = userEmailId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmailId() {
        return this.userEmailId;
    }

    public void setUserEmailId(String userEmailId) {
        this.userEmailId = userEmailId;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userEmailId='" + userEmailId + '\'' +
                '}';
    }

    public ArrayList<User> getUserList(){return userList;}

    public void setUserList(ArrayList<User> userList){
        this.userList=userList;
    }


}
