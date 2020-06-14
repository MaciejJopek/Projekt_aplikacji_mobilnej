package uwr.edu.pl.aplikacjatreningowa;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.SimpleDateFormat;
import java.util.Date;

public class przysiadywykres extends AppCompatActivity {
    EditText yValue;
    Button btn_insert;

    FirebaseDatabase database;
    DatabaseReference reference;

    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM");
    GraphView graphView;
    LineGraphSeries series;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_przysiadywykres);

        yValue=(EditText) findViewById(R.id.inputTextY);
        btn_insert = (Button) findViewById(R.id.insertButton);
        graphView = (GraphView) findViewById(R.id.graph);

        series = new LineGraphSeries();
        series.setColor(Color.WHITE);
        series.setDrawDataPoints(true);

        database=FirebaseDatabase.getInstance();
        reference=database.getReference("PrzysiadyTable");

        setListeners();

        graphView.addSeries(series);
        graphView.getGridLabelRenderer().setNumHorizontalLabels(7);
        graphView.getGridLabelRenderer().setGridColor(Color.WHITE);
        graphView.getGridLabelRenderer().setHorizontalLabelsColor(Color.WHITE);
        graphView.getGridLabelRenderer().setVerticalLabelsColor(Color.WHITE);
        graphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter(){
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if (isValueX){
                    return sdf.format(new Date((long) value));
                }else {
                    return super.formatLabel(value, isValueX);
                }
            }
        });
    }
    private void setListeners() {
        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = reference.push().getKey();
                long x = new Date().getTime();
                int y = Integer.parseInt(yValue.getText().toString());

                PointValue pointValue = new PointValue(x,y);

                reference.child(id).setValue(pointValue);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot) {
                DataPoint[] dp = new DataPoint[(int) dataSnapshot.getChildrenCount()];
                int index = 0;
                for (DataSnapshot myDataSnapshot: dataSnapshot.getChildren())
                {
                    PointValue pointValue = myDataSnapshot.getValue(PointValue.class);
                    dp[index]=new DataPoint(pointValue.getxValue(),pointValue.getyValue());
                    index++;
                }
                series.resetData(dp);
            }

            @Override
            public void onCancelled( DatabaseError databaseError) {

            }
        });
    }
}
