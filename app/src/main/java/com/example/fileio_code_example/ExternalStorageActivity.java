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

public class ExternalStorageActivity extends UtilityActivity {

    private File testExternalFile;
    private Button externalWrite,externalRead;
    private EditText externalFileName,externalFileData;
    private String fileData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_storage);

        externalFileName = findViewById(R.id.externalFileName);
        externalFileData = findViewById(R.id.externalData);
        externalWrite = findViewById(R.id.externalWrite);
        externalRead = findViewById(R.id.externalRead);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testExternalFile = getExternalDocumentFile(getApplicationContext(),getViewString(externalFileName.getId()));
                if (view == externalWrite && isExternalStorageWritable()) {
                    writeToStorage(testExternalFile,getViewString(externalFileData.getId()));
                }
                else if (view == externalRead && isExternalStorageReadable()) {
                    externalFileData.setText(readFileFromStorage(testExternalFile));
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Your device either contains an emulated sd card or is not mounted",Toast.LENGTH_SHORT).show();
                }
            }
        };

        externalWrite.setOnClickListener(onClickListener);
        externalRead.setOnClickListener(onClickListener);
    }


    private File getExternalDocumentFile(Context context, String fileName)
    {
        return new File (context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS),fileName);
    }

    private boolean isExternalStorageWritable()
    {
        //Ensure the external storage is in fact an sd card and not the emulated one that's built into android devices
        return Environment.isExternalStorageRemovable() && Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    private boolean isExternalStorageReadable()
    {
        String state = Environment.getExternalStorageState();
        //Ensure the external storage is in fact an sd card and not the emulated one that's built into android devices
        return Environment.isExternalStorageRemovable() && Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
    }

}
