package com.neusoft.shixinwei.multimediaapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBHelper2 {
    private static final String TAG ="DBHelper2";
    SQLiteDatabase db;
    Context context;
    public static String path;

    public DBHelper2(Context context){
        this.context=context;
    }
    public boolean open(){
        path=context.getFilesDir()+"/"+activity_login.inputName+"2.db";
        db=SQLiteDatabase.openOrCreateDatabase(path,null);
        //创建表格
        String sql="create table if not exists "+activity_login.inputName+
                "(title varchar(100) primary key, author varchar(100),"+
                "duration varchar(100),size varchar(100),path varchar(100))";
        try {
            db.execSQL(sql);
            return true;
        }catch(Exception e){
            Log.e(TAG,"open:error"+e.toString());
            e.printStackTrace();
            return false;
        }
    }
    public boolean intert(String title, String author, String duration,String size,String path){
        if(db!=null&&db.isOpen()){
            ContentValues values=new ContentValues();
            values.put("title",title);
            values.put("author",author);
            values.put("duration",duration);
            values.put("size",size);
            values.put("path",path);
            long c=db.insert(activity_login.inputName,null,values);
            if(c>0)
                return true;
            else return false;
        }else {
            return false;
        }
    }
}
