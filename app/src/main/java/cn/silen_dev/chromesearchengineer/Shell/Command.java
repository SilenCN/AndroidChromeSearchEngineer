package cn.silen_dev.chromesearchengineer.Shell;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import cn.silen_dev.chromesearchengineer.AppConf;

/**
 * Created by silen on 16-11-20.
 */

public class Command {
    public static Process process;
    public static InputStream inputStream;
    public static OutputStream outputStream;

    public static void getRoot() throws IOException {
        process = Runtime.getRuntime().exec("su");
    }

    public static void copyDatabase(String packageName) throws IOException, InterruptedException {
        File file = new File(AppConf.SAVE_PATH);

        if (!file.exists()) {
            file.mkdir();
        }
        if (new File(file.getPath() + "/Web Data").exists()) {
            new File(file.getPath() + "/Web Data").delete();
        }
        String command = "cp \"/data/data/" + packageName + "/app_chrome/Default/Web Data\" \"" + file.getPath() + "/\"";
        System.out.println(command);
        outputStream = process.getOutputStream();
        outputStream.write(command.getBytes());
        outputStream.flush();
        outputStream.close();
        process.waitFor();
        inputStream = process.getInputStream();
        while (inputStream.read() != -1) ;
        inputStream.close();
    }

    public static void write(String packageName) throws IOException, InterruptedException {
        String command = "rm -f \"/data/data/" + packageName + "/app_chrome/Default/Web Data\"";
/*        shell(command);
        getRoot();*/
        command = "cp -f \"" + AppConf.SAVE_PATH + "/Web Data\"" + " /data/data/" + packageName + "/app_chrome/Default/";
        shell(command);
        getRoot();
        shell("rm -f \"/data/data/" + packageName + "/app_chrome/Local State\"");
        inputStream = process.getInputStream();
        while (inputStream.read() != -1) ;
        inputStream.close();
    }

    private static void shell(String command) throws IOException, InterruptedException {
        outputStream = process.getOutputStream();
        outputStream.write(command.getBytes());
        outputStream.flush();
        outputStream.close();
        process.waitFor();
    }
}
