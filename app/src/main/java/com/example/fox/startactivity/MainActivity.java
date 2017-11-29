package com.example.fox.startactivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText NameEntry;
    private Button DoSomethingCoolButton;
    private Button searchGooogle;
    private EditText foundAnotherInfo;
    private Button showMap;
    private Button actionButton;
    private List<String> listTreaning;
    ListView listView;
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
         * Using findViewById, we get a reference to our Button from xml. This allows us to
         * do things like set the onClickListener which determines what happens when the button
         * is clicked.
         */
        DoSomethingCoolButton = (Button) findViewById(R.id.b_do_something_cool);
        NameEntry = (EditText) findViewById(R.id.et_text_entry);
        searchGooogle = (Button)findViewById(R.id.search_internet);
        foundAnotherInfo = (EditText)findViewById(R.id.find_new_query);
        showMap = (Button)findViewById(R.id.show_map);
        listTreaning = new ArrayList<String>();
        for(int i = 0; i< 15; i++){
            listTreaning.add("Упражненеи"+i);

        }
         listView = (ListView)findViewById(R.id.list_treaning) ;
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1);
        listView.setAdapter(adapter);

        /* Setting an OnClickListener allows us to do something when this button is clicked. */
        DoSomethingCoolButton.setOnClickListener(new View.OnClickListener() {//Почему не сработала передача OnClicListner??
            /**
             * The onClick method is triggered when this button (mDoSomethingCoolButton) is clicked.
             *
             * @param v The view that is clicked. In this case, it's mDoSomethingCoolButton.
             */
            @Override
            public void onClick(View v) {

                String enterText = NameEntry.getText().toString();

                Context context = MainActivity.this;
                Class destinationActivity = ChildActivity.class;
                Intent startChildActivity = new Intent(context, destinationActivity);

                startChildActivity.putExtra(Intent.EXTRA_TEXT, enterText);
                        startActivity(startChildActivity);



                /*
                 * Storing the Context in a variable in this case is redundant since we could have
                 * just used "this" or "MainActivity.this" in the method call below. However, we
                 * wanted to demonstrate what parameter we were using "MainActivity.this" for as
                 * clear as possible.
                 */
              /*  Context context = MainActivity.this;
                String message = "Button clicked!\nTODO: Start a new Activity and pass some data.";
                Toast.makeText(context, message, Toast.LENGTH_LONG).show();*/
            }
        });

        searchGooogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String findInfo = foundAnotherInfo.getText().toString();
                openWebPage(findInfo);


            }
        });

        showMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String addressString = "1600 Amphitheatre Parkway, CA";

                // COMPLETED (6) Use Uri.Builder with the appropriate scheme and query to form the Uri for the address
                Uri.Builder builder = new Uri.Builder();
                builder.scheme("geo")
                        .path("0,0")
                        .query(addressString);
                Uri addressUri = builder.build();
                goShowMap(addressUri);
            }
        });


    }

    private void goShowMap(Uri geoLocation) {

        // COMPLETED (2) Create an Intent with action type, Intent.ACTION_VIEW
        /*
         * Again, we create an Intent with the action, ACTION_VIEW because we want to VIEW the
         * contents of this Uri.
         */
        Intent intent = new Intent(Intent.ACTION_VIEW);

        // COMPLETED (3) Set the data of the Intent to the Uri passed into this method
        /*
         * Using setData to set the Uri of this Intent has the exact same affect as passing it in
         * the Intent's constructor. This is simply an alternate way of doing this.
         */
        intent.setData(geoLocation);


        // COMPLETED (4) Verify that this Intent can be launched and then call startActivity
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);

        }
    }

    private void openWebPage(String url) {//Ссылка не работает, выяснить почему

        Uri webPage= Uri.parse(url);
        Intent intent =  new Intent(Intent.ACTION_VIEW, webPage);

        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return true;
    }
}