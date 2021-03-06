package com.example.bitmexam;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListViewShow extends AppCompatActivity {
    private ListView listView;
    DataBaseHelper dataBaseHelper;
    ArrayList<String> listData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        listView = findViewById(R.id.listView);
        dataBaseHelper = new DataBaseHelper(this);

//R.array is setted up in values>>string.xml

        loadData();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.sample_view, R.id.textViewID, listData);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String value = adapterView.getItemAtPosition(i).toString();

                Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT).show();

            }
        });
        // loadData();
    }

    public void loadData() {
        // dataBaseHelper = new DataBaseHelper(this);


        Cursor cursor = dataBaseHelper.showAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(getApplicationContext(), "No data available", Toast.LENGTH_SHORT).show();
        } else {

            while (cursor.moveToNext()) {
                listData.add(cursor.getString(1) + " \n" + cursor.getString(2) + " \n" + cursor.getString(3));

            }
        }


    }
}
