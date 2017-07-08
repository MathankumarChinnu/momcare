package mash.pregnency;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Pragnencydetails extends AppCompatActivity {
    Button pdsubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pragnencydetails);
        InitUI();
    }
    private void InitUI(){
        pdsubmit = (Button) findViewById(R.id.p_submit);
        pdsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Pragnencydetails.this, Docterdetails.class);
                startActivity(i);
            }
        });
    }
}
