package com.example.reics;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MarketPriceSearchFragment1 extends Fragment implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "MarketPriceSF1";
    private boolean isDefaultSelection;
    public Spinner spinner,spinnerCity,spinnerYearFr,spinnerMonthFr,spinnerYearTo,spinnerMonthTo;
    public ArrayList<String> arrayArea,arrayAreaCode,arrayAreaName,arrayCity,arrayCityCode,arrayCityName,arrayYear,arrayMonth,arrayMonthCode;
    public String ret;
    public JsonObjectRequest jsonObjectRequest;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        isDefaultSelection = true;
        return inflater.inflate(R.layout.fragment_market_price_search, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.button_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (spinner.getSelectedItem().equals("")) {
                    Toast.makeText(getActivity(),"Plase enter area",Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Toast.makeText(getActivity(),"Search",Toast.LENGTH_SHORT).show();
                    setPreference();
                    NavHostFragment.findNavController(MarketPriceSearchFragment1.this)
                            .navigate(R.id.action_First2Fragment_to_Second2Fragment);
                }
            }
        });

        // catch view contents
        spinner = (Spinner)getActivity().findViewById(R.id.spinner_area);
        spinnerCity = (Spinner) getActivity().findViewById(R.id.spinner_city);
        spinnerYearFr = (Spinner) getActivity().findViewById(R.id.spinner_year_from);
        spinnerMonthFr = (Spinner) getActivity().findViewById(R.id.spinner_month_from);
        spinnerYearTo = (Spinner) getActivity().findViewById(R.id.spinner_year_to);
        spinnerMonthTo = (Spinner) getActivity().findViewById(R.id.spinner_month_to);

        // create area spinner
        arrayArea = new ArrayList<String>();
        arrayAreaCode = new ArrayList<String>();
        arrayAreaName = new ArrayList<String>();
        createArea();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,arrayArea);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(this);

        //create city spinner (initialize)
        arrayCity = new ArrayList<String>();
        arrayCityCode = new ArrayList<String>();
        arrayCityName = new ArrayList<String>();

        arrayCity.add("");
        arrayCityCode.add("");
        arrayCityName.add("");
        ArrayAdapter<String> arrayAdapterCity = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,arrayCity);
        spinnerCity.setAdapter(arrayAdapterCity);

        // create year and month spinner
        arrayYear = new ArrayList<String>();
        arrayMonth = new ArrayList<String>();
        arrayMonthCode = new ArrayList<String>();
        createYearMonth();

        ArrayAdapter<String> arrayAdapterYear = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,arrayYear);
        ArrayAdapter<String> arrayAdapterMonth = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,arrayMonth);
        spinnerYearFr.setAdapter(arrayAdapterYear);
        spinnerYearTo.setAdapter(arrayAdapterYear);
        spinnerMonthFr.setAdapter(arrayAdapterMonth);
        spinnerMonthTo.setAdapter(arrayAdapterMonth);


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void createYearMonth() {
        arrayYear.clear();
        arrayMonth.clear();
        arrayMonthCode.clear();

        //year
        final DateFormat df = new SimpleDateFormat("yyyy");
        final Date d = new Date(System.currentTimeMillis());
        for (int i = Integer.parseInt(df.format(d)); i > 1970; i--) {
            arrayYear.add(String.valueOf(i));
        }

        //month
        arrayMonthCode.add("1");
        arrayMonthCode.add("2");
        arrayMonthCode.add("3");
        arrayMonthCode.add("4");
        arrayMonth.add("1 (1-3月)");
        arrayMonth.add("2 (4-6月)");
        arrayMonth.add("3 (7-10月)");
        arrayMonth.add("4 (11-12月)");

    }

    private void createArea() {

        //for not to display any area at first, add blank
        arrayArea.add("");
        arrayAreaCode.add("");
        arrayAreaName.add("");

        arrayAreaCode.add("01");
        arrayAreaCode.add("02");
        arrayAreaCode.add("03");
        arrayAreaCode.add("04");
        arrayAreaCode.add("05");
        arrayAreaCode.add("06");
        arrayAreaCode.add("07");
        arrayAreaCode.add("08");
        arrayAreaCode.add("09");
        arrayAreaCode.add("10");
        arrayAreaCode.add("11");
        arrayAreaCode.add("12");
        arrayAreaCode.add("13");
        arrayAreaCode.add("14");
        arrayAreaCode.add("15");
        arrayAreaCode.add("16");
        arrayAreaCode.add("17");
        arrayAreaCode.add("18");
        arrayAreaCode.add("19");
        arrayAreaCode.add("20");
        arrayAreaCode.add("21");
        arrayAreaCode.add("22");
        arrayAreaCode.add("23");
        arrayAreaCode.add("24");
        arrayAreaCode.add("25");
        arrayAreaCode.add("26");
        arrayAreaCode.add("27");
        arrayAreaCode.add("28");
        arrayAreaCode.add("29");
        arrayAreaCode.add("30");
        arrayAreaCode.add("31");
        arrayAreaCode.add("32");
        arrayAreaCode.add("33");
        arrayAreaCode.add("34");
        arrayAreaCode.add("35");
        arrayAreaCode.add("36");
        arrayAreaCode.add("37");
        arrayAreaCode.add("38");
        arrayAreaCode.add("39");
        arrayAreaCode.add("40");
        arrayAreaCode.add("41");
        arrayAreaCode.add("42");
        arrayAreaCode.add("43");
        arrayAreaCode.add("44");
        arrayAreaCode.add("45");
        arrayAreaCode.add("46");
        arrayAreaCode.add("47");

        arrayAreaName.add("北海道");
        arrayAreaName.add("青森県");
        arrayAreaName.add("岩手県");
        arrayAreaName.add("宮城県");
        arrayAreaName.add("秋田県");
        arrayAreaName.add("山形県");
        arrayAreaName.add("福島県");
        arrayAreaName.add("茨城県");
        arrayAreaName.add("栃木県");
        arrayAreaName.add("群馬県");
        arrayAreaName.add("埼玉県");
        arrayAreaName.add("千葉県");
        arrayAreaName.add("東京都");
        arrayAreaName.add("神奈川県");
        arrayAreaName.add("新潟県");
        arrayAreaName.add("富山県");
        arrayAreaName.add("石川県");
        arrayAreaName.add("福井県");
        arrayAreaName.add("山梨県");
        arrayAreaName.add("長野県");
        arrayAreaName.add("岐阜県");
        arrayAreaName.add("静岡県");
        arrayAreaName.add("愛知県");
        arrayAreaName.add("三重県");
        arrayAreaName.add("滋賀県");
        arrayAreaName.add("京都府");
        arrayAreaName.add("大阪府");
        arrayAreaName.add("兵庫県");
        arrayAreaName.add("奈良県");
        arrayAreaName.add("和歌山県");
        arrayAreaName.add("鳥取県");
        arrayAreaName.add("島根県");
        arrayAreaName.add("岡山県");
        arrayAreaName.add("広島県");
        arrayAreaName.add("山口県");
        arrayAreaName.add("徳島県");
        arrayAreaName.add("香川県");
        arrayAreaName.add("愛媛県");
        arrayAreaName.add("高知県");
        arrayAreaName.add("福岡県");
        arrayAreaName.add("佐賀県");
        arrayAreaName.add("長崎県");
        arrayAreaName.add("熊本県");
        arrayAreaName.add("大分県");
        arrayAreaName.add("宮崎県");
        arrayAreaName.add("鹿児島県");
        arrayAreaName.add("沖縄県");

        for(int i = 1; i < arrayAreaCode.size(); i++) {
            arrayArea.add(arrayAreaCode.get(i) + ":" + arrayAreaName.get(i));
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //"onItemSelected" event is occurred also when initialization, so we use "isDefaultSelection" and check default motion or user operation
        if (isDefaultSelection == true) {
            isDefaultSelection = false;
        } else {
            int index = arrayArea.indexOf(parent.getSelectedItem().toString());
            String key = new String(arrayAreaCode.get(index));
            requestWeblandCity(key);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        new AlertDialog.Builder(getActivity()).setTitle("title").setMessage("onNothingSelected").setPositiveButton("OK",null).show();
    }

    public void requestWeblandCity(String key) {

        String id = new String(key);
        String url = "https://www.land.mlit.go.jp/webland/api/CitySearch?area=" + id;

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d(TAG, response.toString(2));

                            ret = response.getString("status");
                            arrayCityCode.clear();
                            arrayCityName.clear();
                            arrayCity.clear();
                            if (ret.equals("OK")) {
                                arrayCityCode.add("");
                                arrayCityName.add("");
                                arrayCity.add("");
                                for (int i = 0; i < response.getJSONArray("data").length(); i++) {
                                    arrayCityCode.add(response.getJSONArray("data").getJSONObject(i).getString("id"));
                                    arrayCityName.add(response.getJSONArray("data").getJSONObject(i).getString("name"));
                                    arrayCity.add(arrayCityCode.get(i+1) + ":" + arrayCityName.get(i+1));
                                }
                            } else {
                            }
                            ArrayAdapter<String> arrayAdapterCity = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,arrayCity);

                            spinnerCity.setAdapter(arrayAdapterCity);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(),"Timeout or something error",Toast.LENGTH_SHORT).show();
                        Log.d(TAG,error.toString());
                    }
                }
        );
        MySingleton.getInstance(getActivity()).addToRequestQueue(jsonObjectRequest);
    }

    public void setPreference() {
        //save search condition in preference
        //TODO null handling
        SharedPreferences pref = getActivity().getSharedPreferences("search_condition",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();

        editor.putString("AREA_CODE",arrayAreaCode.get(
                arrayArea.indexOf(spinner.getSelectedItem().toString())
                )
        );
        editor.putString("CITY_CODE",arrayCityCode.get(
                arrayCity.indexOf(spinnerCity.getSelectedItem().toString())
                )
        );
        editor.putString("YEAR_FR",arrayYear.get(
                arrayYear.indexOf(spinnerYearFr.getSelectedItem().toString())
                )
        );
        editor.putString("MONTH_FR",arrayMonthCode.get(
                arrayMonth.indexOf(spinnerMonthFr.getSelectedItem().toString())
                )
        );
        editor.putString("YEAR_TO",arrayYear.get(
                arrayYear.indexOf(spinnerYearTo.getSelectedItem().toString())
                )
        );
        editor.putString("MONTH_TO",arrayMonthCode.get(
                arrayMonth.indexOf(spinnerMonthTo.getSelectedItem().toString())
                )
        );
        editor.putString("YEAR_MONTH_FR",arrayYear.get(
                arrayYear.indexOf(spinnerYearFr.getSelectedItem().toString())
                ) + arrayMonthCode.get(
                arrayMonth.indexOf(spinnerMonthFr.getSelectedItem().toString())
                )
        );
        editor.putString("YEAR_MONTH_TO",arrayYear.get(
                arrayYear.indexOf(spinnerYearTo.getSelectedItem().toString())
                ) + arrayMonthCode.get(
                arrayMonth.indexOf(spinnerMonthTo.getSelectedItem().toString())
                )
        );

        editor.commit();

    }

}