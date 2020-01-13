package id.masnadh.myapppeg.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import id.masnadh.myapppeg.R;
import id.masnadh.myapppeg.adapters.PendidikanAdapter;
import id.masnadh.myapppeg.connections.AppController;
import id.masnadh.myapppeg.connections.Server;
import id.masnadh.myapppeg.models.PendidikanModel;

public class PendidikanActivity extends AppCompatActivity {

    public static final String EXTRA_MENU = "extra_menu_pendidikan";

    RecyclerView mRecyclerView;
    Button btnInsert, btnDelete;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mManager;
    List<PendidikanModel> mItems;
    ProgressDialog pd;
    public final static String TAG_ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendidikan);

        Intent intent = getIntent();

//        Parcelable[] dashboard = intent.getParcelableArrayExtra(EXTRA_MENU);

        mRecyclerView = (RecyclerView) findViewById(R.id.rvPend);
        btnInsert = (Button) findViewById(R.id.btn_insertPend);
        btnDelete = (Button) findViewById(R.id.btn_deletePend);
        mItems = new ArrayList<>();

        mManager = new LinearLayoutManager(PendidikanActivity.this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mManager);
        mAdapter = new PendidikanAdapter(mItems, PendidikanActivity.this);
        mRecyclerView.setAdapter(mAdapter);

        loadJson();

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PendidikanActivity.this,TambahPendidikanActivity.class);
                startActivity(intent);
            }
        });

    }

    private void loadJson() {

//        pd.setMessage("Mengambil Data");
//        pd.setCancelable(false);
//        pd.show();

        JsonArrayRequest reqData = new JsonArrayRequest(Request.Method.POST, Server.URL_PEND,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
//                        pd.cancel();
                        Log.d("volley","response : " + response.toString());
                        for(int i = 0 ; i < response.length(); i++)
                        {
                            try {
                                JSONObject data = response.getJSONObject(i);
                                PendidikanModel pm = new PendidikanModel();
                                int extraId = Integer.parseInt(getIntent().getStringExtra(TAG_ID));
                                //String nama = data.getString("nama");
                                int id = data.getInt("id_peg");
//                                pm.setJenjang(data.getString("tingkat"));
//                                pm.setNamaSek(data.getString("nama_sekolah"));
//                                pm.setProdi(data.getString("jurusan"));
//                                pm.setLulus(data.getString("thn_lulus"));


                                if ( extraId == id) {
                                    pm.setJenjang(data.getString("tingkat"));
                                    pm.setNamaSek(data.getString("nama_sekolah"));
                                    pm.setProdi(data.getString("jurusan"));
                                    pm.setLulus(data.getString("thn_lulus"));
                                    mItems.add(pm);
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        mAdapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Log.d("volley", "error : " + error.getMessage());
                    }
                });

        AppController.getInstance().addToRequestQueue(reqData);
    }

}
