package cn.silen_dev.chromesearchengineer.Shell;

import android.os.Handler;
import android.os.Message;

/**
 * Created by silen on 16-11-20.
 */

public class ShellWriteThread extends Thread {
    Handler handler;
    OnTaskFinish onTaskFinish;
    String packageName;
    public ShellWriteThread(String packageName) {
        super();
        this.packageName=packageName;
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                onTaskFinish.finish();
            }
        };
    }

    @Override
    public void run() {
        super.run();
        try {
            Command.getRoot();
            Command.write(packageName);
            handler.sendMessage(new Message());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setOnTaskFinish(OnTaskFinish onTaskFinish){
        this.onTaskFinish=onTaskFinish;
    }
    public interface OnTaskFinish{
        void finish();
    }
}
