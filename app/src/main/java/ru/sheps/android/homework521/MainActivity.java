package ru.sheps.android.homework521;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
 EditText editLogin;
 EditText editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editLogin = findViewById(R.id.editLogin);
        editPassword = findViewById(R.id.editPassword);

    }

    public void onClickLogin(View view) throws IOException {

        String savedLogin = readSavedData("login");
        String savedPassword = readSavedData("password");

        if (editLogin.getText().toString().equals(savedLogin) && editPassword.getText().toString().equals(savedPassword))
        {
            Toast.makeText(this, "Верный логин и пароль", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Неверный логин и пароль", Toast.LENGTH_SHORT).show();
        }

    }

    private String readSavedData(String fileName) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = openFileInput(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        BufferedReader reader = new BufferedReader(inputStreamReader);
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void onClickRegistration(View view) {
        FileOutputStream fileLogins = null;
        FileOutputStream fileLPassword = null;
        if (editLogin.getText().equals("") || editPassword.getText().equals("")) {
            Toast.makeText(this, "Введите логин и пароль", Toast.LENGTH_SHORT).show();
        } else {
            try {
                fileLogins = openFileOutput("login", MODE_PRIVATE);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileLogins);
            BufferedWriter bw = new BufferedWriter(outputStreamWriter);
            try {
                bw.write(editLogin.getText().toString());
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                fileLPassword = openFileOutput("password", MODE_PRIVATE);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            OutputStreamWriter outputStreamWriterPassword = new OutputStreamWriter(fileLPassword);
            BufferedWriter bwp = new BufferedWriter(outputStreamWriterPassword);
            try {
                bwp.write(editPassword.getText().toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                bw.close();
                bwp.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Toast.makeText(this, "Логин и пароль успешно сохранены", Toast.LENGTH_SHORT).show();
        }
    }
}
