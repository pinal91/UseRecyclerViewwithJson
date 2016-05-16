package com.example.pinal.property;
import android.app.ProgressDialog;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    Json js;
    ArrayList<HashMap<String,String>>  data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv=(ListView)findViewById(R.id.lv);

        data=new ArrayList<HashMap<String, String>>();

        get g=new get();
        g.execute();
    }


    class get extends AsyncTask<Void,Void,Void>{

        String strfname,straddress,stremail,strid,gender,mobile,str1,str2;
        String idno,firstname,lastname,eid;
        ProgressDialog pd;
        String name,area,type,bed,bath,rooms,country,logo,image;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pd=new ProgressDialog(MainActivity.this);
            pd.setMessage("getting data");
            pd.setIndeterminate(false);
            pd.setCancelable(false);
            pd.show();

        }

        @Override
        protected Void doInBackground(Void... params) {


            String s = "http://aaqaar.com/beta/api/property?type=R&country_id=18&userid=52&lang=en&curr=KWD";
            //  String s = "http://raw.githubusercontent.com/mobilesiri/Android-Custom-Listview-Using-Volley/master/richman.json";

            Log.d("dataaaaaaa",s.toString());

            Json js=new Json();

            // Log.d("Async completed", "Done");
            // Log.d("database", "product comes" + s);

            String resp=js.display1(s);
            System.out.println("String response" + resp);

            if(resp!= null){
                try {

                    JSONObject  jobj=new JSONObject(resp);
                    JSONArray jarray=jobj.getJSONArray("property");




                    for (int i=0;i<jarray.length();i++){

                        JSONObject jo=jarray.getJSONObject(i);
                      /*  strid = jo.getString("title");
                        strfname = jo.getString("url");*/

                        name = jo.getString("name");
                        area = jo.getString("area");
                        type = jo.getString("type");
                        bed = jo.getString("beds");
                        bath = jo.getString("baths");
                        rooms = jo.getString("rooms");

                        country = jo.getString("country");
                        image = jo.getString("image");
                        logo = jo.getString("logo");


                        Log.d("object json:", jo.toString());
                        HashMap<String,String> map=new HashMap<String, String>();

                     /*   map.put("strid",strid);*/

                        map.put("name",name);
                        map.put("area",area);
                        map.put("type",type);
                        map.put("bed",bed);
                        map.put("bath",bath);
                        map.put("rooms",rooms);

                        map.put("country",country);
                        map.put("image",image);
                        map.put("logo",logo);

                        data.add(map);

                        Log.d("map value ", String.valueOf(map));


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);


            if(pd.isShowing()){
                pd.dismiss();


            }
            Adapter an = new Adapter(MainActivity.this,data);
            Log.d("d", "d");
            lv.setAdapter(an);



        }


    }

}
