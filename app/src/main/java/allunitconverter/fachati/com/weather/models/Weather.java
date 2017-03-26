package allunitconverter.fachati.com.weather.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by fachati on 23/03/17.
 */
public class Weather implements Parcelable {


    private int id;
    private String description;
    private String icon;
    private String main;

    public String getMain() {
        return main;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.description);
        dest.writeString(this.icon);
        dest.writeString(this.main);
    }

    public Weather() {
    }

    private Weather(Parcel in) {
        this.id = in.readInt();
        this.description = in.readString();
        this.icon = in.readString();
        this.main = in.readString();
    }

    public static final Creator<Weather> CREATOR = new Creator<Weather>() {
        @Override
        public Weather createFromParcel(Parcel source) {
            return new Weather(source);
        }

        @Override
        public Weather[] newArray(int size) {
            return new Weather[size];
        }
    };

    @Override
    public String toString() {
        return "Weather{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", icon='" + icon + '\'' +
                ", main='" + main + '\'' +
                '}';
    }
}
