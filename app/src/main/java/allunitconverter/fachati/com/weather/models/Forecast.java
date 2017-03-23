package allunitconverter.fachati.com.weather.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by fachati on 23/03/17.
 */
public class Forecast implements Parcelable {

    private City city;
    @SerializedName("list")
    private ArrayList<List> listWeather;

    public ArrayList<List> getList() {
        return listWeather;
    }

    public City getCity() {
        return city;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.city, flags);
        dest.writeList(this.listWeather);
    }

    private Forecast(Parcel in) {
        this.city = in.readParcelable(City.class.getClassLoader());
        this.listWeather = new ArrayList<>();
        in.readList(this.listWeather, List.class.getClassLoader());
    }

    public static final Creator<Forecast> CREATOR = new Creator<Forecast>() {
        @Override
        public Forecast createFromParcel(Parcel source) {
            return new Forecast(source);
        }

        @Override
        public Forecast[] newArray(int size) {
            return new Forecast[size];
        }
    };
}
