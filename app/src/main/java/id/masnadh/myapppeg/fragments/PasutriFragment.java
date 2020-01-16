package id.masnadh.myapppeg.fragments;


import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import id.masnadh.myapppeg.R;
import id.masnadh.myapppeg.models.PasutriModel;
import id.masnadh.myapppeg.models.PendidikanModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class PasutriFragment extends Fragment {

    String id;
    RecyclerView mRecyclerView;
    Button btnInsertPas, btnDeletePas;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mManager;
    List<PasutriModel> mItems;
    ProgressDialog pd;
    public final static String TAG_ID = "id";

    public PasutriFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pasutri, container,false);



        return view;
    }

}
