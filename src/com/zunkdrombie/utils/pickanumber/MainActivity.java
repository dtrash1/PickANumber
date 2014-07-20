package com.zunkdrombie.utils.pickanumber;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
        maxRndAvail = 5;
      
        final NumberPicker np1 = (NumberPicker) findViewById(R.id.numberPicker1);
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
            	//new PickRandomNumTask().execute();
            	PickRandomNumTask prntask = new PickRandomNumTask();
            	Void params = null;
            	prntask.execute(params);
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
    	
    	AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);

    	alert.setTitle("New Max Value");
    	alert.setMessage("Enter the new maximum random number you'd like:");

    	// Set an EditText view to get user input 
    	final EditText input = new EditText(MainActivity.this);
    	alert.setView(input);

    	alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
    	public void onClick(DialogInterface dialog, int whichButton) {
		String value = input.getText().toString();
		try {
		   int myNum = Integer.parseInt(value);
	    	//TODO: store the global variable maxRndAvail in settings
	    	//      for retrieval later
		   MainActivity.this.maxRndAvail = myNum;
		   final NumberPicker np1 = (NumberPicker) findViewById(R.id.numberPicker1);
	        np1.setMaxValue(myNum);
		} catch(NumberFormatException nfe) {
			//TODO: pop up a warning that you must enter an integer.
		}}
    	});

    	alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
    	  public void onClick(DialogInterface dialog, int whichButton) {
    	    // Canceled.
    	  }
    	});

    	alert.show();
    	
    	
    	
    }
    
    
    public class PickRandomNumTask extends AsyncTask<Void, Integer, Integer> {
    	@Override
    	protected Integer doInBackground(Void... dummy) {
        	NumberPicker np1 = (NumberPicker) findViewById(R.id.numberPicker1);
        	int maxval = np1.getValue();
        	//TODO:  Figure out why wait() causes a crash
//        	try {
//				wait(1);
//			} catch (InterruptedException e) {
//			}
        	int r = rgen.nextInt(maxval)+1;
        	return r;
        }

    	@Override
        protected void onPreExecute() {
        	findViewById(R.id.numberPicker1).setEnabled(false);
        	findViewById(R.id.button2).setEnabled(false);
        	findViewById(R.id.textView2).setVisibility(View.INVISIBLE); 
            findViewById(R.id.progressBar1).setVisibility(View.VISIBLE);           	
        }
        
    	@Override
        protected void onPostExecute(Integer r) {
        	findViewById(R.id.numberPicker1).setEnabled(true);
        	findViewById(R.id.button2).setEnabled(true);
        	findViewById(R.id.progressBar1).setVisibility(View.INVISIBLE);  
        	final TextView tv2 = (TextView) findViewById(R.id.textView2);
        	tv2.setText(Integer.toString(r));
        	tv2.setVisibility(View.VISIBLE);
        }
    } 
}

