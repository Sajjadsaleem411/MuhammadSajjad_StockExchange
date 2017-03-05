package app.stockexchange.com.Control;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import app.stockexchange.com.Model.Data;

/**
 * Created by Muhammad Sajjad on 3/4/2017.
 */
public class SQLite {

    SQLiteDatabase db;
    Context mContext;

    public SQLite(Context context) {
        mContext = context;
        CreateDatabase();

    }


    private void CreateDatabase() {

        db = mContext.openOrCreateDatabase("vReport", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS stock(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "company VARCHAR," +
                "price VARCHAR," +
                "image VARCHAR," +
                "detail VARCHAR," +
                "found VARCHAR," +
                "created_at DATETIME DEFAULT CURRENT_TIMESTAMP);");

    }

    public void InsertData(String name, String price,int image,String detail,String found) {


            try {

                ContentValues insertValues = new ContentValues();
                insertValues.put("company", name);
                insertValues.put("price", price);
                insertValues.put("image", ""+image);
                insertValues.put("detail", detail);
                insertValues.put("found", found);
                db.insert("stock", null, insertValues);
            } catch (Exception e) {

            }

    }

    public ArrayList<Data> GetData(String name){

        ArrayList<Data> datas=new ArrayList<>();

        Cursor c = db.rawQuery("SELECT * FROM stock where company='" + name +"'  ", null);
        if (c.getCount() == 0) {
            //        showMessage("Error", "No records found");
            Log.d("error", "no data in survey database");
     //       return null;
        } else {
            Log.d("Data","***************");
            if (c != null && c.moveToFirst()) ;
            do {
                Log.d("company"+name+ "data:", "=" + c.getString(0) + " " + c.getString(1) +
                        " " + c.getString(2) + " " + c.getString(3) + " " + c.getString(4));
       //         return new String[]{c.getString(1), c.getString(2), c.getString(3)};

                Data data=new Data();
                data.setName(c.getString(1));
                data.setPrice(Float.parseFloat(c.getString(2)));
                data.setImage(Integer.parseInt(c.getString(3)));
                data.setDetail(c.getString(4));
                data.setFound(c.getString(5));
                datas.add(data);

            } while (c.moveToNext());
        }

        return datas;
    }

}
