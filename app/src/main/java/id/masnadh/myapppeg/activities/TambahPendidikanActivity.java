package id.masnadh.myapppeg.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import id.masnadh.myapppeg.R;
import id.masnadh.myapppeg.connections.AppController;
import id.masnadh.myapppeg.connections.Server;

public class TambahPendidikanActivity extends AppCompatActivity {

    EditText etJenjang, etNamaSek, etProdi, etLulus;
    Button btnbatal,btnsimpan;
    ProgressDialog pd;

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
               // pd.cancel();
                try {
                    JSONObject jsonObject = new JSONObject(response);
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
        }){
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
}
