package com.ccx.demochenapp;

import android.icu.text.SimpleDateFormat;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Calendar;

/**
 * @author chuangxin.chen
 */
public class TimeFragment extends Fragment {

    private ClockView clockView;
    private TextView time_text;
    private Handler handler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View inflate = inflater.inflate(R.layout.fragment_time, container, false);
        clockView = inflate.findViewById(R.id.clock);
        time_text = inflate.findViewById(R.id.time_text);
        //创建线程去执行
        HandlerThread handlerThread = new HandlerThread("TimeHandler");
        handlerThread.start();
        handler = new Handler(handlerThread.getLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                updateTime();
                sendEmptyMessageDelayed(0, 1);

            }
        };
        handler.sendEmptyMessage(0);
        return inflate;
    }

    /**
     * 更新时间
     */
    private void updateTime() {
        clockView.post(new Runnable() {
            @Override
            public void run() {
                clockView.invalidate();
            }
        });
        time_text.post(new Runnable() {
            @Override
            public void run() {
                // 获取当前时间
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
                String currentTime = sdf.format(calendar.getTime());
                time_text.setText(currentTime);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}