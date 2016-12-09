package cn.silen_dev.chromesearchengineer.Main.Dialog;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import cn.silen_dev.chromesearchengineer.Chrome.Engineer.Model.ShortEngineer;
import cn.silen_dev.chromesearchengineer.Http.HttpTask;
import cn.silen_dev.chromesearchengineer.R;

/**
 * Created by silen on 16-11-23.
 */

public class ShareEngineerDialog extends DialogFragment {
    ShortEngineer shortEngineer;
    Context context;

    public ShareEngineerDialog(ShortEngineer shortEngineer,Context context) {
        super();
        this.shortEngineer = shortEngineer;
        this.context=context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.share_dialog_title);
        builder.setMessage(String.format(getString(R.string.share_dialog_message), new Object[]{shortEngineer.getShort_name(), shortEngineer.getKeyword(), shortEngineer.getUrl(), shortEngineer.getInput_encodings()}));
        builder.setPositiveButton(R.string.share_dialog_title, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                final ProgressDialog progressDialog = new ProgressDialog(getContext());
                progressDialog.setMessage(getString(R.string.share_progress_message));
                progressDialog.show();
                HttpTask httpTask = null;
                try {
                   //httpTask = new HttpTask("http://192.168.2.86:8080/Engineer/PushEngineer","Engineer=" + URLEncoder.encode(new Gson().toJson(shortEngineer).trim(),"UTF-8"));
                   httpTask = new HttpTask("http://silen.ren/ChromeSearchEngineer/Engineer/PushEngineer","Engineer=" + URLEncoder.encode(new Gson().toJson(shortEngineer).trim(),"UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                httpTask.setOnHttpFinish(new HttpTask.OnHttpFinish() {
                    @Override
                    public void result(String result) {
                        progressDialog.dismiss();
                        if ("true".equals(result.trim()))
                            Toast.makeText(context, getString(R.string.share_result_success), Toast.LENGTH_LONG).show();
                        else {
                            Toast.makeText(context, getString(R.string.share_result_error), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void error() {
                        progressDialog.dismiss();
                        Toast.makeText(context, getString(R.string.share_result_error), Toast.LENGTH_LONG).show();
                    }
                });
                httpTask.start();
            }
        });
        return builder.create();
    }
}
