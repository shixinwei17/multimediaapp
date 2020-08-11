package com.neusoft.shixinwei.multimediaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    MediaPlayer player;
    ProgressBar progressBar;
    VideoView videoView;
    MediaController controller;
    String path;
    String title;
    String author;
    String duration;
    String size;
    String path1;
    String title1;
    String author1;
    String duration1;
    String size1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar=findViewById(R.id.progressBar);
        videoView=findViewById(R.id.videoView);

        Intent intent=getIntent();
        path=intent.getStringExtra("path");
        title=intent.getStringExtra("title");
        author=intent.getStringExtra("author");
        duration=intent.getStringExtra("duration");
        size=intent.getStringExtra("size");
        path1=intent.getStringExtra("path1");
        title1=intent.getStringExtra("title1");
        author1=intent.getStringExtra("author1");
        duration1=intent.getStringExtra("duration1");
        size1=intent.getStringExtra("size1");

        if(music.b!=0){
        player=MediaPlayer.create(this, Uri.parse(path));

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    if (player.isPlaying()){
                        progressBar.setProgress(player.getCurrentPosition());
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();}
        if(path1!=null){
            videoView.setVideoURI(Uri.parse(path1));
            videoView.start();
            controller=new MediaController(this);
            videoView.setMediaController(controller);
        }
    }

    public void openmusic(View view){
        Intent i=new Intent();
        i.setClass(this,music.class);
        startActivity(i);
    }
    public void openvideo(View view){
        Intent i=new Intent();
        i.setClass(this,video.class);
        startActivity(i);
    }
    public void onstart(View view){
        if (player!=null){
            progressBar.setMax(player.getDuration());
            player.start();}
    }
    public void onpause(View view){
        if(path!=null){
        player.pause();}
    }
    public void openmyself(View view){
        Intent i=new Intent();
        if(activity_login.a==0)
            i.setClass(this,activity_login.class);
        else if(activity_login.a==1)
            i.setClass(this,activity_myself.class);
        startActivity(i);
    }
    public void musiccollection(View view){
        if(activity_login.a==0){
            Toast.makeText(this,"收藏功能登录后才能使用！",Toast.LENGTH_SHORT).show();
        }
        else{
            if(music.b!=0){
                boolean ret;
                DBHelper1 dbHelper=new DBHelper1(this);
                dbHelper.open();
                ret=dbHelper.intert(title,author,duration,size,path);
                Toast.makeText(this,"收藏成功！",Toast.LENGTH_SHORT).show();
                if(!ret){
                    Toast.makeText(this,"数据库操作失败！",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
    public void videocollection(View view){
        if(activity_login.a==0){
            Toast.makeText(this,"收藏功能登录后才能使用！",Toast.LENGTH_SHORT).show();
        }
        else{
            if(video.c!=0){
                boolean ret;
                DBHelper2 dbHelper=new DBHelper2(this);
                dbHelper.open();
                ret=dbHelper.intert(title1,author1,duration1,size1,path1);
                Toast.makeText(this,"收藏成功！",Toast.LENGTH_SHORT).show();
                if(!ret){
                    Toast.makeText(this,"数据库操作失败！",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
    public void search(View view){
        Intent i=new Intent();
        i.setClass(this,webview.class);
        startActivity(i);
    }
}
