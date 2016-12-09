package cn.silen_dev.chromesearchengineer.Sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import cn.silen_dev.chromesearchengineer.Chrome.Engineer.Model.ShortEngineer;
import cn.silen_dev.chromesearchengineer.Chrome.Engineer.Server.EngineerServer;

/**
 * Created by silen on 16-11-20.
 */

public class SearchEngineerDB {

    private static final String  GET_ALL_ENGINEER_SQL= "SELECT * FROM keywords";
    private SQLiteDatabase sqLiteDatabase;

    public SearchEngineerDB() {
        sqLiteDatabase=SqliteBase.getSqlLiteDatabase();
    }
    public List<ShortEngineer> getAllEngineer(){
        List<ShortEngineer> shortEngineerList=new ArrayList<>();
        Cursor cursor=sqLiteDatabase.rawQuery(GET_ALL_ENGINEER_SQL,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            ShortEngineer engineer=new ShortEngineer();
            engineer.setId(cursor.getInt(cursor.getColumnIndex("id")));
            engineer.setKeyword(cursor.getString(cursor.getColumnIndex("keyword")));
            engineer.setFavicon_url(cursor.getString(cursor.getColumnIndex("favicon_url")));
            engineer.setPrepopulate_id(cursor.getInt(cursor.getColumnIndex("prepopulate_id")));
            engineer.setShort_name(cursor.getString(cursor.getColumnIndex("short_name")));
            engineer.setShow_in_default_list(cursor.getInt(cursor.getColumnIndex("show_in_default_list"))==1?true:false);
            engineer.setUrl(cursor.getString(cursor.getColumnIndex("url")));
            engineer.setInput_encodings(cursor.getString(cursor.getColumnIndex("input_encodings")));
            shortEngineerList.add(engineer);
            cursor.moveToNext();
        }
        EngineerServer.setShortEngineerList(shortEngineerList);
        return shortEngineerList;
    }
    public void updateEngineer(ShortEngineer engineer){
        ContentValues values=new ContentValues();
        values.put("short_name",engineer.getShort_name());
        values.put("keyword",engineer.getKeyword());
        values.put("url",engineer.getUrl());
        values.put("show_in_default_list",engineer.isShow_in_default_list());
        values.put("prepopulate_id",engineer.getPrepopulate_id());
        sqLiteDatabase.update("keywords",values,"id="+engineer.getId(),null);
    }
    public void insertEngineer(ShortEngineer engineer){
        ContentValues values=new ContentValues();
        values.put("short_name",engineer.getShort_name());
        values.put("keyword",engineer.getKeyword());
        values.put("url",engineer.getUrl());
        values.put("show_in_default_list",engineer.isShow_in_default_list());

        values.put("favicon_url","https://www.baidu.com/favicon.ico");
        values.put("prepopulate_id",engineer.getPrepopulate_id());
        sqLiteDatabase.insert("keywords",null,values);
    }
    public void deleteEngineer(ShortEngineer shortEngineer){
        sqLiteDatabase.delete("keywords","id="+shortEngineer.getId(),null);
    }
}
