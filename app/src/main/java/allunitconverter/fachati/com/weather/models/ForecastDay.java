package allunitconverter.fachati.com.weather.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by fachati on 23/03/17.
 */
public class ForecastDay implements Parcelable {

    private City city;
    @SerializedName("list")
    private ArrayList<ListDay> listWeather;

    public ArrayList<ListDay> getList() {
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

    private ForecastDay(Parcel in) {
        this.city = in.readParcelable(City.class.getClassLoader());
        this.listWeather = new ArrayList<>();
        in.readList(this.listWeather, List.class.getClassLoader());
    }

    public static final Creator<ForecastDay> CREATOR = new Creator<ForecastDay>() {
        @Override
        public ForecastDay createFromParcel(Parcel source) {
            return new ForecastDay(source);
        }

        @Override
        public ForecastDay[] newArray(int size) {
            return new ForecastDay[size];
        }
    };
}
