package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private ListView cityList;
    private ArrayAdapter<String> cityAdapter;
    private ArrayList<String> dataList;
    private Button addButton, inputButton, deleteButton;
    private EditText inputText;
    private LinearLayout addLayout;
    private int selectedIndex = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);

        String []cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        dataList.add("New city");
        cityAdapter.notifyDataSetChanged();

        // Find views
        addButton = findViewById(R.id.add_button);
        addLayout = findViewById(R.id.add_layout);
        inputText = findViewById(R.id.input_text);
        inputButton = findViewById(R.id.input_button);
        deleteButton = findViewById(R.id.delete_button);


        addButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // show option to enter name of new city that will then be added to the list
                addLayout.setVisibility(View.VISIBLE);
                }
        }));

        inputButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // add selected item
                String text = inputText.getText().toString().trim();

                if(!text.isEmpty()) {

                    dataList.add(text);
                    cityAdapter.notifyDataSetChanged();

                    inputText.setText("");

                    addLayout.setVisibility(View.GONE);
                }
            }
        }));

        // Handle item selection in the ListView
        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedIndex = position; // Track the selected index
                Toast.makeText(MainActivity.this, "Selected: " + dataList.get(position), Toast.LENGTH_SHORT).show();
            }
        });

        // Delete button click listener
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedIndex >= 0 && selectedIndex < dataList.size()) {
                    // Remove the selected item
                    dataList.remove(selectedIndex);
                    cityAdapter.notifyDataSetChanged();
                    selectedIndex = -1; // Reset the selected index
                } else {
                    Toast.makeText(MainActivity.this, "No item selected", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }




}