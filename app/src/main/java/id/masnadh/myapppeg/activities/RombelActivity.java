package id.masnadh.myapppeg.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import id.masnadh.myapppeg.R;

public class RombelActivity extends AppCompatActivity {

    public static final String EXTRA_MENU = "extra_menu_rombel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rombel);

        Intent intent = getIntent();

        Parcelable[] dashboard = intent.getParcelableArrayExtra(EXTRA_MENU);
    }
}
