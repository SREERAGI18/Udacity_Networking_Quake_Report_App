package com.example.android.quakereport;

import android.content.Context;

import android.graphics.drawable.GradientDrawable;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.List;

public class CustomDataAdaptor extends ArrayAdapter<Earthquake> {

    List<Earthquake> data;
    public CustomDataAdaptor(@NonNull EarthquakeActivity context, int resource, @NonNull List<Earthquake> objects) {
        super(context, resource, objects);
        this.data = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;

        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.quake_layout, parent, false);
        }

        final Earthquake currData = data.get(position);

        TextView mag = (TextView) listItemView.findViewById(R.id.magnitude);
        mag.setText(currData.getMagnitude());

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) mag.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currData.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        String quakeLocation = currData.getLocation();
        String a = String.valueOf(quakeLocation.charAt(0));

        String primary = "";
        String offset = "";

        if(a.matches("[0-9]")){
            int i =0;

            while(quakeLocation.charAt(i) != 'f'){
                i++;
            }

            offset = quakeLocation.substring(0, i+1);
            primary = quakeLocation.substring(i+2,quakeLocation.length());
        }else{
            offset = "Near the";
            primary = quakeLocation;
        }

        TextView location = (TextView) listItemView.findViewById(R.id.location);
        location.setText(primary);

        TextView sec = (TextView) listItemView.findViewById(R.id.offset);
        sec.setText(offset);

        TextView date = (TextView) listItemView.findViewById(R.id.date);
        date.setText(currData.getDate());

        TextView time = (TextView) listItemView.findViewById(R.id.time);
        time.setText(currData.getTime());

        return listItemView;
    }

    private int getMagnitudeColor(String magnitude) {
        double d = Double.parseDouble(magnitude);
        int magId;
        int mag = (int)d;

        switch (mag){
            case 0:

            case 1: magId = R.color.magnitude1; break;
            case 2: magId = R.color.magnitude2; break;
            case 3: magId = R.color.magnitude3; break;
            case 4: magId = R.color.magnitude4; break;
            case 5: magId = R.color.magnitude5; break;
            case 6: magId = R.color.magnitude6; break;
            case 7: magId = R.color.magnitude7; break;
            case 8: magId = R.color.magnitude8; break;
            case 9: magId = R.color.magnitude9; break;
            default: magId = R.color.magnitude10plus; break;
        }
        return ContextCompat.getColor(getContext(), magId);
    }
}
