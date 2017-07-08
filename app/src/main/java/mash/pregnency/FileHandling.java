package mash.pregnency;

import android.os.Environment;

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

/**
 * Created by MASH001 on 7/5/2017.
 */

public class FileHandling {

    public static String dec="",sym="",belly="";

    static int getCurrentDays()
    {
        final String dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/pregnancy/";

        String filename = dir+"signup"+".csv";
        String aDataRow = "";
        String lmpp = "",current="";
        String[] e123=new String[10];

        try {
            File myFile = new File(filename);
            FileInputStream fIn = new FileInputStream(myFile);
            BufferedReader myReader = new BufferedReader(
                    new InputStreamReader(fIn));

            while ((aDataRow = myReader.readLine()) != null) {
                e123=aDataRow.split(",");

            }

            //confirm[0]=e123[9];
            lmpp=e123[8];
            //wee[0]=Integer.parseInt(e123[9]);
            //dayy[0]=Integer.parseInt(e123[10]);
            myReader.close();

        } catch (IOException e) {
            return -1;
        }

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        current = df.format(c.getTime());

        //SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
        //String inputString1 = "23 01 1997";
        //String inputString2 = "27 04 1997";

        try {
            Date date1 = df.parse(lmpp);
            Date date2 = df.parse(current);
            long diff = date2.getTime() - date1.getTime();
            long currdays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);// +  wee[0]*7 + dayy[0];
            String tem=""+currdays;
            //Toast.makeText(this,tem,Toast.LENGTH_LONG).show();
            //System.out.println ("Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
            //txt1.setText("Today date is"+date2+"\n"+"You are now at: "+(currdays/7)+" Weeks "+(currdays%7)+" Days");
            return (int) currdays;
        } catch (ParseException e) {
            //txt1.setText(e.getMessage());
            return -1;
        }

    }

    static String getCurrentDesc(InputStream is, String value)
    {
        Element element2;
        try {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(is);

            Element element = doc.getDocumentElement();
            element.normalize();
            NodeList nList = null;

            nList = doc.getElementsByTagName("week" + value);

            for (int i = 0; i < nList.getLength(); i++) {

                Node node = nList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    element2 = (Element) node;
                    dec=getValue("description", element2);
                    sym=getValue("symptoms", element2);
                    belly=getValue("belly", element2);
                }
            }
        }

        catch (Exception e) {
            dec = e.getMessage();
        }
        return dec;
    }
    static String getCurrentSymp()
    {
        return sym;
    }
    static String getCurrentBelly()
    {
        return belly;
    }

    private static String getValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }

    static String getSelectedWeek(String week)
    {
        String lmp1="";
        final String dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/pregnancy/";

        String filename = dir+"signup"+".csv";
        String aDataRow = "";
        String msg = "";
        String[] e123=new String[10];

        try {
            File myFile = new File(filename);
            FileInputStream fIn = new FileInputStream(myFile);
            BufferedReader myReader = new BufferedReader(
                    new InputStreamReader(fIn));

            while ((aDataRow = myReader.readLine()) != null) {
                e123=aDataRow.split(",");
            }

            lmp1=e123[8]; //LMP Date
            myReader.close();

        } catch (IOException e) {
            return e.getMessage();
        }

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        //current = df.format(c.getTime());

        try {
            int week1=Integer.parseInt(week);
            Date date1 = df.parse(lmp1);

            c.setTime(date1);  // Setting lmp date
            c.add(Calendar.DATE, ((week1-1)*7)+1);
            msg=(df.format(c.getTime()))+ " to ";
            c.add(Calendar.DATE, 6);
            msg+=(df.format(c.getTime()));
        } catch (ParseException e) {
            return e.getMessage();
        }
        return msg;
    }
}
