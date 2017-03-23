package allunitconverter.fachati.com.weather.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by fachati on 23/03/17.
 */
public class List implements Parcelable {

    @SerializedName("dt")
    private int mDt;

    @SerializedName("dt_txt")
    private String dt;

    private Main main;

    @SerializedName("weather")
    private ArrayList<Weather> weathers;

    private Clouds clouds;

    private Wind wind;

    private Rain rain;

    private Snow snow;

    public Snow getSnow() {
        return snow;
    }

    public void setSnow(Snow snow) {
        this.snow = snow;
    }

    public Rain getRain() {
        return rain;
    }

    public void setRain(Rain rain) {
        this.rain = rain;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public ArrayList<Weather> getWeather() {
        return weathers;
    }

    public void setWeather(ArrayList<Weather> weather) {
        weathers = weather;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public String getDtText() {
        return dt;
    }

    public void setDtText(String dtText) {
        dt = dtText;
    }

    public int getDt() {
        return mDt;
    }

    public void setDt(int dt) {
        mDt = dt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mDt);
        dest.writeString(this.dt);
        dest.writeParcelable(this.main, flags);
        dest.writeList(this.weathers);
        dest.writeParcelable(this.clouds, flags);
        dest.writeParcelable(this.wind, flags);
        dest.writeParcelable(this.rain, flags);
        dest.writeParcelable(this.snow, flags);
    }

    public List() {
    }

    private List(Parcel in) {
        this.mDt = in.readInt();
        this.dt = in.readString();
        this.main = in.readParcelable(Main.class.getClassLoader());
        this.weathers = new ArrayList<>();
        in.readList(this.weathers, Weather.class.getClassLoader());
        this.clouds = in.readParcelable(Clouds.class.getClassLoader());
        this.wind = in.readParcelable(Wind.class.getClassLoader());
        this.rain = in.readParcelable(Rain.class.getClassLoader());
        this.snow = in.readParcelable(Snow.class.getClassLoader());
    }

    public static final Creator<List> CREATOR = new Creator<List>() {
        @Override
        public List createFromParcel(Parcel source) {
            return new List(source);
        }

        @Override
        public List[] newArray(int size) {
            return new List[size];
        }
    };
}
