package mash.pregnency;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by MASH001 on 7/6/2017.
 */

public class DateAdpterr extends BaseAdapter {
    private Context context;
    private final String[] mobileValues;

    public DateAdpterr(Context context, String[] mobileValues) {
        this.context = context;
        this.mobileValues = mobileValues;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(context);

            // get layout from mobile.xml
            gridView = inflater.inflate(R.layout.weekdate, null);

            // set value into textview
            TextView textView = (TextView) gridView
                    .findViewById(R.id.week);
            textView.setText(mobileValues[position]);
            int days=FileHandling.getCurrentDays();
            int weekk=(days/7);
            //String wweek=""+weekk;
            if(weekk>position) {
                textView.setBackgroundColor(Color.parseColor("#bcc639"));
            }else if(weekk==position) {
                textView.setBackgroundColor(Color.parseColor("#FF1493"));
            }
            else
            {
                textView.setBackgroundColor(Color.parseColor("#FFA500"));
            }


            /*String mobile = mobileValues[position];

            if (mobile.equals("Windows")) {
//                imageView.setImageResource(R.drawable.windows_logo);
                textView.setText("8.1");
            } else if (mobile.equals("iOS")) {
//                imageView.setImageResource(R.drawable.ios_logo);
                textView.setText("13.1");
            } else if (mobile.equals("Blackberry")) {
//                imageView.setImageResource(R.drawable.blackberry_logo);
                textView.setText("18.1");
            } else {
//                imageView.setImageResource(R.drawable.android_logo);
                textView.setText("0.0");
            }*/

        } else {
            gridView = (View) convertView;
        }

        return gridView;
    }

    @Override
    public int getCount() {
        return mobileValues.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}
