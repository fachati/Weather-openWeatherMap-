package allunitconverter.fachati.com.weather.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by fachati on 23/03/17.
 */
public class Snow implements Parcelable {

    @SerializedName("3h")
    private float volume;

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(this.volume);
    }

    public Snow() {
    }

    private Snow(Parcel in) {
        this.volume = in.readFloat();
    }

    public static final Creator<Snow> CREATOR = new Creator<Snow>() {
        @Override
        public Snow createFromParcel(Parcel source) {
            return new Snow(source);
        }

        @Override
        public Snow[] newArray(int size) {
            return new Snow[size];
        }
    };
}
