package com.smalltasksathand.kaushik.smalltasksathand;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;


public class MainActivity extends Activity {
    private static final String TAG ="" ;
    static double longitude;
    static double latitude;
    static TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        longitude=0;latitude=0;
        t=(TextView)findViewById(R.id.textView2);
        final EditText description = (EditText) findViewById(R.id.description);
        Button post= (Button) findViewById(R.id.button);
        final RadioButton map=(RadioButton)(findViewById(R.id.radioButton2));
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getApplicationContext(),MAPS.class);
                Log.v(TAG,"after intent");
                startActivityForResult(in, 1);
            }
        });
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Toast.makeText(getApplicationContext(),description.getText(),Toast.LENGTH_SHORT).show();
                //System.out.print("sucess");
                try {
                    connection con= new connection();
                    if(!map.isChecked())
                    {
                    LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                    String provider=locationManager.NETWORK_PROVIDER;
                    Location location = locationManager.getLastKnownLocation(provider);
                    longitude=location.getLongitude();
                    latitude=location.getLatitude();
                    }
                    con.push("12345",description.getText().toString(),longitude,latitude);
                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT);
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(),"task posted",Toast.LENGTH_SHORT).show();
            }
        });

    }
    public static void update(LatLng l)
    {
        latitude=l.latitude;
        longitude=l.longitude;
        t.setText(l.toString());
        t.setVisibility(View.VISIBLE);

     }//onActivityResult

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
