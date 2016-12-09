package cn.silen_dev.chromesearchengineer.Main.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import cn.silen_dev.chromesearchengineer.Chrome.Engineer.Model.ShortEngineer;
import cn.silen_dev.chromesearchengineer.R;

/**
 * Created by silen on 16-11-23.
 */

public class EngineerListLongClickDialog extends DialogFragment {
    ShortEngineer shortEngineer;
    Context context;
    public EngineerListLongClickDialog(ShortEngineer shortEngineer,Context context) {
        super();
        this.shortEngineer = shortEngineer;
        this.context=context;
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
                    case 0:break;
                    case 1:new ShareEngineerDialog(shortEngineer,context).show(getActivity().getSupportFragmentManager(),null);break;
                    default:break;
                }
            }
        });
        return builder.create();
    }
}
