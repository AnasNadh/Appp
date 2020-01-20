package id.masnadh.myapppeg.fragments;


import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import id.masnadh.myapppeg.R;
import id.masnadh.myapppeg.connections.Server;
import id.masnadh.myapppeg.models.AnakModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class AnakFragment extends Fragment {

    FloatingActionButton fab;
    ListView list;
    SwipeRefreshLayout swipe;
    List<AnakModel> itemList = new ArrayList<>();
    Adapter adapter;
    int success;
    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;
    EditText txt_id, txt_nama, txt_alamat;
    String id, nama, alamat;

    private static final String TAG = AnakFragment.class.getSimpleName();

    private static String url_select     = Server.URL + "select.php";
    private static String url_insert     = Server.URL + "insert.php";
    private static String url_edit       = Server.URL + "edit.php";
    private static String url_update     = Server.URL + "update.php";
    private static String url_delete     = Server.URL + "delete.php";

    public static final String TAG_ID       = "id";
    public static final String TAG_NAMA     = "nama";
    public static final String TAG_ALAMAT   = "alamat";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    String tag_json_obj = "json_obj_req";


    public AnakFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_anak, container,false);
        // Inflate the layout for this fragment

        // menghubungkan variablel pada layout dan pada java
        fab     = (FloatingActionButton) view.findViewById(R.id.fab_add);
        swipe   = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        list    = (ListView) view.findViewById(R.id.list);

        // untuk mengisi data dari JSON ke dalam adapter
//        adapter = new Adapter(AnakFragment.this.getActivity(), itemList);
//        list.setAdapter(adapter);

        // menamilkan widget refresh
//        swipe.setOnRefreshListener(this);
//
//        swipe.post(new Runnable() {
//                       @Override
//                       public void run() {
//                           swipe.setRefreshing(true);
//                           itemList.clear();
//                           adapter.notifyDataSetChanged();
//                           callVolley();
//                       }
//                   }
//        );
//
//        // fungsi floating action button memanggil form biodata
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DialogForm("", "", "", "SIMPAN");
//            }
//        });
        return view;
    }

}
