package allunitconverter.fachati.com.weather.WeatherService;

import java.util.Map;

import allunitconverter.fachati.com.weather.models.Forecast;
import allunitconverter.fachati.com.weather.models.ForecastDay;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by fachati on 23/03/17.
 */

public interface WeatherService {

    @GET("data/2.5/forecast")
    Observable<Forecast> getWeatherHour(@Query("lat") String lat
            ,@Query("lon") String lon
            ,@Query("units") String units
            ,@Query("lang") String lang
            ,@Query("cnt") String cnt
            ,@Query("appid") String appid);

    @GET("data/2.5/forecast/daily")
    Observable<ForecastDay> getWeatherDay(@Query("lat") String lat
            , @Query("lon") String lon
            , @Query("units") String units
            , @Query("lang") String lang
            , @Query("cnt") String cnt
            , @Query("appid") String appid);

    class Factory {
        public static WeatherService create() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://api.openweathermap.org/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(WeatherService.class);
        }
    }


}
