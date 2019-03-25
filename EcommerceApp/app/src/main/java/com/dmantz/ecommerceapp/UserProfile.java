package com.dmantz.ecommerceapp;


import com.dmantz.ecommerceapp.model.User;

import java.util.ArrayList;


public class UserProfile {
    Integer userId;
    Integer userKey;
    Integer profileKey;
    User user;
    String firstName;
    String lastName;
    String password;
    String DOB;
    String gender;
    Integer Age;
    String emailid;
    String mobileNumber;
    Long altmobileNumber;
    String lastLoggedLocation;
    ArrayList shipAddressList;
    ArrayList<String> billingAddressList;

    public UserProfile() {

    }

    public UserProfile(org.json.simple.JSONObject userProfileJson) {
        this.setFirstName((String) userProfileJson.get("firstName"));
        this.setLastName((String) userProfileJson.get("lastName"));
        this.setDOB((String) userProfileJson.get("DOB"));
        this.setMobileNumber((String) userProfileJson.get("mobileNumber"));
        this.setAltmobileNumber((Long) userProfileJson.get("altMobileNumber"));
        this.setEmailid((String) userProfileJson.get("emailId"));
        this.setpassword((String) userProfileJson.get("password"));


    }


    public String getpassword() {
        return password;
    }

    public void setpassword(String password) {
        this.password = password;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserKey() {
        return userKey;
    }

    public void setUserKey(Integer userKey) {
        this.userKey = userKey;
    }

    public Integer getProfileKey() {
        return profileKey;
    }

    public void setProfileKey(Integer profileKey) {
        this.profileKey = profileKey;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String dOB) {
        DOB = dOB;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return Age;
    }

    public void setAge(Integer age) {
        Age = age;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Long getAltmobileNumber() {
        return altmobileNumber;
    }

    public void setAltmobileNumber(Long altmobileNumber) {
        this.altmobileNumber = altmobileNumber;
    }

    public String getLastLoggedLocation() {
        return lastLoggedLocation;
    }

    public void setLastLoggedLocation(String lastLoggedLocation) {
        this.lastLoggedLocation = lastLoggedLocation;
    }

    public ArrayList getShipAddressList() {
        return shipAddressList;
    }

    public void setShipAddressList(ArrayList shipAddressList) {
        this.shipAddressList = shipAddressList;
    }

    public ArrayList<String> getBillingAddressList() {
        return billingAddressList;
    }

    public void setBillingAddressList(ArrayList<String> billingAddressList) {
        this.billingAddressList = billingAddressList;
    }

    @Override
    public String toString() {
        return "UserProfile [userId=" + userId + ", userKey=" + userKey + ", profileKey=" + profileKey + ", user="
                + user + ", firstName=" + firstName + ", lastName=" + lastName + ", DOB=" + DOB + ", gender=" + gender
                + ", Age=" + Age + ", emailid=" + emailid + ", mobileNumber=" + mobileNumber + ", altmobileNumber="
                + altmobileNumber + ", lastLoggedLocation=" + lastLoggedLocation + ", shipAddressList="
                + shipAddressList + ", billingAddressList=" + billingAddressList + "]";
    }


}
