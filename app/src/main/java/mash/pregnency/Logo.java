package mash.pregnency;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

public class Logo extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 1000;
    SessionManager manager;
    Context context;

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }

    Thread splashTread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);

        context = Logo.this;
        manager = new SessionManager(context);

        Thread background = new Thread() {
            public void run() {

                try {
                    // Thread will sleep for 3 seconds
                    sleep(20 * 100);

                    // After 5 seconds redirect to another intent
                    String status = manager.getPreferences(Logo.this, CommonVariables.SHAREFPREFS_KEY_LoginStatus);
//                    Log.d("status", status);
                    if (status.equals(CommonVariables.SHAREFPREFS_VALUE_LoggedIn)) {
                        Intent i = new Intent(Logo.this, Homeactivity.class);
                        startActivity(i);
                    }  else {
                        Intent i = new Intent(Logo.this, Signup.class);
                        startActivity(i);
                    }
                    /*Intent i = new Intent(Logo.this, MainActivity.class);
                    startActivity(i);*/

                    //Remove activity
                    Logo.this.finish();


                } catch (Exception e) {

                }
            }
        };

        // start thread
        background.start();

    }
}
