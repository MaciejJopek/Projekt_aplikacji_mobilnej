package uwr.edu.pl.aplikacjatreningowa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class statystyki extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statystyki);

        LinearLayout button_waga = (LinearLayout) findViewById(R.id.Waga);
        LinearLayout button_laweczka = (LinearLayout) findViewById(R.id.Laweczka);
        button_laweczka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openlaweczka();
            }
        });
        button_waga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openwaga();
            }
        });
    }

    private void openlaweczka() {
        Intent intent = new Intent(this,laweczkawykres.class);
        startActivity(intent);
    }

    public void openwaga() {
        Intent intent = new Intent(this,wagawykres.class);
        startActivity(intent);
    }
}
