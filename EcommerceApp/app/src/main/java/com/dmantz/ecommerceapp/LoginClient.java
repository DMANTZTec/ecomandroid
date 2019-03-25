package com.dmantz.ecommerceapp;


import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class LoginClient {
    private static final String TAG = "LOGINCLIENT";
    private Properties props;
    private UserProfile userProfile;
    String targetURL = "http://192.168.100.4:8080/UserApp/login";
    String registerURL = "http://192.168.100.4:8080/UserApp/RegisterUser";
    static LoginClient loginClient2;

    private LoginClient() {

    }

    public static LoginClient getLoginClient2() {
        if (loginClient2 == null) {
            return new LoginClient();
        }
        return loginClient2;
    }


    public JSONObject login(String username, String password) throws IOException {

        Log.d(TAG, "Enter login()");
        JSONObject loginUserJson = new JSONObject();
        JSONObject responseJson;
        loginUserJson.put("username", username);
        loginUserJson.put("password", password);

        URL url = new URL(targetURL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoInput(true);
        connection.setDoOutput(true);
        DataOutputStream dataoutput = new DataOutputStream(connection.getOutputStream());
        dataoutput.write(loginUserJson.toString().getBytes("UTF-8"));
        dataoutput.flush();
        BufferedReader bufferResponse = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        StringBuffer response = new StringBuffer();
        while ((line = bufferResponse.readLine()) != null) {
            response.append(line);
            response.append("\r");
        }

        bufferResponse.close();
        Log.d(TAG, response.toString());
        JSONParser parser = new JSONParser();
        JSONObject statusJsonObj = null;

        try {
            responseJson = (JSONObject) parser.parse(response.toString());
            //Log.d(TAG," value of responseJson " +  responseJson);
            String status = (String) responseJson.get("status");
            String reason = (String) responseJson.get("reason");
            statusJsonObj = new JSONObject();
            statusJsonObj.put("status", status);
            statusJsonObj.put("reason", reason);


            JSONObject userProfileJson = (JSONObject) responseJson.get("UserProfile");
            this.userProfile = new UserProfile(userProfileJson);

            //Log.d(TAG,"firstName is "+ userProfile.getFirstName());


        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();


        }
        //Log.d(TAG," statusJsonObj equal to "+statusJsonObj.toString());
        return statusJsonObj;

    }


    public JSONObject registerUser(String firstName, String lastName, String emailid, String password, String mobileNumber) throws Exception {
        JSONObject registerUserObj = new JSONObject();
        JSONObject responseJson;
        registerUserObj.put("firstName", firstName);
        registerUserObj.put("lastName", lastName);
        registerUserObj.put("emailid", emailid);
        registerUserObj.put("password", password);
        registerUserObj.put("mobileNumber", mobileNumber);

        URL url = new URL(registerURL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoInput(true);
        connection.setDoOutput(true);
        DataOutputStream dataoutput = new DataOutputStream(connection.getOutputStream());
        //dataoutput.write(loginUserJson.toString().getBytes("UTF-8"));
        dataoutput.write(registerUserObj.toString().getBytes("UTF-8"));
        dataoutput.flush();
        BufferedReader bufferResponse = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        StringBuffer response = new StringBuffer();
        while ((line = bufferResponse.readLine()) != null) {
            response.append(line);
            response.append("\r");
        }

        bufferResponse.close();
        System.out.println(response);
        JSONParser parser = new JSONParser();
        JSONObject statusJsonObj = null;

        try {
            responseJson = (JSONObject) parser.parse(response.toString());
            //System.out.println(" value of responseJson " +  responseJson);
            //System.out.println(" Set  user firstName is " + responseJson);
            String status = (String) responseJson.get("status");
            String reason = (String) responseJson.get("reason");
            statusJsonObj = new JSONObject();
            statusJsonObj.put("status", status);
            statusJsonObj.put("reason", reason);


            JSONObject userProfileJson = (JSONObject) responseJson.get("UserProfile");
            this.userProfile = new UserProfile(userProfileJson);

            //System.out.println("firstName is "+ userProfile.getFirstName());


        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();


        }
        //System.out.println(" statusJsonObj equal to "+statusJsonObj.toString());
        //System.out.println(" Set  user firstName is " + registerUserObj.get("firstName"));
        return statusJsonObj;


    }

    public UserProfile getUserProfile() {
        userProfile.getFirstName();
        userProfile.getEmailid();
        System.out.println("user first name is " + userProfile.getFirstName());
        return userProfile;
    }

    public static void main(String[] args) throws Exception {
        LoginClient loginClientObj = new LoginClient();
        JSONObject loginResponse = loginClientObj.login("shashi", "shashi123");
        System.out.println(loginResponse.toString());
        loginClientObj.registerUser("shashi  ", "reddy ", "shashi@gmail.com ", "shashi123", "8978971254");
        JSONObject registerResponse = loginClientObj.registerUser("shashi ", "reddy ", "shashi@gmail.com ", "shashi123", "8978971285");
        System.out.println(registerResponse.toString());
        //   loginClientObj.getUserProfile();


    }


}
