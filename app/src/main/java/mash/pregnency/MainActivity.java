package mash.pregnency;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity {
    private Context context;
    SessionManager manager;
    Button signin,signup;
    String email,mobno;
    EditText emaill,mobnoo;
    String eemail="",mmobno="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        manager = new SessionManager(context);
        InitUI();
    }
    private void InitUI(){
        signin = (Button) findViewById(R.id.signin);
        signup = (Button) findViewById(R.id.signup);
        emaill = (EditText) findViewById(R.id.email);
        emaill.setSingleLine(true);
        mobnoo = (EditText) findViewById(R.id.mobno);
        mobnoo.setSingleLine(true);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/pregnancy/";

                String filename = dir+"signup"+".csv";
                //String filename="signup";
                StringBuffer stringBuffer = new StringBuffer();
                String aDataRow = "",week="",day="";
                String aBuffer = "";

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
                    eemail=e123[2];
                    mmobno=e123[1];
                    myReader.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                email = emaill.getText().toString();
                mobno = mobnoo.getText().toString();
                //Toast.makeText(context, "Mobile and Password"+email+mobno, Toast.LENGTH_LONG).show();
                if((email.contains(eemail)|| email.equals(eemail)) && (mobno.contains(mmobno) || mobno.equals(mmobno)))
                {
                    manager.setPreferences(context, CommonVariables.SHAREFPREFS_KEY_LoginStatus, CommonVariables.SHAREFPREFS_VALUE_LoggedIn);
                    Intent i = new Intent(MainActivity.this, Homeactivity.class);
                    //notifyPreg();
                    startActivity(i);
                }

            }});
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Signup.class);
                startActivity(i);
            }
        });
    }
    private static String getValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
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

        Notification.Builder builder = new Notification.Builder(MainActivity.this);

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
}
