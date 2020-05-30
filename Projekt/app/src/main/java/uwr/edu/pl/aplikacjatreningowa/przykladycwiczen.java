package uwr.edu.pl.aplikacjatreningowa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class przykladycwiczen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_przykladycwiczen);
        TextView button_fbw = (TextView) findViewById(R.id.textView13);

        ImageView button_fbw2 = (ImageView) findViewById(R.id.imageView5);

        button_fbw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFBW();
            }
        });

        button_fbw2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFBW();
            }
        });

    }

    private void openFBW() {
        Intent intent = new Intent(this, FBW.class);
        startActivity(intent);
    }
}
