package com.ccx.demochenapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author chuangxin.chen
 */
public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // 设置Toolbar
        ImageButton left_btn = findViewById(R.id.left_btn);
        left_btn.setOnClickListener(view -> {
            onBackPressed();
        });

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String dataTime = intent.getStringExtra("dataTime");
        String author1 = intent.getStringExtra("author");
        int[] icon = intent.getIntArrayExtra("icon");
        String newsContent = intent.getStringExtra("newsContent");

        TextView textView = findViewById(R.id.textView);
        TextView time = findViewById(R.id.time);
        TextView author = findViewById(R.id.author);
        ImageView newsDetails_image1 = findViewById(R.id.newsDetails_image);
        ImageView newsDetails_image2 = findViewById(R.id.newsDetails_image2);
        TextView newsContent1 = findViewById(R.id.newsContent);
        TextView newsContent2 = findViewById(R.id.newsContent2);

        textView.setText(title);
        time.setText(dataTime);
        author.setText(author1);

        if (icon.length > 1) {
            String substring = newsContent.substring(0, newsContent.length() / 2);
            String substring1 = newsContent.substring(newsContent.length() / 2);
            newsContent1.setText(substring);
            newsContent2.setText(substring1);
            newsDetails_image1.setImageResource(icon[0]);
            newsDetails_image2.setImageResource(icon[1]);
        } else {
            newsContent1.setText(newsContent);
            newsDetails_image1.setImageResource(icon[0]);
        }
    }
}