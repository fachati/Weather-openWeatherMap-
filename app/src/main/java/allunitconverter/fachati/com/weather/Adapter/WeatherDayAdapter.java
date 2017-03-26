package allunitconverter.fachati.com.weather.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import allunitconverter.fachati.com.weather.R;
import allunitconverter.fachati.com.weather.models.ListDay;

/**
 * Created by ismailfachati on 25/03/2017.
 */
public class WeatherDayAdapter extends RecyclerView.Adapter<WeatherDayAdapter.ViewHolder> {

    ArrayList<ListDay> list;
    private Context contex;

    public WeatherDayAdapter(ArrayList<ListDay> list,Context context) {
        this.list = list;
        this.contex=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int itemType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_weather_days,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder myViewHolder, int position) {
        ListDay myObject = list.get(position);
        myViewHolder.bind(myObject);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView mTextDay;
        private TextView mTextTmpMin;
        private TextView mTextTmpMax;
        private ImageView mImageStatus;

        public ViewHolder(View itemView) {
            super(itemView);

            mTextDay = (TextView) itemView.findViewById(R.id.textDay);
            mTextTmpMin = (TextView) itemView.findViewById(R.id.textTmpMinDay);
            mTextTmpMax = (TextView) itemView.findViewById(R.id.textTmpMaxDay);
            mImageStatus = (ImageView) itemView.findViewById(R.id.imageStatusDay);
        }

        public void bind(ListDay weather){
            String idStatus= weather.getWeather().get(0).getIcon();


            mTextDay.setText(getDay(weather.getDt())+"");

            mTextTmpMin.setText(String.format ("%02d",(int)weather.getMain().getTempMin())+"°");
            mTextTmpMax.setText(String.format ("%02d",(int)weather.getMain().getTempMax())+"°");

            Picasso.with(contex).load("http://openweathermap.org/img/w/"+idStatus+".png").into(mImageStatus);
        }

        public String getDay(int time){

            long timestamp = Long.parseLong(String.valueOf(time)) * 1000L;
            Date convertedDate = new Date(timestamp);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(convertedDate);
            String[] days = new String[] { "Dimanche", "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi" };

            return days[convertedDate.getDay()]+"";
        }




    }
}