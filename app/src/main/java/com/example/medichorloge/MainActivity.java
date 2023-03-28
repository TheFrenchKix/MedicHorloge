package com.example.medichorloge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TextView testTxt;
    Button btnRegister, btnLogin;
    EditText inputUsername, inputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputUsername = (EditText) findViewById(R.id.txtUsername);
        inputPassword = (EditText)findViewById(R.id.txtPassword);
        testTxt = (TextView)findViewById(R.id.txtTest);
        btnRegister = (Button)findViewById(R.id.btnRegister);
        btnLogin = (Button)findViewById(R.id.btnLogin);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Task().execute();
            }
        });
    }

    class Task extends AsyncTask<Void, Void, Void>{

        String records = "", error="";

        @Override
        protected Void doInBackground(Void... voids) {
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.1.83/medichorloge","root","");
                Statement statement = connection.createStatement();

                ResultSet resultSet = statement.executeQuery("SELECT * FROM users");

                while(resultSet.next()) {

                    records += resultSet.getString(1) + " " + resultSet.getString(2) + "\n";

                }

            }
            catch(Exception e)
            {

                error = e.toString();

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            testTxt.setText(records);

            super.onPostExecute(aVoid);

        }
    }

    public void welcomeScreen(View view) {
        Intent wlc  = new Intent(this, HomePage.class);
        startActivity(wlc);
    }

    public void show(View view) {

    }

    public void loginUser(View view) {

    }

    public void registerUser(final String username, final String password, final String date) {

    }
}