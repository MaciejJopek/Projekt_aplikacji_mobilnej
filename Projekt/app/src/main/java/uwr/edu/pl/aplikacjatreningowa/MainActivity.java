package uwr.edu.pl.aplikacjatreningowa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button button;
    Animation sharktg,napisgl,buttonrozpocznij;
    ImageView sharkanim;
    TextView napisglowny;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.rozpocznij);
        sharkanim = findViewById(R.id.shark);
        napisglowny = findViewById(R.id.textView);

        sharktg = AnimationUtils.loadAnimation(this,R.anim.sharktg);
        buttonrozpocznij = AnimationUtils.loadAnimation(this,R.anim.buttonrozpocznij);
        napisgl= AnimationUtils.loadAnimation(this,R.anim.napisgl);

        sharkanim.startAnimation(sharktg);
        button.startAnimation(buttonrozpocznij);
        napisglowny.startAnimation(napisgl);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOpcje();
            }
        });
    }
    public void openOpcje()
    {
        Intent intent = new Intent(this, Opcje.class);
        startActivity(intent);
    }
}
