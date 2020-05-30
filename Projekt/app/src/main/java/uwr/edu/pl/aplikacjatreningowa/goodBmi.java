package uwr.edu.pl.aplikacjatreningowa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class goodBmi extends AppCompatActivity {
    TextView wynik;
    Button kobietyBMI,mezczyzniBMI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_bmi);
        wynik = (TextView) findViewById(R.id.textView7);
        Intent intent = getIntent();
        final String dane = intent.getStringExtra("DANE");
        Float f= Float.parseFloat(dane);
        wynik.setText(dane);



        kobietyBMI = (Button) findViewById(R.id.button);
        mezczyzniBMI = (Button) findViewById(R.id.button2);

        kobietyBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openkobietaBMI();
            }
        });
        mezczyzniBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openmezczyznaBMI();
            }
        });
    }



    private void openmezczyznaBMI() {
        Intent intent = new Intent(this, BMIgraph.class);
        startActivity(intent);
    }

    public void openkobietaBMI()
    {
        Intent intent = new Intent(this, bmigirls.class);
        startActivity(intent);
    }
}
