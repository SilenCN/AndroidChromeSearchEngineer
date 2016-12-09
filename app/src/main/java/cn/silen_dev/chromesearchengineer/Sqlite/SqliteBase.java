package cn.silen_dev.chromesearchengineer.Sqlite;

import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import cn.silen_dev.chromesearchengineer.AppConf;

/**
 * Created by silen on 16-11-20.
 */

public class SqliteBase {
    private static SQLiteDatabase sqLiteDatabase;
    public static SQLiteDatabase getSqlLiteDatabase(){
        if (null==sqLiteDatabase||!sqLiteDatabase.isOpen()){
            sqLiteDatabase=SQLiteDatabase.openDatabase(AppConf.SAVE_PATH+"/Web Data",null,0);
        }
        return sqLiteDatabase;
    }
}
