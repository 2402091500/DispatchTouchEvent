package com.example.administrator.dispatchtouchevent;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {


    private Topbar topbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        topbar=(Topbar)findViewById(R.id.topbar);
        topbar.setOnTopbarClicklistener(new Topbar.TopbarListener() {
            @Override
            public void onleftclick() {
                Toast.makeText(MainActivity.this,"左边被点击了",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onrightclick() {
                Toast.makeText(MainActivity.this,"右边被点击了",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void ontitleclick() {

                Toast.makeText(MainActivity.this,"中间被点击了",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
