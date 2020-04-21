package uwr.edu.pl.aplikacjatreningowa;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.Toast;

public class Stoper extends AppCompatActivity {
    private Button start,stop,reset;
    private ImageView icanchor;
    private Animation round;
    private Chronometer chronometer;
    private boolean running;
    private  long pauseOffset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stoper);

        chronometer = findViewById(R.id.chronometer);
        //chronometer.setFormat("Time:%s");


        start = findViewById(R.id.Start);
        stop = findViewById(R.id.Stop);
        reset = findViewById(R.id.Reset);
        icanchor = findViewById(R.id.icanchor);

        round = AnimationUtils.loadAnimation(this,R.anim.round);


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                icanchor.startAnimation(round);
                if(!running)
                {
                    chronometer.setBase(SystemClock.elapsedRealtime()-pauseOffset);
                    chronometer.start();
                    running = true;
                }

            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                icanchor.clearAnimation();
                if(running){
                    chronometer.stop();
                    pauseOffset = SystemClock.elapsedRealtime()-chronometer.getBase();
                    running = false;
                }
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                icanchor.clearAnimation();
                chronometer.stop();
                chronometer.setBase(SystemClock.elapsedRealtime());
                running = false;
                pauseOffset = 0;
            }
        });
    }

}
