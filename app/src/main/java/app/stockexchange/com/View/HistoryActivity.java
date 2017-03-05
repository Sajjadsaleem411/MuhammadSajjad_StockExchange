package app.stockexchange.com.View;

import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

import app.stockexchange.com.Control.SQLite;
import app.stockexchange.com.Model.Data;
import app.stockexchange.com.R;

public class HistoryActivity extends AppCompatActivity {

    private SQLite sqLite;
    public  ArrayList<Data> data;
    private BarChart mChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        sqLite=new SQLite(this);
        data=sqLite.GetData(getIntent().getStringExtra("Company"));


// Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public void SetGraph(){
        // To make vertical bar chart, initialize graph id this way
        mChart = (BarChart) findViewById(R.id.barchart);
        mChart.setBorderColor(R.color.colorPrimary);

        ArrayList<BarEntry> entries = new ArrayList<>();

        for(int i=0;i<data.size();i++){

            entries.add(new BarEntry((float) data.get(i).getPrice(), i));


        }
        BarDataSet dataset = new BarDataSet(entries, "");
        dataset.setBarSpacePercent(50f);


        // creating labels
        ArrayList<String> labels = new ArrayList<String>();
        for(int i=0;i<data.size();i++){

            labels.add(String.valueOf(data.get(i).getName()));

        }
        BarData data = new BarData(labels, dataset);
        data.setGroupSpace(10f);
        mChart.setData(data); // set the data and list of lables into chart

        mChart.setDescription("");  // set the description
        mChart.getAxisRight().setDrawLabels(false);

        XAxis xAxis = mChart.getXAxis();

        xAxis.setDrawGridLines(true);
//        xAxis.setAxisLineWidth(50f);
        xAxis.setTextSize(5f);
        xAxis.setTextColor(Color.RED);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawAxisLine(true);


    }
}
