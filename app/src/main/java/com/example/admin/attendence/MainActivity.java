package com.example.admin.attendence;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7;
    //String  ="http://203.124.96.117:8063/Service1.asmx/Attendance";
    RequestQueue requestQueue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestQueue= Volley.newRequestQueue(MainActivity.this);

        tv1= (TextView) findViewById(R.id.tv_id);
        tv2= (TextView) findViewById(R.id.tv_Roll);
        tv3= (TextView) findViewById(R.id.t_name);
        tv4= (TextView) findViewById(R.id.tv_attndnce);
        tv5= (TextView) findViewById(R.id.tv_status);
        tv6= (TextView) findViewById(R.id.tv_cls_id);
        tv7= (TextView) findViewById(R.id.tv_section_id);

        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest("http://203.124.96.117:8063/service1.asmx/Attendance", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                for(int i=0;i<jsonArray.length();i++)
                {
                    try {
                        JSONObject obj= (JSONObject) jsonArray.get(i);

                        String StuId=obj.getString("StuId");
                        String StuRollNo=obj.getString("StuRollNo");
                        String Name=obj.getString("Name");
                        String  Att_date=obj.getString("Att_date");
                        String Status=obj.getString("Status");
                        String ClassId=obj.getString("ClassId");
                        String SectionId=obj.getString("SectionId");

                        tv1.setText("StuId");
                        tv2.setText("StuRollNo");
                        tv3.setText("Name");
                        tv4.setText("Att_date");
                        tv5.setText("Status");
                        tv6.setText("ClassId");
                        tv7.setText("SectionId");



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(MainActivity.this, "" + volleyError, Toast.LENGTH_SHORT).show();
            }

        });

        requestQueue.add(jsonArrayRequest);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
