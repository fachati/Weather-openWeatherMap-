package allunitconverter.fachati.com.weather;

import android.os.Bundle;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import allunitconverter.fachati.com.weather.WeatherService.WeatherService;
import allunitconverter.fachati.com.weather.models.Forecast;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

public class WelcomeActivity extends BaseActivity {


    private Subscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Log.e("addresslol",getLocationFromAddress("madrid").latitude+"-"+getLocationFromAddress("madrid").longitude);
        loadGithubRepos();
    }

    public void loadGithubRepos() {

        Map<String, String> params = new HashMap<String, String>();

        params.put("lat", "48.7817798");
        params.put("lon", "2.215113");
        params.put("units", "val1");
        params.put("lang", "fr");
        params.put("appid", "69d3ebaf41c279fdab03729bcfa7da53");

        WeatherApplication application = WeatherApplication.get(WelcomeActivity.this);
        WeatherService weatherService = application.getService();
        subscription = weatherService.getWeather("48.7817798","2.215113","val1","fr","69d3ebaf41c279fdab03729bcfa7da53")//params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(application.defaultSubscribeScheduler())
                .subscribe(new Subscriber<Forecast>() {
                    @Override
                    public void onCompleted() {
                        Log.e("tag","complet");
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
}
