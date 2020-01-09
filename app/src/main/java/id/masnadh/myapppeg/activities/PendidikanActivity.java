package id.masnadh.myapppeg.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import id.masnadh.myapppeg.R;

public class PendidikanActivity extends AppCompatActivity {

    public static final String EXTRA_MENU = "extra_menu_pendidikan";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendidikan);

        Intent intent = getIntent();

        Parcelable[] dashboard = intent.getParcelableArrayExtra(EXTRA_MENU);
    }
}
