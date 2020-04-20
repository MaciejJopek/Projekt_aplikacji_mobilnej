package uwr.edu.pl.aplikacjatreningowa;

import androidx.appcompat.app.AppCompatActivity;



import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class Stoper extends AppCompatActivity {
    Button start;
    ImageView icanchor;
    Animation round;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stoper);

        start = findViewById(R.id.Start);
        icanchor = findViewById(R.id.icanchor);

        round = AnimationUtils.loadAnimation(this,R.anim.round);


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                icanchor.startAnimation(round);
            }
        });
    }
}
