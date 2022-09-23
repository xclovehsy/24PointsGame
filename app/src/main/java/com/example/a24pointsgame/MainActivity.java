package com.example.a24pointsgame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button playBnt;
    private Button guideBnt;
    private Button aboutBnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化组件
        initComponent();

        // 添加响应事件
        addListener();


    }

    /**
     * 添加响应事件
     */
    private void addListener() {
        playBnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, GameActivity.class));
            }
        });

        guideBnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Guide.class));
            }
        });

        aboutBnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AboutMe.class));
            }
        });
    }

    /**
     * 添加组件
     */
    private void initComponent() {
        playBnt = findViewById(R.id.play);
        guideBnt = findViewById(R.id.guide);
        aboutBnt = findViewById(R.id.about);


    }
}