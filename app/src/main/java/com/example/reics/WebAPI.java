package com.example.reics;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class WebAPI {

    public ArrayList<String> mArrayCity = new ArrayList<String>();
    public ArrayList<String> mArrayCityCode = new ArrayList<String>();
    public ArrayList<String> mArrayCityName = new ArrayList<String>();

    public String mRet = new String();

    private static final String TAG = "WebAPI";

//    public void requestWeblandArea(Context context, String key) {
//
//        String id = new String(key);
//        String url = "https://www.land.mlit.go.jp/webland/api/CitySearch?area=" + id;
//
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,url,null,
//                new Response.Listener<JSONArray>() {
//
//                    @Override
//                    public void onResponse(JSONArray response) {
//
//                        try {
//                            mRet = response.getString(0);
//                            mArrayCityCode.add(response.getJSONArray(1).getJSONObject(0).getString("id"));
//                            mArrayCityName.add(response.getJSONArray(1).getJSONObject(0).getString("name"));
////                            mRet = response.getString("status");
////                            mArrayCityCode.add(response.getJSONArray("data").getJSONObject(0).getString("id"));
////                            mArrayCityName.add(response.getJSONArray("data").getJSONObject(0).getString("name"));
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.d(TAG,error.toString());
//                    }
//                }
//        );
//        MySingleton.getInstance(context).addToRequestQueue(jsonArrayRequest);
//    }
}
