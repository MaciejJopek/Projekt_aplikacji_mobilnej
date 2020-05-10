package uwr.edu.pl.aplikacjatreningowa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Opcje extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opcje);
        /*
        LinearLayout button_stoper = (LinearLayout) findViewById(R.id.Stoper);

        button_stoper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStoper();
            }
        });
    */
    }
    public void openStoper() {
        Intent intent = new Intent(this,Stoper.class);
        startActivity(intent);
    }
}
