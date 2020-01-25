package id.masnadh.myapppeg.fragments;


import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;

import id.masnadh.myapppeg.R;
import id.masnadh.myapppeg.connections.Server;

public class ProfileFragment extends Fragment {

    String id;
    SharedPreferences sharedpreferences;
    //public final String deluser = Server.URL+"de.php";
    public final static String TAG = "Profile";
    public static final String TAG_ID = "id";
    public final static String TAG_IDU = "idu";
    public static final String TAG_LEVEL = "level";
    public static final String TAG_USERNAME = "username";

    public  static final int RequestPermissionCode  = 1 ;
    Button btnFoto;
    ImageView fotoProfile;
    Boolean session = false;
    final String ambilfoto = Server.URL+"siswa.php";
    final String gantifoto = Server.URL+"gantifoto.php";

    private String Document_img1="";
    Bitmap bitmap;
    ProgressDialog progressDialog;
    int PICK_IMAGE_REQUEST = 111;
    private RequestQueue requestQueue;
    private StringRequest stringRequest;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

}
