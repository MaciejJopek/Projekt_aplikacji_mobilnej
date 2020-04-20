package uwr.edu.pl.aplikacjatreningowa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Opcje extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opcje);
        Button button_stoper = (Button) findViewById(R.id.stoper);
        button_stoper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStoper();
            }
        });
    }
    public void openStoper() {
        Intent intent = new Intent(this,Stoper.class);
        startActivity(intent);
    }
}
