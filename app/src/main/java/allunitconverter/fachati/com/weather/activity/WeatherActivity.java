package allunitconverter.fachati.com.weather.activity;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.squareup.picasso.Picasso;

import allunitconverter.fachati.com.weather.Adapter.WeatherDayAdapter;
import allunitconverter.fachati.com.weather.Adapter.WeatherHoursAdapter;
import allunitconverter.fachati.com.weather.R;
import allunitconverter.fachati.com.weather.WeatherApplication;
import allunitconverter.fachati.com.weather.WeatherService.WeatherService;
import allunitconverter.fachati.com.weather.models.Forecast;
import allunitconverter.fachati.com.weather.models.ForecastDay;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

public class WeatherActivity extends BaseActivity {


    private TextView mTextCity;
    private TextView mTextStatus;
    private TextView mTextTmp;
    private TextView mTextWind;
    private TextView mTextHumidity;
    private TextView mTextPressure;



    private RecyclerView mListWeatherHours;
    private RecyclerView mListWeatherDay;

    private Subscription subscription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        Log.e("ezfzefezfezf","lsrgsegsehihihii");
        setupUI();
        String lat = getIntent().getExtras().getString("latitude");
        String lon = getIntent().getExtras().getString("longitude");
        Log.e("ezfzefezfezf",lat+"lhihihii");
        //double lat = getMyLocation().latitude, lon = getMyLocation().longitude;
        loadWeatherDay(lat+"",lon+"");
        loadWeatherHour(lat+"",lon+"");

        //setInfo();



    }

    public void setupUI(){
        mTextCity=(TextView)findViewById(R.id.cityName);
        mTextStatus=(TextView)findViewById(R.id.status);
        mTextTmp=(TextView)findViewById(R.id.temp);
        mTextWind=(TextView)findViewById(R.id.wind);
        mTextHumidity=(TextView)findViewById(R.id.humidity);
        mTextPressure =(TextView)findViewById(R.id.pressure);


        mListWeatherHours = (RecyclerView)findViewById(R.id.recycleViewHour);
        mListWeatherDay = (RecyclerView)findViewById(R.id.recycleViewDay);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mListWeatherHours.setLayoutManager(layoutManager);
        mListWeatherDay.setLayoutManager(new LinearLayoutManager(this));
    }

    public void setInfo(Forecast forecast){

        mTextCity.setText(forecast.getCity().getName());
        mTextStatus.setText(forecast.getList().get(0).getWeather().get(0).getDescription());
        mTextTmp.setText(forecast.getList().get(0).getMain().getTemp()+"");
        mTextWind.setText(forecast.getList().get(0).getWind().getSpeed()+"");
        mTextHumidity.setText(forecast.getList().get(0).getMain().getHumidity()+"");
        mTextPressure.setText(forecast.getList().get(0).getMain().getPressure()+"");


        mListWeatherHours.setAdapter(new WeatherHoursAdapter(forecast.getList(),this));
        //mListWeatherDay.setAdapter(new WeatherDayAdapter(getForecastDay().getList(),this));

    }


    public void clickGetWeatherForOther(View v) {
        final Dialog dialog = new Dialog(WeatherActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_city);

        final EditText textCity = (EditText) dialog.findViewById(R.id.editTextCity);
        textCity.setTextColor(getResources().getColor(R.color.black));
        Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(!textCity.getText().toString().isEmpty()){
                    LatLng location=getLocationFromAddress(textCity.getText().toString());


                    Log.e("city",textCity.getText().toString()+"--"+location.latitude+" - "+location.longitude);
                    loadWeatherDay(location.latitude+"",location.longitude+"");
                    loadWeatherHour(location.latitude+"",location.longitude+"");
                }



                dialog.dismiss();

            }
        });

        dialog.show();
    }

    public void loadWeatherHour(String lat,String lon) {
        WeatherApplication application = WeatherApplication.get(this);
        WeatherService weatherService = application.getService();
        subscription = weatherService.getWeatherHour(lat,lon,getString(R.string.unit),"fr","10",getString(R.string.APP_ID))//params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(application.defaultSubscribeScheduler())
                .subscribe(new Subscriber<Forecast>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable error) {
                        Log.e("error",error.toString());
                    }

                    @Override
                    public void onNext(Forecast forecast) {
                        //BaseActivity.forecastHour =forecast;
                        setInfo(forecast);
                        hideStatusBar();

                    }
                });
    }

    public void loadWeatherDay(String lat,String lon) {
        WeatherApplication application = WeatherApplication.get(this);
        WeatherService weatherService = application.getService();
        subscription = weatherService.getWeatherDay(lat,lon,getString(R.string.unit),"fr","10",getString(R.string.APP_ID))//params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(application.defaultSubscribeScheduler())
                .subscribe(new Subscriber<ForecastDay>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable error) {
                        Log.e("tag",error.toString());
                    }

                    @Override
                    public void onNext(ForecastDay forecast) {

                        mListWeatherDay.setAdapter(new WeatherDayAdapter(forecast.getList(),WeatherActivity.this));

                        hideStatusBar();
                    }
                });
    }


}
