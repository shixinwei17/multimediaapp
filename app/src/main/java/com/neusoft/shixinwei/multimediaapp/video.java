package com.neusoft.shixinwei.multimediaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class video extends AppCompatActivity {

    List<Map<String, String>> mediaList;
    ListView listView;
    SimpleAdapter adapter;
    public static int c=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        mediaList = new ArrayList<>();
        listView = findViewById(R.id.listView);
        adapter = new SimpleAdapter(this,mediaList,R.layout.listitem,
                new String[]{"title","author","duration","size"},
                new int[]{R.id.tvTitle,R.id.tvAuthor,R.id.tvDuration,R.id.tvSize});
        listView.setAdapter(adapter);

        //请求访问存储权限
        if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
        getAllMediaList(this);
        adapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //获取数据
                Map<String,String>map=(Map<String, String>)parent.getAdapter().getItem(position);
                //跳转详细页面
                Intent intent=new Intent();
                intent.setClass(video.this,MainActivity.class);
                c=1;
                //携带数据打包
                intent.putExtra("path1",map.get("path"));
                intent.putExtra("title1",map.get("title"));
                intent.putExtra("author1",map.get("author"));
                intent.putExtra("duration1",map.get("duration"));
                intent.putExtra("size1",map.get("size"));
                //启动新的页面
                startActivity(intent);
            }
        });
    }
    List<Map<String, String>> getAllMediaList(Context context) {
        mediaList.clear();
        Uri uri;
        String[] projection;
            uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
            projection = new String[]{
                    MediaStore.Video.Media.TITLE,
                    MediaStore.Video.Media.ARTIST,
                    MediaStore.Video.Media.DATA,
                    MediaStore.Video.Media.DURATION,
                    MediaStore.Video.Media.SIZE
            };
        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
        while (cursor.moveToNext()) {
            if(Integer.parseInt(cursor.getString(3))>10000) {
                Map<String, String> m = new HashMap<>();
                m.put("title", cursor.getString(0));
                m.put("author", cursor.getString(1));
                m.put("path", cursor.getString(2));
                m.put("duration",Integer.toString(Integer.parseInt(cursor.getString(3))/60000)+":"+Integer.toString(Integer.parseInt(cursor.getString(3))/1000%60));
                m.put("size",String.format("%.1f",Float.parseFloat(cursor.getString(4))/1048576));
                mediaList.add(m);
                Log.d("tag", m.toString());
            }
            else{continue;}
        }
        return mediaList;
    }
}
