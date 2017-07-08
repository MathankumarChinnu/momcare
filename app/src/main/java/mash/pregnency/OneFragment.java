package mash.pregnency;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

/**
 * Created by MASH001 on 7/6/2017.
 */

public class OneFragment  extends Fragment {
    String a;
    TextView txt;
    public OneFragment(String a) {
        this.a=a;
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_one, container, false);
        /*txt=(TextView)rootView.findViewById(R.id.text1);
        txt.setText(a);*/
        String msg =  "<html><body><p align='justify'>" +a+"</p></body></html>";
        WebView webView =(WebView)rootView.findViewById(R.id.webView1);
        webView.loadDataWithBaseURL(null, msg, "text/html", "utf-8", null);
        return rootView;
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_one, container, false);
    }

}
