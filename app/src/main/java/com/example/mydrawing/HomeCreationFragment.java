package com.example.mydrawing;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.mydrawing2.VideoSelectionActivity2;

/**
 * 创作fragment
 * Created by Jerry on 2019/4/6.
 */

public class HomeCreationFragment extends Fragment implements View.OnClickListener {

    private LinearLayout buttons_rl;
    private ImageButton album_ref_ibtn;
    private ImageButton photograph_ref_ibtn;
    private ImageButton no_ref_ibtn;
    private ImageButton ten_s_ibtn;
    private ImageButton thirty_s_ibtn;
    private ImageButton sixty_s_ibtn,time_select_cancel;
    Intent intent;
    Bundle bundle;
    View timeSelectView;
    AlertDialog.Builder builder;
    AlertDialog dialog;
    String videosec = "10s";

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.creation_fragment, null);

        initview(view);
        bundle = new Bundle();
        return view;
    }

    private void initview(View view) {
        buttons_rl = view.findViewById(R.id.buttons_rl);
        album_ref_ibtn = view.findViewById(R.id.album_ref_ibtn);
        photograph_ref_ibtn = view.findViewById(R.id.photograph_ref_ibtn);
        no_ref_ibtn = view.findViewById(R.id.no_ref_ibtn);
        buttons_rl.setOnClickListener(this);
        album_ref_ibtn.setOnClickListener(this);
        photograph_ref_ibtn.setOnClickListener(this);
        no_ref_ibtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
//        if (isNetworkAvailable(getContext())) {
//            return;
//        }
        switch (view.getId()) {

            case R.id.buttons_rl:

                break;

            case R.id.album_ref_ibtn:
                Toast.makeText(getContext(), "相册", Toast.LENGTH_SHORT).show();
                showTimeSelection();
                break;

            case R.id.photograph_ref_ibtn:
                Toast.makeText(getContext(), "拍照", Toast.LENGTH_SHORT).show();
                showTimeSelection();
                break;

            case R.id.no_ref_ibtn:
                Toast.makeText(getContext(), "无参考", Toast.LENGTH_SHORT).show();
                intent = new Intent(getActivity(),VideoActivity.class);
                bundle = new Bundle();
                bundle.clear();

                showTimeSelection();
                break;
        }

    }

    void showTimeSelection(){
        if(builder==null){
            builder = new AlertDialog.Builder(getActivity());

        }
        if(timeSelectView==null){
            timeSelectView = LayoutInflater.from(getActivity()).inflate(R.layout.time_selection_layout, null);
            ten_s_ibtn = (ImageButton) timeSelectView.findViewById(R.id.ten_s_ibtn);
            thirty_s_ibtn = (ImageButton) timeSelectView.findViewById(R.id.thirty_s_ibtn);
            sixty_s_ibtn = (ImageButton) timeSelectView.findViewById(R.id.sixty_s_ibtn);
            time_select_cancel = (ImageButton) timeSelectView.findViewById(R.id.cancel_ibtn);


            ten_s_ibtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    videosec = "10s";
                    ten_s_ibtn.setImageResource(R.drawable.ten_s_selected);
                    thirty_s_ibtn.setImageResource(R.drawable.fifteen_s_unselect);
                    sixty_s_ibtn.setImageResource(R.drawable.thirty_s_unselect);
                    bundle.putString("record_sec", videosec);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    getActivity().finish();
                }
            });
            thirty_s_ibtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    videosec = "15s";
                    ten_s_ibtn.setImageResource(R.drawable.ten_s_unselect);
                    thirty_s_ibtn.setImageResource(R.drawable.fifteen_s_select);
                    sixty_s_ibtn.setImageResource(R.drawable.thirty_s_unselect);
                    bundle.putString("record_sec", videosec);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    getActivity().finish();
                }
            });
            sixty_s_ibtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    videosec = "30s";
                    ten_s_ibtn.setImageResource(R.drawable.ten_s_unselect);
                    thirty_s_ibtn.setImageResource(R.drawable.fifteen_s_unselect);
                    sixty_s_ibtn.setImageResource(R.drawable.thirty_s_selected);
                    bundle.putString("record_sec", videosec);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    getActivity().finish();
                }
            });
            time_select_cancel.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    dialog.dismiss();
                }
            });

            builder.setView(timeSelectView);
            dialog = builder.create();
        }
        dialog.show();

    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            Log.i("NetWorkState", "Unavailabel");
            return false;
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        Log.i("NetWorkState", "Availabel");
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
