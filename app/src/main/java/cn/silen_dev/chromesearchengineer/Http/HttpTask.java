package cn.silen_dev.chromesearchengineer.Http;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by silen on 16-11-23.
 */

public class HttpTask extends Thread {
    Handler handler;
    OnHttpFinish onHttpFinish;
    String url;
    String parms;
    public HttpTask(String url,String parms) {
        super();
        this.url = url;
        this.parms=parms;

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 0) {
                    onHttpFinish.error();
                } else {
                    onHttpFinish.result((String) msg.obj);
                }
            }
        };
    }

    @Override
    public void run() {
        super.run();
        try {
            URL Url = new URL(url);
            System.out.println(url);
            HttpURLConnection connection = (HttpURLConnection)Url.openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(2000);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            OutputStream outputStream=connection.getOutputStream();
            outputStream.write(parms.getBytes());
            BufferedReader reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String result=null;
            String line=null;
            while (null!=(line=reader.readLine())){
                if (null==result){
                    result=line;
                }else {
                    result+="\n"+line;
                }
            }
            Log.i("HttpResult",result);
            Message message=new Message();
            message.what=1;
            message.obj=result;
            handler.sendMessage(message);
        } catch (Exception e) {
            Message message=new Message();
            message.what=0;
            handler.sendMessage(message);
        }
    }

    public interface OnHttpFinish {
        void result(String result);

        void error();
    }

    public void setOnHttpFinish(OnHttpFinish onHttpFinish) {
        this.onHttpFinish = onHttpFinish;
    }
}
