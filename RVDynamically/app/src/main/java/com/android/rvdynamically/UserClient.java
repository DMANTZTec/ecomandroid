package com.android.rvdynamically;

import android.content.Context;
import android.util.Log;

import com.android.rvdynamically.model.User;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import static android.content.ContentValues.TAG;

public class UserClient {

    static UserClient userClient;

    private Context context;
    User userObj = new User();
    ArrayList<User> userArrayList = new ArrayList<>();

    //List<User> userList = new ArrayList<>();


    String all_userURL = "http://192.168.0.140:8080/android/allUsers";

    public static UserClient getUserClient() {

        if (userClient == null) {
            userClient = new UserClient();
        }

        return userClient;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ArrayList<User> getUserFromDB() {

        try {
            URL url = new URL(all_userURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("context-Type", "application/json");

           /* Gson gson = new Gson();

            String userJson = gson.toJson(userObj);

            DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
            dataOutputStream.write(userJson.getBytes());
            dataOutputStream.flush();*/

            BufferedReader bufferedresponse = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = bufferedresponse.readLine()) != null) {
                response.append(line);
                response.append("/r");
            }
            bufferedresponse.close();

            JSONObject storejsonObj = new JSONObject(response.toString());

            Log.d(TAG, "list obj" + storejsonObj);
            JSONArray jsonArrayobj = storejsonObj.getJSONArray("userEmailId");
            // JSONArray jsonArrayobj = storejsonObj.getJSONArray("userId");


            Log.d(TAG, "user" + jsonArrayobj);

            userObj = new User();


            for (int i = 0; i < jsonArrayobj.length(); i++) {

                User usernew = new User();

                usernew.setUserId((int) jsonArrayobj.getJSONObject(i).get("userId"));
                usernew.setUserName((String) jsonArrayobj.getJSONObject(i).get("userName"));
                usernew.setUserEmailId((String) jsonArrayobj.getJSONObject(i).get("userEmailId"));


            }

            userObj.setUserList(userArrayList);

            return userArrayList;


        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<User> getUserArrayList() {
        return userArrayList;
    }

    public void setUserArrayList(ArrayList<User> userArrayList) {
        this.userArrayList = userArrayList;
    }
}
