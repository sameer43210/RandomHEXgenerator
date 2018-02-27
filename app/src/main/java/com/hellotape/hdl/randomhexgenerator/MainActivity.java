package com.hellotape.hdl.randomhexgenerator;

import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private AdView mAdView;
    int buttonclicked = 0;
    int length_min;
    int length_max;
    int i ;

    int diff ;
    TextView noofdigit , noofresult , output ;
    int maxval,minval,outputval ;
    Button b1;
    int noofdigitval , noofresultval  ;
    Random r = new Random();
    String append ,final_result ,final_result_final ;
    Chronometer cmtimer;
Handler handler ;
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        try{

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        try{

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        this.cmtimer = (Chronometer)findViewById(R.id.chronometer);
        this.noofresult = (EditText) findViewById(R.id.editText);
        this.noofdigit = (EditText)findViewById(R.id.editText2);
        this.output= (TextView)findViewById(R.id.textView);
        this.b1  = (Button)findViewById(R.id.button);
        this.b1.setOnClickListener(this);
        handler = new Handler(getApplicationContext().getMainLooper());
        cmtimer.setVisibility(View.INVISIBLE);
        cmtimer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                long minutes = ((SystemClock.elapsedRealtime() - cmtimer.getBase())/1000) / 60;
                long seconds = ((SystemClock.elapsedRealtime() - cmtimer.getBase())/1000) % 60;
              //  elapsedTime = SystemClock.elapsedRealtime();
            }
        });
    }


    @Override
    public void onClick(View v) {

        buttonclicked= buttonclicked + 1 ;
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        String check_min = noofdigit.getText().toString();
        String check_max = noofresult.getText().toString();

        if ((TextUtils.isEmpty(check_min))||(TextUtils.isEmpty(check_max))) {
            android.widget.Toast.makeText(getBaseContext(), "!! One of the fields is empty !!",
                    Toast.LENGTH_LONG).show();

        }
        else if(((noofdigit.getText().length()) > 9)||((noofdigit.getText().length()) > 9)){

                android.widget.Toast.makeText(getBaseContext(), "!! Please lower down the number of digits or number of results !!",
                        Toast.LENGTH_LONG).show();
            }else {
         //
            append = "" ;

                noofdigitval = Integer.parseInt(noofdigit.getText().toString());
                noofresultval = Integer.parseInt(noofresult.getText().toString());
            if((noofdigitval)*(noofresultval) > 24999) {
                cmtimer.setVisibility(View.VISIBLE);
                output.setText("Wait for output  ...\n");
                if(buttonclicked == 4)
                {
                 output.setText("Please be patient you have put a large number\n");
                }
                else if(buttonclicked == 3)
                {
                    output.setText("keep calm and take breath ...\n");
                }
                else if(buttonclicked > 1)
                {
                    output.setText("wait for output  :)\n");
                }
                else{

                }
                if(buttonclicked<=1) {
                    cmtimer.setBase(SystemClock.elapsedRealtime());
                    cmtimer.start();
                }
            }
                   else
            {
                if(buttonclicked <=1) {
                    cmtimer.setVisibility(View.INVISIBLE);
                }
            }
                if(buttonclicked <=1) {


                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                final_result_final = "";
                                for (int y = 0; y < noofresultval; y++) {
                                    final_result = "";
                                    diff = noofdigitval;
                                    for (; ; ) {
                                        diff = diff - 7;
                                        if (diff > 0) {
                                            hexgenerator(diff);
                                        } else {
                                            hexgenerator(diff);
                                            break;
                                        }
                                        final_result = final_result + append;
                                    }
                                    final_result_final = final_result_final + final_result + append + "\n";
                                    buttonclicked = 0;


                                }
                                cmtimer.stop();
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        output.setText(final_result_final);
                                    }
                                });

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                }




            }

        }



    void hexgenerator(int val)
    {
        double num = 0 ;
        if(val > 0)
        {

            for(i=0;i<6;i++)
            {
                num = num + Math.pow(16,i);
            }
            minval = (int) (15 * num) + 1 ;
            num = 0 ;
            for(i=0;i<7;i++)
            {
                num = num + Math.pow(16,i);
            }
            maxval = (int) (15 * num);
            num = 0 ;
            outputval = r.nextInt(maxval - minval + 1) + minval;
           // append = append + Integer.toHexString(outputval);
            append = Integer.toHexString(outputval);
        }
        else
        {
         int   loop = val + 7 ;
            for(i=0;i<(loop -1);i++)
            {
                num = num + Math.pow(16,i);
            }
            minval = (int) (15 * num) + 1 ;
            num = 0 ;
            for(i=0;i<(loop);i++)
            {
                num = num + Math.pow(16,i);
            }
            maxval = (int) (15 * num);
            num = 0 ;
            outputval = r.nextInt(maxval - minval + 1) + minval;
           // append = append + Integer.toHexString(outputval) + "\n";
            append = Integer.toHexString(outputval);

        }

    }

}
