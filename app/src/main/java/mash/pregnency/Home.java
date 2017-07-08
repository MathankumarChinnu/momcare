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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Element;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Context context;
    SessionManager manager;
    TextView tv1;
    EditText enterweek;
    String enterweeek;
    Button enter,ente;
    private int mYear, mMonth, mDay, mHour, mMinute;
    String SelectedStartDate = "", SelectedStartTime = "";
    String CurrentDate="";
    String FinalDate="";
    String dayDifference,spinnerr="",checklistt="",addcheck="";
    String[] check;
    Date date1;
    Date date2;
    Spinner spinn;
    Element element2;
    CalendarView  calendar;
    GridView gridView;
    static final String[] numbers = new String[] {
            "01", "02", "03", "04", "05",
            "06", "07", "08", "09", "10",
            "11", "12", "13", "14", "15",
            "16", "17", "18", "19", "20",
            "21", "22", "23", "24", "25", "26",
            "27", "28", "29", "30", "31","32",
            "33", "34", "35", "36", "37","38", "39", "40", "41", "42",};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_home);
        setSupportActionBar(toolbar);
        context = Home.this;
        manager = new SessionManager(context);
        setTitle("Calendar View");
        Init();
        //initializeCalendar();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_home);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_home);
        navigationView.setNavigationItemSelectedListener(this);
    }
    private void Init() {
        //tv1 = (TextView) findViewById(R.id.textView1);
        //enterweek = (EditText) findViewById(R.id.enterweek);
        //enter = (Button) findViewById(R.id.enter);
        //enter.setOnClickListener(this);
        //ente = (Button) findViewById(R.id.ente);
        //ente.setOnClickListener(this);
        //enterweek.setOnClickListener(this);
        check = new String[10];
        final String dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/pregnancy/";

        String filename = dir+"signup"+".csv";
        //String filename="signup";
        StringBuffer stringBuffer = new StringBuffer();
        String aDataRow = "",week="",day="";
        String aBuffer = "";
        int[] wee=new int[3];
        int[] dayy=new int[3];
        String[] e123=new String[10];

        try {
            File myFile = new File(filename);
            FileInputStream fIn = new FileInputStream(myFile);
            BufferedReader myReader = new BufferedReader(
                    new InputStreamReader(fIn));

            while ((aDataRow = myReader.readLine()) != null) {


                aBuffer += aDataRow + "\n";
                e123=aDataRow.split(",");

            }
                    /*for( int t=0;t< e12.length;t++)
                    {
                        Toast.makeText(getApplicationContext(),"for"+e1[t], Toast.LENGTH_LONG).show();
                    }*/
                    /*for (int j=0;j<aDataRow.length();j++)
                    {
                        e1[j]= String.valueOf(aDataRow.split(","));
                        Toast.makeText(getApplicationContext(),"while"+e1[j], Toast.LENGTH_LONG).show();
                    }
*/
            //wee[0]=Integer.parseInt(e123[9]);
           // dayy[0]=Integer.parseInt(e123[10]);
            myReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        //Toast.makeText(getApplicationContext(),wee[0]+"="+dayy[0], Toast.LENGTH_LONG).show();
        /*if(dayy[0]==0) {
            numbers[wee[0]-1] += "";
            numbers[wee[0]-1] += "&";
        }else
        {
            numbers[wee[0]] += "";
            numbers[wee[0]] += "&";
        }*/
        //spinn = (Spinner) findViewById(R.id.spinne);
        /*gridView = (GridView) findViewById(R.id.gridView1);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, numbers);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                String a= (String) parent.getItemAtPosition(position);
                String tem[]= a.split("&");
                v.setBackgroundColor(getResources().getColor(R.color.green));
                //Toast.makeText(Home.this,a,Toast.LENGTH_SHORT).show();
                *//*Toast.makeText(getApplicationContext(),
                        ((TextView) v).getText(), Toast.LENGTH_SHORT).show();*//*
                Intent newActivity1 = new Intent(Home.this, Detaill.class);
                newActivity1.putExtra("week", tem[0]);
                startActivity(newActivity1);
            }
        });*/
        gridView = (GridView) findViewById(R.id.gridView1);

        gridView.setAdapter(new DateAdpterr(this, numbers));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                /*Toast.makeText(getApplicationContext(),
                        ((TextView) v.findViewById(R.id.week))
                                .getText(), Toast.LENGTH_SHORT).show();*/
                //v.setBackgroundColor(getResources().getColor(R.color.green));
                Intent newActivity1 = new Intent(Home.this, Detaill.class);
                newActivity1.putExtra("week",((TextView) v.findViewById(R.id.week)).getText());
                startActivity(newActivity1);

            }

        });

    }

   /* public void initializeCalendar() {
        calendar = (CalendarView) findViewById(R.id.calender1);
        // sets whether to show the week number.
        calendar.setShowWeekNumber(false);
        // sets the first day of week according to Calendar.
        // here we set Monday as the first day of the Calendar
        calendar.setFirstDayOfWeek(2);
        //The background color for the selected week.
        calendar.setSelectedWeekBackgroundColor(getResources().getColor(R.color.green));
        //sets the color for the dates of an unfocused month.
        calendar.setUnfocusedMonthDateColor(getResources().getColor(R.color.transparent));
        //sets the color for the separator line between weeks.
        calendar.setWeekSeparatorLineColor(getResources().getColor(R.color.transparent));
        //sets the color for the vertical bar shown at the beginning and at the end of the selected date.
        calendar.setSelectedDateVerticalBar(R.color.darkgreen);
        //sets the listener to be notified upon selected date change.
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
        //show the selected date as a toas
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int day) {
                Toast.makeText(getApplicationContext(), day + "/" + month + "/" + year, Toast.LENGTH_LONG).show();
            }
        });
    }*/

    /*private static String getValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }
    @Override
    public void onClick(View v) {
        if (v == enterweek) {

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


                            CurrentDate = (monthOfYear + 1) + "/" + dayOfMonth+"/"+year;
                            Toast.makeText(context, "CurrentDate : " + CurrentDate, Toast.LENGTH_LONG).show();
                            enterweek.setText(CurrentDate);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }

        if (v == enter) {

            //spinnerr = spinn.getSelectedItem().toString();
            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            FinalDate = (mMonth + 1) + "/" + mDay + "/" + mYear;
            Toast.makeText(context, "FinalDate" + FinalDate, Toast.LENGTH_LONG).show();
            enter.setText(FinalDate);
        }
        if (v == ente)
        {
            try {


                final SimpleDateFormat dates = new SimpleDateFormat("MM/dd/yyyy");

                date1 = dates.parse(CurrentDate);
                date2 = dates.parse(FinalDate);

                //Comparing dates
                long difference = Math.abs(date1.getTime() - date2.getTime());
                long differenceDatess = difference / (24 * 60 * 60 * 1000);
                float differenceDates = difference / (24 * 60 * 60 * 1000);
                float diff = differenceDates / 7;
                long difff = differenceDatess / 7;
                //Convert long to String
                dayDifference = Float.toString(differenceDates);


                Toast.makeText(context, "dayDifference" + dayDifference, Toast.LENGTH_LONG).show();
                Toast.makeText(context, "divideWeek  " + diff, Toast.LENGTH_LONG).show();
                Toast.makeText(context, "divideWeeklong  " + difff, Toast.LENGTH_LONG).show();
                //Toast.makeText(context, "spinnerr" +spinnerr, Toast.LENGTH_LONG).show();
                //enterweeek = enterweek.getText().toString();
                //Toast.makeText(context, "Enterweeek" + enterweeek, Toast.LENGTH_LONG).show();
                //spin();
                if(difff<=diff) {

                    tv1.setText("");
                    //Toast.makeText(context, "Source and Destination" +sourcee+dest, Toast.LENGTH_LONG).show();
                    //auto_sourcepoint.setText(dest);

                    InputStream is = getAssets().open("file.xml");
                    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                    Document doc = dBuilder.parse(is);

                    Element element = doc.getDocumentElement();
                    element.normalize();
                    NodeList nList = null;
                    if(difff == diff) {
                        nList = doc.getElementsByTagName("week" + difff );
                    }else if(difff<diff) {
                        nList = doc.getElementsByTagName("week" + (difff + 1));
                    }
                    //Toast.makeText(context, "nList" +nList, Toast.LENGTH_LONG).show();
                    for (int i = 0; i < nList.getLength(); i++) {

                        Node node = nList.item(i);
                        //Toast.makeText(context, "node" +node, Toast.LENGTH_LONG).show();
                        if (node.getNodeType() == Node.ELEMENT_NODE) {
                            element2 = (Element) node;
                            //Toast.makeText(context, "element2" +element2, Toast.LENGTH_LONG).show();

                            //Toast.makeText(context, "element2" +element2, Toast.LENGTH_LONG).show();
                            //tv1.setText(tv1.getText() + "\nDescription : " + getValue(spinnerr, element2) + "\n");
                            //tv1.setText(tv1.getText() + "\nSymptoms : " + getValue(spinnerr, element2) + "\n");
                            //tv1.setText(tv1.getText() + "\nBelly : " + getValue(spinnerr, element2) + "\n");
                            //tv1.setText(tv1.getText() + "\nChecklist : " + getValue(spinnerr, element2) + "\n");
                            //tv1.setText(tv1.getText() + "\n-----------------------");
                            checklistt = getValue("checklist",element2);
                            check = checklistt.split(",");
                            Toast.makeText(context, "checklist" +checklistt, Toast.LENGTH_LONG).show();
                            Toast.makeText(context, "checklength" + check.length, Toast.LENGTH_LONG).show();
                            *//*for(int j=0;j<10;j++) {
                                Toast.makeText(context, "check" + check[j], Toast.LENGTH_LONG).show();
                            }*//*
                            addNotification(check);

                            //displayNotification();
                        }
                    }
                }

            }catch (Exception e) {
                e.printStackTrace();
            }
        }
       *//* if (v == spinner)
        {
            spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    spinnerr = parent.getItemAtPosition(position).toString();
                    Toast.makeText(context, "spinnerr" +spinnerr, Toast.LENGTH_LONG).show();

                }
            });
        }*//*
    }
    private void addNotification(String[] checkk) {
        for(int k=0;k<check.length;k++)
        {
            addcheck = check[k];

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.pragnant1)
                        .setContentTitle("CheckList")
                        .setContentText(addcheck);

        Intent notificationIntent = new Intent(this, Home.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, k, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);
        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(k, builder.build());
    }}
    protected void displayNotification() {
        Log.i("Start", "notification");
        int numMessages=0;
   *//* Invoking the default notification service *//*
        NotificationCompat.Builder  mBuilder = new NotificationCompat.Builder(this);

        mBuilder.setContentTitle("New Message");
        mBuilder.setContentText("You've received new message.");
        mBuilder.setTicker("New Message Alert!");
        mBuilder.setSmallIcon(R.drawable.pragnant1);

   *//* Increase notification number every time a new notification arrives *//*
        mBuilder.setNumber(++numMessages);

   *//* Add Big View Specific Configuration *//*
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();

        String[] events = new String[6];
        for(int k=0;k<check.length;k++)
        {
            events[k] = new String(check[k]);
        }
       *//* events[0] = new String(check[0]);
        events[1] = new String(check[1]);
        events[2] = new String(check[2]);
        events[3] = new String(check[3]);
        events[4] = new String("This is 5th line...");
        events[5] = new String("This is 6th line...");*//*

        // Sets a title for the Inbox style big view
        inboxStyle.setBigContentTitle("CheckList:");

        // Moves events into the big view
        for (int i=0; i < events.length; i++) {
            inboxStyle.addLine(events[i]);
        }

        mBuilder.setStyle(inboxStyle);

   *//* Creates an explicit intent for an Activity in your app *//*
        Intent resultIntent = new Intent(this, Home.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(Home.class);

   *//* Adds the Intent that starts the Activity to the top of the stack *//*
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

   *//* notificationID allows you to update the notification later on. *//*
        mNotificationManager.notify(0, mBuilder.build());
    }*/
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_home);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
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

        if (id == R.id.nav_home_home) {

            Intent i = new Intent(Home.this, Home.class);
            startActivity(i);
            finish();
        }else if (id == R.id.nav_home_details) {

            Intent i = new Intent(Home.this, Homeactivity.class);
            startActivity(i);
            finish();
        }
        else if (id == R.id.nav_home_checkup) {

            Intent i = new Intent(Home.this, Checkup.class);
            startActivity(i);
            finish();
        }
        else if (id == R.id.nav_home_viewhistory) {

            Intent i = new Intent(Home.this, Viewhistory.class);
            startActivity(i);
            finish();
        }
        else if (id == R.id.nav_home_profilereg) {

            Intent i = new Intent(Home.this, Profilereg.class);
            startActivity(i);
            finish();
        }
        /*else if (id == R.id.nav_home_pragnancydetails) {

            Intent i = new Intent(Home.this, Pragnencydetails.class);
            startActivity(i);
            finish();
        }
        else if (id == R.id.nav_home_docterdetails) {

            Intent i = new Intent(Home.this, Docterdetails.class);
            startActivity(i);
            finish();
        }*/
        else if (id == R.id.nav_home_logout) {
            manager.setPreferences(context, CommonVariables.SHAREFPREFS_KEY_LoginStatus,"0");
            Toast.makeText(Home.this, "Logout Success", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(Home.this, MainActivity.class);
            startActivity(i);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_home);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
