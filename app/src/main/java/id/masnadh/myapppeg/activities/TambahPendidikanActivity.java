package id.masnadh.myapppeg.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import id.masnadh.myapppeg.R;

public class TambahPendidikanActivity extends AppCompatActivity {

    EditText etJenjang,rtNamaSek,etProdi,etLulus;
    Button btnbatal,btnsimpan;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_pendidikan);
    }
}
