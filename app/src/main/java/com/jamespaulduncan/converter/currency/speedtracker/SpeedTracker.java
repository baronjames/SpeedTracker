package com.jamespaulduncan.converter.currency.speedtracker;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class SpeedTracker extends ActionBarActivity
{
    private static final String TAG = "JD SpeedTracker";

    Context context;
    private TextView speedTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speed_tracker);

        speedTextView = (TextView) findViewById(R.id.speedTextView);

        // Acquire a reference to the system Location Manager
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        // Define a listener that responds to location updates
        LocationListener locationListener = new LocationListener()
        {
            public void onLocationChanged(Location location)
            {
                location.getLatitude();
                float conversionFactor = 3.6f;
                float kmph = location.getSpeed() * conversionFactor;
                //Toast.makeText(context, "Current speed:" + location.getSpeed(), Toast.LENGTH_SHORT).show();
                //speedTextView.setText("Speed = " + location.getSpeed() + "m/s");
                speedTextView.setText(kmph + "k/h   " + location.getSpeed() + "m/s");
                Log.d(TAG, "toString " + location.toString());
                Log.d(TAG, "describeContents " + location.describeContents());
                Log.d(TAG, "getAccuracy " + location.getAccuracy());
                Log.d(TAG, "getAltitude " + location.getAltitude());
//                Log.d(TAG, "getElapsedRealtimeNanos " + location.getElapsedRealtimeNanos()); //Requires API level 17+
                Log.d(TAG, "getLatitude " + location.getLatitude());
                Log.d(TAG, "getLongitude " + location.getLongitude());
                Log.d(TAG, "getTime " + location.getTime());

            }

            public void onStatusChanged(String provider, int status, Bundle extras)
            {
            }

            public void onProviderEnabled(String provider)
            {
            }

            public void onProviderDisabled(String provider)
            {
            }
        };
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_speed_checker, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
