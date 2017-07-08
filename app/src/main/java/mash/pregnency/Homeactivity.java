package mash.pregnency;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class Homeactivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Context context;
    SessionManager manager;
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    TextView txt1;
    int[] wee=new int[3];
    int[] dayy=new int[3];
    String[] confirm= new String[4];
    String lmpp="",current="";
    String value="", dec="",sym="",belly="";
    long currdays=0;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeactivity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_homeacitivity);
        setSupportActionBar(toolbar);
        context = Homeactivity.this;
        manager = new SessionManager(context);
        setTitle("Home");
        Init();
        notifyPreg();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_homeacitivity);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_homeacitivity);
        navigationView.setNavigationItemSelectedListener(this);
    }
    private void Init()
    {
        txt1=(TextView)findViewById(R.id.txt1);

        currdays=FileHandling.getCurrentDays();

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        current = df.format(c.getTime());
//"Today date is "+current+"\n"+
        txt1.setText("Your baby is: "+(currdays/7)+" Weeks "+(currdays%7)+" Days  old!");

        try {

            InputStream is = getAssets().open("file.xml");
            value=""+((currdays/7)+1);
            dec=FileHandling.getCurrentDesc(is, value);
            sym=FileHandling.getCurrentSymp();
            belly=FileHandling.getCurrentBelly();
        }

        catch (Exception e) {
            e.printStackTrace();
        }
        //      Tabbed View
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        viewPager = (ViewPager) findViewById(R.id.viewpager1);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs1);
        tabLayout.setupWithViewPager(viewPager);
//       tabbed view end
    }

    private void notifyPreg()
    {
        int wee=1, dayy=1;
        long currdays=0;
        String confirmm="";
        String current="";
        String greet="";
        //Logic starts

        // Start of Week identification

        String dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/pregnancy/";

        String filename = dir+"signup"+".csv";

        StringBuffer stringBuffer = new StringBuffer();
        String aDataRow = "",week="",day="";
        String aBuffer = "";
        String[] e123=new String[10];

        try {
            File myFile = new File(filename);
            FileInputStream fIn = new FileInputStream(myFile);
            BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));

            while ((aDataRow = myReader.readLine()) != null) {
                aBuffer += aDataRow + "\n";
                e123=aDataRow.split(",");
            }
            confirmm=e123[8];
            myReader.close();

        } catch (IOException e) {
            notify(wee,"Signup file",e.getMessage());
        }

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        current = df.format(c.getTime());

        try {
            Date date1 = df.parse(confirmm);
            Date date2 = df.parse(current);
            long diff = date2.getTime() - date1.getTime();
            currdays=FileHandling.getCurrentDays();
            //currdays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) +  wee*7 + dayy;
            String tem=""+currdays;

            Date dt = new Date();
            int hours = dt.getHours();
            int min = dt.getMinutes();

            if(hours>=0 && hours<=12){
                //  Toast.makeText(this, "Good Morning", Toast.LENGTH_SHORT).show();
                notify(0,"Good Morning ","You are at "+((currdays/7)+1)+" week");
            }
            else if(hours>=12 && hours<=16)
            {
                //    Toast.makeText(this, "Good Afternoon", Toast.LENGTH_SHORT).show();
                //greet="Good Afternoon";
                notify(0,"Good Afternoon ","You are at "+((currdays/7)+1)+" week");
            }
            else if(hours>=16 && hours<=21)
            {
                //Toast.makeText(this, "Good Evening", Toast.LENGTH_SHORT).show();
                //greet="Good Evening";
                notify(0,"Good Evening ","You are at "+((currdays/7)+1)+" week");
            }
            else if(hours>=21 && hours<=24)
            {
                //Toast.makeText(this, "Good Night", Toast.LENGTH_SHORT).show();
                //greet="Good Night";
                notify(0,"Good Night "+hours,"You are at "+((currdays/7)+1)+" week");
            }

        } catch (ParseException e) {    notify(wee,"Greet parse date",e.getMessage());        }
        // End of Week identification 'wee' for week

        //Start Greet and Check List

        try {
            InputStream is = getAssets().open("file.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(is);

            String cl0="";

            Element element=doc.getDocumentElement();
            element.normalize();

            NodeList nList = doc.getElementsByTagName("week"+((currdays/7)+1));

            for (int i=0; i<nList.getLength(); i++) {

                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element2 = (Element) node;
                    cl0=getValue("checklist", element2);
                    //  Toast.makeText(MainActivity.this,"cl0"+cl0,Toast.LENGTH_LONG).show();
                    //      notify(wee,greet + " CheckList",cl0);
                }
            }


            String[] words=cl0.split(",");//splits the string based on whitespace
            // using java foreach loop to print elements of string array
            for(String w:words) {
                //System.out.println(w);
                notify(wee,"Checklist",w);
                //Toast.makeText(MainActivity.this,w,Toast.LENGTH_LONG).show();
                wee++;
            }

        } catch (Exception e) {notify(wee,"Checklist",e.getMessage());}
        //End of Greet and Check List

        // Start of Appointment
        String appdt1="";

        // Retrive Next Checkup date

        dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/pregnancy/";

        filename = dir + "checkup" + ".csv";
        stringBuffer = new StringBuffer();
        aDataRow = "";

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
                            break;
                        case 1:

                            break;
                        case 2:

                            break;
                        case 3:

                            break;
                        case 4:

                            break;
                        case 5:

                            appdt1=w;
                            // Toast.makeText(MainActivity.this,w,Toast.LENGTH_LONG).show();
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
            //return (e.getMessage().toString());
        }


        // End of retrival

        // Check current date fun
        try {
            //appdt1="07-07-2017";
            Date date3 = df.parse(appdt1);
            Date date4 = df.parse(current);
            long diff = date3.getTime() - date4.getTime();
            currdays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
//                Toast.makeText(MainActivity.this,currdays+"=="+appdt1,Toast.LENGTH_LONG).show();
            notify(wee,"Doctor Checkup Reminder","You have "+currdays+" days for Next Checkup");
            if(currdays==0)
            {
                notify(wee,"Doctor Checkup Reminder"," on Today");
//                    Toast.makeText(MainActivity.this,"Doctor Checkup Reminder"+" on Today",Toast.LENGTH_LONG).show();
            }
            else if(currdays==1)
            {
                notify(wee,"Doctor Checkup Reminder"," on Tomorrow");

            }
        } catch (ParseException e) {
            //notify(wee,"CheckDate",e.getMessage()+"**"+appdt1);
        }


        // End of Appointment
        //Logic ends

    }

    public void notify(int i, String title, String msg) // Notification setting
    {


        NotificationManager notifManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification note = new Notification(R.drawable.logo_pragnancy, "New E-mail", System.currentTimeMillis());

        PendingIntent intent = PendingIntent.getActivity(this, 0, new Intent(this, Homeactivity.class), 0);

        //note.setLatestEventInfo(this, "New E-mail", "You have one unread message.", intent);

        Notification.Builder builder = new Notification.Builder(Homeactivity.this);

        builder.setAutoCancel(false);
        builder.setTicker(title);
        builder.setContentTitle(title);
        builder.setContentText(msg);
        //builder.setStyle(new NotificationCompat.MediaStyle());
        builder.setSmallIcon(R.drawable.logo_pragnancy);
        //builder.setSmallIcon(R.drawable.pragnat);
        builder.setContentIntent(intent);
        builder.setOngoing(true);
        builder.setAutoCancel(true);
        //  builder.setSubText("This is subtext...");   //API level 16
        builder.setNumber(i);
        //builder.build();
        note = builder.getNotification();
        notifManager.notify(i, note);
    }



    //TabbedView Declaration
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            Intent i =new Intent(Tabbs.this,Stopwatch.class);
            startActivity(i);
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }*/
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new OneFragment(dec), "Description");
        adapter.addFragment(new TwoFragment(sym), "Symptoms");
        adapter.addFragment(new ThreeFragment(belly), "Belly");
        //Toast.makeText(context, "belly" + bellyy, Toast.LENGTH_LONG).show();
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


        /*expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        // Listview Group click listener
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();

            }
        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(
                        getApplicationContext(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        });*/


    /*
     * Preparing the list data
     */
   /* private void prepareListData() {

        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        List<String> top250 = new ArrayList<String>();
        List<String> nowShowing = new ArrayList<String>();
        List<String> comingSoon = new ArrayList<String>();
        try {
            InputStream is = getAssets().open("file.xml");

            value=""+((currdays/7)+1);

            dec=FileHandling.getCurrentDesc(is, value);
            sym=FileHandling.getCurrentSymp();
            belly=FileHandling.getCurrentBelly();

            top250.add(dec);
            nowShowing.add(sym);
            comingSoon.add(belly);

        } catch (Exception e) {e.printStackTrace();}






        // Adding child data
        listDataHeader.add("Description");
        listDataHeader.add("Symptoms");
        listDataHeader.add("Belly");

        // Adding child data
        *//*List<String> top250 = new ArrayList<String>();
        top250.add("The Shawshank Redemption");
        top250.add("The Godfather");
        top250.add("The Godfather: Part II");
        top250.add("Pulp Fiction");
        top250.add("The Good, the Bad and the Ugly");
        top250.add("The Dark Knight");
        top250.add("12 Angry Men");

        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("The Conjuring");
        nowShowing.add("Despicable Me 2");
        nowShowing.add("Turbo");
        nowShowing.add("Grown Ups 2");
        nowShowing.add("Red 2");
        nowShowing.add("The Wolverine");

        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("2 Guns");
        comingSoon.add("The Smurfs 2");
        comingSoon.add("The Spectacular Now");
        comingSoon.add("The Canyons");
        comingSoon.add("Europa Report");*//*

        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
        listDataChild.put(listDataHeader.get(1), nowShowing);
        listDataChild.put(listDataHeader.get(2), comingSoon);
    }*/

    private static String getValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_homeacitivity);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.homeactivity, menu);
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

        if (id == R.id.nav_homeacitivity_home) {

            Intent i = new Intent(Homeactivity.this, Home.class);
            startActivity(i);
            finish();
        }else if (id == R.id.nav_homeacitivity_details) {

            Intent i = new Intent(Homeactivity.this, Homeactivity.class);
            startActivity(i);
            finish();
        }
        else if (id == R.id.nav_homeacitivity_checkup) {

            Intent i = new Intent(Homeactivity.this, Checkup.class);
            startActivity(i);
            finish();
        }
        else if (id == R.id.nav_homeacitivity_viewhistory) {

            Intent i = new Intent(Homeactivity.this, Viewhistory.class);
            startActivity(i);
            finish();
        }
        else if (id == R.id.nav_homeacitivity_profilereg) {

            Intent i = new Intent(Homeactivity.this, Profilereg.class);
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
        else if (id == R.id.nav_homeacitivity_logout) {
            manager.setPreferences(context, CommonVariables.SHAREFPREFS_KEY_LoginStatus,"0");
            Toast.makeText(Homeactivity.this, "Logout Success", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(Homeactivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_homeacitivity);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
