package mash.pregnency;

import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Element;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Calendar;

public class Checkup extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {
    private Context context;
    SessionManager manager;
    Button checkup_submit,checkupread;
    Element element2;
    EditText e1,e2,e3,e4,e5,e6,e7;
    String ee1,ee2,ee3,ee4,ee5,ee6,ee7,spp1,spp2;
    Spinner sp1,sp2;
    private int mYear, mMonth, mDay, mHour, mMinute;
    TextView t1,t2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkup);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_checkup);
        setSupportActionBar(toolbar);
        context = Checkup.this;
        manager = new SessionManager(context);
        setTitle("Checkup Entry");
        Init();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_checkup);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_checkup);
        navigationView.setNavigationItemSelectedListener(this);
    }
    private  void Init()
    {
        checkup_submit = (Button) findViewById(R.id.checkup_submit);
        //checkupread = (Button) findViewById(R.id.checkupread);
        //e1=(EditText)findViewById(R.id.date);
        t1=(TextView)findViewById(R.id.date);
        t1.setOnClickListener(this);
        e2=(EditText)findViewById(R.id.weight);
        sp1=(Spinner)findViewById(R.id.maxbp);
        sp2=(Spinner)findViewById(R.id.minbp);
        e3=(EditText)findViewById(R.id.bellysize);
        t2=(TextView)findViewById(R.id.nextcheckdate);
        t2.setOnClickListener(this);
        e5=(EditText)findViewById(R.id.remarks);
        /*final String dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/pregnancy/";
        //Toast.makeText(getApplicationContext(),dir + "dir",Toast.LENGTH_LONG).show();
        File newdir = new File(dir);
        //Toast.makeText(getApplicationContext(),newdir + "newdir",Toast.LENGTH_LONG).show();
        newdir.mkdirs();*/

        checkup_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ee1 = t1.getText().toString();
                //Toast.makeText(getApplicationContext(),ee1 + "ee1",Toast.LENGTH_LONG).show();
                ee2 = e2.getText().toString();
                spp1=sp1.getSelectedItem().toString();
                spp2=sp2.getSelectedItem().toString();
                ee3 = e3.getText().toString();
                ee4 = t2.getText().toString();
                ee5 = e5.getText().toString();
                //ee6 = e6.getText().toString();
                //ee7 = e7.getText().toString();
                //logpass = e8.getText().toString();


                //String file = dir+count+".jpg";
                if (ee1.length()>0&&ee2.length()>0&&spp1.length()>0&&spp2.length()>0&&ee3.length()>0&&ee4.length()>0&&ee5.length()>0) {
                    final String dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/pregnancy/";
                    String filename = dir + "checkup" + ".csv";
                    //Toast.makeText(getApplicationContext(),dir + "dir",Toast.LENGTH_LONG).show();
                    //String filename1 = dir+"checkup";
                    FileOutputStream fos;
                    try {
                        //File myFile = new File("/sdcard/"+filename);
                        File myFile = new File(filename);

                        if (!myFile.exists()) {
                            myFile.createNewFile();
                        }
                        //Toast.makeText(getApplicationContext(),myFile + "myFile",Toast.LENGTH_LONG).show();
                        FileOutputStream fOut = new

                                FileOutputStream(myFile, true);
                        OutputStreamWriter myOutWriter = new

                                OutputStreamWriter(fOut);
                        myOutWriter.append(ee1 + "," + ee2 + "," + spp1 + "," + spp2 + "," + ee3 + "," + ee4 + "," + ee5 + "\n");
                        myOutWriter.close();
                        fOut.close();

                        Toast.makeText(getApplicationContext(), filename + "saved", Toast.LENGTH_LONG).show();
                        t1.setText("");
                        e2.setText("");
                        sp1.clearFocus();
                        e3.setText("");
                        t2.setText("");
                        e5.setText("");

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    Toast.makeText(context,"Please enter Missed Fields",Toast.LENGTH_LONG).show();
                }


            }
        });
        /*checkupread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                *//*e1.setText("");
                e2.setText("");
                e3.setText("");
                e4.setText("");
                e5.setText("");*//*
                //String filename="checkup";
                final String dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/pregnancy/";
                String filename = dir+"checkup"+".csv";
                StringBuffer stringBuffer = new StringBuffer();
                String aDataRow = "",aBuffer = "";
                String[] tem = new String[50];
                ArrayList<String> temp=new ArrayList<String>();

                String[] e12=new String[10];

                try {
                    //File myFile = new File("/sdcard/"+filename);
                    File myFile = new File(filename);
                    FileInputStream fIn = new FileInputStream(myFile);
                    BufferedReader myReader = new BufferedReader(
                            new InputStreamReader(fIn));

                    while ((aDataRow = myReader.readLine()) != null) {

                        Toast.makeText(getApplicationContext(),"aDataRow"+aDataRow, Toast.LENGTH_LONG).show();
                        aBuffer += aDataRow + "\n";

                        Toast.makeText(getApplicationContext(),"aBuffer"+aBuffer, Toast.LENGTH_LONG).show();

                        e12=aDataRow.split(",");

                    }
                    tem=aBuffer.split("&");
                    temp.add(aBuffer);
                    Toast.makeText(getApplicationContext(),"temp"+temp, Toast.LENGTH_LONG).show();
                    *//*for( int t=0;t< e12.length;t++)
                    {
                        Toast.makeText(getApplicationContext(),"for"+e1[t], Toast.LENGTH_LONG).show();
                    }*//*
                    *//*for (int j=0;j<aDataRow.length();j++)
                    {
                        e1[j]= String.valueOf(aDataRow.split(","));
                        Toast.makeText(getApplicationContext(),"while"+e1[j], Toast.LENGTH_LONG).show();
                    }
*//*
                    *//*e1.setText(e12[0]);
                    e2.setText(e12[1]);
                    e3.setText(e12[4]);
                    e4.setText(e12[5]);
                    e5.setText(e12[6]);*//*
                    myReader.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                //Toast.makeText(getApplicationContext(),aBuffer, Toast.LENGTH_LONG).show();
            }
        });*/
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_checkup);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.checkup, menu);
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

        if (id == R.id.nav_checkup_home) {

            Intent i = new Intent(Checkup.this, Home.class);
            startActivity(i);
            finish();
        }else if (id == R.id.nav_checkup_details) {

            Intent i = new Intent(Checkup.this, Homeactivity.class);
            startActivity(i);
            finish();
        }
        else if (id == R.id.nav_checkup_checkup) {

            Intent i = new Intent(Checkup.this, Checkup.class);
            startActivity(i);
            finish();
        }
        else if (id == R.id.nav_checkup_viewhistory) {

            Intent i = new Intent(Checkup.this, Viewhistory.class);
            startActivity(i);
            finish();
        }
        else if (id == R.id.nav_checkup_profilereg) {

            Intent i = new Intent(Checkup.this, Profilereg.class);
            startActivity(i);
            finish();
        }
        /*else if (id == R.id.nav_checkup_pragnancydetails) {

            Intent i = new Intent(Checkup.this, Pragnencydetails.class);
            startActivity(i);
            finish();
        }
        else if (id == R.id.nav_checkup_docterdetails) {

            Intent i = new Intent(Checkup.this, Docterdetails.class);
            startActivity(i);
            finish();
        }*/
        else if (id == R.id.nav_checkup_logout) {
            manager.setPreferences(context, CommonVariables.SHAREFPREFS_KEY_LoginStatus,"0");
            Toast.makeText(Checkup.this, "Logout Success", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(Checkup.this, MainActivity.class);
            startActivity(i);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_checkup);
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
