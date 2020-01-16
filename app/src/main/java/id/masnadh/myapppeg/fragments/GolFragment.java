package id.masnadh.myapppeg.fragments;


import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import id.masnadh.myapppeg.R;
import id.masnadh.myapppeg.connections.Server;

/**
 * A simple {@link Fragment} subclass.
 */
public class GolFragment extends Fragment {

    final String url = Server.URL+"jabatan.php";

    TextView tvJab,tvEse, tvTmt;
    //String id,idu;
    SharedPreferences sharedpreferences;
    public final static String TAG = DataFragment.class.getSimpleName() ;
    public final static String TAG_ID = "id";
    RequestQueue requestQueue;

    public GolFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_gol, container,false);

        tvJab = (TextView) view.findViewById(R.id.detail_jab);
//        tvEse = (TextView)  view.findViewById(R.id.detail_ese);
//        tvTmt = (TextView)  view.findViewById(R.id.detail_tmtJab);

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        StringRequest stringRequests = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    JSONArray dataArray = new JSONArray(response);

                    for (int i = 0; i < dataArray.length(); i++) {

                        JSONObject obj = dataArray.getJSONObject(i);

                        int extraId = Integer.parseInt(getActivity().getIntent().getStringExtra(TAG_ID));
                        int id = obj.getInt("id_peg");
                        String jabatan = obj.getString("jabatan_app");
                        String eselon = obj.getString("eselon");
                        String tmt = obj.getString("tmt_jabatan");

                        if ( extraId == id) {


                            tvJab.setText(jabatan);
//                            tvEse.setText(eselon);
//                            tvTmt.setText(tmt);

                        }
                    }

                    Log.d(TAG, "onResponse:" + response);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                tvJab.setText(error.getLocalizedMessage());
            }
        });

        requestQueue.add(stringRequests);

        return view;

    }

}
