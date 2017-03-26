package allunitconverter.fachati.com.weather.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by fachati on 23/03/17.
 */
public class MainDay implements Parcelable {


    @SerializedName("min")
    private float tempMin;

    @SerializedName("max")
    private float tempMax;




    public float getTempMin() {
        return tempMin;
    }

    public void setTempMin(float tempMin) {
        this.tempMin = tempMin;
    }

    public float getTempMax() {
        return tempMax;
    }

    public void setTempMax(float tempMax) {
        this.tempMax = tempMax;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeFloat(this.tempMin);
        dest.writeFloat(this.tempMax);
    }

    public MainDay() {
    }

    private MainDay(Parcel in) {
        this.tempMin = in.readFloat();
        this.tempMax = in.readFloat();
    }

    public static final Creator<MainDay> CREATOR = new Creator<MainDay>() {
        @Override
        public MainDay createFromParcel(Parcel source) {
            return new MainDay(source);
        }

        @Override
        public MainDay[] newArray(int size) {
            return new MainDay[size];
        }
    };


    @Override
    public String toString() {
        return "MainDay{" +
                "tempMin=" + tempMin +
                ", tempMax=" + tempMax +
                '}';
    }
}
