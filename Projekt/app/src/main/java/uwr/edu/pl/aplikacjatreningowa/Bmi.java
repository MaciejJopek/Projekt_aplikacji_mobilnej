package uwr.edu.pl.aplikacjatreningowa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class Bmi extends AppCompatActivity {
    SeekBar wysokoscbar, wagabar;
    TextView wysokosc,waga;
    Button wynikBMI, kobietyBMI, mezczyzniBMI;
    float wynikkoncowy;
    int min=0,  max=250, currentwzrozt=0, currentwaga=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
        wysokoscbar = (SeekBar) findViewById(R.id.seekBarwysokosc);
        wysokosc = (TextView) findViewById(R.id.textView6);
        wagabar = (SeekBar) findViewById(R.id.seekBarwaga);
        waga = (TextView) findViewById(R.id.textView4);
        wynikBMI = (Button) findViewById(R.id.buttonwynikbmi);

        wysokoscbar.setMax(max-min);
        wysokoscbar.setProgress(currentwzrozt-min);
        wysokosc.setText(""+currentwzrozt);

        wagabar.setMax(max-min);
        wagabar.setProgress(currentwaga-min);
        waga.setText(""+currentwaga);

        wysokoscbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                currentwzrozt = progress + min;
                wysokosc.setText(""+currentwzrozt);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        wagabar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                currentwaga = progress + min;
                waga.setText(""+currentwaga);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        wynikBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opengoodBmi();
            }
        });
    }
    public void opengoodBmi(){
        float wrozstmetry = (float) currentwzrozt/100;
        float mianownik = (float) Math.pow(wrozstmetry,2);
        float currentwagafloat = currentwaga;
        wynikkoncowy = currentwagafloat/mianownik;
        String ostatecznywynikdoprzeslania = String.format("%.2f",wynikkoncowy);
        if (wynikkoncowy>=18.5 && wynikkoncowy<=24.9) {
            Intent intent = new Intent(this, goodBmi.class);
            intent.putExtra("DANE", ostatecznywynikdoprzeslania);
            startActivity(intent);
        }
        if (wynikkoncowy<18.5 || wynikkoncowy>24.9){
            Intent intent = new Intent(this, badBmi.class);
            intent.putExtra("DANE", ostatecznywynikdoprzeslania);
            startActivity(intent);
        }
    }

}
