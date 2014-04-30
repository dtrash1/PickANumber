package com.zunkdrombie.utils.pickanumber;

import android.os.Bundle;
import android.app.Activity;
import android.view.*;
import android.widget.*;

import java.util.Random;

public class MainActivity extends Activity {

	Random rgen;
	int	maxRndAvail;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rgen = new Random();
        final NumberPicker np1 = (NumberPicker) findViewById(R.id.numberPicker1);
        // TODO: load maxRndAvail from app settings, then use below
        maxRndAvail = 5;
        np1.setMaxValue(maxRndAvail);
        np1.setMinValue(1);
        final TextView tv2 = (TextView) findViewById(R.id.textView2);
        tv2.setVisibility(View.INVISIBLE);
        final ProgressBar pb = (ProgressBar) findViewById(R.id.progressBar1);
        pb.setVisibility(View.VISIBLE);
        
        final Button button_pick = (Button) findViewById(R.id.button2);
        button_pick.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                final ProgressBar pb = (ProgressBar) findViewById(R.id.progressBar1);
                pb.setVisibility(View.VISIBLE);           	
            	final NumberPicker np1 = (NumberPicker) findViewById(R.id.numberPicker1);
            	int maxval = np1.getValue();
            	//TODO:  Add an AsyncTask to cause a 500ms pause
            	int r = rgen.nextInt(maxval)+1;
            	final TextView tv2 = (TextView) findViewById(R.id.textView2);
            	tv2.setText(Integer.toString(r));
            	tv2.setVisibility(View.VISIBLE);
            	pb.setVisibility(View.INVISIBLE);  
            }
        });
                
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_settings:
                doSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    protected void doSettings()  {
    	//TODO: pop up a dialog that lets us get an integer input for maxRndAvail
    	//TODO: change the global variable maxRndAvail and store in settings
    	//      for retrieval later
    }
}

