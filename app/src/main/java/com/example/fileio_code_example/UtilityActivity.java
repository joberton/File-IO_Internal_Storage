package com.example.fileio_code_example;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class UtilityActivity extends AppCompatActivity {

    public void writeToStorage(File file, String data)
    {
        try
        {
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(data.getBytes());
            outputStream.close();
            Toast.makeText(getApplicationContext(), "Data has been successfully written to storage",Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), "Data has failed to be written to storage!!!",Toast.LENGTH_SHORT).show();
            Log.e("File_Write", "An error was encountered while writing to storage: " + e.getMessage());
        }
    }

    public String readFileFromStorage(File file)
    {
        StringBuilder data = new StringBuilder();
        int asciiCharacter;
        try
        {
            FileInputStream inputStream = new FileInputStream(file);
            while((asciiCharacter = inputStream.read()) != -1)
            {
                data.append(Character.toString((char) asciiCharacter));
            }
            inputStream.close();
            Toast.makeText(getApplicationContext(), "Data has been successfully read from storage!!!",Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), "Data has failed to be read from storage!!!",Toast.LENGTH_SHORT).show();
            Log.e("File_Read", "An error was encountered while reading from storage: " + e.getMessage());
        }
        return data.toString();
    }

    public String getViewString(int id)
    {
        return ((EditText)findViewById(id)).getText().toString();
    }
}
