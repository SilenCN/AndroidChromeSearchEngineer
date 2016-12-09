package cn.silen_dev.chromesearchengineer;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import cn.silen_dev.chromesearchengineer.Chrome.Engineer.Model.ShortEngineer;
import cn.silen_dev.chromesearchengineer.Chrome.Engineer.Server.EngineerServer;
import cn.silen_dev.chromesearchengineer.Chrome.Package.Server.ChromePackageHelper;
import cn.silen_dev.chromesearchengineer.Chrome.Package.Task.ChromePackageTask;
import cn.silen_dev.chromesearchengineer.Main.Dialog.EngineerListLongClickDialog;
import cn.silen_dev.chromesearchengineer.Shell.ShellThread;
import cn.silen_dev.chromesearchengineer.Sqlite.SearchEngineerDB;

public class StartActivity extends AppCompatActivity {

    ListView engieerListView;
    ArrayAdapter adapter;
    Handler handler;
    FloatingActionButton floatingActionButton;
    static int startOnce = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        engieerListView = (ListView) findViewById(R.id.mainEngineerList);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                new SearchEngineerDB().getAllEngineer();
                for (ShortEngineer shortEngineer : EngineerServer.getShortEngineerList()) {
                    adapter.add(shortEngineer.getShort_name() + "(" + shortEngineer.getKeyword() + ")");
                }
                engieerListView.setAdapter(adapter);
                getSupportActionBar().setTitle(ChromePackageHelper.getChrome().getName());
            }
        };
        new ChromePackageTask(this, getSupportFragmentManager(), handler).execute();

        engieerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    Toast.makeText(StartActivity.this, getResources().getString(R.string.google_default_search_engineer_toast), Toast.LENGTH_LONG).show();
                } else {
                    EngineerServer.shortEngineer = EngineerServer.getShortEngineerList().get(i);
                    EngineerServer.create = false;
                    startActivity(new Intent(StartActivity.this, ModifyActivity.class));
                }
            }
        });
        engieerListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                new EngineerListLongClickDialog(EngineerServer.getShortEngineerList().get(i),StartActivity.this).show(getSupportFragmentManager(),null);
                return true;
            }
        });
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShortEngineer engineer = new ShortEngineer();

                engineer.setId(EngineerServer.getShortEngineerList().get(EngineerServer.getShortEngineerList().size() - 1).getId());
                EngineerServer.create = true;
                EngineerServer.shortEngineer = engineer;
                startActivity(new Intent(StartActivity.this, ModifyActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_donate) {
            new AliPaySupport(StartActivity.this).show(getSupportFragmentManager(), null);
            return true;
        } else if (id == R.id.action_about) {
            new AboutDialog(StartActivity.this).show(getSupportFragmentManager(),null);
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (startOnce > 1) {
            adapter.clear();
            adapter.notifyDataSetChanged();
/*
            ShellThread shellThread = new ShellThread(ChromePackageHelper.getChrome().getPackageName());
            shellThread.setOnTaskFinish(new ShellThread.OnTaskFinish() {
                @Override
                public void finish() {

                }
            });
            shellThread.start();
            */
            handler.sendMessage(new Message());
        }
        startOnce++;
    }

    @Override
    public void onBackPressed() {
        EngineerServer.shortEngineerList = null;
        ChromePackageHelper.list = null;
        startOnce = 0;

        finish();
    }
    @SuppressLint("ValidFragment")
   public static class AliPaySupport extends DialogFragment {
        Context context;

        public AliPaySupport(Context context) {
            super();
            this.context = context;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            super.onCreateDialog(savedInstanceState);
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            cmb.setText(getResources().getString(R.string.donate_alipay_count));
            builder.setTitle(R.string.donate_alipay_text);
            builder.setMessage(R.string.donate_alipay_dialogTV);
            builder.setPositiveButton(R.string.donate_alipay_openAlipay, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface p1, int p2) {
                    startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://ds.alipay.com/?from=mobilecodec&scheme=alipayqr%3A%2F%2Fplatformapi%2Fstartapp%3FsaId%3D10000007%26clientVersion%3D3.7.0.0718%26qrcode%3Dhttps%253A%252F%252Fqr.alipay.com%252Fapx05582lqz2lsejkj6r5c9%253F_s%253Dweb-other")));
                }
            });
            return builder.create();
        }

    }
    @SuppressLint("ValidFragment")
   public static class AboutDialog extends DialogFragment {
        Context context;

        public AboutDialog(Context context) {
            super();
            this.context = context;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            super.onCreateDialog(savedInstanceState);
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            cmb.setText(getResources().getString(R.string.donate_alipay_count));
            builder.setTitle(R.string.about);
            builder.setMessage(R.string.about_content);
            builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface p1, int p2) {

                }
            });
            return builder.create();
        }

    }
}
