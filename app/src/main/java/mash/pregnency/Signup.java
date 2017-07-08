package mash.pregnency;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Calendar;

public class Signup extends AppCompatActivity implements View.OnClickListener{
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        context = Signup.this;
        manager = new SessionManager(context);
        InitUI();
    }
    private void InitUI(){

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
        /*final String dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/pregnancy/";
        //Toast.makeText(getApplicationContext(),dir + "dir",Toast.LENGTH_LONG).show();
        File newdir = new File(dir);
        //Toast.makeText(getApplicationContext(),newdir + "newdir",Toast.LENGTH_LONG).show();
        newdir.mkdirs();*/
        l1.setVisibility(View.VISIBLE);
        l2.setVisibility(View.GONE);
        l3.setVisibility(View.GONE);
        l5.setVisibility(View.GONE);

        f1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                    Intent i = new Intent(Signup.this, Homeactivity.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(context,"Please enter Missed Fields",Toast.LENGTH_LONG).show();
                }

            }
        });
        /*regread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                *//*try {
                    e1.setText("");
                    e2.setText("");
                    e3.setText("");
                    e4.setText("");
                    e5.setText("");
                    e6.setText("");
                    e7.setText("");
                    //Toast.makeText(context, "Source and Destination" +sourcee+dest, Toast.LENGTH_LONG).show();
                    //auto_sourcepoint.setText(dest);

                    InputStream is = getAssets().open("profile.xml");
                    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                    Document doc = dBuilder.parse(is);

                    Element element = doc.getDocumentElement();
                    element.normalize();
                    NodeList nList = null;
           *//**//* if (difff == diff) {
                nList = doc.getElementsByTagName("week" + difff);
            } else if (difff < diff) {
                nList = doc.getElementsByTagName("week" + (difff + 1));
            }*//**//*
                    nList = doc.getElementsByTagName("profile1");
                    //Toast.makeText(context, "nList" + nList, Toast.LENGTH_LONG).show();
                    for (int i = 0; i < nList.getLength(); i++) {

                        Node node = nList.item(i);
                        //Toast.makeText(context, "node" + node, Toast.LENGTH_LONG).show();
                        if (node.getNodeType() == Node.ELEMENT_NODE) {
                            element2 = (Element) node;
                            //Toast.makeText(context, "element2" + element2, Toast.LENGTH_LONG).show();

                            //Toast.makeText(context, "element2" +element2, Toast.LENGTH_LONG).show();
                            e1.setText(getValue("name", element2));
                            e2.setText(getValue("mobno", element2));
                            e3.setText(getValue("email", element2));
                            e4.setText(getValue("dob", element2));
                            e5.setText(getValue("height", element2));
                            e6.setText(getValue("city", element2));
                            e7.setText(getValue("country", element2));
                            //tv1.setText(tv1.getText() + "\n-----------------------");
                        }
                    }
                }

                catch (Exception e) {
                    e.printStackTrace();
                }
            }*//*
                final String dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/pregnancy/";
                String filename = dir+"signup"+".csv";
                StringBuffer stringBuffer = new StringBuffer();
                String aDataRow = "",temp="";
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
                    e1.setText(e123[0]);
                    e2.setText(e123[1]);
                    e3.setText(e123[2]);
                    e4.setText(e123[3]);
                    e5.setText(e123[5]);
                    e6.setText(e123[6]);
                    e7.setText(e123[7]);
                    e8.setText(e123[9]);
                    e9.setText(e123[12]);
                    e10.setText(e123[13]);
                    e11.setText(e123[14]);
                    e12.setText(e123[15]);

                    myReader.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(),aBuffer, Toast.LENGTH_LONG).show();
            }
        });*/

    }

    int minteger = 0;


    public void increaseInteger(View view) {
        minteger = minteger + 1;
        display(minteger);

    }public void decreaseInteger(View view) {
        minteger = minteger - 1;
        display(minteger);
    }

    private void display(int number) {
       /* TextView displayInteger = (TextView) findViewById(
                R.id.integer_number);*/
        t3.setText("" + number);
    }

    private static String getValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
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
