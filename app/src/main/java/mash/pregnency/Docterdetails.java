package mash.pregnency;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Docterdetails extends AppCompatActivity {
    Button d_submit,d_read;
    EditText e1,e2,e3,e4,e5,e6,e7;
    String ee1,ee2,ee3,ee4,ee5,ee6,ee7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docterdetails);
        InitUI();
    }
    private void InitUI(){
        d_submit = (Button) findViewById(R.id.d_submit);
        d_read = (Button) findViewById(R.id.d_read);
        e1=(EditText)findViewById(R.id.doctername);
        e2=(EditText)findViewById(R.id.dcity);
        e3=(EditText)findViewById(R.id.hospitalno);
        e4=(EditText)findViewById(R.id.dmob);
        //e5=(EditText)findViewById(R.id.height);
        //e6=(EditText)findViewById(R.id.city);
        //e7=(EditText)findViewById(R.id.country);
        d_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ee1 = e1.getText().toString();
                ee2 = e2.getText().toString();
                ee3 = e3.getText().toString();
                ee4 = e4.getText().toString();
                //ee5 = e5.getText().toString();
                //ee6 = e6.getText().toString();
                //ee7 = e7.getText().toString();
                //logpass = e8.getText().toString();


                String filename="docterdetails";
                FileOutputStream fos;
                try {
                    File myFile = new File("/sdcard/"+filename);
                    myFile.createNewFile();
                    FileOutputStream fOut = new

                            FileOutputStream(myFile);
                    OutputStreamWriter myOutWriter = new

                            OutputStreamWriter(fOut);
                    myOutWriter.append(ee1+","+ee2+","+ee3+","+ee4);
                    myOutWriter.close();
                    fOut.close();

                    Toast.makeText(getApplicationContext(),filename + "saved",Toast.LENGTH_LONG).show();


                } catch (FileNotFoundException e) {e.printStackTrace();}
                catch (IOException e) {e.printStackTrace();}
               Intent i = new Intent(Docterdetails.this, MainActivity.class);
                startActivity(i);
            }
        });
        d_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filename="docterdetails";
                StringBuffer stringBuffer = new StringBuffer();
                String aDataRow = "",temp="";
                String aBuffer = "";

                String[] e12=new String[10];

                try {
                    File myFile = new File("/sdcard/"+filename);
                    FileInputStream fIn = new FileInputStream(myFile);
                    BufferedReader myReader = new BufferedReader(
                            new InputStreamReader(fIn));

                    while ((aDataRow = myReader.readLine()) != null) {


                        aBuffer += aDataRow + "\n";
                        e12=aDataRow.split(",");

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
                    e1.setText(e12[0]);
                    e2.setText(e12[1]);
                    e3.setText(e12[2]);
                    e4.setText(e12[3]);
                    myReader.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                //Toast.makeText(getApplicationContext(),aBuffer, Toast.LENGTH_LONG).show();
            }
        });
    }
}
