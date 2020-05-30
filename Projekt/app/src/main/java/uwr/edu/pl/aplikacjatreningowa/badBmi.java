package uwr.edu.pl.aplikacjatreningowa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class badBmi extends AppCompatActivity {
    TextView wynik,komunikat;
    Button kobietyBMI,mezczyzniBMI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bad_bmi);
        wynik = (TextView) findViewById(R.id.textView7);
        komunikat = (TextView) findViewById(R.id.komunikat);
        Intent intent = getIntent();
        final String dane = intent.getStringExtra("DANE");
        Float f= Float.parseFloat(dane);
        if (f<18.5){
            wynik.setText(dane);
            komunikat.setText("Przykro nam - wykryto niedowagę");
        }
        if (f>=25 && f<=29.9){
            wynik.setText(dane);
            komunikat.setText("Przykro nam - wykryto nadwagę");
        }
        if (f>30){
            wynik.setText(dane);
            komunikat.setText("Przykro nam - wykryto otyłość");
        }
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
