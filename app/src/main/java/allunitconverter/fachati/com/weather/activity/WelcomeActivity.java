package allunitconverter.fachati.com.weather.activity;

import android.os.Bundle;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import allunitconverter.fachati.com.weather.R;
import allunitconverter.fachati.com.weather.WeatherApplication;
import allunitconverter.fachati.com.weather.WeatherService.WeatherService;
import allunitconverter.fachati.com.weather.models.Forecast;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

public class WelcomeActivity extends BaseActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        //Log.e("addresslol",getLocationFromAddress("madrid").latitude+"-"+getLocationFromAddress("madrid").longitude);
    }



}
