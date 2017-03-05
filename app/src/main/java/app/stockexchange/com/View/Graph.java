package app.stockexchange.com.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

import app.stockexchange.com.R;

public class Graph extends AppCompatActivity {

    LineChart lineChart;

    String[] name = {"Google", "Facebook", "Microsoft", "Fetch Sky", "Amazon"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        lineChart=(LineChart)findViewById(R.id.linechart);

        lineChart.setDescription("");
        ArrayList<Entry> entries = new ArrayList<>();

        for(int i=0;i<MainActivity.userDatas.length;i++){
            entries.add(new Entry((float) MainActivity.userDatas[i].getProfit(), i));


        }
          /*  entries.add(new Entry(4f, 0));
            entries.add(new Entry(8f, 1));
            entries.add(new Entry(6f, 2));
            entries.add(new Entry(2f, 3));
            entries.add(new Entry(18f, 4));
            entries.add(new Entry(9f, 5));*/

        LineDataSet dataset = new LineDataSet(entries, "# of Calls");
        dataset.setColor(R.color.colorPrimary);

        ArrayList<String> labels = new ArrayList<String>();

        for(int i=0;i<MainActivity.userDatas.length;i++){

            labels.add(name[i]);

        }

        LineData data = new LineData(labels, dataset);
        dataset.setColor(R.color.colorPrimary);
        dataset.setDrawCubic(true);
        dataset.setDrawFilled(true);


        lineChart.setData(data);
        lineChart.animateY(5000);;

    }
}
