package ru.sheps.android.homework521;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
 EditText editLogin;
 EditText editPassword;
 Button btnLogin;
 Button btnPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editLogin = findViewById(R.id.editLogin);
        editPassword = findViewById(R.id.editPassword);

    }

    public void onClickLogin(View view) {
        // Получим входные байты из файла которых нужно прочесть.
        FileInputStream fileInputStream = openFileInput(filename); // какой именно файл открывать? С паролями или с логинами?
// Декодируем байты в символы
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
// Читаем данные из потока ввода, буферизуя символы так, чтобы обеспечить эффективную запись отдельных символов.
        BufferedReader reader = new BufferedReader(inputStreamReader);
        reader.readLine();
    }

    public void onClickRegistration(View view) {
        if (editLogin.getText().equals("") || editPassword.getText().equals("")) {
            Toast.makeText(this, "Введите логин и пароль", Toast.LENGTH_SHORT).show();
        } else {
            // Создадим файл и откроем поток для записи данных
            FileOutputStream fileLogins = null;
            try {
                fileLogins = openFileOutput("login", MODE_PRIVATE);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
// Обеспечим переход символьных потока данных к байтовым потокам.
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileLogins);
// Запишем текст в поток вывода данных, буферизуя символы так, чтобы обеспечить эффективную запись отдельных символов.
            BufferedWriter bw = new BufferedWriter(outputStreamWriter);
// Осуществим запись данных
            try {
                bw.write(editLogin.getText().toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            FileOutputStream fileLPassword = null;
            try {
                fileLPassword = openFileOutput("password", MODE_PRIVATE);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
// Обеспечим переход символьных потока данных к байтовым потокам.
            OutputStreamWriter outputStreamWriterPassword = new OutputStreamWriter(fileLPassword);
// Запишем текст в поток вывода данных, буферизуя символы так, чтобы обеспечить эффективную запись отдельных символов.
            BufferedWriter bwp = new BufferedWriter(outputStreamWriterPassword);
// Осуществим запись данных
            try {
                bwp.write(editPassword.getText().toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
// закроем поток
            try {
                bw.close();
                bwp.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
