package uwr.edu.pl.aplikacjatreningowa;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import uwr.edu.pl.aplikacjatreningowa.Data.database;


public class przysiadywykres extends AppCompatActivity {
    private BarChart barChart;
    private Button button;
    private EditText editText;
    private database db;
    long date = System.currentTimeMillis();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_przysiadywykres);
        barChart =(BarChart) findViewById(R.id.graph);
        button = (Button) findViewById(R.id.insertButton);
        editText = (EditText) findViewById(R.id.inputTextY);

        addDataToGraph();
        barChart.invalidate();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveToDatabase();

            }
        });
    }
    public void SaveToDatabase(){
        db = new database(this);

        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd MMM");
        String xvalue = sdf.format(date);

        String yvalue = editText.getText().toString();

        db.saveDate(xvalue,yvalue, "Przysiady");
        addDataToGraph();
        barChart.invalidate();

        db.close();
    }
    public void addDataToGraph(){
        db = new database(this);

        final ArrayList<BarEntry> yVals = new ArrayList<BarEntry>();
        final ArrayList<String> ydata = db.queryYData("'Przysiady'");

        for (int i = 0;i<db.queryYData("'Przysiady'").size();i++){
            BarEntry newBarEntry = new BarEntry(i, Float.parseFloat(db.queryYData("'Przysiady'").get(i)));
            yVals.add(newBarEntry);
        }
        final ArrayList<String> xVals = new ArrayList<String>();
        final ArrayList<String> xdata = db.queryXData("'Przysiady'");

        for (int i = 0; i < db.queryXData("'Przysiady'").size();i++){
            xVals.add(xdata.get(i));
        }

        BarDataSet dataSet =new BarDataSet(yVals, "kg");
        ArrayList<IBarDataSet> dataSets1 = new ArrayList<>();
        dataSets1.add(dataSet);

        BarData data = new BarData(dataSets1);

        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xVals));

        barChart.setData(data);


        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        xAxis.setAvoidFirstLastClipping(true);
        xAxis.setDrawLabels(true);
        xAxis.isCenterAxisLabelsEnabled();
        xAxis.setTextColor(Color.WHITE);
        //xAxis.setGranularityEnabled(true);

        YAxis rightAxis = barChart.getAxisRight();
        rightAxis.setEnabled(false);

        //barChart.setMaxVisibleValueCount(5);
        barChart.setVisibleXRangeMaximum(10);
        barChart.setBackgroundColor(12);
        barChart.setFitBars(true);
        barChart.moveViewToX(10);

    }
}
