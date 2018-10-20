package com.example.mybesttestbroadcast;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by 杜松阳 on 2018/10/20.
 * 该类作为所有活动的父类，由于该类继承自AppCompatActivity，
 * 因此该类的子类也同样具有AppCompatActivity的所有方法。
 * 由于我们想要所有的程序都可以拥有该强制下线功能，
 * 因此我们把它写在所有类的继承类中，成为我们所有类所共有的方法。
 */

public class BaseActivity extends AppCompatActivity {
    private ForceOfflineReceiver receiver;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.mybesttestbroadcast.FORCE_OFFLINE");
        receiver = new ForceOfflineReceiver();
        registerReceiver(receiver,intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (receiver != null){
            unregisterReceiver(receiver);
            receiver = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}

class ForceOfflineReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(final Context context, final Intent intent) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);//构建一个对话框
        builder.setTitle("Warning");
        builder.setMessage("You are forced to be offline,Please try to login again");
        builder.setCancelable(false);
        //使用setCancelable()将对话框设置为不可取消，否则用户点击back以后就可以继续使用程序了，违背初衷。
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            //setPostitiveButton()来对对话框注册确定按钮。
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ActivityCollector.finishAll();//销毁所有活动
                Intent intent = new Intent(context,LoginActivity.class);
                context.startActivity(intent);//重新启动LoginActivity。
            }
        });
        builder.show();
    }
}
