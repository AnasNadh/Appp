package id.masnadh.myapppeg.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import id.masnadh.myapppeg.R;
import id.masnadh.myapppeg.connections.AppController;
import id.masnadh.myapppeg.connections.Server;
import id.masnadh.myapppeg.models.PendidikanModel;

public class TambahPendidikanActivity extends AppCompatActivity {

    EditText etJenjang, etNamaSek, etProdi, etLulus;
    Button btnbatal,btnsimpan;
    ProgressDialog pd;
    public final static String TAG_ID = "id";
    List<PendidikanModel> mItems;

    SharedPreferences sharedpreferences;
    Boolean session = false;

    String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_pendidikan);

        etJenjang = (EditText) findViewById(R.id.inp_jenjang);
        etNamaSek = (EditText) findViewById(R.id.inp_nama_sekolah);
        etProdi = (EditText) findViewById(R.id.inp_prodi);
        etLulus = (EditText) findViewById(R.id.inp_lulus);
        btnbatal = (Button) findViewById(R.id.btn_cancel_pend);
        btnsimpan = (Button) findViewById(R.id.btn_simpan_pend);
        pd = new ProgressDialog(TambahPendidikanActivity.this);

        btnsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpanData();
            }
        });

        btnbatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main = new Intent(TambahPendidikanActivity.this, PendidikanActivity.class);
                startActivity(main);
            }
        });
    }

    private void simpanData() {
//        pd.setMessage("Menyimpan Data");
//        pd.setCancelable(false);
//        pd.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.URL_INSERT_PEND, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
   //             pd.cancel();

//                for(int i = 0 ; i < response.length(); i++){
//                Log.d("volley","response : " + response.toString());
//
                try {
                    JSONObject jsonObject = new JSONObject(response);
                                           PendidikanModel pm = new PendidikanModel();
//
                        int code = Integer.parseInt(jsonObject.getString("code"));
                        if (code == 1)
                        {
                            daftarBerhasil();
                        }else if(code == 0)
                        {
                            daftarGAgal();
                        }
//                        int extraId = Integer.parseInt(getIntent().getStringExtra(TAG_ID));
//                        int id = jsonObject.getInt("id_peg");
////                        String ktp = jsonObject.getString("tingkat");
////                        String tempat = jsonObject.getString("nama_sekolah");
////                        String tanggal = jsonObject.getString("jurusan");
////                        String agama = jsonObject.getString("thn_lulus");
////
//                        if ( extraId == id) {
////
////                            etJenjang.setText(ktp);
////                            etNamaSek.setText(tempat);
////                            etProdi.setText(tanggal);
////                            etLulus.setText(agama);
//                            pm.setJenjang(jsonObject.getString("tingkat"));
//                            pm.setNamaSek(jsonObject.getString("nama_sekolah"));
//                            pm.setProdi(jsonObject.getString("jurusan"));
//                            pm.setLulus(jsonObject.getString("thn_lulus"));
//                            mItems.add(pm);
//                        }

//                }

                    Toast.makeText(TambahPendidikanActivity.this, "pesan : "+ jsonObject.getString("message"), Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }


                //startActivity(new Intent(TambahPendidikanActivity.this, PendidikanActivity.class));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.cancel();
                Toast.makeText(TambahPendidikanActivity.this, "pesan : Gagal Insert Data", Toast.LENGTH_SHORT).show();
            }



        })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map = new HashMap<>();
                map.put("tingkat",etJenjang.getText().toString());
                map.put("nama_sekolah",etNamaSek.getText().toString());
                map.put("jurusan",etProdi.getText().toString());
                map.put("thn_lulus",etLulus.getText().toString());

                return map;

            }
        };

        AppController.getInstance().addToRequestQueue(stringRequest);
    }

    public void daftarBerhasil()
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert
                .setMessage("Pendaftaran Berhasil")
                .setCancelable(false)
                .setPositiveButton("Login", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        sharedpreferences = getSharedPreferences(LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);
                        session = sharedpreferences.getBoolean(LoginActivity.session_status, false);

                        id = sharedpreferences.getString(TAG_ID, id);

                        if(session) {
                            Intent sukses = new Intent(TambahPendidikanActivity.this, PendidikanActivity.class);
                            sukses.putExtra(TAG_ID, id);
                            finish();
                            startActivity(sukses);
                        }

//                        Intent login = new Intent(TambahPendidikanActivity.this, PendidikanActivity.class);
//                        finish();
//                        startActivity(login);

                    }
                });

        AlertDialog berhasil = alert.create();
        berhasil.show();
    }
    public void daftarGAgal()
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert
                .setMessage("Pendaftaran Gagal")
                .setCancelable(false)
                .setNegativeButton("Ulangi", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        recreate();
                    }
                });

        AlertDialog berhasil = alert.create();
        berhasil.show();
    }

}
