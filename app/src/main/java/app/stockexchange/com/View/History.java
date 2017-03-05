package app.stockexchange.com.View;

import android.content.Intent;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import app.stockexchange.com.Control.SQLite;
import app.stockexchange.com.Model.Data;
import app.stockexchange.com.R;

public class History extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private SQLite sqLite;

    public static ArrayList<Data> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        sqLite=new SQLite(this);

        data=sqLite.GetData(getIntent().getStringExtra("Company"));
// Always cast your custom Toolbar here, and set it as the ActionBar.
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);

        // Get the ActionBar here to configure the way it behaves.
        final ActionBar ab = getSupportActionBar();
        //ab.setHomeAsUpIndicator(R.drawable.ic_menu); // set a custom icon for the default home button
        ab.setDisplayShowHomeEnabled(true); // show or hide the default home button
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowCustomEnabled(true); // enable overriding the default toolbar circular_progress_bar
        ab.setDisplayShowTitleEnabled(false); // disable the default title element here (for centered title)

        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(History.this, MainActivity.class);
                startActivity(intent);

                finish();
            }
        });
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_history, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_history, container, false);
            /*
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));*/

            return rootView;
        }
    }
    /**
     * A placeholder fragment containing a simple view.
     */
    public static class GraphFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        String[] months={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};

        private BarChart mChart;
        public LineChart lineChart;

        private static final String ARG_SECTION_NUMBER = "section_number";

        public GraphFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static GraphFragment newInstance() {
            GraphFragment fragment = new GraphFragment();
            /*Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);*/
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.graph_fragment, container, false);

            // To make vertical bar chart, initialize graph id this way
            mChart = (BarChart) rootView.findViewById(R.id.barchart);
            lineChart = (LineChart) rootView.findViewById(R.id.linechart);

            LineChart();
            mChart.setBorderColor(R.color.colorPrimary);

            ArrayList<BarEntry> entries = new ArrayList<>();

            for(int i=0;i<months.length;i++){

                entries.add(new BarEntry((float) data.get(i).getPrice(), i));


            }
            BarDataSet dataset = new BarDataSet(entries, "");
            dataset.setBarSpacePercent(50f);
            dataset.setColor(R.color.colorPrimary);


            // creating labels
            ArrayList<String> labels = new ArrayList<String>();
            for(int i=0;i<months.length;i++){

                labels.add(months[i]);

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


        return rootView;
        }
        public void LineChart(){

            lineChart.setDescription("");
            ArrayList<Entry> entries = new ArrayList<>();

            for(int i=0;i<months.length;i++){

                entries.add(new Entry((float) data.get(i).getPrice(), i));


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

            for(int i=0;i<months.length;i++){

                labels.add(months[i]);

            }

            LineData data = new LineData(labels, dataset);
            dataset.setColor(R.color.colorPrimary);
            dataset.setDrawCubic(true);
            dataset.setDrawFilled(true);


            lineChart.setData(data);
            lineChart.animateY(5000);;

        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

            if(position==0){

                return PlaceholderFragment.newInstance(position + 1);
            }
            else{

                return GraphFragment.newInstance();
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Detail";
                case 1:
                    return "Graph";
            }
            return null;
        }
    }
}
