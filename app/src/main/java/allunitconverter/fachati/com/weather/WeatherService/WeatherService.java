package allunitconverter.fachati.com.weather.WeatherService;

import java.util.Map;

import allunitconverter.fachati.com.weather.models.Forecast;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by fachati on 23/03/17.
 */

public interface WeatherService {


    //@GET("data/2.5/forecast?lat=48.7817798&lon=2.215113&units=metric&lang=fr&appid=69d3ebaf41c279fdab03729bcfa7da53")
    @GET("data/2.5/forecast")
    //@FormUrlEncoded
    Observable<Forecast> getWeather(@Query("lat") String lat
            ,@Query("lon") String lon
            ,@Query("units") String units
            ,@Query("metric") String metric
            ,@Query("appid") String appid);//@QueryMap Map<String, String> params);

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
