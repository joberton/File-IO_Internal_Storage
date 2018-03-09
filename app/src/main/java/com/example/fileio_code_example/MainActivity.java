package com.example.fileio_code_example;

import android.content.Context;
import android.content.Intent;
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

public class MainActivity extends UtilityActivity {

    private Button write,read,externalStorage;
    private EditText fileName,fileData;

    private File testInternalStorageFile;
    private String testInternalStorageData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        write = findViewById(R.id.write);
        read = findViewById(R.id.read);
        externalStorage = findViewById(R.id.externalStorage);
        fileName = findViewById(R.id.fileName);
        fileData = findViewById(R.id.fileData);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testInternalStorageFile = getInternalDocumentFile(getApplicationContext(),getViewString(fileName.getId()));
                if(view == write)
                {
                    testInternalStorageData = getViewString(fileData.getId());
                    writeToStorage(testInternalStorageFile,testInternalStorageData);
                }
                else if(view == read)
                {
                    testInternalStorageData = readFileFromStorage(testInternalStorageFile);
                    fileData.setText(testInternalStorageData);
                }
                else if(view == externalStorage)
                {
                    startActivity(new Intent(getApplicationContext(),ExternalStorageActivity.class));
                }
            }
        };

        write.setOnClickListener(onClickListener);
        read.setOnClickListener(onClickListener);
        externalStorage.setOnClickListener(onClickListener);
    }

    private File getInternalDocumentFile(Context context, String fileName)
    {
        return new File (context.getFilesDir(),fileName);
    }
}
