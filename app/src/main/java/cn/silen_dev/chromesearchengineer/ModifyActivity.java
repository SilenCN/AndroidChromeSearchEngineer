package cn.silen_dev.chromesearchengineer;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import cn.silen_dev.chromesearchengineer.Chrome.Engineer.Server.EngineerServer;
import cn.silen_dev.chromesearchengineer.Chrome.Package.Model.Chrome;
import cn.silen_dev.chromesearchengineer.Chrome.Package.Server.ChromePackageHelper;
import cn.silen_dev.chromesearchengineer.Shell.ShellWriteThread;
import cn.silen_dev.chromesearchengineer.Sqlite.SearchEngineerDB;

public class ModifyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        getSupportActionBar().setTitle(EngineerServer.create ? getResources().getString(R.string.modify_activity_title_create) : EngineerServer.shortEngineer.getShort_name());
        try {
            getSupportActionBar().setDisplayOptions(android.support.v7.app.ActionBar.DISPLAY_HOME_AS_UP, android.support.v7.app.ActionBar.DISPLAY_HOME_AS_UP);
        } catch (Exception e) {
        }

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                checkDialog checkDialog=new checkDialog();
                checkDialog.show(getSupportFragmentManager(),null);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public static class checkDialog extends DialogFragment {
        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            super.onCreateDialog(savedInstanceState);
            final Activity activity=getActivity();
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle(R.string.modify_activity_check_dialog_title);
            builder.setMessage(getResources().getString(R.string.modify_activity_check_dialog_message));
            builder.setPositiveButton(R.string.modify_activity_check_dialog_ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (EngineerServer.create) {
                        new SearchEngineerDB().insertEngineer(EngineerServer.shortEngineer);
                    } else {
                        new SearchEngineerDB().updateEngineer(EngineerServer.shortEngineer);
                    }
                    ShellWriteThread shellWriteThread=new ShellWriteThread(ChromePackageHelper.getChrome().getPackageName());
                    shellWriteThread.setOnTaskFinish(new ShellWriteThread.OnTaskFinish() {
                        @Override
                        public void finish() {
                            activity.finish();
                        }
                    });
                    shellWriteThread.start();
                }
            });
            builder.setNegativeButton(R.string.modify_activity_check_dialog_no, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    getActivity().finish();
                }
            });

            return builder.create();
        }
    }

    @Override
    public void onBackPressed() {
        checkDialog checkDialog=new checkDialog();
        checkDialog.show(getSupportFragmentManager(),null);
    }
}
