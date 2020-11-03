package uwr.edu.pl.aplikacjatreningowa;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import uwr.edu.pl.aplikacjatreningowa.Data.database;

public class hantlewykres extends AppCompatActivity {

    private BarChart barChart;
    private Button button;
    private EditText editText;
    private database db;
    long date = System.currentTimeMillis();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hantlewykres);

        barChart =(BarChart) findViewById(R.id.barchart);
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

        db.saveDate(xvalue,yvalue);
        addDataToGraph();
        barChart.invalidate();

        db.close();
    }
    public void addDataToGraph(){
        db = new database(this);

        final ArrayList<BarEntry> yVals = new ArrayList<BarEntry>();
        final ArrayList<String> ydata = db.queryYData();

        for (int i = 0;i<db.queryYData().size();i++){
            BarEntry newBarEntry = new BarEntry(i, Float.parseFloat(db.queryYData().get(i)));
            yVals.add(newBarEntry);
        }
        final ArrayList<String> xVals = new ArrayList<String>();
        final ArrayList<String> xdata = db.queryXData();

        for (int i = 0; i < db.queryXData().size();i++){
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
//        barChart.setFitBars(true);
        barChart.moveViewToX(10);


    }
}
//    private BarChart mChart;
//    EditText yValue;
//    Button btn_insert;
//    FirebaseDatabase database;
//    DatabaseReference reference;
//
//    BarDataSet lineDataSet = new BarDataSet(null,"dataset1");
//    ArrayList<IBarDataSet> iLineDataSets = new ArrayList<>();
//    BarData lineData;
//
//    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM");
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_hantlewykres);
//        yValue=(EditText) findViewById(R.id.inputTextY);
//        btn_insert = (Button) findViewById(R.id.insertButton);
//        database=FirebaseDatabase.getInstance();
//        reference=database.getReference("hantleTable");
//        setListeners();
//
//        mChart = (BarChart) findViewById(R.id.barchart);
////        mChart.setDragEnabled(true);
////        mChart.setScaleEnabled(true);
//
////        lineDataSet.setLineWidth(5);
//    }
//
//
//    private void setListeners() {
//        btn_insert.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Date date = new Date();
//                int mMonth = date.getMonth();
//
//                String id = reference.push().getKey();
//                int x = mMonth;
//                int y = Integer.parseInt(yValue.getText().toString());
//
//                PointValue pointValue = new PointValue(x,y);
//
//                reference.child(id).setValue(pointValue);
//            }
//        });
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange( DataSnapshot dataSnapshot) {
//                ArrayList<BarEntry> dataVals = new ArrayList<BarEntry>();
//                if (dataSnapshot.hasChildren()) {
//                    int index = 1;
//                    for (DataSnapshot myDataSnapshot : dataSnapshot.getChildren()) {
//                        PointValue pointValue = myDataSnapshot.getValue(PointValue.class);
//                        dataVals.add(new BarEntry(pointValue.getxValue(), pointValue.getyValue()));
//                        index ++;
//
//                    }
//                    showChart(dataVals);
//                }else{
//                    mChart.clear();
//                    mChart.invalidate();
//                }
//            }
//
//            @Override
//            public void onCancelled( DatabaseError databaseError) {
//
//            }
//        });
//    }
//
//    private void showChart(ArrayList<BarEntry> dataVals) {
//
//        lineDataSet.setValues(dataVals);
//        iLineDataSets.clear();
//        iLineDataSets.add(lineDataSet);
//        lineData = new BarData(iLineDataSets);
//        mChart.clear();
//        mChart.setData(lineData);
//        mChart.invalidate();
//    }
//
//    private class MyValueFormatter implements IValueFormatter{
//
//        @Override
//        public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
//            return value+ "$";
//        }
//    }
//}