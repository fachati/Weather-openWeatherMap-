package allunitconverter.fachati.com.weather.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by fachati on 23/03/17.
 */
public class Main implements Parcelable {
    
    private float temp;
    
    private float humidity;
    
    @SerializedName("temp_min")
    private float tempMin;
    
    @SerializedName("temp_max")
    private float tempMax;
    
    @SerializedName("sea_level")
    private float saeLevel;
    
    @SerializedName("grnd_level")
    private float grndLevel;

    private float pressure;

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

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

    public float getSeaLevel() {
        return saeLevel;
    }

    public void setSeaLevel(float seaLevel) {
        saeLevel = seaLevel;
    }

    public float getGrndLevel() {
        return grndLevel;
    }

    public void setGrndLevel(float grndLevel) {
        this.grndLevel = grndLevel;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(this.temp);
        dest.writeFloat(this.humidity);
        dest.writeFloat(this.tempMin);
        dest.writeFloat(this.tempMax);
        dest.writeFloat(this.saeLevel);
        dest.writeFloat(this.grndLevel);
        dest.writeFloat(this.pressure);
    }

    public Main() {
    }

    private Main(Parcel in) {
        this.temp = in.readFloat();
        this.humidity = in.readFloat();
        this.tempMin = in.readFloat();
        this.tempMax = in.readFloat();
        this.saeLevel = in.readFloat();
        this.grndLevel = in.readFloat();
        this.pressure = in.readFloat();
    }

    public static final Creator<Main> CREATOR = new Creator<Main>() {
        @Override
        public Main createFromParcel(Parcel source) {
            return new Main(source);
        }

        @Override
        public Main[] newArray(int size) {
            return new Main[size];
        }
    };

    @Override
    public String toString() {
        return "Main{" +
                "temp=" + temp +
                ", humidity=" + humidity +
                ", tempMin=" + tempMin +
                ", tempMax=" + tempMax +
                ", saeLevel=" + saeLevel +
                ", grndLevel=" + grndLevel +
                ", pressure=" + pressure +
                '}';
    }
}
