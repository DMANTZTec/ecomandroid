package com.android.user;

public class User {
    private String userId;
    private String userName;
    private String userEmailId;

    public User(String userId, String userName, String userEmailId) {
        this.userId = userId;
        this.userName = userName;
        this.userEmailId = userEmailId;
    }

    public User() {

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmailId() {
        return userEmailId;
    }

    public void setUserEmailId(String userEmailId) {
        this.userEmailId = userEmailId;
    }
}
