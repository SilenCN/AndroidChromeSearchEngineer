package cn.silen_dev.chromesearchengineer.Chrome.Package.Dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import cn.silen_dev.chromesearchengineer.Chrome.Package.Model.Chrome;
import cn.silen_dev.chromesearchengineer.Chrome.Package.Server.ChromePackageHelper;
import cn.silen_dev.chromesearchengineer.R;
import cn.silen_dev.chromesearchengineer.Shell.ShellThread;

/**
 * Created by silen on 16-11-20.
 */

public class SelectChromeDialog extends DialogFragment {
    Context context;
    Handler handler;

    public SelectChromeDialog() {
        this(null,null);
    }

    @SuppressLint("ValidFragment")
    public SelectChromeDialog(Context context, Handler handler) {
        super();
        this.context = context;
        this.handler=handler;
        this.setCancelable(false);

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.choose_chrome_dialog_title);
        ListView listview = new ListView(context);
        listview.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        listview.setPadding(20, 20, 20, 20);
        ArrayAdapter adapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1);
        for (Chrome chrome : ChromePackageHelper.getList()) {
            adapter.add(chrome.getName());
        }
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedChrome(i);
            }
        });
        builder.setView(listview);
        return builder.create();
    }

    public void selectedChrome(int position) {
        ChromePackageHelper.setChoose_chrome_position(position);
        ShellThread shellThread = new ShellThread(ChromePackageHelper.getChrome().getPackageName());
        shellThread.setOnTaskFinish(new ShellThread.OnTaskFinish() {
            @Override
            public void finish() {
                handler.sendMessage(new Message());
            }
        });
        shellThread.start();
        dismiss();
    }
}
