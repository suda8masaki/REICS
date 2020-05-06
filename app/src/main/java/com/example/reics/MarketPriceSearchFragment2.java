package com.example.reics;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MarketPriceSearchFragment2 extends Fragment {

    private static final String TAG = "MarketPriceSF2";

    public JsonObjectRequest jsonObjectRequest;
    public String ret;
    public String areaCode, cityCode, yearMonthFr, yearMonthTo;
    public ArrayList<Data> arraySearchResult;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_market_price_result, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.button_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(MarketPriceSearchFragment2.this)
                        .navigate(R.id.action_Second2Fragment_to_First2Fragment);
            }
        });

        // get search condition
        SharedPreferences pref = getActivity().getSharedPreferences("search_condition", Context.MODE_PRIVATE);
        areaCode = pref.getString("AREA_CODE", "");
        cityCode = pref.getString("CITY_CODE", "");
        yearMonthFr = pref.getString("YEAR_MONTH_FR", "");
        yearMonthTo = pref.getString("YEAR_MONTH_TO", "");

        //get data from API
        requestWeblandTransactionPrice(areaCode, cityCode, yearMonthFr, yearMonthTo);

    }

    public void requestWeblandTransactionPrice(String areaCode, String cityCode, String yearMonthFr, String yearMonthTo) {

        String url = "https://www.land.mlit.go.jp/webland/api/TradeListSearch?";

        String param;
        param = "from=" + yearMonthFr;
        param = param + "&to=" + yearMonthTo;

        if (areaCode.equals("")) {
        } else {
            param = param + "&area=" + areaCode;
        }
        if (cityCode.equals("")) {
        } else {
            param = param + "&city=" + cityCode;
        }

        url = url + param;
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d(TAG, response.toString(2));

                            ret = response.getString("status");
                            if (ret.equals("OK")) {
                                arraySearchResult = new ArrayList<Data>();
                                for (int i = 0; i < response.getJSONArray("data").length(); i++) {
                                    Data data = new Data();
                                    //use if sentence because of handling null
                                    if (response.getJSONArray("data").getJSONObject(i).has("Type")) {
                                        data.setType(response.getJSONArray("data").getJSONObject(i).getString("Type"));
                                    }
                                    if (response.getJSONArray("data").getJSONObject(i).has("Region")) {
                                        data.setRegion(response.getJSONArray("data").getJSONObject(i).getString("Region"));
                                    }
                                    if (response.getJSONArray("data").getJSONObject(i).has("MunicipalityCode")) {
                                        data.setMunicipalityCode(response.getJSONArray("data").getJSONObject(i).getString("MunicipalityCode"));
                                    }
                                    if (response.getJSONArray("data").getJSONObject(i).has("Prefecture")) {
                                        data.setPrefecture(response.getJSONArray("data").getJSONObject(i).getString("Prefecture"));
                                    }
                                    if (response.getJSONArray("data").getJSONObject(i).has("Municipality")) {
                                        data.setMunicipality(response.getJSONArray("data").getJSONObject(i).getString("Municipality"));
                                    }
                                    if (response.getJSONArray("data").getJSONObject(i).has("DistrictName")) {
                                        data.setDistrictName(response.getJSONArray("data").getJSONObject(i).getString("DistrictName"));
                                    }
                                    if (response.getJSONArray("data").getJSONObject(i).has("TradePrice")) {
                                        data.setTradePrice(response.getJSONArray("data").getJSONObject(i).getString("TradePrice"));
                                    }
                                    if (response.getJSONArray("data").getJSONObject(i).has("PricePerUnit")) {
                                        data.setPricePerUnit(response.getJSONArray("data").getJSONObject(i).getString("PricePerUnit"));
                                    }
                                    if (response.getJSONArray("data").getJSONObject(i).has("FloorPlan")) {
                                        data.setFloorPlan(response.getJSONArray("data").getJSONObject(i).getString("FloorPlan"));
                                    }
                                    if (response.getJSONArray("data").getJSONObject(i).has("Area")) {
                                        data.setArea(response.getJSONArray("data").getJSONObject(i).getString("Area"));
                                    }
                                    if (response.getJSONArray("data").getJSONObject(i).has("UnitPrice")) {
                                        data.setUnitPrice(response.getJSONArray("data").getJSONObject(i).getString("UnitPrice"));
                                    }
                                    if (response.getJSONArray("data").getJSONObject(i).has("LandShape")) {
                                        data.setLandShape(response.getJSONArray("data").getJSONObject(i).getString("LandShape"));
                                    }
                                    if (response.getJSONArray("data").getJSONObject(i).has("Frontage")) {
                                        data.setFrontage(response.getJSONArray("data").getJSONObject(i).getString("Frontage"));
                                    }
                                    if (response.getJSONArray("data").getJSONObject(i).has("TotalFloorArea")) {
                                        data.setTotalFloorArea(response.getJSONArray("data").getJSONObject(i).getString("TotalFloorArea"));
                                    }
                                    if (response.getJSONArray("data").getJSONObject(i).has("BuildingYear")) {
                                        data.setBuildingYear(response.getJSONArray("data").getJSONObject(i).getString("BuildingYear"));
                                    }
                                    if (response.getJSONArray("data").getJSONObject(i).has("Structure")) {
                                        data.setStructure(response.getJSONArray("data").getJSONObject(i).getString("Structure"));
                                    }
                                    if (response.getJSONArray("data").getJSONObject(i).has("Use")) {
                                        data.setUse(response.getJSONArray("data").getJSONObject(i).getString("Use"));
                                    }
                                    if (response.getJSONArray("data").getJSONObject(i).has("Purpose")) {
                                        data.setPurpose(response.getJSONArray("data").getJSONObject(i).getString("Purpose"));
                                    }
                                    if (response.getJSONArray("data").getJSONObject(i).has("Direction")) {
                                        data.setDirection(response.getJSONArray("data").getJSONObject(i).getString("Direction"));
                                    }
                                    if (response.getJSONArray("data").getJSONObject(i).has("Classification")) {
                                        data.setClassification(response.getJSONArray("data").getJSONObject(i).getString("Classification"));
                                    }
                                    if (response.getJSONArray("data").getJSONObject(i).has("Breadth")) {
                                        data.setBreadth(response.getJSONArray("data").getJSONObject(i).getString("Breadth"));
                                    }
                                    if (response.getJSONArray("data").getJSONObject(i).has("CityPlanning")) {
                                        data.setCityPlanning(response.getJSONArray("data").getJSONObject(i).getString("CityPlanning"));
                                    }
                                    if (response.getJSONArray("data").getJSONObject(i).has("CoverageRation")) {
                                        data.setCoverageRatio(response.getJSONArray("data").getJSONObject(i).getString("CoverageRation"));
                                    }
                                    if (response.getJSONArray("data").getJSONObject(i).has("FloorAreaRatio")) {
                                        data.setFloorAreaRatio(response.getJSONArray("data").getJSONObject(i).getString("FloorAreaRatio"));
                                    }
                                    if (response.getJSONArray("data").getJSONObject(i).has("Period")) {
                                        data.setPeriod(response.getJSONArray("data").getJSONObject(i).getString("Period"));
                                    }
                                    if (response.getJSONArray("data").getJSONObject(i).has("Renovation")) {
                                        data.setRenovation(response.getJSONArray("data").getJSONObject(i).getString("Renovation"));
                                    }
                                    if (response.getJSONArray("data").getJSONObject(i).has("Remarks")) {
                                        data.setRemarks(response.getJSONArray("data").getJSONObject(i).getString("Remarks"));
                                    }
                                    arraySearchResult.add(data);
                                }
                            } else {
                            }
                            //custom ListAdapter to use Data type list
                            SearchListAdapter adapter = new SearchListAdapter(getActivity());
                            adapter.setSearchList(arraySearchResult);
                            ListView listView = (ListView) getActivity().findViewById(R.id.list_view);
                            listView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(),"Timeout or something error",Toast.LENGTH_SHORT).show();
                        Log.d(TAG, error.toString());
                    }
                }
        );
        MySingleton.getInstance(getActivity()).addToRequestQueue(jsonObjectRequest);
    }
}

class Data {
    //row id
    long id;
    //取引の種類
    private String type;
    //地区
    private String region;
    //市区町村コード
    private String municipalityCode;
    //都道府県名
    private String prefecture;
    //市区町村名
    private String municipality;
    //地区名
    private String districtName;
    //取引価格（総額）
    private String tradePrice;
    //坪単価
    private String pricePerUnit;
    //間取り
    private String floorPlan;
    //面積（平方メートル）
    private String area;
    //取引価格（平方メートル単価）
    private String unitPrice;
    //土地の形状
    private String landShape;
    //間口
    private String frontage;
    //延床面積（平方メートル）
    private String totalFloorArea;
    //建築年
    private String buildingYear;
    //建物の構造
    private String structure;
    //用途
    private String use;
    //今後の利用目的
    private String purpose;
    //前面道路：方位
    private String direction;
    //前面道路：種類
    private String classification;
    //前面道路：幅員（m）
    private String breadth;
    //都市計画
    private String cityPlanning;
    //建ぺい率（%）
    private String coverageRatio;
    //容積率（%）
    private String floorAreaRatio;
    //取引時点
    private String period;
    //改装
    private String renovation;
    //取引の事情など
    private String remarks;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public void setType(String tmp) {
        this.type = tmp;
    }
    public String getType() {
        return type;
    }

    public void setRegion(String tmp) {
        this.region = tmp;
    }
    public String getRegion() {
        return region;
    }

    public void setMunicipalityCode(String tmp) {
        this.municipalityCode = tmp;
    }
    public String getMunicipalityCode() {
        return municipalityCode;
    }

    public void setPrefecture(String tmp) {
        this.prefecture = tmp;
    }
    public String getPrefecture() {
        return prefecture;
    }

    public void setMunicipality(String tmp) {
        this.municipality = tmp;
    }
    public String getMunicipality() {
        return municipality;
    }

    public void setDistrictName(String tmp) {
        this.districtName = tmp;
    }
    public String getDistrictName() {
        return districtName;
    }

    public void setTradePrice(String tmp) {
        this.tradePrice = tmp;
    }
    public String getTradePrice() {
        return tradePrice;
    }

    public void setPricePerUnit(String tmp) {
        this.pricePerUnit = tmp;
    }
    public String getPricePerUnit() {
        return pricePerUnit;
    }

    public void setFloorPlan(String tmp) {
        this.floorPlan = tmp;
    }
    public String getFloorPlan() {
        return floorPlan;
    }

    public void setArea(String tmp) {
        this.area = tmp;
    }
    public String getArea() {
        return area;
    }

    public void setUnitPrice(String tmp) {
        this.unitPrice = tmp;
    }
    public String getUnitPrice() {
        return unitPrice;
    }

    public void setLandShape(String tmp) {
        this.landShape = tmp;
    }
    public String getLandShape() {
        return landShape;
    }

    public void setFrontage(String tmp) {
        this.frontage = tmp;
    }
    public String getFrontage() {
        return frontage;
    }

    public void setTotalFloorArea(String tmp) {
        this.totalFloorArea = tmp;
    }
    public String getTotalFloorArea() {
        return totalFloorArea;
    }

    public void setBuildingYear(String tmp) {
        this.buildingYear = tmp;
    }
    public String getBuildingYear() {
        return buildingYear;
    }

    public void setStructure(String tmp) {
        this.structure = tmp;
    }
    public String getStructure() {
        return structure;
    }

    public void setUse(String tmp) {
        this.use = tmp;
    }
    public String getUse() {
        return use;
    }

    public void setPurpose(String tmp) {
        this.purpose = tmp;
    }
    public String getPurpose() {
        return purpose;
    }

    public void setDirection(String tmp) {
        this.direction = tmp;
    }
    public String getDirection() {
        return direction;
    }

    public void setClassification(String tmp) {
        this.classification = tmp;
    }
    public String getClassification() {
        return classification;
    }

    public void setBreadth(String tmp) {
        this.breadth = tmp;
    }
    public String getBreadth() {
        return breadth;
    }

    public void setCityPlanning(String tmp) {
        this.cityPlanning = tmp;
    }
    public String getCityPlanning() {
        return cityPlanning;
    }

    public void setCoverageRatio(String tmp) {
        this.coverageRatio = tmp;
    }
    public String getCoverageRatio() {
        return coverageRatio;
    }

    public void setFloorAreaRatio(String tmp) {
        this.floorAreaRatio = tmp;
    }
    public String getFloorAreaRatio() {
        return floorAreaRatio;
    }

    public void setPeriod(String tmp) {
        this.period = tmp;
    }
    public String getPeriod() {
        return period;
    }

    public void setRenovation(String tmp) {
        this.renovation = tmp;
    }
    public String getRenovation() {
        return renovation;
    }

    public void setRemarks(String tmp) {
        this.remarks = tmp;
    }
    public String getRemarks() {
        return remarks;
    }
}

class SearchListAdapter extends BaseAdapter {

    Context context;
    LayoutInflater layoutInflater = null;
    ArrayList<Data> data;

    public SearchListAdapter(Context context) {
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setSearchList(ArrayList<Data> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = layoutInflater.inflate(R.layout.search_result_item,parent,false);
        ((TextView) convertView.findViewById(R.id.textView1)).setText(data.get(position).getType());
        ((TextView) convertView.findViewById(R.id.textView2)).setText(data.get(position).getRegion());
        ((TextView) convertView.findViewById(R.id.textView3)).setText(data.get(position).getMunicipalityCode());
        ((TextView) convertView.findViewById(R.id.textView4)).setText(data.get(position).getPrefecture());
        ((TextView) convertView.findViewById(R.id.textView5)).setText(data.get(position).getMunicipality());
        ((TextView) convertView.findViewById(R.id.textView6)).setText(data.get(position).getDistrictName());
        ((TextView) convertView.findViewById(R.id.textView7)).setText(data.get(position).getTradePrice());
        ((TextView) convertView.findViewById(R.id.textView8)).setText(data.get(position).getPricePerUnit());
        ((TextView) convertView.findViewById(R.id.textView9)).setText(data.get(position).getFloorPlan());
        ((TextView) convertView.findViewById(R.id.textView10)).setText(data.get(position).getArea());
        ((TextView) convertView.findViewById(R.id.textView11)).setText(data.get(position).getUnitPrice());
        ((TextView) convertView.findViewById(R.id.textView12)).setText(data.get(position).getLandShape());
        ((TextView) convertView.findViewById(R.id.textView13)).setText(data.get(position).getFrontage());
        ((TextView) convertView.findViewById(R.id.textView14)).setText(data.get(position).getTotalFloorArea());
        ((TextView) convertView.findViewById(R.id.textView15)).setText(data.get(position).getBuildingYear());
        ((TextView) convertView.findViewById(R.id.textView16)).setText(data.get(position).getStructure());
        ((TextView) convertView.findViewById(R.id.textView17)).setText(data.get(position).getUse());
        ((TextView) convertView.findViewById(R.id.textView18)).setText(data.get(position).getPurpose());
        ((TextView) convertView.findViewById(R.id.textView19)).setText(data.get(position).getDirection());
        ((TextView) convertView.findViewById(R.id.textView20)).setText(data.get(position).getClassification());
        ((TextView) convertView.findViewById(R.id.textView21)).setText(data.get(position).getBreadth());
        ((TextView) convertView.findViewById(R.id.textView22)).setText(data.get(position).getCityPlanning());
        ((TextView) convertView.findViewById(R.id.textView23)).setText(data.get(position).getCoverageRatio());
        ((TextView) convertView.findViewById(R.id.textView24)).setText(data.get(position).getFloorAreaRatio());
        ((TextView) convertView.findViewById(R.id.textView25)).setText(data.get(position).getPeriod());
        ((TextView) convertView.findViewById(R.id.textView26)).setText(data.get(position).getRenovation());
        ((TextView) convertView.findViewById(R.id.textView27)).setText(data.get(position).getRemarks());

        return convertView;
    }
}