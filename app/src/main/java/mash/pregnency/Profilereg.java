package mash.pregnency;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Element;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Calendar;

public class Profilereg extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {
    private Context context;
    SessionManager manager;
    Button regsignup,regread,color;
    Element element2;
    EditText e1,e2,e3,e4,e5,e6,e7,e8,e9,e10,e11,e12;
    String ee1,ee2,ee3,ee4,ee5,ee6,ee7,ee8,ee9,ee10,ee11,ee12;
    String spp1="",spp2="",spp3="",spp4="";
    Spinner sp1,sp2,sp3,sp4;
    private int mYear, mMonth, mDay, mHour, mMinute;
    TextView t1,t2,t3,textcolor;
    LinearLayout l1,l2,l3,l4,l5;
    FloatingActionButton f1,f2,f3;
    String[] e123=new String[10];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilereg);
        context = Profilereg.this;
        manager = new SessionManager(context);
        setTitle("Profile");
        Init();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_profilereg);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_profilereg);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_profilereg);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void Init(){

        l1=(LinearLayout) findViewById(R.id.linearlayout1);
        l2=(LinearLayout) findViewById(R.id.linearlayout2);
        l3=(LinearLayout) findViewById(R.id.linearlayout3);
        l5=(LinearLayout) findViewById(R.id.submit1);

        f1=(FloatingActionButton)findViewById(R.id.floatingActionButton);
        f2=(FloatingActionButton)findViewById(R.id.floatingActionButton1);
        f3=(FloatingActionButton)findViewById(R.id.floatingActionButton3);

        regsignup = (Button) findViewById(R.id.regsignup);
        //regread = (Button) findViewById(R.id.regread);
        //color
        /*color = (Button) findViewById(R.id.color);
        textcolor = (TextView) findViewById(R.id.textcolor);
        int defaultColorR=0,defaultColorG=0,defaultColorB=0;
        final ColorPicker cp = new ColorPicker(Signup.this, defaultColorR, defaultColorG, defaultColorB);
        cp.show();
        color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedColorRGB = cp.getColor();
                textcolor.setText(selectedColorRGB);
            }
        });*/
        //clor end
        e1=(EditText)findViewById(R.id.name);
        e2=(EditText)findViewById(R.id.mobno);
        e3=(EditText)findViewById(R.id.email);
        t1=(TextView)findViewById(R.id.dob);
        t1.setOnClickListener(this);
        sp1=(Spinner)findViewById(R.id.bg);
        e5=(EditText)findViewById(R.id.height);
        e6=(EditText)findViewById(R.id.city);
        //e7=(EditText)findViewById(R.id.country);
        //sp2=(Spinner)findViewById(R.id.attempt);
        t3=(TextView) findViewById(R.id.attempt);
        t2=(TextView)findViewById(R.id.confirmdate);
        t2.setOnClickListener(this);
        //sp3=(Spinner)findViewById(R.id.ppweeks);
        //sp4=(Spinner)findViewById(R.id.ppdays);
        e9=(EditText)findViewById(R.id.doctername);
        e10=(EditText)findViewById(R.id.dcity);
        e11=(EditText)findViewById(R.id.hospitalno);
        e12=(EditText)findViewById(R.id.dmob);


        /*
        e7.setText("");
       */
        //Read and Assign

        final String dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/pregnancy/";
        String filename = dir+"signup"+".csv";
        StringBuffer stringBuffer = new StringBuffer();
        String aDataRow = "",temp="";
        String aBuffer = "";

        try {
            File myFile = new File(filename);
            FileInputStream fIn = new FileInputStream(myFile);
            BufferedReader myReader = new BufferedReader(
                    new InputStreamReader(fIn));

            while ((aDataRow = myReader.readLine()) != null) {


                aBuffer += aDataRow + "\n";
                e123=aDataRow.split(",");

            }

            myReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        //Toast.makeText(getApplicationContext(),aBuffer, Toast.LENGTH_LONG).show();
        //Read and Assign End


        /*final String dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/pregnancy/";
        //Toast.makeText(getApplicationContext(),dir + "dir",Toast.LENGTH_LONG).show();
        File newdir = new File(dir);
        //Toast.makeText(getApplicationContext(),newdir + "newdir",Toast.LENGTH_LONG).show();
        newdir.mkdirs();*/
        l1.setVisibility(View.VISIBLE);
        l2.setVisibility(View.GONE);
        l3.setVisibility(View.GONE);
        l5.setVisibility(View.GONE);
        //assign
        e1.setText(e123[0]);
        e2.setText(e123[1]);
        e3.setText(e123[2]);
        t1.setText(e123[3]);
        e5.setText(e123[5]);
        e6.setText(e123[6]);

        f1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //read
                ee1 = e1.getText().toString();
                ee2 = e2.getText().toString();
                ee3 = e3.getText().toString();
                ee4 = t1.getText().toString();
                spp1=sp1.getSelectedItem().toString();
                ee5 = e5.getText().toString();
                ee6 = e6.getText().toString();
//ee7 = e7.getText().toString();
                if(ee1.length()>0&&ee2.length()>0&&ee3.length()>0&&ee4.length()>0&&spp1.length()>0&&ee5.length()>0&&ee6.length()>0)
                {
                    l1.setVisibility(View.GONE);
                    l2.setVisibility(View.VISIBLE);
                    l3.setVisibility(View.GONE);
                    l5.setVisibility(View.GONE);
                    //assign
                    t3.setText(e123[7]);
                    t2.setText(e123[8]);
                }
                else
                {
                    Toast.makeText(context,"Please enter Missed Fields",Toast.LENGTH_LONG).show();
                }


            }
        });
        f2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //read
                //spp2=sp2.getSelectedItem().toString();
                spp2=t3.getText().toString();
                ee8 = t2.getText().toString();
                //spp3=sp3.getSelectedItem().toString();
                //spp4=sp4.getSelectedItem().toString();
                if(spp2.length()>0&&ee8.length()>0)
                {
                    l1.setVisibility(View.GONE);
                    l2.setVisibility(View.GONE);
                    l3.setVisibility(View.VISIBLE);
                    l5.setVisibility(View.VISIBLE);
                    //assign
                    e9.setText(e123[9]);
                    e10.setText(e123[10]);
                    e11.setText(e123[11]);
                    e12.setText(e123[12]);
                }
                else
                {
                    Toast.makeText(context,"Please enter Missed Fields",Toast.LENGTH_LONG).show();
                }
            }
        });
        f3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l1.setVisibility(View.VISIBLE);
                l2.setVisibility(View.GONE);
                l3.setVisibility(View.GONE);
                l5.setVisibility(View.GONE);
            }
        });
        regsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //read

                ee9 = e9.getText().toString();
                ee10 = e10.getText().toString();
                ee11 = e11.getText().toString();
                ee12 = e12.getText().toString();
                //Toast.makeText(getApplicationContext(),spp1+spp2+spp3+spp4+ee8+ee10+ee12 + "test",Toast.LENGTH_LONG).show();
                //logpass = e8.getText().toString();
                if(ee9.length()>0&&ee10.length()>0&&ee11.length()>0&&ee12.length()>0) {
                    final String dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/pregnancy/";
                    final String filename = dir+"signup"+".csv";
                    //String filename="signup";

                    try {
                        File myFile = new File(filename);
                        if (!myFile.exists()) {
                            myFile.createNewFile();
                        }
                        FileOutputStream fOut = new

                                FileOutputStream(myFile);
                        Writer myOutWriter = new

                                OutputStreamWriter(fOut);
                        myOutWriter.append(ee1 + "," + ee2 + "," + ee3 + "," + ee4 + "," + spp1 + "," + ee5 + "," + ee6 + "," + spp2 + "," + ee8 + "," + ee9 + "," + ee10 + "," + ee11 + "," + ee12 + "\n");
                        myOutWriter.close();
                        fOut.close();

                        Toast.makeText(getApplicationContext(), filename + "saved", Toast.LENGTH_LONG).show();


                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    manager.setPreferences(context, CommonVariables.SHAREFPREFS_KEY_LoginStatus, CommonVariables.SHAREFPREFS_VALUE_LoggedIn);
                    Intent i = new Intent(Profilereg.this, Homeactivity.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(context,"Please enter Missed Fields",Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_profilereg);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profilereg, menu);
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

        if (id == R.id.nav_profilereg_home) {

            Intent i = new Intent(Profilereg.this, Home.class);
            startActivity(i);
            finish();
        }else if (id == R.id.nav_profilereg_details) {

            Intent i = new Intent(Profilereg.this, Homeactivity.class);
            startActivity(i);
            finish();
        }
        else if (id == R.id.nav_profilereg_checkup) {

            Intent i = new Intent(Profilereg.this, Checkup.class);
            startActivity(i);
            finish();
        }
        else if (id == R.id.nav_profilereg_viewhistory) {

            Intent i = new Intent(Profilereg.this, Viewhistory.class);
            startActivity(i);
            finish();
        }
        else if (id == R.id.nav_profilereg_profilereg) {

            Intent i = new Intent(Profilereg.this, Profilereg.class);
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
        else if (id == R.id.nav_profilereg_logout) {
            manager.setPreferences(context, CommonVariables.SHAREFPREFS_KEY_LoginStatus,"0");
            Toast.makeText(Profilereg.this, "Logout Success", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(Profilereg.this, MainActivity.class);
            startActivity(i);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_profilereg);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        if(v==t1)
        {
// Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            t1.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if(v==t2)
        {
            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            t2.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
    }
}
