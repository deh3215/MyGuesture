package com.example.a32150.myguesture;

import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView result;
    GestureOverlayView gestureOverlayView;
    GestureLibrary gestureLibrary;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv=(ImageView)findViewById(R.id.imageView1);

        result = (TextView)findViewById(R.id.textView1);
        gestureOverlayView = (GestureOverlayView) findViewById(R.id.gestureOverlay);
        gestureOverlayView.addOnGesturePerformedListener(listener);
        findGuestureLib();

    }

    void findGuestureLib()  {
        gestureLibrary = GestureLibraries.fromRawResource(this, R.raw.gestures);
        if(!gestureLibrary.load())
            finish();


    }

    GestureOverlayView.OnGesturePerformedListener listener = new GestureOverlayView.OnGesturePerformedListener() {
        @Override
        public void onGesturePerformed(GestureOverlayView gestureOverlayView, Gesture gesture) {
            ArrayList<Prediction> list = gestureLibrary.recognize(gesture);
//            if(list.size()>0 && list.get(0).score>5)    {
//                String name = list.get(0).name;
//                Double score = list.get(0).score;
//                result.setText("手勢 : "+name+", \n分數 : "+score);
//            }

            //String [] font
            if(list.size()>0)   {
                if(list.get(0).score > 3 && list.get(0).toString().equals("toRight") )   {
                    result.setVisibility(View.VISIBLE);
                    Random rnd = new Random();
                    int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                    int size = rnd.nextInt(32);
                    result.setText("文字向右");
                    result.setTextColor(color);
                    result.setTextSize(size);
                    iv.setVisibility(View.INVISIBLE);

                    //Toast.makeText(MainActivity.this, list.get(0).toString(), Toast.LENGTH_SHORT);

                }   else if(list.get(0).toString().equals("toLeft"))    {
                    Integer[] mImageIds = {
                            R.drawable.mickey_basketball2,
                            R.drawable.mickey_mouse2,
                            R.drawable.mickey_tattoo_disney_tattoos,
                            R.drawable.mickey2

                    };
                    result.setVisibility(View.INVISIBLE);
                    iv.setVisibility(View.VISIBLE);
                    Random rnd = new Random();
                    int rd=rnd.nextInt(mImageIds.length);
                    iv.setImageResource(mImageIds[rd]);
                }   else    {//最後一種手勢Z


                }


            }
        }
    };
}
