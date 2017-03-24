package allunitconverter.fachati.com.weather;

import android.content.Context;



import allunitconverter.fachati.com.weather.WeatherService.WeatherService;
import rx.Scheduler;
import rx.schedulers.Schedulers;

/**
 * Created by fachati on 23/03/17.
 */

public class WeatherApplication extends android.app.Application {

    private WeatherService service;
    private Scheduler defaultSubscribeScheduler;



    public static WeatherApplication get(Context context) {
        return (WeatherApplication) context.getApplicationContext();
    }

    public WeatherService getService() {
        if (service == null) {
            service = WeatherService.Factory.create();
        }
        return service;
    }

    public Scheduler defaultSubscribeScheduler() {
        if (defaultSubscribeScheduler == null) {
            defaultSubscribeScheduler = Schedulers.io();
        }
        return defaultSubscribeScheduler;
    }
}
