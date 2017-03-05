package app.stockexchange.com.View;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import app.stockexchange.com.Model.UserData;
import app.stockexchange.com.R;
import app.stockexchange.com.Control.SQLite;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final int RATE_LENGTH = 50000;

    public static UserData[] userDatas;
    public static UserData user;

    int[] id_image={R.drawable.google,R.drawable.facebook,R.drawable.microsoft,R.drawable.fs_icon,R.drawable.amazon};
    public ArrayAdapter<String> adapter;
    float previous = 0;
    String[] name = {"Google", "Facebook", "Microsoft", "Fetch Sky", "Amazon"};
    String[] detail={
            "Google is an American multinational technology company specializing in Internet-related services and products. These include online advertising technologies, search, cloud computing, software, and hardware. ",
            "Facebook is an American for-profit corporation and online social media and social networking service based in Menlo Park, California.",
            "Microsoft Corporation /ˈmaɪkrəˌsɒft, -roʊ-, -ˌsɔːft/ is an American multinational technology company headquartered in Redmond, Washington",
            "Fetch Sky, also called FetchSky, is an American electronic commerce and cloud computing company that was founded on July 5, 1994 by Jeff Bezos and is based in Seattle, Washington.",
            "Amazon.com, also called Amazon, is an American electronic commerce and cloud computing company that was founded on July 5, 1994 by Jeff Bezos and is based in Seattle, Washington."
    };

    String[] found={
            "4 September 1998",
            "February 2004",
            " 4 April 1975",
            " 4 June 2015",
            "July 5, 1994"
    };

    SQLite sqLite;
    Button[] rate;
    LinearLayout[] layouts;

    float min = 100.0f, max = 5000.0f;

    TextView[] company;
    int[] id;
    int[] idrate;
    int[] id_layout;
    int rang = 5;
    Random r;
    public static float c_bal=10000.0f;
    float[] price_array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userDatas=new UserData[5];
        for(int i=0;i<5;i++){
            user=new UserData();
            userDatas[i]=user;
        }
        sqLite = new SQLite(this);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, name);

        id = new int[]{R.id.G_price, R.id.f_price, R.id.m_price, R.id.fs_price, R.id.A_price};
        idrate = new int[]{R.id.G_rate, R.id.f_rate, R.id.m_rate, R.id.fs_rate, R.id.a_rate};
        id_layout = new int[]{R.id.g_layout, R.id.f_layout, R.id.m_layout, R.id.fs_layout, R.id.a_layout};

        Button buy,sell;
        final TextView c_balance;

        company = new TextView[rang];
        c_balance=(TextView)findViewById(R.id.c_balance);
        c_balance.setText("$ "+c_bal);

        rate = new Button[rang];
        layouts=new LinearLayout[rang];

        for (int i = 0; i < rang; i++) {

            company[i] = (TextView) findViewById(id[i]);
            rate[i] = (Button) findViewById(idrate[i]);
            layouts[i]=(LinearLayout)findViewById(id_layout[i]);
            layouts[i].setOnClickListener(this);
        }
        r = new Random();
        price_array = new float[rang];

        ChangePrice();

        Button graph=(Button)findViewById(R.id.graph);
        graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(userDatas!=null)
                startActivity(new Intent(MainActivity.this,Graph.class));

            }
        });

        buy = (Button) findViewById(R.id.buy);
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Select by Category")
                        .setAdapter(adapter, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                // TODO: user specific action

                                if (name[which].contentEquals(name[0])) {

                                    if(price_array[0]<c_bal){
                                        user.setSell_price(price_array[0]);
                                        user.setProfit(0.0f);
                                        user.setLost(0.0f);
                                        userDatas[0]=(user);

                                       c_bal=c_bal-price_array[0];
                                        c_balance.setText("$ "+c_bal);

                                    }

                                }
                                else if (name[which].contentEquals(name[1])) {
                                    if(price_array[1]<c_bal){

                                        user.setProfit(0.0f);
                                        user.setLost(0.0f);
                                        userDatas[0]=(user);

                                        user.setSell_price(price_array[0]);
                                        c_bal=c_bal-price_array[1];
                                        c_balance.setText("$ "+c_bal);
                                    }
                                }

                                else if (name[which].contentEquals(name[2])) {
                                    if(price_array[2]<c_bal){

                                        user.setProfit(0.0f);
                                        user.setLost(0.0f);
                                        userDatas[0]=(user);

                                        c_bal=c_bal-price_array[2];
                                        c_balance.setText("$ "+c_bal);
                                    }
                                }
                                else if (name[which].contentEquals(name[3])) {
                                    if(price_array[3]<c_bal){

                                        user.setProfit(0.0f);
                                        user.setLost(0.0f);
                                        userDatas[0]=(user);

                                        c_bal=c_bal-price_array[3];
                                        c_balance.setText("$ "+c_bal);
                                    }

                                }
                                else if (name[which].contentEquals(name[4])) {
                                    if(price_array[4]<c_bal){

                                        user.setProfit(0.0f);
                                        user.setLost(0.0f);

                                        userDatas[0]=(user);

                                        c_bal=c_bal-price_array[4];
                                        c_balance.setText("$ "+c_bal);
                                    }

                                }

                                dialog.dismiss();
                            }
                        }).create().show();
            }
        });

        sell = (Button) findViewById(R.id.sell);
        sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Select by Category")
                        .setAdapter(adapter, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                // TODO: user specific action

                                if (name[which].contentEquals(name[0])) {

                                    c_bal=c_bal+price_array[0];
                                    c_balance.setText("$ "+c_bal);

                                    UserData userData=userDatas[0];
                                    if(userData.getSell_price()>price_array[0]){
                                        userData.setLost(userData.getSell_price()-price_array[0]);
                                    }
                                    else {
                                        userData.setProfit(price_array[0]-userData.getSell_price());

                                    }
                                    userDatas[0]=userData;

                                }
                                else if (name[which].contentEquals(name[1])) {
                                    c_bal=c_bal+price_array[1];
                                    c_balance.setText("$ "+c_bal);

                                    UserData userData=userDatas[1];
                                    if(userData.getSell_price()>price_array[1]){
                                        userData.setLost(userData.getSell_price()-price_array[1]);
                                    }
                                    else {
                                        userData.setProfit(price_array[1]-userData.getSell_price());

                                    }
                                    userDatas[1]=userData;

                                }

                                else if (name[which].contentEquals(name[2])) {
                                    c_bal=c_bal+price_array[2];
                                    c_balance.setText("$ "+c_bal);

                                    UserData userData=userDatas[2];
                                    if(userData.getSell_price()>price_array[2]){
                                        userData.setLost(userData.getSell_price()-price_array[2]);
                                    }
                                    else {
                                        userData.setProfit(price_array[2]-userData.getSell_price());

                                    }
                                    userDatas[2]=userData;

                                }
                                else if (name[which].contentEquals(name[3])) {
                                    c_bal=c_bal+price_array[3];
                                    c_balance.setText("$ "+c_bal);

                                    UserData userData=userDatas[3];
                                    if(userData.getSell_price()>price_array[3]){
                                        userData.setLost(userData.getSell_price()-price_array[3]);
                                    }
                                    else {
                                        userData.setProfit(price_array[3]-userData.getSell_price());

                                    }
                                    userDatas[3]=userData;

                                }
                                else if (name[which].contentEquals(name[4])) {
                                    c_bal=c_bal+price_array[4];
                                    c_balance.setText("$ "+c_bal);

                                    UserData userData=userDatas[4];
                                    if(userData.getSell_price()>price_array[4]){
                                        userData.setLost(userData.getSell_price()-price_array[4]);
                                    }
                                    else {
                                        userData.setProfit(price_array[4]-userData.getSell_price());

                                    }
                                    userDatas[4]=userData;

                                }

                                dialog.dismiss();
                            }
                        }).create().show();
            }
        });


        Thread t = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(RATE_LENGTH);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // update TextView here!
                                ChangePrice();

                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };

        t.start();


    }

    void ChangePrice() {
        for (int i = 0; i < rang; i++) {
            previous = price_array[i];
            float price = r.nextFloat() * (max - min) + min;
            price_array[i] = price;
            sqLite.InsertData(name[i], "" + price_array[i],id_image[i],detail[i],found[i]);

            Log.d("" + i, "Value" + price_array[i]);
            if (previous < price_array[i]) {
                rate[i].setBackgroundColor(getResources().getColor(R.color.green));
            } else {
                rate[i].setBackgroundColor(getResources().getColor(R.color.red));

            }
        }
        for (int i = 0; i < rang; i++) {

            company[i].setText("$ " + price_array[i]);

        }


    }

    @Override
    public void onClick(View view) {
        Intent intent;
        if(view.getId()==R.id.g_layout){


            intent=new Intent(MainActivity.this,History.class);
            intent.putExtra("Company",name[0]);
            startActivity(intent);

        }
        else if(view.getId()==R.id.f_layout){
            intent=new Intent(MainActivity.this,History.class);
            intent.putExtra("Company",name[1]);
            startActivity(intent);

        }
        else if(view.getId()==R.id.m_layout){

            intent=new Intent(MainActivity.this,History.class);
            intent.putExtra("Company",name[2]);
            startActivity(intent);
        }
        else if(view.getId()==R.id.fs_layout){

            intent=new Intent(MainActivity.this,History.class);
            intent.putExtra("Company",name[3]);
            startActivity(intent);
        }
        else if(view.getId()==R.id.a_layout){

            intent=new Intent(MainActivity.this,History.class);
            intent.putExtra("Company",name[4]);
            startActivity(intent);
        }

    }
}

