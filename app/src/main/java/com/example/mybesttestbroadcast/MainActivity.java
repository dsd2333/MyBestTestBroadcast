package com.example.mybesttestbroadcast;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends BaseActivity {

    private Button force_offline;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        force_offline = (Button) findViewById(R.id.force_offline);
        force_offline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.mybesttestbroadcast.FORCE_OFFLINE");
                sendBroadcast(intent);
            }
        });
    }
}
