package allunitconverter.fachati.com.weather.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import allunitconverter.fachati.com.weather.R;
import allunitconverter.fachati.com.weather.models.List;

/**
 * Created by ismailfachati on 25/03/2017.
 */
public class WeatherHoursAdapter extends RecyclerView.Adapter<WeatherHoursAdapter.ViewHolder> {

    ArrayList<List> list;
    private Context contex;
    
    public WeatherHoursAdapter(ArrayList<List> list,Context context) {
        this.list = list;
        this.contex=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int itemType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_weather_hours,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder myViewHolder, int position) {
        List myObject = list.get(position);
        myViewHolder.bind(myObject);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView mTextHours;
        private TextView mTextTmp;
        private ImageView mImageStatus;
        
        public ViewHolder(View itemView) {
            super(itemView);

            mTextHours = (TextView) itemView.findViewById(R.id.textHour);
            mTextTmp = (TextView) itemView.findViewById(R.id.textTmpHour);
            mImageStatus = (ImageView) itemView.findViewById(R.id.imageStatusHour);
        }
        
        public void bind(List weather){
            String idStatus= weather.getWeather().get(0).getIcon();
            mTextHours.setText(String.format ("%02d",getHourFromStringDate(weather.getDtText()))+"");
            mTextTmp.setText(String.format ("%02d",(int)weather.getMain().getTempMin())+"°");
            Picasso.with(contex).load("http://openweathermap.org/img/w/"+idStatus+".png").into(mImageStatus);
            }

        public int getHourFromStringDate(String date){

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date convertedDate = new Date();
            try {
                convertedDate = dateFormat.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar calendar = GregorianCalendar.getInstance();
            calendar.setTime(convertedDate);
            int hour=calendar.get(Calendar.HOUR_OF_DAY);
            return hour;
        }

    }
}