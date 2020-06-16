package uwr.edu.pl.aplikacjatreningowa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class Opcje extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opcje);

        LinearLayout button_stoper = (LinearLayout) findViewById(R.id.Stoper);
        LinearLayout button_bmi = (LinearLayout) findViewById(R.id. Bmi);
        LinearLayout button_statystyki = (LinearLayout) findViewById(R.id.Statystyki);
        LinearLayout button_przykladwe_cwiczenia = (LinearLayout) findViewById(R.id.Cwiczenia);
        LinearLayout button_trening = (LinearLayout) findViewById(R.id.trening);

        button_stoper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStoper();
            }
        });

        button_bmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBmi();
            }
        });

        button_statystyki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStatystyki();
            }
        });

        button_przykladwe_cwiczenia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCwieczenia();
            }
        });
        button_trening.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTrening();
            }
        });
    }
    public void openStoper() {
        Intent intent = new Intent(this,Stoper.class);
        startActivity(intent);
    }
    public void openBmi(){
        Intent intent = new Intent(this, Bmi.class);
        startActivity(intent);
    }
    public void openStatystyki(){
        Intent intent = new Intent(this, statystyki.class);
        startActivity(intent);
    }
    public void openCwieczenia(){
        Intent intent = new Intent(this, przykladycwiczen.class);
        startActivity(intent);
    }
    public void openTrening(){
        Intent intent = new Intent(this, Notatnik.class);
        startActivity(intent);
    }
}
