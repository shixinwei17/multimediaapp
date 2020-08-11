package com.neusoft.shixinwei.multimediaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class activity_myself extends AppCompatActivity {
    List<Map<String, String>> musicList;
    List<Map<String, String>> videoList;
    SimpleAdapter adapter1;
    SimpleAdapter adapter2;
    ListView listView1;
    ListView listView2;
    SQLiteDatabase db1;
    SQLiteDatabase db2;
    DBHelper1 dbHelper;
    DBHelper2 dbHelper2;
    public static int a=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myself);

        DBHelper db=new DBHelper(activity_myself.this);
        db.open();
        TextView textView=findViewById(R.id.textView9);
        TextView textView1=findViewById(R.id.textView15);
        textView.setText(activity_login.inputName);
        textView1.setText(db.getRealname(activity_login.inputName));

        musicList = new ArrayList<>();
        listView1 = findViewById(R.id.music);
        dbHelper=new DBHelper1(this);
        dbHelper.open();
        adapter1 = new SimpleAdapter(this,musicList,R.layout.listitem1,
                new String[]{"title","author","duration","size","path"},
                new int[]{R.id.tvTitle2,R.id.tvAuthor2,R.id.tvDuration2,R.id.tvSize2,R.id.tvPath});
        listView1.setAdapter(adapter1);
        getAllmusicList();
        adapter1.notifyDataSetChanged();

        videoList = new ArrayList<>();
        listView2 = findViewById(R.id.video);
        dbHelper2=new DBHelper2(this);
        dbHelper2.open();
        adapter2 = new SimpleAdapter(this,videoList,R.layout.listitem2,
                new String[]{"title","author","duration","size","path"},
                new int[]{R.id.tvTitle3,R.id.tvAuthor3,R.id.tvDuration3,R.id.tvSize3,R.id.tvPath2});
        listView2.setAdapter(adapter2);
        getAllvideoList();
        adapter2.notifyDataSetChanged();

        listView1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String,String>map=(Map<String, String>)parent.getAdapter().getItem(position);
                //跳转详细页面
                Intent intent=new Intent();
                intent.setClass(activity_myself.this,MainActivity.class);
                music.b=1;
                //携带数据打包
                intent.putExtra("path",map.get("path"));
                intent.putExtra("title",map.get("title"));
                intent.putExtra("author",map.get("author"));
                intent.putExtra("duration",map.get("duration"));
                intent.putExtra("size",map.get("size"));
                //启动新的页面
                startActivity(intent);
                return true;
            }
        });
        listView2.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String,String>map=(Map<String, String>)parent.getAdapter().getItem(position);
                //跳转详细页面
                Intent intent=new Intent();
                intent.setClass(activity_myself.this,MainActivity.class);
                video.c=1;
                //携带数据打包
                intent.putExtra("path1",map.get("path"));
                intent.putExtra("title1",map.get("title"));
                intent.putExtra("author1",map.get("author"));
                intent.putExtra("duration1",map.get("duration"));
                intent.putExtra("size1",map.get("size"));
                //启动新的页面
                startActivity(intent);
                return true;
            }
        });
    }
    public void exit(View view){
        Intent i=new Intent();
        i.setClass(this,MainActivity.class);
        activity_login.a=0;
        startActivity(i);
    }

    List<Map<String, String>> getAllmusicList() {
        musicList.clear();
        db1=SQLiteDatabase.openOrCreateDatabase(DBHelper1.path,null);
        Cursor cursor = db1.query(activity_login.inputName, null, null, null, null,null, null);
        while (cursor.moveToNext()) {
                Map<String, String> m = new HashMap<>();
                m.put("title", cursor.getString(cursor.getColumnIndex("title")));
                m.put("author", cursor.getString(cursor.getColumnIndex("author")));
                m.put("path", cursor.getString(cursor.getColumnIndex("path")));
                m.put("duration", cursor.getString(cursor.getColumnIndex("duration")));
                m.put("size", cursor.getString(cursor.getColumnIndex("size")));
                musicList.add(m);
        }
        return musicList;
    }
    List<Map<String, String>> getAllvideoList() {
        videoList.clear();
        db2=SQLiteDatabase.openOrCreateDatabase(DBHelper2.path,null);
        Cursor cursor = db2.query(activity_login.inputName, null, null, null, null,null, null);
        while (cursor.moveToNext()) {
            Map<String, String> m = new HashMap<>();
            m.put("title", cursor.getString(cursor.getColumnIndex("title")));
            m.put("author", cursor.getString(cursor.getColumnIndex("author")));
            m.put("path", cursor.getString(cursor.getColumnIndex("path")));
            m.put("duration", cursor.getString(cursor.getColumnIndex("duration")));
            m.put("size", cursor.getString(cursor.getColumnIndex("size")));
            videoList.add(m);
        }
        return videoList;
    }

}
