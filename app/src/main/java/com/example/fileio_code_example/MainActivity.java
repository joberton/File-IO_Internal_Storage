package com.example.fileio_code_example;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    private Button write,read;
    private EditText fileName,fileData;

    private String testInternalStorageFileName;
    private String testInternalStorageData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        write = findViewById(R.id.write);
        read = findViewById(R.id.read);
        fileName = findViewById(R.id.fileName);
        fileData = findViewById(R.id.fileData);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view == write)
                {
                    testInternalStorageFileName = getViewString(fileName.getId());
                    testInternalStorageData = getViewString(fileData.getId());
                    writeToInternalStorage(testInternalStorageFileName,testInternalStorageData);
                }
                else if(view == read)
                {
                    testInternalStorageFileName = getViewString(fileName.getId());
                    testInternalStorageData = readFileFromInternalStorage(testInternalStorageFileName);
                    fileData.setText(testInternalStorageData);
                }
            }
        };

        write.setOnClickListener(onClickListener);
        read.setOnClickListener(onClickListener);

    }

    private String readFileFromInternalStorage(String fileName)
    {
        FileInputStream inputStream;
        StringBuilder data = new StringBuilder();
        int asciiCharacter;
        try
        {
            inputStream = openFileInput(fileName);
            while((asciiCharacter = inputStream.read()) != -1)
            {
                data.append(Character.toString((char) asciiCharacter));
            }
            inputStream.close();
            Toast.makeText(getApplicationContext(), "Data has been successfully read from internal storage!!!",Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), "Data has failed to be read from internal storage!!!",Toast.LENGTH_SHORT).show();
            Log.e("File_Read", "An error was encountered while reading from the file: " + e.getMessage());
        }
        return data.toString();
    }

    private void writeToInternalStorage(String fileName, String data)
    {
        FileOutputStream outputStream;
        try
        {
            outputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
            outputStream.write(data.getBytes());
            outputStream.close();
            Toast.makeText(getApplicationContext(), "Data has been successfully written to internal storage!!!",Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), "Data has failed to be written to internal storage!!!",Toast.LENGTH_SHORT).show();
            Log.e("File_Write", "An error was encountered while writing to the file: " + e.getMessage());
        }
    }

    private String getViewString(int id)
    {
        return ((EditText)findViewById(id)).getText().toString();
    }
}
