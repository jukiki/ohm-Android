package com.example.pesselch66748.speedreader;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private int index = 0;

    String arr[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = (TextView)findViewById(R.id.tvWholeText);
        tv.setMovementMethod(new ScrollingMovementMethod());

        final Button btnDelete = (Button)findViewById(R.id.btnDelete);

        btnDelete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                TextView tv = (TextView)findViewById(R.id.tvWholeText);
                tv.setText("");
                tv = (TextView)findViewById(R.id.tvSingleWord);
                tv.setText("");
                index = 0;
            }
        });
    }

    public void loadText(View v)
    {
        TextView tv = (TextView)findViewById(R.id.tvWholeText);
        tv.setText(auslesen("text"));
        arr = auslesen("text").split(" ");
    }

    public void nextWord(View v)
    {
        TextView tv = (TextView)findViewById(R.id.tvSingleWord);
        tv.setText(arr[index++]);
    }

    private String auslesen(String assetName)
    {
        AssetManager manager = getAssets();
        try
        {
            InputStream is = manager.open(assetName);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line;
            while((line=br.readLine()) != null)
            {
                sb.append(line).append("\n");
            }
            return sb.toString();

        }
        catch (IOException e)
        {
            return "Asset konnte nicht gelesen werden";
        }
    }

}
