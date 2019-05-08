package com.android.user;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<User> mRecyclerViewUsers = new ArrayList<>();


//    ArrayList<String> jthings = new ArrayList<>();
//    User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addUserFromJson();
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        RecyclerView.Adapter adapter = new RecyclerViewAdapter(this, mRecyclerViewUsers);
        mRecyclerView.setAdapter(adapter);

      //  get_json();

    }

   private void addUserFromJson() {

       try {
           String jsonDataString = readJsonDataFromFile();
           JSONArray userJsonArray = new JSONArray(jsonDataString);

           for (int i=0;i<userJsonArray.length();i++){
               JSONObject userObject = userJsonArray.getJSONObject(i);

               String user_Id = userObject.getString("userId");
               String user_Name = userObject.getString("userName");
               String user_EmailId = userObject.getString("userEmailId");

               User pojo = new User(user_Id,user_Name,user_EmailId);
               Log.d("pojo test ", "addUserFromJson: "+pojo.getUserId());
               mRecyclerViewUsers.add(pojo);
           }
       } catch (IOException e) {
           e.printStackTrace();
       } catch (JSONException e) {
           e.printStackTrace();
       }

   }
    private String readJsonDataFromFile() throws IOException{
        InputStream inputStream = null;
        StringBuilder builder = new StringBuilder();

        try {


            String jsonDataString = null;
            inputStream = getResources().openRawResource(R.raw.user123);
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(inputStream, StandardCharsets.UTF_8)
            );
            while ((jsonDataString = bufferedReader.readLine())!=null){
                builder.append(jsonDataString);
            }
        }finally {
            if (inputStream != null){
                inputStream.close();
            }
        }
        return new String(builder);
    }
}



//    public void get_json(){
//        String json;
//        try {
//            InputStream is = getAssets().open("user123");//to get json file here user123 is jsonfile name
//            int size = is.available();
//            byte[] buffer = new byte[size];
//            is.read(buffer);
//            is.close();
//
//            json = new String(buffer,"UTF-8");
//            JSONArray jsonArray = new JSONArray(json);
//
//
//            for (int i=0;i<jsonArray.length();i++){
//                JSONObject jobj = jsonArray.getJSONObject(i);
//
//                jthings.add((jobj.getString("userId"));
//                jthings.add(jobj.getString("userEmailId"));
//                jthings.add(jobj.getString("userName"));
//
//
//
//            }
//
//            Toast.makeText(getApplicationContext(),jthings.toString(),Toast.LENGTH_LONG).show();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//
//    }
