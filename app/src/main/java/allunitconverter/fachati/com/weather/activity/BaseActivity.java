package allunitconverter.fachati.com.weather.activity;

import android.Manifest;
import android.app.ActivityManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import allunitconverter.fachati.com.weather.WeatherApplication;
import allunitconverter.fachati.com.weather.WeatherService.WeatherService;
import allunitconverter.fachati.com.weather.models.Forecast;
import allunitconverter.fachati.com.weather.models.Weather;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by fachati on 23/03/17.
 */

public class BaseActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static final String APP_ID = "80e4eede56844462ef3cdc721208c31f";

    private static final int PERMISSION_ACCESS_COARSE_LOCATION = 1;
    private GoogleApiClient googleApiClient;
    private LatLng myLocation;
    private Subscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();
        hideStatusBar();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.ACCESS_COARSE_LOCATION },
                    PERMISSION_ACCESS_COARSE_LOCATION);
        }

        googleApiClient = new GoogleApiClient.Builder(this, this, this).addApi(LocationServices.API).build();


    }

    public LatLng getLocationFromAddress(String strAddress){
        LatLng latLng=null;
        Geocoder coder = new Geocoder(this);
        List<Address> addresses;
        try {
            addresses = coder.getFromLocationName(strAddress, 5);
            if (addresses == null) {
            }
            Address location = addresses.get(0);
            double lat = location.getLatitude();
            double lng = location.getLongitude();
            latLng = new LatLng(lat,lng);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return latLng;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_ACCESS_COARSE_LOCATION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    Toast.makeText(this, "Need your location!", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (googleApiClient != null) {
            googleApiClient.connect();
        }
    }

    @Override
    protected void onStop() {
        googleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.i(WelcomeActivity.class.getSimpleName(), "Connected to Google Play Services!");

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
            myLocation=new LatLng(lastLocation.getLatitude(),lastLocation.getLongitude());
            double lat = lastLocation.getLatitude(), lon = lastLocation.getLongitude();
            loadInformations(lat+"",lon+"");
            Log.e("text",lat+" - "+lon);

        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i(WelcomeActivity.class.getSimpleName(), "Can't connect to Google Play Services!");
    }

    void hideStatusBar() {
        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    public void loadInformations(String lat,String lon) {



        WeatherApplication application = WeatherApplication.get(this);
        WeatherService weatherService = application.getService();
        subscription = weatherService.getWeather(lat,lon,"val1","fr","69d3ebaf41c279fdab03729bcfa7da53")//params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(application.defaultSubscribeScheduler())
                .subscribe(new Subscriber<Forecast>() {
                    @Override
                    public void onCompleted() {
                        if(getActivityName().compareTo("WeatherActivity")!=0)
                            startActivity(new Intent(BaseActivity.this, WeatherActivity.class));
                    }

                    @Override
                    public void onError(Throwable error) {
                        Log.e("tag",error.toString());
                    }

                    @Override
                    public void onNext(Forecast forecast) {
                        Log.e("tag","complet"+forecast.getCity().getName());

                    }
                });
    }

    public String getActivityName(){
        ActivityManager am = (ActivityManager)getSystemService(ACTIVITY_SERVICE);
        List< ActivityManager.RunningTaskInfo > taskInfo = am.getRunningTasks(1);
        String currentActivity=(taskInfo.get(0).topActivity.getClassName())
                .substring(taskInfo.get(0).topActivity.getClassName().lastIndexOf(".")+1,taskInfo.get(0).topActivity.getClassName().length());
        return currentActivity;
    }





}
