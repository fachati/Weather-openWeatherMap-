package allunitconverter.fachati.com.weather.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by fachati on 23/03/17.
 */
public class ListDay implements Parcelable {

    @SerializedName("dt")
    private int mDt;


    @SerializedName("temp")
    private MainDay main;

    @SerializedName("weather")
    private ArrayList<Weather> weathers;





    public ArrayList<Weather> getWeather() {
        return weathers;
    }

    public void setWeather(ArrayList<Weather> weather) {
        weathers = weather;
    }

    public MainDay getMain() {
        return main;
    }

    public void setMain(MainDay main) {
        this.main = main;
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
        dest.writeParcelable(this.main, flags);
        dest.writeList(this.weathers);
    }

    public ListDay() {
    }

    private ListDay(Parcel in) {
        this.mDt = in.readInt();
        this.main = in.readParcelable(Main.class.getClassLoader());
        this.weathers = new ArrayList<>();
        in.readList(this.weathers, Weather.class.getClassLoader());
    }

    public static final Creator<ListDay> CREATOR = new Creator<ListDay>() {
        @Override
        public ListDay createFromParcel(Parcel source) {
            return new ListDay(source);
        }

        @Override
        public ListDay[] newArray(int size) {
            return new ListDay[size];
        }
    };

    @Override
    public String toString() {
        return "ListDay{" +
                "mDt=" + mDt +
                ", main=" + main +
                ", weathers=" + weathers +
                '}';
    }
}
