package mash.pregnency;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by MASH001 on 6/24/2017.
 */

public class Tab2Symptems extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab2symptems, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.sypmtab2);
        //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
        //textView.setText(getArguments().getInt(ARG_SECTION_NUMBER));
        return rootView;
    }
}
