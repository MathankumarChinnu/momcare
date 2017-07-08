package mash.pregnency;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Viewhistory extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{
    private Context context;
    SessionManager manager;
    BarChart chart;
    ArrayList<BarEntry> BARENTRY;
    ArrayList<String> BarEntryLabels;
    BarDataSet Bardataset;
    BarData BARDATA;
    String[] date=new String[3];
    float[] weigh=new float[3];
    String datee="June",spinn="";
    Spinner spin;
    Button button;
    List<String> viewcdate;
    List<BarEntry> viewweight;
    List<BarEntry> viewmaxbp;
    List<BarEntry> viewminbp;
    List<BarEntry> viewbellysize;
    List<String> viewnextcdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewhistory);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_viewhistory);
        setSupportActionBar(toolbar);
        context = Viewhistory.this;
        manager = new SessionManager(context);
        setTitle("View History");
        Init();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_viewhistory);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        String msg = read();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_viewhistory);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void Init() {

        spin = (Spinner) findViewById(R.id.spin);
        button = (Button) findViewById(R.id.button);
        viewcdate = new ArrayList<String>();
        viewweight = new ArrayList<BarEntry>();
        viewmaxbp = new ArrayList<BarEntry>();
        viewminbp = new ArrayList<BarEntry>();
        viewbellysize = new ArrayList<BarEntry>();
        viewnextcdate = new ArrayList<String>();

;        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinn = spin.getSelectedItem().toString();
                //Toast.makeText(Viewhistory.this, msg, Toast.LENGTH_LONG).show();
                if (viewweight.isEmpty() && viewmaxbp.isEmpty() && viewminbp.isEmpty() && viewbellysize.isEmpty()) {
                    Toast.makeText(context, "Please fill checkup details", Toast.LENGTH_SHORT).show();
                } else {
                    if (spinn.equals("Weight")) {
                        //Init Chart
                        chart = (BarChart) findViewById(R.id.chart1);
                        // Data set
                        BarDataSet dataSet = new BarDataSet(viewweight, "Weight");
                        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                        BarData data = new BarData(viewcdate, dataSet);
                        chart.setData(data);
                        chart.setDescription("Weight");
                        chart.animateY(300);
                    } else if (spinn.equals("MaxBP")) {
                        //Init Chart
                        chart = (BarChart) findViewById(R.id.chart1);
                        // Data set
                        BarDataSet dataSet = new BarDataSet(viewmaxbp, "MaxBP");
                        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                        BarData data = new BarData(viewcdate, dataSet);
                        chart.setData(data);
                        chart.setDescription("MaxBP");
                        chart.animateY(300);
                    } else if (spinn.equals("MinBP")) {
                        //Init Chart
                        chart = (BarChart) findViewById(R.id.chart1);
                        // Data set
                        BarDataSet dataSet = new BarDataSet(viewminbp, "MinBP");
                        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                        BarData data = new BarData(viewcdate, dataSet);
                        chart.setData(data);
                        chart.setDescription("MinBP");
                        chart.animateY(300);
                    } else if (spinn.equals("BellySize")) {
                        //Init Chart
                        chart = (BarChart) findViewById(R.id.chart1);
                        // Data set
                        BarDataSet dataSet = new BarDataSet(viewbellysize, "BellySize");
                        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                        BarData data = new BarData(viewcdate, dataSet);
                        chart.setData(data);
                        chart.setDescription("BellySize");
                        chart.animateY(300);
                    }
                }
            }
        });
    }

    public String read() {
        final String dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/pregnancy/";

        String filename = dir + "checkup" + ".csv";
        StringBuffer stringBuffer = new StringBuffer();
        String aDataRow = "";

        String msg="";

        try {
            File myFile = new File(filename);
            FileInputStream fIn = new FileInputStream(myFile);
            BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));

            int index=0;
            while ((aDataRow = myReader.readLine()) != null) {
               // msg+=("*aDataRow*"+aDataRow);
                String[] words=aDataRow.split(",");//splits the string based on whitespace
                // using java foreach loop to print elements of string array
                int i=0;
                for(String w:words){
                    //System.out.println(w);
                    //msg+=(i+"-token-"+w);
                    switch (i) {
                        case 0:
                             viewcdate.add(w);
                             break;
                        case 1:
                            viewweight.add(new BarEntry(Float.parseFloat(w),index));
                            break;
                        case 2:
                            viewmaxbp.add(new BarEntry(Float.parseFloat(w),index));
                            break;
                        case 3:
                            viewminbp.add(new BarEntry(Float.parseFloat(w),index));
                            break;
                        case 4:
                            viewbellysize.add(new BarEntry(Float.parseFloat(w),index));
                            break;
                        case 5:
                            viewnextcdate.add(w);
                            break;
                        default:
                            break;
                    }
                    i++;
                }
                index++;
            }
            myReader.close();

        } catch (IOException e) {
            //Toast.makeText(Viewhistory.this, "token"+e.getMessage(), Toast.LENGTH_SHORT).show();
            return (e.getMessage().toString());
        }

        return msg+"Sucess";
    }
        /*chart = (BarChart) findViewById(R.id.chart1);

        BARENTRY = new ArrayList<>();

        BarEntryLabels = new ArrayList<String>();

        //AddValuesToBARENTRY();

        //AddValuesToBarEntryLabels();
        //Bardataset = new BarDataSet(BARENTRY, "Projects");
        if (spinn=="Weight") {
            Bardataset = new BarDataSet(viewweight, "Projects");
        }else if(spinn=="MaxBP")
        {
            Bardataset = new BarDataSet(viewmaxbp, "Projects");
        }else if(spinn=="MinBP")
        {
            Bardataset = new BarDataSet(viewminbp, "Projects");
        }else if(spinn=="BellySize")
        {
            Bardataset = new BarDataSet(viewbellysize, "Projects");
        }

        BARDATA = new BarData(viewcdate, Bardataset);

        Bardataset.setColors(ColorTemplate.COLORFUL_COLORS);

        chart.setData(BARDATA);

        chart.animateY(3000);

    }

    public void AddValuesToBARENTRY() {

        *//*BARENTRY.add(new BarEntry(2f, 0));
        BARENTRY.add(new BarEntry(4f, 1));
        BARENTRY.add(new BarEntry(6f, 2));
        BARENTRY.add(new BarEntry(8f, 3));
        BARENTRY.add(new BarEntry(7f, 4));
        BARENTRY.add(new BarEntry(3f, 5));*//*
        BARENTRY.add(new BarEntry(weigh,0));

    }

    public void AddValuesToBarEntryLabels() {

        *//*BarEntryLabels.add("January");
        BarEntryLabels.add("February");
        BarEntryLabels.add("March");
        BarEntryLabels.add("April");
        BarEntryLabels.add("May");
        BarEntryLabels.add("June");*//*
        BarEntryLabels.add(datee);
    }*/


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_viewhistory);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.viewhistory, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_viewhistory_home) {

            Intent i = new Intent(Viewhistory.this, Home.class);
            startActivity(i);
            finish();
        }else if (id == R.id.nav_viewhistory_details) {

            Intent i = new Intent(Viewhistory.this, Homeactivity.class);
            startActivity(i);
            finish();
        }
        else if (id == R.id.nav_viewhistory_checkup) {

            Intent i = new Intent(Viewhistory.this, Checkup.class);
            startActivity(i);
            finish();
        }
        else if (id == R.id.nav_viewhistory_viewhistory) {

            Intent i = new Intent(Viewhistory.this, Viewhistory.class);
            startActivity(i);
            finish();
        }
        else if (id == R.id.nav_viewhistory_profilereg) {

            Intent i = new Intent(Viewhistory.this, Profilereg.class);
            startActivity(i);
            finish();
        }
        /*else if (id == R.id.nav_viewhistory_pragnancydetails) {

            Intent i = new Intent(Viewhistory.this, Pragnencydetails.class);
            startActivity(i);
            finish();
        }
        else if (id == R.id.nav_viewhistory_docterdetails) {

            Intent i = new Intent(Viewhistory.this, Docterdetails.class);
            startActivity(i);
            finish();
        }*/
        else if (id == R.id.nav_viewhistory_logout) {
            manager.setPreferences(context, CommonVariables.SHAREFPREFS_KEY_LoginStatus,"0");
            Toast.makeText(Viewhistory.this, "Logout Success", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(Viewhistory.this, MainActivity.class);
            startActivity(i);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_viewhistory);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
