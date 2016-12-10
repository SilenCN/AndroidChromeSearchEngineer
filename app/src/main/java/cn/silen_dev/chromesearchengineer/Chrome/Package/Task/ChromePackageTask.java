package cn.silen_dev.chromesearchengineer.Chrome.Package.Task;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;

import java.util.List;

import cn.silen_dev.chromesearchengineer.Chrome.Package.Dialog.SelectChromeDialog;
import cn.silen_dev.chromesearchengineer.Chrome.Package.Model.Chrome;
import cn.silen_dev.chromesearchengineer.Chrome.Package.Server.ChromePackageHelper;
import cn.silen_dev.chromesearchengineer.R;
import cn.silen_dev.chromesearchengineer.Shell.ShellThread;

/**
 * Created by silen on 16-11-20.
 */

public class ChromePackageTask extends AsyncTask<String, Integer, String> {
    int le = 0;
    ProgressDialog pdialog;
    PackageManager packageManager;
    Context context;
    FragmentManager fragmentManager;
    Handler handler;

    public ChromePackageTask(Context context, FragmentManager fragmentManager, Handler handler) {
        this.context = context;
        pdialog = new ProgressDialog(context, 0);
        pdialog.setCanceledOnTouchOutside(false);

        pdialog.setTitle(R.string.chrome_package_loadtask_title);
        pdialog.setMessage(context.getResources().getString(R.string.chrome_package_loadtask_message));

        pdialog.setCancelable(true);

        pdialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pdialog.show();
        packageManager = context.getPackageManager();
        this.fragmentManager = fragmentManager;
        this.handler = handler;
    }

    @Override
    protected String doInBackground(String[] p1) {

        List<PackageInfo> list = packageManager.getInstalledPackages(0);
        pdialog.setMax(list.size());
        String[] chromeArray = context.getResources().getStringArray(R.array.chrome_package);
        int i = 0;
        for (PackageInfo info : list) {
                for (String chromePackage : chromeArray) {
                    if (info.applicationInfo.packageName.toLowerCase().equals(chromePackage.toLowerCase())) {
                        Chrome chrome = new Chrome();
                        chrome.setName(info.applicationInfo.loadLabel(packageManager).toString());
                        chrome.setPackageName(info.applicationInfo.packageName);
                        chrome.setIcon(info.applicationInfo.loadIcon(packageManager));
                        ChromePackageHelper.addChromePackage(chrome);
                    }
                }
            publishProgress(++i);
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        pdialog.dismiss();
        if (ChromePackageHelper.getList().size() > 1) {
            SelectChromeDialog selectChromeDialog = new SelectChromeDialog(context,handler);
            selectChromeDialog.setCancelable(false);
            selectChromeDialog.show(fragmentManager, null);
        } else {
            ChromePackageHelper.setChoose_chrome_position(0);
            ShellThread shellThread = new ShellThread(ChromePackageHelper.getChrome().getPackageName());
            shellThread.setOnTaskFinish(new ShellThread.OnTaskFinish() {
                @Override
                public void finish() {
                    handler.sendMessage(new Message());
                }
            });
            shellThread.start();
        }
        //new ShellThread().start();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Integer[] values) {
        super.onProgressUpdate(values);
        pdialog.setProgress(values[0]);
    }
}