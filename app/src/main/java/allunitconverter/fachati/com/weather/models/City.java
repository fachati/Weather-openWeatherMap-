package allunitconverter.fachati.com.weather.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by fachati on 23/03/17.
 */

public class City implements Parcelable{

    private String name;
    private String country;
    private int population;



    protected City(Parcel in) {
        name = in.readString();
        country = in.readString();
        population = in.readInt();
    }

    public static final Creator<City> CREATOR = new Creator<City>() {
        @Override
        public City createFromParcel(Parcel in) {
            return new City(in);
        }

        @Override
        public City[] newArray(int size) {
            return new City[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(country);
        parcel.writeInt(population);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;

        City City = (City) o;

        if (population != City.population) return false;
        if (!name.equals(City.name)) return false;
        return country.equals(City.country);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + country.hashCode();
        result = 31 * result + population;
        return result;
    }

    public String getName() {
        return name;
    }


}
