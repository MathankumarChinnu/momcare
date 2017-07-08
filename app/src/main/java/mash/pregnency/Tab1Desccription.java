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
public class Tab1Desccription extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    public Tab1Desccription() {
    }
    public static Tab1Desccription newInstance(int sectionNumber) {
        Tab1Desccription fragment = new Tab1Desccription();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1description, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.desctab1);
        textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
        //textView.setText(getArguments().getInt(ARG_SECTION_NUMBER));
        return rootView;
    }
}
