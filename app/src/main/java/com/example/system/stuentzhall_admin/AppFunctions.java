package com.example.system.stuentzhall_admin;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Base64;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class AppFunctions {

    static String root_URL;//Fill with your hostname

    static Context c;

    public static String BitMapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

    public static Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0,
                    encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    public  static void populateList_student(final Context context , final ListView list){

        RequestQueue queue = Volley.newRequestQueue(context);
        final String url = root_URL+"fetch_data.php";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                            Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {


                    }
                });

        queue.add(jsonObjectRequest);

    }

    public static boolean add_student(final Context context , final String name , final String reg , final Bitmap img){

        RequestQueue queue = Volley.newRequestQueue(context);
        final String url = root_URL+"insert_data.php";

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        if(!TextUtils.equals(response,"saved successfully"))
                            Toast.makeText(context, "Unable to reach out the server.", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("name", name);
                params.put("reg", reg);
                params.put("barcode", BitMapToString(img));

                return params;
            }
        };
        queue.add(postRequest);

        return true;
    }

    public static void addDate(final Context context , final String date){

        RequestQueue queue = Volley.newRequestQueue(context);
        final String url = root_URL+"add_date.php";

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        if(!TextUtils.equals(response,"saved successfully"))
                            Toast.makeText(context, "Unable to reach out the server.", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("date", date);
                return params;
            }
        };
        queue.add(postRequest);

    }



    public static void server_sync_exam(final Context context , final ListView listView){

        RequestQueue queue = Volley.newRequestQueue(context);
        final String url = root_URL+"fetch_exam.php";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            ArrayList<String> list = new ArrayList<String>();
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(context , android.R.layout.simple_list_item_1,list);

                            for(int i=0;i<response.length();i++)
                                list.add(response.get(String.valueOf(i)).toString());

                            listView.setAdapter(adapter);
                            listView.deferNotifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {


                    }
                });

        queue.add(jsonObjectRequest);

    }

    public static void addHall(final Context context ,final String session){

        RequestQueue queue = Volley.newRequestQueue(context);
        final String url = root_URL+"add_hall.php";

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        if(!TextUtils.equals(response,"saved successfully"))
                            Toast.makeText(context, "Unable to reach out the server.", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Problem with connectivity.", Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("session", session);
                return params;
            }
        };
        queue.add(postRequest);

    }

    public static void server_sync_hall(final Context context , final ListView listView , final String date) {

        RequestQueue queue = Volley.newRequestQueue(context);
        final String url = root_URL + "fetch_hall.php";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {


                    }
                });

        queue.add(jsonObjectRequest);

    }


    }
