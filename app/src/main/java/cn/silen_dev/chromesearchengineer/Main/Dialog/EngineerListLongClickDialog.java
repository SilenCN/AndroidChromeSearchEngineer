package cn.silen_dev.chromesearchengineer.Main.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import cn.silen_dev.chromesearchengineer.Chrome.Engineer.Model.ShortEngineer;
import cn.silen_dev.chromesearchengineer.R;
import cn.silen_dev.chromesearchengineer.Sqlite.SearchEngineerDB;

/**
 * Created by silen on 16-11-23.
 */

public class EngineerListLongClickDialog extends DialogFragment {
    ShortEngineer shortEngineer;
    Context context;
    Handler handler;
    public EngineerListLongClickDialog(ShortEngineer shortEngineer,Context context,Handler handler) {
        super();
        this.shortEngineer = shortEngineer;
        this.context=context;
        this.handler=handler;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        final AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setTitle(shortEngineer.getShort_name());
        ListView listView=new ListView(getContext());
        listView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        ArrayAdapter adapter=new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.EngineerListLongClickOperate));
        listView.setAdapter(adapter);
        builder.setView(listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:new DeleteEngineerDialog(shortEngineer,handler).show(getActivity().getSupportFragmentManager(),null);dismiss();break;
                    case 1:
                        Toast.makeText(getContext(),"暂不开放",Toast.LENGTH_SHORT).show();dismiss();
                        //new ShareEngineerDialog(shortEngineer,context).show(getActivity().getSupportFragmentManager(),null);
                        break;
                    default:break;
                }
            }
        });
        return builder.create();
    }
}
