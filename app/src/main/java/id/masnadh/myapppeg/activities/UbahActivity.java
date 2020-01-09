package id.masnadh.myapppeg.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.renderscript.Sampler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import id.masnadh.myapppeg.R;
import id.masnadh.myapppeg.connections.AppController;
import id.masnadh.myapppeg.connections.Server;

public class UbahActivity extends AppCompatActivity {

    public static final String EXTRA_MENU = "extra_menu_ubah";

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormat;

    String url = Server.URL +"pegawai.php";
    String url_update  = Server.URL+"update.php";

    final String TAG ="Edit";
    public final static String TAG_ID = "id";
    public final static String TAG_MESSAGE = "message";

    Spinner edKepeg, edNikah, edGolda, edJk, edAgama;
    TextView idUser,nipd,nisnUser,TvkodeKelas,Tvjurusan,Tvtgllahir,kelasAwal,thnmasuk;
    EditText etNama, etNip, etNuptk, etKtp, etTmpLhr, etTglLhr, etAlamat;

    Button updateData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah);
        editData();

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbarEdDaata);
        setSupportActionBar(toolbar);

        dateFormat = new SimpleDateFormat("dd-MM-yy", Locale.US);
        getSupportActionBar().setTitle("Ubah Data Pegawai");

        updateData = (Button)  findViewById(R.id.updateBtn);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpan();
            }
        });

        etTglLhr = (EditText) findViewById(R.id.edtTgl);
        etTglLhr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });

        etNama = (EditText) findViewById(R.id.edNama);
        etNip = (EditText) findViewById(R.id.edt_nip);
        etNuptk = (EditText) findViewById(R.id.edNuptk);
        etKtp = (EditText) findViewById(R.id.edKtp);
        etTmpLhr = (EditText) findViewById(R.id.edLahir);
        etTglLhr = (EditText) findViewById(R.id.edtTgl);
        etTmpLhr = (EditText) findViewById(R.id.edLahir);
        etAlamat = (EditText) findViewById(R.id.edtAlamat);
        edKepeg = (Spinner) findViewById(R.id.edStatusKepeg);
        edAgama = (Spinner) findViewById(R.id.edtAgama);
        edJk = (Spinner) findViewById(R.id.edtJk);
        edGolda = (Spinner) findViewById(R.id.edtGolDarah);
        edNikah = (Spinner) findViewById(R.id.edtNikah);


    }
    public void editData() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequests =
                new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray dataArray= new JSONArray(response);

                            for (int i =0; i<dataArray.length(); i++)
                            {


                                JSONObject obj = dataArray.getJSONObject(i);
                                int extraId = Integer.parseInt(getIntent().getStringExtra(TAG_ID));
                                String nama = obj.getString("nama");
                                int id = obj.getInt("id_peg");
                                String nip = obj.getString("nip");
                                String nuptk = obj.getString("nuptk");
                                String alamat = obj.getString("alamat");
                                String ktp = obj.getString("ktp");
                                String tempat = obj.getString("tempat_lhr");

                              if(extraId==id)
                                {

//                                    idUser.setText(id_peg);
//                                    EdnamaUser.setText(nama);
////                                    Edtmplahir.setText(tempatLahir);
////                                    Edtgllahir.setText(tanggalLahir);
////                                    TvkodeKelas.setText(kodekelas);
////                                    Tvjurusan.setText(jurusanS);

//                                    String code = obj.getString("code");
//                                    if (code.equals("sukses"))
//                                    {
//                                        ubahBerhasil();
//                                    }else if (code.equals("gagal"))
//                                    {
//                                        ubahGagal();
//                                    }
                                }
                            }
                            Log.d(TAG, "onResponse:" + response);
                        }  catch(
                                JSONException e)

                        {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        etNama.setText(error.getLocalizedMessage());
                    }
                });
        requestQueue.add(stringRequests);
    }

    private void simpan()
    {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_update, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject dataObj = new JSONObject(response);


                    Toast.makeText(UbahActivity.this, dataObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                    // adapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(UbahActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override

            protected Map<String,String> getParams() throws AuthFailureError {

                Map<String,String> map = new HashMap<>();

                //map.put("id_peg", idUser.getText().toString());
                map.put("nama", etNama.getText().toString());
                map.put("tempat_lahir", etTmpLhr.getText().toString());
                map.put("tanggal_lahir", etTglLhr.getText().toString());
                // params.put("tahun_nmasuk", thnmasuk.getText().toString());

                return map;
            }

        };

        AppController.getInstance().addToRequestQueue(stringRequest);
    }


    public void ubahBerhasil()
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert
                .setMessage("Perubahan Data Berhasil")
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent login = new Intent(UbahActivity.this, DataActivity.class);
                        finish();
                        startActivity(login);

                    }
                });

        AlertDialog berhasil = alert.create();
        berhasil.show();
    }
    public void ubahGagal()
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert
                .setMessage("Perubahan Data Belum Berhasil")
                .setCancelable(false)
                .setPositiveButton("Ulangi", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        recreate();

                    }
                });

        AlertDialog berhasil = alert.create();
        berhasil.show();
    }


    private void showDateDialog()
    {
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year,month,dayOfMonth);
                etTglLhr.setText(dateFormat.format(newDate.getTime()));
            }
        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH),newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
}
